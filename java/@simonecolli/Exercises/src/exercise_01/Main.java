package exercise_01;

public class Main {
	public static void main(String[] args) {
		new Main().go();
	}
	public void go() {
		Activity a = new ActivityImpl();
		for(int i = 0; i < 50; ++i) {
			a.perform(new PrintedClass(i)).start();
		}
	}
	
	
	private class PrintedClass {
		private int id_;
		private PrintedClass(int id) {
			id_ = id;
		}
		@Override
		public String toString() {
			return "id_: " + id_;
		}
	}
}
