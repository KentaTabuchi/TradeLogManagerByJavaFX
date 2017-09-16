package customControl;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import application.H2DBConnector;
import application.ISQLExecutable;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import propertyBeans.TradeLogRecord;
import sqlPublication.SQLReadAllTradeLog;
import sqlPublication.SQLUpdateTradeLog;

/**
 * @author misskabu
 *
 * @param <S>
 * @param <T>
 * 
 * This class is not independent.This is strong depends on TradeLogRecord,SQLReadAllTradeLog,
 * MySQLConnector. 
 */
public class DatePickerTableCell<S, T> extends TableCell<TradeLogRecord, Date> {
	private DatePicker datePicker;
	private ArrayList<TradeLogRecord> record;
	
    public DatePickerTableCell(List<TradeLogRecord> recordList) {
        super();
        this.record = (ArrayList<TradeLogRecord>) recordList;
        if (datePicker == null) {
            createDatePicker();
        }
        setGraphic(datePicker);
        setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                datePicker.requestFocus();
            }
        });
    }

	private void createDatePicker() {
        this.datePicker = new DatePicker();
        datePicker.setPromptText("dd/mm/yyyy");
        datePicker.setEditable(true);
        datePicker.setOnAction(t -> {
		    LocalDate date = datePicker.getValue();
		    int index = getIndex();
		    SimpleDateFormat smp = new SimpleDateFormat("dd/MM/yyyy");
		    Calendar cal = Calendar.getInstance();
		    cal.set(Calendar.DAY_OF_MONTH, date.getDayOfMonth());
		    cal.set(Calendar.MONTH, date.getMonthValue() - 1);
		    cal.set(Calendar.YEAR, date.getYear());
		    setText(smp.format(cal.getTime()));
		    commitEdit(cal.getTime());

		    if (null != getTradeLogRecord()) {
		        getTradeLogRecord().get(index).setDateProperty((cal.getTime()));
		    }
		});

        setAlignment(Pos.CENTER);
        }
    @Override
    public void updateItem(Date item, boolean empty) {
        super.updateItem(item, empty);
        SimpleDateFormat smp = new SimpleDateFormat("dd/MM/yyyy");
        if (null == this.datePicker) {
            System.out.println("datePicker is NULL");
        }
        if (empty) {
            setText(null);
            setGraphic(null);
        } else {
            if (isEditing()) {
                setContentDisplay(ContentDisplay.TEXT_ONLY);
            } else {
                setDatepickerDate(smp.format(item));
                setText(smp.format(item));
                setGraphic(this.datePicker);
                setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
            }
        }
    }
    private void setDatepickerDate(String dateAsStr) {
        LocalDate ld = null;
        int day, month, year;
        day = month = year = 0;
        try {
            day = Integer.parseInt(dateAsStr.substring(0, 2));
            month = Integer.parseInt(dateAsStr.substring(3, 5));
            year = Integer.parseInt(dateAsStr.substring(6, dateAsStr.length()));
        } catch (NumberFormatException e) {
            System.out.println("setDatepikerDate / unexpected error " + e);
        }
        ld = LocalDate.of(year, month, day);
        datePicker.setValue(ld);
    }
    @Override
    public void startEdit() {
        super.startEdit();
    }

    @Override
    public void cancelEdit() {
        super.cancelEdit();
        setContentDisplay(ContentDisplay.TEXT_ONLY);
    }
    @Override
    public void commitEdit(Date date){
    	super.commitEdit(date);
    	System.out.println("datePickerEdited");
    	int indexRow = getIndex();
		List<TradeLogRecord> recordList=null;
		SQLReadAllTradeLog sqlReadAllTradeLog = new SQLReadAllTradeLog();
		@SuppressWarnings("unused")
		H2DBConnector con = new H2DBConnector(sqlReadAllTradeLog);
		recordList = sqlReadAllTradeLog.recordList;
		TradeLogRecord record = recordList.get(indexRow);

		ISQLExecutable sqlUpdateTradeLog = new SQLUpdateTradeLog(
				record.idProperty().intValue(),
				new java.sql.Date(date.getTime()),
				record.codeProperty().intValue(),
				record.purchasePriceProperty().intValue(),
				record.purchaseNumProperty().intValue(),
				record.sellingPriceProperty().intValue(),
				record.sellingNumProperty().intValue());
		@SuppressWarnings("unused")
		H2DBConnector mySQLConnector = new H2DBConnector(sqlUpdateTradeLog);	
    }
    
    public ArrayList<TradeLogRecord> getTradeLogRecord() {
        return record;
    }

    public void setTradeLogRecord(ArrayList<TradeLogRecord> record) {
        this.record = record;
    }

    public DatePicker getDatePicker() {
        return datePicker;
    }

    public void setDatePicker(DatePicker datePicker) {
        this.datePicker = datePicker;
    }
}
