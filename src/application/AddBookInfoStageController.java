/**
 * 
 */
package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import sqlPublication.SQLAddBookInfo;

/**
 * @author misskabu
 *
 */
public class AddBookInfoStageController implements Initializable{
	@FXML TextField securitiesCodeText;
	@FXML TextField bookNameText;
	@FXML ComboBox<Marcket> marcketCombo;
	
	//ここにテキストボックスに入力されたデータを吸い上げてデータベースに書き込む処理を書く
	@FXML protected void onAddBookInfoButtonClick(ActionEvent evt){
	
		System.out.println("AddButton was Clicked from AddBookInfoStage");
		System.out.println(this.securitiesCodeText.getText());
		System.out.println(this.bookNameText.getText());
		System.out.println(this.marcketCombo.getValue().toString());

		ISQLExecutable sqlExecutable= 
				new SQLAddBookInfo( Integer.parseInt(this.securitiesCodeText.getText()),
									this.bookNameText.getText(),
									this.marcketCombo.getValue().toString());
    	@SuppressWarnings("unused")
		MySQLConnector mysqlConnector = new MySQLConnector(sqlExecutable);
    
    	
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
			this.marcketCombo.getItems().addAll((Marcket.values()));
	}

}
