package de.mathisneunzig.sitzplaner;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class SitzplanFileFilter extends FileFilter {
	
	private String endung;
	
	public SitzplanFileFilter(String endung) {
		
		this.endung = endung;
		System.out.println("FileFilter aktiviert!");
		
	}

	@Override
	public boolean accept(File f) {
		
		if(f == null) return false;
        
        if(f.isDirectory()) return true;
        	
        return f.getName().toLowerCase().endsWith(endung);
		
	}

	@Override
	public String getDescription() {
		
		return "Sitzplan-Datei (.sitzplanX)";
		
	}
	
}
