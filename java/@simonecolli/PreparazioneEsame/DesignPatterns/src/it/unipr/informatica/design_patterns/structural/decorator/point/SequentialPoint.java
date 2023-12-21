package it.unipr.informatica.design_patterns.structural.decorator.point;

public class SequentialPoint implements DiagonalDraggablePoint {

	private int x_;
	private int y_;
	
	public SequentialPoint() {
		this.x_ = 0;
		this.y_ = 0;
	}
	
	@Override
	public void moveDiagonal(int distance, String draggerName) {
		System.out.println("Moved by " + draggerName + ": Origin x=" + x_ + " y=" + y_);
		x_ += distance;
		y_ += distance;
	}

	@Override
	public void currentPosition() {
		System.out.println("Current position: x=" + x_ + " y=" + y_);
	}
	
}
