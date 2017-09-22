package stageController;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;


public class HelpStageContoroller implements Initializable{
	@FXML private WebView webView;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		WebEngine engine = webView.getEngine();
		String url = getClass().getResource("index.html").toExternalForm();
		System.out.println(url);
		engine.load(url);
	}
	
	
}
