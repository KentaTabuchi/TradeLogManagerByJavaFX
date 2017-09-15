package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import propertyBeans.BookInfoRecord;
import propertyBeans.TradeLogRecord;
import sqlPublication.SQLReadAllBookInfo;
import sqlPublication.SQLUpdateBookInfo;

public class BookInfoTableStageController implements Initializable {
    @FXML private TableView<BookInfoRecord> tableView;
    @SuppressWarnings("rawtypes")
	@FXML private TableColumn codeColumn;
    @SuppressWarnings("rawtypes")
	@FXML private TableColumn bookNameColumn;
    @SuppressWarnings("rawtypes")
	@FXML private TableColumn marcketColumn;
	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO 自動生成されたメソッド・スタブ
		  //引数の"id","date"などの文字列がPropertyBeansクラスのBookInfoRecordのprivate変数名と完全一致させると
		  //TableViewと関連づけられる。文字列を間違えるとデータを表示できない。
		
			marcketColumn.setCellFactory(ComboBoxTableCell.forTableColumn(this.marcketNameList().toArray()));
			
			codeColumn.setCellValueFactory(new PropertyValueFactory<TradeLogRecord,Integer>("securitiesCode"));
	        bookNameColumn.setCellValueFactory(new PropertyValueFactory<TradeLogRecord,Integer>("bookName"));
	        marcketColumn.setCellValueFactory(new PropertyValueFactory<TradeLogRecord,String>("marcket"));
	        
	        
		this.printRecord();
	}
		private ArrayList<String> marcketNameList(){
			ArrayList<String> list = new ArrayList<>();
			for(Marcket marcketName:Marcket.values()){
				list.add(marcketName.name());
			}
			return list;
		} 
	@FXML protected void onReloadBookInfoClick(ActionEvent evt){
		System.out.println("starting onReloadBookInfo Menu Click was successed.");
		this.printRecord();
	}
	@FXML protected void onMarcketColumnCommit(CellEditEvent<BookInfoRecord, String> event){
		System.out.println("onSecuritiesCodeColumnCommit Start");
		event.getRowValue().setMarcketProperty(event.getNewValue());
		this.updateRecord();
		this.printRecord();
	}

	  private void printRecord(){
			SQLReadAllBookInfo sqlReadAllBookInfo= new SQLReadAllBookInfo();
	    	@SuppressWarnings("unused")
			MySQLConnector mysqlConnector = new MySQLConnector(sqlReadAllBookInfo);
	    	for ( int i = 0; i<tableView.getItems().size(); i++) {
	    	    tableView.getItems().clear();
	    	}
			sqlReadAllBookInfo.recordList.forEach(e->{
				this.tableView.getItems().add(new BookInfoRecord(
							e.securitiesCodeProperty().get(),
							e.bookNameProperty().get(),
							e.marcketProperty().get())
						);
			});
	  }
		private void updateRecord(){
			int indexRow = tableView.getSelectionModel().getSelectedIndex(); 

			ObservableList<BookInfoRecord> recordList = tableView.getItems();
			BookInfoRecord record = recordList.get(indexRow);

			System.out.println("Selected Row =" + indexRow);
			System.out.println(record.securitiesCodeProperty());
			System.out.println(record.bookNameProperty());
			System.out.println(record.marcketProperty());

			ISQLExecutable sqlUpdateBookInfo = new SQLUpdateBookInfo(
					record.securitiesCodeProperty().intValue(),
					record.bookNameProperty().getValue(),
					record.marcketProperty().getValue()
					);
			@SuppressWarnings("unused")
			MySQLConnector mySQLConnector = new MySQLConnector(sqlUpdateBookInfo);		
		}
}
