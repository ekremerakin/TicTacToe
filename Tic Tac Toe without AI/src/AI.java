import java.util.Random;

/*
 * This class is not finished! 
 * It just create a random movement and
 * the ai plays there. 
 * 
 * AI class will be responsible for the artificial
 * intelligence method(s).
 */
public class AI extends Player {
	
	/*
	 * Random object.
	 */
	Random random = new Random();
	
	/*
	 * Default constructor.
	 */
	public AI() {
		userID = "AI";
		score = 0;
	}
	
	/*
	 * Minimax algorithm will go in this method.
	 */
	protected int setAiPosition(int[][] input) {
		int output = random.nextInt(9)+1;
		return output;
	}

	/*
	 * Encapsulated variable.
	 */
	@Override
	protected String getUserID() {
		return userID;
	}
}