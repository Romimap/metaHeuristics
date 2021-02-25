package main;

import java.io.IOException;

import COLParser.Graph;
import COLParser.Graph.Vertex;

public class Main {
    public static void main(String[] args) throws IOException {

        int iterations = 1000;
        int neighbors = 5;
        int maxCol = 15;
        int nombre_essais = 100000;

        Graph g = new Graph("le450_15b.col");

        long startTry = System.currentTimeMillis();
        for (int k = 0; k < nombre_essais; k++) {
            System.out.print("Try n° " + k + " : ");
            long start = System.currentTimeMillis();

            State s = new State(g, maxCol);

            for (int i = 0; i < iterations; i++) {
                State[] voisins = new State[10];

                for (int j = 0; j < neighbors; j++)
                    voisins[j] = s.GenerateNeighboringState(maxCol, j);

                State min = s;

                for (int j = 0; j < neighbors; j++)
                    if (min.Violations() > voisins[j].Violations())
                        min = voisins[j];

                s = min;
            }

            long end = System.currentTimeMillis();

            int violation = s.HardCheckViolations();

            System.out.println(violation + "/" + g.GetEdgeCount());
            
            if (violation == 0) {
                System.out.println("");
                System.out.println(iterations + " iterations : " + (end - start) + "ms");
                System.out.println(neighbors + " neighbors");
                System.out.println(maxCol + " colors");
                System.out.println("");
                System.out.println("id" + "		" + "violations" + "	" + "degree" + "		" + "value");
                System.out.println("--" + "		" + "----------" + "	" + "------" + "		" + "-----");
                System.out.println("");
                for (Vertex v : s.sortedVertexByViolation)
                    System.out.println(v.ID() + "		" + s.Violations(v.ID()) + "		" + v.GetDegree() + "		" + s.Value(v.ID()));
                System.out.println("Solution find with try number "+ k + " after " + (end - startTry) + "ms");
                break;
            }
        }
    }

    public static State SA_Move(State x) {return null;}

    public static State MetaHeuristic (Graph g, int maxCol, int maxTries, int maxMoves) {
        State best = null;

        for (int i = 0; i < maxTries; i++) {
            State x = new State(g, maxCol);
            State bestWalk = null;
            for (int j = 0; j < maxMoves; j++) {
                x = SA_Move(x);
                if (bestWalk == null || x.Violations() < bestWalk.Violations())
                    bestWalk = x;
            }
            if (best == null || bestWalk.Violations() < best.Violations())
                best = bestWalk;
        }

        return best;
    }
}
