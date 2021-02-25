package main;

import java.io.IOException;

import COLParser.Graph;
import COLParser.Graph.Vertex;

public class Main {
    public static void main(String[] args) throws IOException {
        int maxMoves = 3000;
        int maxTries = 10;
        int maxCol = 15;
        float temperature = 1;
        float alpha = 0.999f;
        boolean verbose = false;

        Graph g = new Graph("le450_15b.col");

        long start = System.currentTimeMillis();
        State best = MetaHeuristic(g, maxCol, maxTries, maxMoves, temperature, alpha);
        long end = System.currentTimeMillis();

        int violation = best.HardCheckViolations();

        System.out.println("");
        if (violation == 0)
            System.out.println("Solution find found after " + (end - start) + "ms");
        else
            System.out.println("Approximation found after " + (end - start) + "ms with " + violation + " violations.");
        System.out.println(maxMoves + " maxMoves");
        System.out.println(maxTries + " maxTries");
        System.out.println(maxCol + " colors");
        System.out.println("Temperature : " + temperature);
        System.out.println("Alpha : " + alpha);
        if (verbose) {
            System.out.println("");
            System.out.println("id" + "		" + "violations" + "	" + "degree" + "		" + "value");
            System.out.println("--" + "		" + "----------" + "	" + "------" + "		" + "-----");
            System.out.println("");
            for (Vertex v : best.sortedVertexByViolation)
                System.out.println(v.ID() + "		" + best.Violations(v.ID()) + "		" + v.GetDegree() + "		" + best.Value(v.ID()));
        }
    }

    public static State SA_Move(State cur, int maxCol, float temperature) {
        int bestCost = Integer.MAX_VALUE;
        State x_best = cur;
        boolean accepted = false;
        for (int i = 0; i < maxCol; i++) {
            State x = cur.GenerateNeighboringState(maxCol);
            if (x.Violations() <= cur.Violations() || Math.random() < Math.exp(-x.DeltaViolations() / temperature)) {
                accepted = true;
            }
            if (x.Violations() < bestCost) {
                x_best = x;
                bestCost = x.Violations();
            }
        }
        if (accepted) return x_best;
        else return cur;
    }

    public static State MetaHeuristic(Graph g, int maxCol, int maxTries, int maxMoves, float baseTemp, float alpha) {
        State best = null;

        for (int i = 0; i < maxTries; i++) {
            State x = new State(g, maxCol);
            State bestWalk = null;
            float temperature = baseTemp;
            for (int j = 0; j < maxMoves; j++) {
                x = SA_Move(x, maxCol, temperature);
                temperature *= alpha;
                if (bestWalk == null || x.Violations() < bestWalk.Violations())
                    bestWalk = x;
            }
            if (best == null || bestWalk.Violations() < best.Violations())
                best = bestWalk;
        }

        return best;
    }
}
