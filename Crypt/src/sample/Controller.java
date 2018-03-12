package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import sample.service.key.CheckKey;
import sample.service.key.KeyGetting;

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
				String keyFirst = this.key.getText();
				boolean isKeyCorrect = checkKey.checkFirst(keyFirst);
				if (isKeyCorrect) {
					int keyInt = Integer.parseInt(keyFirst); //TODO add error if key > 50% of count symbol by source text
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
				String keySecond = this.key.getText();

				boolean isKeySecondCorrect = checkKey.checkKey(keySecond);
				if (isKeySecondCorrect) {
					if (keySecond.length() < (sourceText.length()/2)){
						if (cryptoKindValue.equals("Encoding")) {
							Encryption encryption = new Encryption();
							String resultTextSecondEncoding = encryption.secondEncryption(keySecond, sourceText);
							result.setText(resultTextSecondEncoding);
						} else {
							Decryption decryption = new Decryption();
							String resultTextSecondDecryption = decryption.decryptionSecond(keySecond, sourceText);
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
				String keyThird = this.key.getText();
				boolean isKeyThirdCorrect = checkKey.checkDoubleKey(keyThird);
				if (isKeyThirdCorrect) {
					if (cryptoKindValue.equals("Encoding")) {
						System.out.println("fuck damn!");
						KeyGetting getting = new KeyGetting();
						String[] keys = getting.getDoubleKeyFromString(keyThird);
						System.out.println("key one: "+keys[0]);
						System.out.println("key two: "+keys[1]);
						Encryption encryption = new Encryption();
						encryption.thirdEncription(keys, sourceText);
					}else{
						//TODO decryption
					}
				}
					break;

		}

	}
}
/**
 * int[][] array = new int[5][5];
 int i = 0;
 for (int height = 0; height < 5; height++) {
 i = height;
 for (int width = 0; width < 5; width++) {
 array[height][width] = i;
 i++;
 System.out.print(array[height][width]);
 }
 System.out.println();
 }
 */