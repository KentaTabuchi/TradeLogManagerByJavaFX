/**
 * 
 */
package application;
import java.sql.*;
/**
 * @author misskabu
 * 
 */
public class MySQLConnector {
		private final static String DATABASE_PASS = "jdbc:mysql://127.0.0.1/TradeLog?autoReconnect=true&useSSL=false";
		private final static String USER = "root"; 
		private final static String PASSWORD = "";
	    public static void main(String[] args) {
	        Connection con = null;
	        try {
	            // JDBCドライバのロード - JDBC4.0（JDK1.6）以降は不要
	            Class.forName("com.mysql.jdbc.Driver").newInstance();
	            // MySQLに接続
	            con = DriverManager.getConnection(DATABASE_PASS,USER,PASSWORD);
	            System.out.println("MySQLに接続できました。");
	            
	        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
	            System.out.println("JDBCドライバのロードに失敗しました。");
	        } catch (SQLException e) {
	            System.out.println("MySQLに接続できませんでした。");
	        } finally {
	            if (con != null) {
	                try {
	                    con.close();
	                } catch (SQLException e) {
	                    System.out.println("MySQLのクローズに失敗しました。");
	                }
	            }
	        }
	    }
	
}
