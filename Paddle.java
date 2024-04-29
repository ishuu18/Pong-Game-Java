import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JComponent;

public class Paddle extends JComponent implements Updateable {
    private int dx;
    private int dy;
    //private int width;
    //private int height;
    //private int speed;
    private Color color;
    private Rectangle rect;
    int topBounds;
    int bottomBounds;
    
    public Paddle(int x, int y, int topBounds, int bottomBounds, Color color, int width) {
        this.dx = 0;
        this.dy = 0;
        rect = new Rectangle(0,0,width,50);
        this.color = color;
        this.setBounds(x,y,width+1,51);
        this.topBounds = topBounds;
        this.bottomBounds = bottomBounds;
    }
    public void update() 
    {
    	int y = getY()+dy;
    	if(getY()+dy<topBounds)
    	{
    		y = topBounds;
    	}
    	else if(y + getHeight() > bottomBounds)
    	{
    		y = bottomBounds - getHeight();
    	}
    	setLocation(getX(),y);
    }
    
	 
	 public void paintComponent(Graphics g)
	 {
		
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(color);
		g2.fill(rect);
		
	 }
	 
	 public int getDx() 
	 {
		return dx;
	 }
	 public void setDx(int dx) 
	 {
		this.dx = dx;
	 }
	 public int getDy() 
	 {
		return dy;
	 }
	public void setDy(int dy) 
	{
		this.dy = dy;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public void setBottomBounds(int num)
	{
		bottomBounds = num;
	}
	public void setTopBounds(int n)
	{
		topBounds = n;
	}
}
