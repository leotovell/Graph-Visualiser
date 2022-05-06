package com.leo.algorithms;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
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
	SpriteBatch batch;
	Stage stage;
	private LButton addVertexButton, addEdgeButton, removeVertexButton, removeEdgeButton;
	private ArrayList<LButton> editButtons;
	private BitmapFont font;
	private boolean addVertexToggle, addEdgeToggle, removeVertexToggle, removeEdgeToggle;
	private int WINDOW_WIDTH, WINDOW_HEIGHT, VERTEX_RADIUS;
	private Random r;
	private LButton lastButtonPressed = null;
	private boolean[] buttonToggles = {false, false, false, false};
	private Vertex toRemove = null;
	private Vertex addSource, addDestination, edgeSource, edgeDestination;
	
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
		
//		this.addEdge("a", "b", 10);
//		this.addEdge("b", "a", 29);
		
		for(Vertex vertex: this.getVertexes()) {
			int x = r.nextInt(WINDOW_WIDTH - 40) + 20;
			int y = r.nextInt(WINDOW_HEIGHT - 80) + 60;
			
			
			while(coordinatesCollide(x, y, vertex, this)) {
				x = r.nextInt(WINDOW_WIDTH - 40) + 20;
				y = r.nextInt(WINDOW_HEIGHT - 80) + 60;
			}
			
			System.out.println(WINDOW_HEIGHT - 80 + 60);
			
			
			vertex.setX(x);
			vertex.setY(y);
			vertex.setRadius(VERTEX_RADIUS);
			}
		}
	
	public boolean coordinatesCollide(int x, int y, Vertex currentVertex, Graph graph) {
		boolean collided = false;
		for(Vertex vertex: graph.getVertexes()) {
			if(vertex != currentVertex & vertex.hasCoords()) {
				int dx = vertex.getX() - x;
				int dy = vertex.getY() - y;
				int distance = (int) Math.hypot(dx, dy);
				if(distance < 75) {
					collided = true;
				}}}
		//return false;
		return collided;
	}
	
	public boolean addVertex(String name) {
		Vertex existingVertex = findVertex(name);
		if(Objects.isNull(existingVertex)) {
			Vertex vertex = new Vertex(name);
			this.vertexList.add(vertex);
			return true;
		}
		else {
			System.out.println("Vertex with name: " + name + ", already exists, process aborted.");			
		}
		return false;
	}
	
	public void addVertex(int x, int y) {
		String alphabet = "abcdefghijklmnopqrstuvwxyz";
		StringBuilder sb = new StringBuilder(3);
		for(int j = 0; j < 3; j++) {
			int index = (int)(alphabet.length() * Math.random());
			sb.append(alphabet.charAt(index));}
		String newName = sb.toString();
		boolean vertexCreated = addVertex(newName);
		if(vertexCreated) {
			Vertex newVertex = findVertex(newName);
//				System.out.println("---- addVertex function Start ----");
//				System.out.println(x);
//				System.out.println(y);
//				System.out.println("---- addVertex function  End ----");
			newVertex.setX(x);
			newVertex.setY(y);
			newVertex.setRadius(VERTEX_RADIUS);
		}
			else System.out.println("Vertex already exists, try clicking again.");
		}
	
	public void removeVertex(Vertex vertex) {
		ArrayList<Edge> edgesToDelete = new ArrayList<>();
		for(Vertex vertexToCheck: this.getVertexes()) { // Get all verticies in graph.
			for(Edge edge: vertexToCheck.getEdges()) { // Get each edge
				if(edge.getEndVertex() == vertex) { // If edge is attached to the vertex we are deleting
					edgesToDelete.add(edge);
				}}};
		
		for(Edge edge: edgesToDelete) this.removeEdge(edge.getStartVertex(), edge.getEndVertex());
		
		this.vertexList.remove(vertex); // Vertex edges are deleted along with self.
	}
	
	public void addEdge(Vertex source, Vertex destination, Integer weight) {
		source.addEdge(destination, weight, this.getVertexes());
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
			start.addEdge(end, weight, this.getVertexes());
		}
	}

	public void removeEdge(Vertex source, Vertex destination) {
		source.removeEdge(destination);
		destination.removeEdge(source);
		//Try both - quick get around to doing validation
	}
	
	public ArrayList<Vertex> getVertexes(){
		return this.vertexList;
	}
	
	public void clearGraph() {
		this.getVertexes().clear();
	}
	
	public void draw() {
		
		this.update();
		
		
		for(Vertex vertex: this.getVertexes()) {
			for(Edge edge: vertex.getEdges()){
				edge.draw(sr);
			}}
		
		stage.draw();
			
		for(LButton button: editButtons) {
			button.draw(sr, batch, editButtons);
		}
		
		for(Vertex vertex: this.getVertexes()) {
			vertex.draw(sr, font, batch);
		}
	}
	
	public void update() {
		
		for(int i = 0; i < editButtons.size(); i++) {
			buttonToggles[i] = editButtons.get(i).getToggled();
		}
		
		for(Vertex vertex: this.getVertexes()) {
			for(Edge edge: vertex.getEdges()) {
				edge.draw(sr);
			}}
		stage.act(Gdx.graphics.getDeltaTime());
		
		for(Vertex vertex: this.getVertexes()) {
			vertex.checkDragged();
		}
	
		
		
		boolean toAdd = false;
		
		if(buttonToggles[0]) {
			if(Gdx.input.isButtonJustPressed(Buttons.LEFT)) {
				int x = Gdx.input.getX();
				int y = Gdx.input.getY();
				int h = Gdx.graphics.getHeight();
				System.out.println("h" + (h - y));
				System.out.println("X" + x);
				System.out.println("y" + y);
				this.addVertex(x, h - y);

				// CORRECT X/Y
			}
		}
		
		Vertex toRemove = null;
		for(Vertex vertex: this.getVertexes()) {
			if(buttonToggles[1]) {
				if(vertex.checkClicked()) {
					if(addSource == null) { addSource = vertex; vertex.setColor(Color.PURPLE);}
					else if(addDestination == null) { addDestination = vertex; vertex.setColor(Color.PURPLE);}
					}
				if(addSource != null & addDestination != null) {
					addEdge(addSource, addDestination, r.nextInt(15));
					addSource.setColor(Color.FIREBRICK);
					addDestination.setColor(Color.FIREBRICK);
					addSource = addDestination = null;
				}
			}
			else if(buttonToggles[2]) {
				if(vertex.checkClicked()) {
					toRemove = vertex;
			}}
			else if(buttonToggles[3]) {
				if(vertex.checkClicked()) {
					if(edgeSource == null) {edgeSource = vertex; vertex.setColor(Color.CHARTREUSE);}
					else if(edgeDestination == null) {edgeDestination = vertex; vertex.setColor(Color.CHARTREUSE);}
					}
				if(edgeSource != null & edgeDestination != null) {
					removeEdge(edgeSource, edgeDestination);
					edgeSource.setColor(Color.FIREBRICK);
					edgeDestination.setColor(Color.FIREBRICK);
					edgeSource = edgeDestination = null;
				}
			}
		}

		if(toRemove != null) this.removeVertex(toRemove);;
		
		}
	
	public BitmapFont getFont() {
		return font;
	}
	
	public SpriteBatch getBatch() {
		return batch;
	}
	
	public ShapeRenderer getShapeRenderer() {
		return sr;
	}
	
	public ArrayList<LButton> getEditButtons(){
		return editButtons;
	}
	
	
}
