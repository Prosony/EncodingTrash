package methods;

public class Vigenere {

    // Описание:             Ключом указываются символы, индекс которых используется
    //                       для определения числа смещения каждого символа в строке.
    //
    //                       При шифровании каждый символ строки смещается вперед
    //                       на число (индекс символа в ключе) позиций в алфавите.
    //
    //                       При дешифровании каждый символ строки смещается назад
    //                       на число (индекс символа в ключе) позиций в алфавите.
    //
    // Ключ:                 БАГБА
    // Индексы:              [Б][А][Г][Б][А]
    //                       [1][0][3][1][0]
    //
    // Алгоритм шифрования:  Каждый символ строки смещается вперед
    //                       на число (индекс символа в ключе) позиций в алфавите.
    //
    // Строка:               ВЕДРО
    //
    //
    // Алгоритм:             [в] -> [г], [е] -> [е] ...
    //
    // Результат:            ГЕЖСО
    //
    // Алгорим дешифрования: Каждый символ строки смещается назад
    //                       на число (индекс символа в ключе) позиций в алфавите.
    //
    // Строка:               ГЕЖСО
    //
    // Алгоритм:             [г] -> [в], [е] -> [е] ...
    //
    // Результат:            ВЕДРО

    private char[] key;
    private char[][] doubleCharacterArray;

    private final char[] eng = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
    //  'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',' '
    //   0   1   2   3   4   5   6   7   8   9  10  11  12  13  14  15  16  17  18  19  20  21  22  23  24  25  26
    private final char[] rus = {'а','б','в','г','д','е','ё','ж','з','и','й','к','л','м','н','о','п','р','с','т','у','ф','х','ц','ч','ш','щ','ъ','ы','ь','э','ю','я'};
    //  'а','б','в','г','д','е','ё','ж','з','и','й','к','л','м','н','о','п','р','с','т','у','ф','х','ц','ч','ш','щ','ъ','ы','ь','э','ю','я',' '
    //   0   1   2   3   4   5   6   7   8   9  10  11  12  13  14  15  16  17  18  19  20  21  22  23  24  25  26  27  28  29  30  31  32  33

    public Vigenere (String input, String key) {
        this.key = key.toCharArray();

        String[] strings = input.split("(?<=\\G.{" + key.length() + "})");
        doubleCharacterArray = new char[strings.length][strings[0].length()];

        int index = 0;
        for (String substring: strings) {
            doubleCharacterArray[index] = substring.toCharArray();
            index ++;
        }
    }

    public String encode () {
        StringBuilder stringBuilder = new StringBuilder();
        for (char[] row : doubleCharacterArray) {
            for (int col = 0; col < row.length; col++) {
                for (int indexRussianChar = 0; indexRussianChar < rus.length; indexRussianChar++) {
                    if (Character.toLowerCase(row[col]) == rus[indexRussianChar]) {
                        stringBuilder.append(findEncodedCharacter(col, indexRussianChar, rus));
                        break;
                    }
                }
                for (int indexEnglishChar = 0; indexEnglishChar < eng.length; indexEnglishChar++) {
                    if (Character.toLowerCase(row[col]) == eng[indexEnglishChar]) {
                        stringBuilder.append(findEncodedCharacter(col, indexEnglishChar, eng));
                        break;
                    }
                }
            }
        }
        return stringBuilder.toString();
    }

    private char findEncodedCharacter (int characterIndex, int characterIndexInAlphabet, char[] alphabet) {
        for (int index = 0; index < key.length; index ++) {
            if (index == characterIndex) {
                for (int indexKeyInAlphabet = 0; indexKeyInAlphabet < alphabet.length; indexKeyInAlphabet ++) {
                    if (Character.toLowerCase(key[index]) == alphabet[indexKeyInAlphabet]) {
                        for (int plus = 0; plus < indexKeyInAlphabet; plus ++) {
                            characterIndexInAlphabet ++;
                            if (characterIndexInAlphabet >= alphabet.length) {
                                characterIndexInAlphabet = 0;
                            }
                        }
                        return alphabet[characterIndexInAlphabet];
                    }
                }
            }
        }
        return '*';
    }

    public String decode () {
        StringBuilder stringBuilder = new StringBuilder();
        for (char[] row : doubleCharacterArray) {
            for (int col = 0; col < row.length; col++) {
                for (int index = 0; index < rus.length; index++) {
                    if (Character.toLowerCase(row[col]) == rus[index]) {
                        stringBuilder.append(findDecodedCharacter(col, index, rus));
                        break;
                    }
                }
                for (int index = 0; index < eng.length; index++) {
                    if (Character.toLowerCase(row[col]) == eng[index]) {
                        stringBuilder.append(findDecodedCharacter(col, index, eng));
                        break;
                    }
                }
            }
        }
        return stringBuilder.toString();
    }

    private char findDecodedCharacter (int characterIndex, int characterIndexInAlphabet, char[] alphabet) {
        for (int index = 0; index < key.length; index ++) {
            if (index == characterIndex) {
                for (int indexKeyInAlphabet = 0; indexKeyInAlphabet < alphabet.length; indexKeyInAlphabet ++) {
                    if (Character.toLowerCase(key[index]) == alphabet[indexKeyInAlphabet]) {
                        for (int minus = 0; minus < indexKeyInAlphabet; minus ++) {
                            characterIndexInAlphabet--;
                            if (characterIndexInAlphabet < 0) {
                                characterIndexInAlphabet = alphabet.length - 1;
                            }
                        }
                        return alphabet[characterIndexInAlphabet];
                    }
                }
            }
        }
        return '*';
    }
}
