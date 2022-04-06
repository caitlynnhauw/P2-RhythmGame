public class Board {
	boolean[][] board = new boolean[20][10];
	int[][] intBoard = new int[20][10];
	
	//Keeping track of pieces
	//0 - empty, 1 - I piece, 2 - O piece, 3 - T piece, 4 - L piece, 5 - J piece, 6 - Z piece, 7 - S piece
	//false - empty, true - not empty
	
	public Board() {
		for(int r = 0; r < board.length; r++) {
			for(int c = 0; c < board[0].length; c++) {
				board[r][c] = false;
			}
		}
		
		for(int r = 0; r < intBoard.length; r++) {
			for(int c = 0; c < intBoard[0].length; c++) {
				intBoard[r][c] = 0;
			}
		}
	}
	
	public void setEmpty() {
		for(int r = 0; r < board.length; r++) {
			for(int c = 0; c < board[0].length; c++) {
				board[r][c] = false;
			}
		}
		
		for(int r = 0; r < intBoard.length; r++) {
			for(int c = 0; c < intBoard[0].length; c++) {
				if(intBoard[r][c] == 1) {
					intBoard[r][c] = 2;
				}
			}
		}
	}
	
	
	
	public void spawn(String shape) {
		if(shape.equals("O")) {
			//System.out.println("O Piece");
			int width = 2;
			int height = 2;
			
			double rnd = Math.floor(Math.random()*(11-width));		
			for(int r = 0; r < height; r++) {
				for(int c = (int) rnd; c < width + (int) rnd; c++) {
					intBoard[r][c] = 1;
					board[r][c] = true;
				}
			}
		}else if(shape.equals("I")) {
			int width = 1;
			int height = 4;
			
			double rnd = Math.floor(Math.random()*(11-width));		
			for(int r = 0; r < height; r++) {
				for(int c = (int) rnd; c < width + (int) rnd; c++) {
					intBoard[r][c] = 1;
					board[r][c] = true;
				}
			}
		}else if(shape.equals("S")) {
			int width = 3;
			int height = 2;
			int i = 0;
			
			double rnd = Math.floor(Math.random()*(11-width));		
			for(int r = 0; r < height; r++) {
				if(i == 0) {
					for(int c = (int) rnd+1; c < width + (int) rnd; c++) {
						intBoard[r][c] = 1;
						board[r][c] = true;
					}
				}else {
					for(int c = (int) rnd; c < width + (int) rnd-1; c++) {
						intBoard[r][c] = 1;
						board[r][c] = true;
					}
				}
				i++;
			}
		}else if(shape.equals("Z")) {
			int width = 3;
			int height = 2;
			int i = 0;
			
			double rnd = Math.floor(Math.random()*(11-width));		
			for(int r = 0; r < height; r++) {
				if(i == 0) {
					for(int c = (int) rnd; c < width + (int) rnd-1; c++) {
						intBoard[r][c] = 1;
						board[r][c] = true;
					}
				}else {
					for(int c = (int) rnd+1; c < width + (int) rnd; c++) {
						intBoard[r][c] = 1;
						board[r][c] = true;
					}
				}
				i++;
			}
		}else if(shape.equals("T")) {
			int width = 3;
			int height = 2;
			int i = 0;
			
			double rnd = Math.floor(Math.random()*(11-width));		
			for(int r = 0; r < height; r++) {
				if(i == 0) {
					for(int c = (int) rnd+1; c < width + (int) rnd-1; c++) {
						intBoard[r][c] = 1;
						board[r][c] = true;
					}
				}else {
					for(int c = (int) rnd; c < width + (int) rnd; c++) {
						intBoard[r][c] = 1;
						board[r][c] = true;
					}
				}
				i++;
			}
		}else if(shape.equals("J")) {
			int width = 3;
			int height = 2;
			int i = 0;
			
			double rnd = Math.floor(Math.random()*(11-width));		
			for(int r = 0; r < height; r++) {
				if(i == 0) {
					for(int c = (int) rnd; c < width + (int) rnd-2; c++) {
						intBoard[r][c] = 1;
						board[r][c] = true;
					}
				}else {
					for(int c = (int) rnd; c < width + (int) rnd; c++) {
						intBoard[r][c] = 1;
						board[r][c] = true;
					}
				}
				i++;
			}
		}else if(shape.equals("L")) {
			int width = 3;
			int height = 2;
			int i = 0;
			double rnd = Math.floor(Math.random()*(11-width));
			
			for(int r = 0+2; r < height+2; r++) {
				if(i == 0) {
					for(int c = (int) rnd+2; c < width + (int) rnd; c++) {
						intBoard[r][c] = 1;
						board[r][c] = true;
					}
				}else {
					for(int c = (int) rnd; c < width + (int) rnd; c++) {
						intBoard[r][c] = 1;
						board[r][c] = true;
					}
				}
				i++;
			}
		}
		
	}//end of spawn
	 
	public void update() {
		
		for(int r = board.length-1; r >= 0; r--) {
			for(int c = board[r].length-1; c >= 0;c --) {
				if(board[r][c] == true) {
					if(intBoard[r+1][c] ==2)
						setEmpty();
				}
			}
		}
	}//end of update
	
	public int[] clockwiseTurn(int[] getter) {
		int[] temp = new int[9];
		
		for(int i = 0; i < 9; i++) {
			temp[i] = getter[i];
			//System.out.print(getter[i]);
		}
		
		
		for(int i = 0; i < 9; i++) {
			if(i == 0) {
				getter[2] = temp[i];
			}else if(i == 1) {
				getter[5] = temp[i];
			}else if(i == 2) {
				getter[8] = temp[i];
			}else if(i == 3) {
				getter[1] = temp[i];
			}else if(i == 4) {
				getter[4] = temp[i];
			}else if(i == 5){
				getter[7] = temp[i];
			}else if(i == 6) {
				getter[0] = temp[i];
			}else if(i == 7) {
				getter[3] = temp[i];
			}else if(i == 8) {
				getter[6] = temp[i];
			}
		}
		for(int i = 0; i < 9; i++){
			//System.out.print(getter[i]);
		}
		
		return getter;
	}//end of clockwise rotate
	
	public void rotate(int row, int col) {
		int[] getter = new int[9];
		int[] setter;
		int count = 0;
		
		for(int r = row-1; r < row+2; r++) {
			for(int c = col-1; c < col+2; c++) {
				getter[count] = intBoard[r][c];
				count++;
			}
		}
		
		setter = clockwiseTurn(getter);
		count = 0;
		
		for(int i = 0; i < 9; i++) {
			getter[i] = setter[i];
		}
		
		for(int i = 0; i < 9; i++) {
			if(i%3 == 0) {
				System.out.println("");
			}
			System.out.print(getter[i]);
		}
		
		for(int r = row-1; r < row+2; r++) {
			for(int c = col-1; c < col+2; c++) {
				intBoard[r][c] = getter[count];
				count++;
			}
		}
		count = 0;
	}//end of rotate
	
	public void testFall() {
		
		
		for(int r = board.length-1; r >= 0; r--) {
			for(int c = board[r].length-1; c >= 0;c --) {
	
				if(board[r][c] == true) {
					if(intBoard[r+1][c] ==2) {
						setEmpty();
						break;}
					board[r+1][c] = board[r][c];
					intBoard[r+1][c] = 1;
					board[r][c] = false;
					intBoard[r][c] = 0;
				}
			}
		}
		for(int c = board[0].length-1; c >= 0;c --) {
			if(board[board.length-1][c]) {
				setEmpty();
				
			}
		}
		toString();
	}
	
	public String toString() {
		/*
		for(int r = 0; r < board.length; r++) {
			for(int c = 0; c < board[0].length; c++) {
				if(board[r][c] == true) {
					System.out.print(board[r][c] + "  ");
				}else {
					System.out.print(board[r][c] + " ");
				}
				
			}
			System.out.println("");
		}
		*/
		
		///*
		for(int r = 0; r < intBoard.length; r++) {
			for(int c = 0; c < intBoard[0].length; c++) {
				if(intBoard[r][c] == 1) {
					System.out.print(intBoard[r][c] + " ");
				}else {
					System.out.print(intBoard[r][c] + " ");
				}		
			}
			System.out.println("");
		}
		//*/
		return "";
	}// end of toString
}
