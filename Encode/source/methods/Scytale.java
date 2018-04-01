package methods;

public class Scytale {

    // Описание:             Ключом указывается количество строк в матрице,
    //                       количество столбцов высчитывается.
    //
    //                       При шифровании строка записывается в матрицу
    //                       слева направо, сверзу вниз, и выписывается сверху
    //                       вниз, слева направо.
    //
    //                       При дешифровании строка записывается в матрицу
    //                       сверху вниз, слева направо, и выписывается слева
    //                       направо, сверху вниз.
    //
    // Ключ:                 Число - количество строк в матрице
    //
    // Алгоритм шифрования:  Символы текста записываются в матрицу слева направо, сверху вниз
    //                       и выписываются сверху вниз, слева направо
    //
    // Запись:               [Я][ ][п][р][о]
    //                       [г][р][а][м][м]
    //                       [и][с][т][*][*]
    //
    // Результат:            Яги рспатрм*ом*
    //
    // Алгорим дешифрования: Символы текста записываются в матрицу сверху вниз, слева направо
    //                       и выписываются слева направо, сверху вниз
    //
    // Запись:               [Я][ ][п][р][о]
    //                       [г][р][а][м][м]
    //                       [и][с][т][*][*]
    //
    // Рехультат:            Я программист

    private char[] string;
    private char[][] matrix;

    public Scytale(String input, int key) {
        this.string = input.toCharArray();

        if (input.length() % key != 0) {
            matrix = new char[key][(int) Math.ceil((double) input.length() / key)];
        } else {
            matrix = new char[key][input.length() / key];
        }
    }

    public String encode () {
        int count = 0;
        for (int row = 0; row < matrix.length; row ++) {
            for (int col = 0; col < matrix[row].length; col ++) {
                if (count > string.length - 1) {
                    matrix[row][col] = '*';
                } else {
                    matrix[row][col] = string[count];
                }
                count ++;
            }
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (int col = 0; col < matrix[0].length; col ++) {
            for (char[] row : matrix) {
                stringBuilder.append(row[col]);
            }
        }

        return  stringBuilder.toString();
    }

    public String decode () {
        int count = 0;
        for (int col = 0; col < matrix[0].length; col ++) {
            for (int row = 0; row < matrix.length; row ++) {
                matrix[row][col] = string[count];
                count ++;
            }
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (char[] row : matrix) {
            for (char col : row) {
                stringBuilder.append(col);
            }
        }

        return stringBuilder.toString().replace("*", "");
    }
}
