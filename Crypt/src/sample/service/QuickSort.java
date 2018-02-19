package sample.service;



public class QuickSort {
	private ABC abc = new ABC();

	public void sortDuoMassive(char[][] source, int height , int width){
		char[] key = new char[width];

			for (int indexWidth = 0; indexWidth < width; indexWidth++) {
				key[indexWidth] = source[0][indexWidth];

			}
		char[] result = sorting(key, 0, width-1, (width-1)/2 );
			System.out.println("done");
			for(int index = 0; index < width; index++){
				System.out.print(result[index]);
			}
	}

	private char[] sorting(char[] source, int begin , int end, int support ){
		int left = begin;
		int right = end;
		int supportNumberChar = abc.getNumberABC(source[support]); //support element

		while(left < right){
			while(abc.getNumberABC(source[left]) < supportNumberChar){
					left++;
			}
			while(abc.getNumberABC(source[right]) > supportNumberChar){
					right--;
			}
			if (left < right){
				int changeLeft = left + 1;
				int changeRight = right - 1;
				char afk = source[changeLeft];
				source[changeLeft] = source[changeRight];
				source[changeRight] = afk;
			}
		}

		if (begin < right){sorting(source, begin, right, (begin+right)/2);}
		if (end > left){sorting(source, left, end, (end + left)/2);}

		return source;
	}
}
