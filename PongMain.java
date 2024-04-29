import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;

public class PongMain extends JFrame implements ActionListener {

	private Paddle cyan;
	private Paddle magenta;
	private Timer timer;
	private Ball ball;
	private JLabel cyanScoreLabel;
	private JLabel magentaScoreLabel;
	private int cyanScore;
	private int magentaScore;
	//private Line line;
	
	
    public PongMain() {
    this.setBounds(850,100,550,500);
		this.setLayout(null);
		this.setTitle("Pong");
		this.setResizable(false);
		this.getContentPane().setBackground(Color.BLACK);
		this.setLocationRelativeTo(null);
		int topBounds = 0;
		int bottomBounds = getHeight();
		int paddleX = 15;
		int paddleY = this.getHeight()/2-40;
		int paddle_width = 10;
		
		cyan = new Paddle(paddleX,paddleY, topBounds, bottomBounds,Color.cyan, paddle_width);
		this.add(cyan);
		
		paddleX = this.getWidth() - 40;
		magenta = new Paddle(paddleX,paddleY,topBounds,bottomBounds,Color.magenta, paddle_width);
		this.add(magenta);
		
		//line.setLocation(getWidth(),getHeight());
		//add(line);
		
		this.add(magenta);
		
		
		int xBall = this.getWidth()/2-7;
		int yBall = this.getHeight()/2-7;
		ball = new Ball(xBall,yBall,9, cyan, magenta);
		this.add(ball);
		int topLine = 0;
		int bottomLine = getHeight();
		int midLine = getWidth()/2;
		Line line = new Line(midLine,topBounds,midLine,bottomLine,Color.WHITE,20);
		line.setSize(getWidth(), getHeight());
		add(line);
		cyanScore = 0;
		magentaScore = 0;
		cyanScoreLabel = new JLabel("0");
		cyanScoreLabel.setBounds(200, 10, 50, 50);
		cyanScoreLabel.setForeground(Color.WHITE);
		cyanScoreLabel.setFont(new Font("Tahoma",Font.BOLD,30));
		this.add(cyanScoreLabel);
		magentaScoreLabel = new JLabel("0");
		magentaScoreLabel.setBounds(330, 10, 50, 50);
		magentaScoreLabel.setForeground(Color.WHITE);
		magentaScoreLabel.setFont(new Font("Tahoma",Font.BOLD,30));
		this.add(magentaScoreLabel);
		
		timer = new Timer(25,this);
		timer.start();
		
		this.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void keyPressed(KeyEvent e) {
				int code = e.getKeyCode();
				
				switch(code)
				{
				case KeyEvent.VK_W:
					cyan.setDy(-7);
					break;
				
				case KeyEvent.VK_S:
					cyan.setDy(7);
					break;
				
				case KeyEvent.VK_UP:
					magenta.setDy(-7);
					break;
				case KeyEvent.VK_DOWN:
					magenta.setDy(7);
					break;
				}
			}



			@Override
			public void keyReleased(KeyEvent e) {
				int code = e.getKeyCode();
				
				switch(code)
				{
				case KeyEvent.VK_W:
					cyan.setDy(0);
					break;
				case KeyEvent.VK_S:
					cyan.setDy(0);
					break;
				case KeyEvent.VK_UP:
					magenta.setDy(0);
					break;
				case KeyEvent.VK_DOWN:
					magenta.setDy(0);
					break;
				
			}
			
			
			
			
		}});
		
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		this.setVisible(true);
		//get insets for the frame
		//set bottom bounds for both paddles to height - top inset - bottom inset
		Insets insets = this.getInsets();
		cyan.setBottomBounds(this.getHeight() - insets.top - insets.bottom);
		magenta.setBottomBounds(this.getHeight()-insets.top-insets.bottom);
		
		//cyan.setTopBounds(this.getWidth() - insets.top - insets.bottom);
		//magenta.setTopBounds(this.getWidth()-insets.top-insets.bottom);
    }
   // public int getCyanScore()
    //{
    //	return cyanScore;
    //}
    //public int getMagentaScore()
    //{
    //	return magentaScore;
    //}
    
    

    private boolean endGame = false;
    
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		
	if(!endGame)
	{
		cyan.update();
	    magenta.update();
	    ball.update();

	    // Check for collision with cyan paddle
	    if (ball.getBounds().intersects(cyan.getBounds())) {
	    	System.out.println("1");
	        ball.setLocation(ball.getX(), ball.getY()); //- ball.getWidth());
	      //  ball.setDx((int) (ball.getDx()));
	        ball.setDx(-ball.getDx());
	    }
	    

	    // Check for collision with magenta paddle
	    if (ball.getBounds().intersects(magenta.getBounds())) {
	    	System.out.println("2");
	        ball.setLocation(ball.getX(), ball.getY());// + ball.getWidth());
	      //  ball.setDx((int) (ball.getDx()));
	        ball.setDx(-ball.getDx());
	    }
	   

	    // Check for collision with top and bottom of frame
	    if (ball.getY() <= 0 || ball.getY() + ball.getHeight() >= getHeight()-43.5) {
	    	System.out.println("3");
	        ball.setDy(-ball.getDy());
	    }

	    
	    if (ball.getX() < 0) {
	        ball.setLocation(getWidth()/2 - ball.getWidth()/2, getHeight()/2 - ball.getHeight()/2);
	        ball.setDx(-ball.getDx());
	        ball.setDy(2);
	        magentaScore++;
	        magentaScoreLabel.setText(Integer.toString(magentaScore));
	        if(magentaScore == 10)
	        {
	        	endGame = true;
	        	System.out.println("Magenta Wins the Game");
	        	timer.stop();
	        	
	        }
	    } else if (ball.getX() + ball.getWidth() > getWidth()) {
	        ball.setLocation(getWidth()/2 - ball.getWidth()/2, getHeight()/2 - ball.getHeight()/2);
	        ball.setDx(-ball.getDx());
	        ball.setDy(2);
	        cyanScore++;
	        cyanScoreLabel.setText(Integer.toString(cyanScore));
	        if(cyanScore == 10)
	        {
	        	endGame = true;
	        	System.out.println("Cyan Wins the Game");
	        	timer.stop();
	        	
	        }
	    }
	}
	
	    
	    

	    
	    
	   /* if (ball.getX() <= 0) {
	        // magenta player scores
	        magentaScore++;
	        magentaScoreLabel.setText(Integer.toString(magentaScore));
	        ball.reset(); // move the ball to the center of the screen
	    } else if (ball.getX() + ball.getWidth() >= getWidth()) {
	        // cyan player scores
	        cyanScore++;
	        cyanScoreLabel.setText(Integer.toString(cyanScore));
	        ball.reset(); // move the ball to the center of the screen
	    }
*/
	    
	}
	
	
	
	public static void main(String[] args) 
	{
		new PongMain();
		
		

	}
}
