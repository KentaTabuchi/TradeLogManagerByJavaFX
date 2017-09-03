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
	    	MySQLConnector mysqlConnector = new MySQLConnector();
	    	mysqlConnector.init();
	    }
	public void init(){
        Connection con = null;
        try {
            // JDBCドライバのロード - JDBC4.0（JDK1.6）以降は不要
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            // MySQLに接続
            con = DriverManager.getConnection(DATABASE_PASS,USER,PASSWORD);
            System.out.println("MySQLに接続できました。");
            //ここから
            this.executeQuery(con);
  
            //ここまでに接続できた場合の処理をかく
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
	private void executeQuery(Connection con){
		System.out.println("executeQuery");
		final String SQL = "SELECT * FROM TRADE_LOG";
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(SQL);
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		ResultSet rs = null;
		try {
			rs = ps.executeQuery();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
			try {
				while(rs.next()){
					Integer id=rs.getInt("ID");
					Date date=rs.getDate("TRADE_DATE");
					Integer code=rs.getInt("SECURITIES_CODE");
					//String name=rs.getString("BOOK_NAME");
					Integer purchasePrice=rs.getInt("PURCHASE_PRICE");
					Integer purchaseNum=rs.getInt("PURCHASE_NUMBER");
					Integer sellingPrice=rs.getInt("SELLING_PRICE");
					Integer sellingNum=rs.getInt("SELLING_NUMBER");
					System.out.println(id);
					System.out.println(date);
					System.out.println(code);
					System.out.println(purchasePrice);
					System.out.println(purchaseNum);
					System.out.println(sellingPrice);
					System.out.println(sellingNum);
				}
			} catch (SQLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		}

	}

