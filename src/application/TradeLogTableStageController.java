/**
 * 
 */
package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import sqlPublication.SQLReadAllTradeLog;
import sqlPublication.TradeLogRecord;

/**
 * @author misskabu
 *
 */
public class TradeLogTableStageController implements Initializable{
    @FXML private TableView<TradeLogRecord> tableView;
    @SuppressWarnings("rawtypes")
	@FXML private TableColumn idColumn;
    @SuppressWarnings("rawtypes")
	@FXML private TableColumn dateColumn;
    @SuppressWarnings("rawtypes")
	@FXML private TableColumn codeColumn;
    @SuppressWarnings("rawtypes")
	@FXML private TableColumn bookNameColumn;
    @SuppressWarnings("rawtypes")
	@FXML private TableColumn purchasePriceColumn;
    @SuppressWarnings("rawtypes")
	@FXML private TableColumn purchaseNumColumn;
    @SuppressWarnings("rawtypes")
	@FXML private TableColumn sellingPriceColumn;
    @SuppressWarnings("rawtypes")
	@FXML private TableColumn sellingNumColumn;
    
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
	@FXML protected void onReloadLogsClick(ActionEvent evt){
		System.out.println("starting onReloadLogs Menu Click was successed.");
		SQLReadAllTradeLog sqlReadAllTradeLog= new SQLReadAllTradeLog();
    	@SuppressWarnings("unused")
		MySQLConnector mysqlConnector = new MySQLConnector(sqlReadAllTradeLog);
    	for ( int i = 0; i<tableView.getItems().size(); i++) {
    	    tableView.getItems().clear();
    	}
		sqlReadAllTradeLog.recordList.forEach(e->{
			this.tableView.getItems().add(new TradeLogRecord(
					e.idProperty().get(),e.dateProperty().get(),e.codeProperty().get(),e.nameProperty().get(),e.purchasePriceProperty().get(),e.purchaseNumProperty().get(), e.sellingPriceProperty().get(), e.sellingNumProperty().get()));
			
		});
	
	}
	  @SuppressWarnings("unchecked")
	@Override
	    public void initialize(URL url, ResourceBundle rb) {
		  //引数の"id","date"などの文字列がPropertyBeansクラスのTradeLogRecordのprivate変数名と完全一致させると
		  //TableViewと関連づけられる。文字列を間違えるとデータを表示できない。
	        idColumn.setCellValueFactory(new PropertyValueFactory<TradeLogRecord,Integer>("id"));
	        dateColumn.setCellValueFactory(new PropertyValueFactory<TradeLogRecord,Integer>("date"));
	        codeColumn.setCellValueFactory(new PropertyValueFactory<TradeLogRecord,Integer>("code"));
	        bookNameColumn.setCellValueFactory(new PropertyValueFactory<TradeLogRecord,String>("name"));
	        purchasePriceColumn.setCellValueFactory(new PropertyValueFactory<TradeLogRecord,Integer>("purchasePrice"));
	        purchaseNumColumn.setCellValueFactory(new PropertyValueFactory<TradeLogRecord,Integer>("purchaseNum"));
	        sellingPriceColumn.setCellValueFactory(new PropertyValueFactory<TradeLogRecord,Integer>("sellingPrice"));
	        sellingNumColumn.setCellValueFactory(new PropertyValueFactory<TradeLogRecord,Integer>("sellingNum"));
	       

	    }
}
