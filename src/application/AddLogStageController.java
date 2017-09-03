package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class AddLogStageController {
	@FXML TextField idText;
	@FXML DatePicker datePicker;
	@FXML TextField codeText;
	@FXML TextField purchasePriceText;
	@FXML TextField purchaseNumText;
	@FXML TextField sellingPriceText;
	@FXML TextField sellingNumText;
	
	@FXML
	protected void onAddButtonClick(ActionEvent evt){
		//ここにテキストボックスに入力されたデータを吸い上げてデータベースに書き込む処理を書く
		System.out.println(this.idText.getText());
		System.out.println(this.datePicker.getValue());
		System.out.println(codeText.getText());
		System.out.println(purchasePriceText.getText());
	}
}
