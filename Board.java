import java.util.ArrayList;

public class Board {
	boolean[][] board = new boolean[20][10];
	int[][] intBoard = new int[20][10];
	String[][] colorBoard = new String[20][10];
	String[] randomBlock = { "O", "I", "S", "Z", "T", "L", "J" };
	public static ArrayList<String> queue = new ArrayList<String>();
	boolean testForNew = false;
	boolean gameOver = false;
	boolean isSZ = false;
	boolean isI = false;
	boolean iRotate = false;
	boolean fallKey = false;
	boolean rightKey = false;
	boolean leftKey = false;
	boolean isRowFilled = false;
	boolean lineClearing = false;
	int topRowFilled = 0;
	int sec = 0;
	int fallSec = 0;
	int clearRowIndex = 0;
	int landingRow = 0;
	String blockColor = "nothingtest";
	
	static int linesCleared = 0;
	
	public int centerR, centerC;
	// false - empty, true - not empty

	public Board() {
		for (int r = 0; r < board.length; r++) {
			for (int c = 0; c < board[0].length; c++) {
				board[r][c] = false;
				intBoard[r][c] = 0;
				colorBoard[r][c] = "navy";
				
			}
		}
	}

	public void setEmpty() {
		for (int r = 0; r < board.length; r++) {
			for (int c = 0; c < board[0].length; c++) {
				board[r][c] = false;
			}
		}

		for (int r = 0; r < intBoard.length; r++) {
			for (int c = 0; c < intBoard[0].length; c++) {
				if (intBoard[r][c] == 1) {
					intBoard[r][c] = 2;
				}
			}
		}
		spawnNewBlock();
	}

	public void spawn(String shape) {
		if(shape.equals("O")) {
			int width = 2;
			int height = 2;
			
			double rnd = Math.floor(Math.random()*(11-width));
			//double rndColor = Math.floor(Math.random()*((randomColor.length+1)-width));
			//String color = randomColor[(int) rndColor];
			for(int r = 0; r < height; r++) {
				for(int c = (int) rnd; c < width + (int) rnd; c++) {
					intBoard[r][c] = 1;
					board[r][c] = true;
					colorBoard[r][c] = "blue";
					blockColor = "blue";
				}
			}
			isSZ = false;
			isI = false;
		}else if(shape.equals("test")) {
			int width = 10;
			int height = 2; 
					
			double rnd = Math.floor(Math.random()*(11-width));		
			for(int r = 0; r < height; r++) {
				for(int c = (int) rnd; c < width + (int) rnd; c++) {
					intBoard[r][c] = 1;
					board[r][c] = true;
				}
			}
			isSZ = false;
			isI = false;
		}else if(shape.equals("I")) {
			iRotate = false;
			int width = 1;
			int height = 4;
			int count = 0;
			double rnd = Math.floor(Math.random()*(11-width));
			//double rndColor = Math.floor(Math.random()*((randomColor.length+1)-width));
			//String color = randomColor[(int) rndColor];
			
			for(int r = 0; r < height; r++) {
				for(int c = (int) rnd; c < width + (int) rnd; c++) {
					if(count == 2) {
						centerR = r-1;
						centerC = c;
					}
					intBoard[r][c] = 1;
					board[r][c] = true;
					colorBoard[r][c] = "violet";
					blockColor = "violet";
					count++;
				}
			}
			isSZ = false;
			isI = true;
		}else if(shape.equals("S")) {
			isSZ = true;
			isI = false;
			int width = 3;
			int height = 2;
			int i = 0;
			int count = 1;
			double rnd = Math.floor(Math.random()*(11-width));	
			//double rndColor = Math.floor(Math.random()*((randomColor.length+1)-width));
			//String color = randomColor[(int) rndColor];
			
			for(int r = 0; r < height; r++) {
				if(i == 0) {
					for(int c = (int) rnd+1; c < width + (int) rnd; c++) {
						intBoard[r][c] = 1;
						board[r][c] = true;
						colorBoard[r][c] = "sky";
						blockColor = "sky";
						count++;
					}
				}else {
					for(int c = (int) rnd; c < width + (int) rnd-1; c++) {
						intBoard[r][c] = 1;
						board[r][c] = true;
						colorBoard[r][c] = "sky";
						blockColor = "sky";
						if(count == 4) {
							centerR = r;
							centerC = c;
						}
						count++;
					}
				}
				i++;
			}
		}else if(shape.equals("Z")) {
			isSZ = true;
			isI = false;
			int width = 3;
			int height = 2;
			int i = 0;
			int count = 1;
			double rnd = Math.floor(Math.random()*(11-width));
			//rndColor = Math.floor(Math.random()*((randomColor.length+1)-width));
			//String color = randomColor[(int) rndColor];
			
			for(int r = 0; r < height; r++) {
				if(i == 0) {
					for(int c = (int) rnd; c < width + (int) rnd-1; c++) {
						intBoard[r][c] = 1;
						board[r][c] = true;
						colorBoard[r][c] = "vibrantblue";
						blockColor = "vibrantblue";
						count++;
					}
				}else {
					for(int c = (int) rnd+1; c < width + (int) rnd; c++) {
						intBoard[r][c] = 1;
						board[r][c] = true;
						colorBoard[r][c] = "vibrantblue";
						blockColor = "vibrantblue";
						if(count == 3) {
							centerR = r;
							centerC = c;
						}
						count++;
					}
				}
				i++;
			}
		}else if(shape.equals("T")) {
			int width = 3;
			int height = 2;
			int i = 0;
			int count = 1;
			double rnd = Math.floor(Math.random()*(11-width));
			//double rndColor = Math.floor(Math.random()*((randomColor.length+1)-width));
			//String color = randomColor[(int) rndColor];
			
			for(int r = 0; r < height; r++) {
				if(i == 0) {
					for(int c = (int) rnd+1; c < width + (int) rnd-1; c++) {
						intBoard[r][c] = 1;
						board[r][c] = true;
						colorBoard[r][c] = "navy";
						blockColor = "navy";
						count++;
					}
				}else {
					for(int c = (int) rnd; c < width + (int) rnd; c++) {
						intBoard[r][c] = 1;
						board[r][c] = true;
						colorBoard[r][c] = "navy";
						blockColor = "navy";
						if(count == 3) {
							centerR = r;
							centerC = c;
						}
						count++;
					}
				}
				i++;
			}
			isSZ = false;
			isI = false;
		}else if(shape.equals("J")) {
			int width = 3;
			int height = 2;
			int i = 0;
			int count = 1;
			double rnd = Math.floor(Math.random()*(11-width));
			//double rndColor = Math.floor(Math.random()*((randomColor.length+1)-width));
			//String color = randomColor[(int) rndColor];
			
			for(int r = 0; r < height; r++) {
				if(i == 0) {
					for(int c = (int) rnd; c < width + (int) rnd-2; c++) {
						intBoard[r][c] = 1;
						board[r][c] = true;
						colorBoard[r][c] = "teal";
						blockColor = "teal";
						count++;
					}
				}else {
					for(int c = (int) rnd; c < width + (int) rnd; c++) {
						intBoard[r][c] = 1;
						board[r][c] = true;
						colorBoard[r][c] = "teal";
						blockColor = "teal";
						if(count == 3) {
							centerR = r;
							centerC = c;
						}
						count++;
					}
				}
				i++;
			}
			isSZ = false;
			isI = false;
		}else if(shape.equals("L")) {
			int width = 3;
			int height = 2;
			int i = 0;
			int count = 1;
			double rnd = Math.floor(Math.random()*(11-width));
			//double rndColor = Math.floor(Math.random()*((randomColor.length+1)-width));
			//String color = randomColor[(int) rndColor];
			
			for(int r = 0; r < height; r++) {
				if(i == 0) {
					for(int c = (int) rnd+2 ; c < width + (int) rnd; c++) {
						intBoard[r][c] = 1;
						board[r][c] = true;
						colorBoard[r][c] = "purple";
						blockColor = "purple";
						count++;
					}
				}else {
					for(int c = (int) rnd; c < width + (int) rnd; c++) {
						intBoard[r][c] = 1;
						board[r][c] = true;
						colorBoard[r][c] = "purple";
						blockColor = "purple";
						if(count == 3) {
							centerR = r;
							centerC = c;
						}
						count++;
					}
				}
				i++;
			}
			isSZ = false;
			isI = false;
		}
	}//end of spawn

	public void update() {
		for (int r = board.length - 1; r >= 0; r--) {
			for (int c = board[r].length - 1; c >= 0; c--) {
				if (board[r][c] == true) {
					if (intBoard[r + 1][c] == 2)
						setEmpty();
				}
			}
		}

		// test for gameOver
		for (int c = 0; c < board[0].length; c++) {
			if (intBoard[1][c] == 2) {
				topRowFilled++;
			}
		}
		if (topRowFilled > 5) {
			gameOver = true;
			System.out.println("Game Over");
		}
		
		nextMoves();
		
	}// end of update

	public void updateBoolArr() {
		for (int r = 0; r < intBoard.length; r++) {
			for (int c = 0; c < intBoard[0].length; c++) {
				if (intBoard[r][c] == 1) {
					board[r][c] = true;
				} else {
					board[r][c] = false;
				}
			}
		}

	}

	public void spawnNewBlock() {
		// check if the remaining blocks have stopped moving
		for (int r = 0; r < intBoard.length; r++) {
			for (int c = 0; c < intBoard[r].length; c++) {
				if (intBoard[r][c] == 2 || intBoard[r][c] == 0) {
					testForNew = true;
				} else
					testForNew = false;
			}
		}

		if (testForNew && gameOver == false) {
			double rnd = Math.floor(Math.random() * (randomBlock.length));
			//spawn(randomBlock[(int) rnd]);
			queue.remove(0);
			queue.add(randomBlock[(int) rnd]);
			spawn(queue.get(0));
			testForNew = false;
		}
	}

	public int[] ClockwiseTurn(int[] getter) { // clockwise turn repositions each element in a 9x9 array, to another
													// position (based off its current one)
		int[] temp = new int[9];

		for (int i = 0; i < 9; i++) {
			temp[i] = getter[i];
			// System.out.print(getter[i]);
		}

		for (int i = 0; i < 9; i++) {
			if (i == 0) {
				getter[2] = temp[i];
			} else if (i == 1) {
				getter[5] = temp[i];
			} else if (i == 2) {
				getter[8] = temp[i];
			} else if (i == 3) {
				getter[1] = temp[i];
			} else if (i == 4) {
				getter[4] = temp[i];
			} else if (i == 5) {
				getter[7] = temp[i];
			} else if (i == 6) {
				getter[0] = temp[i];
			} else if (i == 7) {
				getter[3] = temp[i];
			} else if (i == 8) {
				getter[6] = temp[i];
			}
		}

		for (int i = 0; i < 9; i++) {
			// System.out.print(getter[i]);
		}

		return getter;
	}// end of clockwise rotate

	public void rotate(int row, int col) {
		int[] getter = new int[9];		   
		int[] setter;
		boolean isSet = false;
		int count = 0;
		int leftEdge = 0;
		int rightEdge = 0;
		int iRightEdge = 0;
		for(int r = 0; r < intBoard.length; r++) {
			for(int c = 0; c < intBoard[0].length; c++) {
				if(intBoard[r][c] == 1 && c == 9) {
					rightEdge++;
				}else if(intBoard[r][c] == 1 && c == 0){
					leftEdge++;
				}else if(intBoard[r][c] == 1 && c == 8) {
					iRightEdge++;
				}
			}
		}
		if(rightEdge > 2 || rightEdge > 1 && isSZ == true) {
			moveLeft();
			rightEdge = 0;
			leftEdge = 0;
			iRightEdge = 0;
			rotate(centerR, centerC);
		}else if(leftEdge > 2 || leftEdge > 1 && isSZ == true) {
			moveRight();
			rightEdge = 0;
			leftEdge = 0;
			iRightEdge = 0;
			rotate(centerR, centerC);
		}else if(iRightEdge > 2 && isI == true) {
			moveLeft();
			rightEdge = 0;
			leftEdge = 0;
			iRightEdge = 0;
			rotate(centerR, centerC);
		}else{
			if(isI == true) {
				if(iRotate == false) {
					if(intBoard[centerR][centerC+2] == 2) {
						isSet = true;
					}else {
						isSet = false;
					}
				}else{
					if(intBoard[centerR+2][centerC] == 2) {
						isSet = true;
					}else {
						isSet = false;
					}
				}
			}
			for(int r = row-1; r < row+2; r++) {
				for(int c = col-1; c < col+2; c++) {
					getter[count] = intBoard[r][c];
					count++;
				}
			}
			count = 0;
		
			for(int i = 0; i < 9; i++) {
				if(getter[i] == 2) {
					isSet = true;
				}
			}
		
			if(isSet == false) {
				setter = ClockwiseTurn(getter);
				if(isI == true) {
					if(iRotate == false) {
						intBoard[centerR][centerC+2] = 1;
						intBoard[centerR+2][centerC] = 0;
						iRotate = true;
					}else{
						intBoard[centerR+2][centerC] = 1;
						intBoard[centerR][centerC+2] = 0;
						iRotate = false;
					}
				}
		
				for(int i = 0; i < 9; i++) {
					getter[i] = setter[i];
				}
		
				/*
				for(int i = 0; i < 9; i++) {
					if(i%3 == 0) {
					System.out.println("");
					}
					System.out.print(getter[i]);
				}
			 	*/
				
				//setting the rotate
				for(int r = row-1; r < row+2; r++) {
					for(int c = col-1; c < col+2; c++) {
						intBoard[r][c] = getter[count];
						count++;
					}
				}
			}
		}
	}//end of rotate

	public void testFall() {
		updateBoolArr();
		updateColors();
		for (int r = board.length - 1; r >= 0; r--) {
			for (int c = board[0].length - 1; c >= 0; c--) {

				if (board[r][c] == true) {
					if (intBoard[r + 1][c] == 2) {
						setEmpty();
						break;
					}
					board[r + 1][c] = board[r][c];
					intBoard[r + 1][c] = 1;
					colorBoard[r + 1][c] = colorBoard[r][c];
					board[r][c] = false;
					intBoard[r][c] = 0;
					colorBoard[r][c] = blockColor;
				}
			}
		}
		for (int c = board[0].length - 1; c >= 0; c--) {
			if (board[board.length - 1][c]) {
				setEmpty();
				centerR--;
			}
		}
		centerR++;
		//toString();
	}

	public boolean isRowFilled(int[] row) {
		for(int i = 0; i < row.length; i++) {
			if(row[i] != 2) {
				return false;
			}
		}
		
		return true;
	}
	
	
	public boolean clearLine() {
		for (int r = board.length - 1; r >= 0; r--) {
			if (isRowFilled(intBoard[r])) {
				//System.out.println("Hello");
				for (int c = board[0].length - 1; c >= 0; c--) {
					board[r][c] = false;
					intBoard[r][c] = 2;
				}
				
				pushBlocksDown(r);
				linesCleared++;
			
			}
		}
		
		updateBoolArr();
		
		if(linesCleared > 0) {
			return true;
		}else {
			return false;
		}

		/*
		 * for (int r = board.length - 1; r > 0; r--) { int count = 0; for (int c = 0; c
		 * < board[r].length; c++) { if (intBoard[r][c] == 2) { count++; } }
		 */
	} // end of clear line method
	
	public static int getLinesCleared() {
		return linesCleared;
	}
	

	public void pushBlocksDown(int r) {
		for (int row = r; row > 0; row--) {
			for (int c = 0; c < intBoard[0].length; c++) {
				if (intBoard[row][c] == 2 || intBoard[row-1][c] == 2) {
					board[row][c] = board[row - 1][c];
					intBoard[row][c] = intBoard[row - 1][c];
					colorBoard[row][c] = colorBoard[row-1][c];
				}
			}
		}
		toString();
		
	}
	public void updateColors() {
		for(int r = 0; r < colorBoard.length; r++) {
			for(int c = 0; c < colorBoard[0].length; c++) {
				if(intBoard[r][c] == 1 && r != 19  && intBoard[r+1][c] != 2) {
					colorBoard[r+1][c] = blockColor;
				}
			}
		}
	}
	
	public void updateColorsRight() {
		for(int r = 0; r < colorBoard.length; r++) {
			for(int c = 0; c < colorBoard[0].length; c++) {
				if(intBoard[r][c] == 1 && c != 9) {
					if(intBoard[r][c+1] != 2) {
						colorBoard[r][c+1] = blockColor;
					}
				}
			}
		}
	}
	public void updateColorsLeft() {
		for(int r = 0; r < colorBoard.length; r++) {
			for(int c = 0; c < colorBoard[0].length; c++) {
				if(intBoard[r][c] == 1 && c != 0) {
					if(intBoard[r][c-1] != 2) {
						colorBoard[r][c-1] = blockColor;
					}
				}
			}
		}
	}




	public void moveRight() { // has a couple bugs
		updateColorsRight();
		boolean isEdge = false;
		for (int r = 0; r < intBoard.length; r++) {
			for (int c = 0; c < intBoard[0].length; c++) {
				if (c == intBoard[0].length - 1 && intBoard[r][c] == 1) {
					isEdge = true;
					// System.out.print(isEdge);
				} else if (c != 9) {
					if (intBoard[r][c + 1] == 2 && intBoard[r][c] == 1) {
						isEdge = true;
					}
				}
			}
		}

		if (isEdge == false) {
			for (int r = intBoard.length - 1; r > -1; r--) {
				for (int c = intBoard[0].length - 1; c > -1; c--) {
					if (intBoard[r][c] == 1) {
						intBoard[r][c + 1] = 1;
						intBoard[r][c] = 0;
					}

				}
			}
			centerC++;
			// System.out.println("Current centerC: " + centerC);
		}

	}// end of moveright

	public void moveLeft() { // has a couple bugs
		updateColorsLeft();
		boolean isEdge = false;
		for (int r = 0; r < intBoard.length; r++) {
			for (int c = 0; c < intBoard[0].length; c++) {
				if (c == 0 && intBoard[r][c] == 1) {
					isEdge = true;
					// System.out.print(isEdge);
				} else if (c != 0) {
					if (intBoard[r][c - 1] == 2 && intBoard[r][c] == 1) {
						isEdge = true;
					}
				}
			}
		}

		if (isEdge == false) {
			for (int r = 0; r < intBoard.length; r++) {
				for (int c = 0; c < intBoard[0].length; c++) {
					if (intBoard[r][c] == 1) {
						intBoard[r][c - 1] = 1;
						intBoard[r][c] = 0;
					}

				}
			}
			centerC--;
			// System.out.println("Current centerC: " + centerC);
		}

	}// end of moveleft

	public String toString() {
		/*
		 * for(int r = 0; r < board.length; r++) { for(int c = 0; c < board[0].length;
		 * c++) { if(board[r][c] == true) { System.out.print(board[r][c] + "  "); }else
		 * { System.out.print(board[r][c] + " "); }
		 * 
		 * } System.out.println(""); }
		 */

		/// *
		for (int r = 0; r < intBoard.length; r++) {
			for (int c = 0; c < intBoard[0].length; c++) {
				if (intBoard[r][c] == 1) {
					System.out.print(intBoard[r][c] + " ");
				} else {
					System.out.print(intBoard[r][c] + " ");
				}
			}
			System.out.println("");
		}
		// */
		return "";
	}// end of toString
	
	public void nextMoves() {
		for(int e = 0; e < queue.size(); e++) {
			
		}
	}
	public void previewLand() {
		for(int r = 0; r < intBoard.length; r++) {
			if(intBoard[r][centerC] != 2 && intBoard[r][centerC+1] != 2 && intBoard[r][centerC-1] != 2) {
				landingRow = r;
			}
		}
		System.out.println(landingRow);
		
	}
}
