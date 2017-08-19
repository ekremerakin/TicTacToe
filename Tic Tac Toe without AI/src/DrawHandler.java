import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

/*
 * DrawHandler is created for defining 
 * special methods for TicTacToe game.
 */
public class DrawHandler {

	/*
	 * Draws the game arena.
	 */
	protected void drawGameArena (Graphics2D graphics2d) {
		graphics2d.setColor(Color.BLACK);
		graphics2d.setStroke(new BasicStroke(5));
		//Vertical Lines
		graphics2d.drawLine(133, 0, 133, 400);
		graphics2d.drawLine(267, 0, 267, 400);
		//Horizontal Lines
		graphics2d.drawLine(0, 133, 400, 133);
		graphics2d.drawLine(0, 267, 400, 267);
	}
	
	/*
	 * Draws X lines for the given center coordinates.
	 * Note: width and height have a constant value which
	 * is 70.
	 */
	protected void drawX (Graphics2D graphics2d, int x, int y) {
			graphics2d.setColor(Color.BLUE);
			graphics2d.setStroke(new BasicStroke(10));
			graphics2d.drawLine(x-35, y-35, x+35, y+35);
			graphics2d.drawLine(x+35, y-35, x-35, y+35);
	}
	
	/*
	 * Draws O circle for the given center coordinates.
	 * Note: width and height have a constant value which
	 * is 70.
	 */
	protected void drawO (Graphics2D graphics2d, int x, int y) {
			graphics2d.setColor(Color.RED);
			graphics2d.setStroke(new BasicStroke(10));
			graphics2d.drawOval(x-35, y-35, 70, 70);
	}

	/*
	 * Draws a string which is "click to start".
	 * Since drawString(Graphics2D class') method is not creating 
	 * a new line with \n, there is another method found from the
	 * web and used to display texts with '\n' inside them. 
	 */
	public void drawStartTheGame(Graphics2D graphics2d) {
		graphics2d.setColor(Color.BLACK);
		graphics2d.setFont(new Font("Serif", Font.BOLD, 75));
		drawString(graphics2d, "Click\n   to\nStart", 115, 35);
	}
	
	/*
	 * This one is same for the drawStartTheGame method. It draws which
	 * user is going to play first.
	 */
	public void drawWhoWin(Graphics2D graphics2d, String userID) {
		graphics2d.setColor(Color.BLACK);
		graphics2d.setFont(new Font("Serif", Font.BOLD, 75));
		drawString(graphics2d, userID, 70, 75);
	}
	
	/*
	 * This method created for using the '\n' next line symbol since
	 * Graphics2D.drawString is not let us to do that directly.
	 */
	private void drawString(Graphics2D graphics2D, String text, int x, int y) {
        for (String line : text.split("\n"))
            graphics2D.drawString(line, x, y += graphics2D.getFontMetrics().getHeight());
    }
	
	/*
	 * This method draws the scores.
	 */
	protected void drawScores(Graphics2D graphics2D, String userID_1, String userID_2, 
			int user_1_score, int user_2_score) {
		graphics2D.setColor(Color.BLACK);
		graphics2D.setFont(new Font("Serif", Font.BOLD, 75));
		graphics2D.drawString(userID_1+":", 65, 70);
		graphics2D.drawString(user_1_score + "", 150, 150);
		
		graphics2D.drawLine(0, 200, 400, 200);
		graphics2D.drawString(userID_2+":", 65, 270);
		graphics2D.drawString(user_2_score + "", 150, 350);
	}
	
}
