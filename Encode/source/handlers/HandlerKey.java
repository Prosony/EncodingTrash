package handlers;

public class HandlerKey {
    public boolean check (Integer algorithmIndex, String newValue) {
        switch (algorithmIndex) {
            case 0:
                return newValue.matches("^$|^[0-9]+$");
            case 1:
                // TODO - регулярное выражение, неповтояющиеся символы
                return newValue.matches("^$|^([a-zA-Z]+)$|^([а-яА-Я]+)$");
            case 2:
                return  newValue.matches("^$|^[0-9]+$");
            case 3:
                // TODO - регулярное выражение [d,d][d+]
                return newValue.matches("^$|^(.)+$");
            case 4:
                return true;
            case 5:
                return newValue.matches("^$|^[0-9]+$");
            case 6:
                return newValue.matches("^$|^[0-9]+$");
            case 7:
                return newValue.matches("^$|^[a-zA-Z]+|[а-яА-Я]+$");
            case 8:
                return true;
        }
        return false;
    }
}
