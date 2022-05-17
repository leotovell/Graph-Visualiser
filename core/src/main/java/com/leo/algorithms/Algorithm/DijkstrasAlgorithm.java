package com.leo.algorithms.Algorithm;

import java.util.ArrayList;
import java.util.HashMap;

import com.leo.algorithms.Graph;
import com.leo.algorithms.Vertex;

@SuppressWarnings("unused")
public class DijkstrasAlgorithm extends Algorithm{
	

	public static void apply(Graph graph, Vertex startVertex, Vertex endVertex) {
		ArrayList<Vertex> unvisitedVertices = graph.getVertexes();
		HashMap<Vertex, Object[]> table = initMap(graph);
		
	}
	
	public static HashMap<Vertex, Object[]> initMap(Graph graph){
		HashMap<Vertex, Object[]> table = new HashMap<>();
		for(Vertex v: graph.getVertexes()) table.put(v, new Object[] {Double.POSITIVE_INFINITY, null});
		return table;
	}
	
}
