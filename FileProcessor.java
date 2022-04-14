package com.oop.eng;

import java.io.BufferedReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

import static java.util.stream.Collectors.*;

@SuppressWarnings("unused")
public class FileProcessor{
	
	//Attributes
	//
	private String fileName; //Store the name of the file.
	private String[] searchText; //Store the user's search terms.
	File userFile;

	//Constructor
	//
	public FileProcessor(String newFileName, String newSearchText)
	{
		this.setFileName(newFileName);
		this.setSearchText(newSearchText);
	}
	
	//Methods
	/*
	compareString: Read the file and compare the passed string to the text inside it.
	The files to be read are stored in the TextFiles folder. The path should look like this:
	"C:\\Users\\YourUserNameHere\\eclipse-workspace\\OOP-CA-C20384993\\TextFiles"
	
	Return type: ArrayList <TextFile> : This is a list of all files that contained at least 1 search term match.
	The sorted array list will be returned to SearchEngineGUI and be displayed there in the searchButton ActionListener.
	
	Parameters: 
	String passedFileName, this is the filename the user entered. If no file was entered or the file couldn't be 
	found, it will be set to "". An if statement check will determine this.
	
	String[] searchTerms, The search terms entered by the user. They are stored in an array, with each word having its own
	index position.
	
	int separateOrcombined, Determines how the search will be performed. 
	0: separate (default setting)
	1: combined
	2: separate (case matching)
	3: combined (case matching)
	
	There are 4 courses for this method:
	1.) If the user enters a specific file to search through, the method will search only that file for the 
	search terms.
	1.i) Separate Search
	1.ii) Combined Search
	1.iii) Separate Search (Case Matching)
	1.iv) Combined Search (Case Matching)
	
	2.) If no filenames are entered, then all files will be searched for the search term(s).
	2.i) Separate Search
	2.ii) Combined Search
	2.iii) Separate Search (Case Matching)
	2.iv) Combined Search (Case Matching)
	
	Buffered Reader code: https://www.guru99.com/buffered-reader-in-java.html
	
	*/
	public ArrayList<TextFile> compareString(String passedFileName, String[] searchTerms, int separateOrCombined)
	{
		//Read the names of the text files that are in the TextFiles folder.
		File Textfiles = new File("C:\\Users\\C20384993\\eclipse-workspace\\OOP-CA-C20384993\\TextFiles");
		//Create array to store the file names as strings.
		File[] resources = Textfiles.listFiles();
		
		//fileResults ArrayList will store a TextFile.java object for each file read/a single file.
		ArrayList<TextFile> fileResults = new ArrayList<TextFile>();
		
		//Variables
		
		//Used in if statement to check if passedFileName was found/did match.
		boolean fileFound = false; 
		//Count how many time the search term(s) was matched. Integer Object used so it can be put into the array list.
		Integer numOfMatches = 0;
		//Count how many words are in the file. Increments by the number of words found on the current line.
		//Used for calculating matchPercentage.
		int wordCount = 0;

		
		//Go through the project folder to try and find the file name entered.
		for (File file : resources)
		{
			//Check it's a file and not a directory.
		    if (file.isFile())
		    {
		        
		        //Set selectedFile as the found filename and fileFound to true. 
		        //These will be used in option 1 of the if/if else statement.
		        if(passedFileName.equals(file.getName()) == true)
		        {
		        	fileFound = true;
		        }//end if
		    }
		}
		
		
		
		//1.) Specific filename && search term, i.e. search through given file.
		//Only if the user has specified a file to search, a search term to search for, and the specified file was found.
		if(passedFileName != null && searchTerms != null && fileFound == true)
		{
			File userFile = new File(fileName); //Open the selected file.
			String currentLine = ""; //Stores current line of text from file.

			
			int currentSearchTerm = 0; //Integer for looping through the array.
			int currentWord = 0; //Used in the second while loop to go through the array of words from the current line. 
			
			//Create a bufferedReader to read the file.
			//
			BufferedReader myBufferReader = null;
			try {
				myBufferReader = new BufferedReader(new FileReader("C:\\Users\\C20384993\\eclipse-workspace\\OOP-CA-C20384993\\TextFiles\\"+userFile));
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			//Store the search terms as a single consecutive string for the else if if(combinedOrSeparated == 1).
			String searchTermsLine = searchTerms[0];
			
			for(int searchLineLoop = 1;searchLineLoop < searchTerms.length;searchLineLoop++)
			{
				searchTermsLine = searchTermsLine+" "+searchTerms[searchLineLoop]; //Current string + space + next word in searchTerms.
			}//end for
			
			int nextWords = 0; //Looping variable.
			
			//Read through the file.
			//
			try {
				while((currentLine = myBufferReader.readLine()) != null)
				{
					//Read the current line, split it into an array that contains each word, loop through array
					//and check if searchTerm matches.
					
					//Reset currentWord to 0 for the new line.
					currentWord=0;
					
					//Split currentLine into an array that contains each word.
					String[] separatedWords = currentLine.split(" ");
					
					//Add the number of words found in the current line of the file to the word count.
					wordCount = wordCount+separatedWords.length;
					
					
					//Get the length of the array(number of words on the current line) and loop through it.
					while(currentWord<separatedWords.length)
					{
						//Determine how the search terms will be compared.
						//0: Separately, increase numOfMatches every time one of any of the words in the array match.
						if(separateOrCombined == 0 || separateOrCombined == 2)
						{
							//If current line has any words matching the searchTerms.
							//Check the current word against every term in the searchTerms array.
							for(currentSearchTerm=0;currentSearchTerm<searchTerms.length;currentSearchTerm++)
							{	
								//1.i) No case matching
								if(separateOrCombined == 0)
								{
									//Get current word as lower case and check if it equals the current searchTerm also lowercase.
									//E.g. Day, day, DAY, how user entered it. Also check for common sentence endings 
									//e.g. commas, full stops, question marks.
									//
									if((separatedWords[currentWord]).equals(searchTerms[currentSearchTerm]) || 
											separatedWords[currentWord].toLowerCase().equals(searchTerms[currentSearchTerm].toLowerCase()) ||
											separatedWords[currentWord].toLowerCase().equals((searchTerms[currentSearchTerm]+",").toLowerCase()) ||
											separatedWords[currentWord].toLowerCase().equals((searchTerms[currentSearchTerm]+".").toLowerCase()) ||
											separatedWords[currentWord].toLowerCase().equals((searchTerms[currentSearchTerm]+"?").toLowerCase()) ||
											separatedWords[currentWord].toLowerCase().equals((searchTerms[currentSearchTerm]+"!").toLowerCase()))
									{
						
										//Increase matched by 1.
										numOfMatches++;
										
									}//end if
									
								}//end if
								
								//1.iii) If separate case matching is selected.
								else if(separateOrCombined == 2)
								{
									if((separatedWords[currentWord]).equals(searchTerms[currentSearchTerm]))
									{						
										//Increase matched by 1.
										numOfMatches++;
									}//end if
									
								}//end else
								
							}//end for
							
							currentWord++;
							
						}//end if
						
						
						//1: Combined, increase NumOfMatches when the same number of words on the line
						//match up with the array of search terms order.
						else if(separateOrCombined == 1 || separateOrCombined == 3)
						{
							
							//1. iv) With case matching.	
							if(separateOrCombined == 3)
							{
								//Only do if there are enough words left in the line to equal the same amount of words in searchTerms.
								if(separatedWords.length-currentWord > searchTerms.length-1)
								{
								
									//Read consecutive words from the line equal to the length of the searchTerms array.
									//Set first word to that of separatedWords[currentWord], then add on words from separatedWords until
									//fileLine has the same number of words as the searchTerms array.
									String fileLine = separatedWords[currentWord];
									for(nextWords = 1;nextWords < searchTerms.length;nextWords++)
									{
										fileLine = fileLine+" "+separatedWords[currentWord+nextWords]; //Current string + space + next word
									}//end for

								
									//If the current consecutive words from the file match the searchTerms.
									if((fileLine).equals(searchTermsLine))
									{
										//Increase matched by 1.
										numOfMatches++;
										
									}//end if
									
								}//end if
								
							}//end if
							
							//1.ii) No case matching
							else if(separateOrCombined == 1)
							{
								//Only do if there are enough words left in the line to equal the same amount of words in searchTerms.
								if(separatedWords.length-currentWord > searchTerms.length-1)
								{
								
									//Read consecutive words from the line equal to the length of the searchTerms array.
									//Set first word to that of separatedWords[currentWord], then add on words from separatedWords until
									//fileLine has the same number of words as the searchTerms array.
									String fileLine = separatedWords[currentWord];
									for(nextWords = 1;nextWords < searchTerms.length;nextWords++)
									{
										fileLine = fileLine+" "+separatedWords[currentWord+nextWords]; //Current string + space + next word
									}//end for

								
									//If the current consecutive words from the file match the searchTerms.
									if((fileLine).equals(searchTermsLine) ||
											fileLine.toLowerCase().equals(searchTermsLine.toLowerCase()) ||
											fileLine.toLowerCase().equals((searchTermsLine+",").toLowerCase()) ||
											fileLine.toLowerCase().equals((searchTermsLine+".").toLowerCase()) ||
											fileLine.toLowerCase().equals((searchTermsLine+"?").toLowerCase()) ||
											fileLine.toLowerCase().equals((searchTermsLine+"!").toLowerCase())
											)
									{
						
										//Increase matched by 1.
										numOfMatches++;
										
									}//end if
									
								}//end if
								
							}//end else if
							
							currentWord++;
							
							
						}//end else if
						
					}//end while
						
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			//Selected only 1 file to search, so add this file and its numOfMatches to fileResults, a new TextFile object.
			//But only if it contains at least 1 search term match.
			if(numOfMatches > 0)
			{
				fileResults.add(new TextFile(passedFileName,numOfMatches,((float)numOfMatches/wordCount)*100));
			}
			
			return fileResults;
			
		}//end if
		
		//---------------------------------------------------------------------------------------------------------------
		
		//OPTION 2
		//2.) No specific filename && search term, i.e. search through every file.
		
		//Find file names in folder.
		//https://stackoverflow.com/questions/52369327/how-to-read-data-from-all-files-one-by-one-in-a-folder-with-java
		//
		//If the passedFileName is set to "", the user either wants to search all files or the file they want to search isn't there.
		else if(passedFileName.equals("") && searchTerms != null)
		{
			
			
			String currentLine = ""; //Stores current line of text from file.
			int currentWord = 0; //Used in the second while loop to go through the array of words from the current line. 
			
			
			//ArrayList to store each file name.
			//
			ArrayList<String> textFileName = new ArrayList<String>();
			
			//fileResults ArrayList will store a TextFile.java object for each file read.
			
			//Used in separateOrCombined == 1
			int nextWords = 0;
			
			String searchTermsLine = searchTerms[0];
			for(int searchLineLoop = 1;searchLineLoop < searchTerms.length;searchLineLoop++)
			{
				searchTermsLine = searchTermsLine+" "+searchTerms[searchLineLoop]; //Current string + space + next word in searchTerms.
			}//end for
			

			//Fill textFileName with each text file name in the project folder.
			for (File file : resources)
			{
			    if (file.isFile())
			    {
			        //Add the file name to the array.
			        textFileName.add(file.getName());
			    }
			}//end for
			
	        
			//For each file read through it and perform the same code as in the 
			//original if statement to check for matching terms.
			for(int currentSearchTerm = 0;currentSearchTerm<textFileName.size();currentSearchTerm++)
			{
				File userFile = new File(textFileName.get(currentSearchTerm)); //Open file.
				
				//Create bufferedreader object.
				BufferedReader myBufferReader = null;
				try {
					myBufferReader = new BufferedReader(new FileReader("C:\\Users\\C20384993\\eclipse-workspace\\OOP-CA-C20384993\\TextFiles\\"+userFile));
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
						
						//Reset currentWord for the reading of the new file.
						currentWord=0;
						
						//Split currentLine into an array that contains each word.
						String[] separatedWords = currentLine.split(" ");
						
						//Add the number of words found in the current line of the file to the word count.
						wordCount = wordCount+separatedWords.length;
						
						
						//Get the length of the array(current line) and loop through it.
						while(currentWord<separatedWords.length)
						{
							//Separate
							if(separateOrCombined == 0 || separateOrCombined == 2)
							{
								//2.iii) Separate case matching is selected.
								if(separateOrCombined == 2)
								{
									for(int currentSearchIndex=0;currentSearchIndex<searchTerms.length;currentSearchIndex++)
									{
										//If current line has any words matching the searchTerms.
										if((separatedWords[currentWord]).equals(searchTerms[currentSearchIndex]))
										{								
											//Increase matched by 1.
											numOfMatches++;
											
										}//end if
										
									}//end for
								}
								
								//No case matching
								else if(separateOrCombined == 0)
								{
									for(int currentSearchIndex=0;currentSearchIndex<searchTerms.length;currentSearchIndex++)
									{
										//If current line has any words matching the searchTerms.
										
										//If the current word in lower case equals the current search term in lower case.
										if((separatedWords[currentWord]).equals(searchTerms[currentSearchIndex]) || 
											separatedWords[currentWord].toLowerCase().equals(searchTerms[currentSearchIndex].toLowerCase()) ||
											separatedWords[currentWord].toLowerCase().equals((searchTerms[currentSearchIndex]+",").toLowerCase()) ||
											separatedWords[currentWord].toLowerCase().equals((searchTerms[currentSearchIndex]+".").toLowerCase()) ||
											separatedWords[currentWord].toLowerCase().equals((searchTerms[currentSearchIndex]+"?").toLowerCase()) ||
											separatedWords[currentWord].toLowerCase().equals((searchTerms[currentSearchIndex]+"!").toLowerCase()))
										{							
											//Increase matched by 1.
											numOfMatches++;
										
										}//end if
									
									}//end for
								}
							
								currentWord++;
								
							}//end if
							
							//For exact phrase matches.
							//
							else if(separateOrCombined == 1 || separateOrCombined == 3)
							{
								//2.iv) Combined case matching
								if(separateOrCombined == 3)
								{
									if(separatedWords.length-currentWord > searchTerms.length-1)
									{
										//fileLine stores the current word + words after it as one string equal in length to the combined search
										//terms.
										String fileLine = separatedWords[currentWord];
									
										for(nextWords = 1;nextWords < searchTerms.length;nextWords++)
										{
											fileLine = fileLine+" "+separatedWords[currentWord+nextWords];
										}//end for

									
										if((fileLine.equals(searchTermsLine)))
										{
											numOfMatches++;
										}//end if
									
									}//end if
								
								}//end if
								
								//2.ii) Combined case matching
								else if(separateOrCombined == 1)
								{
									if(separatedWords.length-currentWord > searchTerms.length-1)
									{
										//fileLine stores the current word + words after it as one string equal in length to the combined search
										//terms.
										String fileLine = separatedWords[currentWord];
									
										for(nextWords = 1;nextWords < searchTerms.length;nextWords++)
										{
											fileLine = fileLine+" "+separatedWords[currentWord+nextWords];
										}//end for
									
										if(fileLine.equals(searchTermsLine) || 
											fileLine.toLowerCase().equals(searchTermsLine.toLowerCase()) ||
											fileLine.toLowerCase().equals((searchTermsLine+",").toLowerCase()) ||
											fileLine.toLowerCase().equals((searchTermsLine+".").toLowerCase()) ||
											fileLine.toLowerCase().equals((searchTermsLine+"?").toLowerCase()) ||
											fileLine.toLowerCase().equals((searchTermsLine+"!").toLowerCase()))
										{
											numOfMatches++;
										}//end if
									
									}//end if
									
								}//end else if
								
								//Move to next word.
								currentWord++;
								
							}//else if
								
						}//end while
							
					}//end while
					
					//End of file
					
					}//end try
		
				
				catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				//After the file has been searched, create a TextFile object of the file and store it in fileResults.
				//the fileResults Arraylists. 
				
				//Only add the file to the list if it contains at least 1 search term match.
				if(numOfMatches > 0)
				{
					fileResults.add(new TextFile(textFileName.get(currentSearchTerm),numOfMatches,((float)numOfMatches/wordCount)*100));
				}
				
				//Reset variables for the next file.
				numOfMatches = 0; 
				currentWord = 0;
				wordCount = 0;
				
			}//end for
				
			//After each file is read and the fileMatchesArray array has been filled, sort and display the results back to 
			//the GUI.
			
			Collections.sort(fileResults, new Comparator<TextFile>()
			{
				public int compare(TextFile textfile1, TextFile textfile2)
				{
					if(textfile1.getSearchMatches() != textfile2.getSearchMatches())
					{
						return Integer.valueOf(textfile2.getSearchMatches()).compareTo(textfile1.getSearchMatches());
					}
					
					else
					{
						return Float.valueOf(textfile2.getMatchPercentage()).compareTo(textfile1.getMatchPercentage());
					}
				}
					
			});
			
			return fileResults;
			
			}//end else if
		
		return null;
			

	
	}//end compareString

	//----------------------------------------------------------------------------------------------------------------------------------
	
	//Getters and Setters
	//
	public String getFileName() {
		return fileName;
	}

	//setFileName must check if the file name matches a file available.
	public boolean setFileName(String passedFileName) {
		//Read the names of the text files that are in the project folder.
		File Textfiles = new File("C:\\Users\\C20384993\\eclipse-workspace\\OOP-CA-C20384993\\TextFiles");
		File[] resources = Textfiles.listFiles();
		
		//Go through the project folder to try and find the file name entered.
		for (File file : resources)
		{
		    if (file.isFile())
		    {   
		        //Set selectedFile as the found filename.
		        if(passedFileName.equals(file.getName()) == true)
		        {
		        	this.fileName = passedFileName;
		        	return true;
		        }//end if
		        
		        //If the file isn't found or an invalid filename is entered, default to searching all files.
		        //Return false.
		        else
		        {
		        	this.fileName = "";
		        }
		    }
		}
		
		return false;
		
	}//end setFileName

	
	public String[] getSearchText() {
		//Must return a copyOf for arrays.
		return Arrays.copyOf(searchText, searchText.length);
	}

	
	public void setSearchText(String searchText) {
		
		//Take the searchText and split it into a string array. This will allow for multiple search terms to be entered.
		String [] searchTextSplit = searchText.split(" ");
		this.searchText = Arrays.copyOf(searchTextSplit, searchTextSplit.length);
	}

	
	
}//end FileProcessor
