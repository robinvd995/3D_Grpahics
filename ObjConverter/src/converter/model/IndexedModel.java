package converter.model;

import java.util.ArrayList;
import java.util.List;

public class IndexedModel {

	private List<Integer> indices;
	private List<IndexedVertex> vertices;
	
	public IndexedModel(){
		indices = new ArrayList<Integer>();
		vertices = new ArrayList<IndexedVertex>();
	}
	
	public void addIndex(int index){
		indices.add(index);
	}
	
	public void addVertex(IndexedVertex vertex){
		vertices.add(vertex);
	}
	
	public List<Integer> getIndices(){
		return indices;
	}
	
	public List<IndexedVertex> getVertices(){
		return vertices;
	}
}
