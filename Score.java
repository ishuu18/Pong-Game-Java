import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JLabel;

public class Score extends PongMain {
	
	private int num;
	private JLabel label;
	private int numClicks;
	
	public Score(JLabel label,int num)
	{
		this.num = num;
		this.label = label;
	}
	
	public void updateScore()
	{
		
	}
}
	
	
	