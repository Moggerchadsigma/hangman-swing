package com.example.code;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;

import com.example.images.ImageManager;


public class Main {
	private JFrame frame;
	private int count = 1;
	private int wordOrder;
	private JPanel mainMenu;
	private JPanel mainGame;
	private JPanel cardPanel;
	private CardLayout cardLayout;
	private FlowLayout flowLayout;
	private JButton enterBtn;
	private JButton startBtn;
	private JButton exitBtn;
	private JLabel hangMan;
	private ArrayList<String> words;
	private JTextField textField;
	private Random randomGen;
	private JLabel message;
	private JLabel[] characters;
	private String myGuess;
	private String guessWord;
	private char[] chars;
	private int attempts;
	Main() throws FileNotFoundException, URISyntaxException, IOException
	{
		//misc
		randomGen = new Random();
		words = MyFileReader.words("words.txt");
		//containers
		frame = new JFrame("Hangman v1.0");
		
		cardPanel = new JPanel();
		mainMenu = new JPanel();
		mainGame = new JPanel();
		
		//layouts
		cardLayout = new CardLayout();
		flowLayout = new FlowLayout();
		
		//components
		enterBtn = new JButton("Enter");
		startBtn = new JButton("tap to start the game");
		exitBtn = new JButton("tap to exit the game");
		
		hangMan = new JLabel(new ImageIcon(ImageManager.getImageURL("hangman1.png")));
		
		textField = new JTextField("Enter one char");
		
		//containers and layouts settings
		cardPanel.setLayout(cardLayout);
		cardPanel.add(mainMenu);
		cardPanel.add(mainGame);
		
		frame.setContentPane(cardPanel);
		frame.setSize(800,600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(true);
		
		mainGame.add(hangMan);
		mainGame.add(textField);
		mainGame.add(enterBtn);
		
				
		mainMenu.add(startBtn);
		//components setting
		textField.setMaximumSize(textField.getMinimumSize());
		
		//event handling
		textField.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e)
			{
				textField.setText("");
			}
		});
		enterBtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e)
			{
				myGuess = textField.getText();
				textField.setText("Enter one char");
				if(myGuess.length() > 1)
				{
					System.out.println("Only one character allowed!");
				}
				else if(myGuess.length() == 0)
				{
					System.out.println("Type one character");
				}
				else
				{
					int i = 0;
					int k = 0;
					while(i < guessWord.length())
					{
						if(myGuess.equals(String.valueOf(chars[i])))
						{
							characters[i].setText(String.valueOf(chars[i]));
							k++;
						}
						i++;
					}
					if(k == 0)
					{
						attempts++;
						hangMan.setIcon(new ImageIcon(ImageManager.getImageURL("hangman" + attempts + ".png")));
					}
					if(attempts == 6)
					{
						cardLayout.previous(cardPanel);
						System.out.println("you lost");
						hangMan.setIcon(new ImageIcon(ImageManager.getImageURL("hangman1.png")));
						for(int z = 0;z < guessWord.length();z++)
						{
							mainGame.remove(characters[z]);
						}
					}
					k = 0;
					for(int j = 0;j < guessWord.length();j++)
					{
						if(characters[j].getText().equals("_"))
						{
							k++;
						}
					}
					if(k == 0)
					{
						cardLayout.previous(cardPanel);
						System.out.println("you won!");
						hangMan.setIcon(new ImageIcon(ImageManager.getImageURL("hangman1.png")));
						for(int z = 0;z < guessWord.length();z++)
						{
							mainGame.remove(characters[z]);
						}
					}
				}
			}
		});
		startBtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				textField.setText("Enter one char");
				cardLayout.next(cardPanel);
				attempts = 1;
				wordOrder = randomGen.nextInt(9);
				guessWord = words.get(wordOrder);
				
				characters = new JLabel[guessWord.length()];
				chars = new char[guessWord.length()];
				for(int i = 0;i < guessWord.length();i++)
				{
					characters[i] = new JLabel("_");
					mainGame.add(characters[i]);
					chars[i] = guessWord.charAt(i);
				}
				
			}
		});
		exitBtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e)
			{
				cardLayout.previous(cardPanel);
			}
		});
		//components setting
		
	}
	
	public static void main(String[] args)
	{
		SwingUtilities.invokeLater(new Runnable(){
			@Override
			public void run()
			{
				try
				{
					new Main();
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}
	
}
