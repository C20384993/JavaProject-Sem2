package com.oop.eng;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
	JButton searchButton,fileButton;
	
	//Panel labels.
	JPanel optionPanel; //Across top.
	JPanel relatedFilesPanel; //Bottom left.
	JPanel matchesPanel; //Bottom right.
	
	
	//Text field labels.
	JTextField textfileField;
	JTextField searchField;
	
	//Create text area that will display various information, e.g. files searched, search terms, list of files with 
	//highest match at top, etc.
	JTextArea resultsArea;
	
	//Constructor
	public SearchEngineGUI()
	{
		
		//SearchEngineGUIclass inherits from JFrame class.
		//super("My Search Engine");
		
		
		//setSize(400,400);
		//setLayout(new FlowLayout());
		
		//Create object of FileProcessor class. The constructor is passed 2 strings: filename, search term(s).
		FileProcessor fProcessor = new FileProcessor("Empty","Empty");
		
		
		//Create the components of the GUI.
		//
		GUIFrame = new JFrame("My Search Engine");
		//Create panels.
		optionPanel=new JPanel(); 
		matchesPanel=new JPanel();
		//Create file select button.
		fileButton= new JButton("Select file.");
		//Create Search button.
		searchButton= new JButton("Search.");
		//Create text field for entering text file.
		textfileField = new JTextField("Enter filename",20);
		//Create text field for entering search term.
		searchField = new JTextField("Enter search term",20);
		//Create text area that will display various information,
		resultsArea = new JTextArea(10,20);
		
		
		//Set the initial options of the Frame.
		GUIFrame.setVisible(true);
		GUIFrame.setSize(400,400);
		GUIFrame.add(optionPanel);
		GUIFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		
		//Add components to the main panel.
		optionPanel.setLayout(new FlowLayout());
		optionPanel.add(fileButton);
		optionPanel.add(searchButton);
		optionPanel.add(textfileField);
		optionPanel.add(searchField);
		optionPanel.add(resultsArea);
		
		
		//Stop user from entering text into the results area.
		resultsArea.setEditable(false); 
		
		
		//Add titles to the buttons.
		//https://www.tutorialspoint.com/how-to-create-titled-border-for-a-component-in-java
		searchButton.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), 
		"Search",TitledBorder.LEFT, TitledBorder.TOP));
		fileButton.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
		"File Select",TitledBorder.LEFT, TitledBorder.TOP));
		
		
		//Hover over texts
		fileButton.setToolTipText("Enter the name of the file you wish to search and click to select it. Leave it blank to "
				+ "search every file in the directory.");
		fileButton.addActionListener(this);
		
		searchButton.setToolTipText("Click this once you have entered the file name and search terms to begin the search.");
		searchButton.addActionListener(this);
		
		
		/*
		panel1.add(button_1);
		//button_1.setLayout(new BoxLayout (this, BoxLayout.Y_AXIS));
		button_1.setMaximumSize(button_1.getPreferredSize());
		button_1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "File Select",TitledBorder.LEFT, TitledBorder.TOP));
		
		
		//.createEtchedBorder
		//.setPreferredSize()
		*/
		
		//Action listeners for search button and other buttons.
		//
		searchButton.addActionListener(new ActionListener ()
		{
		
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				//if(e.getSource()==searchButton)
				//Checks filename entered and and search terms, then compares them to produce results.
				{
					JOptionPane.showMessageDialog(searchButton,"Search Clicked.");
					fProcessor.compareString(fProcessor.getFileName(), fProcessor.getSearchText());
				}
			}
		});
		
		//Listener function to store input for filename.
		textfileField.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
					String userInput = textfileField.getText();
					fProcessor.setFileName(userInput);
					System.out.print(fProcessor.getFileName());
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
