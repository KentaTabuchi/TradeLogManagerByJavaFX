/**
 * 
 */
package application;

import java.io.FileWriter;
import java.io.IOException;

import propertyBeans.TradeLogRecord;
import sqlPublication.SQLReadAllTradeLog;

/**
 * @author misskabu
 *
 */
public class CsvExpoter {

	/**
	 * データベースをCVSへエクスポートする
	 */
	public CsvExpoter() {
		export();
	}
	private void export(){
		final String currentDirectory = System.getProperty("user.dir");
		final String pass = currentDirectory + "/tradeLog.csv";
		System.out.println(pass);
		
		try {
			FileWriter fw;
			fw = new FileWriter(pass);
			writeDatabaseToFile(fw);
			fw.close();
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			}

	}
	private void writeDatabaseToFile(FileWriter fw){
		SQLReadAllTradeLog sqlReadAllTradeLog= new SQLReadAllTradeLog();
		@SuppressWarnings("unused")
		H2DBConnector mysqlConnector = new H2DBConnector(sqlReadAllTradeLog);

		sqlReadAllTradeLog.recordList.forEach(e->{
			try {
				fw.write(
						e.idProperty().get()+","+
						e.dateProperty().get()+","+
						e.codeProperty().get()+","+
						e.nameProperty().get()+","+
						e.marcketProperty().get()+","+
						e.purchasePriceProperty().getValue().toString()+","+
						e.purchaseNumProperty().getValue().toString()+","+
						e.sellingPriceProperty().getValue().toString()+ ","+
						e.sellingNumProperty().getValue().toString()+","+
						e.PLProperty().get()+","+
						e.memoProperty().get()+"\n");
				fw.flush();
			} catch (Exception e1) {
				// TODO 自動生成された catch ブロック
				e1.printStackTrace();
			}
		});
		
	}

}
