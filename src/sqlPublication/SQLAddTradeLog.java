/**
 * 
 */
package sqlPublication;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;

import application.ISQLExecutable;

/**
 * @author misskabu
 * テキストボックスから集めたデータをtradeLogテーブルに書き込む
 */
public class SQLAddTradeLog implements ISQLExecutable {

	/* (非 Javadoc)
	 * @see application.ISQLExcutable#excuteQuery()
	 */
	private Date tradeDate;
	private int securitiedCode;
	private int purchasePrice;
	private int purchaseNumber;
	private int sellingPrice;
	private int sellingNumber;
	private int pl;
	private String memo;
	
	final String SQL = "INSERT INTO TRADE_LOG ("
			
			+ "TRADE_DATE,"//2
			+ "SECURITIES_CODE,"//3
			+ "PURCHASE_PRICE,"//4
			+ "PURCHASE_NUMBER,"//5
			+ "SELLING_PRICE,"//6
			+ "SELLING_NUMBER,"//7
			+ "PL,"
			+ "MEMO) VALUES(?,?,?,?,?,?,?,?)";
	
	public SQLAddTradeLog (
		
			Date tradeDate,
			int securitiedCode,
			int purchasePrice,
			int purchaseNumber,
			int sellingPrice,
			int sellingNumber,
			int pl,
			String memo){
		

		this.tradeDate = tradeDate;
		this.securitiedCode = securitiedCode;
		this.purchasePrice = purchasePrice;
		this.purchaseNumber = purchaseNumber;
		this.sellingPrice = sellingPrice;
		this.sellingNumber = sellingNumber;
		this.pl = pl;
		this.memo = memo;
	}
	@Override
	public void executeQuery(Connection con) {
			try(PreparedStatement ps = con.prepareStatement(this.SQL)){
			//ps.setInt(1,this.id);
			ps.setDate(1,this.tradeDate);
			ps.setInt(2,this.securitiedCode);
			ps.setInt(3, this.purchasePrice);
			ps.setInt(4, this.purchaseNumber);
			ps.setInt(5, this.sellingPrice);
			ps.setInt(6, this.sellingNumber);
			ps.setInt(7, this.pl);
			ps.setString(8, this.memo);
			int result = ps.executeUpdate();
				if(result!=0){
					System.out.println(result + "件のレコード書き込みに成功しました");
				}
				else{
					System.out.println("書き込みに失敗しました");
				}
			} catch (Exception e) {
			System.out.println("SQL failed.Please check SQL syntax or exiting Table,columnse");
			e.printStackTrace();
			}
	}
}
