import java.awt.BorderLayout;

import javax.swing.JFrame;

/*
 * This program was written by Ekrem ERAKIN and for more 
 * information, don't forget to check my blog out.
 * link: ekremerakin.wordpress.com
 * 
 */

/*
 * This class is a JFrame object. It basically creates
 * the main frame. For all the other panels are creating 
 * in GameControl class.
 */
public class MainClass extends JFrame {

	/**
	 * Serial Version UID.
	 */
	private static final long serialVersionUID = 7810657544848978661L;
	
	/*
	 * GameControl object.
	 */
	private GameControl gameControl;
	
	/*
	 * Default constructor.
	 */
	public MainClass() {
		super("Tic Tac Toe");
		
		setLayout(new BorderLayout());
		
		gameControl = new GameControl(this);
		gameControl.start();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		pack();
		setVisible(true);
	}
	
	/*
	 * Main method just creates a MainClass object.
	 */
	public static void main(String[] args) {
		new MainClass();
	}
}
