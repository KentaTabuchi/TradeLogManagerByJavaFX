/**
 * 
 */
package sqlPublication;

import java.sql.Connection;
import java.sql.PreparedStatement;

import application.ISQLExecutable;

/**
 * @author misskabu
 * テキストボックスから集めたデータをtradeLogテーブルに書き込む
 */
public class SQLCreateTradeLogTable implements ISQLExecutable {

	/* (非 Javadoc)
	 * @see application.ISQLExcutable#excuteQuery()
	 */

	final String SQL = "CREATE TABLE TRADE_LOG("
			+ "ID INTEGER AUTO_INCREMENT PRIMARY KEY,"
			+ "SECURITIES_CODE INT,"
			+ "TRADE_DATE DATE,"
			+ "PURCHASE_PRICE INT,"
			+ "PURCHASE_NUMBER INT,"
			+ "SELLING_PRICE INT,"
			+ "SELLING_NUMBER INT)";
	@Override
	public void executeQuery(Connection con) {
		System.out.println(this.SQL);
		try(PreparedStatement ps = con.prepareStatement(this.SQL)){

			@SuppressWarnings("unused")
			int result = ps.executeUpdate();
			System.out.println("CREATE Table is successed.");}
		catch(org.h2.jdbc.JdbcSQLException e){
			System.out.println("Table is exiting yet");

		} catch (Exception e) {
			System.out.println("SQL failed.Please check SQL syntax or exiting Table,columnse");
			e.printStackTrace();
		}
	}
}
