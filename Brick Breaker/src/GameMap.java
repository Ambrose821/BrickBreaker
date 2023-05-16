import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class GameMap {
	public int map[][];
	public int brickWidth;
	public int brickHeight;
	public GameMap(int row, int col) {
		map = new int[row][col];
		for(int i = 0;i <map.length; i++) {
			for(int j = 0; j< map[0].length; j++) {
				map[i][j]=1;
			}
			
		}
		brickWidth = 540/col;
		brickHeight = 150/row;
	}
	
	public void draw(Graphics2D graphics) {
		for(int i = 0;i <map.length; i++) {
			for(int j = 0; j< map[0].length; j++) {
				if(map[i][j]> 0) {
					graphics.setColor(Color.white);
					graphics.fillRect(j*brickWidth +80, i*brickHeight+50, brickWidth, brickHeight);
					graphics.setStroke(new BasicStroke(3));
					graphics.setColor(Color.GRAY);
					graphics.drawRect(j*brickWidth +80, i*brickHeight+50, brickWidth, brickHeight);
					
				}
			}
			
		}
	}
	
	
	
}
