import java.util.LinkedList;
import java.util.List;

/**
 * Representation of a graph vertex
 */
public class Vertex implements Comparable<Vertex> {
	private final String label;   // label attached to this vertex
	private Vertex path;
	private int pathValue;
	private boolean known;

	/**
	 * Construct a new vertex
	 * @param label the label attached to this vertex
	 */
	public Vertex(String label) {
		if(label == null)
			throw new IllegalArgumentException("null");
		this.label = label;
		this.path = null;
		this.pathValue = Integer.MAX_VALUE;
		this.known = false;
	}

	/**
	 * Get a vertex label
	 * @return the label attached to this vertex
	 */
	public String getLabel() {
		return label;
	}
	
	/**
	 * A string representation of this object
	 * @return the label attached to this vertex
	 */
	public String toString() {
		return label;
	}

	//auto-generated: hashes on label
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((label == null) ? 0 : label.hashCode());
		return result;
	}

	//auto-generated: compares labels
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Vertex other = (Vertex) obj;
		if (label == null) {
                    return other.label == null;
		} else {
		    return label.equals(other.label);
		}
	}
	
	public void setPath(Vertex other) {
		this.path = other;
	}
	
	public Vertex getPath() {
		return this.path;
	}

	public int getPathValue() {
		return this.pathValue;
	}
	
	public void setPathValue(int value) {
		this.pathValue = value;
	}
	
	public boolean getKnown() {
		return this.known;
	}
	
	public void setKnown(boolean other) {
		this.known = other;
	}
	
	public int compareTo(Vertex other) {
		return this.pathValue - other.getPathValue();
	}

}
