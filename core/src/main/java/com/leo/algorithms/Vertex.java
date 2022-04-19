package com.leo.algorithms;

import java.util.ArrayList;
import java.util.Objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;


public class Vertex {

	private int positionSinceLastMoved;
	private String name = null;
	private ArrayList<Edge> edgeList;
	private int x,y,radius;
	private int originalX = 0, originalY = 0;
	private float mouseX, mouseY;
	private boolean moving;
	
	public Vertex(String name) {
		this.edgeList = new ArrayList<>();
		this.name = name;
	}
	
	public void addEdge(Vertex destination, Integer weight) {
		Edge edge = new Edge(this, destination, weight);
		this.edgeList.add(edge);
		
	}
	
	public void removeEdge(Vertex destination) {
		this.edgeList.removeIf(edge -> edge.getEndVertex().equals(destination));
		// Clever -> method
	}
	
	public ArrayList<Edge> getEdges(){
		return edgeList;
	}
	
	public String getName() {
		return this.name;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	public boolean hasCoords() {
		boolean xExists = false;
		boolean yExists = false;
		if(!(Objects.isNull(this.x))) xExists = true; //If X ISNT null
		if(!(Objects.isNull(this.y))) yExists = true; //If Y ISNT null
		return xExists & yExists;
	}
	
	public void draw(ShapeRenderer sr, BitmapFont font, Batch batch) {
		sr.begin(ShapeType.Filled);
		sr.setColor(Color.FIREBRICK);
		sr.circle(this.x, this.y, this.radius);
		sr.end();
		
		GlyphLayout text = new GlyphLayout(font, this.name);
		float fontX = (this.x - (text.width /2));
		float fontY = (this.y + (text.height /2));
		
		batch.begin();
		font.draw(batch, text, fontX, fontY);
		batch.end();
				
		//System.out.println("X: " + this.x + ", Y: " + this.y);
	}
	
	public void checkDragged() {
		originalX = (int) mouseX;
		originalY = (int) mouseY;
		
		mouseX = Gdx.input.getX();
		mouseY = Gdx.graphics.getHeight() - Gdx.input.getY();
	
		int dx = (int) (mouseX - this.x);
		int dy = (int) (mouseY - this.y);
		int distance = (int) (Math.hypot(dx,  dy));
		//System.out.println(distance);
		
		if(Gdx.input.isButtonPressed(Buttons.LEFT) & (distance < this.radius)) {
			moving = true;
			int changeX = (int) (originalX - mouseX);
			int changeY = (int) (originalY - mouseY);
			this.setX(x - changeX);
			this.setY(y - changeY);
		}
	}
}
