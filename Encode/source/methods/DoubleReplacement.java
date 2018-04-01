package methods;

import services.sorting.QuickSorting;
import services.sorting.SortingBySymbol;

/**
 Source string: я работаю на стр
 Заполняе массив
     ||
     \/
 BEFORE:
    [0][2][3][4][1]
    [3][Я][ ][р][а]
    [1][б][о][т][а]
    [4][ю][ ][н][а]
    [2][ ][с][т][р]
        ||
        \/
 Sorting WIDTH:
     [0][1][2][3][4]
     [3][а][Я][ ][р]
     [1][а][б][о][т]
     [4][а][ю][ ][н]
     [2][р][ ][с][т]
 Sorting HEIGHT:
     [0][1][2][3][4]
     [1][а][б][о][т]
     [2][р][ ][с][т]
     [3][а][Я][ ][р]
     [4][а][ю][ ][н]
Result:
 |аботр стаЯ раю н|

 */
public class DoubleReplacement {

    public String thirdEncryption (String input, String key_1, String key_2, int action) {
        char[] inputCharArray = input.toCharArray();
        char[] key_1CharArrayWidth = key_1.toCharArray();
        char[] key_2CharArrayHeight = key_2.toCharArray();

        int countText = 0, count = 1;
        int width = key_1CharArrayWidth.length + 1;
        int height = key_2CharArrayHeight.length + 1;
        System.out.println("key_1: "+key_1+" key_2: "+key_2);
        if (input.length() > (key_1CharArrayWidth.length * key_2CharArrayHeight.length)){
            count = input.length() / (key_1CharArrayWidth.length * key_2CharArrayHeight.length) + 1;
        }

        StringBuilder result = new StringBuilder();
        QuickSorting sort = new QuickSorting();

        char[] doneKeyHeight = sort.sortMassive(key_2CharArrayHeight.clone());
        char[] doneKeyWidth = sort.sortMassive(key_1CharArrayWidth.clone());
        if (action != 0 && (input.length()%(key_1.length()*key_2.length()) != 0)){
            System.out.println("#INFO [DoubleReplacement][thirdEncryption][ERROR] invalid text!");
            return null;
        }
        if (doneKeyWidth !=null && doneKeyHeight != null) {
            for (int index = 0; index < count; index++) {
                int countKeyOne = 0, countKeyTwo = 0;
                char[][] array = new char[height][width];

                for (int cursorH = 0; cursorH < height; cursorH++) {
                    for (int cursorW = 0; cursorW < width; cursorW++) {
                        if (cursorH == 0 && cursorW == 0) array[cursorH][cursorW] = '0';
                        if (cursorH == 0 && cursorW != 0) {
                            if (action == 0) {
                                array[cursorH][cursorW] = key_1CharArrayWidth[countKeyOne];
                                countKeyOne++;
                            } else {
                                array[cursorH][cursorW] = doneKeyWidth[countKeyOne];
                                countKeyOne++;
                            }
                        }
                        if (cursorH != 0 && cursorW == 0) {
                            if (action == 0) {
                                if (countKeyTwo < key_2CharArrayHeight.length) {
                                    array[cursorH][cursorW] = key_2CharArrayHeight[countKeyTwo];
                                    countKeyTwo++;
                                }
                            } else {
                                if (countKeyTwo < doneKeyHeight.length) {
                                    array[cursorH][cursorW] = doneKeyHeight[countKeyTwo];
                                    countKeyTwo++;
                                }

                            }
                        }
                            if (cursorH != 0 && cursorW != 0) {
                                if (countText < inputCharArray.length) {
                                    array[cursorH][cursorW] = inputCharArray[countText];
                                    countText++;
                                } else {
                                    array[cursorH][cursorW] = '*';
                                }
                            }
                        }
                    }
                System.out.println("BEFORE:");
                for (int cursorH = 0; cursorH < height; cursorH++) {
                    for (int cursorW = 0; cursorW < width; cursorW++) {
                        System.out.print("["+array[cursorH][cursorW]+"]");
                    }
                    System.out.println();
                }
                System.out.println("_________________________________________");
                    SortingBySymbol bySymbol = new SortingBySymbol();
                    switch (action) {
                        case 0:
                            result.append(bySymbol.sortingThird(doneKeyHeight, doneKeyWidth, array, action));
                            break;
                        case 1:
                            result.append(bySymbol.sortingThird(key_2CharArrayHeight, key_1CharArrayWidth, array, action));
                            break;
                    }

                }
            }
            switch (action){
                case 0:
                    return result.toString();
                case 1:
                    return result.toString().replace("*", "");
            }
        return null;
    }

}
