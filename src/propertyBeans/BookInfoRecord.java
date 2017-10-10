package propertyBeans;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
public class BookInfoRecord {
	private  IntegerProperty securitiesCode;
	private StringProperty bookName;
	private StringProperty marcket;
	private IntegerProperty profit;
	public BookInfoRecord(int securitiesCode, String bookName, String marcket,int profit) {
		this.securitiesCode= new SimpleIntegerProperty(securitiesCode);
		this.bookName= new SimpleStringProperty(bookName);
		this.marcket= new SimpleStringProperty(marcket);
		this.profit = new SimpleIntegerProperty(profit);
	}

	public IntegerProperty securitiesCodeProperty() {
		return securitiesCode;
	}
	public StringProperty bookNameProperty(){
		return bookName;
	}
	public StringProperty marcketProperty(){
		return marcket;
	}
	public IntegerProperty profitProperty(){
		return profit;
	}
	public void setMarcketProperty(String marcket){
		this.marcket = new SimpleStringProperty(marcket);
	}



}
