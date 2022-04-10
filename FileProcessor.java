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
	//
	//compareString: Read the file and compare the passed string to the text inside it.
	//Check that a file was passed and a string was passed.
	//There are 2 courses for the method:
	//1.) If the user enters a specific file to search through, the method will search only that file for the 
	//search terms.
	//2.) If no filenames are entered, then all files will be searched for the search term(s).
	//
	//Returns an ArrayList.
	//
	//Buffered Reader code: https://www.guru99.com/buffered-reader-in-java.html
	//0: separate
	//1: combined
	public ArrayList<TextFile> compareString(String passedFileName, String[] searchTerms, int separateOrCombined)
	{
		//Read the names of the text files that are in the TextFiles folder.
		File Textfiles = new File("C:\\Users\\C20384993\\eclipse-workspace\\OOP-CA-C20384993\\TextFiles");
		//Create array to store the file names as strings.
		File[] resources = Textfiles.listFiles();
		
		//fileResults ArrayList will store a TextFile.java object for each file read/a single file.
		//
		ArrayList<TextFile> fileResults = new ArrayList<TextFile>();
		
		//Variables
		//
		String selectedFile = ""; //Store the file name string that was selected and found in the project folder.
		boolean fileFound = false; //Used in if statement to check if passedFileName was found/did match.

		
		//Go through the project folder to try and find the file name entered.
		for (File file : resources)
		{
			//Check it's a file and not a directory.
		    if (file.isFile())
		    {
		    	//Debug
		        System.out.println(file.getName());
		        System.out.println(file.exists());
		        
		        //Set selectedFile as the found filename and fileFound to true. 
		        //These will be used in option 1 of the if/if else statement.
		        if(passedFileName.equals(file.getName()) == true)
		        {
		        	selectedFile = passedFileName;
		        	fileFound = true;
		        }//end if
		    }
		}
		
		
		//Count how many time the search term(s) was matched. Integer Object used so it can be put into the array list.
		Integer numOfMatches = 0;
		
		//Count how many words are in the file. Increments by the number of words found on the current line.
		int wordCount = 0;
		
		
		
		//Debug line
		System.out.println(searchTerms);
		
		
		//1.) Specific filename && search term, i.e. search through given file.
		//Only if the user has specified a file to search, a search term to search for, and the specified file was found.
		if(passedFileName != null && searchTerms != null && fileFound == true)
		{
			//Debug line
			System.out.println("Inside if");
			File userFile = new File(fileName); //Open the selected file.
			String currentLine = ""; //Stores current line of text from file.
			
			System.out.println("userFile = "+userFile);
			
			int i = 0; //Integer for looping through the array.
			int y = 0; //Used in the second while loop to go through the array of words from the current line. 
			
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
			
			System.out.println("combined searchTerms = "+searchTermsLine);
			int nextWords = 0;
			
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
					
					//Add the number of words found in the current line of the file to the word count.
					wordCount = wordCount+separatedWords.length;
					
					
					//Get the length of the array(number of words on the current line) and loop through it.
					while(y<separatedWords.length)
					{
						System.out.println("word["+y+"] = "+separatedWords[y]);
						//Determine how the search terms will be compared.
						//0: Separately, increase numOfMatches every time one of any of the words in the array match.
						if(separateOrCombined == 0 || separateOrCombined == 2)
						{
							System.out.println("separate");
							//If current line has any words matching the searchTerms.
							//Check the current word against every term in the searchTerms array.
							for(i=0;i<searchTerms.length;i++)
							{	
								//No case matching
								if(separateOrCombined == 0)
								{
									//Get current word as lower case and check if it equals the current searchTerm also lowercase.
									//E.g. Day, day, DAY, how user entered it.
									//
									System.out.println("lowerCase = "+separatedWords[y].toLowerCase());
									
									if((separatedWords[y]).equals(searchTerms[i]) || separatedWords[y].toLowerCase().equals(searchTerms[i].toLowerCase()))
									{
										//Debug line
										System.out.println("Search term matched.");
						
										//Increase matched by 1.
										numOfMatches++;
									}//end if
									
								}//end if
								
								//If case matching is selected.
								else if(separateOrCombined == 2)
								{
									if((separatedWords[y]).equals(searchTerms[i]))
									{
										//Debug line
										System.out.println("Search term matched.");
						
										//Increase matched by 1.
										numOfMatches++;
									}//end if
								}//end else
								
							}//end for
							
							y++;
						}//end if
						
						
						//1: Combined, increase NumOfMatches when the same number of words on the line
						//match up with the array of search terms order.
						else if(separateOrCombined == 1 || separateOrCombined == 3)
						{
							System.out.println("combined");
							System.out.println("y = "+y);
							
							//With case matching.	
							if(separateOrCombined == 3)
							{
								//Only do if there are enough words left in the line to equal the same amount of words in searchTerms.
								if(separatedWords.length-y > searchTerms.length-1)
								{
								
									//Read consecutive words from the line equal to the length of the searchTerms array.
									//Set first word to that of separatedWords[y], then add on words from separatedWords until
									//fileLine has the same number of words as the searchTerms array.
									String fileLine = separatedWords[y];
									for(nextWords = 1;nextWords < searchTerms.length;nextWords++)
									{
										fileLine = fileLine+" "+separatedWords[y+nextWords]; //Current string + space + next word
									}//end for
								
									System.out.println("combined file line = "+fileLine);
								
									//If the current consecutive words from the file match the searchTerms.
									if((fileLine).equals(searchTermsLine))
									{
										//Debug line
										System.out.println("Search term matched.");
						
										//Increase matched by 1.
										numOfMatches++;
									}//end if
									
								}//end if
								
							}//end if
							
							//No case matching
							else if(separateOrCombined == 1)
							{
								//Only do if there are enough words left in the line to equal the same amount of words in searchTerms.
								if(separatedWords.length-y > searchTerms.length-1)
								{
								
									//Read consecutive words from the line equal to the length of the searchTerms array.
									//Set first word to that of separatedWords[y], then add on words from separatedWords until
									//fileLine has the same number of words as the searchTerms array.
									String fileLine = separatedWords[y];
									for(nextWords = 1;nextWords < searchTerms.length;nextWords++)
									{
										fileLine = fileLine+" "+separatedWords[y+nextWords]; //Current string + space + next word
									}//end for
								
									System.out.println("combined file line = "+fileLine);
								
									//If the current consecutive words from the file match the searchTerms.
									if((fileLine).equals(searchTermsLine) || fileLine.toLowerCase().equals(searchTermsLine.toLowerCase()))
									{
										//Debug line
										System.out.println("Search term matched.");
						
										//Increase matched by 1.
										numOfMatches++;
									}//end if
									
								}//end if
							}//end else if
							y++;
							
							
						}//end else if
						
						//Increment i.
						//i++;
					}//end while
						
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.out.println("numOfMatches = "+numOfMatches);
			System.out.println("wordCount = "+wordCount);
			//System.out.println();
			System.out.println("Percentage = "+((float)numOfMatches/wordCount)*100);
			
			//Selected only 1 file to search, so add this file and its numOfMatches to fileResults, a new TextFile object.
			//But only if it contains at least 1 search term match.
			if(numOfMatches > 0)
			{
				fileResults.add(new TextFile(passedFileName,numOfMatches,((float)numOfMatches/wordCount)*100));
			}
			
			System.out.println(numOfMatches);
			
			return fileResults;
			
		}//end if
		
		//---------------------------------------------------------------------------------------------------------------
		
		//OPTION 2
		//2.) No specific filename && search term, i.e. search through every file.
		
		//Find file names in folder.
		//https://stackoverflow.com/questions/52369327/how-to-read-data-from-all-files-one-by-one-in-a-folder-with-java
		//
		//If the passedFileName is set to "empty", the user either wants to search all files or the file they want to search isn't there.
		else if(passedFileName.equals("empty") && searchTerms != null)
		{
			System.out.println("2.) No specific filename && have search term");
			
			
			String currentLine = ""; //Stores current line of text from file.
			int y = 0; //Used in the second while loop to go through the array of words from the current line. 
			
			
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
			
			System.out.println("combined searchTerms = "+searchTermsLine);

			//Fill textFileName with each text file name in the project folder.
			for (File file : resources)
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
			
	        
			//For each file read through it and perform the same code as in the 
			//original if statement to check for matching terms.
			for(int i = 0;i<textFileName.size();i++)
			{
				File userFile = new File(textFileName.get(i)); //Open file.
				
				System.out.println("\nNow on file: "+textFileName.get(i));
				
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
						
						//Debug Line
						y=0;
						System.out.println("Inside while");
						
						//Split currentLine into an array that contains each word.
						String[] separatedWords = currentLine.split(" ");
						
						//Add the number of words found in the current line of the file to the word count.
						wordCount = wordCount+separatedWords.length;
						
						
						//Get the length of the array(current line) and loop through it.
						while(y<separatedWords.length)
						{
							//Separate
							if(separateOrCombined == 0 || separateOrCombined == 2)
							{
								//If case matching is selected.
								if(separateOrCombined == 2)
								{
									for(int v=0;v<searchTerms.length;v++)
									{
										//DEBUG
										//System.out.println("word["+y+"] = "+separatedWords[y]);
										//If current line has any words matching the searchTerms.
										if((separatedWords[y]).equals(searchTerms[v]))
										{
											//Debug line
											System.out.println("Search term matched. separatedWords ["+y+"] = "+separatedWords[y]);
											System.out.println("searchTerms["+v+"] = "+searchTerms[v]);
								
											//Increase matched by 1.
											numOfMatches++;
											
										}//end if
										
									}//end for
								}
								
								//No case matching
								else if(separateOrCombined == 0)
								{
									for(int v=0;v<searchTerms.length;v++)
									{
										//DEBUG
										//System.out.println("word["+y+"] = "+separatedWords[y]);
										//If current line has any words matching the searchTerms.
										
										//If the current word in lower case equals the current search term in lower case.
										if((separatedWords[y]).equals(searchTerms[v]) || separatedWords[y].toLowerCase().equals(searchTerms[v].toLowerCase()))
										{
											//Debug line
											System.out.println("Search term matched. separatedWords ["+y+"] = "+separatedWords[y]);
											System.out.println("searchTerms["+v+"] = "+searchTerms[v]);
							
											//Increase matched by 1.
											numOfMatches++;
										
										}//end if
									
									}//end for
								}
							
								y++;
								
							}//end if
							
							//For exact phrase matches.
							//
							else if(separateOrCombined == 1 || separateOrCombined == 3)
							{
								//With case matching
								if(separateOrCombined == 3)
								{
									if(separatedWords.length-y > searchTerms.length-1)
									{
										//fileLine stores the current word + words after it as one string equal in length to the combined search
										//terms.
										String fileLine = separatedWords[y];
									
										for(nextWords = 1;nextWords < searchTerms.length;nextWords++)
										{
											fileLine = fileLine+" "+separatedWords[y+nextWords];
										}//end for
									
										System.out.println("combined file line = "+fileLine);
									
										if((fileLine.equals(searchTermsLine)))
										{
											System.out.println("Search terms matched.");
										
											numOfMatches++;
										}//end if
									
									}//end if
								
								}//end if
								
								//No case matching
								else if(separateOrCombined == 1)
								{
									if(separatedWords.length-y > searchTerms.length-1)
									{
										//fileLine stores the current word + words after it as one string equal in length to the combined search
										//terms.
										String fileLine = separatedWords[y];
									
										for(nextWords = 1;nextWords < searchTerms.length;nextWords++)
										{
											fileLine = fileLine+" "+separatedWords[y+nextWords];
										}//end for
									
										System.out.println("combined file line = "+fileLine);
									
										if(fileLine.equals(searchTermsLine) || fileLine.toLowerCase().equals(searchTermsLine.toLowerCase()))
										{
											System.out.println("Search terms matched.");
										
											numOfMatches++;
										}//end if
									
									}//end if
								}//end else if
								y++;
								
							}//else if
								
						}//end while
							
					}//end while
					//End of file
					
					}//end try
		
				
				catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				//After the file has been searched, create an TextFile object of the file and store it in fileResults.
				//the fileResults Arraylists. 
				//https://stackoverflow.com/questions/14767697/dynamically-creating-arraylist-inside-a-loop
				//TextFile tf1 = new TextFile(textFileName.get(i),numOfMatches1);
				//System.out.println("numOfmATCHES");
				System.out.println("Percentage = "+((float)numOfMatches/wordCount)*100);
				System.out.println("NumOfMatches = "+numOfMatches);
				
				//Only add the file to the list if it contains at least 1 search term match.
				if(numOfMatches > 0)
				{
					fileResults.add(new TextFile(textFileName.get(i),numOfMatches,((float)numOfMatches/wordCount)*100));
				}
				
				//Reset numOfMatches for the next file.
				numOfMatches = 0; 
				y = 0;
				wordCount = 0;
				System.out.println("Next file ***********");
				
			}//end for
				
			//After each file is read and the fileMatchesArray array has been filled, display the results back to 
			//the GUI.
			

			//https://www.youtube.com/watch?v=wzWFQTLn8hI
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
			
			//System.out.println("top match = "+fileResults.get(0).getTextFileName());
			
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
		        	System.out.println("file");
		        	this.fileName = passedFileName;
		        	return true;
		        }//end if
		        
		        //If the file isn't found or an invalid filename is entered, default to searching all files.
		        //Return false.
		        else
		        {
		        	this.fileName = "empty";
		        }
		    }
		}
		
		return false;
		
	}//end setFileName

	
	public String[] getSearchText() {
		return Arrays.copyOf(searchText, searchText.length);
	}

	
	public void setSearchText(String searchText) {
		
		//Take the searchText and split it into a string array. This will allow for multiple search terms to be entered.
		String [] searchTextSplit = searchText.split(" ");
		this.searchText = Arrays.copyOf(searchTextSplit, searchTextSplit.length);
	}

	
	
}//end FileProcessor
