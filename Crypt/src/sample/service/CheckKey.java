package sample.service;

public class CheckKey {
    private char [] number = {'0','1','2','3','4','5','6','7','8','9'};
    private char[] abcENG = {'a','b','c','d','e','f','g','h','i','j','k','l','m','o','p','q','r','s','t','u','w','x','y','z'};
    private char[] abcRUS = {'а','б','в','г','д','е','ё','ж','з','и','й','к','л','м','н','о','п','р','с','т','у','ф','ч','ц','ч','щ','ъ','ы','ь','э','ю','я'};

    public boolean checkFirst(String key){

        char[] array = key.toCharArray();
        boolean isKeyCorrect = false;
        for (char anArray : array) {
            for (char aNumber : number) {
                if (anArray == aNumber) {
                    isKeyCorrect = true;
                    break;
                }else{
                    isKeyCorrect = false;
                }
            }
            if (!isKeyCorrect){
                break;
            }
        }
        return isKeyCorrect;
    }
    public boolean checkSecond(String key){

        char[] array = key.toCharArray();

        boolean isKeyCorrect = false;
        ABC abc = new ABC();
        String typeABC = abc.checkABC(array[0]);

        if (typeABC.equalsIgnoreCase("ENG")) {
            for (char anArray : array) {
                for (char anAbcENG : abcENG) {
                    if (anArray == anAbcENG) {
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
        }

        if (typeABC.equalsIgnoreCase("RUS")){
            for (char anArray : array) {
                for (char aENG : abcRUS) {
                    if (anArray == aENG) {
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
        }
        return isKeyCorrect;
    }
}