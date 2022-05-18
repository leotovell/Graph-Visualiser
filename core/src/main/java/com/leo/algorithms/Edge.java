package com.leo.algorithms;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.leo.algorithms.Assets.Resources;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;

public class Edge {
	
	private Vertex start, end;
	private Color color;
	public Color defaultColor = Color.WHITE;
	private Integer weight;
	
	public Edge(Vertex start, Vertex end, Integer weight) {
		this.start = start;
		this.end = end;
		this.weight = weight;
		this.color = defaultColor;
	}
	  
	public void draw(ShapeRenderer sr) {
		sr.begin(ShapeType.Filled);
		sr.setColor(this.color);
		sr.rectLine(this.start.getX(), this.start.getY(), this.end.getX(), this.end.getY(), 5);
		sr.end();
		
		GlyphLayout text = new GlyphLayout(Resources.font, Integer.toString(this.weight));
		
		Resources.batch.begin();
		Color colorBefore = Resources.font.getColor();
		Resources.font.setColor(Color.WHITE);
		Resources.font.draw(Resources.batch, text, getMidpoint()[0] - (text.width / 2), getMidpoint()[1] - 10);
		Resources.batch.end();
		
		Resources.font.setColor(colorBefore);
		
		
	}
	
	public int[] getMidpoint() {
		int x = (start.getX() + end.getX()) / 2;
		int y = (start.getY() + end.getY()) / 2;
		return new int[] {x, y};
	}
	
	@Override
	public String toString() {
		return "Start: " + this.start.getName() + ", End: " + this.end.getName() + ", Weight: " + this.getWeight();
	}
	
	// Getters/Setters
	
	public Vertex getStartVertex() {
		return this.start;
	}
	
	public Vertex getEndVertex() {
		return this.end;
	}
	
	public Integer getWeight() {
		return this.weight;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
}
