package com.oop.eng;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SearchEngineGUI extends JFrame implements ActionListener{

JLabel label1;
	
	//Button labels.
	JButton button_1,button_2;
	
	//Panel labels.
	JPanel panel1;
	
	//Constructor
	public SearchEngineGUI(){
		
		//SearchEngineGUIclass inherits from JFrame class.
		super("My Search Engine");
		
		
		setVisible(true);
		setSize(300,400);
		setLayout(new FlowLayout());
		
		//Create new panel.
		panel1=new JPanel();
		add(panel1);
		panel1.setBackground(Color.red);
		panel1.setLayout(new FlowLayout());
		
		
		//Create first button.
		button_1= new JButton("Click me.");
		panel1.add(button_1);
		//Add hover over text.
		button_1.setToolTipText("This is the first button, and show a message when clicked.");
		button_1.addActionListener(this);
		
		//Create second button.
		button_2= new JButton("Click me 2.");
		panel1.add(button_2);
		//Add hover over text.
		button_2.setToolTipText("This is the second button, it will show a different message."); 
		button_2.addActionListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	//Main function, used for creating a GUI object.
	public static void main(String[] args)
	{
		//Create an object of the GUI class.
		SearchEngineGUI gui_object1 = new SearchEngineGUI();
					
	}
	
}
