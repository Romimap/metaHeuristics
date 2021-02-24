package main;

import java.io.IOException;

import COLParser.Graph;
import COLParser.Graph.Vertex;

public class Main {
	public static void main (String[] args) throws IOException {
		Graph g = new Graph("homer.col");
		long start = System.currentTimeMillis();
		
		State s = new State(g);

		int iterations = 500;
		int neighbors = 3;
		int maxCol = 15;

		
		for (int i = 0; i < iterations; i++) {
			State[] voisins = new State[neighbors];

			for (int j = 0; j < neighbors; j++)
				voisins[j] = s.GenerateNeighboringState(maxCol, j);

			State min = voisins[0];

			for (int j = 1; j < neighbors; j++)
				if (min.Violations() > voisins[j].Violations())
					min = voisins[j];
			
			s = min;
		}

		long end = System.currentTimeMillis();
		
		System.out.println("");
		System.out.println(iterations + " iterations : " + (end - start) + "ms");
		System.out.println(neighbors + " neighbors");
		System.out.println(maxCol + " colors");
		System.out.println("");
		System.out.println("id" + "		" + "violations" + "	" + "degree");
		System.out.println("--" + "		" + "----------" + "	" + "------");
		System.out.println("");
		for (Vertex v : s.sortedVertexByViolation) {
			System.out.println(v.ID() + "		" + s.Violations(v.ID()) + "		" + v.GetDegree());
		}
	}
}
