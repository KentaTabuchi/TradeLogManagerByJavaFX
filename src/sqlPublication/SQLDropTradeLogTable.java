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
public class SQLDropTradeLogTable implements ISQLExecutable {

	/* (非 Javadoc)
	 * @see application.ISQLExcutable#excuteQuery()
	 */

	final String SQL = "DROP TABLE TRADE_LOG";

	@Override
	public void executeQuery(Connection con) {
		System.out.println(this.SQL);
		try(PreparedStatement ps = con.prepareStatement(this.SQL)){

			@SuppressWarnings("unused")
			int result = ps.executeUpdate();
			System.out.println("DROP Table is successed.");}
		catch(org.h2.jdbc.JdbcSQLException e){
			System.out.println("TRADE_LOG Table is not exiting yet");

		} catch (Exception e) {
			System.out.println("SQL failed.Please check SQL syntax or exiting Table,columnse");
			e.printStackTrace();
		}
	}
}
