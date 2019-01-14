
public class CheckingAccount extends BankAccount
{
	private final double OVER_DRAFT_FEE;
	private final double TRANSACTION_FEE;
	private final double FREE_TRANS;
	private int numTransactions = 0;
	
	public CheckingAccount(String n, double b, double odf, double tf, int freeTrans) 
	{
		super(n, b);
		OVER_DRAFT_FEE = odf;
		TRANSACTION_FEE = tf;
		FREE_TRANS= freeTrans;
	}
	
	public CheckingAccount(String n, double odf, double tf, int freeTrans)
	{
		super(n);
		OVER_DRAFT_FEE = odf;
		TRANSACTION_FEE = tf;
		FREE_TRANS= freeTrans;
	}
	
	public void deposit(double amt) 
	{
		
		if (amt < 0) 
		{
			throw new IllegalArgumentException("Amount cannot be negative!");
		}
		else 
		{
			numTransactions++;
			super.deposit(amt);
		
			
			if(numTransactions > FREE_TRANS) 
			{
				withdraw(TRANSACTION_FEE);
			}

		}
	}
	
	public void withdraw(double amt) 
	{
		
	if (amt <= 0 || getBalance() < 0) 
		{
			throw new IllegalArgumentException("Amount cannot be negative!" + "Balance cannot be negative when trying to withdraw");
		}
		else 
		{
			numTransactions++;
			super.withdraw(amt);
			
			
			
			if(numTransactions > FREE_TRANS) 
			{
				super.withdraw(TRANSACTION_FEE);
				
			}
			if( getBalance() < 0) 
			{
				super.withdraw(OVER_DRAFT_FEE);
			}
		}
	}
	
	public void transfer(BankAccount other, double amt) 
	{
		if (amt < 0 ) 
		{
			throw new IllegalArgumentException("Amount cannot be negative!" + "Balance cannot be negative when trying to withdraw");
		}
		else 
		{
			if(getBalance() > 0 && getBalance() > amt) 
			{
			
				if (getName().equals(other.getName())) 
				{
					numTransactions++;
					super.transfer(other, amt);
				
					if ( other.getBalance() < 0 ) 
					{
						withdraw(OVER_DRAFT_FEE);
					}
				
					if ( numTransactions > FREE_TRANS) 
					{
						withdraw(TRANSACTION_FEE);
					}
				}
				else 
				{
					throw new IllegalArgumentException("Names don't match!!");
				}
			}
			else 
			{
				throw new IllegalArgumentException("Balance is neg or less than amt!");
			}
		}
	}

	public void endOfMonthUpdate() 
	{
		numTransactions = 0;
		
	}
}
