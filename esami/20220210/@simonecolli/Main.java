package exam_2022_02_10;


import exam_2022_02_10.concrete.WorkerManagerSingleton;

public class Main {
	public static void main(String[] args) {
		int W = 50;
		WorkerManagerSingleton wm = WorkerManagerSingleton.getInstance();
		wm.createWorker(W);
		for(int i = 0; i < W; ++i) {
			int tmp = i;
			new Thread(() -> {
				wm.getWorker(tmp).execute();
			}).start();
		}
	}
}
