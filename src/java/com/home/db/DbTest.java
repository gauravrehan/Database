package com.home.db;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DbTest {
	
	// JDBC driver name and database URL
	   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	   static final String DB_URL = "jdbc:mysql://localhost:3306/world";

	   //  Database credentials
	   static final String USER = "root";
	   static final String PASS = "root";
	   

	public static void main(String[] args) {
		
		//override classpath. but this doesn't actually overrides because classpath is already read.
		//System.setProperty("java.class.path", "");
		
		System.out.println("classpath=" + System.getProperty("java.class.path"));
		
		
		
		
			Connection conn = null;
		   Statement stmt = null;
		   try{
		      //STEP 2: Register JDBC driver
		      Class.forName("com.mysql.jdbc.Driver");

		      //STEP 3: Open a connection
		      System.out.println("Connecting to database...");
		      conn = DriverManager.getConnection(DB_URL,USER,PASS);
		      
		      DatabaseMetaData dbMetadata = conn.getMetaData();
		      System.out.println(dbMetadata.toString());

		      //STEP 4: Execute a query
		      System.out.println("Creating statement...");
		      stmt = conn.createStatement();
		      String sql;
		      sql = "SELECT ID, Name, CountryCode, District, Population from city";
		      ResultSet rs = stmt.executeQuery(sql);

		      //STEP 5: Extract data from result set
		      while(rs.next()){
		         //Retrieve by column name
		         int id  = rs.getInt("ID");
		         String name = rs.getString("Name");
		         String countryCode = rs.getString("CountryCode");

		         String district = rs.getString("District");
		         int population = rs.getInt("Population");

		        

		         //Display values
		         System.out.print("ID: " + id);
		         System.out.print(", Name: " + name);
		         System.out.print(", CountryCode: " + countryCode);
		         System.out.print(", District: " + district);
		         System.out.println(", Population: " + population);
		      }
		      //STEP 6: Clean-up environment
		      rs.close();
		      stmt.close();
		      conn.close();
		   }catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
		   }finally{
		      //finally block used to close resources
		      try{
		         if(stmt!=null)
		            stmt.close();
		      }catch(SQLException se2){
		      }// nothing we can do
		      try{
		         if(conn!=null)
		            conn.close();
		      }catch(SQLException se){
		         se.printStackTrace();
		      }//end finally try
		   }//end try
		   System.out.println("Goodbye!");
	}

}
