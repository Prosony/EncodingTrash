package sample;

import sample.service.EncryptionService;
import sample.service.QuickSort;
import sample.service.SortBySymbol;

public class Encryption {

	public String firstEncryption(int key, String source){

		EncryptionService service = new EncryptionService();
		int height = service.getHeightSecond(key, source);

		System.out.println("source.length()" + source.length());
		System.out.println("key:" + key);
		System.out.println("height:" + height);

		char[][] array = new char[key][height];
		char[] sourceArray = source.toCharArray();
		int index_of_char = 0;

		System.out.println("#____PARSING_START____#");
		for (int i_key=0; i_key < key; i_key++){
			for (int i_count=0; i_count < height; i_count++){

				if (index_of_char < source.length()){
					array[i_key][i_count] = sourceArray[index_of_char];
					index_of_char++;
				}else{
					System.out.println("#ZERO#ZERO#");
					array[i_key][i_count] = ' ';
				}
				System.out.print(array[i_key][i_count]);

			}
		}
		System.out.println("\n#____PARSING_END____#");
		return service.convertArray(array, key, height, 1);
	}

	public String secondEncryption(String key, String source){

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
					array[indexHeight][indexWidth] = ' ';
				}
			}
		}
		QuickSort sort = new QuickSort();
		char[] result = sort.sortDuoMassive(array, height, width);

		SortBySymbol bySymbol = new SortBySymbol();
		bySymbol.sort(array, result, height, width);

		System.out.println("#INFO [Encryption] [secondEncryption] After");
		return service.convertArray(array, height, width, 2);
	}
	public String thirdEncription(int[] keyHeight, int[] keyWidth, String source){

		return null;
	}


}
