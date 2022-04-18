package com.leo.algorithms;

import java.util.Random;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class graphVisualiser extends ApplicationAdapter {
	
	private Graph graph;
	private ShapeRenderer sr;
	private int WINDOW_WIDTH, WINDOW_HEIGHT;
	private int VERTEX_RADIUS = 15;

	public Graph createGraph() {
		graph = new Graph("My Graph");
		return graph;
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
	
	@Override
	public void create() {
		
		Random r = new Random();
		WINDOW_WIDTH = Gdx.graphics.getWidth();
		WINDOW_HEIGHT = Gdx.graphics.getHeight();
		
		graph = createGraph();
		graph.addVertex("1");
		graph.addVertex("2");
		graph.addVertex("e");
		graph.addVertex("7");
		graph.addEdge("7", "e", 5);
		
		for(Vertex vertex: graph.getVertexes()) {
			int x = r.nextInt(0, WINDOW_WIDTH);
			int y = r.nextInt(0, WINDOW_HEIGHT);
			
			while(coordinatesCollide(x, y, vertex, graph)) {
				x = r.nextInt(0, WINDOW_WIDTH);
				y = r.nextInt(0, WINDOW_HEIGHT);
			}
			
			vertex.setX(x);
			vertex.setY(y);
			vertex.setRadius(VERTEX_RADIUS);
			
		}
		
		
		sr = new ShapeRenderer();
	}

	@Override
	public void render() {
		for(Vertex vertex: graph.getVertexes()) {
			vertex.draw(sr);
		}
	}

	@Override
	public void dispose() {
		
	}
}