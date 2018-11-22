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
public class SQLUpdateTradeLog implements ISQLExecutable {

	/* (非 Javadoc)
	 * @see application.ISQLExcutable#excuteQuery()
	 */
	private int id;
	private Date tradeDate;
	private int securitiedCode;
	private int purchasePrice;
	private int purchaseNumber;
	private int sellingPrice;
	private int sellingNumber;
	private int pL;
	private String memo;
	
	//発行するSQL文。ケアレスミス多発。慎重に。
	final String SQL = "UPDATE TRADE_LOG SET "
			+ "TRADE_DATE = ?,"			//1
			+ "SECURITIES_CODE = ?,"  	//2
			+ "PURCHASE_PRICE = ?,"		//3
			+ "PURCHASE_NUMBER = ?,"		//4
			+ "SELLING_PRICE = ?,"		//5
			+ "SELLING_NUMBER = ?,"			//6
			+ "PL = ?,"	//7
			+ "MEMO = ? " //8
			+ "WHERE ID = ?"; //9			
/* 
 * ここに項目を追加する場合、カンマとスペースに気をつける。
 * 下から２段目の行の最後にカンマを"つけない"。代わりにスペースを入れる。
 * ここで起きるsyntax errorの大半がそれ。
 * 毎回詰まるので、変更するときは変更前の部分を丸ごとコピーとって控えておく。s
 * **/
	
	public SQLUpdateTradeLog(
			int id,
			Date tradeDate,
			int securitiedCode,
			int purchasePrice,
			int purchaseNumber,
			int sellingPrice,
			int sellingNumber,
			int pl,
			String memo){
		
		this.id = id;
		this.tradeDate = tradeDate;
		this.securitiedCode = securitiedCode;
		this.purchasePrice = purchasePrice;
		this.purchaseNumber = purchaseNumber;
		this.sellingPrice = sellingPrice;
		this.sellingNumber = sellingNumber;
		this.pL = pl;
		this.memo = memo;
	}
	@Override
	public void executeQuery(Connection con) {
		
		System.out.println(this.SQL);
			try(PreparedStatement ps = con.prepareStatement(this.SQL)){
			ps.setDate(1,this.tradeDate);
			ps.setInt(2,this.securitiedCode);
			ps.setInt(3, this.purchasePrice);
			ps.setInt(4, this.purchaseNumber);
			ps.setInt(5, this.sellingPrice);
			ps.setInt(6, this.sellingNumber);
			ps.setInt(7, this.pL);
			ps.setString(8, this.memo);
			ps.setInt(9, this.id);
			
			int result = ps.executeUpdate();
				if(result!=0){
					System.out.println(result + "update sucessed.");
				}
				else{
					System.out.println("Updating is failed.");
				}
			} catch (Exception e) {
			System.out.println("SQL failed.Please check SQL syntax or exiting Table,columnse");
			e.printStackTrace();
			}
	}
}
