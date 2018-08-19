import javax.swing.*;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.awt.image.*;
public class Initializer
{
	
	
	JFrame Frame = new JFrame(); //BaseFrame Initialization
	JPanel SelectionPanel = new JPanel(); //Selection Panel
	static JComboBox<String> CountriesComboBox = new JComboBox<String>(); //Combo box
	static JLabel CapitalDisplay = new JLabel();
	static ArrayList<String> CountryList = new ArrayList<String>();
	static ArrayList<String> CapitalList = new ArrayList<String>();
	
	public static void main(String args[])
	{
		Initializer Init = new Initializer();
		Init.InitFrame();
	}
	
	//Generates Window frame and adds the buttons and panels
	void InitFrame() 
	{
		
		BufferedReader CountryListReader = null; //BufferedReader Object
		BufferedReader CapitalListReader = null;
		String CountryLine = "";
		String CapitalLine = "";
		Listener ActionListener = new Listener();
		
		
		int Index = 0;
		//Checking if file exists
		try
		{	
			CountryListReader = new BufferedReader(new FileReader("Resources/CountryNames.dat")); 	
			CapitalListReader = new BufferedReader(new FileReader("Resources/CapitalNames.dat"));
		}catch(FileNotFoundException e)
		{
			final  JPanel panel = new JPanel(); //JPanel for error
			JOptionPane.showMessageDialog(panel,"could not open file", "Error", JOptionPane.ERROR_MESSAGE);  //Displaying error
		}
		
		while(CountryLine != null && CapitalLine != null)
		{
			try {
				CountryLine = CountryListReader.readLine();
				CapitalLine = CapitalListReader.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			CountryList.add(Index, CountryLine); //adding the names of the country to the list
			CapitalList.add(Index, CapitalLine); //adding the names of capitals to another list
			Index++;
		}

		
		CountriesComboBox.setModel(new DefaultComboBoxModel(CountryList.toArray()));
		CountriesComboBox.setBounds(10, 10, 500, 40);

		
		SelectionPanel.setLayout(null);
		Frame.add(SelectionPanel); //adding panel to the frame
		SelectionPanel.add(CountriesComboBox);//adding combo box to the panel
		
		
		//Boundaries and adding Label to SelectionPanel
		CapitalDisplay.setBounds(520, 10, 600, 50);
		CapitalDisplay.setBackground(Color.RED);
		
		CapitalDisplay.setFont(new Font("Source Code Pro", Font.BOLD, 20));
		CapitalDisplay.setBackground(Color.green);
		SelectionPanel.add(CapitalDisplay);
		
		//Binding action listeners
		CountriesComboBox.addActionListener(ActionListener.ComboBoxAction);
		
		
		Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Frame.setSize(600,480);
		Frame.setVisible(true); 	
	}
	
	void TimeDisplayInit()
	{
		BufferedImage[] TimeStamps;
	}
	
}


	