package it.unipr.informatica.design_patterns.structural.adapter.polygon;

public class Main {
	public void objectAdapterTest() {
		Polygon poly = new RectangleObjectAdapter();
		poly.setId("Demo");
		poly.define(3, 4, 20, 30, "red");
		System.out.println("Area of " + poly.getId() + ": " + poly.getSurface());
		System.out.println("Color of " + poly.getId() + ": " + poly.getColor());
	}
	public void classAdapterTest() {
		Polygon poly = new RectangleClassAdapter();
		poly.setId("Demo");
		poly.define(3, 4, 20, 30, "red");
		System.out.println("Area of " + poly.getId() + ": " + poly.getSurface());
		System.out.println("Color of " + poly.getId() + ": " + poly.getColor());
	}
	public static void main(String[] args) {
		new Main().objectAdapterTest();
		new Main().classAdapterTest();
	}
}
