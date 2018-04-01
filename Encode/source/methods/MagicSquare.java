package methods;

public class MagicSquare {

    // Описание:             Ключом указывается размер матрицы и значения
    //                       в ячейках матрицы.
    //
    //                       При шифровании строка записывается в матрицу
    //                       согласно значения в ячейках матрицы, начиная
    //                       от 1 и до последнего значения. Выписывается
    //                       строка слева направо, сверзу низ.
    //
    //                       При дешифровании строка записывается в матрицу
    //                       сверху вниз, слева направо, и выписывается согласно
    //                       порядку значений в ключе.
    //
    // Ключ:                 [размер матрицы][значения ячеек в матрице
    //                       [3,3][2,7,6,9,5,1,4,3,8]
    //
    // Алгоритм шифрования:  Символы строки записывается в матрицу
    //                       согласно значениям в ячейках матрицы, начиная
    //                       от 1 и до последнего значения. Выписывается
    //                       строка слева направо, сверзу низ.
    //
    // Матрица:              [2][7][6]
    //                       [9][5][1]
    //                       [4][3][8]
    //
    // Строка:               Я программист
    //
    // Запись:               [ ][р][г]  [и][*][*]
    //                       [м][о][Я]  [*][*][м]
    //                       [р][п][а]  [т][с][*]
    //
    // Результат:             ргмоЯрпаи****мтс*
    //
    // Алгорим дешифрования: Символы строки записываются в матрицу
    //                       сверху вниз, слева направо, и выписывается согласно
    //                       порядку значений в ключе.
    //
    // Матрица:              [2][7][6]
    //                       [9][5][1]
    //                       [4][3][8]
    //
    // Запись:               [ ][р][г]  [и][*][*]
    //                       [м][о][Я]  [*][*][м]
    //                       [р][п][а]  [т][с][*]
    //
    // Результат:            Я программист

    private int[] size;
    private int[][] matrix;
    private String[] array;
    private String[] strings;

    public MagicSquare (String string, String key) {
        array = key.split("]");

        int i = 0;
        size = new int[2];
        for (char character: array[0].toCharArray()) {
            if (String.valueOf(character).matches("[0-9]")) {
                size[i] = Integer.parseInt(String.valueOf(character));
                i++;
            }
        }

        int row = 0;
        int col = 0;
        matrix = new int[size[0]][size[1]];
        for (char character: array[1].toCharArray()) {
            if (String.valueOf(character).matches("[0-9]")) {
                matrix[row][col] = Integer.parseInt(String.valueOf(character));
                if (col == size[1] - 1) {
                    col = 0;
                    row ++;
                }
                else col ++;
            }
        }

        strings = string.split("(?<=\\G.{" + size[0] * size[1] + "})");
    }
    public String encode () {
        StringBuilder stringBuilder = new StringBuilder();

        for (String substring: strings) {
            for (int[] row : matrix) {
                for (int col : row) {
                    try {
                        stringBuilder.append(substring.charAt(col - 1));
                    } catch (StringIndexOutOfBoundsException ignored) {
                        stringBuilder.append("*");
                    }
                }
            }
        }

        return stringBuilder.toString();
    }

    public String decode () {
        StringBuilder stringBuilder = new StringBuilder();

        int index = 0;
        int [] matrix1d = new int[size[0] * size[1]];
        for (char character: array[1].toCharArray()) {
            if (String.valueOf(character).matches("[0-9]")) {
                matrix1d[index] = Integer.parseInt(String.valueOf(character));
                index ++;
            }
        }

        for (String substring: strings) {
            for (int count = 1; count < matrix1d.length + 1; count ++) {
                for (index = 0; index < matrix1d.length; index ++) {
                    if (count == matrix1d[index]) {
                        stringBuilder.append(substring.charAt(index));
                    }
                }
            }
        }

        return stringBuilder.toString().replace("*", "");
    }
}
