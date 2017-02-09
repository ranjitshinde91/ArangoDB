package demo.arango.com.aql;

import java.util.Map;

import com.arangodb.ArangoCursor;
import com.arangodb.ArangoDB;
import com.arangodb.ArangoDBException;
import com.arangodb.util.MapBuilder;
import com.arangodb.velocypack.VPackSlice;

public class Filter {

	public static void main(String[] args) {
		ArangoDB arangoDB = new ArangoDB.Builder().host("localhost").port(8529).user("Dev").password("123").build();
		String dbName = "TestDB";
		String collectionName = "TestCollection";
		
		try {
			  String query = "FOR t IN "+collectionName+" FILTER t.name == @name RETURN t";
			  Map<String, Object> bindVars = new MapBuilder().put("name", "Ranjit").get();
			  
			  ArangoCursor<VPackSlice> cursor = arangoDB.db(dbName).query(query, bindVars, null,  VPackSlice.class);
			  cursor.forEachRemaining(myDocument -> {
			    System.out.println("Key: " + myDocument.get("_key").getAsString());
			    System.out.println("Attribute Name: " + myDocument.get("name").getAsString());
				System.out.println("Attribute ID: " + myDocument.get("id").getAsInt());
				System.out.println("Attribute Company: " + myDocument.get("company").getAsString());
			  });
			} catch (ArangoDBException e) {
			  System.err.println("Failed to execute query. " + e.getMessage());
			}
	}
}
