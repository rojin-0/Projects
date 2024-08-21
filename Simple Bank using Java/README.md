**Banking System Project**

This project is a Java-based banking system with a graphical user interface (GUI) for managing bank accounts. It includes functionalities for account management such as deposit, withdrawal, and money transfers.

**Project Overview**

**Components**

BankGUI: A Swing-based GUI application for interacting with the banking system.
BankingSystemUsingCSV: A console-based application that demonstrates reading account data from a CSV file and performing basic banking operations.
BasicBankingSystem: A basic console-based example showing fundamental banking operations.

**Files**

1. BankGUI.java
   
   Description: Provides a graphical user interface to manage bank accounts.

     **Features:**
     - View all accounts
     - Deposit funds
     - Withdraw funds
     - Transfer funds between accounts

2. BankingSystemUsingCSV.java
   Description: Demonstrates reading account information from a CSV file and performing operations.

     **Features:**
     - Read account data from newAccounts.csv
     - Perform deposit, withdrawal, and transfer operations
    
3. BasicBankingSystem.java
   Description: Shows basic banking operations with predefined accounts.

     **Features:**
     - Print account balances
     - Perform deposit and withdrawal operations
       
4. Account.java, Customer.java, Transaction.java
   
   Description: Core classes for the banking system.

   Classes:

   Account: Represents a bank account with methods for deposit, withdrawal, and balance retrieval.
   Customer: Represents a customer with first and last names.
   Transaction: Handles money transfers between accounts.
   ReadAccounts: Utility class for reading account information from a CSV file.

**How to Run**
- Use Eclipse and run the BankGUI.java file inside the Assessment.
  
**Dependencies**

Java SDK: Ensure you have Java Development Kit (JDK) installed to compile and run the project.

**Notes**

Make sure to update file paths if necessary (e.g., the path to newAccounts.csv).

The BankingSystemUsingCSV and BasicBankingSystem files are primarily for testing and demonstration purposes.
