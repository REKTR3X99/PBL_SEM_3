//import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.io.*;
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
		ArrayList<String> CountryList = new ArrayList<String>();
		BufferedReader Reader = null; //BufferedReader Object
		String line = "";
		//Checking if file exists
		try
		{	
			Reader = new BufferedReader(new FileReader("Resources/CountryNames.dat")); 	
		}catch(FileNotFoundException e)
		{
			final  JPanel panel = new JPanel(); //JPanel for error
			JOptionPane.showMessageDialog(panel,"could not open file", "Error", JOptionPane.ERROR_MESSAGE);  //Displaying error
		}
		
		while(line != null)
		{
			try {
				line = Reader.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			CountryList.add(line);
		}
		
		
		
		JFrame Frame = new JFrame(); //BaseFrame Initialization
		JPanel Panel = new JPanel(); //Base Panel 
		JButton  PressButton = new JButton(); //Button 
		JComboBox<String> CountriesComboBox = new JComboBox<String>(); //Combo box
		CountriesComboBox.setModel(new DefaultComboBoxModel(CountryList.toArray()));
		
		
		Frame.add(Panel); //adding panel to the frame
		Panel.setLayout(new GridLayout(0,2,10,10)); //setting the paneel layout
		Panel.add(CountriesComboBox);//adding combo box to the panel
		Panel.add(PressButton); //Adding button to the panel
		
		Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Frame.setSize(600,480);
		Frame.setVisible(true); 	
	}
	
	//To get the display resolution and dynamically adjust the default value of the window
	void GetFrameBounds()
	{
		Rectangle Bounds;
	}
}