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

	Background b = new Background(175, 70);
	Bird bird = new Bird(-50, 0);
	Blocks blue = new Blocks(175, 70, "blue");
	Blocks navy = new Blocks(100, 135, "navy");
	Blocks purple = new Blocks(135, 100, "purple");
	Blocks sky = new Blocks(135, 135, "sky");
	Board best = new Board();
	// Music m = new Music("Justin_Bieber_ft_Ludacris_-_Baby__NaijaGreen.Com__.wav",
	// false);
	Blocks[][] tetris = new Blocks[20][10];

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
		// ask the objects to paint themselves

		bird.paint(g);

		blue.paint(g);
		navy.paint(g);
		purple.paint(g);
		sky.paint(g);

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
				Blocks temp = new Blocks((c * 35) + 175, (r * 35) + 70, "navy");
				tetris[r][c] = temp;
			}
		}
		best.spawn("S");
		best.toString();

		JFrame f = new JFrame("Tetris");
		f.setSize(new Dimension(700, 900));
		f.setBackground(Color.blue);
		f.add(this);
		f.setResizable(false);
		f.setLayout(new GridLayout(1, 2));
		f.addMouseListener(this);
		f.addKeyListener(this);
		Timer t = new Timer(16, this);
		t.start();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		blue.squareShape();
	}

	public void squareShape() {
		Blocks[][] OShape = new Blocks[2][2];
		OShape[0][0] = new Blocks(blue.getX() + 35, blue.getY(), "blue");
		OShape[0][1] = navy;
		OShape[1][0] = purple;
		OShape[1][1] = sky;

		for (int row = 0; row < OShape.length; row++) {
			for (int col = 0; col < OShape[row].length; col++) {
				if (OShape[row][col] != null) {

				}
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

	Timer t = new Timer((int) 461.538461538, this);

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		repaint();
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println(arg0.getKeyCode());
		if (arg0.getKeyCode() == 40) {
			blue.testJump();
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
