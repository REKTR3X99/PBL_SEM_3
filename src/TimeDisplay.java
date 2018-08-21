import java.awt.Image;
import java.util.*;

import javax.swing.ImageIcon;


public class TimeDisplay
{
	void getTimeWithOffset()
	{
		
		Calendar CalToGetTime = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
	
		
		int hour24 = CalToGetTime.get(Calendar.HOUR_OF_DAY);
		int minute = CalToGetTime.get(Calendar.MINUTE);
		int[] ImgHour = new int[2]; 
		int[] ImgMinute = new int[2];
		
		String checker = Initializer.GMTOffset.get(Initializer.CountriesComboBox.getSelectedIndex());
		String HourString;
		String MinuteString;
		boolean DoesExist = true;
		
		//Checking if the checker contains "UTC" or not
		/*
		 * If It does have UTC it means that the time cannot be determined 
		 * */
		if(!checker.contains("UTC"))
		{
			HourString = checker.substring(1,3);
			MinuteString = checker.substring(4,6);
			DoesExist = true;
		}else
		{
			DoesExist = false;
			HourString = "0";
			MinuteString ="0";
		}
		
			 if(checker.startsWith("+") && DoesExist) //If the country is ahead GMT
			 {
				 try
				 {
					 hour24 = hour24 + Integer.parseInt(HourString) + 1;
					 minute = minute + Integer.parseInt(MinuteString);
					 
					 if(hour24 >= 24) //Checking the addition exceeds or equals 24 hours
					 {
						 hour24 = hour24 - 24; //Negating by 24 incase it exceeds
					 }
					 
					 if(minute >= 60) //Checking if minutes exceeds or equals 60 minute
					 {
						 minute = minute - 60; //If it does, negate by 60
					 }
				 }catch(Exception e)
				 {
					 System.out.println(e);
				 }
			 }else if(checker.startsWith("-") && DoesExist) //If the country is behind GMT
			 {
				 try
				 {
					 hour24 = hour24 - Integer.parseInt(HourString);
					 minute = minute - Integer.parseInt(MinuteString);
					 
					 if(hour24 < 0)
					 {
						 hour24 = hour24 + 24;
					 }
					 
					 if(minute < 0)
					 {
						 minute = minute + 60;
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