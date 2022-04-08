package com.oop.eng;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class SearchEngineGUI implements ActionListener{
	
	//Frame labels
	JFrame GUIFrame;

	JLabel label1;
	
	//Button labels.
	JButton searchButton;
	JButton fileButton;
	JButton separateWordSearch; //If user wants to search for files with the search terms anywhere.
	JButton combinedWordSearch; //If user wants to search for a specific phrase.
	
	//Panel labels.
	JPanel optionPanel; //Across top, contains components for searching and selecting files.
	JPanel mainPanel; //The main panel. optionPanel and matchesPanel will be added to this one.
	JPanel matchesPanel; //Bottom, contains 2 boxes to display results and most significant files.
	
	
	//Text field labels.
	JTextField textfileField;
	JTextField searchField;
	
	//Create text area that will display various information, e.g. files searched, search terms, list of files with 
	//highest match at top, etc.
	JTextArea resultsArea;
	JTextArea searchOptions; //Display selected search options.
	
	//Stores selection for separate or combined phrase searching.
	//0: separate (default setting)
	//1: combined
	int phraseOption = 0;
	
	//Constructor
	public SearchEngineGUI()
	{
		
		//Create object of FileProcessor class. The constructor is passed 2 strings: filename, search term(s).
		FileProcessor fProcessor = new FileProcessor("Empty","Empty");
		
		
		//Create the components of the GUI.
		//
		GUIFrame = new JFrame("My Search Engine");
		
		//Set the initial options of the Frame.
		GUIFrame.setVisible(true);
		GUIFrame.setSize(400,400);
		
		GUIFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		//Create panels.
		optionPanel=new JPanel(); 
		matchesPanel=new JPanel();
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		GUIFrame.add(mainPanel);
		
		//Create file select button.
		fileButton= new JButton("Select file.");
		//Create Search button.
		searchButton= new JButton("Search.");
		//Create text field for entering text file.
		textfileField = new JTextField("",20);
		//Create text field for entering search term.
		searchField = new JTextField("",20);
		//Create text area that will display various information,
		resultsArea = new JTextArea(10,20);
		
		searchOptions = new JTextArea(10,20);
		
		separateWordSearch= new JButton("Any words");
		
		combinedWordSearch= new JButton("Specific phrase");
		
		
		//Add components to the main panel.
		optionPanel.setLayout(new GridLayout(2, 2, 55, 85)); //2x2 layout with gap 35 horizontal, 55 vertical
		optionPanel.setPreferredSize(new Dimension(GUIFrame.getWidth(),200));
		optionPanel.add(fileButton);
		optionPanel.add(searchButton);
		optionPanel.add(separateWordSearch);
		optionPanel.add(combinedWordSearch);
		optionPanel.add(textfileField);
		optionPanel.add(searchField);
		mainPanel.add(optionPanel, BorderLayout.NORTH);
		
		matchesPanel.setLayout(new FlowLayout());
		matchesPanel.setPreferredSize(new Dimension(GUIFrame.getWidth(),200));
		matchesPanel.add(resultsArea);
		matchesPanel.add(searchOptions);
		mainPanel.add(matchesPanel, BorderLayout.SOUTH);

		
		
		//Stop user from entering text into the results area.
		resultsArea.setEditable(false); 
		
		
		//Add titles to the buttons.
		//https://www.tutorialspoint.com/how-to-create-titled-border-for-a-component-in-java
		searchField.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), 
		"Enter search term(s)",TitledBorder.LEFT, TitledBorder.TOP));
		textfileField.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), 
		"Select files",TitledBorder.LEFT, TitledBorder.TOP));
		resultsArea.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), 
		"Results",TitledBorder.LEFT, TitledBorder.TOP));
		searchOptions.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), 
		"Current Search Parameters",TitledBorder.LEFT, TitledBorder.TOP));
		
		
		//Hover over texts
		fileButton.setToolTipText("Enter the name of the file you wish to search and click to select it. Leave it blank to "
				+ "search every file in the directory.");
		fileButton.addActionListener(this);
		
		searchButton.setToolTipText("Click this once you have entered the file name and search terms to begin the search.");
		searchButton.addActionListener(this);
		
		//Component Sizes
		searchButton.setPreferredSize(new Dimension(80,40));
		
	
		
		//Action listeners for search button and other buttons.
		//
		
		//Search the file(s) when the search button is clicked.
		searchButton.addActionListener(new ActionListener ()
		{
		
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				//if(e.getSource()==searchButton)
				//Checks filename entered and and search terms, then compares them to produce results.
				{
					JOptionPane.showMessageDialog(searchButton,"Searching files.");
					ArrayList<TextFile> resultsArrayList = new ArrayList<TextFile>();
					
					//Empty the resultsArrayList before entering new data.
					resultsArrayList.removeAll(resultsArrayList);
					resultsArrayList = fProcessor.compareString(fProcessor.getFileName(), fProcessor.getSearchText(), phraseOption);
					
					if(resultsArrayList.size() > 0)
					{	
					resultsArea.setText(null);
					resultsArea.append(resultsArrayList.get(0).getTextFileName());
					resultsArea.append("\n");
					resultsArea.append(String.valueOf(resultsArrayList.get(0).getSearchMatches()));
					resultsArea.append("\n");
					resultsArea.append(String.valueOf(resultsArrayList.get(0).getMatchPercentage()));
					resultsArea.append("\n");
					}
					
				}
			}
		});
		
		
		//Store input in textfile field if file button is clicked.
		fileButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
					String userInput = textfileField.getText();
					if(fProcessor.setFileName(userInput) == false)
					{
						resultsArea.append("A file with that name could not be found.");
						resultsArea.append("\n");
						resultsArea.append("Every file will be searched instead.");
					}//end if
					System.out.print("selected .getfilename = "+fProcessor.getFileName());
			}
		});
		
		
		
		
		//Listener function to store input for filename from file text field.
		textfileField.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
					String userInput = textfileField.getText();
					fProcessor.setFileName(userInput);
					System.out.print("selected .getfilename = "+fProcessor.getFileName());
			}
		});
		
		
		//Listener function to store input of search term(s).
		searchField.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
					String userInput = searchField.getText();
					fProcessor.setSearchText(userInput);
					System.out.print(fProcessor.getSearchText());
			}
		});
		
		
		//Listener function to store combined or separate option selection.
		separateWordSearch.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
					//Set to 0 for separate word searching.
					phraseOption = 0;
					searchOptions.append("Search: Separate");
			}
		});
		
		
		//Listener function to store combined or separate option selection.
		combinedWordSearch.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
					//Set to 1 for combined word searching.
					phraseOption = 1;
					searchOptions.append("Search: Combined");
			}
		});
		
		
		
		
		//After clicking search button, If a correct filename is chosen && the search term is entered.
		//Search through file, or if no particular file is selected then search all of them.
		
		
	}//end Constructor
	
	
	public static void main(String[] args)
	{
		
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
