import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;
import java.util.Random;

public class Blocks {
	// add location attributes
	private Image img;
	private AffineTransform tx;
	private int x, y, vx;
	private int vy = 0;
	private int width, height;
	private int random;
	Random rnd = new Random(1234);
	Rectangle hitbox;
	String color = null; // green, blue

	public Blocks(int x, int y, String color) {

		height = 24;
		width = 91;
		this.x = x;
		this.y = y;
		this.color = color;
		img = getImage("/ASSets/" + color + "_block.png"); // load the image for Tree
		tx = AffineTransform.getTranslateInstance(x, y);
		init(x, y);
		// initialize the location of the image
		// use your variables
	}

	public void paint(Graphics g) {
		// these are the 2 lines of code needed draw an image on the screen
		Graphics2D g2 = (Graphics2D) g;

		// call update to update the actually picture location
		update();

		g2.drawImage(img, tx, null);

	}

	/* update the picture variable location */
	private void update() {

		random = rnd.nextInt(420) + 0;

	}

	public void changeColor(String color) {
		img = getImage("/ASSets/" + color + "_block.png");
	}

	public static void nextMoves(int i, Blocks[][] data) {
		switch(Board.queue.get(i)) {
		case "O":
			data[i * 4][3].changeColor("blue");
			data[i * 4][4].changeColor("blue");
			data[i * 4 - 1][3].changeColor("blue");
			data[i * 4 - 1][4].changeColor("blue");
			break;
		case "L":
			data[i*4-1][4].changeColor("purple");
			data[i*4][3].changeColor("purple");
			data[i*4][4].changeColor("purple");
			data[i*4][2].changeColor("purple");
			break;
		case "J":
			data[i*4-1][2].changeColor("teal");
			data[i*4][3].changeColor("teal");
			data[i*4][4].changeColor("teal");
			data[i*4][2].changeColor("teal");
			break;
		case "T":
			data[i*4-1][3].changeColor("navy");
			data[i*4][3].changeColor("navy");
			data[i*4][4].changeColor("navy");
			data[i*4][2].changeColor("navy");
			break;
		case "S":
			data[i*4-1][4].changeColor("sky");
			data[i*4-1][3].changeColor("sky");
			data[i*4][3].changeColor("sky");
			data[i*4][2].changeColor("sky");
			break;
		case "Z":
			data[i*4-1][2].changeColor("vibrantblue");
			data[i*4-1][3].changeColor("vibrantblue");
			data[i*4][3].changeColor("vibrantblue");
			data[i*4][4].changeColor("vibrantblue");
			break;
		case "I":
			data[i*4][2].changeColor("violet");
			data[i*4][3].changeColor("violet");
			data[i*4][4].changeColor("violet");
			data[i*4][5].changeColor("violet");
			break;
		}
	}

	public void testJump() {
		y += 35;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	private void init(double a, double b) {
		tx.setToTranslation(a, b);
		tx.scale(1, 1);
	}

	private Image getImage(String path) {
		Image tempImage = null;
		try {
			URL imageURL = Blocks.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	}
}
