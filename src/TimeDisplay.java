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
					 
					 if(hour24 > 24)
					 {
						 hour24 = hour24 - 24;
					 }
					 
					 if(minute > 60)
					 {
						 minute = minute - 60;
					 }
				 }catch(Exception e)
				 {
					 System.out.println(e);
				 }
			 }else if(checker.startsWith("-"))
			 {
				 try
				 {
					 hour24 = hour24 - Integer.parseInt(HourString);
					 minute = minute - Integer.parseInt(MinuteString);
					 
					 if(hour24 > 24)
					 {
						 hour24 = hour24 - 24;
					 }
					 
					 if(minute > 60)
					 {
						 minute = minute - 60;
					 }
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
			 
			 
			 //Variables for scaling the image
			 ImageIcon Icon;
			 Image IconInstance;
			 Image NewIconInstance;
			 
			 
			 //Performing Image scaling on individual Labels 
			 /*
			  * I know there are better methods but for the time being I'll stick to this so that I won't break the entire code
			  * */
			 
			 Icon = new ImageIcon(Initializer.BufferedImageForTime[ImgHour[0]]); //Converting the File read into BufferedImage into an ImageIcon
			 IconInstance = Icon.getImage(); //Getting the Image from the ImageIcon to an Image
			 NewIconInstance = IconInstance.getScaledInstance(100, 100, Image.SCALE_SMOOTH); //Scaling the Image to 100 x 100 resolution 
			 Icon = new ImageIcon(NewIconInstance); //Converting the image from Image to IconImage
			 
			 Initializer.HourTens.setIcon(Icon); //Setting the Icon on the label to the given ImageIcon
			 
			 Icon = new ImageIcon(Initializer.BufferedImageForTime[ImgHour[1]]);
			 IconInstance = Icon.getImage();
			 NewIconInstance = IconInstance.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
			 Icon = new ImageIcon(NewIconInstance);
			 
			 Initializer.HourUnits.setIcon(Icon);
			 
			 Icon = new ImageIcon(Initializer.BufferedImageForTime[ImgMinute[0]]);
			 IconInstance = Icon.getImage();
			 NewIconInstance = IconInstance.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
			 Icon = new ImageIcon(NewIconInstance);
			 
			 Initializer.MinuteTens.setIcon(Icon);
			 
			 Icon = new ImageIcon(Initializer.BufferedImageForTime[ImgMinute[1]]);
			 IconInstance = Icon.getImage();
			 NewIconInstance = IconInstance.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
			 Icon = new ImageIcon(NewIconInstance);
			 
			 Initializer.MinuteUnits.setIcon(Icon);
		
	}
}