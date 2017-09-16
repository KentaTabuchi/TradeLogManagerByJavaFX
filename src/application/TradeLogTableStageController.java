/**
 * 
 */
package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.ResourceBundle;

import customControl.DatePickerTableCell;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
import propertyBeans.TradeLogRecord;
import sqlPublication.SQLDeleteTradeLog;
import sqlPublication.SQLReadAllBookInfo;
import sqlPublication.SQLReadAllTradeLog;
import sqlPublication.SQLUpdateTradeLog;

/**
 * @author misskabu
 *
 */
public  class TradeLogTableStageController implements Initializable{
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
		this.createBoderPaneStage("AddLogStage.fxml","AddLogStage",350,0, 400, 400);
	}
	@FXML protected void onShowAddBookInfoWindowMenuClick(ActionEvent evt){
		System.out.println("starting onShowAddBookInfoWindowMenuClick was successed.");
		this.createBoderPaneStage("AddBookInfoStage.fxml","AddBookInfoStage",350,0, 400, 170);
	}
	@FXML protected void onShowBookInfoTableWindowMenuClick(ActionEvent evt){
		System.out.println("starting onShowBookInfoTableWindowMenuClick was successed.");
		this.createBoderPaneStage("BookInfoTableStage.fxml","BookInfoTableStage",100,100, 400, 500);
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
			root = (BorderPane)FXMLLoader.load(getClass().getResource("/fxml/" + fxmlFileName));
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
	private void createBoderPaneStage(String fxmlFileName,String title,int posX,int posY,int width,int height){
		Stage stage = new Stage();
		BorderPane root;
		try {
			root = (BorderPane)FXMLLoader.load(getClass().getResource("/fxml/" + fxmlFileName));
			Scene scene = new Scene(root,width,height);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			stage.setScene(scene);
			stage.show();
			stage.setX(stage.getX()+posX);
			stage.setY(stage.getY()+posY);
			stage.setTitle(title);
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}
	@Override public String toString(){
		return "This is the Controller which is Controll fxml.";
		
	}
	@FXML protected void onReloadLogsClick(ActionEvent evt){
		System.out.println("starting onReloadLogs Menu Click was successed.");
		this.printRecord();
	}
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
		this.setCellValueFactoryes();
		this.setCellFactoryes();
		this.printRecord();

	}
	/**
	 * setCellFactory Method make a column Editable. 
	 * dateColumn.setCellFactory(DatePickerTableCell.setTableColumn(dateColumn);
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void setCellFactoryes(){
		SQLReadAllTradeLog sqlReadAllTradeLog = new SQLReadAllTradeLog();
		@SuppressWarnings("unused")
		H2DBConnector connector = new H2DBConnector(sqlReadAllTradeLog);
        dateColumn.setCellFactory(p -> {
		    DatePickerTableCell datePick = new DatePickerTableCell(sqlReadAllTradeLog.recordList);
		    return datePick;
		});
		codeColumn.setCellFactory(ComboBoxTableCell.forTableColumn(this.getSecuritiesCodeList().toArray()));
		purchasePriceColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
		purchaseNumColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
		sellingPriceColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
		sellingNumColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
	}
	@SuppressWarnings("unchecked")
	private void setCellValueFactoryes(){
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

	}
	private ArrayList<Integer> getSecuritiesCodeList(){
		ArrayList<Integer> list = new ArrayList<>();
		SQLReadAllBookInfo sqlReadAllbookInfo = new SQLReadAllBookInfo();
		@SuppressWarnings("unused")
		H2DBConnector connector = new H2DBConnector(sqlReadAllbookInfo);
		sqlReadAllbookInfo.recordList.forEach(e->{
			list.add(e.securitiesCodeProperty().get());
		});
		return list;
  
	}

	/**
	 * @param event CellEditEvent have informations where was edited from,what text was inputed.
	 * tableView.getItems means all recored.
	 * ObservableList<TradeLogRecord> means all record.
	 */
	@FXML protected void onStart(){
		System.out.println("start");
	}
	@FXML protected void onTradeDateColumnCommit(CellEditEvent<TradeLogRecord,Date> event){
		System.out.println("onTradeDateColumnCommit Start");

		try{event.getRowValue().setDateProperty((event.getNewValue()));}
		catch(IllegalArgumentException e){
			System.out.println("Input failed.Please input correct Date");
			Alert alert = new Alert(AlertType.ERROR,"Please input correct Date",ButtonType.OK);
			alert.show();
		}
		this.updateRecord();
	}
	@FXML protected void onSecuritiesCodeColumnCommit(CellEditEvent<TradeLogRecord, Integer> event){
		System.out.println("onSecuritiesCodeColumnCommit Start");
		event.getRowValue().setCodeProperty(event.getNewValue());
		this.updateRecord();
		this.printRecord();
	}
	@FXML protected void onPurchasePriceColumnCommit(CellEditEvent<TradeLogRecord, Integer> event){
		System.out.println("onPurchasePriceColumnCommit Start");
		event.getRowValue().setPurchasePriceProperty(event.getNewValue());
		this.updateRecord();
	}
	@FXML protected void onPurchaseNumberColumnCommit(CellEditEvent<TradeLogRecord, Integer> event){
		System.out.println("onPurchaseNumberColumnCommit Start");
		event.getRowValue().setPurchaseNumberProperty(event.getNewValue());
		this.updateRecord();
	}
	@FXML protected void onSellingPriceColumnCommit(CellEditEvent<TradeLogRecord, Integer> event){
		System.out.println("onSellingPriceColumnCommit Start");
		event.getRowValue().setSellinPriceProperty(event.getNewValue());
		this.updateRecord();
	}
	@FXML protected void onSellingNumberColumnCommit(CellEditEvent<TradeLogRecord, Integer> event){
		System.out.println("onSellingNumberColumnCommit Start");
		event.getRowValue().setSellingNumberProperty(event.getNewValue());
		this.updateRecord();
	}
	@FXML protected void onContextMenuRequested(){
		System.out.println("onContextMuenuRequested Start");
		int indexRow = this.tableView.getSelectionModel().getSelectedIndex();
		System.out.println(indexRow);
		ObservableList<TradeLogRecord> recordList = tableView.getItems();
		try{
			TradeLogRecord record = recordList.get(indexRow);
			SQLDeleteTradeLog sqlDeleteTradeLog = new SQLDeleteTradeLog(record.idProperty().intValue());
			@SuppressWarnings("unused")
			H2DBConnector con = new H2DBConnector(sqlDeleteTradeLog);}
		catch(ArrayIndexOutOfBoundsException e){
			System.out.println("Record is not selected.Please select any record.");
		}
		this.printRecord();
	}

	private void updateRecord(){
		int indexRow = tableView.getSelectionModel().getSelectedIndex(); 

		ObservableList<TradeLogRecord> recordList = tableView.getItems();
		TradeLogRecord record = recordList.get(indexRow);

		System.out.println("Selected Row =" + indexRow);
		System.out.println(record.idProperty());
		System.out.println(record.dateProperty().toString());
		System.out.println(record.nameProperty());
		System.out.println(record.purchasePriceProperty());

		ISQLExecutable sqlUpdateTradeLog = new SQLUpdateTradeLog(
				record.idProperty().intValue(),
				new java.sql.Date(record.dateProperty().getValue().getTime()),
				record.codeProperty().intValue(),
				record.purchasePriceProperty().intValue(),
				record.purchaseNumProperty().intValue(),
				record.sellingPriceProperty().intValue(),
				record.sellingNumProperty().intValue());
		@SuppressWarnings("unused")
		H2DBConnector mySQLConnector = new H2DBConnector(sqlUpdateTradeLog);		
	}

	public void printRecord(){
		SQLReadAllTradeLog sqlReadAllTradeLog= new SQLReadAllTradeLog();
		@SuppressWarnings("unused")
		H2DBConnector mysqlConnector = new H2DBConnector(sqlReadAllTradeLog);
		try{
		for ( int i = 0; i<tableView.getItems().size(); i++) {
			tableView.getItems().clear();
		}}catch(IndexOutOfBoundsException e){
			System.out.println("IndexOutOfBoudsException. please check look counter in the printrecord");
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
