import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Frame extends JPanel implements ActionListener, MouseListener, KeyListener {

	Background b = new Background(-120, 0);
	Bird bird = new Bird(-50, 0);
	Effects stars = new Effects(0, 0, "shootingstars", ".gif");

	static Blocks one = new Blocks(0, 0, "teal");
	static Blocks two = new Blocks(0, 0, "teal");
	static Blocks three = new Blocks(0, 0, "teal");
	static Blocks four = new Blocks(0, 0, "teal");

	Board best = new Board();

	int score = 0;
	int level = 1;
	int currentTime = 1000;
	// Music m = new Music("Justin_Bieber_ft_Ludacris_-_Baby__NaijaGreen.Com__.wav",
	// false);
	Blocks[][] tetris = new Blocks[20][10];
	Blocks[][] nextBlocks = new Blocks[17][6];
	Blocks[][] currBlock = new Blocks[4][5];

	public void paint(Graphics g) {
		super.paintComponent(g);
		// m.run();
		// paint the board contents
		b.paint(g);
		stars.paint(g);
		for (Blocks[] r : tetris) {
			for (Blocks c : r) {
				c.paint(g);
			}
		}

		String s = Integer.toString(score);
		g.setColor(Color.white);
		g.setFont(new Font("Arial Black", Font.BOLD, 60));
		g.drawString(s, 50, 500);
		String l = "Level: " + Integer.toString(level);
		g.setColor(Color.white);
		g.setFont(new Font("Arial Black", Font.BOLD, 30));
		g.drawString(l, 35, 700);


		// test out creating the board using the Board class
		/*for (int r = 0; r < best.board.length; r++) {
			for (int c = 0; c < best.board[r].length; c++) {
				if (best.intBoard[r][c] >= 1) {
					Blocks temp = new Blocks((c * 35) + 200, (r * 35) + 70, best.colorBoard[r][c]);
					temp.paint(g);
					tetris[r][c] = temp;
					if (best.intBoard[r][c] == 1) {
						best.colorBoard[r][c] = best.blockColor;
					}
				} else {
					if(!best.board[r][c]) {
						Blocks temp = new Blocks((c * 35) + 200, (r * 35) + 70, "darkteal");
					
					tetris[r][c] = temp;
					}
				}
			}
		}
		*/
		
		for(int r = 0; r < best.intBoard.length; r++) {
			for(int c = 0; c < best.intBoard[r].length; c++) {
				if(best.intBoard[r][c] == 0) {
					tetris[r][c].changeColor("darkteal");
				}
				if(best.intBoard[r][c] == 1) {
					tetris[r][c].changeColor(best.blockColor);
				}
				if(best.intBoard[r][c] == 2) {
					tetris[r][c].changeColor(best.colorBoard[r][c]);
				}
			}
		}

		for (int r = 0; r < nextBlocks.length; r++) {
			for (int c = 1; c < nextBlocks[r].length; c++) {
				nextBlocks[r][c].paint(g);
				nextBlocks[r][c].changeColor("darkteal");
			}
		}
		for (int i = 1; i < Board.queue.size(); i++) {

			Blocks.nextMoves(i, nextBlocks);
		}
		for (int r = 0; r < currBlock.length; r++) {
			for (int c = 0; c < currBlock[r].length; c++) {
				currBlock[r][c].paint(g);
				currBlock[r][c].changeColor("darkteal");
			}
		}
		switch (Board.queue.get(0)) {
		case "O":
			currBlock[1][1].changeColor("blue");
			currBlock[1][2].changeColor("blue");
			currBlock[2][1].changeColor("blue");
			currBlock[2][2].changeColor("blue");
			break;
		case "L":
			currBlock[2][1].changeColor("purple");
			currBlock[1][3].changeColor("purple");
			currBlock[2][2].changeColor("purple");
			currBlock[2][3].changeColor("purple");
			break;
		case "J":
			currBlock[1][1].changeColor("teal");
			currBlock[2][3].changeColor("teal");
			currBlock[2][1].changeColor("teal");
			currBlock[2][2].changeColor("teal");
			break;
		case "T":
			currBlock[2][1].changeColor("navy");
			currBlock[1][2].changeColor("navy");
			currBlock[2][2].changeColor("navy");
			currBlock[2][3].changeColor("navy");
			break;
		case "S":
			currBlock[1][2].changeColor("sky");
			currBlock[1][3].changeColor("sky");
			currBlock[2][2].changeColor("sky");
			currBlock[2][1].changeColor("sky");
			break;
		case "Z":
			currBlock[1][1].changeColor("vibrantblue");
			currBlock[1][2].changeColor("vibrantblue");
			currBlock[2][2].changeColor("vibrantblue");
			currBlock[2][3].changeColor("vibrantblue");
			break;
		case "I":
			currBlock[2][0].changeColor("violet");
			currBlock[2][1].changeColor("violet");
			currBlock[2][2].changeColor("violet");
			currBlock[2][3].changeColor("violet");
			break;
		}

		best.update();
		// ask the objects to paint themselves

		bird.paint(g);
		one.paint(g);
		two.paint(g);
		three.paint(g);
		four.paint(g);
		
		// for(int r = 0; r < test.length; )
		int red = ((int) (Math.random() * 255));
		int green = ((int) (Math.random() * 255));
		int blue = ((int) (Math.random() * 255));
		g.setColor(new Color(red, green, blue));

		// g.fillRect(100, 100, 50, 50);

	}

	public static void main(String[] arg) {
		Frame f = new Frame();
	}

	public Frame() {

		for (int r = 0; r < tetris.length; r++) {
			for (int c = 0; c < tetris[r].length; c++) {
				Blocks temp = new Blocks((c * 35) + 200, (r * 35) + 70, "navy");
				tetris[r][c] = temp;
			}
		}
		for (int r = 0; r < 17; r++) {
			for (int c = 0; c < 6; c++) {
				Blocks temp = new Blocks(c * 35 + 600, (r * 35) + 100, "darkteal");
				nextBlocks[r][c] = temp;
			}
		}

		for (int r = 0; r < currBlock.length; r++) {
			for (int c = 0; c < currBlock[r].length; c++) {
				Blocks temp = new Blocks(c * 35, (r * 35) + 240, "darkteal");
				currBlock[r][c] = temp;
			}
		}
		best.queue.add("J");
		best.queue.add("L");
		best.queue.add("T");
		best.queue.add("O");
		best.spawn(best.queue.get(0));
		// best.toString();

		JFrame f = new JFrame("Tetris");
		f.setSize(new Dimension(900, 900));
		f.setBackground(Color.blue);
		f.add(this);
		f.setResizable(false);
		f.setLayout(new GridLayout(1, 2));
		f.addMouseListener(this);
		f.addKeyListener(this);
		Timer t = new Timer(currentTime, this);
		t.start();
		Timer t2 = new Timer(1, actions);
		t2.start();
		Timer keyDown = new Timer(100, falling);
		keyDown.start();
		// t2.setRepeats(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}

	public void scoring() {
		if(best.clearLine()){
			switch(Board.getLinesCleared()){
				case 1:
					score += (100 * level);
					best.evaluateLevel();
					break;
			
				case 2:
					score += (200 * level);
					best.evaluateLevel();
					break;
		
				case 3:
					score += (300 * level);
					best.evaluateLevel();
					break;
		
				case 4:
					score += (400 * level);
					best.evaluateLevel();
					break;
			}
		}
	}
	
	public void levels() {
		if (best.clearLine()) {
			if (Board.getTotalLinesCleared() >= 20 && Board.getTotalLinesCleared() < 30) {
				level = 2;
				currentTime -= 50;
			} else if (Board.getTotalLinesCleared() >= 30 && Board.getTotalLinesCleared() < 60) {
				level = 3;
				currentTime -= 50;
			} else if (Board.getTotalLinesCleared() >= 60) {
				level = 4;
				currentTime -= 50;
		}
	}
	}




	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if (best.gameOver == false) {
			best.testFall();
		
		}
	}

	ActionListener actions = new ActionListener(){

	@Override public void actionPerformed(ActionEvent e){
		best.clearLine();
		if(best.isOnEdge(0, 0) == false) {
			best.test.restart();
			best.test.stop();
		}
		repaint();
		scoring();
		levels();
		}
	};

	ActionListener falling = new ActionListener(){

		@Override public void actionPerformed(ActionEvent e){
			if(best.fallKey==true){
				best.testFall();
			}
			if(best.leftKey==true){
				best.moveLeft();
			}
			if(best.rightKey==true){
				best.moveRight();
			}
		}
	};

	@Override
	public void keyPressed(KeyEvent arg0) {
		if(best.gameOver == false) {
		// TODO Auto-generated method stub
		//System.out.println(arg0.getKeyCode());
		if (arg0.getKeyCode() == 40) {// Down
			best.fallKey = true;
		}
		if (arg0.getKeyCode() == 32) {// Spacebar
			best.spaceBar = true;
		}
		if (arg0.getKeyCode() == 38) {// Up Arrow
			best.rotate(best.centerR, best.centerC);
			best.keyDown = true;
		}
		if (arg0.getKeyCode() == 39) {// ->
			best.rightKey = true;
			best.times();
		}
		if (arg0.getKeyCode() == 37) {// <-
			best.leftKey = true;
			best.times();
		}
	}}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getKeyCode() == 40) {// Down
			best.fallKey = false;
			best.keyDown = false;
		}
		if (arg0.getKeyCode() == 39) {
			best.rightKey = false;
			best.keyDown = false;
		}
		if (arg0.getKeyCode() == 37) {
			best.leftKey = false;
			best.keyDown = false;
		}
		if (arg0.getKeyCode() == 32) {// Spacebar
			best.fallKey = false;
			best.keyDown = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

}