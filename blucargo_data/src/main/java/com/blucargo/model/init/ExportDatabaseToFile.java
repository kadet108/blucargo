package com.blucargo.model.init;
//package com.cargotrans.model.init;
//
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//
//import org.dbunit.DatabaseUnitException;
//import org.dbunit.database.DatabaseConnection;
//import org.dbunit.database.IDatabaseConnection;
//import org.dbunit.dataset.IDataSet;
//import org.dbunit.dataset.xml.FlatDtdDataSet;
//import org.dbunit.dataset.xml.FlatXmlDataSet;
//import org.dbunit.dataset.xml.XmlDataSet;
//
//public class ExportDatabaseToFile {
//
//	public static void main(String [] args) throws ClassNotFoundException, SQLException, FileNotFoundException, IOException, DatabaseUnitException{
//        // database connection
//        Class driverClass = Class.forName("com.mysql.jdbc.Driver");
//        Connection jdbcConnection = (Connection) DriverManager.getConnection(
//                "jdbc:mysql://localhost:3306/cargo", "root", "");
//        IDatabaseConnection connection = new DatabaseConnection(jdbcConnection);
//        
//        IDataSet fullDataSet = connection.createDataSet();
////        FlatXmlDataSet.write(fullDataSet, new FileOutputStream("cargo.xml"));
//        XmlDataSet.write(fullDataSet, new FileOutputStream("cargo.xml"));
//        FlatXmlDataSet.write(fullDataSet, new FileOutputStream("flat_cargo.xml"));
//        
//        // write DTD file
//        FlatDtdDataSet.write(connection.createDataSet(), new FileOutputStream("cargo.dtd"));
//	}
//	
//}
