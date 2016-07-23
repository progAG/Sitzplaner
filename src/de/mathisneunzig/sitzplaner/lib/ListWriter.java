package de.mathisneunzig.sitzplaner.lib;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class ListWriter {
	
	private File file;
	private PrintWriter pw;
	
	public ListWriter(File file) {
		
		this.file = file;
		try {
			pw = new PrintWriter(new FileWriter(file));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void printList(List<String> list, String title) {
		
		if(file.exists()) {
			
			pw.println("["+title+"]");
			pw.println(""+list.size());
			for(String s : list) {
				
				pw.println(s);
				
			}
			pw.println("");
			pw.flush();
			
		} else {
			
			System.out.println("Die Datei, in die geschrieben werden soll, existiert nicht!");
			
		}
		
	}
	
	public void close() {
		
		pw.close();
		
	}
	
}
