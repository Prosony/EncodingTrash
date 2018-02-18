package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Controller {

	@FXML
	public Button start;
	@FXML
	public TextArea source, result;
	@FXML
	ChoiceBox<String> crypto;
	@FXML
	TextField key;

	@FXML
	private void startAction(ActionEvent actionEvent){
		//System.out.println("Start bla-bla-bla");

		String typeCrypto = crypto.getValue();
		String sourceText = source.getText();
		System.out.println("source: "+sourceText);
		AlgoCrypt algoCrypt = new AlgoCrypt();

		switch (typeCrypto) {
			case "1":
				int keyInt = Integer.parseInt(this.key.getText()); //TODO add error if key > 50% of count symbol by source text
				String resultText = algoCrypt.firstAlgo(keyInt, sourceText);
				result.setText(resultText);
				break;
			case "2":
				String keyString = this.key.getText();
				algoCrypt.secondAlgo(keyString, sourceText);
				break;
			case "3":
				break;
		}

	}


}
