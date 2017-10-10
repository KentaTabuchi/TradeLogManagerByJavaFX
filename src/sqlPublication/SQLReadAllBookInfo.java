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
	final String SQL_FIND_BOOK_INFO = "SELECT * FROM BOOK_INFO ORDER BY SECURITIES_CODE";
	final String SQL_SUM_PROFIT = "SELECT SUM(PL) AS \"PROFIT\" FROM TRADE_LOG WHERE SECURITIES_CODE = ?";
	@Override
	public void executeQuery(Connection con) {
		this.recordList = new ArrayList<BookInfoRecord>();
		System.out.println("BookInfoRecord->executeQuery");
		PreparedStatement ps = null;
		PreparedStatement ps2 = null;
		try {
			ps = con.prepareStatement(this.SQL_FIND_BOOK_INFO);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ResultSet rs = null;
		ResultSet rs2 = null;
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
					Integer profit = null;
					ps2 = con.prepareStatement(this.SQL_SUM_PROFIT);
					ps2.setInt(1, securitiesCode);
					rs2 = ps2.executeQuery();
					
					while(rs2.next()){
						profit = rs2.getInt("PROFIT");
					}
					
					System.out.println(securitiesCode+bookName+marcket);
					BookInfoRecord record = new BookInfoRecord(securitiesCode,bookName,marcket,profit);
					
					recordList.add(record);
				}
			} catch (SQLException e) {
				System.out.println("SQLfailed.please check SQL syntax,exiting DBtable,Is correct DBcolomn.");
				e.printStackTrace();
			}
		}
}
