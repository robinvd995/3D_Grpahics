package converter.model;

import converter.util.Vector2f;
import converter.util.Vector3f;

public class IndexedVertex extends Vertex{

	public int id;
	
	public IndexedVertex(int id, Vertex vertex){
		this(id, vertex.position, vertex.uv, vertex.normal);
	}
	
	public IndexedVertex(int id, Vector3f position, Vector2f uv, Vector3f normal) {
		super(position, uv, normal);
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
}
