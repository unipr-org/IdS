package it.unipr.informatica.exams.exam_20230112.teoria;

public class AImpl implements A {

	@Override
	public void m(int min, int max, Callback<Integer> callback) {
		new Thread(() -> {
			int time = (int) (Math.random() * (max - min) + min);
			
			try {
				Thread.sleep(time);
				callback.onSuccess(time);
			} catch (InterruptedException e) {
				callback.onFailure(e);
			}
			
		}).start();
	}

} // ! AImpl
