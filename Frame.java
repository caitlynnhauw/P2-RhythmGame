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
	
	Background 	b = new Background(0, 0);
	Bird bird = new Bird(0, 0);
	//Music m = new Music("Justin_Bieber_ft_Ludacris_-_Baby__NaijaGreen.Com__.wav", false);
	Boolean[][] tetris = new Boolean[20][10];
	public void paint(Graphics g) {
		super.paintComponent(g);
		//m.run();
		for(int row = 0; row < tetris.length; row++) {
			for(int col = 0; col < tetris[row].length; col++) {
				tetris[row][col] = true;
			}
		}
		tetris[7][2] = false;
		tetris[4][6] = false;
		tetris[9][9] = false;
		for(int row = 0; row < tetris.length; row++) {
			for(int col = 0; col < tetris[row].length; col++) {
				if(tetris[row][col]) {
					g.setColor(Color.green);
					}else {
						g.setColor(Color.red);
					}
				g.fillRect((col*40)+100, (row*40)+10, 35, 35);
			}
		}
		
		
		//ask the objects to paint themselves
		b.paint(g);
	
		bird.paint(g);
		
		int red =((int)(Math.random() * 255));
		int green =((int)(Math.random() * 255));
		int blue =((int)(Math.random() * 255));
		g.setColor(new Color(red, green, blue));
		
		//g.fillRect(100, 100, 50, 50);
		
		
		
	}
	
	
	public static void main(String[] arg) {
		Frame f = new Frame();
	}
	
	public Frame() {
		JFrame f = new JFrame("Tetris");
		f.setSize(new Dimension(700, 900));
		f.setBackground(Color.blue);
		f.add(this);
		f.setResizable(false);
		f.setLayout(new GridLayout(1,2));
		f.addMouseListener(this);
		f.addKeyListener(this);
		Timer t = new Timer(16, this);
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