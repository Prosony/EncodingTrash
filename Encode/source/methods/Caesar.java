package methods;

public class Caesar {

    // Описание:             Ключом указывается количество смещений символа
    //                       в строке согласно алфавиту.
    //
    //                       При шифровании каждый символ строки смещается
    //                       на символ в алфавите, который дальше на число в ключе.
    //
    //                       При дешифровании каждый символ строки смещается
    //                       на символ в алфавите, который ближе на число в ключе.
    //
    // Ключ:                 3 - количество смещений каждого символа в строке
    //
    // Алгоритм шифрования:  Каждый символ в строке меняется на символ алфавита, который
    //                       впереди символа строки на число, которое указано в ключе.
    //
    // Строка:               япрограммист
    //
    // Алфавит:              'а','б','в','г','д','е','ё','ж','з','и','й',
    //                       'к','л','м','н','о','п','р','с','т','у','ф',
    //                       'х','ц','ч','ш','щ','ъ','ы','ь','э','ю','я'
    //
    // Алгоритм:             [я] -> [в], [п] -> [т] ...
    //
    // Результат:            втусёугпплфх
    //
    // Алгорим дешифрования: Каждый символ в строке меняется на символ алфавита, который
    //                       сзади символа строки на число, которое указано в ключе.
    //
    // Строка:               втусёугпплфх
    //
    // Алфавит:              'а','б','в','г','д','е','ё','ж','з','и','й',
    //                       'к','л','м','н','о','п','р','с','т','у','ф',
    //                       'х','ц','ч','ш','щ','ъ','ы','ь','э','ю','я'
    //
    // Алгоритм:             [в] -> [я], [т] -> [п] ...
    //
    // Результат:            япрограммист

    private int key;
    private char[] characterArray;

    private final char[] eng = {'a','b','c','d','e','f','g','h',
                                'i','j','k','l','m','n','o','p',
                                'q','r','s','t','u','w','x','y','z'};

    private final char[] rus = {'а','б','в','г','д','е','ё','ж','з','и','й',
                                'к','л','м','н','о','п','р','с','т','у','ф',
                                'х','ц','ч','ш','щ','ъ','ы','ь','э','ю','я'};

    public Caesar (String string, String key) {
        this.key = Integer.parseInt(key);
        this.characterArray = string.toCharArray();
    }

    public String encode () {
        StringBuilder stringBuilder = new StringBuilder();

        for (char character : characterArray) {
            for (int indexRussianCharacter = 0; indexRussianCharacter < rus.length; indexRussianCharacter++) {
                if (Character.toLowerCase(character) == rus[indexRussianCharacter]) {
                    stringBuilder.append(findEncodedCharacter(indexRussianCharacter, rus));
                    break;
                }
            }

            for (int indexEnglishCharacter = 0; indexEnglishCharacter < eng.length; indexEnglishCharacter++) {
                if (Character.toLowerCase(character) == eng[indexEnglishCharacter]) {
                    stringBuilder.append(findEncodedCharacter(indexEnglishCharacter, eng));
                    break;
                }
            }
        }

        return stringBuilder.toString();
    }

    private char findEncodedCharacter(int indexCharacterAlphabet, char[] alphabet) {
        for (int index = 0; index < key; index ++) {
            indexCharacterAlphabet ++;
            if (indexCharacterAlphabet >= alphabet.length) {
                indexCharacterAlphabet = 0;
            }
        }

        return alphabet[indexCharacterAlphabet];
    }

    public String decode () {
        StringBuilder stringBuilder = new StringBuilder();

        for (char character : characterArray) {
            for (int indexRussianCharacter = 0; indexRussianCharacter < rus.length; indexRussianCharacter++) {
                if (Character.toLowerCase(character) == rus[indexRussianCharacter]) {
                    stringBuilder.append(findDecodedCharacter(indexRussianCharacter, rus));
                    break;
                }
            }

            for (int indexEnglishCharacter = 0; indexEnglishCharacter < eng.length; indexEnglishCharacter++) {
                if (Character.toLowerCase(character) == eng[indexEnglishCharacter]) {
                    stringBuilder.append(findDecodedCharacter(indexEnglishCharacter, eng));
                    break;
                }
            }
        }

        return stringBuilder.toString();
    }

    private char findDecodedCharacter (int indexCharacterAlphabet, char[] alphabet) {
        for (int index = 0; index < key; index ++) {
            indexCharacterAlphabet --;
            if (indexCharacterAlphabet < 0) {
                indexCharacterAlphabet = alphabet.length - 1;
            }
        }

        return alphabet[indexCharacterAlphabet];
    }
}
