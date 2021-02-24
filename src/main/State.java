package main;

import COLParser.Graph;
import COLParser.Graph.Vertex;
import java.util.LinkedList;

/**
 * This class represents a graph state. it stores the previous state, its children states and the vertex that was changed.
 * @author Romain Fournier
 */
public class State {
    private State parentState = null;
    private Graph graph;
    private int[] values;
    private int changedVertex = -1;
    private LinkedList<State> children = new LinkedList<State>();

    /**
     * Root state
     */
    public State (Graph g) {
        graph = g;
        values = new int[g.GetVertexCount()];
    }

    /**
     * Creates a new state
     * @param g the graph attached to this state
     * @param cv the vertex ID that changed from the previous state 
     * @param s the new values (only s[cv] should be altered)
     * @param ps the parent state
     */
    public State (Graph g, int cv, int[] s, State ps) {
        graph = g;
        assert (s.length != g.GetVertexCount()) : "invalid state, vertex count does not match";
        values = s;
        changedVertex = cv;
        parentState = ps;
    }

    /**
     * generates a neighboring state, only 1 value from 'values' should be altered
     * @return a freshly made state
     */
    public State GenerateNeighboringState () {
        int[] newValues = values.clone();
        int newChangedValue = -1;
        // TODO: generate a state (change a value, set newValues and newChangedValues)



        State s = new State(graph, newChangedValue, newValues, this);
        children.add(s);
        return s;
    }

    /**
     * @return the change in violations this state made
     */
    public int DeltaViolations () {
        if (parentState == null) return 0;
        int parentViolations = parentState.Violations(changedVertex);
        int currentViolations = Violations(changedVertex);
        return currentViolations - parentViolations;
    }

    /**
     * 
     * @param vertexID the vertex to check for violations
     * @return the number of constraints this vertex violates (aka the number of neighbor with the same value)
     */
    public int Violations (int vertexID) {
        int ans = 0;
        Vertex current = graph.Get(vertexID);
        for(Vertex neighbor : current.Getneighborhood()) {
            if (values[current.ID()] == values[neighbor.ID()]) ans++;
        }

        return ans;
    }

}
