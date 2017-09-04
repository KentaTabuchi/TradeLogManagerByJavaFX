/**
 * 
 */
package application;

import java.sql.Connection;

/**
 * @author misskabu
 * 
 * A class inherited by this Interface ensures that it has the method excuteQuery. 
 */
public interface ISQLExecutable {
	void executeQuery(Connection con);
}
