package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {

	public static TradeLogTableStageController tradeLogTableStageController;
	@Override
	public void start(Stage primaryStage) {
		
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("TradeLogTableStage.fxml"));
			Scene scene = new Scene((BorderPane)fxmlLoader.load());
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setWidth(800);
			primaryStage.setX(primaryStage.getX()-200);
			tradeLogTableStageController = fxmlLoader.getController();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
