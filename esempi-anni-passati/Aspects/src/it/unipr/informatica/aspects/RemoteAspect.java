/*
 * RemoteAspect
 *
 * (c) 2021 Federico Bergenti. All Rights Reserved.
 */
package it.unipr.informatica.aspects;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

import it.unipr.informatica.concurrent.ExecutorService;
import it.unipr.informatica.concurrent.Executors;

public class RemoteAspect {
	public static <T> RemoteHandler<T> attach(Class<T> remoteInterface, T target, int port) throws IOException {
		return attach(remoteInterface, target, port, 10);
	}

	public static <T> RemoteHandler<T> attach(Class<T> remoteInterface, T target, int port, int poolSize)
			throws IOException {
		if (remoteInterface == null)
			throw new IllegalArgumentException("remoteInterface == null");

		if (target == null)
			throw new IllegalArgumentException("target == null");

		if (port < 1 || port > 65535)
			throw new IllegalArgumentException("port < 1 || port > 65535");

		ServerSocket serverSocket = new ServerSocket(port);

		return new InnerRemoteHandler<T>(serverSocket, target, poolSize);
	}

	public static <T> T connect(Class<T> remoteInterface, String host, int port) throws IOException {
		if (remoteInterface == null)
			throw new IllegalArgumentException("remoteInterface == null");

		if (host == null || host.length() == 0)
			throw new IllegalArgumentException("host == null || host.length() == 0");

		if (port < 1 || port > 65535)
			throw new IllegalArgumentException("port < 1 || port > 65535");

		@SuppressWarnings("unchecked")
		T result = (T) Proxy.newProxyInstance(remoteInterface.getClassLoader(), new Class<?>[] { remoteInterface },
				new InnerInvocationHandler(host, port));

		return result;
	}

	private static class InnerRemoteHandler<T> implements RemoteHandler<T> {
		private Object target;

		private ExecutorService executorService;

		private ServerSocket serverSocket;

		private InnerRemoteHandler(ServerSocket serverSocket, Object target, int poolSize) {
			this.serverSocket = serverSocket;

			this.target = target;

			this.executorService = Executors.newFixedThreadPool(poolSize + 1);

			executorService.execute(this::serverLoop);
		}

		@Override
		public void shutdown() {
			executorService.shutdown();

			try {
				serverSocket.close();
			} catch (Throwable e) {
				// Blank
			}
		}

		private void serverLoop() {
			try {
				for (;;) {
					Socket socket = serverSocket.accept();

					executorService.execute(() -> serve(socket));
				}
			} catch (SocketException exception) {
				// Blank
			} catch (Throwable throwable) {
				throwable.printStackTrace();
			}
		}

		private void serve(Socket socket) {
			try (InputStream inputStream = socket.getInputStream();
					OutputStream outputStream = socket.getOutputStream();
					ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
					ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);) {
				Object message = objectInputStream.readObject();

				if (!(message instanceof RemoteRequest))
					throw new IllegalStateException("!(message instanceof RemoteRequest)");

				RemoteRequest request = (RemoteRequest) message;

				String methodName = request.getMethodName();

				String[] parameterTypeNames = request.getParameterTypeNames();

				Class<?>[] parameterTypes = new Class[parameterTypeNames.length];

				for (int i = 0; i < parameterTypeNames.length; ++i)
					parameterTypes[i] = getClassFromName(parameterTypeNames[i]);

				Class<?> targetClass = target.getClass();

				Method method = targetClass.getMethod(methodName, parameterTypes);

				RemoteResponse response;

				try {
					Object result = method.invoke(target, request.getArguments());

					if (!(result instanceof Serializable))
						throw new IllegalStateException("!(result instanceof Serializable)");

					response = new InnerRemoteResponse((Serializable) result, null);
				} catch (InvocationTargetException exception) {
					Throwable throwable = exception.getCause();

					if (!(throwable instanceof Serializable))
						throw new IllegalStateException("!(throwable instanceof Serializable)");

					response = new InnerRemoteResponse(null, throwable);
				}

				objectOutputStream.writeObject(response);

				socket.close();
			} catch (Throwable throwable) {
				throwable.printStackTrace();
			}
		}

		private Class<?> getClassFromName(String name) throws ClassNotFoundException {
			switch (name) {
			case "boolean":
				return Boolean.TYPE;
			case "byte":
				return Byte.TYPE;
			case "char":
				return Character.TYPE;
			case "double":
				return Double.TYPE;
			case "float":
				return Float.TYPE;
			case "int":
				return Integer.TYPE;
			case "long":
				return Long.TYPE;
			case "short":
				return Short.TYPE;
			default:
				return Class.forName(name);
			}
		}
	}

	private static class InnerInvocationHandler implements InvocationHandler {
		private String host;

		private int port;

		private InnerInvocationHandler(String host, int port) {
			this.host = host;

			this.port = port;
		}

		@Override
		public Object invoke(Object proxy, Method method, Object[] arguments) throws Throwable {
			String methodName = method.getName();

			Class<?>[] parameterTypes = method.getParameterTypes();

			int parameterCount = parameterTypes.length;

			String[] parameterTypeNames = new String[parameterCount];

			for (int i = 0; i < parameterCount; ++i)
				parameterTypeNames[i] = parameterTypes[i].getName();

			RemoteRequest request = new InnerRemoteRequest(methodName, parameterTypeNames, arguments);

			RemoteResponse response = null;

			try (Socket socket = new Socket(host, port);
					InputStream inputStream = socket.getInputStream();
					OutputStream outputStream = socket.getOutputStream();
					ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);) {
				objectOutputStream.writeObject(request);

				try (ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);) {
					Object message = objectInputStream.readObject();

					if (!(message instanceof RemoteResponse))
						throw new IllegalArgumentException("!(message instanceof RemoteResponse)");

					response = (RemoteResponse) message;
				}
			} catch (Throwable throwable) {
				throw new RemoteException(throwable);
			}

			Throwable exception = response.getException();

			if (exception != null)
				throw exception;

			return response.getResult();
		}
	}

	private static class InnerRemoteRequest implements RemoteRequest {
		private static final long serialVersionUID = 7994546295394535576L;

		private String methodName;

		private String[] parameterTypeNames;

		private Object[] arguments;

		private InnerRemoteRequest(String methodName, String[] parameterTypeNames, Object[] arguments) {
			this.methodName = methodName;

			this.parameterTypeNames = parameterTypeNames;

			this.arguments = arguments;
		}

		@Override
		public String getMethodName() {
			return methodName;
		}

		@Override
		public String[] getParameterTypeNames() {
			return parameterTypeNames;
		}

		@Override
		public Object[] getArguments() {
			return arguments;
		}
	}

	private static class InnerRemoteResponse implements RemoteResponse {
		private static final long serialVersionUID = 8159305632457402638L;

		private Serializable result;

		private Throwable exception;

		public InnerRemoteResponse(Serializable result, Throwable exception) {
			this.result = result;

			this.exception = exception;
		}

		@Override
		public Serializable getResult() {
			return result;
		}

		@Override
		public Throwable getException() {
			return exception;
		}
	}
}
