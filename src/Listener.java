import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Listener
{
	
	Initializer InitObj = new Initializer();
	
	ActionListener ComboBoxAction = new ActionListener()
	{
		
		public void actionPerformed(ActionEvent e)
		{
			System.out.println("Hello");
			
		}
	};

	ActionListener PressButtonAction = new ActionListener()
			{
				
				public void actionPerformed(ActionEvent e)
				{
					Initializer.CapitalDisplay.setText("Hello");
					
				}
			};
}