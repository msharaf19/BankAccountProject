import java.util.ArrayList;
import java.util.Scanner;


public class MainClass 
{
	private static final double OVER_DRAFT_FEE = 15;
	private static final double RATE = .0025;
	private static final double TRANSACTION_FEE = 1.5;
	private static final double MIN_BAL = 300;
	private static final double MIN_BAL_FEE = 10;
	private static final int FREE_TRANSACTIONS = 10;

	public static void main(String[] args) 
	{
		boolean keepGoing = true;
		String nameC = "";
		String nameS = "";
		ArrayList<BankAccount> list = new ArrayList<BankAccount>();
		String initialDepositAnswer;
		String initialDeposit = "";
		boolean notYesOrNo = true;
		boolean invalidCS = true;
		String transaction = "";
		boolean invalidTrans = true;
		double withdrawAmt = 0.0;
		double depositAmt = 0.0;
		double initialDepositDoubleC = 0.0;
		double initialDepositDoubleS = 0.0;
		
		while(keepGoing == true) 
		{
			//Input from the user add an account, make a transaction, or terminate 
			System.out.println("Would you like to... \n\ta:add an account "
				+ "\n\tb:Make a transaction \n\tc:Terminate the program ");
		
			Scanner in = new Scanner(System.in);
			String answer = in.next();
			in.nextLine();
		
			if ( answer.trim().equalsIgnoreCase("a")) 
			{
				//Input from user if they chose to make an account checking or saving account
				System.out.println("Would you like to create a \n\tc:checking or \n\ts:saving account?");
				String typeAccount = in.next();
				in.nextLine();
				invalidCS = true;
				while(invalidCS == true) 
				{
					if (typeAccount.equalsIgnoreCase("c")) 
					{
						//Input for name - checking account
						System.out.println("What is your first name?");
						nameC = in.next();
						in.nextLine();
						notYesOrNo = true;
						
						while(notYesOrNo == true) 
						{
							//initial deposit - checking account
							System.out.println("Would you like to make an initial deposit?");
							initialDepositAnswer = in.next();
							in.nextLine();
					
					
							if (initialDepositAnswer.equalsIgnoreCase("yes")) 
							{
								boolean initialDepositTF = true;
								while(initialDepositTF == true) 
								{
									//input initial deposit
									System.out.println("What is your initial deposit?");
									initialDeposit = in.next();
									in.nextLine();
									notYesOrNo = false;
								
									if(!isNumeric(initialDeposit) || Double.parseDouble(initialDeposit) < 0) 
									{
										System.out.println("Invalid Initial Deposit");
									}
									else 
									{
										initialDepositTF = false;
										initialDepositDoubleC = Double.parseDouble(initialDeposit);
									}
								}
							}
							else if (initialDepositAnswer.equalsIgnoreCase("no")) 
							{
								initialDepositDoubleC = 0;
								notYesOrNo = false;
							}
							else 
							{
								System.out.println("Invalid input... please try again");
								notYesOrNo = true;
							}
						}
						
						// creation of BankAccount + addition to array list
					BankAccount NewBankAccount = new CheckingAccount(nameC, initialDepositDoubleC,OVER_DRAFT_FEE,TRANSACTION_FEE,FREE_TRANSACTIONS);
					list.add(NewBankAccount);
					System.out.println("Congratulations! Here is your information: \n\tName: " + NewBankAccount.getName() + "\n\tAccount Number:"
					+ NewBankAccount.getAccNum() + "\n\tBalance:" + NewBankAccount.getBalance());
					invalidCS = false;
				}
				else if (typeAccount.equalsIgnoreCase("s")) 
				{
					//input from user for name - savings account
					System.out.println("What is your first name?");
					nameS = in.next();
					
					notYesOrNo = true;
					while(notYesOrNo == true) 
					{
						//input initial deposit - savings account
						System.out.println("Would you like to make an initial deposit?");
						initialDepositAnswer = in.next();
						in.nextLine();
					
					
						if (initialDepositAnswer.equalsIgnoreCase("yes")) 
						{
							notYesOrNo = false;
							boolean initialDepositTFS = true;
							while(initialDepositTFS) 
							{
								System.out.println("What is your initial deposit?");
								initialDeposit = in.next();
								in.nextLine();
								notYesOrNo = false;
							
								if(!isNumeric(initialDeposit) || Double.parseDouble(initialDeposit) < 0) 
								{
									System.out.println("Invalid Initial Deposit");
								}
								else 
								{
									initialDepositTFS = false;
									initialDepositDoubleS = Double.parseDouble(initialDeposit);
								}
							}
						}
						else if (initialDepositAnswer.equalsIgnoreCase("no")) 
						{
							initialDepositDoubleS = 0;
							notYesOrNo = false;
						}
						else 
						{
							System.out.println("Invalid input... please try again");
							notYesOrNo = true;
						}
					}
				//Creation of bank account
					BankAccount NewBankAccount = new SavingsAccount(nameS, initialDepositDoubleS,RATE,MIN_BAL,MIN_BAL_FEE );
					list.add(NewBankAccount);
					System.out.println("Congratulations! Here is your information: \n\tName: " + NewBankAccount.getName() + "\n\tAccount Number:"
							+ NewBankAccount.getAccNum() + "\n\tBalance:" + NewBankAccount.getBalance());
					invalidCS = false;
			
				}
				else 
				{
					invalidCS = true;
				}
			}
			}
			//option to make a transaction
			else if (answer.trim().equalsIgnoreCase("b")) 
			{
				if ( list.size() == 0) 
				{
					invalidTrans = false;
					System.out.println("Please add an account before making a transaction");
				}
				else 
				{
					while(invalidTrans == true) 
					{
						System.out.println("What transaction would you like to perform?");
						System.out.println("\tw - withdraw\n\td - deposit\n\tt - transfer\n\ta - get account number\n\tm - main menu");
						transaction = in.next();
					
					
						switch (transaction) 
						{
							//withdraw
							case ("w"):
							{
								boolean withdrawTF = true;
								while (withdrawTF == true) 
								{
									System.out.println("What is your account number?");
								
									String accNumW = in.next();
								
									if(!isNumeric(accNumW)) 
									{
										System.out.println("Please enter a number");
									}
									else  
									{
										int accNumInt = Integer.parseInt(accNumW);
									
										for (BankAccount each: list ) 
										{
											if (accNumInt == each.getAccNum()) 
											{
												System.out.println("How much would you like to withdraw from account #" + each.getAccNum()+ "?");
												withdrawAmt = in.nextDouble();
									
												try 
												{
													each.withdraw(withdrawAmt);
													System.out.println("Your balance is now: " + each.getBalance());
													keepGoing = false;
												}	
												catch(IllegalArgumentException e) 
												{
													System.out.println("Transaction not authorized");
												}
												withdrawTF = false;
											}
										
											else 
											{
												withdrawTF= true;
												System.out.println("Invalid account number... please try again");
											}
								
										}
									}
						
								}
							keepGoing = false;
							break;
							
						}
							//deposit
						case ("d"):
						{
							boolean depositTF = true;
							while(depositTF == true) 
							{
								System.out.println("What is your account number?");
								String accNumD = in.next();
							
								if(!isNumeric(accNumD)) 
								{
									System.out.println("Please enter a number");
								}
								else 
								{
									int accNumDInt = Integer.parseInt(accNumD);
								
									for (BankAccount each: list ) 
									{
										if (accNumDInt == each.getAccNum()) 
										{
											System.out.println("How much would you like to deposit into account #" + each.getAccNum());
											depositAmt = in.nextDouble();
									
											try 
											{
												each.deposit(depositAmt);
												System.out.println("Your balance is now: " + each.getBalance());
											}
											catch(IllegalArgumentException f) 
											{
												System.out.println("Transaction not authorized");
											}
											depositTF = false;
										
										}
										else 
										{		
											System.out.println("Invalid account number... Please try again");
											depositTF = true;
										}
									}
									
								}
							}
							keepGoing = false;
							
							break;
							
						}
						//transfer
						case "t":
						{
							boolean transferTF = true;
							while(transferTF == true) 
							{
								BankAccount TransferFrom = null;
								BankAccount TransferTo = null;
								double transferAmt = 0.0;
								System.out.println("What account would you like to transfer money from?");
								String accNumT = in.next();
							
								if (!isNumeric(accNumT)) 
								{
									System.out.println("Please enter a number");
								}
								else 
								{
									int accNumTInt = Integer.parseInt(accNumT);
									for(BankAccount each: list) 
									{
										if (each.getAccNum() == accNumTInt ) 
										{
											TransferFrom = each;
											transferTF = false;
										}
										else 
										{
											transferTF = true;
										}
									}
								}
							
								System.out.println("What account would you like to tranfer to?");
								String accNumT2 = in.next();
								
								if(!isNumeric(accNumT2)) 
								{
									System.out.println("Please enter a number");
								}
								else 
								{
									int accNumT2Int = Integer.parseInt(accNumT2);
							
								for (BankAccount other: list) 
								{
									if (other.getAccNum() == accNumT2Int) 
									{
										TransferTo = other;
										System.out.println("How much would you like to transfer?");
										transferAmt = in.nextDouble();
										transferTF = false;
									}
									else 
									{
										transferTF = true;
									}
								}
							
								try 
								{
									TransferFrom.transfer(TransferTo, transferAmt);
									System.out.println("Balance of first account after transfer: " + TransferFrom.getBalance());
									System.out.println("Balance of second account after transfer: " + TransferTo.getBalance());
								}
								catch(IllegalArgumentException f) 
								{
									System.out.println("Transaction not authorized");
								}
								}
								}
							
							keepGoing = false;
							break;
						}
						//get account number
						case "a":
						{
							boolean toStringTF = true;
							while(toStringTF == true) 
							{
								System.out.println("What is your account name?");
								String nameT = in.next();
								
								for (BankAccount each: list) 
								{
									if (each.getName().equalsIgnoreCase(nameT)) 
									{
										if (each instanceof CheckingAccount) 
										{
											System.out.println("Checking Account: " + each.toString());
										}
										else if ( each instanceof SavingsAccount) 
										{
											System.out.println("Saving Account: " + each.toString());
										}
										toStringTF = false;
									}
									else 
									{
										System.out.println("There is no account with that name... Please try again");
										toStringTF = true;
									}
								}
							}
							keepGoing = false;
							break;
						}
						//main menu 
						case "m":
						{
							invalidTrans = false;
							keepGoing = true;
							break;
						}
						default:
						{
							System.out.println("Invalid input please try again");
							break;
						}
					}
				}
				}
				
			}
			else if ( answer.trim().equalsIgnoreCase("c")) 
			{
				keepGoing = false;
			}
			else 
			{
				System.out.println("invalid input... please try again");
				keepGoing = true;
			}
		}
	}
	/**
	 * isNumeric method
	 * parameters - String str
	 * @return boolean
	 * */
	private static boolean isNumeric(String str)
	{
		try
		{
			Double.parseDouble(str);
			return true;
		}
		catch(IllegalArgumentException e)
		{
			return false;
		}
	}
}
