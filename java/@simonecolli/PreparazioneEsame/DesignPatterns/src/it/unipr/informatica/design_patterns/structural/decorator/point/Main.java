package it.unipr.informatica.design_patterns.structural.decorator.point;

public class Main {
	public static void main(String[] args) {
			new Main().run();
	}

	public void run() {
		System.out.println( "Non synchronized point:" );
		
		DiagonalDraggablePoint p = new SequentialPoint();
		
		PointDragger mp1 = new PointDragger(p, "Thread 1");
		PointDragger mp2 = new PointDragger(p, "Thread 2");
		Thread t1 = new Thread(mp1);
		Thread t2 = new Thread(mp2);
		t1.start();
		t2.start();
		
		while(t1.isAlive() || t2.isAlive());
			p.currentPosition();
		
		
		System.out.println( "Synchronized point:" );
		
		p = new SynchronizedPoint(new SequentialPoint());
		mp1 = new PointDragger(p, "Thread 1");
		mp2 = new PointDragger(p, "Thread 2");
		t1 = new Thread(mp1);
		t2 = new Thread(mp2);
		t1.start();
		t2.start();
		
		while(t1.isAlive() || t2.isAlive());
			p.currentPosition();
	
	}
	private class PointDragger implements Runnable {
		private DiagonalDraggablePoint point_;
		private String name_;
		
		private PointDragger(DiagonalDraggablePoint p, String name) {
			point_ = p;
			name_ = name;
		}
		@Override
		public void run() {
			for( int i=1; i < 5; i++ ) {
				point_.moveDiagonal(1, name_);
			}
		}
	}
}
