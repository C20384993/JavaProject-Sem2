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
import java.util.Arrays;
import java.util.Iterator;
import java.util.ListIterator;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class SearchEngineGUI implements ActionListener{
	
	//Frame labels
	JFrame GUIFrame;

	//JLabel label1;
	
	//Button group label, used to add radio buttons to it so only 1 can be selected.
	ButtonGroup separateCombinedGroup;
	
	//Radio button labels.
	JRadioButton separateRadio; //If user wants to search for any words in the search terms.
	JRadioButton combinedRadio; //If user wants to search for the specific combination of terms.
	JRadioButton separateCaseMatchRadio; //If user wants to search for any words in the search terms, with case matching.
	JRadioButton combinedCaseMatchRadio; //If user wants to search for a specific combination of the search terms, with case matching.
	
	//Button labels.
	JButton searchButton;
	JButton fileButton;
	
	
	//Panel labels.
	JPanel mainPanel; //The main panel. optionPanel and matchesPanel will be added to this one.
	//
	//Split mainPanel into smaller parts by adding panels to it.
	JPanel topPanel; 
	JPanel bottomPanel; 
	//
	//Split topPanel into two more parts.
	//
	JPanel paramPanel; //Contains searchOptions text area and 2 radio buttons (separate, combined).
	JPanel searchBtnPanel; //Contains search button, search terms text field, filename textfield.
	
	
	
	//Text field labels.
	JTextField textfileField;
	JTextField searchField;
	
	//Create text area that will display various information, e.g. files searched, search terms, list of files with 
	//highest match at top, etc.
	JTextArea resultsArea;
	JTextArea searchOptions; //Display selected search options.
	
	//Scroll labels.
	JScrollPane resultsScroll;
	
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
		GUIFrame.setSize(600,625);
		
		GUIFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		//Create panels.
		topPanel=new JPanel(); 
		bottomPanel=new JPanel();
		paramPanel=new JPanel();
		searchBtnPanel=new JPanel();
		mainPanel = new JPanel();
		
		//Settings for mainPanel.
		mainPanel.setLayout(new GridLayout(2, 1, 0, 0));
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
		resultsArea = new JTextArea(20,40);
		//Create button group.
		separateCombinedGroup = new ButtonGroup();
		//Create radio buttons.
		separateRadio = new JRadioButton();
		combinedRadio = new JRadioButton();
		separateCaseMatchRadio = new JRadioButton();
		combinedCaseMatchRadio = new JRadioButton();
		//Create resultsArea scroll.
		resultsScroll = new JScrollPane(resultsArea);
		resultsScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		separateRadio.setText("Separate");
		combinedRadio.setText("Combined");
		separateCaseMatchRadio.setText("Separate (Case Matching)");
		combinedCaseMatchRadio.setText("Combined (Case Matching)");
		searchOptions = new JTextArea(10,20);
		
		
		//Settings for topPanel.
		topPanel.setLayout(new GridLayout(1, 2, 55, 85)); //1x2 (rowsxcolumns) layout with gap 35 horizontal, 55 vertical
		topPanel.setPreferredSize(new Dimension(GUIFrame.getWidth(),200));
		
		//Settings for paramPanel and searchBtnPanel
		paramPanel.setLayout(new FlowLayout());
		searchBtnPanel.setLayout(new FlowLayout());
		
		//Add components to paramPanel and searchBtnPanel
		paramPanel.add(searchOptions);
		paramPanel.add(separateRadio);
		paramPanel.add(combinedRadio);
		paramPanel.add(separateCaseMatchRadio);
		paramPanel.add(combinedCaseMatchRadio);
		searchBtnPanel.add(searchField);
		searchBtnPanel.add(textfileField);
		searchBtnPanel.add(searchButton);
		
		//Add radio buttons to button group.
		separateCombinedGroup.add(separateRadio);
		separateCombinedGroup.add(combinedRadio);
		separateCombinedGroup.add(separateCaseMatchRadio);
		separateCombinedGroup.add(combinedCaseMatchRadio);
		
		//Add paramPanel and searchBtnPanel to topPanel.
		topPanel.add(paramPanel);
		topPanel.add(searchBtnPanel);

		//Add topPanel to the mainPanel.
		mainPanel.add(topPanel);
		
		//Settings for matchesPanel.
		bottomPanel.setLayout(new BorderLayout());
		bottomPanel.setPreferredSize(new Dimension(GUIFrame.getWidth(),200));
		
		//Components for matchesPanel.
		bottomPanel.add(resultsScroll, BorderLayout.CENTER);
		
		//Add bottomPanel to mainPanel.
		mainPanel.add(bottomPanel);

		
		//Stop user from entering text into the results and search parameters areas.
		resultsArea.setEditable(false); 
		searchOptions.setEditable(false);
		
		
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
		searchButton.setToolTipText("Click this once you have selected your search parameters to begin the search.");
		searchButton.addActionListener(this);
		
		textfileField.setToolTipText("Enter the file name you wish to search. If left blank or the file couldn't be found, every"
				+ " file will be searched.");
		textfileField.addActionListener(this);
		
		searchField.setToolTipText("Enter the terms to search for.");
		searchField.addActionListener(this);
		
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
					//Only search the file(s) if search terms have been entered.
					//
				if(fProcessor.getSearchText()[0].equals("")==false)
				{
					JOptionPane.showMessageDialog(searchButton,"Searching files.");
					ArrayList<TextFile> resultsArrayList = new ArrayList<TextFile>();
					
					
					//Empty the resultsArrayList before entering new data.
					resultsArrayList.removeAll(resultsArrayList);
					resultsArrayList = fProcessor.compareString(fProcessor.getFileName(), fProcessor.getSearchText(), phraseOption);
					
					
					System.out.println("Before if");
					//Only display files with matches.
					if(resultsArrayList.size() > 0)
					{	
						resultsArea.setText(null);
						System.out.println("Before while");
						for(int resultsArrayIter = 0;resultsArrayIter < resultsArrayList.size();resultsArrayIter++)
						{
							resultsArea.append("File Name: "+resultsArrayList.get(resultsArrayIter).getTextFileName());

							resultsArea.append("\nNumber of matches: "+resultsArrayList.get(resultsArrayIter).getSearchMatches());
							
							resultsArea.append("\nSearch term(s) as a percentage of file words: "+resultsArrayList.get(resultsArrayIter).getMatchPercentage()+"%");
							
							resultsArea.append("\n---------------------------------------------------------------------");
							resultsArea.append("\n");
							resultsArea.append("\n");
							
						}
					}
					
					//If no files were found
					else
					{
						resultsArea.setText(null);
						resultsArea.setText("No matches found in file(s).");
					}//end else
					
				}//end if
				
				else
				{
					JOptionPane.showMessageDialog(searchButton,"No search terms were entered, cancelling search.");
				}//end else
					
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
						
						displaySearchParam(fProcessor);

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
					displaySearchParam(fProcessor);
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
					displaySearchParam(fProcessor);
			}
		});
		
		
		//Listener function to store combined or separate option selection.
		separateRadio.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
					//Set to 0 for separate word searching.
					phraseOption = 0;
					displaySearchParam(fProcessor);
			}
		});
		
		
		//Listener function to store combined or separate option selection.
		combinedRadio.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
					//Set to 1 for combined word searching.
					phraseOption = 1;
					displaySearchParam(fProcessor);
			}
		});
		
		
		//Listener function to store combined or separate option selection.
		separateCaseMatchRadio.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
					//Set to 1 for combined word searching.
					phraseOption = 2;
					displaySearchParam(fProcessor);
			}
		});
		
		
		//Listener function to store combined or separate option selection.
		combinedCaseMatchRadio.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
					//Set to 1 for combined word searching.
					phraseOption = 3;
					displaySearchParam(fProcessor);
			}
		});
		
		
		
		
		//After clicking search button, If a correct filename is chosen && the search term is entered.
		//Search through file, or if no particular file is selected then search all of them.
		
		
	}//end Constructor
	
	//Each time user makes a change to search parameters, new parameters will be displayed by this function.
	//
	private void displaySearchParam(FileProcessor fProcessor)
	{
		//Clear all text from searchOption text area.
		searchOptions.setText("");
		
		searchOptions.append("Search Term(s): "+Arrays.toString(fProcessor.getSearchText()));
		
		//If no file could be found/default setting.
		if(fProcessor.getFileName().equals("empty"))
		{
			searchOptions.append("\n");
			searchOptions.append("\nFile(s) Selected: All files");
			
		}
		
		//If 1 file is selected to be searched.
		else
		{
			searchOptions.append("\n");
			searchOptions.append("\nFile(s) Selected: "+fProcessor.getFileName());
			
		}
		
		//Display how search terms will searched for.
		//
		if(phraseOption == 0)
		{
			searchOptions.append("\n");
			searchOptions.append("\nSearch Type: Separate");
		}
		
		else if(phraseOption == 1)
		{
			searchOptions.append("\n");
			searchOptions.append("\nSearch Type: Combined");
		}
		
		else if(phraseOption == 2)
		{
			searchOptions.append("\n");
			searchOptions.append("\nSearch Type: Separate (Case Matching)");
		}
		
		else if(phraseOption == 3)
		{
			searchOptions.append("\n");
			searchOptions.append("\nSearch Type: Combined (Case Matching)");
		}
		
	}//end displaySearchParam
	
	public static void main(String[] args)
	{
		
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
