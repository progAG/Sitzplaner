package de.mathisneunzig.sitzplaner;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GroupReader {
	
	private File file;
	private Scanner sc;
	
	public List<String> list = new ArrayList<String>();
	public List<List> all = new ArrayList<List>();
	
	public GroupReader(File file) {
		
		this.file = file;
		
		try {
			sc = new Scanner(file);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		
		while(sc.hasNextLine()) {
			
			list = new ArrayList<String>();
			
			String[] angaben;
			String zeile = sc.nextLine();
			angaben = zeile.split(",");
			
			if(angaben[2] == null) list.add("tisch");
			else if(angaben[2].equalsIgnoreCase("w")) list.add("maedchen");
			else if(angaben[2].equalsIgnoreCase("m")) list.add("junge");
			
			System.out.println(angaben[2]);
			
			list.add(angaben[0]);
			System.out.println(angaben[0]);
			list.add(angaben[1]);
			System.out.println(angaben[1]);
			System.out.println("===");
			
			all.add(list);
			
		}
		
	}
	
	public List<List> getAllNames() {
		
		return this.all;
		
	}
	
}
