package de.mathisneunzig.sitzplaner;

import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;

import de.mathisneunzig.sitzplaner.lib.MultiplyListReader;
import de.mathisneunzig.sitzplaner.lib.MultiplyListWriter;
import javax.swing.JToggleButton;

public class GUI extends JFrame implements KeyListener {
	
	public String path = null;
	public static String s = System.getProperty("user.dir");
	
	public int action;
	public int modus = 0;
	
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	
	public static void main(String[] args) {
		
		new GUI();
		
	}
	
	public GUI() {
		
		setExtendedState(MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 785, 578);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.info);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		SitzplanPanel plan = new SitzplanPanel(this);
		contentPane.add(plan);
		plan.removeAllSeats();
		
//		LoadPanel auswahl = new LoadPanel();
//		contentPane.add(auswahl);
		
		JButton btnNEU = new JButton("Neu");
		btnNEU.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				plan.removeAllSeats();
				
			}
		});
		btnNEU.setBackground(Color.WHITE);
		btnNEU.setBounds(880, 21, 290, 70);
		contentPane.add(btnNEU);
		
		JButton btnOEFFNEN = new JButton("\u00D6ffnen...");
		btnOEFFNEN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JFileChooser chooser = new JFileChooser("Bitte Speicherplatz auswählen...");
		        chooser.showOpenDialog(null);
//		        chooser.setAcceptAllFileFilterUsed(false);
//		        chooser.setFileFilter(new SitzplanFileFilter(".sitzplanX"));
		        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		        String path = chooser.getSelectedFile().getAbsolutePath();
		        plan.file = new File(path);
		        validate();
		        repaint();
				
			}
		});
		btnOEFFNEN.setBackground(Color.WHITE);
		btnOEFFNEN.setBounds(880, 102, 290, 70);
		contentPane.add(btnOEFFNEN);
		
		JButton btnSPEICHERN = new JButton("Speichern");
		btnSPEICHERN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(path == null) {
					
					JFileChooser chooser = new JFileChooser("Bitte Speicherplatz auswählen...");
			        chooser.showSaveDialog(null);
//			        chooser.setAcceptAllFileFilterUsed(false);
//			        chooser.setFileFilter(new SitzplanFileFilter(".sitzplanX"));
			        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			        path = chooser.getSelectedFile().getAbsolutePath();
			        
				} 

				File f = new File(path);
				
				FileWriter fw;
				try {
					fw = new FileWriter(f);
				    BufferedWriter bw = new BufferedWriter(fw);
				    bw.write("");
				} catch (IOException e2) { 
					e2.printStackTrace();
				}
		        
		        MultiplyListReader mlr = new MultiplyListReader(plan.file);
		        MultiplyListWriter mlw = new MultiplyListWriter(f);
		        
		        List<List> l = mlr.getList("table");
		        System.out.println(l);
		        for(List list : l) {
		        	
		        	System.out.println("Hi");
		        	mlw.printList(list, "table");
		        	System.out.println(list);
		        	
		        }
				
			}
		});
		btnSPEICHERN.setBackground(Color.WHITE);
		btnSPEICHERN.setBounds(880, 183, 290, 70);
		contentPane.add(btnSPEICHERN);
		
		JButton btnSPEICHERNUNTER = new JButton("Speichern unter...");
		btnSPEICHERNUNTER.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JFileChooser chooser = new JFileChooser("Bitte Speicherplatz auswählen...");
		        chooser.showSaveDialog(null);
//		        chooser.setAcceptAllFileFilterUsed(false);
//		        chooser.setFileFilter(new SitzplanFileFilter(".sitzplanX"));
		        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		        path = chooser.getSelectedFile().getAbsolutePath();
		        
		        File f = new File(path);
		        
		        MultiplyListReader mlr = new MultiplyListReader(plan.file);
		        MultiplyListWriter mlw = new MultiplyListWriter(f);
		        
		        List<List> l = mlr.getList("table");
		        for(List list : l) {
		        	
		        	mlw.printList(list, "table");
		        	
		        }
				
			}
		});
		btnSPEICHERNUNTER.setBackground(Color.WHITE);
		btnSPEICHERNUNTER.setBounds(880, 264, 290, 70);
		contentPane.add(btnSPEICHERNUNTER);
		
		JTextArea names = new JTextArea();
		names.setBorder(new LineBorder(new Color(0, 0, 0)));
		names.setBackground(SystemColor.menu);
		names.setEditable(false);
		
		JScrollPane namesScroll = new JScrollPane(names);
		namesScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		namesScroll.setBounds(880, 426, 290, 445);
		contentPane.add(namesScroll);
		
		JButton btnLISTELADEN = new JButton("Namensliste laden...");
		btnLISTELADEN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JFileChooser chooser = new JFileChooser("Bitte Speicherplatz auswählen...");
		        chooser.showOpenDialog(null);
//		        chooser.setAcceptAllFileFilterUsed(false);
//		        chooser.setFileFilter(new SitzplanFileFilter(".sitzplanX"));
		        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		        File f = chooser.getSelectedFile();
		        System.out.println(f.getAbsolutePath());
				
				GroupReader gr = new GroupReader(f);
				List<List> all = gr.getAllNames();
				System.out.println(all);
				plan.reset();
				plan.loadNames(all);
				
			}
		});
		btnLISTELADEN.setBackground(Color.WHITE);
		btnLISTELADEN.setBounds(880, 345, 290, 70);
		contentPane.add(btnLISTELADEN);
		
		JToggleButton tglbtnFreundemodus = new JToggleButton("Freunde-Modus");
		tglbtnFreundemodus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(modus == 0)
					modus = 1;
				else if(modus == 1)
					modus = 0;
				
			}
		});
		tglbtnFreundemodus.setBounds(1180, 21, 107, 394);
		contentPane.add(tglbtnFreundemodus);
		
		setVisible(true);
		
		validate();
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
	
		System.out.println("typed");
		
	}

	@Override
	public void keyPressed(KeyEvent e) {

		System.out.println("pressed");
		
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			
			action = 1;
			System.out.println(action);
			
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
	
		System.out.println("released");
		
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			
			action = 0;
			System.out.println(action);
			
		}
		
	}
}
