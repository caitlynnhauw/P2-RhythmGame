import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Timer;

public class Board {
	boolean[][] board = new boolean[20][10];
	int[][] intBoard = new int[20][10];
	String[][] colorBoard = new String[20][10];
	String[] randomBlock = { "T", "I", "S", "Z", "T", "L", "J" };
	public static ArrayList<String> queue = new ArrayList<String>();
	boolean testForNew = false;
	boolean gameOver = false;
	boolean isSZ = false;
	boolean isI = false;
	boolean isO = false;
	boolean iRotate = false;
	boolean fallKey = false;
	boolean rightKey = false;
	boolean leftKey = false;
	boolean isRowFilled = false;
	boolean lineClearing = false;
	boolean keyDown = false;
	boolean timerStarted = false;
	boolean rotateKey;
	boolean upRotate = false;
	boolean canRotate = true;
	boolean spacebar = false;
	int topRowFilled = 0;
	int sec = 0;
	int fallSec = 0;
	int clearRowIndex = 0;
	int landingRow = 19;
	int delayTimes = 350;
	int[] oneRows = new int[4];
	int[] oneCols = new int[4];
	int[] y = new int [4];

	String blockColor = "nothingtest";
	
	static int linesCleared = 0; // lines cleared in one clear
	static int totalLinesCleared = 0; // lines cleared total
	static int storeLines = 0;

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
		test.stop();
		timerStarted = false;
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
		delayTimes = 350;
	}

	public void spawn(String shape) {
		if(shape.equals("O")) {
			int width = 2;
			int height = 2;
			
			double rnd = 4.0;
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
			isO = true;
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
			isO = false;
		}else if(shape.equals("I")) {
			iRotate = false;
			int width = 1;
			int height = 4;
			int count = 0;
			double rnd = 4.0;
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
			isO = false;
		}else if(shape.equals("S")) {
			int width = 3;
			int height = 2;
			int i = 0;
			int count = 1;
			double rnd = 4.0;	
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
			isSZ = true;
			isI = false;
			isO = false;
		}else if(shape.equals("Z")) {
			int width = 3;
			int height = 2;
			int i = 0;
			int count = 1;
			double rnd = 4.0;
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
			isSZ = true;
			isI = false;
			isO = false;
		}else if(shape.equals("T")) {
			int width = 3;
			int height = 2;
			int i = 0;
			int count = 1;
			double rnd = 4.0;
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
			isO = false;
		}else if(shape.equals("J")) {
			int width = 3;
			int height = 2;
			int i = 0;
			int count = 1;
			double rnd = 4.0;
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
			isO = false;
		}else if(shape.equals("L")) {
			int width = 3;
			int height = 2;
			int i = 0;
			int count = 1;
			double rnd = 4.0;
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
			isO = false;
		}
	}//end of spawn

	public void update() {
		for (int r = board.length - 2; r >= 0; r--) {
			for (int c = 0; c < board[r].length; c++) {
				if (board[r][c] == true) {
					if (intBoard[r + 1][c] == 2) {
						test.start();
						timerStarted = true;
					}
				}
			}
		}

		// test for gameOver
			if (intBoard[1][7] == 2) {
				topRowFilled++;
			}
		if (topRowFilled > 5) {
			gameOver = true;
			System.out.println("Game Over");
		}
		previewLand();
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
		int first = 2;
		int second = 1;
		
		for (int i = 0; i < 9; i++) {
			temp[i] = getter[i];
			// System.out.print(getter[i]);
		}
		
		for (int i = 0; i < 9; i++) {
			switch(i) {
			case 0:
				getter[2] = temp[i];
				break;
			case 1:
				getter[5] = temp[i];
				break;
			case 2:
				getter[8] = temp[i];
				break;
			case 3:
				getter[1] = temp[i];
				break;
			case 4:
				getter[4] = temp[i];
				break;
			case 5:
				getter[7] = temp[i];
				break;
			case 6:
				getter[0] = temp[i];
				break;
			case 7:
				getter[3] = temp[i];
				break;
			case 8:
				getter[6] = temp[i];
				break;
			}
		}
		
		for (int i = 0; i < 9; i++) {
			// System.out.print(getter[i]);
		}

		return getter;
	}// end of clockwise rotate
	
	public boolean rightSurroundings() {
		return false;
	}
	
	public void rotate(int row, int col) {
		int[] getter = new int[9];		   
		int[] setter;
		boolean isSet = false;
		int count = 0;
		int edge = 0;
		
		for(int r = 0; r < intBoard.length; r++) {
			for(int c = 0; c < intBoard[0].length; c++) {
				if(centerC != 0 && centerC != 9) {
					if(intBoard[centerR][centerC] == 1) {
						
					}
				}
			}
		}
		
		if(isO == false) {
			if(centerR == 19) {
				moveUp();
				rotate(centerR, centerC);
			}else if(intBoard[centerR+1][centerC] == 2) {
				moveUp();
				rotate(centerR, centerC);
			}else if(centerC == 0) {
				moveRight();
				rotate(centerR, centerC);
			}else if(centerC == 9 && isI == true || centerC == 8 && isI == true) {
				moveLeft();
				rotate(centerR, centerC);
			}else if(centerC == 9) {
				moveLeft();
				rotate(centerR, centerC);
			}else{
				if(centerC != 0 && centerC != 9) {
					if(intBoard[centerR][centerC-1] == 2) {
						moveRight();
						rotate(centerR, centerC);
					}else if(intBoard[centerR][centerC+1] == 2) {
						moveLeft();
						rotate(centerR, centerC);
					}
				}
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
							colorBoard[centerR][centerC+2] = blockColor;
							intBoard[centerR+2][centerC] = 0;
							colorBoard[centerR+2][centerC] = "darkteal";
							iRotate = true;
						}else{
							intBoard[centerR+2][centerC] = 1;
							colorBoard[centerR+2][centerC] = blockColor;
							intBoard[centerR][centerC+2] = 0;
							colorBoard[centerR][centerC+2] = "darkteal";
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
							if(intBoard[r][c] == 1) {
								colorBoard[r][c] = blockColor;
							}
							count++;
						}
					}
				}
			}
		}
		
	}//end of rotate

	public void testFall() {
		updateBoolArr();
		updateColors();
		boolean holder = isOnEdge(0, 0);
		if(isOnEdge(0,0) == false) {
			centerR++;
		}
		for (int r = board.length - 1; r >= 0; r--) {
			for (int c = board[0].length - 1; c >= 0; c--) {

				if (board[r][c] == true && r != 19) {
					if (intBoard[r + 1][c] == 2) {
						test.start();
						timerStarted = true;
						spacebar = false;
						break;
					}
					//System.out.print(isOnEdge(intBoard.length - r, intBoard[0].length - c));
					if(isOnEdge(intBoard.length - r, intBoard[0].length - c) == false && board[r+1][c] == false && holder == false) {
						board[r + 1][c] = board[r][c];
						intBoard[r + 1][c] = 1;
						colorBoard[r + 1][c] = colorBoard[r][c];
						board[r][c] = false;
						intBoard[r][c] = 0;
						colorBoard[r][c] = blockColor;
					}
				}
			}
		}
		for (int c = board[0].length - 1; c >= 0; c--) {
			if (board[board.length - 1][c]) {
				test.start();
				timerStarted = true;
				spacebar = false;
				if(isOnEdge(0,0) == false) {
					centerR--;
				}
			}
		}
		//System.out.println("CenterR is: " + centerR);
		
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
				// System.out.println("Hello");
				for (int c = board[0].length - 1; c >= 0; c--) {
					board[r][c] = false;
					intBoard[r][c] = 2;
				}
 
				pushBlocksDown(r);
				linesCleared++;
 
			}
		}
 
		updateBoolArr();
		//System.out.println("lines cleared: " + linesCleared);
		return true;
 
		/*
		 * for (int r = board.length - 1; r > 0; r--) { int count = 0; for (int c = 0; c
		 * < board[r].length; c++) { if (intBoard[r][c] == 2) { count++; } }
		 */
	} // end of clear line method

	
	public static int getLinesCleared() {
		return linesCleared;
	}
	public boolean evaluateLevel() {
		if (linesCleared > 0) {
			totalLinesCleared += linesCleared;
			System.out.println("total lines cleared: " + totalLinesCleared);
			linesCleared = 0;
			return true;
		}
		return false;
	}
 
	public static void setClearedLines(int newLines) {
		linesCleared = newLines;
	}// end of getLinesCleared method
 
	public static int getTotalLinesCleared() {
		return totalLinesCleared;
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
	
	public void updateColorsUp() {
		for(int r = 1; r < colorBoard.length; r++) {
			for(int c = 0; c < colorBoard[0].length; c++) {
				if(intBoard[r][c] == 1 && intBoard[r-1][c] != 2) {
					colorBoard[r-1][c] = blockColor;
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

	public void moveUp() {
		//updateColorsUp();
		boolean isEdge = false;
		for (int r = 1; r < intBoard.length; r++) {
			for (int c = 0; c < intBoard[0].length; c++) {
				if (r == 0 && intBoard[r][c] == 1) {
					isEdge = true;
					// System.out.print(isEdge);
				} else if (r != 0) {
					if (intBoard[r -1][c] == 2 && intBoard[r][c] == 1) {
						isEdge = true;
					}
				}
			}
		}

		if (isEdge == false) {
			for (int r = 1; r < intBoard.length; r++) {
				for (int c = 0; c < intBoard[0].length; c++) {
					if (intBoard[r][c] == 1) {
						intBoard[r-1][c] = 1;
						colorBoard[r-1][c] = blockColor;
						intBoard[r][c] = 0;
						colorBoard[r][c] = "darkteal";
					}

				}
			}
			centerR--;
			// System.out.println("Current centerC: " + centerC);
		}
	}

	public void moveRight() { // has a couple bugs
		//updateColorsRight();
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
						colorBoard[r][c+1] = blockColor;
						intBoard[r][c] = 0;
						colorBoard[r][c] = "darkteal";
					}

				}
			}
			centerC++;
			// System.out.println("Current centerC: " + centerC);
		}

	}// end of moveright

	public void moveLeft() { // has a couple bugs
		//updateColorsLeft();
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
						colorBoard[r][c-1] = blockColor;
						intBoard[r][c] = 0;
						colorBoard[r][c] = "darkteal";
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
	
	public void findAllOnes() {
		int i = 0;
		for(int r = 0; r < intBoard.length; r++) {
			for(int c = 0; c < intBoard[r].length; c++) {
				if(intBoard[r][c] == 1) {
					oneCols[i] = c;
					y[i] =(r - centerR);
					oneRows[i] = r;
					i++;
				}
			}
		}
		Frame.one.changeColor("ghost");
		Frame.one.setY(landingRow*35+(y[0]*35)+35);
		Frame.one.setX(oneCols[0]*35+200);
		
		Frame.two.changeColor("ghost");
		Frame.two.setY(landingRow*35+(y[1]*35)+35);
		Frame.two.setX(oneCols[1]*35+200);
		
		Frame.three.changeColor("ghost");
		Frame.three.setY(landingRow*35+(y[2]*35)+35);
		Frame.three.setX(oneCols[2]*35+200);
		
		Frame.four.changeColor("ghost");
		Frame.four.setY(landingRow*35+(y[3]*35)+35);
		Frame.four.setX(oneCols[3]*35+200);
	}
	
	public void previewLand() {
		if(!queue.get(0).equals("O")) { //temp
		findAllOnes();
		for(int r = intBoard.length-2; r > 0; r--) {
			if(intBoard[r + y[0]][oneCols[0]] == 2 || intBoard[r + y[1]][oneCols[1]] == 2 ||
			   intBoard[r + y[2]][oneCols[2]] == 2 || intBoard[r + y[3]][oneCols[3]] == 2) {
				landingRow = r;
				System.out.println(landingRow);
			}
		}
		
	}}
	
	public void hardDrop() {
		for(int r = 0; r < intBoard.length; r++) {
			for(int c = 0; c < intBoard[0].length; c++) {
				if(isOnEdge(r, c) == false) {
					testFall();
				}
			}
		}
		setEmpty();
	}

	
	public boolean isOnEdge(int row, int col) {
		for(int r = intBoard.length-1 - row; r > -1; r--) {
			for(int c = intBoard[0].length-1 - col; c > -1; c--) {
				if(r != 19) {
					if(intBoard[r][c] == 1 && intBoard[r+1][c] == 2) {
						return true;
					}
				}else if(r == 19 && intBoard[r][c] == 1) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	public void times() {
		if(keyDown == true && timerStarted == true) {
			if(rotateKey == false) {
				delayTimes = 100;
			}else {
				delayTimes = 350;
			}
			
			test.restart();
		}
	}
	
	ActionListener delay = new ActionListener() {
		 
		@Override
		public void actionPerformed(ActionEvent e) {
			setEmpty();			
		}
		
	};
	
	Timer test = new Timer(delayTimes, delay);
}



