import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Listener
{	
	ActionListener ComboBoxAction = new ActionListener()
	{
		public void actionPerformed(ActionEvent e)
		{
			Initializer.CapitalDisplay.setText(Initializer.CapitalList.get(Initializer.CountriesComboBox.getSelectedIndex()));	
		}
	};
}