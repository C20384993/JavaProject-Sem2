package com.oop.eng;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileProcessor {
	
	//Attributes
	//
	public String fileName;
	File userFile;

	//Constructor
	//
	public FileProcessor(String new_fileName)
	{
		this.setFileName(new_fileName);
	}
	
	//Methods
	//
		
	//Open the passed file.
	public void openFile()
	{
		//File userFile = new File(fileName);
			
	}
		
	//Read the file and compare the passed string to the text inside it.
	//
	public boolean compareFile(String passedRole)
	{
			
		File userFile = new File(fileName);
		boolean roleMatch = false;
		String currentLine = ""; 
		
		//Array to store each line of file.
		//String[] roleLines = {"empty"};
		ArrayList<String> lines = new ArrayList<String>();
		
		//Integer for looping through the array.
		int i = 0;
		int y = 0;
			
		Scanner myScanner = null;
		try 
		{
			myScanner = new Scanner(userFile);
		} 
		
		catch (FileNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			while(myScanner.hasNextLine())
			{
				currentLine = myScanner.nextLine();
				//roleLines[i] = currentLine
				lines.add(currentLine);
				System.out.println("this line is "+ currentLine);
				//System.out.println("role: "+ passedRole);
				
				//Return true if the current line is the same as the passed role.
				if((lines.get(i)).equals(passedRole))
				{
					System.out.println("String matched.");
					
					//Close the scanner and return true.
					roleMatch = true;
				}
					
				//Increment i.
				i++;
					
			}
			
			myScanner.close();
			
			System.out.println(roleMatch);
			return roleMatch;
		}
		
	
	//Getters and Setters
	//
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	
}
