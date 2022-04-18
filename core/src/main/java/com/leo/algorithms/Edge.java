package com.leo.algorithms;

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
	
}