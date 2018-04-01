package methods;

import services.EncryptionService;
import services.sorting.QuickSorting;
import services.sorting.SortingBySymbol;


public class VerticalReplacement {

    // Описание:             Ключом указывается количество столбцов матрицы и их порядок,
    //                       количество строк высчитывается.
    //
    //                       При шифровании строка записывается в матрицу
    //                       слева направо, сверзу вниз, переставляются столбцы,
    //                       согласно порядку сортированных символов ключа в алфавите и строка
    //                       выписывается также (слева направо, сверху вниз).
    //
    //                       При дешифровании строка записывается в матрицу
    //                       слева направо, сверху вниз, переставляются столбцы
    //                       согласно порядку несортированных символов ключа в алфавите и строка
    //                       выписывается также (слева направл, сверху вниз).
    //
    // Ключ:                 Строка неповторяющихся символов одного алфавита -
    //                       количество строк в матрице и порядок перестановки столбцов матрицы.
    //
    // Алгоритм шифрования:  Символы текста записываются в матрицу слева направо, сверху вниз,
    //                       переставляются столбцы, согласно порядку сортированных символов
    //                       ключа в алфавите и строка выписывается также (слева направо, сверху вниз).
    //
    // Запись:               [д][г][в][б][а]
    //                       [Я][ ][п][р][о]
    //                       [г][р][а][м][м]
    //                       [и][с][т][*][*]
    //
    // Результат:            [а][б][в][г][д]
    //                       [о][р][п][ ][Я]
    //                       [м][м][а][р][г]
    //                       [*][*][т][с][и]
    //
    //                       орп Яммарг**тси
    //
    // Алгорим дешифрования: Символы текста записываются в матрицу слева направо, сверху вниз
    //                       переставляются столбцы согласно порядку несортированных символов
    //                       ключа в алфавите и строка выписывается также (слева направо, сверху вниз).
    //
    // Запись:               [а][б][в][г][д]
    //                       [о][р][п][ ][Я]
    //                       [м][м][а][р][г]
    //                       [*][*][т][с][и]
    //
    // Результат:            [д][г][в][б][а]
    //                       [Я][ ][п][р][о]
    //                       [г][р][а][м][м]
    //                       [и][с][т][*][*]
    //
    //                       Я программист

    public String encode (String source, String key) {
        char[] keyArray = key.toCharArray();
        int width = keyArray.length;

        EncryptionService service = new EncryptionService();
        int height = service.getHeightSecond(width,source);

        char[][] array = new char[height][width];
        char[] sourceArray = source.toCharArray();
        int sourceWidth = sourceArray.length;

        for (int index = 0; index < width; index++){
            array[0][index] = keyArray[index];
        }

        int indexSource = 0;
        for (int indexHeight = 1; indexHeight < height; indexHeight++){
            for (int indexWidth = 0; indexWidth < width; indexWidth++){
                if(indexSource < sourceWidth){
                    array[indexHeight][indexWidth] = sourceArray[indexSource];
                    indexSource++;
                }else{
                    array[indexHeight][indexWidth] = '*';
                }
            }
        }
        QuickSorting sort = new QuickSorting();
        char[] result = sort.sortMassive(keyArray);

        SortingBySymbol bySymbol = new SortingBySymbol();
        bySymbol.sortingSecond(array, result, height, width);

        return service.convertArray(array, height, width, 2);
    }

    public String decode(String source, String key){

        int width = key.length();
        int height;
        QuickSorting quickSort = new QuickSorting();
        char[] resultKey = quickSort.sortMassive(key.toCharArray());

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

        SortingBySymbol bySymbol = new SortingBySymbol();
        char[][] result =  bySymbol.sortingSecond(array, key.toCharArray(), height, width);

        StringBuilder builder = new StringBuilder();
        for (int indexHeight = 1; indexHeight < height; indexHeight++){
            for (int indexWidth = 0; indexWidth < width; indexWidth++) {
                builder.append(result[indexHeight][indexWidth]);
            }
        }
        return builder.toString().replace("*", "");

    }
}
