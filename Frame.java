import java.awt.Color;
import java.awt.Dimension;
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

	Background b = new Background(0, 0);
	Bird bird = new Bird(-50, 0);

	Blocks blue = new Blocks(175, 70, "blue");
	Blocks navy = new Blocks(100, 135, "navy");
	Blocks purple = new Blocks(135, 100, "purple");
	Blocks sky = new Blocks(135, 135, "sky");


	Board best = new Board();
	// Music m = new Music("Justin_Bieber_ft_Ludacris_-_Baby__NaijaGreen.Com__.wav",
	// false);
	Blocks[][] tetris = new Blocks[20][10];
	String color = "blue";
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
		
		//test out creating the board using the Board class
		for(int r = 0; r < best.board.length; r ++) {
			for(int c = 0; c < best.board[r].length; c++) {
				if(best.intBoard[r][c] >=1) {
					Blocks temp = new Blocks((c*35)+175, (r*35)+70, best.colorBoard[r][c]);
					temp.paint(g);
					tetris[r][c] = temp;
					if(best.intBoard[r][c] == 1) {
						best.colorBoard[r][c] = best.blockColor;
					}
				}else {
					Blocks temp = new Blocks((c*35)+175, (r*35)+70, "navy");
					tetris[r][c] = temp;
				}
			}
		}
		best.update();
		// ask the objects to paint themselves

		//bird.paint(g);
	
		// for(int r = 0; r < test.length; )
		int red = ((int) (Math.random() * 255));
		int green = ((int) (Math.random() * 255));
		int blue = ((int) (Math.random() * 255));
		g.setColor(new Color(red, green, blue));

		// g.fillRect(100, 100, 50, 50);
		
		for(int i = 0; i < 1000; i++) {
			repaint();
		}
	}

	public static void main(String[] arg) {
		Frame f = new Frame();
	}

	public Frame() {

		for (int r = 0; r < tetris.length; r++) {
			for (int c = 0; c < tetris[r].length; c++) {
				Blocks temp = new Blocks((c * 35) + 175, (r * 35) + 70, "navy");
				tetris[r][c] = temp;
			}
		}


		best.spawn("L");
		best.toString();

		JFrame f = new JFrame("Tetris");
		f.setSize(new Dimension(700, 900));
		f.setBackground(Color.blue);
		f.add(this);
		f.setResizable(false);
		f.setLayout(new GridLayout(1, 2));
		f.addMouseListener(this);
		f.addKeyListener(this);
		Timer t = new Timer(1000, this);
		t.start();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
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
		best.testFall();
		best.sec++; 
		
		if(best.isRowFilled == true) {
			best.sec++;
	
		}
		
		if(best.sec == 2) {
			best.clearLine();
			best.sec = 0;
			best.isRowFilled = false; 
		}
		
		
		repaint();
	}


	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println(arg0.getKeyCode());
		if (arg0.getKeyCode() == 40) {//Down
			best.testFall();
		}
		if (arg0.getKeyCode() == 79) {//O
			best.spawn("O");
			color = "blue";
		}
		if (arg0.getKeyCode() == 83) {//S
			best.spawn("S");
			color = "sky";
		}
		if(arg0.getKeyCode() == 76) {//L
			best.spawn("L");
			color = "blue";
		}
		if(arg0.getKeyCode() == 32) {//Spacebar
			best.rotate(best.centerR, best.centerC); //Rotate will work for all except I piece
		}
		if(arg0.getKeyCode() == 74) {//J
			best.spawn("J");
			color = "blue";
		}
		if(arg0.getKeyCode() == 84) {//T
			best.spawn("T");
			color = "blue";
		}
		if(arg0.getKeyCode() == 90) {//Z
			best.spawn("Z");
			color = "blue";
		}
		if(arg0.getKeyCode() == 39) {
			best.moveRight();
		}
		if(arg0.getKeyCode() == 37) {
			best.moveLeft();
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

}
