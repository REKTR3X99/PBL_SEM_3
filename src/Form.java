import java.util.*;

class Form
{
	 static Stack S;  //Creating a static object for Stack class
	
	class Stack
	{
		Integer[] StackKeeper = new Integer[32]; //Array of Integers 
		Integer Head = 0; //Head deceleration
		
		//Token = Object to push into the StackKeeper
		public void Push(Integer Token)
		{
			S.StackKeeper[S.Head] = Token; //Assigning value of Token to StackKeeper at Index head
			S.Head++; //Incrementing Head
		}
		
		//Pop function
		public void Pop()
		{
			
		}
		
		public void display()
		{
			for(int i =0; i<S.Head; i++)
			{
				System.out.println(S.StackKeeper[i]);
			}
		}
	}
	
	public static void main(String args[])
	{
		Integer Number; //Save user input
		Integer Temp = 0;  //Temp var
		
		//Create an object to take input
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter a number");
		Number = sc.nextInt();
		
		//Converting to binary
		while(Number != 0)
		{
			Temp = Number % 2;
			S.Push(Temp); //Pushing the binary in stack 
			Number = Number / 2;
		}
		
		
	}
	
}