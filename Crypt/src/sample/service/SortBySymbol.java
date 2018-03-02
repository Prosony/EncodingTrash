package sample.service;

public class SortBySymbol {
    public char[][] sort(char[][] array, char[] key, int height, int width){
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
}
