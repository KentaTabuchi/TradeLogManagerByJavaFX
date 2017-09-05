package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import sqlPublication.SQLAddTradeLog;

public class AddLogStageController {
	@FXML TextField idText;
	@FXML DatePicker datePicker;
	@FXML TextField codeText;
	@FXML TextField purchasePriceText;
	@FXML TextField purchaseNumText;
	@FXML TextField sellingPriceText;
	@FXML TextField sellingNumText;
	
	//ここにテキストボックスに入力されたデータを吸い上げてデータベースに書き込む処理を書く
	@FXML protected void onAddButtonClick(ActionEvent evt){
	
		System.out.println("AddButton was Clicked from AddLogStage");
		System.out.println(this.idText.getText());
		System.out.println(this.datePicker.getValue());
		System.out.println(codeText.getText());
		System.out.println(purchasePriceText.getText());
		
		ISQLExecutable sqlExecutable= 
				new SQLAddTradeLog(	Integer.parseInt(this.idText.getText()),
									java.sql.Date.valueOf(this.datePicker.getValue()),
									Integer.parseInt(this.codeText.getText()),
									Integer.parseInt(this.purchasePriceText.getText()),
									Integer.parseInt(this.purchaseNumText.getText()),
									Integer.parseInt(this.sellingPriceText.getText()), 
									Integer.parseInt(this.sellingNumText.getText()));
    	@SuppressWarnings("unused")
		MySQLConnector mysqlConnector = new MySQLConnector(sqlExecutable);
		
	}
}
