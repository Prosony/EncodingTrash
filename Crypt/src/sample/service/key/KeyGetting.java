package sample.service.key;

public class KeyGetting {

    public String[] getDoubleKeyFromString(String source){

        String[] keys = new String[2];
        char[] sourceKeys = source.toCharArray();

        StringBuilder builderFirstKey = new StringBuilder();
        StringBuilder builderSecondKey = new StringBuilder();
        boolean first = false;
        boolean second = false;

        for (int index = 0; index < sourceKeys.length; index++){
            if (sourceKeys[index] ==','){
                first = true;
            }else {
                if (!first){
                    builderFirstKey.append(sourceKeys[index]);
                }else{
                    builderSecondKey.append(sourceKeys[index]);
                }
            }
        }
        keys[0] = builderFirstKey.toString();
        keys[1] = builderSecondKey.toString();
        return keys;
    }
}
