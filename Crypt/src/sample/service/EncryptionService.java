package sample.service;

public class EncryptionService {

    public int getHeightFirst(int key, String source){
        int height;
        if (source.length() % key > 0){
            height = source.length() / key + 1;
        }else{
            height = source.length() / key;
        }
        return height;
    }

    public int getHeightSecond(int width,  String source){
        int height;
        int length = source.length();
        if((source.length()+width)%width > 0){
            height = (length+width)/width + 1;
        }else{
            height = (source.length()+width)/width;
        }
        return  height;
    }


    public String convertArray(char[][] result , int height, int width, int method){
        StringBuilder builder = new StringBuilder();
        System.out.println("#INFO [Encryption] [secondEncryption] #____RESULT:____#");

        switch (method){
            case 1:
                for(int indexLine = 0; indexLine < width; indexLine ++){
                    for (int indexColumn = 0; indexColumn < height; indexColumn++){
                        builder.append(result[indexColumn][indexLine]);
                        System.out.print(result[indexColumn][indexLine]);
                    }
                }
                break;
            case 2:
                for (int indexHeight = 1; indexHeight < height; indexHeight++){
                    for (int indexWidth = 0; indexWidth < width; indexWidth++) {
                        builder.append(result[indexHeight][indexWidth]);
                        System.out.print(result[indexHeight][indexWidth]);
                    }
                }
                break;
        }
        return  builder.toString();
    }
}