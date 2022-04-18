package com.leo.algorithms;

import java.util.ArrayList;
import java.util.Objects;

public class Graph {

	String name = "Graph";
	
	ArrayList<Vertex> vertexList = new ArrayList<>();
	
	public Graph(String name) {
		this.name = name;
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
		Vertex start = findVertex(source);
		if(Objects.isNull(start)) {
			startFailed = true;
		}
		Vertex end = findVertex(destination);
		if(Objects.isNull(end)) {
			endFailed = true;
		}
		if(startFailed) System.out.println("Source vertex: " + source + ", not found. Edge not added.");
		if(endFailed) System.out.println("Destination vertex: " + destination + ", not found. Edge not added.");
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
	
	
	
}
