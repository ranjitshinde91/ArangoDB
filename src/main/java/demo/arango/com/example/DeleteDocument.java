package demo.arango.com.example;

import com.arangodb.ArangoDB;
import com.arangodb.ArangoDBException;


public class DeleteDocument {
	public static void main(String[] args) {
		ArangoDB arangoDB = new ArangoDB.Builder().host("localhost").port(8529).user("Dev").password("123").build();
		String dbName = "TestDB";
		String collectionName = "TestCollection";

		try {
			arangoDB.db(dbName).collection(collectionName).deleteDocument("Kafka");
			 System.out.println("Document Deleted.");
		} catch (ArangoDBException e) {
			System.out.println("Failed to delete document. " + e.getMessage());
		}
	}
}
