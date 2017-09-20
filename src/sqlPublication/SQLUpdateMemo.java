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
public class SQLUpdateMemo implements ISQLExecutable {

	/* (非 Javadoc)
	 * @see application.ISQLExcutable#excuteQuery()
	 */
	private int id;
	private String memo;
	
	
	final String SQL = "UPDATE TRADE_LOG SET "
			+ "MEMO = ? "			//1
			+ "WHERE ID = ?";			//2

	
	public SQLUpdateMemo(
			int id,
			String memo
){
		
		this.id = id;
		this.memo = memo;

	}
	@Override
	public void executeQuery(Connection con) {
		System.out.println(this.SQL);
			try(PreparedStatement ps = con.prepareStatement(this.SQL)){
			ps.setString(1,this.memo);
			ps.setInt(2,this.id);

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
