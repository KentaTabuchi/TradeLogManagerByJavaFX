/**
 * 
 */
package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import sqlPublication.SQLAddBookInfo;

/**
 * @author misskabu
 *
 */
public class AddBookInfoStageController {
	@FXML TextField securitiesCodeText;
	@FXML TextField bookNameText;
	@FXML TextField marcketText;
	
	//ここにテキストボックスに入力されたデータを吸い上げてデータベースに書き込む処理を書く
	@FXML protected void onAddBookInfoButtonClick(ActionEvent evt){
	
		System.out.println("AddButton was Clicked from AddBookInfoStage");
		System.out.println(this.securitiesCodeText.getText());
		System.out.println(this.bookNameText.getText());
		System.out.println(this.marcketText.getText());

		ISQLExecutable sqlExecutable= 
				new SQLAddBookInfo( Integer.parseInt(this.securitiesCodeText.getText()),
									this.bookNameText.getText(),
									this.marcketText.getText());
    	@SuppressWarnings("unused")
		MySQLConnector mysqlConnector = new MySQLConnector(sqlExecutable);
    
    	
	}
}
