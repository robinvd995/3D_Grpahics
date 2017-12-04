package converter.io;

import converter.model.IndexedModel;
import converter.model.IndexedVertex;
import converter.model.RawFace;
import converter.model.RawModel;
import converter.model.Vertex;

public class ObjConverter {

	public static IndexedModel convertObj(RawModel rawModel){
		
		IndexedModel model = new IndexedModel();
		
		int vertexIndex = 0;
		for(RawFace face : rawModel.getFaces()){
			for(Vertex vertex : face.getVertices()){
				if(!model.getVertices().contains(vertex)){
					IndexedVertex iv = new IndexedVertex(vertexIndex, vertex);
					model.addVertex(iv);
					model.addIndex(vertexIndex);
					vertexIndex++;
					System.out.println("Added a original vertex!");
				}
				else{
					boolean found = false;
					for(IndexedVertex iv : model.getVertices()){
						if(iv.equals(vertex)){
							model.addIndex(iv.id);
							System.out.println("Duplicate found!");
							found = true;
							break;
						}
					}
					if(!found){
						System.out.println("Error whilst loading in a duplicate vertex!");
					}
				}
			}
		}
		return model;
	}
}