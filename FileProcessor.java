package com.oop.eng;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

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
	//compareString: Read the file and compare the passed string to the text inside it.
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
		//Read the names of the text files that are in the project folder.
		File Textfiles = new File("C:\\Users\\C20384993\\eclipse-workspace\\OOP-CA-C20384993");
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
		
		
		//Variables. These will be used in both options.
		int numOfMatches = 0; //Count how many time the search was matched.
		//Debug line
		System.out.println(searchTerms);
		
		
		//1.) Specific filename && search term, i.e. search through given file.
		//if(("Meaningful Work.txt").equals(fileName) || ("Rabies and Vaccines.txt").equals(fileName) || 
		//("Phone data and Machine Learning Humanitarian Aid.txt").equals(fileName) && searchTerms != null)
		if(passedFileName != null && searchTerms != null && fileFound == true)
		{
			//Debug line
			System.out.println("Inside if");
			File userFile = new File(fileName); //Open file
			String currentLine = ""; //Stores current line of text from file.
			
			int i = 0; //Integer for looping through the array.
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
		
		
		
		//OPTION 2
		//2.) No specific filename && search term, i.e. search through every file.
		
		//Find file names in folder.
		//https://stackoverflow.com/questions/52369327/how-to-read-data-from-all-files-one-by-one-in-a-folder-with-java
		else if(passedFileName == null && searchTerms != null)
		{
			System.out.println("2.) No specific filename && have search term");
			
			int numOfMatches1 = 0; //Count how many time the search was matched.
			String currentLine = ""; //Stores current line of text from file.
			int y = 0; //Used in the second while loop to go through the array of words from the current line. 
			
			//Read the names of the text files that are in the project folder.
			File filesFolder = new File("C:\\Users\\C20384993\\eclipse-workspace\\OOP-CA-C20384993");
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
			
			//For each file (except .classpath and .project) read through it and perform the same code as in the 
			//if statement to check for matching terms.
			for(int i = 0;i<textFileName.size();i++)
			{
				if((textFileName.get(i)).equals(".classpath") == false && textFileName.get(i).equals(".project") == false)
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
								System.out.println("word["+y+"] = "+separatedWords[y]);
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
				
				
				}//end if
				
			}//end for
			
			System.out.println(numOfMatches1);
			
		}//end else if

	
	}//end compareString

		
	
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
