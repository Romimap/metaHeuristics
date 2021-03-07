package main;

import java.io.IOException;

import COLParser.Graph;
import COLParser.Graph.Vertex;

public class Main {
    public static void main(String[] args) throws IOException {
        int maxMoves = 3000;
        int maxTries = 20;
        int maxCol = 15;
        float temperature = 1f;
        float alpha = 0.5f;
        boolean verbose = false;
        String filename = "le450_15b.col";

        Graph g = new Graph(filename);

        long start = System.currentTimeMillis();
        State best = MetaHeuristic(g, maxCol, maxTries, maxMoves, temperature, alpha);
        long end = System.currentTimeMillis();

        int violation = best.HardCheckViolations();

        System.out.println("");
        if (violation == 0)
            System.out.println("Solution find found after " + (end - start) + "ms");
        else{
            //System.out.println("Approximation found after " + (end - start) + "ms with " + violation + " violations.");
            System.out.println("Approximation found after " + (end - start) + "ms with " + (violation / 2) + " violations.");
        }
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

    /**
     * Simulated Annealing (SA) method
     * @param cur current configuration
     * @param maxCol Maximum number of colours
     * @param temperature important parameter of SA, A high temperature T allows the algorithm to escape from local minima, A low temperature makes the algorithm a greedy algorithm
     * @return neighbor configuration
     */
    public static State SA_Move(State cur, int maxCol, float temperature) {
        int bestCost = Integer.MAX_VALUE;
        State x_best = cur;
        boolean accepted = false; //Accepted?(cur, x) : cost(x) ≤ cost(cur) or Random()< exp(−∆/T)
        for (int i = 0; i < maxCol; i++) {
            State x = cur.GenerateNeighboringState(maxCol); //Generate-Neighbor(cur): any neighbor (selected randomly)
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

    /**
     *
     * @param g
     * @param maxCol Maximum number of colours
     * @param maxTries Maximun number of tries
     * @param maxMoves Maximum number of moves
     * @param baseTemp Base temperature
     * @param alpha Multiplicator of temperature
     * @return neighbor
     * configuration
     */
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
