package de.mathisneunzig.drclever.lib;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class MultiplyListWriter {
	
	private File file;
	private PrintWriter pw;
	public List<String> oldlist;
	
	public MultiplyListWriter(File file) {
		
		MultiplyListReader mlr = new MultiplyListReader(file);
		this.oldlist = mlr.getWholeFile();
		for(String s : oldlist)
			System.out.println(s);
		System.out.println("c:");
		
		this.file = file;
		try {
			pw = new PrintWriter(new FileWriter(file));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void printList(List<String> list, String title) {
		
		if(file.exists()) {
			
			for(String s : this.oldlist)
				pw.println(s);
			pw.println("");
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
	
//	@SuppressWarnings("rawtypes")
//	public void printList(List<List> bigList, String title) {
//		
//		if(file.exists()) {
//			
//			int size = bigList.size();
//			
//			for(int i = 0;i<size;i++) {
//				
//				@SuppressWarnings("unchecked")
//				List<String> list = bigList.get(i);
//				
//				pw.println("["+title+"]");
//				pw.println(""+list.size());
//				for(String s : list) {
//					
//					pw.println(s);
//					
//				}
//				pw.println("");
//				pw.flush();
//				
//			}
//			
//		} else {
//			
//			System.out.println("Die Datei, in die geschrieben werden soll, existiert nicht!");
//			
//		}
//		
//	}
	
	public void close() {
		
		pw.close();
		
	}
	
}
