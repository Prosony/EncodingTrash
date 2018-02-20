package sample.service;

public class QuickSort {
	private ABC abc = new ABC();
	private int number = 0;
	public void sortDuoMassive(char[][] source, int height , int width){
		char[] key = new char[width];

			for (int indexWidth = 0; indexWidth < width; indexWidth++) {
				key[indexWidth] = source[0][indexWidth];
			}
		sorting(key, 0, width-1); //char[] result =
			System.out.println("done");
//			for(int index = 0; index < width; index++){
//				System.out.print(result[index]);
//			}
	}

	private void sorting(char[] source, int left, int right){

		int index = partition(source, left, right);

		if (left < index - 1){
			System.out.println("#"+number+" left: "+left+" index "+index);
			number++;
			sorting(source, left, index - 1);
		}
		if (index < right) {
			System.out.println("#"+number+" index "+index+" right: "+right);
			number++;
			sorting(source, index, right);
		}
//		return source;
	}

	private int partition(char[] source, int leftPart, int rightPart) {
		int left = leftPart;
		int right = rightPart;
		int support = (left + right)/2;
		int supportNumberChar = abc.getNumberABC(source[support]); //support element
		while(left < right){
			while(abc.getNumberABC(source[left]) < supportNumberChar){
				left++;
			}
			while(abc.getNumberABC(source[right]) > supportNumberChar){
				right--;
			}
			System.out.println("supportNumberChar: "+ supportNumberChar);
			System.out.println("[left]"+left+" < "+right+"[right]");
			if (left < right){
				char afk = source[left];
				source[left] = source[right];
				source[right] = afk;
				left++;
				right--;
			}
		}
		System.out.println("Part 3");
		return left;
	}
}
