import javax.imageio.ImageIO;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
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
	static ArrayList<BufferedImage> ImageList = new ArrayList<BufferedImage>();
	
	public static void main(String args[]) throws FileNotFoundException
	{
		FrameInit FInit = new FrameInit();
		TimeInit TInit = new TimeInit();
		
		//Creating Threads
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

		BufferedReader CountryListReader = null; //BufferedReader Object
		BufferedReader CapitalListReader = null;
		String CountryLine = "";
		String CapitalLine = "";
		Listener ActionListener = new Listener();
		Initializer Init = new Initializer();
		
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
			
			Initializer.CountryList.add(Index, CountryLine); //adding the names of the country to the list
			Initializer.CapitalList.add(Index, CapitalLine); //adding the names of capitals to another list
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
		
		
		Init.Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Init.Frame.setSize(600,480);
		Init.Frame.setVisible(true); 	
	}
	
}

class TimeInit implements Runnable
{
	@Override
	public void run() {
		File ImageFileLoc = new File("Resources/ImageFiles"); //Load the Image collection for time stamps
		File[] IndividualPath = ImageFileLoc.listFiles(); //Getting individual path names
		
		for(int i =0; i<10; i++)
		{
			System.out.println(IndividualPath[i]);
		}
	}
}


	