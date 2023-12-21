package it.unipr.informatica.design_patterns.structural.adapter.polygon;

public class RectangleObjectAdapter implements Polygon{
	private Rectangle rectangle_;
	private String name_;
	public RectangleObjectAdapter() {
		rectangle_ = new Rectangle();
		name_ = "unknown";
	}
	@Override
	public void define(float x0, float y0, float x1, float y1, String color) {
		rectangle_.setShape(x0, y0, x0 - x1, y0 - y1, color);
	}
	
	@Override
	public float[] getCoordinates() {
		float tmp[] = new float[4];
		tmp[0] = rectangle_.getOriginX();
		tmp[1] = rectangle_.getOriginY();
		tmp[2] = rectangle_.getOppositeCornerX();
		tmp[3] = rectangle_.getOppositeCornerY();
		
		return tmp;
	}
	@Override
	public float getSurface() {
		return rectangle_.getArea();
	}
	@Override
	public void setId(String id) {
		name_ = id;
	}
	@Override
	public String getId() {
		return name_;
	}
	@Override
	public String getColor() {
		return rectangle_.getColor();
	}
	
	
}
