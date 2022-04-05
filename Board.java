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
				intBoard[r][c] = 0;
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
	
	
	public void rotate(int r, int c) {
		
	}//end of rotate
	
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
