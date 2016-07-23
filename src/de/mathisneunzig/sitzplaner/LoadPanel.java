package de.mathisneunzig.sitzplaner;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class LoadPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	public LoadPanel() {
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setBackground(Color.WHITE);
		setBounds(880, 21, 250, 850);
		setLayout(null);
	}
	
	public void paintComponent(Graphics pg) {
		
		super.paintComponent(pg);
		Graphics2D g = (Graphics2D) pg;
		Image i = new ImageIcon(this.getClass().getResource("tisch.png")).getImage();
		g.drawImage(i, 30, 30, this);
		
    }
	
}
