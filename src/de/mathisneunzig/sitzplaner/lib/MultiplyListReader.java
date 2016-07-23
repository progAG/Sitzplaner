package de.mathisneunzig.sitzplaner.lib;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MultiplyListReader {
	
	private File file;
	private Scanner sc;
	
	public MultiplyListReader(File file) {
		
		this.file = file;
		
	}
	
	@SuppressWarnings("rawtypes")
	public List<List> getList(String title) {
		
		List<String> list = new ArrayList<String>();
		List<List> bigList = new ArrayList<List>();
		
		if(file == null) {
			
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
		try {
			sc = new Scanner(file);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		
		if(file.exists()) {
			
			while(sc.hasNextLine()) {
				
				String currentLine = sc.nextLine();
				if(currentLine.equalsIgnoreCase("["+title+"]")) {
					
					list = new ArrayList<String>();
					
					int Stringcount = Integer.parseInt(sc.nextLine());
						
					for(int i = 0; i < Stringcount; i++) {
						
						list.add(sc.nextLine());
						
					}
					
					bigList.add(list);
					
				}
				
			}
			
		} else {
			
			System.out.println("Die zu auslesende Datei existiert nicht!");
			
		}
		
		close();
		return bigList;
		
	}
	
	public List<String> getWholeFile() {
		
		List<String> list = new ArrayList<String>();
		
		try {
			sc = new Scanner(file);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		
		if(sc != null) {
			
			while(sc.hasNextLine()) {
				
				list.add(sc.nextLine());
				
			}
			
		} else {
			
			System.out.println("Die Datei ist leer.");
			
		}
		
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
