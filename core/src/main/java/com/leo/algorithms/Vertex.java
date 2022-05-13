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

/*
 * TODO: 
 * 
 * 
 */

public class Vertex {

	private String name = null;
	private ArrayList<Edge> edgeList;
	private int x,y,radius;
	private float mouseX, mouseY, distance;
	private Color color = Color.FIREBRICK;
	
	public Vertex(String name) {
		this.edgeList = new ArrayList<>();
		this.name = name;
	}

	public void addEdge(Vertex destination, Integer weight, ArrayList<Vertex> allVertices) {
		boolean skipCreation = false;
		if(destination == this) {
			System.out.println("Cannot create edge to self!");
			skipCreation = true;
		}
		
		for(Vertex vertex: allVertices) {
			for(Edge edge: vertex.getEdges()) {
				if((edge.getStartVertex() == this && edge.getEndVertex() == destination) | (edge.getEndVertex() == this && edge.getStartVertex() == destination)) {
					System.out.println("Edge already exists in non-directional graph!");
					skipCreation = true;
				}
			}
		}
		
		if(!skipCreation){
			Edge edge = new Edge(this, destination, weight);
			this.edgeList.add(edge);
			}
		}

	public void removeEdge(Vertex destination) {
		this.edgeList.removeIf(edge -> edge.getEndVertex().equals(destination));
		// Removes edge if the destination of that edge is equal to the destination we want.
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
		sr.setColor(this.color);
		sr.circle(this.x, this.y, this.radius);
		sr.end();

		/*
		GlyphLayout text = new GlyphLayout(font, this.name);
		float fontX = (this.x - (text.width /2));
		float fontY = (this.y + (text.height /2));

		batch.begin();
		font.draw(batch, text, fontX, fontY);
		batch.end();
		*/
	
	}

	public void checkDragged() {
		mouseX = Gdx.input.getX();
		mouseY = Gdx.graphics.getHeight() - Gdx.input.getY();

		int dx = (int) (mouseX - this.x);
		int dy = (int) (mouseY - this.y);
		distance = (int) (Math.hypot(dx,  dy));

		if(Gdx.input.isButtonPressed(Buttons.LEFT) & (distance < this.radius)) {
			this.setX(x + Gdx.input.getDeltaX());
			this.setY(y - Gdx.input.getDeltaY());
		}
	}
	
	public boolean checkClicked() {
		boolean clicked = false;
		if(Gdx.input.isButtonJustPressed(Buttons.LEFT) & (distance < this.radius)) clicked = true;
		return clicked;
	}
	
	public void setColor(Color color) {
		this.color = color;
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

	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}

}
