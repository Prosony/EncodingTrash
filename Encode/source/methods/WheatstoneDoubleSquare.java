package methods;

import services.CheckingSymbolOfABC;

public class WheatstoneDoubleSquare {
    /*
LEFT                RIGHT
[ж][щ][н][ю][р]     [и][ч][г][я][т]
[и][т][ь][ц][б]     [,][ж][ь][м][о]
[я][м][е][.][с]     [з][ю][р][в][щ]
[в][ы][п][ч][ ]     [ц][:][п][е][л]
[:][д][у][о][к]     [ъ][а][н][.][х]
[з][э][ф][г][ш]     [э][к][с][ш][д]
[х][а][,][л][ъ]     [б][ф][у][ы][ ]

    Текст делится на биограммы - группу из 2 символов.
    пр ос то
    1 символ биограммы ищется в левой таблице. 2 ищется в правой.
    1 символ шифруется символом полученному углом в правой таблице
    2 символ шифруется символом полученному углом в левой таблице
    source:
        пр ос то
    result:
        пе нг жб
     */
    private char[][] leftTable = new CheckingSymbolOfABC().getLeftTable();
    private char[][] rightTable = new CheckingSymbolOfABC().getRightTable();
    private void printTable(){
        System.out.println("LEFT:");
        for (int height = 0; height < leftTable.length; height++){
            for(int width = 0; width < leftTable[0].length; width++){
                System.out.print("["+leftTable[height][width]+"]");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println("RIGHT:");
        for (int height = 0; height < rightTable.length; height++){
            for(int width = 0; width < rightTable[0].length; width++){
                System.out.print("["+rightTable[height][width]+"]");
            }
            System.out.println();
        }
    }
    public String encode(char[] source, int action){
        printTable();
        int lenght = source.length;
        int lenghtBiogramm;
        String[] biogramm;
        if (lenght%2 == 0){
            lenghtBiogramm = lenght/2;
            biogramm = new String[lenghtBiogramm];
        }else{
            lenghtBiogramm = (lenght/2)+1;
            biogramm = new String[lenghtBiogramm];
        }
        int indexBiogramm = 0;
        for (int index = 0; index < lenght; index++){

            if (index+1 < lenght){
                System.out.println("index: "+index);
                biogramm[indexBiogramm] = (String.valueOf(source[index]) + String.valueOf(source[index+1]));
                System.out.println(biogramm[indexBiogramm]);
            }else{
                biogramm[indexBiogramm] = (String.valueOf(source[index]) + " ");
                System.out.println(biogramm[indexBiogramm]);
            }
            indexBiogramm++;
            index++;
        }
        StringBuilder builder = new StringBuilder();
        for (int index = 0; index < lenghtBiogramm; index++){
            int[] left = new int[0];
            int[] right = new int[0];
            switch(action){
                case 0:
                    left= searchSymbol(biogramm[index].charAt(0), "left");
                    right = searchSymbol(biogramm[index].charAt(1), "right");
                    System.out.println("char: ["+biogramm[index].charAt(0)+"] left: ["+left[0]+","+left[1]+"]");
                    System.out.println("char: ["+biogramm[index].charAt(1)+"] right: ["+right[0]+","+right[1]+"]");
                    System.out.println();
                    if (left[0] != right[0]){

                        builder.append(getEncryptionSymbol(left[0],right[1],"right"));//first symbol in biogramm
                        builder.append(getEncryptionSymbol(right[0],left[1],"left"));//second symbol in biogramm
                    }else{
                        builder.append(getEncryptionSymbol(left[0],left[1],"right"));//first symbol in biogramm
                        builder.append(getEncryptionSymbol(right[0],right[1],"left"));//second symbol in biogramm
                    }
                    break;
                case 1:
                    left = searchSymbol(biogramm[index].charAt(0), "right");
                    right = searchSymbol(biogramm[index].charAt(1), "left");
                    System.out.println("char: ["+biogramm[index].charAt(0)+"] right: ["+left[0]+","+left[1]+"]");
                    System.out.println("char: ["+biogramm[index].charAt(1)+"] left: ["+right[0]+","+right[1]+"]");
                    System.out.println();
                    if (left[0] != right[0]){
                        builder.append(getEncryptionSymbol(left[0],right[1],"left"));//first symbol in biogramm
                        builder.append(getEncryptionSymbol(right[0],left[1],"right"));//second symbol in biogramm
                    }else{
                        builder.append(getEncryptionSymbol(left[0],left[1],"left"));//first symbol in biogramm
                        builder.append(getEncryptionSymbol(right[0],right[1],"right"));//second symbol in biogramm
                    }
                    break;
            }
            if (left.length > 0 && right.length > 0){

            }else{
                System.out.println("#INFO [WheatstoneDoubleSquare][encode] something wrong!");
            }

        }
        System.out.println("result: "+builder.toString());
        return builder.toString();
    }

    private int[] searchSymbol(char symbol, String where){
        int[] location = new int[2];
        int height;
        int width;
        switch(where){
            case "left":
                height = leftTable.length;
                width = leftTable[0].length;
                for(int indexH = 0; indexH < height; indexH++){
                    for (int indexW = 0; indexW < width; indexW++){
                        if (symbol == leftTable[indexH][indexW]){
                            location[0] = indexH;
                            location[1] = indexW;
                            return location;
                        }
                    }
                }
                break;
            case "right":
                height = rightTable.length;
                width = rightTable[0].length;
                for(int indexH = 0; indexH < height; indexH++){
                    for (int indexW = 0; indexW < width; indexW++){
                        if (symbol == rightTable[indexH][indexW]){
                            location[0] = indexH;
                            location[1] = indexW;
                            return location;
                        }
                    }
                }
                break;
        }
        return new int[0];
    }
    private char getEncryptionSymbol(int height, int width, String where){
        switch (where){
            case "left":
                return leftTable[height][width];
            case "right":
                return  rightTable[height][width];
        }
        return 0;
    }
}
