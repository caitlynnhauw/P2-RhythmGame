import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;
import java.util.Random;

public class Blocks {
	//add location attributes
		private Image img; 	
		private AffineTransform tx;
		private int x, y, vx, vy;
		private int width, height;
		private int random;
		Random rnd = new Random(1234);
		Rectangle hitbox;
		String type = null; //green, blue
		public Blocks(int x, int y, String type) {
				
			height = 24;
			width = 91;
			this.x = x;
			this.y = y;
			this.type = type;
			img = getImage("/ASSets/" + type + "_block.png"); //load the image for Tree
			tx = AffineTransform.getTranslateInstance(x, y );
			init(x, y); 
			//initialize the location of the image
										//use your variables
		}

		
		public void paint(Graphics g) {
			//these are the 2 lines of code needed draw an image on the screen
			Graphics2D g2 = (Graphics2D) g;
			
			
			//call update to update the actually picture location
			update();
			
			
			
			
			g2.drawImage(img, tx, null);
			
			

		}
		/* update the picture variable location */
		private void update() {

			y+= vy;
			random = rnd.nextInt(420)+0;
	
			
		}
		public void squareShape() {
			Blocks topLeft = new Blocks (x, y, type);
			Blocks topRight = new Blocks(x+35, y, type);
			Blocks bottomLeft = new Blocks(x, y+35, type);
			Blocks bottomRight = new Blocks(x+35, y+35, type);
			Blocks[][] OShape = new Blocks[2][2];
			OShape[0][0] = topLeft; 
			OShape[0][1] = topRight; 
			OShape[1][0] = bottomLeft;
			OShape[1][1] = bottomRight; 

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
			this.x=x;
		}
		public void setY(int y) {
			this.y=y;
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
