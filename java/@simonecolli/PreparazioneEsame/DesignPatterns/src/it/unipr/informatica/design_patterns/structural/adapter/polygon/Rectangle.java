package it.unipr.informatica.design_patterns.structural.adapter.polygon;

public class Rectangle {
	
	private float x_;
	private float y_;
	private float wide_;
	private float height_;
	private String color_;
	
	public Rectangle() {
	}
	
	public void setShape(float x, float y, float wide, float height, String color) {
		this.x_ = x;
		this.y_ = y;
		this.wide_ = wide;
		this.height_ = height;
		this.color_ = color;
	}
	public float getArea() {
		return height_*wide_;
	}
	public float getOriginX() {
		return x_;
	}
	 
	public float getOriginY() {
		return y_;
	}
	public String getColor() {
		return color_;
	}
	public float getOppositeCornerX() {
		return x_ + height_;		
	}

	public float getOppositeCornerY() {
		return y_ + wide_;
	}
	
}
