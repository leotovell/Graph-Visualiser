package com.leo.algorithms.Algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import com.leo.algorithms.Edge;
import com.leo.algorithms.Graph;
import com.leo.algorithms.Vertex;

public class KruskalsAlgorithm {

	public static void apply(Graph graph) {
		Graph MST = new Graph("MST");
		for(Vertex vertex: graph.getVertexes()) {
			MST.addVertex(vertex.getName());
			MST.findVertex(vertex.getName()).setPosition(vertex.getX(), vertex.getY());
			MST.findVertex(vertex.getName()).clearEdges();
		MST.draw();
		while(true) {
			
		}
			
		}
		ArrayList<Edge> edges = getEdges(graph);
		int edgeCount = 0;
		for(int i = 0; i < graph.getVertexes().size() -1; i++) {
			Edge edge = edges.get(edgeCount);
		}}
	
	public static ArrayList<Edge> getEdges(Graph g){
		ArrayList<Edge> edges = new ArrayList<>();
//		edges.sort(edge -> edge.weight()); // CHEATING
		ArrayList<Edge> sortedEdges = new ArrayList<>();
		for(Vertex v: g.getVertexes()) {
			for(Edge edge: v.getEdges()) {
				edges.add(edge);
			}}
		while(!(edges.isEmpty())) {
			Edge lowestEdge = edges.get(0);
			for(Edge edge: edges) {
				if(edge.getWeight() < lowestEdge.getWeight()) {
					lowestEdge = edge;
				}}
			sortedEdges.add(lowestEdge);
			edges.remove(lowestEdge);
		}
		return sortedEdges;
	}
	
}
