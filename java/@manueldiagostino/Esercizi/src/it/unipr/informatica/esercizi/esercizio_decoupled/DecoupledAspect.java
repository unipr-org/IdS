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
public class DecoupledAspect {
	
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
			Future result = new FutureImpl();
			
			Runnable runnable = () -> {
				synchronized (target) {
					try {
						result.setValue(method.invoke(target, args));
					} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			};
			
			new Thread(runnable).start();
			
			return result.getValue();
		}
	}
	
	public static void main(String[] args) {
		
		Deque<Integer> list = DecoupledAspect.attach(new ArrayDeque<Integer>());
	
		System.out.println(list.add(1));
		System.out.println(list.add(2));
		System.out.println(list.add(3));
		System.out.println(list.removeFirst());
		System.out.println(list.removeFirst());
		System.out.println(list.removeFirst());
	}
}
