/**
 * 
 */
package sqlPublication;

import java.sql.Connection;
import java.sql.PreparedStatement;

import application.ISQLExecutable;

/**
 * @author misskabu
 * 指定されたIDをtradeLogテーブルから削除する
 */
public class SQLDeleteBookInfo implements ISQLExecutable {

	/* (非 Javadoc)
	 * @see application.ISQLExcutable#excuteQuery()
	 */
	private int securitiesCode;
	final String SQL = "DELETE FROM BOOK_INFO WHERE SECURITIES_CODE = ?"; //1
	
	public SQLDeleteBookInfo(int securitiesCode){
		this.securitiesCode = securitiesCode;
	}
	@Override
	public void executeQuery(Connection con) {
			try(PreparedStatement ps = con.prepareStatement(this.SQL)){
			ps.setInt(1,this.securitiesCode);

			int result = ps.executeUpdate();
				if(result!=0){
					System.out.println(result + " Delete successed");
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
