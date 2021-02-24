package main;

import java.io.IOException;

import COLParser.Graph;
import COLParser.Graph.Vertex;

public class Main {
	public static void main (String[] args) throws IOException {
		Graph g = new Graph("homer.col");
		long start = System.currentTimeMillis();
		
		State s = new State(g);

		int iterations = 1000;

		for (int i = 0; i < iterations; i++) {
			s = s.GenerateNeighboringState(16, 0);
		}
		long end = System.currentTimeMillis();


		System.out.println(iterations + " iters : " + (end - start) + "ms");
		for (Vertex v : s.sortedVertexByViolation) {
			System.out.println(v.ID() + " " + s.Violations(v.ID()) + "/" + v.GetDegree());
		}
	}
}
