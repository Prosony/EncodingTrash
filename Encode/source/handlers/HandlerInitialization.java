package handlers;

public class HandlerInitialization {
    public boolean check(int action, int algorithm, String input, String key_1, String key_2) {
        switch (algorithm) {
            case 0:
                if (key_1.length() != 0 && input.length() != 0) {
                    Integer key = Integer.parseInt(key_1);
                    if (action == 1)
                        return
                            key > 1
                            && key < input.length()
                            && input.length() == key * (int) Math.ceil((double) input.length() / key);
                    return key > 1 && key < input.length();
                }
            case 1:
                if (action == 1)
                    return
                        input.length() % key_1.length() == 0;
                return
                    key_1.length() > 1;
            case 2:
                if (action == 1) {
                    return input.length() % (key_1.length() * key_2.length()) == 0;
                }
                return
                    key_1.length() > 1 &&
                    key_2.length() > 1;
            case 3:
                if (action == 1) {
                    String[] array = key_1.split("]");

                    int i = 0;
                    int[] size = new int[2];
                    for (char character: array[0].toCharArray()) {
                        if (String.valueOf(character).matches("[0-9]")) {
                            size[i] = Integer.parseInt(String.valueOf(character));
                            i++;
                        }
                    }

                    return input.length() % (size[0] * size[1]) == 0;
                }
                return input.length() > 0 && key_1.length() > 1;
            case 4:
                return
                    input.length() > 0;
            case 5:
                if (key_1.length() != 0) {
                    Integer key = Integer.parseInt(key_1);
                    return key > 0;
                }
            case 6:
                if (key_1.length() != 0) {
                    Integer key = Integer.parseInt(key_1);
                    return key > 0;
                }
            case 7:
                return
                    key_1.length() > 0 &&
                    input.length() > 0 &&
                    key_1.length() <= input.length();
            case 8:
                return input.length() > 0;

        }
        return false;
    }
}
