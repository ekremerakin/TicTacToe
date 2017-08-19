/*
 * Player class is responsible creating the main player.
 * And also, AI class inherited from this class. That means
 * we can use all of the Player class' variables and method 
 * into the AI class.
 */
public class Player {
	
	/*
	 * UserID and score to display on the GUI.
	 */
	protected String userID;
	protected int score = 0;
	
	/*
	 * These variables are responsible for
	 * ruling the turn mechanism.
	 */
	protected boolean isWon = false;
	protected boolean X_OR_O = false;
	
	/*
	 * Default constructor.
	 */
	public Player() {
		userID = "Player";
	}
	
	/*
	 * Constructor.
	 */
	public Player(boolean isWon) {
		this.isWon = isWon;
		userID = "Player";
	}
	
	/*
	 * These methods are also responsible for
	 * ruling the turn mechanism.
	 */
	protected void playerWon() {
		isWon = true;	
		score++;
	}
	
	protected void playerLose() {
		isWon = false;	
	}
	
	/*
	 * Encapsulated methods.
	 */
	protected boolean isWon() {
		return isWon;
	}
	
	protected String getUserID() {
		return userID;
	}
	
	protected int getScore() {
		return score;
	}
	
	protected boolean getX_OR_O() {
		return X_OR_O;
	}
	
}
