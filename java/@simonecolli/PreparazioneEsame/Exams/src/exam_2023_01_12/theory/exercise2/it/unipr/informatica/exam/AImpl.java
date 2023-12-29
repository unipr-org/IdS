package exam_2023_01_12.theory.exercise2.it.unipr.informatica.exam;

public class AImpl implements A{

	@Override
	public void m(int min, int max, Callback<Integer> callback) {
		new Thread(
			() -> {
				int random = (int)(Math.random() * (max-1 - min) + (min+1));
				try {
					Thread.sleep(random);
					synchronized (callback.getClass()) {
						callback.onSuccess(random);					
					}
				} catch (InterruptedException e) {
					callback.onFailure(e);			
				}
			}
		).start();
	}

}
