package com.blucargo.model.init;
//package com.cargotrans.model.init;
//
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//
//import org.dbunit.DatabaseUnitException;
//import org.dbunit.database.DatabaseConnection;
//import org.dbunit.database.IDatabaseConnection;
//import org.dbunit.dataset.DataSetException;
//import org.dbunit.dataset.IDataSet;
//import org.dbunit.dataset.xml.FlatXmlDataSet;
//import org.dbunit.operation.DatabaseOperation;
//
//
//
//public class ImportDatabaseFromFile {
//
//	public static void main(String [] args) throws ClassNotFoundException, SQLException, FileNotFoundException, IOException, DatabaseUnitException{
//        // database connection
//        Class driverClass = Class.forName("com.mysql.jdbc.Driver");
//        Connection jdbcConnection = (Connection) DriverManager.getConnection(
//                "jdbc:mysql://localhost:3306/cargo", "root", "");
//        IDatabaseConnection connection = new DatabaseConnection(jdbcConnection);
//
//        IDataSet dataSet = new FlatXmlDataSet(new File("cargo.xml"));
//        
//        try {
//			DatabaseOperation.CLEAN_INSERT.execute(connection, dataSet);
//		} catch (DatabaseUnitException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	
//}
