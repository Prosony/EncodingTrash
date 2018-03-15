package sample;

import sample.service.EncryptionService;
import sample.service.sorting.QuickSort;
import sample.service.sorting.SortBySymbol;

import java.util.Arrays;

public class Encryption {

	public String firstEncryption(int key, String source){

		EncryptionService service = new EncryptionService();
		int height = service.getHeightFirst(key, source);

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
		char[] result = sort.sortDuoMassive(array, keyArray,height, width);

		SortBySymbol bySymbol = new SortBySymbol();
		bySymbol.sortSecond(array, result, height, width);

		System.out.println("#INFO [Encryption] [secondEncryption] After");
		return service.convertArray(array, height, width, 2);
	}

	public String thirdEncryption(String[] keys, String source){
		char[] keyHeight = keys[0].toCharArray();
		char[] keyWidth = keys[1].toCharArray();
		int height = keyHeight.length + 1;
		int width = keyWidth.length + 1;
		char[] text = source.toCharArray();
		if (source.length() > 0){
			int count = source.length()/(keyHeight.length + keyWidth.length);
			if (count == 0){count = 1;}

			int countText = 0;
			for (int index = 0; index < count; index++){
				char[][] array = new char[height][width];
				int countKeyOne = 0;
				int countKeyTwo = 0;
				for (int cursorH = 0; cursorH< height; cursorH++){
					for (int cursorW = 0; cursorW < width; cursorW++){

							if (cursorH == 0 && cursorW == 0) array[cursorH][cursorW] = '0';
							if (cursorH == 0 && cursorW !=0) {
								array[cursorH][cursorW] = keyHeight[countKeyOne];
								countKeyOne++;
							}
							if (cursorH != 0 && cursorW ==0){
								array[cursorH][cursorW] = keyWidth[countKeyTwo];
								countKeyTwo++;
							}

						if (cursorH != 0 && cursorW != 0){
							if (countText < text.length){
								array[cursorH][cursorW] = text[countText];
								countText++;
							}else{
								array[cursorH][cursorW] = '*';
							}
						}
					}
				}
				System.out.println("count: "+index);
				System.out.println("___________________________");
				for (int cursorH = 0; cursorH< height; cursorH++) {
					for (int cursorW = 0; cursorW < width; cursorW++) {
						System.out.print(array[cursorH][cursorW]);
					}
					System.out.println();
				}
				System.out.println("___________________________");
				//TODO send to sort
				QuickSort sort = new QuickSort();
				char[] doneKeyHeight = sort.sortMassive(keys[0]);
				char[] doneKeyWidth = sort.sortMassive(keys[1]);
				SortBySymbol bySymbol = new SortBySymbol();
				bySymbol.sortThird(doneKeyHeight, doneKeyWidth, array);

				System.out.println("#INFO [Encryption] [thirdEncryption] doneKeyHeight: "+ Arrays.toString(doneKeyHeight));
				System.out.println("#INFO [Encryption] [thirdEncryption] doneKeyWidth: "+ Arrays.toString(doneKeyWidth));
			}
		}else{
			System.out.println("#INFO [Encryption] [thirdEncryption] source is null");
		}
		return null;
	}

}
