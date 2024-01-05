package it.unipr.informatica.esercizi.esercizio_decoupled;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayDeque;
import java.util.Deque;

import javax.swing.plaf.synth.SynthOptionPaneUI;

/**
 * @author Di Agostino Manuel
 * https://github.com/manueldiagostino
 */
public class DecoupledAspect2 {
	
	@SuppressWarnings("unchecked")
	public static <T> T attach(Object target) {
		if (target == null)
			throw new IllegalArgumentException("target == null");
		
		Class<?> clazz = target.getClass();
		Class<?>[] interfaces = clazz.getInterfaces();
				
		Object res = Proxy.newProxyInstance(
				clazz.getClassLoader(),
				interfaces,
				new InnerInvocationHandler(target));
		
		return (T) res;
	}
	
	protected static class InnerInvocationHandler implements InvocationHandler {
		private final Object target;
		
		public InnerInvocationHandler(Object o) {
			this.target = o;
		}
		
		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			Result result = new Result();
			
			Runnable runnable = () -> {
				Object returnedObj = null;
				try {
					returnedObj = method.invoke(target, args);
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				synchronized (target) {					
					result.setValue(returnedObj);
					target.notifyAll();
				}
			};
			
			synchronized (target) {
				new Thread(runnable).start();
				target.wait();
				
				return result.getValue();				
			}
		}
		
		private static class Result {
			private Object value = null;
			
			public void setValue(Object value) {
				this.value = value;
			}
			
			public Object getValue() {
				return this.value;
			}
		}
	}
	
	public static void main(String[] args) {
		
		Deque<Integer> list = DecoupledAspect2.attach(new ArrayDeque<Integer>());
	
		System.out.println(list.add(1));
		System.out.println(list.add(2));
		System.out.println(list.add(3));
		System.out.println(list.removeFirst());
		System.out.println(list.removeFirst());
		System.out.println(list.removeFirst());
	}
}
