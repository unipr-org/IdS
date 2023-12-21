package it.unipr.informatica.design_patterns.structural.decorator.point;

// si può utilizza un decorator per rendere threadsafe un oggetto non threadsafe
public class SynchronizedPoint implements DiagonalDraggablePoint{
	
	private DiagonalDraggablePoint points_;

//	non è necessario creare un oggetto apposito per fare da mutex,
//	si potrebbe usare poins_
	
	private Object mutex_;
	
	public SynchronizedPoint(DiagonalDraggablePoint points) {
		this.points_ = points;
		mutex_ = new Object();
	}
	
	@Override
	public void moveDiagonal(int distance, String draggerName) {
		synchronized (mutex_) {
			points_.moveDiagonal(distance, draggerName);
		}
	}

	@Override
	public void currentPosition() {
		synchronized (mutex_) {
			points_.currentPosition();
		}
	}
	
}
