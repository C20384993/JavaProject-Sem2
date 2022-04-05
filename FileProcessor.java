package com.oop.eng;

import java.io.BufferedReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.*;

public class FileProcessor extends Control{
	
	//Attributes
	//
	protected String fileName; //Store the name of the file.
	protected String searchText; //Store the user's search terms.
	File userFile;

	//Constructor
	//
	public FileProcessor(String newFileName, String newSearchText)
	{
		this.setFileName(newFileName);
		this.setSearchText(newSearchText);
	}
	
	//Methods
	//
	//compareString: Read the file and compare the passed string to the text inside it.
	//Check that a file was passed and a string was passed.
	//There are 2 paths for the method:
	//1.) If the user enters a specific file to search through, the method will search only that file for the 
	//search terms.
	//2.) If no filenames are entered, then all files will be searched for the search term(s).
	//
	//Buffered Reader code: https://www.guru99.com/buffered-reader-in-java.html
	public void compareString(String passedFileName, String searchTerms)
	{
		//Read the names of the text files that are in the TextFiles folder.
		File Textfiles = new File("C:\\Users\\C20384993\\eclipse-workspace\\OOP-CA-C20384993\\TextFiles");
		File[] resources = Textfiles.listFiles();
		
		//Variables
		//
		String selectedFile = ""; //Store the file name that was selected and found in the project folder.
		boolean fileFound = false; //Used in if statement to check if passedFileName was found/did match.

		
		//Go through the project folder to try and find the file name entered.
		for (File file : resources)
		{
		    if (file.isFile())
		    {
		    	//Debug
		        System.out.println(file.getName());
		        
		        //Set selectedFile as the found filename and fileFound to true. 
		        //These will be used in option 1 of the if/if else statement.
		        if(passedFileName.equals(file.getName()) == true)
		        {
		        	selectedFile = passedFileName;
		        	fileFound = true;
		        }//end if
		    }
		}
		
		
		//Count how many time the search was matched. Integer Object used so it can be put into the list of lists.
		Integer numOfMatches = 0;
		
		
		
		//Debug line
		System.out.println(searchTerms);
		
		
		//1.) Specific filename && search term, i.e. search through given file.
		//Only if the user has specified a file to search, a search term to search for, and the specified file was found.
		if(passedFileName != null && searchTerms != null && fileFound == true)
		{
			//Debug line
			System.out.println("Inside if");
			File userFile = new File(fileName); //Open file
			String currentLine = ""; //Stores current line of text from file.
			
			int i = 0; //Integer for looping through the array. **********CHANGE TO ITERATOR************
			int y = 0; //Used in the second while loop to go through the array of words from the current line. 
			
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
					//Read the current line, split it into an array that contains each word, loop through array
					//and check if searchTerm matches.
					
					//Debug Line
					y=0;
					System.out.println("Inside while");
					
					//Split currentLine into an array that contains each word.
					String[] separatedWords = currentLine.split(" ");
					
					
					//Get the length of the array and loop through it.
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
			
			System.out.println(numOfMatches);
			
		}//end if
		
		//---------------------------------------------------------------------------------------------------------------
		
		//OPTION 2
		//2.) No specific filename && search term, i.e. search through every file.
		
		//Find file names in folder.
		//https://stackoverflow.com/questions/52369327/how-to-read-data-from-all-files-one-by-one-in-a-folder-with-java
		else if(passedFileName.equals("empty") && searchTerms != null)
		{
			System.out.println("2.) No specific filename && have search term");
			
			
			Integer numOfMatches1 = 0; //Count how many time the search was matched.
			String currentLine = ""; //Stores current line of text from file.
			int y = 0; //Used in the second while loop to go through the array of words from the current line. 
			
			//Read the names of the text files that are in the TextFiles folder.
			File filesFolder = new File("C:\\Users\\C20384993\\eclipse-workspace\\OOP-CA-C20384993\\TextFiles");
			File[] resourcesFolder = filesFolder.listFiles();
			
			
			//ArrayList to store each file name.
			ArrayList<String> textFileName = new ArrayList<String>();

			//Fill textFileName with each text file name in the project folder.
			for (File file : resourcesFolder)
			{
			    if (file.isFile())
			    {
			    	//Debug
			        System.out.println(file.getName());
			        //Add the file name to the array.
			        textFileName.add(file.getName());
			    }
			}
			
			//DEBUG
			System.out.println("Print textFileName");
			for(int v = 0; v < textFileName.size(); v++) {   
			    System.out.print(textFileName.get(v));
			} 
			
			
			//This will store the number of matches and each corresponding file.
			//Source: https://www.geeksforgeeks.org/arraylist-of-arraylist-in-java/
			//Each file name will be related to an integer.
			
			List<List<Object>> fileResults = new ArrayList<List<Object>>();
	        
			//For each file read through it and perform the same code as in the 
			//original if statement to check for matching terms.
			for(int i = 0;i<textFileName.size();i++)
			{
				File userFile = new File(textFileName.get(i)); //Open file.
				
				//Create bufferedreader object.
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
						//Read the current line, split it into an array that contains each word, loop through array
						//and check if searchTerm matches.
						
						//Debug Line
						y=0;
						System.out.println("Inside while");
						
						//Split currentLine into an array that contains each word.
						String[] separatedWords = currentLine.split(" ");
						
						
						//Get the length of the array and loop through it.
						while(y<separatedWords.length)
						{
								//DEBUG
								//System.out.println("word["+y+"] = "+separatedWords[y]);
								//If current line has any words matching the searchTerms.
								if((separatedWords[y]).equals(searchTerms))
								{
									//Debug line
									System.out.println("Search term matched.");
							
									//Increase matched by 1.
									numOfMatches1++;
								}
							
								y++;
						}//end while
							
					}//end while
						
					
				}//end try
				
				catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				//After the file has been searched, add the number of matches to
				//the fileResults lists list. 
				//https://stackoverflow.com/questions/14767697/dynamically-creating-arraylist-inside-a-loop
				List<Object> innerList = new ArrayList<>();
				fileResults.add(innerList);
				
				//Open the first list in the fileResults list-of-lists, then add the current file being read to
				//that list. Then add the numOfMatches found in the list.
				fileResults.get(i).add(textFileName.get(i));
				fileResults.get(i).add(numOfMatches1);
				
				//Reset numOfMatches for the next file.
				numOfMatches1 = 0; 
				
				}//end for
				
			//After each file is read and the fileMatchesArray array has been filled, display the results back to 
			//the GUI.
			/*System.out.println("Displaying the reulsts");
			System.out.println(fileResults.size());
			System.out.println(fileResults.get(0).size());
			System.out.println(fileResults.get(0));
			System.out.println(fileResults.get(0).get(0));
			System.out.println(fileResults.get(0).get(1));
			System.out.println(fileResults.get(0).get(0));
			System.out.println("index 1");
			System.out.println(fileResults.get(1));
			System.out.println("index 2");
			System.out.println(fileResults.get(2));
			System.out.println(fileResults.get(2));*/
		//	System.out.println("After 2");
			//System.out.println(fileResults.get(3).get(3));
			//System.out.println("After 3");
			
			
			//Call quickSort from Control.java???? Interface needed, dont put quicksort in control
			System.out.println("Before quicksort");
			System.out.println(fileResults.get(0));
			System.out.println(fileResults.get(1));
			System.out.println(fileResults.get(2));
			System.out.println(fileResults.get(3));
			//System.out.println(fileResults.get(4));
			
			sortBegin(fileResults);

			System.out.println("After quicksort");
			System.out.println(fileResults.get(0));
			System.out.println(fileResults.get(1));
			System.out.println(fileResults.get(2));
			System.out.println(fileResults.get(3));
			//System.out.println(fileResults.get(4));
			
			}//end else if
			
			
		
		//If an invalid file name is entered, inform user.
	
		else
		{
			System.out.println("Invalid filename entered.");
		}//end else

	
	}//end compareString

	//-----------------------------------------------------------------------------------------------------------------
	
	//Getters and Setters
	//
	public String getFileName() {
		return fileName;
	}

	//setFileName must check if the file name matches a file available.
	public void setFileName(String passedFileName) {
		//Read the names of the text files that are in the project folder.
		File Textfiles = new File("C:\\Users\\C20384993\\eclipse-workspace\\OOP-CA-C20384993\\TextFiles");
		File[] resources = Textfiles.listFiles();
		
		String selectedFile = "";
		
		//Go through the project folder to try and find the file name entered.
		for (File file : resources)
		{
		    if (file.isFile())
		    {
		    	//Debug
		        System.out.println("Each file: "+file.getName());
		        
		        //Set selectedFile as the found filename.
		        if(passedFileName.equals(file.getName()) == true)
		        {
		        	this.fileName = passedFileName;
		        }//end if
		        
		        //If the file isn't found or an invalid filename is entered, default to searching all files.
		        else
		        {
		        	this.fileName = "empty";
		        }
		    }
		}
		
		System.out.println(getFileName());
		
	}//end setFileName

	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}

	
	
}
