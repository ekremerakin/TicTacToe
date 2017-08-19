import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

/*
 * PositionProtocol holds the data in an array.All the gui 
 * components displays in order to that array.
 * 
 * Also, this class includes the collision detection methods.
 */
public class PositionProtocol {
	
	/*
	 * Holds static turn variable .
	 */
	private static int turn = 0;
	
	/*
	 * Holds positions for all the users.
	 * 1 for X
	 * 2 for O
	 * 0 for empty area.
	 */
	private int[][] positions = new int[][] {
		{0, 0, 0},
		{0, 0, 0},
		{0, 0, 0},
	};
	
	/*
	 * Holds all the coordinates. When an input comes to
	 * newPosition method in this class, this ArrayList
	 * converts that input to a Point object.
	 */
	private ArrayList<Point> coordinates = new ArrayList<>();
	
	/*
	 * Constructor.
	 */
	public PositionProtocol(GamePanel gamePanel) {
		initializeCoordinates();
	}

	/*
	 * Filling the coordinates ArrayList with Point objects.
	 */
	private void initializeCoordinates() {
		coordinates.add(new Point(66, 66));   //1
		coordinates.add(new Point(199, 66));  //2
		coordinates.add(new Point(333, 66));  //3
		coordinates.add(new Point(66, 199));  //4
		coordinates.add(new Point(199, 199)); //5
		coordinates.add(new Point(333, 199)); //6
		coordinates.add(new Point(66, 333));  //7
		coordinates.add(new Point(199, 333)); //8
		coordinates.add(new Point(333, 333)); //9
	}
	
	/*
	 * This method takes selectedPosition as a parameter and 
	 * converts it to a Point object if the input is a valid number.
	 */
	protected Point newPosition(int selectedPosition) {
		if(selectedPosition<1)
			return null;
		
		
		selectedPosition--;
		int x = coordinates.get(selectedPosition).x;
		int y = coordinates.get(selectedPosition).y;
		
		if((turn+1)%2 == 1) {
			
			if(positions[x/160][y/160] == 0) {
				positions[x/160][y/160] = 1;
				turn++;
			}
			else
				return null;
			
		}else {
			
			if(positions[x/160][y/160] == 0) {
				positions[x/160][y/160] = 2;
				turn++;
			}
			else
				return null;
		}
		
		
		
		printPositionsArray();
		return new Point(x, y);
	}
	
	/*
	 * This method was written for debugging.
	 */
	private void printPositionsArray() {
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				System.out.print(positions[j][i] + " ");
			}
			System.out.println("");
		}
		System.out.println("------");
	}
	
	/*
	 * Resets the turn integer and positions array to 
	 * start a new game.
	 */
	protected void newGame() {
		turn = 0;
		positions = new int[][] {
			{0, 0, 0},
			{0, 0, 0},
			{0, 0, 0},
		};
	}
	
	/*
	 * This method uses the positions array and display all the variables 
	 * inside it.
	 */
	protected void displayStoredPieces(Graphics2D graphics2d) {
		DrawHandler drawHandler = new DrawHandler();
		int count = 0;
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				if(positions[j][i] == 1)
					drawHandler.drawX(graphics2d, coordinates.get(count).x, coordinates.get(count).y);
				else if(positions[j][i] == 2)
					drawHandler.drawO(graphics2d, coordinates.get(count).x, coordinates.get(count).y);
				count++;
			}
		}
	}

	/*
	 * Checking the collision for vertical, horizontal and cross
	 * sections.
	 * X_OR_O is a parameter that represents for which piece we are
	 * checking the collision. 1 for X and 2 for O.
	 */
	protected boolean collisionDetection(int X_OR_O) {
		int count = 0;
		
		/*
		 * Vertical collision
		 */
		for(int i=0;i<3;i++) {
			count = 0;
			for(int j=0;j<3;j++) {
				if(positions[j][i] == X_OR_O)
					count++;
			}
			if(count == 3)
				return true;
		}
		
		/*
		 * Horizontal collision
		 */
		for(int i=0;i<3;i++) {
			count = 0;
			for(int j=0;j<3;j++) {
				if(positions[i][j] == X_OR_O)
					count++;
			}
			if(count == 3)
				return true;
		}
		
		/*
		 * Cross collision
		 */
		if(		positions[0][0] == X_OR_O &&
				positions[1][1] == X_OR_O &&
				positions[2][2] == X_OR_O)
			return true;
		if(		positions[2][0] == X_OR_O &&
				positions[1][1] == X_OR_O &&
				positions[0][2] == X_OR_O)
			return true;
		
		return false;
	}
	
	/*
	 * Encapsulated variables.
	 */
	protected int getTurn() {
		return turn;
	}
	
	protected int[][] getPositions() {
		return positions;
	}

}
