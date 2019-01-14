
public abstract class BankAccount 
{
	private static int nextAccNum = 1;
	private String name;
	private int accNum;
	private double balance;
	
	public BankAccount (String n) 
	{
		name = n;
		balance = 0;
		accNum = nextAccNum;
		nextAccNum++;
	}
	
	public BankAccount (String n, double b) 
	{
		name = n;
		accNum = nextAccNum;
		nextAccNum++;
		balance = b;
		
	}
	/**
	 * deposit method 
	 * parameters - double amt
	 * @return none
	 */
	public void deposit(double amt) 
	{
		balance = amt + balance;
	}
	/**
	 * withdraw method 
	 * parameters - double amt
	 * @return none
	 */
	public void withdraw(double amt) 
	{
		balance = balance - amt;
	}
	
	/**
	 * getAccount method 
	 * no parameters 
	 * @return name 
	 */
	
	public int getAccNum() 
	{
		return accNum;
	}
	
	/**
	 * getName method 
	 * no parameters 
	 * @return name 
	 */

	public String getName() 
	{
		return name;
	}
	
	/**
	 * getBalance method 
	 * parameters - none 
	 * @return balance 
	 */
	public double getBalance() 
	{
		return balance;
	}
	
	/**
	 * withdraw endOfMonthUpdate 
	 * parameters - none
	 * @return none
	 */
	public abstract void endOfMonthUpdate();
	
	/**
	 * withdraw transfer
	 * parameters - BankAccount other, double amt
	 * @return none
	 */
	public void transfer(BankAccount other, double amt) 
	{
		withdraw(amt);
		other.deposit(amt);
	}
	
	/**
	 * withdraw toString 
	 * parameters - none
	 * @return String
	 */
	
	public String toString() 
	{
		return "" + accNum + "\t" + name + "\t" + "$" + balance;
	}
	
	
	

}
