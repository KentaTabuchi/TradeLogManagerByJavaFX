/**
 * 
 */
package customControl;

import application.TradeLogTableStageController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TextField;
import javafx.util.converter.NumberStringConverter;
import propertyBeans.TradeLogRecord;

/**
 * @author misskabu
 *
 */
public class PLTextFieldTableCell extends TableCell<TradeLogRecord, Number> {
	public static TradeLogTableStageController tradeLogTableStageController;
	private TextField textField;
	private Label label;//編集時以外はラベルをグラフィックとして使う
	private int row;
	@Override
	public void commitEdit(Number newValue) {
		// TODO コミットイベントはここでする、バグ残り。
		super.commitEdit(newValue);
		this.setGraphic(label);
		
		tradeLogTableStageController.tableView.getSelectionModel().getSelectedItems().get(row).PLProperty().setValue(newValue);
		tradeLogTableStageController.updateRecord();
       
		System.out.println("PLTextField->commitEdit");
	}

	@Override
	public void updateItem(Number item, boolean empty) {
		System.out.println("PLTextFieldCell->updateItem");
		super.updateItem(item, empty);
		if(item == null || empty){
			setText(null);
			setGraphic(null);
		}
		else{
			System.out.println(item);
			if(super.isEditing()){
				if(this.textField != null){
					this.setGraphic(this.textField);
					this.setText(item.toString());
					}
				else{
					this.setGraphic(label);
					this.setText(item.toString());
				}
			}
			else{
				this.setGraphic(label);
				this.setText(item.toString());				
			}			
			
		}
	}

	@Override
	public void startEdit() {
		this.row = tradeLogTableStageController.tableView.getSelectionModel().getSelectedIndex();
		System.out.println("テーブル内の選択行は" + this.row);
		super.startEdit();
        if (! isEditable()
                || ! getTableView().isEditable()
                || ! getTableColumn().isEditable()) {
            return;
        }
        super.startEdit();

        if (isEditing()) {
            if (textField == null) {
                textField = new TextField();
                textField.setMinWidth(this.getWidth() - this.getGraphicTextGap() * 2);
                //これも使い捨て
                ChangeListener<Boolean> listener = new ChangeListener<Boolean>() {
                    @Override
                    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                        System.out.println(("ChangeListener newValue")+newValue);
                        if (!newValue){
                        	NumberStringConverter converter = new NumberStringConverter();
                        	Number number = converter.fromString(textField.getText());
                    	 commitEdit(number);
                        }
                    }
                };
                System.out.println(("ChangeListener - HashCode")+listener.hashCode());
                textField.focusedProperty().addListener(listener);
                setText(null);
                setGraphic(textField);
                textField.selectAll();
                System.out.println(("startEdit exit"));

            }

        }
	}
	

}
// セルのコールバックを設定のやり方
/*        pLColumn.setCellFactory(tableColumn -> {
    return new TableCell<TradeLogRecord,Integer>() {
        @Override protected void updateItem(final Integer item, final boolean empty) {
            super.updateItem(item, empty);

            if (empty || item == null) {
                setText(null);
                setGraphic(null);
            } else {
                setText(item.toString());

                // 対象外にスタイル設定されていた場合、削除する。
                getStyleClass().remove("minus-number-column");

                // スタイルを設定する。
                if (item < 0) {
                    getStyleClass().add("minus-number-column");
                }
            }

        }
    };
});*/