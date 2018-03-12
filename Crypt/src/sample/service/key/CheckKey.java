package sample.service.key;

import sample.service.ABC;

public class CheckKey {

    public boolean checkFirst(String key){
        char [] number = {'0','1','2','3','4','5','6','7','8','9'};
        boolean isKeyCorrect;
        isKeyCorrect = judge(key.toCharArray(), number);
        return isKeyCorrect;
    }

    public boolean checkKey(String key){
        char[] array = key.toCharArray();
        boolean isKeyCorrect = false;
        ABC abc = new ABC();
        String type = abc.checkABC(array[0]);
        if (type.equalsIgnoreCase("NUM")){
            int[]  abcMatrix = {0,0,0,0,0,0,0,0,0,0};
            isKeyCorrect = judgeSymbol(key.toCharArray(), abc.getABC("NUM"), abcMatrix);
        }
        if (type.equalsIgnoreCase("ENG")) {
            int[]  abcMatrix = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
            isKeyCorrect = judgeSymbol(key.toCharArray(), abc.getABC("ENG"), abcMatrix);
        }

        if (type.equalsIgnoreCase("RUS")){
            int[]  abcMatrix = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
            isKeyCorrect = judgeSymbol(key.toCharArray(), abc.getABC("RUS"), abcMatrix);
        }
        return isKeyCorrect;
    }
    /*************************************
     *              Service
     *************************************/
    private boolean judge(char[] array, char[] ABC){
        boolean isKeyCorrect = false;
        for (char anArray : array) {
            for (char aRUS : ABC) {
                if (anArray == aRUS) {
                    isKeyCorrect = true;
                    break;
                } else {
                    isKeyCorrect = false;
                }
            }
            if (!isKeyCorrect) {
                break;
            }
        }
        return isKeyCorrect;
    }

    private boolean judgeSymbol(char[] array, char[] ABC, int[] matric){
        boolean isKeyCorrect = false;
        for (char anArray : array) {
            for (int cursor = 0; cursor < ABC.length; cursor++) {
                if (anArray == ABC[cursor]) {
                    matric[cursor] = matric[cursor] + 1;
                    if (matric[cursor] > 1) {
                        System.out.println("#INFO [CheckKey] [judgeSymbol] [ERROR] key have dublicate symbol!");
                        isKeyCorrect = false;
                    } else {
                        isKeyCorrect = true;
                    }
                    break;
                } else {
                    isKeyCorrect = false;
                }
            }
            if (!isKeyCorrect) {
                break;
            }
        }
        return isKeyCorrect;
    }
    public boolean checkDoubleKey(String keys) {
        boolean isKeyCorrect = false;
        int[]  abcMatrix = {0,0,0,0,0,0,0,0,0,0};
        ABC abc = new ABC();
        char[] source = keys.toCharArray();
        char[] number =  abc.getABC("NUM");
        for (char aSource : source) {
            for (int cursor = 0; cursor < number.length; cursor++) {
                if (aSource == ',') {
                    for (int element = 0; element < abcMatrix.length; element++) {
                        abcMatrix[element] = 0;
                    }
                } else {
                    if (aSource == number[cursor]) {
                        abcMatrix[cursor] = abcMatrix[cursor] + 1;
                        if (abcMatrix[cursor] > 1) {
                            System.out.println("#INFO [CheckKey] [judgeSymbol] [ERROR] key have duplicate symbol!");
                            isKeyCorrect = false;
                        } else {
                            isKeyCorrect = true;
                        }
                        break;
                    } else {
                        isKeyCorrect = false;
                    }
                }
            }
            if (!isKeyCorrect) {
                break;
            }
        }
      return isKeyCorrect;
    }
}