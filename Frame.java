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
 
	Background b = new Background(-120, 0);
	Bird bird = new Bird(-50, 0);
 
	Blocks blue = new Blocks(175, 70, "blue");
	Blocks navy = new Blocks(100, 135, "navy");
	Blocks purple = new Blocks(135, 100, "purple");
	Blocks btest = new Blocks(0, 0, "sky");
	
 
	Board best = new Board();
	// Music m = new Music("Justin_Bieber_ft_Ludacris_-_Baby__NaijaGreen.Com__.wav",
	// false);
	Blocks[][] tetris = new Blocks[20][10];
	Blocks[][] nextBlocks = new Blocks[17][6];
	Blocks[][] currBlock = new Blocks[4][4];
	public void paint(Graphics g) {
		super.paintComponent(g);
		// m.run();
		// paint the board contents
		b.paint(g);
		for(Blocks[] r : tetris) {
			for(Blocks c : r) {
				c.paint(g);
			}
		}
		
		
		//test out creating the board using the Board class
		for(int r = 0; r < best.board.length; r ++) {
			for(int c = 0; c < best.board[r].length; c++) {
				if(best.intBoard[r][c] >=1) {
					Blocks temp = new Blocks((c*35)+200, (r*35)+70, best.colorBoard[r][c]);
					temp.paint(g);
					tetris[r][c] = temp;
					if(best.intBoard[r][c] == 1) {
						best.colorBoard[r][c] = best.blockColor;
					}
				}else {
					Blocks temp = new Blocks((c*35)+200, (r*35)+70, "darkteal");
					
					tetris[r][c] = temp;
				}
			}
		}
		
		for(int r = 0; r < nextBlocks.length; r++) {
			for(int c = 0; c < nextBlocks[r].length; c++) {
				nextBlocks[r][c].paint(g);
				nextBlocks[r][c].changeColor("darkteal");
			}
		}
		for(int i = 1; i < best.queue.size(); i++) {
			
			if(best.queue.get(i).equals("O")) {
				nextBlocks[i*4][2].changeColor("blue");
				nextBlocks[i*4][3].changeColor("blue");
				nextBlocks[i*4-1][2].changeColor("blue");
				nextBlocks[i*4-1][3].changeColor("blue");
			}
			if(best.queue.get(i).equals("L")) {
				nextBlocks[i*4-1][4].changeColor("purple");
				nextBlocks[i*4][3].changeColor("purple");
				nextBlocks[i*4][4].changeColor("purple");
				nextBlocks[i*4][2].changeColor("purple");
			}
			if(best.queue.get(i).equals("J")) {
				nextBlocks[i*4-1][2].changeColor("teal");
				nextBlocks[i*4][3].changeColor("teal");
				nextBlocks[i*4][4].changeColor("teal");
				nextBlocks[i*4][2].changeColor("teal");
			}
			if(best.queue.get(i).equals("T")) {
				nextBlocks[i*4-1][3].changeColor("navy");
				nextBlocks[i*4][3].changeColor("navy");
				nextBlocks[i*4][4].changeColor("navy");
				nextBlocks[i*4][2].changeColor("navy");
			}
			if(best.queue.get(i).equals("S")) {
				nextBlocks[i*4-1][4].changeColor("sky");
				nextBlocks[i*4-1][3].changeColor("sky");
				nextBlocks[i*4][3].changeColor("sky");
				nextBlocks[i*4][2].changeColor("sky");
			}
			if(best.queue.get(i).equals("Z")) {
				nextBlocks[i*4-1][2].changeColor("vibrantblue");
				nextBlocks[i*4-1][3].changeColor("vibrantblue");
				nextBlocks[i*4][3].changeColor("vibrantblue");
				nextBlocks[i*4][4].changeColor("vibrantblue");
			}
			if(best.queue.get(i).equals("I")) {
				nextBlocks[i*4-2][3].changeColor("violet");
				nextBlocks[i*4-1][3].changeColor("violet");
				nextBlocks[i*4][3].changeColor("violet");
				nextBlocks[i*4+1][3].changeColor("violet");
			}
		}
		for(int r = 0; r < currBlock.length; r++) {
			for(int c = 0; c < currBlock[r].length; c++) {
				currBlock[r][c].changeColor("darkteal");
				currBlock[r][c].paint(g);
			}
		}
		switch(best.queue.get(0)) {
		case "O":
			currBlock[1][1].changeColor("blue");
			currBlock[1][2].changeColor("blue");
			currBlock[2][1].changeColor("blue");
			currBlock[2][2].changeColor("blue");

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
				Blocks temp = new Blocks((c * 35) + 200, (r * 35) + 70, "navy");
				tetris[r][c] = temp;
			}
		}
		for(int r = 0; r < 17; r++) {
			for(int c = 0; c < 6; c++) {
					Blocks temp = new Blocks(c*35+600, (r*35)+100, "darkteal");
					nextBlocks[r][c] = temp;
			}
		}
		
		for(int r = 0; r < currBlock.length; r++) {
			for(int c = 0; c < currBlock[r].length; c++) {
				Blocks temp = new Blocks(c*35+35, (r*35)+240, "darkteal");
				currBlock[r][c] = temp;
			}
		}
		best.queue.add("S");
		best.queue.add("L");
		best.queue.add("T");
		best.queue.add("O");
		best.spawn("test");
		//best.toString();
 
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
		//t2.setRepeats(true);
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
		if(best.gameOver == false) {
	    best.testFall();
		}
	}
	
	ActionListener actions = new ActionListener() {
 
		@Override
		public void actionPerformed(ActionEvent e) {
			best.clearLine();
			repaint();
			
			if(best.lineClearing) {
				
			}
		}
		
	};
 
	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		//System.out.println(arg0.getKeyCode());
		if (arg0.getKeyCode() == 40) {//Down
			best.testFall();
		}
		if (arg0.getKeyCode() == 79) {//O
			best.spawn("O");
		}
		if (arg0.getKeyCode() == 83) {//S
			best.spawn("S");
		}
		if(arg0.getKeyCode() == 76) {//L
			best.spawn("L");
		}
		if(arg0.getKeyCode() == 32) {//Spacebar
			best.rotate(best.centerR, best.centerC); //Rotate will work for all except I piece
		}
		if(arg0.getKeyCode() == 74) {//J
			best.spawn("J");
		}
		if(arg0.getKeyCode() == 84) {//T
			best.spawn("T");
		}
		if(arg0.getKeyCode() == 90) {//Z
			best.spawn("Z");
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
