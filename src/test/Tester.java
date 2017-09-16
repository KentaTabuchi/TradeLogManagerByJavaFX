package test;

import application.H2DBConnector;
import application.ISQLExecutable;
import sqlPublication.SQLCreateTradeLogTable;


public class Tester {
	   
		/**
		 * @param args
		 * this main method made for DB Connection Test.
		 */
		@SuppressWarnings("unused")
		public static void main(String[] args) {
			//ISQLExecutable sqlExecutable= new SQLDropTradeLogTable();
			ISQLExecutable sqlExecutable= new SQLCreateTradeLogTable();
	    	H2DBConnector mysqlConnector = new H2DBConnector(sqlExecutable);
	    }
}
