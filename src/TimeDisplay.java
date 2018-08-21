import java.awt.Image;
import java.util.*;

import javax.swing.ImageIcon;


public class TimeDisplay
{
	void getTimeWithOffset()
	{
		
		Calendar CalToGetTime = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
		/*List<String> TimeZoneIDList = new ArrayList<String>();
		String[] TimeZoneID = TimeZone.getAvailableIDs();
		Initializer Init = new Initializer();
		/*int hour24 = CalToGetTime.get(Calendar.HOUR_OF_DAY);
		int Minute24 = CalToGetTime.get(Calendar.MINUTE);
		//System.out.println(Initializer.CountriesComboBox.getSelectedItem());
		*/
		int hour24 = CalToGetTime.get(Calendar.HOUR_OF_DAY);
		int minute = CalToGetTime.get(Calendar.MINUTE);
		int[] ImgHour = new int[2]; 
		int[] ImgMinute = new int[2];
		
		String checker = Initializer.GMTOffset.get(Initializer.CountriesComboBox.getSelectedIndex());
		String HourString = checker.substring(1,3);
		String MinuteString = checker.substring(4,6);
		
			 if(checker.startsWith("+"))
			 {
				 try
				 {
					 hour24 = hour24 + Integer.parseInt(HourString);
					 minute = minute + Integer.parseInt(MinuteString);
				 }catch(Exception e)
				 {
					 System.out.println(e);
				 }
			 }else if(checker.startsWith("-"))
			 {
				 try
				 {
					 hour24 = hour24 - Integer.parseInt(HourString);
					 minute = minute + Integer.parseInt(MinuteString);
				 }catch(Exception e)
				 {
					 System.out.println(e);
				 }
			 }
			 System.out.println(hour24 + ":" + minute);
			
			 System.out.println(hour24 % 10);
			 
			 ImgHour[1] = hour24 % 10;
			 ImgMinute[1] = minute % 10;
			 hour24 = hour24 / 10;
			 minute = minute / 10;
			 ImgHour[0] = hour24;
			 ImgMinute[0] = minute;
			 
			 
			 ImageIcon Icon = new ImageIcon(Initializer.BufferedImageForTime[ImgHour[0]]);
			 Image IconInstance = Icon.getImage();
			 Image NewIconInstance = IconInstance.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
			 Icon = new ImageIcon(NewIconInstance);
			 
			 Initializer.HourTens.setIcon(Icon);
			 
		
	}
}