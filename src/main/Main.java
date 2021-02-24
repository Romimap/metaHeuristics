package main;

import java.io.IOException;

import COLParser.Graph;
import COLParser.Graph.Vertex;

public class Main {
	public static void main (String[] args) throws IOException {
		Graph g = new Graph("homer.col");
		
		State root = new State(g);
		for (Vertex v : root.sortedVertexByViolation) {
			System.out.println(v.ID() + " " + root.Violations(v.ID()) + "/" + v.GetDegree());
		}
	}
}
