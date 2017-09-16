/**
 * 
 */
package application;
import java.sql.*;

/**
 * @author misskabu
 * 
 */
public class H2DBConnector {
	private final String H2DRIVER = "org.h2.Driver";
	private final String PASS_H2DB_TRADE = "jdbc:h2:./db/tradeLogger";
		private final static String USER = "root"; 
		private final static String PASSWORD = "";	
	/**
	 * @param sqlExecutable
	 * 
	 */
	public H2DBConnector(ISQLExecutable sqlExecutable){
        Connection con = null;
        try {
            // JDBCドライバのロード - JDBC4.0（JDK1.6）以降は不要
            Class.forName(H2DRIVER).newInstance();
            // MySQLに接続
            con = DriverManager.getConnection(PASS_H2DB_TRADE,USER,PASSWORD);
            System.out.println("H2Databaseに接続できました。");
            sqlExecutable.executeQuery(con); //delegate to an other Class what inherited ISQLExecutable
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            System.out.println("JDBCドライバのロードに失敗しました。");
        } catch (SQLException e) {
            System.out.println("H2Databaseに接続できませんでした。");
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    System.out.println("H2Databaseのクローズに失敗しました。");
                }
            }
        }		
	}

}
