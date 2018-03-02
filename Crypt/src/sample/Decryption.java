package sample;

import sample.service.QuickSort;
import sample.service.SortBySymbol;

public class Decryption {

    public String decryptionFirst(int key, String source){
        int count;
        if (source.length() % key > 0){
            count = source.length() / key + 1;
        }else{
            count = source.length() / key;
        }
        char[][] array = new char[key][count];
        char[] sourceArray = source.toCharArray();
        int index_of_char = 0;

        System.out.println("#____START_DECRYPTION___#");
        StringBuilder builder = new StringBuilder();
        for (int i_count=0; i_count < count; i_count++){
            for (int i_key=0; i_key < key; i_key++){
                if (index_of_char < source.length()){
                    array[i_key][i_count] = sourceArray[index_of_char];
                    index_of_char++;
                }else{
                    System.out.println("#ZERO#ZERO#");
                }
                System.out.print(array[i_key][i_count]);
            }
        }
        for (int i_key = 0; i_key < key; i_key++) {
            for (int i_count=0; i_count < count; i_count++) {
                builder.append(array[i_key][i_count]);
            }
        }
        return builder.toString();
    }

    public String decryptionSecond(String key, String source){

        int width = key.length();
        int height;
        QuickSort quickSort = new QuickSort();
        char[] resultKey = quickSort.sortMassive(key);

        if((source.length()+width)%width > 0){
            height = (source.length()+width)/width + 1;
        }else{
            height = (source.length()+width)/width;
        }

        char[][] array = new char[height][width];
        char[] sourceArray = source.toCharArray();
        int indexSource = 0;
        int sourceWidth = sourceArray.length;
        for (int indexWidth = 0; indexWidth < width; indexWidth++){
            array[0][indexWidth] = resultKey[indexWidth];
        }

        for (int indexHeight = 1; indexHeight < height; indexHeight++){
            for (int indexWidth = 0; indexWidth < width; indexWidth++){
                if(indexSource < sourceWidth){
                    array[indexHeight][indexWidth] = sourceArray[indexSource];
                    indexSource++;
                }
            }
        }

        SortBySymbol bySymbol = new SortBySymbol();
        char[][] result =  bySymbol.sort(array, key.toCharArray(), height, width);

        StringBuilder builder = new StringBuilder();
        for (int indexHeight = 1; indexHeight < height; indexHeight++){
            for (int indexWidth = 0; indexWidth < width; indexWidth++) {
                builder.append(result[indexHeight][indexWidth]);
            }
        }
        return builder.toString();
    }

}
