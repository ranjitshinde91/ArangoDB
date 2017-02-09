package demo.arango.com.crud;

import com.arangodb.ArangoDB;
import com.arangodb.entity.CollectionEntity;

public class CreateCollection {
	public static void main(String[] args) {

		ArangoDB arangoDB = new ArangoDB.Builder().host("localhost").port(8529).user("Dev").password("123").build();
		String dbName = "TestDB";
		String collectionName = "TestCollection";
		try {
			CollectionEntity myArangoCollection = arangoDB.db(dbName).createCollection(collectionName);
			System.out.println("Collection created: " + myArangoCollection.getName());
		} catch (Exception e) {
			System.out.println("Failed to create colleciton " + collectionName + "; " + e.getMessage());
		}
	}
}
