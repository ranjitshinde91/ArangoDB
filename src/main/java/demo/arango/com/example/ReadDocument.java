package demo.arango.com.example;

import com.arangodb.ArangoDB;
import com.arangodb.ArangoDBException;
import com.arangodb.entity.BaseDocument;

public class ReadDocument {
	public static void main(String[] args) {
		ArangoDB arangoDB = new ArangoDB.Builder().host("localhost").port(8529).user("Dev").password("123").build();
		String dbName = "TestDB";
		String collectionName = "TestCollection";

		try {
			BaseDocument myDocument = arangoDB.db(dbName).collection(collectionName).getDocument("Kafka", BaseDocument.class);
			System.out.println("Key: " + myDocument.getKey());
			System.out.println("Attribute 'name': " + myDocument.getProperties().get("name"));
			System.out.println("Attribute 'id': " + myDocument.getProperties().get("id"));
			System.out.println("Attribute 'company': " + myDocument.getProperties().get("company"));
		} catch (ArangoDBException e) {
			System.out.println("Failed to get document. " + e.getMessage());
		}
	}
}
