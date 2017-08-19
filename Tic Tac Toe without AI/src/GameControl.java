/*
 * This class is responsible for creating the players
 * and repainting the gamePanel until the program finishes.
 */
public class GameControl extends Thread {
	
	/*
	 * GamePanel object.
	 */
	private GamePanel gamePanel;
	
	/*
	 * Objects for user and ai.
	 */
	private Player player1;
	private AI ai;
	
	/*
	 * Constructor. 
	 * Taking mainClass JFrame object as a parameter
	 * to add the JPanel into main frame in this class.
	 */
	public GameControl(MainClass mainClass) {
		player1 = new Player(true);
		ai = new AI();

		gamePanel = new GamePanel(player1, ai);
		
		mainClass.add(gamePanel);
	}
	
	/*
	 * Game Loop.
	 * It's just responsible for repainting the gamePanel components
	 * again and again.
	 */
	@Override
	public void run() {
		while(true) {
			gamePanel.repaint();
		}
	}
	
	
	
}
