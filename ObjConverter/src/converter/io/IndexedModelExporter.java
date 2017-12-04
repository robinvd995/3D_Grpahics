package converter.io;

import java.io.File;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import converter.model.IndexedModel;
import converter.model.IndexedVertex;

public class IndexedModelExporter {

	private Document document;
	private File file;
	
	public IndexedModelExporter(File file){
		this.file = file;
	}
	
	public void exportModel(IndexedModel model) throws ParserConfigurationException, TransformerFactoryConfigurationError, TransformerException{
		
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = dbf.newDocumentBuilder();
		document = builder.newDocument();
		
		Element root = document.createElement("model");
		document.appendChild(root);
		
		Element size = document.createElement("size");
		size.appendChild(getElement("vertices", String.valueOf(model.getVertices().size())));
		size.appendChild(getElement("indices", String.valueOf(model.getIndices().size())));
		root.appendChild(size);
		
		Element vertices = document.createElement("vertices");
		for(IndexedVertex vertex : model.getVertices()){
			vertices.appendChild(createVertexNode(vertex));
		}
		root.appendChild(vertices);
		
		List<Integer> indices = model.getIndices();
		String indicesString = "";
		for(int i = 0; i < indices.size(); i++){
			indicesString = indicesString + indices.get(i) + (i + 1 < indices.size() ? " " : "");
		}

		root.appendChild(getElement("indices", indicesString));
		
		Transformer transformer = TransformerFactory.newInstance().newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
		DOMSource source = new DOMSource(document);
		StreamResult result = new StreamResult(file);
		transformer.transform(source, result);
	}
	
	private Node createVertexNode(IndexedVertex vertex){
		String vertexData = vertex.position.getX() + " " + vertex.position.getY() + " " + vertex.position.getZ() + " " + 
							vertex.uv.getX() + " " + vertex.uv.getY() + " " +
							vertex.normal.getX() + " " + vertex.normal.getY() + " " + vertex.normal.getZ();
		Element vertexElement = getElement("vertex", vertexData);
		vertexElement.setAttribute("index", String.valueOf(vertex.id));		
		return vertexElement;
	}
	
	private Element getElement(String name, String value){
		Element node = document.createElement(name);
		node.appendChild(document.createTextNode(value));
		return node;
	}
}
