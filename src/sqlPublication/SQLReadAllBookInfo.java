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
import propertyBeans.BookInfoRecord;

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
			e.printStackTrace();
		}
			try {
				while(rs.next()){
					Integer securitiesCode=rs.getInt("SECURITIES_CODE");
					String bookName=rs.getString("BOOK_NAME");
					String marcket=rs.getString("MARCKET");
					Integer profit = 100;
					System.out.println(securitiesCode+bookName+marcket);
					// TODO 仮に１００を入れているが実際には損益の集計された金額が入るようにする
					BookInfoRecord record = new BookInfoRecord(securitiesCode,bookName,marcket,profit);
					
					recordList.add(record);
				}
			} catch (SQLException e) {
				System.out.println("SQLfailed.please check SQL syntax,exiting DBtable,Is correct DBcolomn.");
				e.printStackTrace();
			}
		}
}
