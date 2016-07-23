package de.mathisneunzig.drclever.lib;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ListReader {
	
	private File file;
	private Scanner sc;
	
	public ListReader(File file) {
		
		this.file = file;
		
	}
	
	public List<String> getList(String title) {
		
		List<String> list = new ArrayList<String>();
		
		try {
			sc = new Scanner(file);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		
		if(file.exists()) {
			
			while(sc.hasNextLine()) {
				
				String currentLine = sc.nextLine();
				if(currentLine.equalsIgnoreCase("["+title+"]")) {
					
					int Stringcount = Integer.parseInt(sc.nextLine());
						
					for(int i = 0; i < Stringcount; i++) {
						
						list.add(sc.nextLine());
						
					}
					
				}
				
			}
			
		} else {
			
			System.out.println("Die zu auslesende Datei existiert nicht!");
			
		}
		
		close();
		return list;
		
	}
	
	public int getStringCount(String title) {
		
		int Stringcount = 0;
		
		if(file.exists()) {
			
			try {
				sc = new Scanner(file);
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
			
			while(sc.hasNextLine()) {
				
				String currentLine = sc.nextLine();
				if(currentLine.equalsIgnoreCase("["+title+"]")) {
					
					Stringcount = Integer.parseInt(sc.nextLine());
					
				}
				
			}
			
		}
		
		return Stringcount;
		
	}
	
	public void close() {
		
		sc.close();
		
	}
	
}
