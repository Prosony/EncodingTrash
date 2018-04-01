import handlers.HandlerAlgorithm;
import handlers.HandlerInitialization;
import handlers.HandlerKey;
import javafx.scene.control.*;
import methods.*;

public class Controller {
    public Button start;
    public Label error;
    public Label counter;
    public TextArea input;
    public TextArea output;
    public TextField key_1;
    public TextField key_2;
    public ChoiceBox<String> action;
    public ChoiceBox<String> algorithm;

    private final HandlerInitialization handlerInitialization = new HandlerInitialization();

    public void initialize () {
        action.getSelectionModel().select(0);
        algorithm.getSelectionModel().select(0);

        HandlerKey handlerKey = new HandlerKey();
        HandlerAlgorithm handlerAlgorithm = new HandlerAlgorithm();

        input.textProperty().addListener((observable, oldValue, newValue) -> {
            checkInitialization();
            counter.setText("Количество символов: " + input.getLength());
        });

        algorithm.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            key_1.setText("");
            key_2.setText("");

            if ((Integer) newValue == 4) {
                key_1.setVisible(false);
            } else {
                key_1.setVisible(true);
            }

            if (handlerAlgorithm.check((Integer) newValue)) {
                key_2.setVisible(true);
            } else {
                key_2.setVisible(false);
            }

            checkInitialization();
        });

        key_1.textProperty().addListener((observable, oldValue, newValue) -> {
            if (handlerKey.check(this.algorithm.getSelectionModel().getSelectedIndex(), newValue))
                error.setText("");
            else {
                key_1.setText(oldValue);

                switch (this.algorithm.getSelectionModel().getSelectedIndex()) {
                    case 0:
                        error.setText("1. Ключ должен состоять только из цифр.");
                        break;
                    case 1:
                        error.setText(
                            "1. Для метода шифрования 'Вертикальная перестановка' требуется\n" +
                            "указать ключ, состоящий из слова или набора символов\n" +
                            "из одного афавита, без знаков, цифр и пробелов.");
                        break;
                    case 2:
                        error.setText(
                            "1. Для метода шифрования двойной перестановки требуется\n" +
                            "указать ключ одним десятичным числом\n" +
                            "без пробелов и символов.");
                        break;
                    case 3:
                        error.setText(
                            "1. Для метода шифрования магический квадрат трубуется указать ключ вида:\n" +
                            "[n, m] - где n - кол-во стобцов, m - кол-во строк\n" +
                            "[n,...] - цифры в ячейках матрицы");
                        break;
                    case 4:
                        break;
                    case 5:
                        error.setText(
                            "1. Для метода шифрования цезарь требуется\n" +
                            "указать хотябы одну букву букву и ключ\n" +
                            "который больше нуля.");
                        break;
                    case 7:
                        error.setText(
                            "1. Ключ должен состоять только из букв\n" +
                            "русского или английского алфавита.");
                        break;
                }
            }
            checkInitialization();
        });

        key_2.textProperty().addListener((observable, oldValue, newValue) -> {
            if (handlerKey.check(this.algorithm.getSelectionModel().getSelectedIndex(), newValue))
                error.setText("");
            else {
                key_2.setText(oldValue);
                switch (this.algorithm.getSelectionModel().getSelectedIndex()) {
                    case 2:
                        error.setText(
                            "1. Для метода шифрования двойной перестановки\n" +
                            "требуется указать ключ одним десятичным числом\n" +
                            "без пробелов и символов.");
                        break;
                }
            }
            checkInitialization();
        });

        start.setOnMouseClicked(event -> {
            switch (algorithm.getSelectionModel().getSelectedIndex()) {
                case 0:
                    Scytale scytale = new Scytale(input.getText(), Integer.parseInt(key_1.getText()));
                    if (action.getSelectionModel().getSelectedIndex() == 0)
                        output.setText(scytale.encode());
                    else
                        output.setText(scytale.decode());
                    break;
                case 1:
                    VerticalReplacement vertical = new VerticalReplacement(); //input.getText(), key_1.getText()
                    if (action.getSelectionModel().getSelectedIndex() == 0){
                        output.setText(vertical.encode(input.getText(), key_1.getText()));
                    } else{
                        output.setText(vertical.decode(input.getText(), key_1.getText()));
                    }
                    break;
                case 2:
                    DoubleReplacement doubleReplacement = new DoubleReplacement();
                    output.setText(doubleReplacement.thirdEncryption(input.getText(), key_1.getText(), key_2.getText(), action.getSelectionModel().getSelectedIndex()));
                    break;
                case 3:
                    MagicSquare magicSquare = new MagicSquare(input.getText(), key_1.getText());
                    if (action.getSelectionModel().getSelectedIndex() == 0)
                        output.setText(magicSquare.encode());
                    else
                        output.setText(magicSquare.decode());
                    break;
                case 4:
                    output.setText(new PolybianSquare().encoder(input.getText().toCharArray(),action.getSelectionModel().getSelectedIndex()));
                    break;
                case 5:
                    Caesar caesar = new Caesar(input.getText(), key_1.getText());
                    if (action.getSelectionModel().getSelectedIndex() == 0)
                        output.setText(caesar.encode());
                    else
                        output.setText(caesar.decode());
                    break;
                case 6:
                    GronfeldCode code = new GronfeldCode();
                    output.setText(code.encode(input.getText().toCharArray(), key_1.getText().toCharArray(), action.getSelectionModel().getSelectedIndex()));
                    break;
                case 7:
                    Vigenere vigenere = new Vigenere(input.getText(), key_1.getText());
                    if (action.getSelectionModel().getSelectedIndex() == 0)
                        output.setText(vigenere.encode());
                    else
                        output.setText(vigenere.decode());
                    break;
                case 8:
                    WheatstoneDoubleSquare doubleSquare = new WheatstoneDoubleSquare();
                    output.setText( doubleSquare.encode(input.getText().toCharArray(), action.getSelectionModel().getSelectedIndex()));
                    break;
            }
        });
    }

    private void checkInitialization () {
        start.setVisible(handlerInitialization.check(
            this.action.getSelectionModel().getSelectedIndex(),
            this.algorithm.getSelectionModel().getSelectedIndex(),
            input.getText(),
            key_1.getText(),
            key_2.getText()
        ));

        switch (this.algorithm.getSelectionModel().getSelectedIndex()) {
            case 0:
                if  (!start.isVisible())
                    error.setText(
                        "1. Ключ должен быть больше единицы.\n" +
                        "2. Ключ не должен быть равен или быть больше длины текста.\n" +
                        "3. При расшифровке зашифрованный текст должен занимать все ячейки матрицы.");
                break;
            case 1:
                if  (!start.isVisible())
                    error.setText(
                        "1. Ключ должен состоять как минимум из двух символов и быть меньше длины текста.\n" +
                        "2. Ключ должен состоять из символов одного алфавита.\n" +
                        "3. Символы не должны повторяться.\n" +
                        "4. При расшифровке зашифрованный текст должен заполнять все ячейки матрицы.");
                break;
            case 2:
                if  (!start.isVisible())
                    error.setText(
                        "1. Ключ должен быть больше единицы.\n" +
                        "2. Ключ не должен быть равен длине текста.\n" +
                        "3. Не должен быть больше длины текста.");
                break;
            case 3:
                if  (!start.isVisible())
                    error.setText("1. При дешифровании зашифрованный текст должен заполнять все ячейки матрицы.");
                break;
            case 4:
                if  (!start.isVisible())
                    error.setText("1. Укажите текст.");
                break;
            case 5:
                if  (!start.isVisible())
                    error.setText(
                        "1. Укажите ключ больше нуля.\n" +
                        "2. Укажите текст или символ.");
                break;
            case 6:
                if  (!start.isVisible())
                    error.setText(
                        "1. Ключ должен быть больше единицы.\n" +
                        "2. Ключ не должен быть равен длине текста.\n" +
                        "3. Не должен быть больше длины текста.");
                break;
            case 7:
                if  (!start.isVisible())
                    error.setText(
                        "1. Ключ должен состоять из букв\n" +
                        "русского или английского алфавита.\n" +
                        "2. Ключ не должен содержать знаки и пробелы.\n" +
                        "3. Ключ должен быть меньше или равен длине текста.");
                break;
            case 8:
                if  (!start.isVisible())
                    error.setText("Намудрил братан");
                break;
        }
    }
}
