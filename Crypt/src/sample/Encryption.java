package sample;

import sample.service.QuickSort;
import sample.service.SortBySymbol;

public class Encryption {

	public String firstEncryption(int key, String source){
		int count;
		if (source.length() % key > 0){
			count = source.length() / key + 1;
		}else{
			count = source.length() / key;
		}

		System.out.println("source.length()" + source.length());
		System.out.println("key:" + key);
		System.out.println("count:" + count);

		char[][] array = new char[key][count];
		char[] sourceArray = source.toCharArray();
		int index_of_char = 0;

		System.out.println("#____PARSING_START____#");
		for (int i_key=0; i_key < key; i_key++){
			for (int i_count=0; i_count < count; i_count++){

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
		return convertArray(array, key, count, 1);
	}

	public String secondEncryption(String key, String source){

		char[] keyArray = key.toCharArray();
		int width = keyArray.length;

		int height;
		if((source.length()+width)%width > 0){
			height = (source.length()+width)/width + 1;
		}else{
			height = (source.length()+width)/width;
		}
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
		return convertArray(array, height, width, 2);
	}

	private String convertArray(char[][] result , int height, int width, int method){
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
