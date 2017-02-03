package demo.arango.com.example;

import java.util.ArrayList;
import java.util.List;

import com.arangodb.ArangoDB;
import com.arangodb.ArangoEdgeCollection;
import com.arangodb.ArangoVertexCollection;
import com.arangodb.entity.DocumentField;
import com.arangodb.entity.DocumentField.Type;
import com.arangodb.entity.EdgeDefinition;
import com.arangodb.entity.GraphEntity;
import com.arangodb.entity.VertexEntity;

public class Graph {
	public static void main(String[] args) {
		try {
			ArangoDB arangoDB = new ArangoDB.Builder().host("localhost").port(8529).user("Dev").password("123").build();
			String dbName = "TestDB";
			String collectionName = "TestCollection";
			String graphName= "Academical";

			List<EdgeDefinition> edgeDefinitions = new ArrayList<EdgeDefinition>();
			EdgeDefinition edgeDefHasWritten = new EdgeDefinition();
			edgeDefHasWritten.collection("HasWritten");
			edgeDefHasWritten.from("Person");
			edgeDefHasWritten.to("Publication");

			edgeDefinitions.add(edgeDefHasWritten);

			// Create the graph:
			GraphEntity graphAcademical = arangoDB.db(dbName).createGraph(graphName, edgeDefinitions);

			Person person1 = new Person("Bob", "Dr");
			Person person2 = new Person("Floyd", "master of arts");
			ArangoVertexCollection personVertex = arangoDB.db(dbName).graph(graphName).vertexCollection("Person");
			VertexEntity p1 = personVertex.insertVertex(person1);
			VertexEntity p2 = personVertex.insertVertex(person2);
			
			
			ArangoVertexCollection publicationVertex = arangoDB.db(dbName).graph(graphName).vertexCollection("Publication");
			Publication publication1 = new Publication("PUB1","Surgery for dummies", "1-234-1", 42);
			Publication publication2 = new Publication("PUB2", "Relaxing while working", "5-678-x", 815);
			Publication publication3 = new Publication("PUB3", "Infrasound in art and science",	"7-081-5", 60);
			VertexEntity v1 = publicationVertex.insertVertex(publication1);
			VertexEntity v2 =publicationVertex.insertVertex(publication2);
			VertexEntity v3 =publicationVertex.insertVertex(publication3);
			
			ArangoEdgeCollection hasWrittenCollection = arangoDB.db(dbName).graph(graphName).edgeCollection("HasWritten");

			
			hasWrittenCollection.insertEdge(new Relation(p1.getId(), v1.getId()));
			hasWrittenCollection.insertEdge(new Relation(p2.getId(), v2.getId()));
			hasWrittenCollection.insertEdge(new Relation(p1.getId(), v3.getId()));
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}

class Relation{
	@DocumentField(Type.ID)
	private String id;

	@DocumentField(Type.KEY)
	private String key;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	@DocumentField(Type.FROM)
	private String from;

	@DocumentField(Type.TO)
	private String to;
	
	public String getFrom() {
		return from;
	}
	
	public Relation(final String from, final String to){
		this.from = from;
		this.to = to;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	
	
}
class Person {
	String name;
	String title;
	

	@DocumentField(Type.ID)
	private String id;

	@DocumentField(Type.KEY)
	private String key;


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Person(String name, String title) {
		this.id = name;
		this.key = name;
		this.name = name;
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	
}

class Publication {
	String title;
	String isbn;
	int pages;
	

	@DocumentField(Type.ID)
	private String id;

	@DocumentField(Type.KEY)
	private String key;


	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public Publication(String key, String title, String isbn, int pages) {
		this.id = title;
		this.key = key;
		this.title = title;
		this.isbn = isbn;
		this.pages = pages;
	}
}
