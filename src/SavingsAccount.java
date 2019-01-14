
public class SavingsAccount extends BankAccount
{
	private final double MIN_BAL;
	private final double MIN_BAL_FEE;
	private double intRate;
	
	public SavingsAccount(String n, double b, double r, double mb, double mbf) 
	{
		super(n,b);
		intRate = r;
		MIN_BAL = mb;
		MIN_BAL_FEE = mbf;	
	}
	
	public SavingsAccount(String n, double r, double mb, double mbf) 
	{
		super(n);
		intRate = r;
		MIN_BAL = mb;
		MIN_BAL_FEE = mbf;
	}
	
	/**
	 * withdraw method
	 * parameters - double amt
	 * @return none
	 */
	public void deposit(double amt) 
	{
		if ( amt < 0 ) 
		{
			throw new IllegalArgumentException("amount cannot be negative!!");
		}
		else 
		{
			super.deposit(amt);
		}
			
	}
	/**
	 * withdraw method 
	 * parameters - double amt
	 * @return none
	 */
	public void withdraw(double amt) 
	{
		if (amt < 0) 
		{
			throw new IllegalArgumentException("Amount cannot be negative!" + " Balance cannot be negative!");
		}
		else 
		{
			if (getBalance() < amt || getBalance() < 0 ) 
			{
				throw new IllegalArgumentException("Balance cannot go negative!");
			}
			else 
			{
				super.withdraw(amt);
				
				if( getBalance() < MIN_BAL ) 
				{
					super.withdraw(MIN_BAL_FEE);
				}
			}
		}
	}
	/**
	 * transfer method 
	 * parameters - BankAccount other, double amt
	 * @return none
	 */
	public void transfer(BankAccount other, double amt) 
	{
		if ( amt < 0 ) 
		{
			throw new IllegalArgumentException("Amount cannot be negative");
		}
		else 
		{
			if (getBalance() < amt || getBalance() < 0) 
			{
				throw new IllegalArgumentException("NOOOO");
			}
			else 
			{
			
				if (getName().equals(other.getName())) 
				{
					super.transfer(other, amt);
				}
				else 
				{
					throw new IllegalArgumentException("Names are not equal!!");
				}
			}
		}
	
	}
	
	/**
	 * addInterest method 
	 * parameters - none
	 * @return none
	 */
	public void addInterest() 
	{
		deposit((getBalance()*intRate));
	}
	
	/**
	 * endOfMonthUpdate method 
	 * parameters - none
	 * @return none
	 */
	
	public void endOfMonthUpdate() 
	{
		this.addInterest();
	}
	
}
