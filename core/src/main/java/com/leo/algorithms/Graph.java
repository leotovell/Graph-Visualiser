package com.leo.algorithms;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.leo.algorithms.Assets.LButton;


public class Graph {

	String name = "Graph";
	ArrayList<Vertex> vertexList = new ArrayList<>();
	ShapeRenderer sr;
	Batch batch;
	Stage stage;
	private LButton addVertexButton, addEdgeButton, removeVertexButton, removeEdgeButton;
	private ArrayList<LButton> editButtons;
	private BitmapFont font;
	private boolean addVertexToggle, addEdgeToggle, removeVertexToggle, removeEdgeToggle;
	private int WINDOW_WIDTH, WINDOW_HEIGHT, VERTEX_RADIUS;
	private Random r;
	
	public Graph(String name) {
		this.name = name;
		sr = new ShapeRenderer();
		batch = new SpriteBatch();
		stage = new Stage(new ScreenViewport());
		font = new BitmapFont();
		font = new BitmapFont();
		font.setColor(Color.WHITE);
		addVertexToggle = addEdgeToggle = removeVertexToggle = removeEdgeToggle = false;
		WINDOW_WIDTH = Gdx.graphics.getWidth();
		WINDOW_HEIGHT = Gdx.graphics.getHeight();
		VERTEX_RADIUS = 15;
		r = new Random();
		
		createUI();
		createDefaultVertices();
		
	}
	
	public void createUI() {
		addVertexButton = new LButton("Add Vertex", Gdx.graphics.getWidth()-520, 10, 120, 30, Color.WHITE, ShapeType.Filled);
		addVertexButton.setClickedColor(Color.GREEN);

		addEdgeButton = new LButton("Add Edge", Gdx.graphics.getWidth()-390, 10, 120, 30, Color.WHITE, ShapeType.Filled);
		addEdgeButton.setClickedColor(Color.GREEN);
		
		removeVertexButton = new LButton("Remove Vertex", Gdx.graphics.getWidth()-260, 10, 120, 30, Color.WHITE, ShapeType.Filled);
		removeVertexButton.setClickedColor(Color.RED);
		
		removeEdgeButton = new LButton("Remove Edge", Gdx.graphics.getWidth()-130, 10, 120, 30, Color.WHITE, ShapeType.Filled);
		removeEdgeButton.setClickedColor(Color.RED);
		
		editButtons = new ArrayList<>();
		editButtons.add(addVertexButton);
		editButtons.add(addEdgeButton);
		editButtons.add(removeVertexButton);
		editButtons.add(removeEdgeButton);

	}
	
	public void createDefaultVertices() {
		String[] verticesToMake = {"a", "b", "c", "d", "1", "2", "3", "4"};
		for(String vertex: verticesToMake) {
			this.addVertex(vertex);
		}
		
		for(int i = 0; i < 0; i++) {
			this.addVertex(String.valueOf(i));
		}
		
		this.addEdge("a", "b", 10);
		this.addEdge("b", "c", 9);
		this.addEdge("c", "d", 8);
		this.addEdge("a", "3", 12);
		this.addEdge("c", "2", 10);
		this.addEdge("c", "1", 10);
		this.addEdge("3", "4", 10);
		this.addEdge("d", "3", 10);
		
		for(Vertex vertex: this.getVertexes()) {
			int x = r.nextInt(WINDOW_WIDTH - 40) + 20;
			int y = r.nextInt(WINDOW_HEIGHT - 80) + 60;
			
			
			while(coordinatesCollide(x, y, vertex, this)) {
				x = r.nextInt(WINDOW_WIDTH - 40) + 20;
				y = r.nextInt(WINDOW_HEIGHT - 80) + 60;
			}
			
			
			vertex.setX(x);
			vertex.setY(y);
			vertex.setRadius(VERTEX_RADIUS);
			}
		}
	
	public boolean coordinatesCollide(int x, int y, Vertex currentVertex, Graph graph) {
		boolean collided = false;
		for(Vertex vertex: graph.getVertexes()) {
			if(vertex != currentVertex & vertex.hasCoords()) {
				if(x < 20) x = 20;
				if(x > WINDOW_WIDTH - 20) x = WINDOW_WIDTH - 20;
				if(y < 20) y = 20;
				if(y > WINDOW_HEIGHT - 20) y = WINDOW_HEIGHT - 20;
				int dx = vertex.getX() - x;
				int dy = vertex.getY() - y;
				int distance = (int) Math.hypot(dx, dy);
				if(distance < 75) {
					collided = true;
				}}}
		return collided;
	}
	
	public void addVertex(String name) {
		Vertex existingVertex = findVertex(name);
		if(Objects.isNull(existingVertex)) {
			Vertex vertex = new Vertex(name);
			this.vertexList.add(vertex);
		}
		else {
			System.out.println("Vertex with name: " + name + ", already exists, process aborted.");			
		}
	}
	
	public void addVertex(int x, int y, ArrayList<Actor> actors) {
		for(Actor actor: actors) {
			actor.setVisible(true);
		}
		
		for(Actor actor: actors) {
			actor.setVisible(false);
		}
		// Ask for weight;
		
	}
	
	public void removeVertex(Vertex vertex) {
		this.vertexList.remove(vertex);
	}
	
	public void addEdge(Vertex source, Vertex destination, Integer weight) {
		source.addEdge(destination, weight);
	}
	
	public Vertex findVertex(String data) {
		Vertex result = null;
		for(Vertex vertex: this.vertexList) {
			if(vertex.getName().equals(data)) {
				result = vertex;
			}
		}
		return result;
	}
	
	public void addEdge(String source, String destination, Integer weight) {
		boolean startFailed = false;
		boolean endFailed = false;
		int callersLineNumber = new Exception().getStackTrace()[1].getLineNumber();
		Vertex start = findVertex(source);
		if(Objects.isNull(start)) {
			startFailed = true;
		}
		Vertex end = findVertex(destination);
		if(Objects.isNull(end)) {
			endFailed = true;			
		}
		if(startFailed) System.out.println("Source vertex: " + source + ", not found. Edge not added. | Line: " + callersLineNumber);
		if(endFailed) System.out.println("Destination vertex: " + destination + ", not found. Edge not added. | Line: " + callersLineNumber);
		if(!(startFailed) & !(endFailed)) {
			start.addEdge(end, weight);
		}
	}

	public void removeEdge(Vertex source, Vertex destination) {
		source.removeEdge(destination);
	}
	
	public ArrayList<Vertex> getVertexes(){
		return this.vertexList;
	}
	
	public void draw() {
		
		this.update();
		
		for(Vertex vertex: this.getVertexes()) {
			for(Edge edge: vertex.getEdges()){
				edge.draw(sr);
			}}
		
		stage.draw();
		
		for(LButton button: editButtons) {
			button.draw(sr, batch);
		}
		
		for(Vertex vertex: this.getVertexes()) {
			vertex.draw(sr, font, batch);
		}
	}
	
	public void update() {
		for(Vertex vertex: this.getVertexes()) {
			for(Edge edge: vertex.getEdges()) {
				edge.draw(sr);
			}
		}
		
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
		
		for(LButton button: editButtons) {
			button.draw(sr, batch);
		}
		
		for(Vertex vertex: this.getVertexes()) {
			if(addVertexToggle) {
				//
			}
			
			vertex.checkDragged();
			
		}
		
	}
	
	public BitmapFont getFont() {
		return font;
	}
	
	
	
}
