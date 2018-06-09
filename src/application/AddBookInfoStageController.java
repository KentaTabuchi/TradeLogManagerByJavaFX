/**
 * 
 */
package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import sqlPublication.SQLAddBookInfo;

/**
 * @author misskabu
 *
 */
public class AddBookInfoStageController implements Initializable{
	@FXML TextField securitiesCodeText;
	@FXML TextField bookNameText;
	@FXML ComboBox<String> marcketCombo;
	
	//ここにテキストボックスに入力されたデータを吸い上げてデータベースに書き込む処理を書く
	@FXML protected void onAddBookInfoButtonClick(ActionEvent evt){
	
		System.out.println("AddButton was Clicked from AddBookInfoStage");
		System.out.println(this.securitiesCodeText.getText());
		System.out.println(this.bookNameText.getText());
		System.out.println(this.marcketCombo.getValue().toString());
		
		final String temp = "["+this.securitiesCodeText.getText()+"]"+this.bookNameText.getText();
		
		try{
		ISQLExecutable sqlExecutable= 
				new SQLAddBookInfo( Integer.parseInt(this.securitiesCodeText.getText()),
									this.bookNameText.getText(),
									this.marcketCombo.getValue().toString());
    	@SuppressWarnings("unused")
		H2DBConnector mysqlConnector = new H2DBConnector(sqlExecutable);
		Alert alert = new Alert(AlertType.NONE,temp + "を追加しました", ButtonType.OK);
		alert.show();
		
    	}catch(NullPointerException e){
    		Alert alert = new Alert(AlertType.ERROR, "記入漏れがあります", ButtonType.OK);
    		alert.show();
    	}
    	
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
			//this.marcketCombo.getItems().addAll((Marcket.values()));
			for(int i=0;i<Marcket.values().length;i++){
				this.marcketCombo.getItems().add(Marcket.values()[i].getString());
			}
	}

}
