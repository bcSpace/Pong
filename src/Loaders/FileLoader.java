package Loaders;

import java.io.IOException;
import java.io.InputStream;

public class FileLoader {
	char[] chars = new char[10000]; 
	String strings = "";
	int counter = 0; 
	public void loadLevel(String path) {
		int r; 
		InputStream is = this.getClass().getResourceAsStream(path);
		counter = 0; 
		try {while ((r = is.read()) != -1) {
			chars[counter] = (char)(r); 
			counter++; 
			}} catch (IOException e) {System.out.println("ERROR LOADING FILE:" + path + " CLOSING..."); System.exit(0);}
		strings = "";
		for(int i = 0; i < counter; i++) strings += Character.toString(chars[i]); 
		System.out.println("File loaded: " + path); 
	}

	public String getLevel() {
		return strings;
	}
	
	
}
