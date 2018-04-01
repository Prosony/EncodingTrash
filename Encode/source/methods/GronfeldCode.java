package methods;


import services.CheckingSymbolOfABC;

public class GronfeldCode {
    /**
     Ищет символ в шифрующей таблице и сдвигает index соответственно элементу в ключе
     encryption table:
         9 Й Ф Я Ц 1 Ы @ $ ? ) + < Ч У 0 В э щ л
         т е а ч й С 8 К А 5 М Е 2 П И Н з д ь #
         % & - / > н п с ц ф Р Т ! № ^ * _   ` Г
         О 6 Ь Ш 3 Л Б ъ ж б г р м у ы Щ Д Ю 7 З
         Ж Х Э 4 Ъ х ю " ; : ( = \ ~ ш о и к в я
     key:
        234
     sours text:
         просто
     result:
         фЩ9Рй9
     */
    public String encode(char[] source, char[] key, int action){
        char[] encryptionLine = getEncryptionLine();

        int lengthKey = key.length;
        int lengthText = source.length;
        int index = 0;
        int indexKey = 0;

        StringBuilder builder = new StringBuilder();
        while(index < lengthText){
            if(action == 0){
                builder.append(searchElementEncode(encryptionLine,source[index],key[indexKey]));
            }else{
                builder.append(searchElementDecode(encryptionLine,source[index],key[indexKey]));
            }
            index++;
            if (index < lengthKey){
                indexKey = index;

            }else{
                int result = index;
                while(result >= index){
                    result = result - lengthKey;
                }
                indexKey= result;
            }
        }
        System.out.println(builder.toString());
        return builder.toString();
    }
    private char searchElementEncode(char[] encryptionLine, char element, char elementKey){
        int length = encryptionLine.length;
        char result = 0;
        for (int cursor = 0; cursor < length; cursor++){
            if (element == encryptionLine[cursor]){
                System.out.println("source[index]: "+element+" key: "+elementKey);
                if (cursor + Integer.parseInt(String.valueOf(elementKey)) + 1 >= length){
                    result = encryptionLine[length - (cursor + Integer.parseInt(String.valueOf(elementKey)) + 1)];
                }else{
                    result = encryptionLine[cursor + (Integer.parseInt(String.valueOf(elementKey)) + 1)];

                }
                break;
            }
        }
        return result;
    }
    private char searchElementDecode(char[] encryptionLine, char element, char elementKey){
        int length = encryptionLine.length;
        char result = 0;
        for (int cursor = 0; cursor < length; cursor++){
            if (element == encryptionLine[cursor]){
                System.out.println("source[index]: "+element+" cursor: "+cursor+" key: "+elementKey);
//                int asdasdas = (Integer.parseInt(String.valueOf(elementKey)) + 1);
//                System.out.println("shift: "+asdasdas);
                if (cursor - Integer.parseInt(String.valueOf(elementKey)) - 1 <= 0){
                    result = encryptionLine[(cursor - Integer.parseInt(String.valueOf(elementKey)) - 1 + length)];
                }else{
                    result = encryptionLine[(cursor  - Integer.parseInt(String.valueOf(elementKey)) - 1)];

                }
                break;
            }
        }
        return result;
    }
    private char[] getEncryptionLine(){
        char[][] encryptionSquareTable = new CheckingSymbolOfABC().getEncryptionSquareTable();
        int heightMax = encryptionSquareTable.length;
        int widthMax = encryptionSquareTable[0].length;

        StringBuilder builder = new StringBuilder();
        for (int height = 0; height < heightMax; height++) {
            for (int width = 0; width < widthMax; width++) {
                builder.append(encryptionSquareTable[height][width]);
            }
        }
        return builder.toString().toCharArray();
    }
}
