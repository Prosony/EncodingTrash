package sample.service.sorting;

public class SortBySymbol {

    public char[][] sortSecond(char[][] array, char[] key, int height, int width){
        int index = 0;
        while(index < width){
            if (array[0][index] != key[index]){
                for (int local = 0; local < width; local++){
                    if (key[index] == array[0][local]){
                        for (int localHeight = 0; localHeight < height; localHeight++){
                            char temp = array[localHeight][index];
                            array[localHeight][index] = array[localHeight][local];
                            array[localHeight][local] = temp;
                        }
                    }
                }
            }
            index++;
        }
    return array;
    }
    public char[][] sortThird(char[] keyHeight, char[] keyWidth, char[][] source){
        int heightSize = keyHeight.length;
        int widthSize = keyWidth.length;
        int index = 0;

        while(index < widthSize){
            if (keyHeight[index] !=source[0][index] ){
                for (int local = 0; local < widthSize; local++){
                    if (keyHeight[index] == source[0][local]){
                        char temp = source[0][local];
                        source[0][index] = source[0][local];
                        source[0][local] = temp;
                        index++;
                        local = 0;

                        //TODO other elements
                    }
                }
            }
            index++;
        }

        for(int width = 0; width< widthSize; width++){
            if (source[0][width] == keyWidth[width]){

            }
        }

        return null;
    }
}
