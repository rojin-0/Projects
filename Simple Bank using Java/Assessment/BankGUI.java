//RojinSinghMahat(2417733)
package Assessment;
//import statements
import java.awt.EventQueue;
import java.util.LinkedList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BankGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField accDeposit;
	private JTextField accWithdraw;
	private JTextField acc1Transfer;
	private JTextField acc2Transfer;
	private JTextField depositInput;
	private JTextField withdrawInput;
	private JTextField transferAmount;
	private JLabel lblWithdrawAmount;
	private JLabel lblAccountNumber_1;
	private JLabel lblTransferFrom;
	private JLabel lblTransferTo;
	private JLabel lblAmount;
    private JLabel showAllData;
    private StringBuilder sbAllData;
    private LinkedList<Account> globalAccounts;
    private Transaction transferObject = new Transaction();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					String file = "C:\\Users\\rojin\\Downloads\\newAccounts.csv";
                    ReadAccounts readAccounts = new ReadAccounts(file);//initialize variable to get names accNo and balance

                    //fill the lists with the names, accNo and balances given in the csv file
                    LinkedList<String> firstNames = readAccounts.getFirstNames();
                    LinkedList<String> lastNames = readAccounts.getLastNames();
                    LinkedList<Integer> accountList = readAccounts.getAccounts();
                    LinkedList<Integer> balanceList = readAccounts.getBalances();

                    LinkedList<Account> accounts = new LinkedList<>();//variable to store accounts in list
                    for (int i = 0; i < firstNames.size(); i++) {
                        accounts.add(new Account(firstNames.get(i), lastNames.get(i), accountList.get(i), balanceList.get(i)));
                    }

                    BankGUI frame = new BankGUI(accounts);
                    frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public BankGUI(LinkedList<Account> accounts) {
		// initialize global variables
		this.globalAccounts = accounts;
        this.sbAllData = new StringBuilder();
        
        //setup jFrame properties
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 604, 405);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton showAllButton = new JButton("Show All");
		showAllButton.setBounds(10, 14, 97, 21);
		contentPane.add(showAllButton);
		
		showAllData = new JLabel("ShowAllData");
		showAllData.setBounds(117, 14, 463, 60);
		contentPane.add(showAllData);
		
		JButton depositButton = new JButton("Deposit");
		depositButton.setBounds(240, 115, 97, 29);
		contentPane.add(depositButton);
		
		JButton withdrawButton = new JButton("Withdraw");
		withdrawButton.setBounds(240, 205, 97, 29);
		contentPane.add(withdrawButton);
		
		JButton transferButton = new JButton("Transfer");
		transferButton.setBounds(240, 280, 97, 29);
		contentPane.add(transferButton);
		
		accDeposit = new JTextField();
		accDeposit.setBounds(413, 84, 96, 19);
		contentPane.add(accDeposit);
		accDeposit.setColumns(10);
		
		accWithdraw = new JTextField();
		accWithdraw.setBounds(413, 168, 96, 19);
		contentPane.add(accWithdraw);
		accWithdraw.setColumns(10);
		
		acc1Transfer = new JTextField();
		acc1Transfer.setBounds(118, 235, 96, 19);
		contentPane.add(acc1Transfer);
		acc1Transfer.setColumns(10);
		
		acc2Transfer = new JTextField();
		acc2Transfer.setBounds(118, 281, 96, 19);
		contentPane.add(acc2Transfer);
		acc2Transfer.setColumns(10);
		
		depositInput = new JTextField();
		depositInput.setBounds(151, 84, 96, 19);
		contentPane.add(depositInput);
		depositInput.setColumns(10);
		
		withdrawInput = new JTextField();
		withdrawInput.setBounds(151, 168, 96, 19);
		contentPane.add(withdrawInput);
		withdrawInput.setColumns(10);
		
		transferAmount = new JTextField();
		transferAmount.setBounds(413, 235, 96, 19);
		contentPane.add(transferAmount);
		transferAmount.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Deposit Amount:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(20, 83, 121, 16);
		contentPane.add(lblNewLabel);
		
		JLabel lblAccountNumber = new JLabel("Account Number:");
		lblAccountNumber.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblAccountNumber.setBounds(282, 87, 133, 16);
		contentPane.add(lblAccountNumber);
		
		lblWithdrawAmount = new JLabel("Withdraw Amount:");
		lblWithdrawAmount.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblWithdrawAmount.setBounds(10, 171, 139, 16);
		contentPane.add(lblWithdrawAmount);
		
		lblAccountNumber_1 = new JLabel("Account Number:");
		lblAccountNumber_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblAccountNumber_1.setBounds(282, 171, 133, 16);
		contentPane.add(lblAccountNumber_1);
		
		lblTransferFrom = new JLabel("Transfer From:");
		lblTransferFrom.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTransferFrom.setBounds(10, 238, 133, 16);
		contentPane.add(lblTransferFrom);
		
		lblTransferTo = new JLabel("Transfer To:");
		lblTransferTo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTransferTo.setBounds(10, 280, 133, 16);
		contentPane.add(lblTransferTo);
		
		lblAmount = new JLabel("Amount:");
		lblAmount.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblAmount.setBounds(347, 234, 133, 16);
		contentPane.add(lblAmount);
		
		// Create event handler for button actions
        Handler handler = new Handler();
        //action listeners for the buttons
        showAllButton.addActionListener(handler);
        depositButton.addActionListener(handler);
        withdrawButton.addActionListener(handler);
        transferButton.addActionListener(handler);
	}
	private class Handler implements ActionListener{//handles all button actions
		public void actionPerformed(ActionEvent e) {
			//check which button was clicked
			if (e.getActionCommand().equals("Show All")) {
				for (Account account : globalAccounts) {//Step:1 add data to the variable
				    sbAllData.append("Account Number: ").append(account.getAccountNumber()).append(", ")
				             .append("First Name: ").append(account.getFirstName()).append(", ")
				             .append("Last Name: ").append(account.getLastName()).append(", ")
				             .append("Balance: ").append(account.getBalance()).append("<br>");
				}
				//Step:2 display the data in the JLabel in HTML format
                showAllData.setText("<html>" + sbAllData.toString().replace("\n", "<br>") + "</html>");
            } else if (e.getActionCommand().equals("Deposit")) {
                int accNum = Integer.parseInt(accDeposit.getText());
                int amount = Integer.parseInt(depositInput.getText());
                for (Account account : globalAccounts) {//check for existence of account and deposit if exists
                    if (account.getAccountNumber() == accNum) {
                        account.deposit(amount);
                        break;
                    }
                }
            } else if (e.getActionCommand().equals("Withdraw")) {
                int accNum = Integer.parseInt(accWithdraw.getText());
                int amount = Integer.parseInt(withdrawInput.getText());
                for (Account account : globalAccounts) {
                    if (account.getAccountNumber() == accNum) {//check for given account and try to withdraw money if the account exists
                    	if (account.getBalance() >= amount) { // Check if there's enough balance
                            account.withdraw(amount);
                        } else {
                            // Handle insufficient balance situation (e.g., display an error message)
                        	JOptionPane.showMessageDialog(contentPane,"Insufficient money in the account.");
                        }
                    }
                }
            } else if (e.getActionCommand().equals("Transfer")) {//transfer money if transfer button is clicked
                int fromAccNum = Integer.parseInt(acc1Transfer.getText());
                int toAccNum = Integer.parseInt(acc2Transfer.getText());
                int amount = Integer.parseInt(transferAmount.getText());
                Account fromAccount = null;
                Account toAccount = null;
                for (Account account : globalAccounts) {
                    if (account.getAccountNumber() == fromAccNum) {
                        fromAccount = account;
                    }
                    if (account.getAccountNumber() == toAccNum) {
                        toAccount = account;
                    }
                }
                if (fromAccount != null && toAccount != null) {
                    fromAccount.withdraw(amount);
                    toAccount.deposit(amount);
                }	
		}
	}
}
}

