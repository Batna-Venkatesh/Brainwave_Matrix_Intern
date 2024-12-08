import java.util.*;

class Account {
   
    private String pin;
    private double balance;
    private List<String> transactionHistory;

    public Account(String pin, double initialBalance) {
       
        this.pin = pin;
        this.balance = initialBalance;
        this.transactionHistory = new ArrayList<>();
    }

    public boolean authenticate(String enteredPin) {
        return pin.equals(enteredPin);
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactionHistory.add("Deposited: $" + amount);
            System.out.println("Deposit successful.");
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            transactionHistory.add("Withdrew: $" + amount);
            System.out.println("Withdrawal successful.");
        } else {
            System.out.println("Invalid withdrawal amount or insufficient balance.");
        }
    }

    public void showTransactionHistory() {
        if (transactionHistory.isEmpty()) {
            System.out.println("No transactions available.");
        } else {
            System.out.println("Transaction History:");
            for (String transaction : transactionHistory) {
                System.out.println(transaction);
            }
        }
    }
    
}

class ATM {
    private Map<String, Account> accounts;
    private Scanner scanner;

    public ATM() {
        accounts = new HashMap<>();
        scanner = new Scanner(System.in);
        initializeAccounts();
    }

    private void initializeAccounts() {
        accounts.put("user1",new Account("1234", 0.00));
        
    }

    public void start() {
        System.out.println("Welcome to the ATM!");
     
	System.out.print("Enter User ID: ");
	String userId = scanner.nextLine();
	 Account account = accounts.get(userId);
	if (account == null) {
            System.out.println("Invalid User ID.");
            return;
        }
        System.out.print("Enter PIN: ");
        String enteredPin = scanner.nextLine();

        if (!account.authenticate(enteredPin)) {
            System.out.println("Invalid PIN.");
            return;
        }

        System.out.println("Login successful!");

        boolean quit = false;
        while (!quit) {
            System.out.println("\n--- ATM Menu ---");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Transaction History");
            System.out.println("5. Quit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Current Balance: $" + account.getBalance());
                    break;
                case 2:
                    System.out.print("Enter deposit amount: ");
                    double depositAmount = scanner.nextDouble();
                    account.deposit(depositAmount);
                    break;
                case 3:
                    System.out.print("Enter withdrawal amount: ");
                    double withdrawalAmount = scanner.nextDouble();
                    account.withdraw(withdrawalAmount);
                    break;
                case 4:
                    account.showTransactionHistory();
                    break;
                case 5:
                    quit = true;
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    public static void main(String[] args) {
        ATM atm = new ATM();
        atm.start();
    }
}
