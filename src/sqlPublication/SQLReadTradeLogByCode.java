/**
 * 
 */
package sqlPublication;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import application.ISQLExecutable;
import propertyBeans.TradeLogRecord;

/**
 * @author misskabu
 * TRADE_LOG TABLE から　読み出したデータを表に表示するためのSQL
 */
public class SQLReadTradeLogByCode implements ISQLExecutable {

	/* (非 Javadoc)
	 * @see application.ISQLExcutable#excuteQuery()
	 */
	private int code;
	public List<TradeLogRecord> recordList;
	
	public SQLReadTradeLogByCode(int code){
		this.code = code;
	}
	
	final String SQL = "SELECT * FROM TRADE_LOG LEFT JOIN BOOK_INFO ON TRADE_LOG.SECURITIES_CODE ="
			+ " BOOK_INFO.SECURITIES_CODE WHERE BOOK_INFO.SECURITIES_CODE = ? ORDER BY TRADE_DATE";
	@Override
	public void executeQuery(Connection con) {
		this.recordList = new ArrayList<TradeLogRecord>();
		System.out.println("executeQuery");
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(this.SQL);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ResultSet rs = null;
		try {
			ps.setInt(1,this.code);
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
					String name=rs.getString("BOOK_NAME");
					String marcket = rs.getString("MARCKET");
					Integer purchasePrice=rs.getInt("PURCHASE_PRICE");
					Integer purchaseNum=rs.getInt("PURCHASE_NUMBER");
					Integer sellingPrice=rs.getInt("SELLING_PRICE");
					Integer sellingNum=rs.getInt("SELLING_NUMBER");
					String memo = rs.getString("MEMO");
					System.out.println(id+date.toString()+code+name+purchasePrice+purchaseNum+sellingPrice+sellingNum);

					TradeLogRecord record = new TradeLogRecord(
							id,
							date,
							code,
							name,
							marcket,
							purchasePrice,
							purchaseNum,
							sellingPrice,
							sellingNum,
							memo);
					recordList.add(record);
				}
			} catch (SQLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		}
}
