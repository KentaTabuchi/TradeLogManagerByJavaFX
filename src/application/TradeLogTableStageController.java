/**
 * 
 */
package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * @author misskabu
 *
 */
public class TradeLogTableStageController {
	@FXML protected void onShowAddLogWindowMenuClick(ActionEvent evt){
		System.out.println("starting onShowAddLogWindowMenuClick was successed.");
	  Stage addLogStage = new Stage();
	  BorderPane root;
	try {
		root = (BorderPane)FXMLLoader.load(getClass().getResource("AddLogStage.fxml"));
		Scene scene = new Scene(root,400,400);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		addLogStage.setScene(scene);
		addLogStage.show();
		addLogStage.setX(addLogStage.getX()+400);
	} catch (IOException e) {
		// TODO 自動生成された catch ブロック
		e.printStackTrace();
	}

	}
}
