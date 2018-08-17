import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Listener
{
	
	Initializer Init = new Initializer();

	
	ActionListener ComboBoxAction = new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					System.out.println("Hello");
					Init.CapitalDisplay.setText("Hello");
				}
			};
	
			ActionListener PressButtonAction = new ActionListener()
					{
						public void actionPerformed(ActionEvent e)
						{
							
						}
					};
}