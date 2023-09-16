package it.unipr.informatica.exercise;

public class Exercise1 {
	private static void work() {
		try {
			int k = (int)Math.floor(100 * Math.random());
			
			Thread.sleep(5000 + 10 * k);
			
			System.out.println(Thread.currentThread().getName() + " " + k);
		} catch (Throwable throwable) {
			// Blank
		}
	}
	
	public static void main(String[] args) {
		Launcher launcher = new LauncherImpl();

		MonitorSet monitorSet = new MonitorSetImpl();
		
		int n = 5;
		
		for (int i = 0; i < n; ++i) {
			Monitor m = launcher.launch(Exercise1::work);
			
			monitorSet.add(m);
		}

		for (int i = 0; i < n; ++i) {
			try {
				monitorSet.await();
			} catch (Throwable throwable) {
				throwable.printStackTrace();
			}
		}
	}
}
