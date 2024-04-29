import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JComponent;
import java.awt.geom.Ellipse2D;
public class Ball extends JComponent implements Updateable {
	private Ellipse2D.Double ball;
	private int dx;
	private int dy;
	private int radius;
	private Paddle cyan;
	private Paddle magenta;
	private Color color;
	private double vx = -150.0;
	private double vy = 10.0;
	
	public Ball(int x, int y, int dx, Paddle cyan, Paddle magenta)
	{
		ball = new Ellipse2D.Double(0,0,12,12);
		
		this.dx = 10*(int) (Math.pow(-1,(int)(Math.random()*2)));
		this.dy = 3*(int) (Math.pow(-1,(int)(Math.random()*2)));
		this.setBounds(x,y,12,12);
		
		this.cyan = cyan;
		this.magenta = magenta;
		
		//dx = 
		//dy = 3*(int) (Math.pow(-1,(int)(Math.random()*2)));
		//color = Color.blue;
		//this.setSize(new Dimension(11,11));
		//this.setLocation(x,y);
	}
	public void setDx(int dx)
	{
		this.dx = dx;
	}
	
	public void setDy(int dy)
	{
		this.dy = dy;
	}
	public int getDx()
	{
		return dx;
	}
	public int getDy()
	{
		return dy;
	}
	public void setColor(Color c)
	{
		color = c;
	}
	@Override
	public void paintComponent(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.yellow);
		g2.fill(ball);
		
	}
	public void reset() {
	    // Set the ball's position to the center of the screen
	    int centerX = getParent().getWidth() / 2 - radius;
	    int centerY = getParent().getHeight() / 2 - radius;
	    setLocation(centerX, centerY);

	    // Set the ball's velocity to a random value
	    vx = -200.0;
	    vy = 10.0;
	    dx = 40*(int) (Math.pow(-1,(int)(Math.random()*2)));
	    dy = 8*(int) (Math.pow(-1,(int)(Math.random()*2)));
	}

	
	@Override
	public void update() {
		setLocation(this.getX() + dx, this.getY()+dy);
	}

	
}
