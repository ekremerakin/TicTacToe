import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class SidePanel extends JPanel{
	
	private JLabel deneme;
	/**
	 * Serial Version UID.
	 */
	private static final long serialVersionUID = 5214506819372822801L;

	public SidePanel() {
		setBackground(Color.RED);
		deneme = new JLabel("Tic Tac Toe");
		add(deneme);
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(200,400);
	}
	
}
