package it.unipr.informatica.design_patterns.structural.adapter.polygon;

public class RectangleClassAdapter extends Rectangle implements Polygon{
	
	private String name_;
	public RectangleClassAdapter() {
		super();
		this.name_  = "unknowk";
	}
	
	@Override
	public void define(float x0, float y0, float x1, float y1, String color) {
		super.setShape(x0, y0, x1-x0, y1-y0, color);
	}

	@Override
	public float[] getCoordinates() {
		float tmp[] = new float[4];
		tmp[0] = super.getOriginX();
		tmp[1] = super.getOriginY();
		tmp[2] = super.getOppositeCornerX();
		tmp[3] = super.getOppositeCornerY();
		
		return tmp;
	}

	@Override
	public float getSurface() {
		return super.getArea();
	}

	@Override
	public void setId(String id) {
		name_ = id;
	}

	@Override
	public String getId() {
		return name_;
	}

}
