package main;

import java.io.IOException;

import COLParser.Graph;
import COLParser.Graph.Vertex;

public class Main {
	public static void main (String[] args) throws IOException {

		Graph g = new Graph(args[0]);
		
		
		System.out.println("v: " + g.GetVertexCount() + ", e: " + g.GetEdgeCount());
		for (Vertex v : g.GetSortedVertexList()) {
			System.out.println(v + " " + v.GetDegree());
		}
	}
}
