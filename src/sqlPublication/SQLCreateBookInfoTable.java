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
public class SQLCreateBookInfoTable implements ISQLExecutable {

	/* (非 Javadoc)
	 * @see application.ISQLExcutable#excuteQuery()
	 */

	final String SQL = "CREATE TABLE BOOK_INFO("
			+ "SECURITIES_CODE INT PRIMARY KEY,"
			+ "BOOK_NAME VARCHAR(40),"
			+ "MARCKET VARCHAR(20))";

	@Override
	public void executeQuery(Connection con) {
		System.out.println(this.SQL);
		try(PreparedStatement ps = con.prepareStatement(this.SQL)){

			ps.executeUpdate();
		
				System.out.println("Create Table has sucessed.");
}
		catch(org.h2.jdbc.JdbcSQLException e){
			System.out.println("BOOK_INFO Table is exiting yet");

		} catch (Exception e) {
			System.out.println("SQL failed.Please check SQL syntax or exiting Table,columnse");
			e.printStackTrace();
		}
	}
}
