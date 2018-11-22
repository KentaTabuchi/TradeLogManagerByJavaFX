package customControl;

import javafx.scene.control.cell.TextFieldTableCell;
import propertyBeans.TradeLogRecord;

public class CustomTextField extends TextFieldTableCell<TradeLogRecord, Number> {

	@Override
	public void startEdit() {
		super.startEdit();
		System.out.println("startEdit");
	}

	@Override
	public void cancelEdit() {
		super.cancelEdit();
	}

	@Override
	public void updateItem(Number item, boolean empty) {
		super.updateItem(item, empty);
		System.out.println("updateItem");
	}
	
}
