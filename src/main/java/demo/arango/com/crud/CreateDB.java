package demo.arango.com.crud;

import com.arangodb.ArangoDB;

public class CreateDB {
	public static void main(String[] args) {
		ArangoDB arangoDB = new ArangoDB.Builder().build();
		String dbName = "TestDB";
		try {
			arangoDB.createDatabase(dbName);
			System.out.println("Database created: " + dbName);
		} catch (Exception e) {
			System.out.println("Failed to create database " + dbName + "; " + e.getMessage());
		}
	}
}
