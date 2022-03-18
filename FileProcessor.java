package com.oop.eng;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileProcessor {
	
	//Attributes
	//
	protected String fileName; //Store the name of the file.
	protected String searchText; //Store the user's search terms.
	File userFile;

	//Constructor
	//
	public FileProcessor(String new_fileName, String new_searchText)
	{
		this.setFileName(new_fileName);
		this.setSearchText(new_searchText);
	}
	
	//Methods
	//
		
	//Open the passed file.
	public void openFile()
	{
		//File userFile = new File(fileName);
			
	}
	
	
	//Read the file and compare the passed string to the text inside it.
	//Check that a file was passed and a string was passed.
	//
	public boolean compareString(String passedFileName, String searchTerms)
	{
		//1.) Specific filename && search term, i.e. search through given file.
		if(("Meaningful Work.txt").equals(fileName) || ("Rabies and Vaccines.txt").equals(fileName) || 
		("Phone data and Machine Learning Humanitarian Aid.txt").equals(fileName) && searchTerms != null)
		{
			
		}
		
		//2.) No specific filename && search term, i.e. search through every file.
		else if(passedFileName != null && searchTerms != null)
		{
					
		}


		//Open the file.
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

	//setFileName must check if the file name matches a file available.
	public void setFileName(String fileName) {
		if(("Meaningful Work.txt").equals(fileName) || ("Rabies and Vaccines.txt").equals(fileName) || 
		("Phone data and Machine Learning Humanitarian Aid.txt").equals(fileName)) 
		{
			this.fileName = fileName;
		}
		
		else
		{
			System.out.println("Not matching filename.");
			System.out.println(fileName);
			this.fileName = null;
		}
	}

	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}

	
	
}
