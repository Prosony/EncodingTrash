package sample.service;

public class QuickSort {
	private ABC abc = new ABC();

	public void sortDuoMassive(char[][] source, int height , int width){
		char[] key = new char[width];

		for (int indexWidth = 0; indexWidth < width; indexWidth++) {
			key[indexWidth] = source[0][indexWidth];
		}
		char[] result = sorting(key, 0, width-1); //char[] result =
			System.out.println("done\n");
			for(int index = 0; index < width; index++){
				System.out.print(result[index]);
			}
	}

	private char[] sorting(char[] source, int left, int right){
		if (left < right){
			int index = partition(source, left, right);
			sorting(source, left, index - 1);
			sorting(source, index +1, right);
		}
		return source;
	}

	private int partition(char[] source, int leftPart, int rightPart) {
		int supportNumberChar = abc.getNumberABC(source[rightPart]); //support element
		int indexLow = (leftPart - 1);
		for (int index = leftPart; index < rightPart; index++){
			if (abc.getNumberABC(source[index]) <= supportNumberChar){
				indexLow++;
				char temp = source[indexLow];
				source[indexLow] = source[index];
				source[index] = temp;
			}
		}
		char temp = source[indexLow+1];
		source[indexLow+1] = source[rightPart];
		source[rightPart] = temp;
		return indexLow+1;
	}
}
