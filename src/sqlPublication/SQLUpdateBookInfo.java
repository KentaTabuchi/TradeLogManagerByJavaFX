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
public class SQLUpdateBookInfo implements ISQLExecutable {

	/* (非 Javadoc)
	 * @see application.ISQLExcutable#excuteQuery()
	 */
	private int securitiedCode;
	private String bookName;
	private String marcket;
	
	final String SQL = "UPDATE BOOK_INFO SET "
			+ "BOOK_NAME = ?,"  			//1
			+ "MARCKET = ?"				//2
			+ "WHERE SECURITIES_CODE = ?";	//3
	
	public SQLUpdateBookInfo(
			int securitiedCode,
			String bookName,
			String marcket
			){
		this.securitiedCode = securitiedCode;
		this.bookName = bookName;
		this.marcket = marcket;
	}
	@Override
	public void executeQuery(Connection con) {
		System.out.println(this.SQL);
			try(PreparedStatement ps = con.prepareStatement(this.SQL)){
			ps.setString(1,this.bookName);
			ps.setString(2,this.marcket);
			ps.setInt(3, this.securitiedCode);

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
