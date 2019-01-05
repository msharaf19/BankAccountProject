
public class SavingsAccount extends BankAccount
{
	public static final double INT_RATE = .25;
	
	// has a balance variable but does not have access to the variable 
	
	public void addInterest() 
	{
		//implicit parameter: what object is running the addInterest method will run the getBalance method 
		
		deposit(getBalance() * INT_RATE);
	}

}

