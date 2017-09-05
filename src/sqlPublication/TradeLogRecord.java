package sqlPublication;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
public class TradeLogRecord {
	private  IntegerProperty id;
	private StringProperty date;
	private IntegerProperty code;
	private StringProperty name;
	private  IntegerProperty purchasePrice;
	private  IntegerProperty purchaseNum;
	private  IntegerProperty sellingPrice;
	private IntegerProperty sellingNum;
	
	public TradeLogRecord(int id, String date, int code, String name, int purchasePrice,
			int purchaseNum, int sellingPrice, int sellingNum) {
		this.id= new SimpleIntegerProperty(id);
		this.date= new SimpleStringProperty(date);
		this.code= new SimpleIntegerProperty(code);
		this.name= new SimpleStringProperty(name);
		this.purchasePrice = new SimpleIntegerProperty(purchasePrice);
		this.purchaseNum = new SimpleIntegerProperty(purchaseNum);
		this.sellingPrice = new SimpleIntegerProperty(sellingPrice);
		this.sellingNum = new SimpleIntegerProperty(sellingNum);
	}

	public IntegerProperty idProperty() {
		return id;
	}
	public StringProperty dateProperty(){
		return date;
	}
	public IntegerProperty codeProperty(){
		return code;
	}
	public StringProperty nameProperty(){
		return name;
	}
	public IntegerProperty purchasePriceProperty(){
		return purchasePrice;
	}
	public IntegerProperty purchaseNumProperty(){
		return purchaseNum;
	}
	public IntegerProperty sellingPriceProperty(){
		return sellingPrice;
	}
	public IntegerProperty sellingNumProperty(){
		return sellingNum;
	}
}
