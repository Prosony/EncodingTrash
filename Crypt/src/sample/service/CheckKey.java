package sample.service;

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
        String typeABC = abc.checkABC(array[0]);

        if (typeABC.equalsIgnoreCase("NUM")){
            int[]  abcMatrix = {0,0,0,0,0,0,0,0,0,0};
            isKeyCorrect = judgeSymbol(key.toCharArray(), abc.getABC("NUM"), abcMatrix);
        }
        if (typeABC.equalsIgnoreCase("ENG")) {
            int[]  abcMatrix = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
            isKeyCorrect = judgeSymbol(key.toCharArray(), abc.getABC("ENG"), abcMatrix);
        }

        if (typeABC.equalsIgnoreCase("RUS")){
            int[]  abcMatrix = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
            isKeyCorrect = judgeSymbol(key.toCharArray(), abc.getABC("RUS"), abcMatrix);
        }

        if (typeABC.equalsIgnoreCase("THIRD")){
            int[]  abcMatrix = {0,0,0,0,0,0,0,0,0,0};

            if (key.length()%2 == 0){

                String[] keys = new String[1];
                char[] sourceKeys = key.toCharArray();
                int middle = sourceKeys.length/2;
                StringBuilder builderFirstKey = new StringBuilder();
                StringBuilder builderSecondKey = new StringBuilder();

                for (int index = 0; index < sourceKeys.length; index++){
                    if (index > middle){
                        builderSecondKey.append(sourceKeys[index]);
                    }else{
                        builderFirstKey.append(sourceKeys[index]);
                    }

                }
                keys[0] = builderFirstKey.toString();
                keys[1] = builderSecondKey.toString();
                for (String cursor : keys){
                    isKeyCorrect = judgeSymbol(cursor.toCharArray(), abc.getABC("NUM"), abcMatrix);
                }
//                isKeyCorrect = judgeSymbol(key.toCharArray(), abc.getABC("NUM"), abcMatrix);
            }else {
                isKeyCorrect = false;
            }

        }

        return isKeyCorrect;
    }

    public boolean checkKey(String[] keys){
        for (String key : keys){
            checkThird(key);
        }
    }

    public boolean checkThird(){
        ABC abc = new ABC();
        boolean isKeyCorrect = false;
        int[]  abcMatrix = {0,0,0,0,0,0,0,0,0,0};

        isKeyCorrect = judgeSymbol(key.toCharArray(), abc.getABC("RUS"), abcMatrix);
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
        for (int index = 0; index < array.length; index++) {
            for (int cursor = 0; cursor < ABC.length; cursor++) {
                if (array[index] == ABC[cursor]) {
                    matric[cursor] = matric[cursor] + 1;
                 if (matric[cursor] > 1){
                     System.out.println("#INFO [CheckKey] [judgeSymbol] [ERROR] key have dublicate symbol!");
                     isKeyCorrect = false;
                 }else{
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

    private boolean judgeSymbolForThird(char[] array, char[] ABC, int[] matric){
        boolean isKeyCorrect = false;
        for (int index = 0; index < array.length; index++) {
            for (int cursor = 0; cursor < ABC.length; cursor++) {
                if (array[index] == ABC[cursor]) {
                    matric[cursor] = matric[cursor] + 1;
                    if (matric[cursor] > 2){
                        System.out.println("#INFO [CheckKey] [judgeSymbol] [ERROR] key have dublicate symbol!");
                        isKeyCorrect = false;
                    }else{
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
}
