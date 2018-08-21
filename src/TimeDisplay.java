import java.awt.Image;
import java.util.*;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class TimeDisplay
{
	void getTimeWithOffset()
	{
		
		//Setting the Calendar to read GMT Time
		Calendar CalToGetTime = new GregorianCalendar(TimeZone.getTimeZone("GMT")); 
	
		
		int GMTHour = CalToGetTime.get(Calendar.HOUR_OF_DAY); //Get the current GMT Hour
		int GMTMinute = CalToGetTime.get(Calendar.MINUTE); //Get the current GMT time
		int[] ImgHour = new int[2];  //Image Corresponding to the given hour
		int[] ImgMinute = new int[2]; //Image corresponding to the given minute
		
		String checker = Initializer.GMTOffset.get(Initializer.CountriesComboBox.getSelectedIndex());
		String HourString; //To Store the hours
		String MinuteString; //To Store the minutes
		boolean DoesExist = true;
		
		//Checking if the checker contains "UTC" or not
		/*
		 * If It does have UTC it means that the time cannot be determined 
		 * */
		if(!checker.contains("UTC"))
		{
			/*
			 * Format of the time in TimeZoneOffset.dat is (sign)X1X2.Y1Y2 
			 * 
			 * where sign gives the position relative to GMT, + means its ahead and - means its behind
			 * X1 and X2 correspond to the hours  
			 * Y1 and Y2 correspond to the minutes
			 * 
			 * substring of 1,3 is X1 and X2 
			 * substring of 4,6 is Y1 and Y2 
			 * */
			HourString = checker.substring(1,3);
			MinuteString = checker.substring(4,6);
			DoesExist = true; //Saying that the given value exists in the Zone Offset data 
		}else
		{
			DoesExist = false;
			HourString = "0";
			MinuteString ="0";
		}
		
		
		/*
		 * Bottom code checks if the start of the checker. 
		 * Checker equals the data which has the index of the selected item from the combobox
		 * and the ZoneOffsetData.
		 * 
		 * It checks if the checker starts with which sign and if the spcified value exists in the given data or if its "UTC" 
		 * for which the time cannot be determined.
		 */
		
		
		/*
		 * if checker starts with "+" that is the read data starts with a "+" then add the number of hours of offset 
		 * which is in the GMTHour to the given hour.
		 * 
		 * After this if the number of Hours is more than 25, then negate the hours by 24 which would bring them in range
		 * Same with the number of minutes
		 */
		
		
		/*
		 * if checker starts with "-" the selected country is behind GMT by X1X2 amount of hours. 
		 * for this negate the number of hours ( Offset) from GMT.
		 * 
		 * If the number is less than 0 then add 24 to it to bring it to the proper range
		 * same with the number of minutes
		 * */
		
		
			 if(checker.startsWith("+") && DoesExist) //If the country is ahead GMT and does exists in the ZoneOffsetData
			 {
				 try
				 {
					// offsetting the given GMTHour from the calendar by the Zone Offset from GMT
					 //For some reason  if I don't add 1 the time remains an hour behind so there's a 1
					 //Also converting HourString from String to Int
					 GMTHour = GMTHour + Integer.parseInt(HourString) + 1; 
					 
					 //Offsetting the minute and adding it to the GMTMinute.
					 //Also parsing the value of MinuteString to convert from String to Int
					 GMTMinute = GMTMinute + Integer.parseInt(MinuteString);
					 
					 if(GMTHour >= 24) //Checking the addition exceeds or equals 24 hours
					 {
						 GMTHour = GMTHour - 24; //Negating by 24 incase it exceeds
					 }
					 
					 if(GMTMinute >= 60) //Checking if minutes exceeds or equals 60 minute
					 {
						 GMTMinute = GMTMinute - 60; //If it does, negate by 60
					 }
				 }catch(Exception e)
				 {
					 final  JPanel panel = new JPanel(); //JPanel for error
						JOptionPane.showMessageDialog(panel,"Parsing Error for countries Ahead of GMT", "Error", JOptionPane.ERROR_MESSAGE);  //Displaying error
				 }
			 }else if(checker.startsWith("-") && DoesExist) //If the country is behind GMT
			 {
				 try
				 {
					 GMTHour = GMTHour - Integer.parseInt(HourString);
					 GMTMinute = GMTMinute - Integer.parseInt(MinuteString);
					 
					 if(GMTHour < 0)
					 {
						 GMTHour = GMTHour + 24;
					 }
					 
					 if(GMTMinute < 0)
					 {
						 GMTMinute = GMTMinute + 60;
					 }
				 }catch(Exception e)
				 {
					 final  JPanel panel = new JPanel(); //JPanel for error
						JOptionPane.showMessageDialog(panel,"Parsing error for countries behind GMT", "Error", JOptionPane.ERROR_MESSAGE);  //Displaying error
				 }
			 }
			 System.out.println(GMTHour + ":" + GMTMinute);
			
			 System.out.println(GMTHour % 10);
			 
			 ImgHour[1] = GMTHour % 10; //Extracting the Second number of Hour 
			 ImgMinute[1] = GMTMinute % 10; //Extracting the Second number of Minute
			 GMTHour = GMTHour / 10; //Dividng Hour by 10  
			 GMTMinute = GMTMinute / 10; //Dividing Minute by 10
			 ImgHour[0] = GMTHour; //Extracting the First number of Hour
			 ImgMinute[0] = GMTMinute; //EXtracting First number of Minute
			 
			 
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