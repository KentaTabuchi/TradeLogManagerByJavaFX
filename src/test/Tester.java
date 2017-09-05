package test;

import application.ISQLExecutable;
import application.MySQLConnector;
import sqlPublication.SQLReadAllTradeLog;


public class Tester {
	   
		/**
		 * @param args
		 * this main method made for DB Connection Test.
		 */
		@SuppressWarnings("unused")
		public static void main(String[] args) {
			ISQLExecutable sqlExecutable= new SQLReadAllTradeLog();
	    	MySQLConnector mysqlConnector = new MySQLConnector(sqlExecutable);
	    }
}
