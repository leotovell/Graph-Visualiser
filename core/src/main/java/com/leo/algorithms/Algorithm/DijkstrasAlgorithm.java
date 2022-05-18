package com.leo.algorithms.Algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import com.leo.algorithms.Graph;
import com.leo.algorithms.Vertex;
import com.leo.algorithms.Assets.Resources;

@SuppressWarnings("unused")
public class DijkstrasAlgorithm extends Algorithm{
	
	/*
	 * HashMap is used to store final algorithm data:
	 * 
	 * Vertex | Shortest distance from starting Vertex - this | previous Vertex (used to construct shortest path)
	 * 
	 * 
	 * 
	 */

	public static ArrayList<Vertex> apply(Graph graph, Vertex startVertex, Vertex endVertex) {
		ArrayList<Vertex> unvisitedVertices = new ArrayList<Vertex>(graph.getVertexes()); // Create copy, not reference
		ArrayList<Vertex> visitedVertices = new ArrayList<>();
		HashMap<Vertex, Object[]> table = initMap(graph, startVertex);
		while(!(unvisitedVertices.isEmpty())) {
			int lowestDistance = 9999999;
			Vertex currentVertex = null;
			for(Vertex vertex: unvisitedVertices) {
				if ((int) (table.get(vertex)[0]) < lowestDistance) { // Compare the distance from start vertex in the table to current lowest.
					lowestDistance = (int) (table.get(vertex)[0]); 
					currentVertex = vertex;
				}}
		ArrayList<Vertex> neighbours = currentVertex.getNeighbours(); // Get neighbours of currentVertex
		for(Vertex neighbour: neighbours) {
			int previousDistance = 0;
			Vertex vertexToCheck = currentVertex;
			while(table.get(vertexToCheck)[1] != null) { // While the current neighbour has previousVertex's recorded
				previousDistance += (int) (table.get(vertexToCheck)[0]); // Add up the distance
				vertexToCheck = (Vertex) table.get(vertexToCheck)[1]; // set the next one to be checked and accumulated
			}
			
			// Update Table
			int distanceFromStart = (graph.getEdge(currentVertex, neighbour).getWeight()) + previousDistance;
			if(distanceFromStart < (int) (table.get(neighbour)[0])) {
				Object[] newList = new Object[] {distanceFromStart, currentVertex};
				table.replace(neighbour, newList);
			}}
		visitedVertices.add(currentVertex);
		unvisitedVertices.remove(currentVertex);
		}
		return constructPath(table, endVertex, startVertex);
	}
	
	public static HashMap<Vertex, Object[]> initMap(Graph graph, Vertex startVertex){
		HashMap<Vertex, Object[]> table = new HashMap<>();
		for(Vertex v: graph.getVertexes()) table.put(v, new Object[] {9999999, null}); // For each vertex, set distance to infinity and previous as null
		table.replace(startVertex, new Object[] {0, null}); // Place starting vertex distance to starting vertex to 0.
		return table;
	}
	
	public static ArrayList<Vertex> constructPath(HashMap<Vertex, Object[]> table, Vertex endVertex, Vertex startVertex) {
		ArrayList<Vertex> path = new ArrayList<>();
		Vertex currentVertex = endVertex;
		while(table.get(currentVertex)[1] != null) {
			path.add(0, currentVertex);
			currentVertex = (Vertex) table.get(currentVertex)[1];
		}
		path.add(0, startVertex);
		return path;
	}
	
}
