package test;

import java.time.LocalDateTime;

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
			LocalDateTime date = LocalDateTime.now();
			System.out.println(date.getYear());
			System.out.println(date.getMonthValue());
	    }
}
