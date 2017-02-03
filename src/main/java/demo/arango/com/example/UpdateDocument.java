package demo.arango.com.example;

import com.arangodb.ArangoDB;
import com.arangodb.ArangoDBException;
import com.arangodb.entity.BaseDocument;

public class UpdateDocument {
	public static void main(String[] args) {
		ArangoDB arangoDB = new ArangoDB.Builder().host("localhost").port(8529).user("Dev").password("123").build();
		String dbName = "TestDB";
		String collectionName = "TestCollection";
		
		BaseDocument myDocument = arangoDB.db(dbName).collection(collectionName).getDocument("Kafka", BaseDocument.class);
		myDocument.addAttribute("age", 25L);
		try {
		  arangoDB.db(dbName).collection(collectionName).updateDocument("Kafka", myDocument);
		  System.out.println("Document updated !");
		} catch (ArangoDBException e) {
		  System.err.println("Failed to update document. " + e.getMessage());
		}
		
	}
}
