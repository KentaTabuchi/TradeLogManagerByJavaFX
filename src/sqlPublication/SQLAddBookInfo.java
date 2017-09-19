/**
 * 
 */
package sqlPublication;

import java.sql.Connection;
import java.sql.PreparedStatement;

import application.ISQLExecutable;

/**
 * @author misskabu
 * テキストボックスから集めたデータをBOOK_INFOテーブルに書き込む
 */
public class SQLAddBookInfo implements ISQLExecutable {

	/* (非 Javadoc)
	 * @see application.ISQLExcutable#excuteQuery()
	 */
	private int securitiesCode;
	private String bookName;
	private String marcket;
	public boolean isError = false;
	final String SQL = "INSERT INTO BOOK_INFO ("
			+ "SECURITIES_CODE,"//1
			+ "BOOK_NAME,"//2
			+ "MARCKET"//3
			+ ") VALUES(?,?,?)";
	
	public SQLAddBookInfo(
			int securitiesCode,
			String bookName,
			String marcket){
		
		this.securitiesCode = securitiesCode;
		this.bookName = bookName;
		this.marcket = marcket;
	}
	@Override
	public void executeQuery(Connection con) {
			try(PreparedStatement ps = con.prepareStatement(this.SQL)){
			ps.setInt(1,this.securitiesCode);
			ps.setString(2,this.bookName);
			ps.setString(3,this.marcket);
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
			this.isError = true;
			}
	}
}
