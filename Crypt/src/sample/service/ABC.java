package sample.service;

public class ABC {

	private char[] abcENG = {'a','b','c','d','e','f','g','h','i','j','k','l','m','o','p','q','r','s','t','u','w','x','y','z'};
	private char[] abcRUS = {'а','б','в','г','д','е','ё','ж','з','и','й','к','л','м','н','о','п','р','с','т','у','ф','ч','ц','ч','щ','ъ','ы','ь','э','ю','я'};
	private char [] abcNUM = {'0','1','2','3','4','5','6','7','8','9'};

	public int getNumberABC(char symbol){
		int indexEquals = -1;
		String abcKind = checkABC(symbol);
		if (abcKind != null){

			switch (abcKind){
				case "rus":
					for (int index = 0; index < abcRUS.length; index++){
						if (symbol == abcRUS[index]){
							indexEquals = index;
							break;
						}
					}
					if (indexEquals != -1){
						return indexEquals;
					}
					break;
				case "eng":
					for (int index = 0; index < abcENG.length; index++){
						if (symbol == abcENG[index]){
							indexEquals = index;
							break;
						}
					}
					if (indexEquals != -1){
						return indexEquals;
					}
					break;

			}
		}else {
			System.out.println("#INFO [ABC] [getNumberABC] [ERROR] abc not found!");
		}
		return -1;
	}
	public String checkABC(char symbol){
		for (char anAbcENG : abcENG) {
			if (symbol == anAbcENG) {
				return "eng";
			}
		}
		for (char abcRU : abcRUS) {
			if (symbol == abcRU) {
				return "rus";
			}
		}
		for (char number : abcNUM){
			if (symbol == number){
				return "NUM";
			}
		}
		return null;
	}
	public char[] getABC(String type){

		switch (type){
			case "ENG": return abcENG;
			case "RUS": return abcRUS;
			case "NUM": return abcNUM;
		}
		return null;
	}
}

