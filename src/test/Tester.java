package test;

import application.ISQLExecutable;
import application.MySQLConnector;
import sqlPublication.SQLCreateBookInfoTable;
import sqlPublication.SQLCreateTradeLogTable;
import sqlPublication.SQLDropTradeLogTable;


public class Tester {
	   
		/**
		 * @param args
		 * this main method made for DB Connection Test.
		 */
		@SuppressWarnings("unused")
		public static void main(String[] args) {
			//ISQLExecutable sqlExecutable= new SQLDropTradeLogTable();
			ISQLExecutable sqlExecutable= new SQLCreateTradeLogTable();
			
	    	MySQLConnector mysqlConnector = new MySQLConnector(sqlExecutable);
	    }
}
