import java.awt.event.KeyListener;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;

public class Play extends JPanel implements KeyListener, ActionListener {
	

	private static final long serialVersionUID = 1L;
	private boolean play = false;
	private int totalBricks = 21;
	
	private GameMap bricks;
	
	
	private Timer time;
	private int score = 0;
	private int setTimeOut =8;
	
	private int sliderPosX =310;
	
	private int ballXcoord = 120;
	private int ballYcoord = 350;
	private int ballXvector = 3;
	
	private int ballYvector = 2;
	
	public Play() {
		
		
		
		
		bricks = new GameMap(3,7);
	
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		time = new Timer(8, this);
		
		time.start();
		}
	
	
	
	public void paint(Graphics graphics) {
	//BackDrop
		graphics.setColor(Color.GRAY);
		graphics.fillRect(1, 1, 692, 592);
		
		
		
		//bricks
		bricks.draw((Graphics2D) graphics);
		
		//borders
		graphics.setColor(Color.black);
		int borderWidth = 3;
		int width = getWidth() - borderWidth;
		int height = getHeight() - borderWidth;
		graphics.fillRect(0, 0, borderWidth, height);
		graphics.fillRect(0, 0, width, borderWidth);
		graphics.fillRect(width - borderWidth+3, 0, borderWidth, height);
		graphics.fillRect(0, height - borderWidth+4, width, borderWidth);
		
		//Slider
		graphics.setColor(Color.green);
		graphics.fillRect(sliderPosX,550,100,8);
		
				
		//ball
		graphics.setColor(Color.white);
		graphics.fillOval(ballXcoord, ballYcoord, 20, 20);
		graphics.dispose();
		
		//bricks

		
		
		
		
		graphics.setColor(Color.blue);
		graphics.fillRect(sliderPosX, 100, 500, 600);
	}
	@Override
	public void keyReleased(KeyEvent e) {
	}
	@Override
	public void keyTyped(KeyEvent e) {
	} 
	@Override
	public void actionPerformed(ActionEvent e) {
		Rectangle rect = new Rectangle(ballXcoord,ballYcoord,20,20);
		
		Rectangle slider = new Rectangle(sliderPosX,550,100,8);
		
		time.start();
		if(play) {
			if(rect.intersects(slider)){
				
				
			
				
				int sliderX = sliderPosX;
				int sliderY =550;
				int sliderWidth = 100;
				int sliderHeight = 8;
				
				int sliderVertOneX = (int) slider.getMinX();
				int sliderVertexOneY = (int) slider.getMinY();
				
				int sliderVertTwoX = (int) slider.getMaxX();
				int sliderVertexTwoY = (int) slider.getMaxY();
				
				if(ballXcoord + 29< slider.x || ballXcoord+1 >= slider.x + slider.width) {
					ballXvector = -(ballXvector);
					ballYvector = -ballYvector;
					
				}
				
				if((ballXcoord >= sliderVertOneX && ballXcoord < sliderVertOneX+30)) {
					if(ballXvector>0) {
					ballYvector = -ballYvector;
					ballXvector = -(2*ballXvector);
					}
					else {
						ballYvector = -ballYvector;
						ballXvector = (2*ballXvector);
					}
				}
				
			
				
				else if((ballXcoord <= sliderVertTwoX && ballXcoord > sliderVertTwoX-25)) {
					if(ballXvector>0) {
					ballYvector = -ballYvector;
					ballXvector = (2*ballXvector);
					}
					else {
						ballYvector = -ballYvector;
						ballXvector = -(2*ballXvector);
					}
				}
				
				else {
					ballYvector = -ballYvector;
					while(Math.abs(ballXvector)>1) {
						ballXvector= ballXvector/2;
						
						}
				}
				
			
				
				
				
			}
			boolean multiHit = false; //Account for the double direction change when a ball hits 2 bricks at once
			int hitCounter =0;
			for(int i = 0; i<bricks.map.length; i++) {
				for(int j =0; j<bricks.map[0].length; j++) {
					
					int brickX = j*bricks.brickWidth+80;
					int brickY = i*bricks.brickHeight+50;
					
					int brickW = bricks.brickWidth;
					int brickH = bricks.brickHeight;
					
					
					Rectangle brickRec = new Rectangle(brickX,brickY,brickW,brickH);
					if(rect.intersects(brickRec) && bricks.map[i][j] != 0) {
						bricks.map[i][j] =0;
						++hitCounter;
						if(hitCounter>=2) {
							multiHit = true;
							hitCounter =0;
						}
						
						totalBricks--;
						score +=5;
						if(score ==105)
						{
							System.out.println("You Win");
							System.exit(0);
						}
						
						if(ballXcoord + 19<= brickRec.x || ballXcoord+1 >= brickRec.x +brickRec.width) {
							ballXvector = -ballXvector;
						}
						else {
							ballYvector = -ballYvector;
						}
					}
				}
			}
			ballXcoord += ballXvector;
			ballYcoord  += ballYvector;
			if(ballXcoord <0) {
				ballXvector = - ballXvector;
			}
			if(ballYcoord<0) {
				ballYvector = -ballYvector;
			}
			if(ballXcoord> getWidth()-15) {
				ballXvector = - ballXvector;
			}
			
					
		}
		repaint();
		
	}


	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			
			if(sliderPosX>=600) {
			sliderPosX = 600;
			}
			else {
					moveRight();
				}
			}
		
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			if(sliderPosX<10) {
			sliderPosX =10;
			}
			else {
				moveLeft();
		}
	}
	}
	public void moveRight() {
		play = true;
		sliderPosX+=20;
		
	}
	public void moveLeft() {
		play = true;
		sliderPosX-=20;
	}
	
	
		
	
	

	

}
