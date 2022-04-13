package com.oop.eng;

//Used to store attributes about each file read.
//
public class TextFile{
	
	private String textFileName; //The name of the file.
	private int searchMatches; //The number of times the search term(s) were found in the file.
	private float matchPercentage; //The percentage of words in the file that are search term(s). 
	

	//Constructor
	//
	public TextFile(String newTextFileName, int newSearchMatches, float newMatchPercentage)
	{
		this.setTextFileName(newTextFileName);
		this.setSearchMatches(newSearchMatches);
		this.setMatchPercentage(newMatchPercentage);
	}
	

	//Getters and Setters
	//
	public String getTextFileName() {
		return textFileName;
	}

	public void setTextFileName(String textFileName) {
		this.textFileName = textFileName;
	}

	public int getSearchMatches() {
		return searchMatches;
	}

	public void setSearchMatches(int searchMatches) {
		this.searchMatches = searchMatches;
	}
	
	public float getMatchPercentage() {
		return matchPercentage;
	}
	
	public void setMatchPercentage(float matchPercentage) {
		matchPercentage = Math.round(matchPercentage); //Round the percentage up to remove long decimal places.
		this.matchPercentage = matchPercentage;
	}

	
}//end class