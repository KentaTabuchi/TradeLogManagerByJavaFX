/**
 * 
 */
package sqlPublication;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import application.ISQLExecutable;

/**
 * @author misskabu
 * TRADE_LOG TABLE から　読み出したデータを表に表示するためのSQL
 */
public class SQLReadAllBookInfo implements ISQLExecutable {

	/* (非 Javadoc)
	 * @see application.ISQLExcutable#excuteQuery()
	 */
	public List<BookInfoRecord> recordList;
	final String SQL = "SELECT * FROM BOOK_INFO ORDER BY SECURITIES_CODE";
	@Override
	public void executeQuery(Connection con) {
		this.recordList = new ArrayList<BookInfoRecord>();
		System.out.println("BookInfoRecord->executeQuery");
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(this.SQL);
		} catch (SQLException e) {
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
					Integer securitiesCode=rs.getInt("SECURITIES_CODE");
					String bookName=rs.getString("BOOK_NAME");
					String marcket=rs.getString("MARCKET");
					System.out.println(securitiesCode+bookName+marcket);
					BookInfoRecord record = new BookInfoRecord(securitiesCode,bookName,marcket);
					recordList.add(record);
				}
			} catch (SQLException e) {
				System.out.println("SQLfailed.please check SQL syntax,exiting DBtable,Is correct DBcolomn.");
				e.printStackTrace();
			}
		}
}
