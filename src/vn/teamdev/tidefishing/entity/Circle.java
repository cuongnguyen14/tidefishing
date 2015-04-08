package vn.teamdev.tidefishing.entity;

public class Circle {
	
	public Circle(float cx, float cy, float radius) {
		super();
		this.cx = cx;
		this.cy = cy;
		this.radius = radius;
	}

	private float cx, cy, radius;

	public float getCx() {
		return cx;
	}

	public void setCx(float cx) {
		this.cx = cx;
	}

	public float getCy() {
		return cy;
	}

	public void setCy(float cy) {
		this.cy = cy;
	}

	public float getRadius() {
		return radius;
	}

	public void setRadius(float radius) {
		this.radius = radius;
	}
}
