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

	Blocks blue = new Blocks(175, 70, "blue");
	Blocks navy = new Blocks(100, 135, "navy");
	Blocks purple = new Blocks(135, 100, "purple");
	Blocks btest = new Blocks(0, 0, "sky");

	Board best = new Board();

	int score = 0;
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
		for (Blocks[] r : tetris) {
			for (Blocks c : r) {
				c.paint(g);
			}
		}

		String s = Integer.toString(score);
		g.setColor(Color.white);
		g.setFont(new Font("Arial Black", Font.BOLD, 60));
		g.drawString(s, 50, 500);

		// test out creating the board using the Board class
		for (int r = 0; r < best.board.length; r++) {
			for (int c = 0; c < best.board[r].length; c++) {
				if (best.intBoard[r][c] >= 1) {
					Blocks temp = new Blocks((c * 35) + 200, (r * 35) + 70, best.colorBoard[r][c]);
					temp.paint(g);
					tetris[r][c] = temp;
					if (best.intBoard[r][c] == 1) {
						best.colorBoard[r][c] = best.blockColor;
					}
				} else {
					Blocks temp = new Blocks((c * 35) + 200, (r * 35) + 70, "darkteal");

					tetris[r][c] = temp;
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
		btest.paint(g);
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
		Timer t = new Timer(1000, this);
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
					score += 100;
					best.linesCleared = 0;
					break;
			
				case 2:
					score += 200;
					best.linesCleared = 0;
					break;
		
				case 3:
					score += 300;
					best.linesCleared = 0;
					break;
		
				case 4:
					score += 400;
					best.linesCleared = 0;
					break;
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

	ActionListener actions=new ActionListener(){

	@Override public void actionPerformed(ActionEvent e){best.clearLine();repaint();

	scoring();

	}

	};

	ActionListener falling=new ActionListener(){

	@Override public void actionPerformed(ActionEvent e){if(best.fallKey==true){best.testFall();}if(best.leftKey==true){best.moveLeft();}if(best.rightKey==true){best.moveRight();}}

	};

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println(arg0.getKeyCode());
		if (arg0.getKeyCode() == 40) {// Down
			best.fallKey = true;
		}
		if (arg0.getKeyCode() == 79) {// O
			best.spawn("O");
		}
		if (arg0.getKeyCode() == 83) {// S
			best.spawn("S");
		}
		if (arg0.getKeyCode() == 76) {// L
			best.spawn("L");
		}
		if (arg0.getKeyCode() == 74) {// J
			best.spawn("J");
		}
		if (arg0.getKeyCode() == 84) {// T
			best.spawn("T");
		}
		if (arg0.getKeyCode() == 90) {// Z
			best.spawn("Z");
		}
		if (arg0.getKeyCode() == 32) {// Spacebar
			best.fallKey = true;
		}
		if (arg0.getKeyCode() == 38) {// Up Arrow
			best.rotate(best.centerR, best.centerC);
		}
		if (arg0.getKeyCode() == 39) {// ->
			best.rightKey = true;
		}
		if (arg0.getKeyCode() == 37) {// <-
			best.leftKey = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getKeyCode() == 40) {// Down
			best.fallKey = false;
		}
		if (arg0.getKeyCode() == 39) {
			best.rightKey = false;
		}
		if (arg0.getKeyCode() == 37) {
			best.leftKey = false;
		}
		if (arg0.getKeyCode() == 32) {// Spacebar
			best.fallKey = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

}