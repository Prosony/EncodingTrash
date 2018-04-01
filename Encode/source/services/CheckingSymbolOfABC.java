package services;

import static java.lang.Character.toLowerCase;

public class CheckingSymbolOfABC {

    private char[] abcENG = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
    private char[] abcRUS = {'а','б','в','г','д','е','ё','ж','з','и','й','к','л','м','н','о','п','р','с','т','у','ф','х','ц','ч','ш','щ','ъ','ы','ь','э','ю','я'};
    private char [] abcNUM = {'0','1','2','3','4','5','6','7','8','9'};

    private char[][] encryptionSquareTable = {
            {'9','Й','Ф','Я','Ц','1','Ы','@','$','?',')','+','<','Ч','У','0','В','э','щ','л'},
            {'т','е','а','ч','й','С','8','К','А','5', 'М','Е','2','П','И','Н','з','д','ь','#'},
            {'%','&','-','/','>','н','п','с','ц','ф','Р','Т','!','№','^','*','_',' ','`','Г'},
            {'О','6','Ь','Ш','3','Л','Б','ъ','ж','б','г','р','м','у','ы','Щ','Д','Ю','7','З'},
            {'Ж','Х','Э','4','Ъ','х','ю','\"',';',':','(','=','\\','~','ш','о','и','к','в','я'}
    };
    char[][] leftTable = {
            {'ж','щ','н','ю','р'},
            {'и','т','ь','ц','б'},
            {'я','м','е','.','с'},
            {'в','ы','п','ч',' '},
            {':','д','у','о','к'},
            {'з','э','ф','г','ш'},
            {'х','а',',','л','ъ'}
    };
    char[][] rightTable = {
            {'и','ч','г','я','т'},
            {',','ж','ь','м','о'},
            {'з','ю','р','в','щ'},
            {'ц',':','п','е','л'},
            {'ъ','а','н','.','х'},
            {'э','к','с','ш','д'},
            {'б','ф','у','ы',' '}
    };
    public int getNumberABC(char symbol){
        int indexEquals = -1;
        String abcKind = checkABC(symbol);
        if (abcKind != null){

            switch (abcKind){
                case "rus":
                    for (int index = 0; index < abcRUS.length; index++){
                        if (toLowerCase(symbol) == toLowerCase(abcRUS[index])){
                            indexEquals = index;
                            break;
                        }
                    }
                    if (indexEquals != -1){
                        return indexEquals;
                    }
                    break;
                case "eng":
                    for (int index = 0; index < abcENG.length; index++){
                        if (toLowerCase(symbol) == toLowerCase(abcENG[index])){
                            indexEquals = index;
                            break;
                        }
                    }
                    if (indexEquals != -1){
                        return indexEquals;
                    }
                    break;
                case "num":
                    for (int index = 0; index < abcNUM.length; index++){
                        if (toLowerCase(symbol) == toLowerCase(abcNUM[index])){
                            indexEquals = index;
                            break;
                        }
                    }
                    if (indexEquals != -1){
                        return indexEquals;
                    }
                    break;
            }
        }else {
            System.out.println("#INFO [ABC] [getNumberABC] [ERROR] abc not found! symbol: "+symbol);
        }
        return -1;
    }
    public String checkABC(char symbol){
        for (char anAbcENG : abcENG) {
            if (toLowerCase(symbol) == toLowerCase(anAbcENG)) {
                return "eng";
            }
        }
        for (char abcRU : abcRUS) {
            if (toLowerCase(symbol) == toLowerCase(abcRU)) {
                return "rus";
            }
        }
        for (char number : abcNUM){
            if (toLowerCase(symbol) == toLowerCase(number)){
                return "num";
            }
        }

        return null;
    }
    public char[] getABC(String type){

        switch (type){
            case "ENG": return abcENG;
            case "RUS": return abcRUS;
            case "NUM": return abcNUM;
        }
        return null;
    }
    public char[] getAbcENG(){
        return abcENG;
    }
    public char[] getAbcRUS(){
        return abcRUS;
    }
    public char[][] getEncryptionSquareTable(){
        return encryptionSquareTable;
    }
    public char[][] getLeftTable(){
        return leftTable;
    }
    public char[][] getRightTable() {
        return rightTable;
    }
}

