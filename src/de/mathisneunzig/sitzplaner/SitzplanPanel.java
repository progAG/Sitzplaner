package de.mathisneunzig.sitzplaner;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import de.mathisneunzig.sitzplaner.lib.MultiplyListReader;
import de.mathisneunzig.sitzplaner.lib.MultiplyListWriter;

public class SitzplanPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	public File file;
	
	private GUI gui;
	
	@SuppressWarnings("static-access")
	public SitzplanPanel(GUI gui) {
		
		this.gui = gui;
		
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setBackground(Color.WHITE);
		setBounds(20, 21, 850, 850);
		setLayout(null);
		
		file = new File(gui.s+"/src/de/mathisneunzig/sitzplaner/utils/logfile.txt");
//		System.out.println(file.getAbsolutePath());
		
		if(!file.exists()) {
			
			try {
				file.createNewFile();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
		}
		
		addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {}
			
			@Override
			public void mousePressed(MouseEvent e) {
				
				if(e.getButton() == 1) {
					
				//	if(gui.modus == 0)
						addSeat("tisch", e.getX(), e.getY(), "Nachname", "Vorname");
					
				} else if(e.getButton() == 3) {
					
					removeSeat(e.getX(), e.getY());
					
				}
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {}
			
			@Override
			public void mouseEntered(MouseEvent e) {}
			
			@Override
			public void mouseClicked(MouseEvent e) {}
		});
		
	}
	
	public void removeSeat(int x, int y) {
	
		int newX = ((int) Math.round(x/76))*76;
		int newY = ((int) Math.round(y/76))*76;
//		System.out.println("X: "+x+" Y: "+y+" newX: "+newX+" newY: "+newY);
		
		MultiplyListReader mlr = new MultiplyListReader(file);
		
		List<List> oList = mlr.getList("table");
		List<List> relationOList = mlr.getList("relations");
		
		int count = 0;
		
		for(List list : mlr.getList("table")) {
			
			if(Integer.parseInt((String) list.get(1)) == newX && Integer.parseInt((String) list.get(2)) == newY) {
				
				oList.remove(count);
				
			}
			
//			System.out.println(count);
			count ++;
//			System.out.println(count);
//			System.out.println("=====");
			
		}
		
		FileWriter fw;
		try {
			fw = new FileWriter(file);
		    BufferedWriter bw = new BufferedWriter(fw);
		    bw.write("");
		} catch (IOException e) { 
			e.printStackTrace();
		}
		
//		System.out.println("OLIST: "+oList);
		
		MultiplyListWriter mlw = new MultiplyListWriter(file);
		
		for(List newL : oList) {
			
			mlw.printList(newL, "table");
			
		}
		
		for(List newL : relationOList) {
			
			mlw.printList(newL, "relations");
			
		}
		
		validate();
		repaint();
	
	}

	public void addSeat(String gender, int x, int y, String nachname, String vorname) {
		
		boolean b = false;
		
		int newX = ((int) Math.round(x/76))*76;
		int newY = ((int) Math.round(y/76))*76;
//		System.out.println("X: "+x+" Y: "+y+" newX: "+newX+" newY: "+newY);
		
		ArrayList<String> l = new ArrayList<String>();
		l.add(gender);
		l.add(String.valueOf(newX));
		l.add(String.valueOf(newY));
		l.add(nachname);
		l.add(vorname);
		
		MultiplyListReader mlr = new MultiplyListReader(file);
		
		for(List list : mlr.getList("table")) {
			
			if(Integer.parseInt((String) list.get(1)) == Integer.parseInt((String) l.get(1)) && Integer.parseInt((String) list.get(2)) == Integer.parseInt((String) l.get(2)))
				b = true;
			
		}
		
		if(b == false) {
			
			MultiplyListWriter mlw = new MultiplyListWriter(file);
			
			mlw.printList(l, "table");
			
		}
		
		validate();
		repaint();
		
	}

	public void paintComponent(Graphics pg) {
		
		super.paintComponent(pg);
		Graphics2D g = (Graphics2D) pg;
		
		MultiplyListReader mlr = new MultiplyListReader(file);
	    
		for(List list : mlr.getList("table")) {
			
			String gender = (String) list.get(0);
			int x = Integer.parseInt((String) list.get(1));
			int y = Integer.parseInt((String) list.get(2));
//			System.out.println(list);
			
			Image i = new ImageIcon(this.getClass().getResource(gender+".png")).getImage();
			g.drawImage(i, x, y, this);
			g.drawString((String) list.get(3), x+5, y+40);
			g.drawString((String) list.get(4), x+5, y+60);
			
		}
		
    }

	public void deleteWholeFile() {
		
		file.delete();
		
	}

	public void removeAllSeats() {
		
		FileWriter fw;
		try {
			fw = new FileWriter(file);
		    BufferedWriter bw = new BufferedWriter(fw);
		    bw.write("");
		} catch (IOException e) { 
			e.printStackTrace();
		}
		
		validate();
		repaint();
		
	}

	public void loadNames(List<List> all) {
		
		for(List l : all) {
			
			String gender = (String) l.get(0);
			String lastname = (String) l.get(1);
			String name = (String) l.get(2);
			System.out.println(gender);
			System.out.println(lastname);
			System.out.println(name);
			System.out.println("===");
			
			MultiplyListReader mlr = new MultiplyListReader(file);
			
			List<List> list = mlr.getList("table");
			
			while(true) {
				
				int zufallszahl = (int) ((Math.random()*list.size())+1);
				
				List<String> list2 = list.get(zufallszahl-1);
				
				if(((String) list2.get(0)).equalsIgnoreCase("tisch")) {
					
					removeSeat(Integer.parseInt((String) list2.get(1)), Integer.parseInt((String) list2.get(2)));
					addSeat(gender, Integer.parseInt((String) list2.get(1)), Integer.parseInt((String) list2.get(2)), lastname, name);
					break;
					
				}
				
			}
			
		}
		
	}

	public void reset() {
		
		MultiplyListReader mlr = new MultiplyListReader(file);
		List<List> list = mlr.getList("table");
		
		for(List l : list) {
			
			removeSeat(Integer.parseInt((String) l.get(1)), Integer.parseInt((String) l.get(2)));
			addSeat("tisch", Integer.parseInt((String) l.get(1)), Integer.parseInt((String) l.get(2)), "Nachname", "Vorname");
			
		}
		
	}
	
}
