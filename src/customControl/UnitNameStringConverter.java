package customControl;

import javafx.util.StringConverter;

public class UnitNameStringConverter extends StringConverter<Integer>{

	private String unitName;
	public UnitNameStringConverter(String unitName){
		super();
		this.unitName = unitName;
	}
	@Override
	public Integer fromString(String string) {
		return Integer.valueOf(string);
		
	}

	@Override
	public String toString(Integer number) {
		//numberがnullPointerになっているので修正中
		try{
			return number.toString() + this.unitName;
		}catch(NullPointerException e){
			System.out.println("numberに値が入っていません");
			return "0" + this.unitName;
		}
	}



}
