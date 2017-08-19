import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

/*
 * This class is a JPanel's child class. It creates a 
 * panel which we can play the game on. 
 * 
 * Also, this class is responsible to draw everything in 
 * the given order.
 * 
 */
public class GamePanel extends JPanel {
	
	/**
	 * Serial Version UID.
	 */
	private static final long serialVersionUID = -1316546369342961121L;

	/*
	 * Objects from other classes.
	 */
	private DrawHandler drawHandler;
	private PositionProtocol positionProtocol;
	
	/*
	 * When a player wins the game, it would be the winner and
	 * starts the game with the X piece.
	 */
	private Player winner;
	private Player player1;
	private AI ai;
	
	/*
	 * Object from Graphics2D.
	 */
	private Graphics2D graphics2d;
	
	private boolean isNewGame = false;
	private boolean showAtTheBeginning = true;
	private boolean showScores = true;
	private boolean showWhoStartsFirst = true;
	private boolean isAiPlays = false;
	
	/*
	 * Some variables for defining the position of 
	 * the X or O.
	 */
	private int selectedPosition = 0;
	private int selectedX;
	private int selectedY;
	
	/*
	 * Default constructor.
	 */
	public GamePanel(Player player1, AI ai) {
		positionProtocol = new PositionProtocol(this);
		drawHandler = new DrawHandler();
		this.player1 = player1;
		winner = player1;
		this.ai = ai;
		setPanelProperties();		
	}
	
	/*
	 * Setting panel properties.
	 */
	private void setPanelProperties() {
		
		setBackground(Color.WHITE);
		setBorder(BorderFactory.createLineBorder(Color.BLACK) );
		setFocusable(true);
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				//System.out.println("x: " + e.getX() + " y: " + e.getY());
				if(showAtTheBeginning) {
					showAtTheBeginning = false;
				}
				else if(showScores) {
					showScores = false;	
				}
				else if(showWhoStartsFirst) {
					showWhoStartsFirst = false;
					isNewGame = true;
				}
				else if(isNewGame && !isAiPlays) {
					
					int X = e.getX();
					int Y = e.getY();
				
					if(Y<=125) {
						if(X<=125)
							selectedPosition = 1;
						else if(X>=140 && X<=260)
							selectedPosition = 2;
						else if(X>=275)
							selectedPosition = 3;
						else 
							selectedPosition = 0;
					}
					else if(Y>=140 && Y<=260) {
						if(X<=125)
							selectedPosition = 4;
						else if(X>=140 && X<=260)
							selectedPosition = 5;
						else if(X>=275)
							selectedPosition = 6;
						else 
							selectedPosition = 0;
					}
					else if(Y>=260) {
						if(X<=125)
							selectedPosition = 7;
						else if(X>=140 && X<=260)
							selectedPosition = 8;
						else if(X>=275)
							selectedPosition = 9;
						else 
							selectedPosition = 0;
					}
					
					Point point = positionProtocol.newPosition(selectedPosition);
					if(point!=null) {
						selectedX = point.x;
						selectedY = point.y;
						if(positionProtocol.getTurn()!=9)
							isAiPlays = true;
					}
				}
			}
		});
	}
	
	/*
	 * To draw the elements, paintComponent method was 
	 * override. Also, some of the drawing methods were
	 * moved to DrawHandler class to avoid getting messy. 
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		graphics2d = (Graphics2D)g;
		if(showAtTheBeginning) {
			drawHandler.drawStartTheGame(graphics2d);
		} 
		else if(showScores) {
			drawHandler.drawScores(graphics2d, player1.getUserID(), ai.getUserID(),
					player1.getScore(), ai.getScore());
		}
		else if(showWhoStartsFirst) {
			drawHandler.drawWhoWin(graphics2d, winner.getUserID()+"\n  Starts");
		}
		else if(isNewGame && !isAiPlays) {
			drawHandler.drawGameArena(graphics2d);
			if(positionProtocol.getTurn()%2 ==  1) {
				drawHandler.drawX(graphics2d, selectedX, selectedY);
			} else if(positionProtocol.getTurn() != 0) {
				drawHandler.drawO(graphics2d, selectedX, selectedY);
			}
			positionProtocol.displayStoredPieces(graphics2d);
			collisionDetection();
		}
		else if(isAiPlays) {
			Point point = null;
			int selectedPosition = ai.setAiPosition(positionProtocol.getPositions());
			point = positionProtocol.newPosition(selectedPosition);
			if(point!=null) {
				selectedX = point.x;
				selectedY = point.y;
				isAiPlays = false;
			}
		}
	}
	
	/*
	 * Checks the collision and changes the order of the 
	 * players.
	 */
	private void collisionDetection() {
		if(positionProtocol.collisionDetection(1)) {
			if(player1.isWon() == true) {
				player1.playerWon();
				ai.playerLose();
				winner = player1;
			}
			else if(ai.isWon() == true) {
				isAiPlays = true;
				ai.playerWon();
				player1.playerLose();
				winner = (Player)ai;
			}
			showScores = true;
			showWhoStartsFirst = true;
			isNewGame = false;
			positionProtocol.newGame();
		}
		else if(positionProtocol.collisionDetection(2)) {
			if(player1.isWon() == false) {
				player1.playerWon();
				ai.playerLose();
				winner = player1;
			}
			else if(ai.isWon() == false) {
				isAiPlays = true;
				ai.playerWon();
				player1.playerLose();
				winner = (Player)ai;
			}
			showScores = true;
			showWhoStartsFirst = true;
			isNewGame = false;
			positionProtocol.newGame();
		}
		else if(positionProtocol.getTurn() == 9) {
			System.out.println("due");
			showScores = true;
			showWhoStartsFirst = true;
			isNewGame = false;
			positionProtocol.newGame();
		}
	}
	
	/*
	 * Return the size of the panel to the main frame.
	 */
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(400,400);
	}

}
