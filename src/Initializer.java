//import java.util.*;
import javax.swing.*;
import java.awt.*;

public class Initializer
{
	public static void main(String args[])
	{
		Initializer Init = new Initializer();
		Init.InitFrame();
	}
	
	//Generates Window frame and adds the buttons and panels
	void InitFrame()
	{
		Dimension ButtonDimmension = new Dimension(100,100);
		DefaultListModel<String> CountryListModel = new DefaultListModel<String>();
		
		
		/*
		 * Add a itterative statement to pass valeus of country to DefaultListModel
		 * */
		
		
		
		JFrame Frame = new JFrame(); //BaseFrame Initialization
		JPanel Panel = new JPanel();
		JButton  PressButton = new JButton();
		JList<String> CountryList = new JList<String>(CountryListModel);
		JComboBox<String> CountriesComboBox = new JComboBox<String>();
		DefaultComboBoxModel<String> CountryListDefModel = new DefaultComboBoxModel<String>();
		
		CountryList.setModel(CountryListDefModel);
		CountriesComboBox.setModel(CountryListDefModel);
		CountriesComboBox.add(CountryList);
		
		
		Frame.add(Panel);
		Panel.setLayout(new GridLayout(0,2,10,10));
		Panel.add(CountriesComboBox);
		Panel.add(PressButton);
		
		Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Frame.setSize(600,480);
		Frame.setVisible(true);
		
		
		/*JFrame Frame = new JFrame(); //Base Frame Initialization
		Dimension ButtonDim = new Dimension(100,100); //Dimension variable to set an uniform size to the buttons
		DefaultListModel<String> CountryListModel = new DefaultListModel<String>(); //DefaultListModel to store the list of countries
		JButton  Press = new JButton("Press"); //Initializing button with the text Press
		JList <String>CountryList = new JList<String>(CountryListModel); //Initializing JList to add into the frame with the list "CountryListModel"
		JPanel Panel = new JPanel();
		
		
		CountryListModel.add(0, "India"); //Adding countries to the List
		CountryListModel.add(1,"United Kingdom");
		//Setting the base Frame
		Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Frame.setSize(640,480); //Window Size of 300x300 pixels
	
		//Setting panel Layout
		Panel.setLayout(new GridLayout(0,2,10,10));
		
		//Adding panel to the frame
		Frame.add(Panel); 
		
		
		Panel.add(Press); //Adding the press button to the panel
		Panel.add(CountryList); //Adding the country selection into the Drop Down menu
		
		Frame.setVisible(true);*/
		
	}
	
	//To get the display resolution and dynamically adjust the default value of the window
	void GetFrameBounds()
	{
		Rectangle Bounds;
	}
}