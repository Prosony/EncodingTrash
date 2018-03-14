package sample.service;

public class QuickSort {
	private ABC abc = new ABC();

	public char[] sortMassive(String key){
		char[] keyArray = key.toCharArray();
		return sorting(keyArray, 0, keyArray.length-1);
	}
	public char[] sortDuoMassive(char[][] source, char[] key, int height , int width){
		char[] result = sorting(key, 0, width-1); //char[] result =
		System.out.println("#INFO [QuickSort] [sortDuoMassive] done");
		for(int index = 0; index < width; index++){
			System.out.print(result[index]);
		}
		System.out.println();
		return result;
	}

	public char[] sorting(char[] source, int left, int right){
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
