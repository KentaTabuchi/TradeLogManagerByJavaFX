package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import propertyBeans.BookInfoRecord;
import propertyBeans.TradeLogRecord;
import sqlPublication.SQLReadAllBookInfo;

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
	        codeColumn.setCellValueFactory(new PropertyValueFactory<TradeLogRecord,Integer>("securitiesCode"));
	        bookNameColumn.setCellValueFactory(new PropertyValueFactory<TradeLogRecord,Integer>("bookName"));
	        marcketColumn.setCellValueFactory(new PropertyValueFactory<TradeLogRecord,Integer>("marcket"));
		this.printRecord();
	}
	@FXML protected void onReloadBookInfoClick(ActionEvent evt){
		System.out.println("starting onReloadBookInfo Menu Click was successed.");
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
}
