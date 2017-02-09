package demo.arango.com.crud;

import com.arangodb.ArangoDB;
import com.arangodb.ArangoDBException;
import com.arangodb.velocypack.VPackSlice;

public class ReadDocumentVelocyPack {
	public static void main(String[] args) {
		ArangoDB arangoDB = new ArangoDB.Builder().host("localhost").port(8529).user("Dev").password("123").build();
		String dbName = "TestDB";
		String collectionName = "TestCollection";

		try {
			VPackSlice myDocument = arangoDB.db(dbName).collection(collectionName).getDocument("Ranjit", VPackSlice.class);
			System.out.println("Key: " + myDocument.get("_key").getAsString());
			System.out.println("Attribute Name: " + myDocument.get("name").getAsString());
			System.out.println("Attribute ID: " + myDocument.get("id").getAsInt());
			System.out.println("Attribute Company: " + myDocument.get("company").getAsString());
		} catch (ArangoDBException e) {
			System.out.println("Failed to get document. " + e.getMessage());
		}
	}
}
