package com.leo.algorithms;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class Edge {
	
	private Vertex start, end;
	private Integer weight;
	
	public Edge(Vertex start, Vertex end, Integer weight) {
		this.start = start;
		this.end = end;
		this.weight = weight;
	}

	public Vertex getStartVertex() {
		return this.start;
	}
	
	public Vertex getEndVertex() {
		return this.end;
	}
	
	public Integer getWeight() {
		return this.weight;
	}
	
	@Override
	public String toString() {
		return "Start: " + this.start.getName() + ", End: " + this.end.getName() + ", Weight: " + this.getWeight();
	}
	
	public void draw(ShapeRenderer sr) {
		sr.begin(ShapeType.Filled);
		sr.setColor(Color.WHITE);
		//sr.line(this.start.getX(), this.start.getY(), this.end.getX(), this.end.getY());
		sr.rectLine(this.start.getX(), this.start.getY(), this.end.getX(), this.end.getY(), 5);
		sr.end();
	}
	
}
