package demo.arango.com.aql;

import java.util.Map;
import com.arangodb.ArangoCursor;
import com.arangodb.ArangoDB;
import com.arangodb.ArangoDBException;
import com.arangodb.util.MapBuilder;

public class CustomReturn {

	public static void main(String[] args) {
		ArangoDB arangoDB = new ArangoDB.Builder().host("localhost").port(8529).user("Dev").password("123").build();
		String dbName = "TestDB";
		String collectionName = "TestCollection";
		
		try {
			  String query = "FOR t IN "+collectionName+" FILTER t.name == @name  RETURN { 'firstName' : t.name, 'id' : t.id}";
			  Map<String, Object> bindVars = new MapBuilder().put("name", "Ranjit").get();
			  
			  ArangoCursor<Map> cursor = arangoDB.db(dbName).query(query, bindVars, null,  Map.class);
			  cursor.forEachRemaining(myDocument -> {
			    System.out.println("Attribute Name: " + myDocument.get("firstName"));
				System.out.println("Attribute ID: " + myDocument.get("id"));
			  });
			} catch (ArangoDBException e) {
			  System.err.println("Failed to execute query. " + e.getMessage());
			  e.printStackTrace();
			}
	}
}
