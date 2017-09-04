/**
 * 
 */
package application;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author misskabu
 *
 */
public class SQLRecordSelector implements ISQLExecutable {

	/* (非 Javadoc)
	 * @see application.ISQLExcutable#excuteQuery()
	 */
	String sql;
	@Override
	public void executeQuery(Connection con) {
		// TODO 自動生成されたメソッド・スタブ
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
