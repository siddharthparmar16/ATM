import java.util.Scanner;

class Account {
    int accountNumber;
    String pin;
    float balance;
}

public class Main {
    static final int MAX_ACCOUNTS = 10;
    static Account[] accounts = new Account[MAX_ACCOUNTS];
    static int accountCount = 0;

    public static void createAccount(int accountNumber, String pin, float initialBalance) {
        if (accountCount < MAX_ACCOUNTS) {
            accounts[accountCount] = new Account();
            accounts[accountCount].accountNumber = accountNumber;
            accounts[accountCount].pin = pin;
            accounts[accountCount].balance = initialBalance;
            accountCount++;
            System.out.println("Account created successfully!");
        } else {
            System.out.println("Cannot create more accounts. Limit reached.");
        }
    }

    public static int findAccount(int accountNumber) {
        for (int i = 0; i < accountCount; ++i) {
            if (accounts[i].accountNumber == accountNumber) {
                return i; 
            }
        }
        return -1; 
    }

    public static void withdraw(int accountIndex, float amount) {
        if (accounts[accountIndex].balance >= amount) {
            accounts[accountIndex].balance -= amount;
            System.out.printf("Withdrawal successful. Remaining balance: $%.2f\n", accounts[accountIndex].balance);
        } else {
            System.out.println("Insufficient funds!");
        }
    }

    public static void deposit(int accountIndex, float amount) {
        accounts[accountIndex].balance += amount;
        System.out.printf("Deposit successful. New balance: $%.2f\n", accounts[accountIndex].balance);
    }

    public static void displayBalance(int accountIndex) {
        System.out.println("Account Number: " + accounts[accountIndex].accountNumber);
        System.out.printf("Current Balance: $%.2f\n", accounts[accountIndex].balance);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int choice, accountNumber, accountIndex;
        String pin;
        float amount;

        System.out.println("Enter Account Number:");
        accountNumber = scanner.nextInt();
        System.out.println("Enter Account Pin:");
        pin = scanner.next();
        System.out.println("Enter Account Starting Balance:");
        float init_balance = scanner.nextFloat();
        createAccount(accountNumber, pin, init_balance);

        do {
            System.out.println("\nWelcome to Mini ATM");
            System.out.println("1. Login");
            System.out.println("2. Exit");
            System.out.print("Enter choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter account number: ");
                    accountNumber = scanner.nextInt();
                    System.out.print("Enter PIN: ");
                    pin = scanner.next();

                    accountIndex = findAccount(accountNumber);
                    if (accountIndex != -1 && accounts[accountIndex].pin.equals(pin)) {
                        int option;
                        do {
                            System.out.println("\n1. Withdraw");
                            System.out.println("2. Deposit");
                            System.out.println("3. Check Balance");
                            System.out.println("4. Logout");
                            System.out.print("Enter option: ");
                            option = scanner.nextInt();

                            switch (option) {
                                case 1:
                                    System.out.print("Enter amount to withdraw: ");
                                    amount = scanner.nextFloat();
                                    withdraw(accountIndex, amount);
                                    break;
                                case 2:
                                    System.out.print("Enter amount to deposit: ");
                                    amount = scanner.nextFloat();
                                    deposit(accountIndex, amount);
                                    break;
                                case 3:
                                    displayBalance(accountIndex);
                                    break;
                                case 4:
                                    System.out.println("Logged out.");
                                    break;
                                default:
                                    System.out.println("Invalid option");
                            }
                        } while (option != 4);
                    } else {
                        System.out.println("Invalid account number or PIN");
                    }
                    break;
                case 2:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        } while (choice != 2);

        scanner.close();
    }
}
