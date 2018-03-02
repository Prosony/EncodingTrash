package sample.service;

public class CheckKey {

    public boolean checkFirst(String key){
        char [] number = {'0','1','2','3','4','5','6','7','8','9'};
        boolean isKeyCorrect;
        isKeyCorrect = judge(key.toCharArray(), number);
        return isKeyCorrect;
    }
    public boolean checkSecond(String key){

        char[] array = key.toCharArray();
        boolean isKeyCorrect = false;
        ABC abc = new ABC();
        String typeABC = abc.checkABC(array[0]);

        if (typeABC.equalsIgnoreCase("ENG")) {
            char[] abcENG = {'a','b','c','d','e','f','g','h','i','j','k','l','m','o','p','q','r','s','t','u','w','x','y','z'};
            isKeyCorrect = judge(key.toCharArray(), abcENG);
        }

        if (typeABC.equalsIgnoreCase("RUS")){
            char[] abcRUS = {'а','б','в','г','д','е','ё','ж','з','и','й','к','л','м','н','о','п','р','с','т','у','ф','ч','ц','ч','щ','ъ','ы','ь','э','ю','я'};
            isKeyCorrect = judge(key.toCharArray(), abcRUS);
        }
        return isKeyCorrect;
    }

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
}
