package services.sorting;

import java.util.Arrays;

public class SortingBySymbol {

    public char[][] sortingSecond(char[][] array, char[] key, int height, int width){
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

    public String sortingThird (char[] keyHeight, char[] keyWidth, char[][] source, int action) {
        char[][] begin;
        char[][] result;
        System.out.println("keyHeight: "+ Arrays.toString(keyHeight) + " keyWidth: "+Arrays.toString(keyWidth));
        StringBuilder builder = new StringBuilder();

        switch(action){
            case 0:
                begin = reshuffleWidth(keyWidth, source);
                printArray("-------WIDTH-------",begin);
                result =  reshuffleHeight(keyHeight, begin);
                printArray("-------HEIGHT-------",result);

                for (char row = 1; row < result.length; row ++) {
                    for (char col = 1; col < result[row].length; col ++) {
                        builder.append(result[row][col]);
                    }
                }
                return builder.toString();
            case 1:
                begin = reshuffleHeight(keyHeight, source);
                printArray("-------WIDTH-------",begin);
                result = reshuffleWidth(keyWidth, begin);
                printArray("-------HEIGHT-------",result);
                for (char row = 1; row < result.length; row ++) {
                    for (char col = 1; col < result[row].length; col ++) {
                        builder.append(result[row][col]);
                    }
                }
                return builder.toString();
        }

        return null;
    }

    /***************************************************
     *                SERVICE FOR SORTING              *
     **************************************************/
    private char[][] reshuffleHeight (char[] key, char[][] source) {
        System.out.println("source.length: "+source.length);
        System.out.println("source[0].length: "+source[0].length);
        for (int col = 1; col < source.length; col ++) {
            if (col < key.length) {
                if (source[col][0] != key[col - 1]) {
                    for (int row = 1; row < source.length; row++) {
                        if (source[row][0] == key[col - 1]) {
                            for (int i = 0; i < source[0].length; i++) {
                                char temp = source[col][i];
                                source[col][i] = source[row][i];
                                source[row][i] = temp;
                            }
                        }
                    }
                }
            }
        }
        return source;
    }

    private char[][] reshuffleWidth (char[] key, char[][] source) {
        for (int col = 1; col < source[0].length; col ++) {
            if (col < key.length) {
                if (source[0][col] != key[col - 1]) {
                    for (int index = 1; index < source[0].length; index ++) {
                        if (source[0][index] == key[col-1]) {
                            for (int i = 0; i < source.length; i ++) {
                                char temp = source[i][col];
                                source[i][col] = source[i][index];
                                source[i][index] = temp;
                            }
                        }
                    }
                }
            }
        }
        return source;
    }
    private void printArray(String str,char[][] result){
        System.out.println(str);
        for (char row = 0; row < result.length; row ++) {
            for (char col = 0; col < result[row].length; col ++) {
                System.out.print("[" + result[row][col] + "]");
            }
            System.out.println();
        }
    }
}