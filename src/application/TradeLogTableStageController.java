/**
 * 
 */
package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
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
 	@FXML private TableColumn marcketColumn;
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
		this.createBoderPaneStage("AddLogStage.fxml",350,0, 400, 400);
	}
	@FXML protected void onShowAddBookInfoWindowMenuClick(ActionEvent evt){
		System.out.println("starting onShowAddBookInfoWindowMenuClick was successed.");
		this.createBoderPaneStage("AddBookInfoStage.fxml",350,0, 400, 170);
	}
	@FXML protected void onShowBookInfoTableWindowMenuClick(ActionEvent evt){
		System.out.println("starting onShowBookInfoTableWindowMenuClick was successed.");
		this.createBoderPaneStage("BookInfoTableStage.fxml",100,100, 400, 500);
	}
	/**
	 * @param fxmlFileName
	 * @param posX  this is not absolute number,Input relative position from first Stage. 
	 * @param posY
	 * @param width
	 * @param height
	 * 
	 */
	private void createBoderPaneStage(String fxmlFileName,int posX,int posY,int width,int height){
		Stage stage = new Stage();
		BorderPane root;
		try {
			root = (BorderPane)FXMLLoader.load(getClass().getResource(fxmlFileName));
			Scene scene = new Scene(root,width,height);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			stage.setScene(scene);
			stage.show();
			stage.setX(stage.getX()+posX);
			stage.setY(stage.getY()+posY);
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}
	@FXML protected void onReloadLogsClick(ActionEvent evt){
		System.out.println("starting onReloadLogs Menu Click was successed.");
		this.printRecord();
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
	        marcketColumn.setCellValueFactory(new PropertyValueFactory<TradeLogRecord,String>("marcket"));
	        purchasePriceColumn.setCellValueFactory(new PropertyValueFactory<TradeLogRecord,Integer>("purchasePrice"));
	        purchaseNumColumn.setCellValueFactory(new PropertyValueFactory<TradeLogRecord,Integer>("purchaseNum"));
	        sellingPriceColumn.setCellValueFactory(new PropertyValueFactory<TradeLogRecord,Integer>("sellingPrice"));
	        sellingNumColumn.setCellValueFactory(new PropertyValueFactory<TradeLogRecord,Integer>("sellingNum"));
	       
	        //setCellFactory Method make a column Editable. 
	        purchasePriceColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
	        purchaseNumColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
	        sellingPriceColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
	        sellingNumColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
	        
	        this.printRecord();
	    }
	  
	  /**
	 * @param event CellEditEvent have informations where was edited from,what text was inputed.
	 * tableView.getItems means all recored.
	 * ObservableList<TradeLogRecord> means all record.
	 */
	@FXML protected void onPurchasePriceColumnCommit(CellEditEvent<TradeLogRecord, Integer> event){
		  System.out.println("onPurchasePriceColumnCommit Start");
		  int indexRow = tableView.getSelectionModel().getSelectedIndex(); 
		  event.getRowValue().setPurchasePriceProperty(event.getNewValue());
		  ObservableList<TradeLogRecord> recordList = tableView.getItems();
		  TradeLogRecord record = recordList.get(indexRow);

		  System.out.println("Selected Row =" + indexRow);
		  System.out.println(record.idProperty());
		  System.out.println(record.dateProperty());
		  System.out.println(record.nameProperty());
		  System.out.println(record.purchasePriceProperty());
	  }

	  private void printRecord(){
			SQLReadAllTradeLog sqlReadAllTradeLog= new SQLReadAllTradeLog();
	    	@SuppressWarnings("unused")
			MySQLConnector mysqlConnector = new MySQLConnector(sqlReadAllTradeLog);
	    	for ( int i = 0; i<tableView.getItems().size(); i++) {
	    	    tableView.getItems().clear();
	    	}
			sqlReadAllTradeLog.recordList.forEach(e->{
				this.tableView.getItems().add(new TradeLogRecord(
						e.idProperty().get(),
						e.dateProperty().get(),
						e.codeProperty().get(),
						e.nameProperty().get(),
						e.marcketProperty().get(),
						e.purchasePriceProperty().get(),
						e.purchaseNumProperty().get(), 
						e.sellingPriceProperty().get(), 
						e.sellingNumProperty().get()));
			});
	  }
}
