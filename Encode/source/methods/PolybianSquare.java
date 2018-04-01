package methods;

import services.CheckingSymbolOfABC;

public class PolybianSquare {
    /**
     Ищет символ в шифрующей таблице и берет букву на 1 срочку ниже.
     encryption table:
         9 Й Ф Я Ц 1 Ы @ $ ? ) + < Ч У 0 В э щ л
         т е а ч й С 8 К А 5 М Е 2 П И Н з д ь #
         % & - / > н п с ц ф Р Т ! № ^ * _   ` Г
         О 6 Ь Ш 3 Л Б ъ ж б г р м у ы Щ Д Ю 7 З
         Ж Х Э 4 Ъ х ю " ; : ( = \ ~ ш о и к в я

     sours text:
        просто
     result:
        Б=0ъ%0
     */
    // TODO - добавить английский в таблицу
    private char[][] encryptionSquareTable = new CheckingSymbolOfABC().getEncryptionSquareTable();
    private int heightMax = encryptionSquareTable.length;
    private int widthMax = encryptionSquareTable[0].length;

    public String encoder(char[] source, int action){
        int lenght = source.length;
        int index = 0;
        char symbol;
        StringBuilder result = new StringBuilder();
        switch (action){
            case 0:
                while(index < lenght){
                    symbol = searchEncoderSymbol(source[index]);
                    if (symbol == source[index]){
                        return null;
                    }
                    result.append(symbol);
                    index++;
                }
                break;
            case 1:
                while(index < lenght){
                    symbol = searchDecoderSymbol(source[index]);
                    if (symbol == source[index]){
                        return null;
                    }
                    result.append(symbol);
                    index++;
                }
                break;
        }

        System.out.println(result.toString());
        return result.toString();
    }
    private char searchEncoderSymbol(char symbol){
        for (int height = 0; height < heightMax; height++){
            for (int width = 0; width < widthMax; width++){
                if (symbol == encryptionSquareTable[height][width]){
                      if (height >= heightMax-1){
                          return encryptionSquareTable[0][width];
                      }else{
                          return encryptionSquareTable[height+1][width];
                      }
                }
            }
        }
        return symbol;
    }
    private char searchDecoderSymbol(char symbol){
        for (int height = 0; height < heightMax; height++){
            for (int width = 0; width < widthMax; width++){
                if (symbol == encryptionSquareTable[height][width]){
                    if (height == 0){
                        return encryptionSquareTable[heightMax-1][width];
                    }else{
                        return encryptionSquareTable[height-1][width];

                    }
                }
            }
        }
        return symbol;
    }
}
