package com.oop.eng;

public class TextFile{
	
	private String textFileName;
	private int searchMatches;
	private float matchPercentage;
	

	//Constructor
	//
	public TextFile(String newTextFileName, int newSearchMatches)
	{
		this.setTextFileName(newTextFileName);
		this.setSearchMatches(newSearchMatches);
	}
	

	//Getters and Setters
	//
	public String getTextFileName() {
		//System.out.println("textFileName = "+this.textFileName);
		return textFileName;
	}

	public void setTextFileName(String textFileName) {
		this.textFileName = textFileName;
	}

	public int getSearchMatches() {
		//System.out.println("searchMatches = "+this.searchMatches);
		return searchMatches;
	}

	public void setSearchMatches(int searchMatches) {
		this.searchMatches = searchMatches;
	}
	
	public float getMatchPercentage() {
		return matchPercentage;
	}
	
	public void setMatchPercentage(float matchPercentage) {
		this.matchPercentage = matchPercentage;
	}

	
}//end class