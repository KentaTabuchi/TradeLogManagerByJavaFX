package test;

import java.sql.Connection;
import java.sql.PreparedStatement;

import application.H2DBConnector;
import application.ISQLExecutable;

public class ExecuteSQL {
	public static void main(String args[]){
	
		@SuppressWarnings("unused")
		H2DBConnector connector = new H2DBConnector(new ISQLExecutable(){
		final String SQL = "SELECT * TRADE_LOG";
			@Override
			public void executeQuery(Connection con) {
				
				try(PreparedStatement ps = con.prepareStatement(this.SQL)){
					int result = ps.executeUpdate();
						if(result!=0){
							System.out.println(result + "件のレコード書き込みに成功しました");
						}
						else{
							System.out.println("書き込みに失敗しました");
						}
					} catch (Exception e) {
					System.out.println("SQL failed.Please check SQL syntax or exiting Table,columnse");
					e.printStackTrace();}
			}});
		
	}			

}
