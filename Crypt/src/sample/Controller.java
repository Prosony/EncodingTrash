package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import sample.service.CheckKey;

public class Controller {

	@FXML
	public Button start;
	@FXML
	public TextArea source, result;
	@FXML
	ChoiceBox<String> method;
	@FXML
	TextField key;
	@FXML
	ChoiceBox<String> cryptoKind;

	@FXML
	private void startAction(ActionEvent actionEvent) {

		String methodValue = method.getValue();
		String cryptoKindValue = cryptoKind.getValue();
		String sourceText = source.getText();
		System.out.println("source: " + sourceText);


		CheckKey checkKey = new CheckKey();

		switch (methodValue) {
			case "1":
				String key = this.key.getText();
				boolean isKeyCorrect = checkKey.checkFirst(key);
				if (isKeyCorrect) {
					int keyInt = Integer.parseInt(key); //TODO add error if key > 50% of count symbol by source text
					if (keyInt < (sourceText.length()/2)){
						if (cryptoKindValue.equals("Encoding")) {
							Encryption encryption = new Encryption();
							String resultTextFirstEncoding = encryption.firstEncryption(keyInt, sourceText);
							result.setText(resultTextFirstEncoding);
						} else {
							Decryption decryption = new Decryption();
							String resultTextFirstdecryption = decryption.decryptionFirst(keyInt, sourceText);
							result.setText(resultTextFirstdecryption);
						}
					}else{
						System.out.println("#INFO [Controller] [startAction] [ERROR] key is too long!");
					}
				} else {
					System.out.println("#INFO [Controller] [startAction] [ERROR] key is invalid!");
				}
				break;
			case "2":
				String keyString = this.key.getText();
				boolean isKeySecondCorrect = checkKey.checkSecond(keyString);
				if (isKeySecondCorrect) {
					if (keyString.length() < (sourceText.length()/2)){
						if (cryptoKindValue.equals("Encoding")) {
							Encryption encryption = new Encryption();
							String resultTextSecondEncoding = encryption.secondEncryption(keyString, sourceText);
							result.setText(resultTextSecondEncoding);
						} else {
							Decryption decryption = new Decryption();
							String resultTextSecondDecryption = decryption.decryptionSecond(keyString, sourceText);
							result.setText(resultTextSecondDecryption);
						}
					}else{
						System.out.println("#INFO [Controller] [startAction] [ERROR] key is too long!");
					}
				}else{
					System.out.println("#INFO [Controller] [startAction] [ERROR] key is invalid!");
				}
				break;
			case "3":
				break;
		}

	}
}
