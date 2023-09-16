package it.unipr.informatica.exercise;

public class Logger {
	private static volatile Logger instance;
	
	public static Logger getInstance() {
		if (instance == null)
			synchronized (Logger.class) {
				if (instance == null)
					instance = new Logger();
			}
		
		return instance;
	}
	
	public int useAndPrint(Resource r1, Resource r2, Resource r3) {
		int t = r1.use() + r2.use() + r3.use();
		
		System.out.println(t);
		
		return t;
	}
	
	private Logger() {
		// Blank
	}
}
