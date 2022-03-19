package com.oop.eng;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SearchEngineGUI extends JFrame implements ActionListener{

	JLabel label1;
	
	//Button labels.
	JButton searchButton,button_1;
	
	//Panel labels.
	JPanel panel1;
	
	//Text field labels.
	JTextField textfile_field;
	JTextField search_field;
	
	//Constructor
	public SearchEngineGUI()
	{
		
		//SearchEngineGUIclass inherits from JFrame class.
		super("My Search Engine");
		
		
		setVisible(true);
		setSize(400,500);
		setLayout(new FlowLayout());
		
		//Current thinking: Make object of file processor, but cant pass in file name and search term yet.
		//have those functions in FILEProcessor class 
		FileProcessor fProcessor = new FileProcessor("Empty","Empty");
		
		//Create new panel.
		panel1=new JPanel();
		add(panel1);
		panel1.setBackground(Color.red);
		panel1.setLayout(new FlowLayout());
		
		
		//Create first button.
		button_1= new JButton("Select file POSSIBLY REDUNDANT.");
		panel1.add(button_1);
		//Add hover over text.
		button_1.setToolTipText("This is the first button, and show a message when clicked.");
		button_1.addActionListener(this);
		
		
		//Create Search button.
		searchButton= new JButton("Search.");
		panel1.add(searchButton);
		//Add hover over text.
		searchButton.setToolTipText("Search for your entered terms in the files."); 
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
		
		
		//After clicking search button, If a correct filename is chosen && the search term is entered.
		//Search through file, or if no particular file is selected then search all of them.
		
		
		//Create text field for entering filename.
		JTextField textfile_field = new JTextField("Enter filename",20);
		panel1.add(textfile_field);
		
		//Listener function to store input for filename.
		textfile_field.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				String user_input = textfile_field.getText();
				fProcessor.setFileName(user_input);
				System.out.print(fProcessor.getFileName());
			}
		});
		
		
		
		//Create text field for entering search term.
		JTextField search_field = new JTextField("Enter search term",20);
		panel1.add(search_field, BorderLayout.CENTER);
		
		//Listener function to store input of search term(s).
		search_field.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				String user_input = search_field.getText();
				fProcessor.setSearchText(user_input);
				System.out.print(fProcessor.getSearchText());
			}
		});
		
	}//end Constructor
	
	
	
	public static void main(String[] args)
	{
		
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
