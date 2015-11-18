import java.util.*;

/**
 * A representation of a graph.
 * Assumes that we do not have negative cost edges in the graph.
 */
public class MyGraph implements Graph {
    // you will need some private fields to represent the graph
    // you are also likely to want some private helper methods

    private Map<Vertex, Set<Edge>> mainMap;
    private Collection<Edge> allEdges;
    public Vertex bitchinEdge = new Vertex("IND");
    public Vertex bitchinVertex = new Vertex("MKC");

    /**
     * Creates a MyGraph object with the given collection of vertices
     * and the given collection of edges.
     * @param v a collection of the vertices in this graph
     * @param e a collection of the edges in this graph
     * @throws IllegalWeightException 
     * @throws IllegalVertexException 
     */
    public MyGraph(Collection<Vertex> v, Collection<Edge> e) /*throws IllegalWeightException, IllegalVertexException*/ {
    
	    mainMap = new HashMap<Vertex, Set<Edge>>();
	    allEdges = new HashSet<Edge>();
		Iterator<Vertex> vIterator = v.iterator();
		while(vIterator.hasNext()) {

			Vertex currentVertex = vIterator.next();
			System.out.println(currentVertex);
			if(!mainMap.containsKey(currentVertex)) {
				Set<Edge> emptySet = new HashSet<Edge>();
				mainMap.put(currentVertex, emptySet);
			}
		}
		Iterator<Edge> eIterator = e.iterator();
		while(eIterator.hasNext()) {
			Edge currentEdge = eIterator.next();
			if(currentEdge.getWeight() < 0) {
				throw new IllegalArgumentException("Wrong weight");
			}
			else if (!mainMap.containsKey(currentEdge.getSource())) {
				throw new IllegalArgumentException("Source Vertex not in graph");
			} else if (!mainMap.containsKey(currentEdge.getDestination())) {
				throw new IllegalArgumentException("Destination Vertex not in graph");
			} else {
				if(checkValidEdge(currentEdge)) {
					System.out.println(currentEdge);
					mainMap.get(currentEdge.getSource()).add(currentEdge);
					allEdges.add(currentEdge);
				}
			}			
	    }
    }

    /** 
     * Return the collection of vertices of this graph
     * @return the vertices as a collection (which is anything iterable)
     */
    public Collection<Vertex> vertices() {

	return mainMap.keySet();

    }

    /** 
     * Return the collection of edges of this graph
     * @return the edges as a collection (which is anything iterable)
     */
    public Collection<Edge> edges() {

	return allEdges;

    }

    /**
     * Return a collection of vertices adjacent to a given vertex v.
     *   i.e., the set of all vertices w where edges v -> w exist in the graph.
     * Return an empty collection if there are no adjacent vertices.
     * @param v one of the vertices in the graph
     * @return an iterable collection of vertices adjacent to v in the graph
     * @throws IllegalArgumentException if v does not exist.
     */
    public Collection<Vertex> adjacentVertices(Vertex v) {
		if(!mainMap.containsKey(v)) {
			throw new IllegalArgumentException("Vertex " + v + " does not exist");
		}
		Collection<Vertex> adjacent = new HashSet<Vertex>();
		Iterator<Edge> aIterator = mainMap.get(v).iterator();
		while(aIterator.hasNext()) {
			Edge currentEdge = aIterator.next();
			adjacent.add(currentEdge.getDestination());
		}
		return adjacent;
    }

    /**
     * Test whether vertex b is adjacent to vertex a (i.e. a -> b) in a directed graph.
     * Assumes that we do not have negative cost edges in the graph.
     * @param a one vertex
     * @param b another vertex
     * @return cost of edge if there is a directed edge from a to b in the graph, 
     * return -1 otherwise.
     * @throws IllegalArgumentException if a or b do not exist.
     */
    public int edgeCost(Vertex a, Vertex b) {

		if(!mainMap.containsKey(a)) {
			throw new IllegalArgumentException("Vertex " + a + " does not exist");
		}
		if(!mainMap.containsKey(b)) {
			throw new IllegalArgumentException("Vertex " + b + " does not exist");
		}
		Iterator<Edge> aIterator = mainMap.get(a).iterator();
		while(aIterator.hasNext()) {
			Edge currentEdge = aIterator.next();
			if(currentEdge.getDestination().equals(b)) {
				return currentEdge.getWeight();
			}
		}
		return -1;
    }
    /**
     * Returns the shortest path from a to b in the graph, or null if there is
     * no such path.  Assumes all edge weights are nonnegative.
     * Uses Dijkstra's algorithm.
     * @param a the starting vertex
     * @param b the destination vertex
     * @return a Path where the vertices indicate the path from a to b in order
     *   and contains a (first) and b (last) and the cost is the cost of 
     *   the path. Returns null if b is not reachable from a.
     * @throws IllegalArgumentException if a or b does not exist.
     */
    public Path shortestPath(Vertex a, Vertex b) {

	// YOUR CODE HERE (you might comment this out this method while doing Part 1)

    }
    
    private boolean checkValidEdge(Edge originalEdge) {
    	Set<Edge> currentVertexSet = mainMap.get(originalEdge.getSource());
    	if (currentVertexSet.size() != 0) {
    		Iterator<Edge> edgeIterator = currentVertexSet.iterator();
    		while(edgeIterator.hasNext()) {
    			Edge currentEdge = edgeIterator.next();
    			if (originalEdge.getDestination().equals(currentEdge.getDestination()) && 
    					originalEdge.getWeight() != currentEdge.getWeight()) {
    				throw new IllegalArgumentException("Cannot insert edges with same destination "
    						+ "and different weight");
    			}
    		}
    	}
    	return true;
    }
    
    class IllegalVertexException extends Exception {
    	
    	public IllegalVertexException() {}
    	
    	public IllegalVertexException(String message) {
    		super(message);
    	}
    }
    
    class IllegalWeightException extends Exception {
    	
    	public IllegalWeightException() {
    		super("The edge weight cannot be negative");
    	}
    }

}