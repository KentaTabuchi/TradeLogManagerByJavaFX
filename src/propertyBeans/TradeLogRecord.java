package propertyBeans;

import java.util.Date;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
public class TradeLogRecord {
	private  IntegerProperty id;
	private ObjectProperty<Date> date;
	private IntegerProperty code;
	private StringProperty name;
	private StringProperty marcket;
	private  IntegerProperty purchasePrice;
	private  IntegerProperty purchaseNum;
	private  IntegerProperty sellingPrice;
	private IntegerProperty sellingNum;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public TradeLogRecord(int id, Date date, int code, String name,String marcket ,int purchasePrice,
			int purchaseNum, int sellingPrice, int sellingNum) {
		this.id= new SimpleIntegerProperty(id);
		this.date= new SimpleObjectProperty(date);
		this.code= new SimpleIntegerProperty(code);
		this.name= new SimpleStringProperty(name);
		this.marcket = new SimpleStringProperty(marcket);
		this.purchasePrice = new SimpleIntegerProperty(purchasePrice);
		this.purchaseNum = new SimpleIntegerProperty(purchaseNum);
		this.sellingPrice = new SimpleIntegerProperty(sellingPrice);
		this.sellingNum = new SimpleIntegerProperty(sellingNum);
	}

	public IntegerProperty idProperty() {
		return id;
	}
	public ObjectProperty<Date> dateProperty(){
		return date;
	}
	public IntegerProperty codeProperty(){
		return code;
	}
	public StringProperty nameProperty(){
		return name;
	}
	public StringProperty marcketProperty(){
		return marcket;
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
	public void setIdProperty(int id){
		this.id = new SimpleIntegerProperty(id);
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void setDateProperty(Date date){
		this.date = new SimpleObjectProperty(date);
	}
	public void setCodeProperty(int code){
		this.code = new SimpleIntegerProperty(code);
	}
	public void setPurchasePriceProperty(int purchasePrice){
		this.purchasePrice = new SimpleIntegerProperty(purchasePrice);
	}
	public void setPurchaseNumberProperty(int purchaseNumber){
		this.purchaseNum = new SimpleIntegerProperty(purchaseNumber);
	}
	public void setSellinPriceProperty(int sellingPrice){
		this.sellingPrice = new SimpleIntegerProperty(sellingPrice);
	}
	public void setSellingNumberProperty(int sellingNumber){
		this.sellingNum = new SimpleIntegerProperty(sellingNumber);
	}
}
