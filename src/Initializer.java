import javax.imageio.ImageIO;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.awt.Color;
import java.awt.Font;
import java.io.*;
import java.awt.image.*;
public class Initializer
{	
	JFrame Frame = new JFrame(); //BaseFrame Initialization
	JPanel SelectionPanel = new JPanel(); //Selection Panel
	static JComboBox<String> CountriesComboBox = new JComboBox<String>(); //Combo box
	static JLabel CapitalDisplay = new JLabel(); //For displaying the capital of the selected country
	
	
	static ArrayList<String> CountryList = new ArrayList<String>();  //To hold  all the country list
	static ArrayList<String> CapitalList = new ArrayList<String>(); //To hold all thee corresponding capitals
	static ArrayList<String> GMTOffset = new ArrayList<String>(); //To hold all the corresponding GMT offset of the countries
	
	
	static BufferedImage[] BufferedImageForTime = new  BufferedImage[10]; //To hold the 10 buffered image from 0 - 9 
	
	
	//Components used to display the Image 
	static JLabel HourTens = new JLabel();
	static JLabel HourUnits = new JLabel();
	static JLabel MinuteTens = new JLabel();
	static JLabel MinuteUnits = new JLabel();
	
	
	public static void main(String args[]) throws FileNotFoundException
	{
		FrameInit FInit = new FrameInit(); //Generating an object for the Frame Initialization class
		TimeInit TInit = new TimeInit(); //Object for the Time Initialization class
		
		/*
		 * Creating Threads because the Frame would become unresponsive if the Time Initialization is done after
		 * Since main thread would be occupied with Time Display rather than Frame display
		 * */
		Thread InitFrameThread = new Thread(FInit);
		Thread TimeDispThread = new Thread(TInit);
		
		
		InitFrameThread.start(); //Starting the frame thread
		TimeDispThread.start(); //starting the Time Display thread
		
	}
}

class FrameInit implements Runnable
{
	@Override
	public void run() {

		BufferedReader CountryListReader = null; //BufferedReader Object for reading countries and generating a list
		BufferedReader CapitalListReader = null; //BFDRD Obj for reading Capitals 
		BufferedReader GMTOffsetReader = null; //To read GMT Offsets
		
		//Strings used to  add read line into the list
		String CountryLine = ""; 
		String CapitalLine = "";
		String GMTOffsetLine = "";
		
		//Listener which listens to actions performed s
		Listener ActionListener = new Listener();
		
		//Initializer Object
		Initializer Init = new Initializer();
		
		int Index = 0;
		//Checking if file exists
		try
		{	
			CountryListReader = new BufferedReader(new FileReader("Resources/CountryNames.dat")); 	//Reading Country List
			CapitalListReader = new BufferedReader(new FileReader("Resources/CapitalNames.dat")); //Reading Capital List
			GMTOffsetReader = new BufferedReader(new FileReader("Resources/TimeZoneOffset.dat")); //Reading GMT Offset for each country
			
		}catch(FileNotFoundException e) //Catch exception if the file is not found
		{
			final  JPanel panel = new JPanel(); //JPanel for error
			JOptionPane.showMessageDialog(panel,"could not open file", "Error", JOptionPane.ERROR_MESSAGE);  //Displaying error
		}
		
		while(CountryLine != null && CapitalLine != null && GMTOffsetLine != null)
		{
			try {
				CountryLine = CountryListReader.readLine();
				CapitalLine = CapitalListReader.readLine();
				GMTOffsetLine = GMTOffsetReader.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			Initializer.CountryList.add(Index, CountryLine); //adding the names of the country to the list
			Initializer.CapitalList.add(Index, CapitalLine); //adding the names of capitals to another list
			Initializer.GMTOffset.add(Index, GMTOffsetLine); //adding GMT Offset to its corresponding list
			
			Index++;
		}

		
		Initializer.CountriesComboBox.setModel(new DefaultComboBoxModel(Initializer.CountryList.toArray()));
		Initializer.CountriesComboBox.setBounds(10, 10, 500, 40);

		
		Init.SelectionPanel.setLayout(null);
		Init.Frame.add(Init.SelectionPanel); //adding panel to the frame
		Init.SelectionPanel.add(Initializer.CountriesComboBox);//adding combo box to the panel
		
		
		//Boundaries and adding Label to SelectionPanel
		Initializer.CapitalDisplay.setBounds(520, 10, 600, 50);
		Initializer.CapitalDisplay.setBackground(Color.RED);
		
		Initializer.CapitalDisplay.setFont(new Font("Source Code Pro", Font.BOLD, 20));
		Initializer.CapitalDisplay.setBackground(Color.green);
		Init.SelectionPanel.add(Initializer.CapitalDisplay);
		
		//Binding action listeners
		Initializer.CountriesComboBox.addActionListener(ActionListener.ComboBoxAction);
		
		//IMG FRAME 
		
		Initializer.HourTens.setBounds(10, 240, 100, 100);
		Initializer.HourUnits.setBounds(120, 240, 100, 100);
		Initializer.MinuteTens.setBounds(230, 240, 100, 100);
		Initializer.MinuteUnits.setBounds(340, 240, 100, 100);
		
		Init.SelectionPanel.add(Initializer.HourTens);
		Init.SelectionPanel.add(Initializer.HourUnits);
		Init.SelectionPanel.add(Initializer.MinuteTens);
		Init.SelectionPanel.add(Initializer.MinuteUnits);

		Init.Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Init.Frame.setSize(600,480);
		Init.Frame.setVisible(true); 	
	}
	
}

class TimeInit implements Runnable
{
	@Override
	public void run() {
		File ImageFileLoc = new File("Resources/ImageFiles");//Load the Image collection for time stamps
		
		File[] IndividualPath = ImageFileLoc.listFiles(); //Getting individual path names
		Arrays.sort(IndividualPath);
		for(int i =0; i<10; i++)
		{
			System.out.println(IndividualPath[i]);
		}
		for(int i =0; i<10; i++)
		{
			Initializer.BufferedImageForTime[i] = new BufferedImage(720,720,BufferedImage.TYPE_INT_RGB);
		}
		
		try
		{
			for(int i =0;  i<10; i++)
			{
				Initializer.BufferedImageForTime[i] = ImageIO.read(IndividualPath[i]);
			}
			
		}catch(Exception e)
		{
			System.out.println(e);
		}
		
		
		}
	}



	