package customControl;

import javafx.util.StringConverter;

public class UnitNameStringConverter extends StringConverter<String>{

	private String unit;
	@Override
	public String fromString(String string) {
		return string;
	}

	@Override
	public String toString(String unit) {
		return unit + this.unit;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

}
