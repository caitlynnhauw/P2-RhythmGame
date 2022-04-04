

public class O extends Blocks{
	private int x, y;
	private String color = "";
	public O(int x, int y, String color) {
		super(x, y, color);
		Blocks topLeft = new Blocks (x, y, color);
		Blocks topRight = new Blocks(x+35, y, color);
		Blocks bottomLeft = new Blocks(x, y+35, color);
		Blocks bottomRight = new Blocks(x+35, y+35, color);
		
		Blocks[][] OShape = new Blocks[2][2];
		OShape[0][0] = topLeft; 
		OShape[0][1] = topRight; 
		OShape[1][0] = bottomLeft;
		OShape[1][1] = bottomRight; 
	}
}
