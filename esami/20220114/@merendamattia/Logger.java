package it.unipr.informatica.exams.exam_20220114;

public class Logger {
	private static Logger INSTANCE = null;
	private Logger() { }
	public static Logger getInstance() {
		if(INSTANCE == null) {
			synchronized (Logger.class) {
				if(INSTANCE == null)
					INSTANCE = new Logger();
			}
		}
		return INSTANCE;
	}
	// ! Singleton
	
	public void useAndPrint(Resource r1, Resource r2, Resource r3) {
		int t1 = r1.use();
		int t2 = r2.use();
		int t3 = r3.use();
		int sum = t1 + t2 + t3;
		System.out.println("[useAndPrint] r(" + r1.getID() + "): " + t1 + ", r(" + r2.getID() + "): " + t2 + ", r(" + r3.getID() + "): " + t3 + ", sum: " + sum);
	}
} // ! Logger
