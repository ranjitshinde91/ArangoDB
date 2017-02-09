package demo.arango.com.crud;

import com.arangodb.ArangoDB;
import com.arangodb.entity.BaseDocument;

public class CreateDocument {
	public static void main(String[] args) {
		ArangoDB arangoDB = new ArangoDB.Builder().host("localhost").port(8529).user("Dev").password("123").build();
		String dbName = "TestDB";
		String collectionName = "TestCollection";
		
	    BaseDocument myObject = new BaseDocument();
		myObject.setKey("Ranjit");
		myObject.addAttribute("name", "Ranjit");
		myObject.addAttribute("id", 639);
		myObject.addAttribute("company", "GSLab");
		
		try {
		  arangoDB.db(dbName).collection(collectionName).insertDocument(myObject);
		  System.out.println("Document created"); 
		} catch (Exception e) {
		  System.out.println("Failed to create document. " + e.getMessage()); 
		}
	}
}
