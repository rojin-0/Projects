//RojinSinghMahat(2417733)
package Assessment;

public class BasicBankingSystem {
	public static void main(String[] args) {
		Account Account1 = new Account("Jeffrey", "Ting", 1, 2000);
		Account Account2 = new Account("Hiran", "Patel", 2, 1000);
		
		 // Step 3: Print out the balance of account1
        System.out.println("Balance of account1: " + Account1.getBalance());
        
        // Step 4: Print out the balance of account2
        System.out.println("Balance of account2: " + Account2.getBalance());
        
        // Step 5: Deposit 250 to account1
        Account1.deposit(250);
        
        // Step 6: Print out the balance of account1
        System.out.println("Balance of account1 after deposit: " + Account1.getBalance());
        
        // Step 7: Withdraw 500 from account2
        Account2.withdraw(500);
        
        // Step 8: Print out the balance of account2
        System.out.println("Balance of account2 after withdrawal: " + Account2.getBalance());
	}
}
class Customer{
	private String firstName;
	private String lastName;
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setFirstName(String FName) {
		this.firstName = FName;
	}
	public void setLastName(String LName) {
		this.lastName = LName;
	}
}

class Account extends Customer{
	private int balance;
	private int accountNumber;
	
	Account(String FName, String LName, int accNum, int accBal){
		setFirstName(FName);
		setLastName(LName);
		this.accountNumber = accNum;
		this.balance = accBal;
	}
	public int getBalance() {
		return balance;
	}
	public int getAccountNumber() {
		return accountNumber;
	}
	public void deposit(int amount) {
		this.balance +=amount;
	}
	public void withdraw(int amount) {
		if(this.balance>=amount) {
		this.balance -= amount;}
		else {
			System.out.println("Not enough money in the account for withdrawal.");
		}
	}
}

class Transaction{
	public void transfer(Account acc1, Account acc2, int amount) {
		acc1.withdraw(amount);
		acc2.deposit(amount);
	}
}