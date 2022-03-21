package com.oop.eng;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
	//There are 2 paths for the method:
	//1.) If the user enters a specific file to search through, the method will search only that file for the 
	//search terms.
	//2.) If no filenames are entered, then all files will be searched for the search term(s).
	//***NOTE: Will add way to check files in folder, rather than manually typing each filename in if statement. 
	//
	//Buffered Reader code: https://www.guru99.com/buffered-reader-in-java.html
	public void compareString(String passedFileName, String searchTerms)
	{
		//Variables. These will be used in both options.
		int numOfMatches = 0; //Count how many time the search was matched.
		//Debug line
		System.out.println(searchTerms);
		
		
		//1.) Specific filename && search term, i.e. search through given file.
		if(("Meaningful Work.txt").equals(fileName) || ("Rabies and Vaccines.txt").equals(fileName) || 
		("Phone data and Machine Learning Humanitarian Aid.txt").equals(fileName) && searchTerms != null)
		{
			//Debug line
			System.out.println("Inside if");
			File userFile = new File(fileName); //Open file
			String currentLine = ""; //Stores current line of text from file.
			
			//ArrayList to store each line of file.
			ArrayList<String> textFileLines = new ArrayList<String>();
			
			//Integer for looping through the array.
			int i = 0;
			int y = 0;
			
			BufferedReader myBufferReader = null;
			try {
				myBufferReader = new BufferedReader(new FileReader(userFile));
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			//Read through the file.
			//
			try {
				while((currentLine = myBufferReader.readLine()) != null)
				{
					//Dev note: The line being read means the whole line.
					//A line is marked by \n, e.g. "Harris reviewed his earnings" vs "Harris\n reviewed\n" etc
					//"Harris reviewed his earnings" is one line, but "Harris" "reviewed" are separate.
					//So matching the searchTerm doesn't work for a line with more than 1 word in it.
					//option 1.) Split each word in text file onto a separate line so it's 1 word per line.
					//option 2.) Use some delimiter to identify each word in a line.
					//option 3.) None of the above, use string.split on current line and use textFileLines.add
					
					//Debug Line
					y=0;
					System.out.println("Inside while");
					//currentLine = myBufferReader.nextLine();
					
					//Split currentLine into an array that contains each word.
					String[] separatedWords = currentLine.split(" ");
					
					//roleLines[i] = currentLine
					
					//Add the current line into the array.
					//textFileLines.add(currentLine);
					//Debug line
					//System.out.println("this line is "+ currentLine);
					//System.out.println(textFileLines.get(i));
					
					//Reminder: currentLine is split up into an array or strings, need to loop through array and 
					//check which strings match, but how to know how many elements are in the array?
					while(y<separatedWords.length)
					{
						System.out.println("word["+y+"] = "+separatedWords[y]);
						//If current line has any words matching the searchTerms.
						if((separatedWords[y]).equals(searchTerms))
						{
							//Debug line
							System.out.println("Search term matched.");
						
							//Increase matched by 1.
							numOfMatches++;
						}
						
						y++;
					}
						
					//Increment i.
					i++;
						
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//myScanner.close();
			System.out.println(numOfMatches);
		}
		
		//2.) No specific filename && search term, i.e. search through every file.
		else if(passedFileName != null && searchTerms != null)
		{
			System.out.println("2.) No specific filename && search term");
		}

		/*
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
			*/
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
			System.out.println("Not a matching filename.");
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
