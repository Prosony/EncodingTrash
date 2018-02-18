package sample;

import sample.service.ABC;

public class AlgoCrypt {

	public String firstAlgo(int key, String source){
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
		return convertArray(array, key, count);
	}

	public String secondAlgo(String key, String source){
		char[] keyArray = key.toCharArray();
		ABC abc = new ABC();
		int number = abc.getNumberABC(key.toCharArray()[0]);
		System.out.println("number by ABC:" + number);

		return null;
	}
	private String convertArray(char[][] result , int key, int count){

		StringBuilder builder = new StringBuilder();
		System.out.println("#____RESULT:____#");
		for(int indexLine = 0; indexLine < count; indexLine ++){
			for (int indexColumn = 0; indexColumn < key; indexColumn++){
				builder.append(result[indexColumn][indexLine]);
				System.out.print(result[indexColumn][indexLine]);
			}
		}
		return builder.toString();
	}

}