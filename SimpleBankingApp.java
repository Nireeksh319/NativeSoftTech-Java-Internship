import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

public class SimpleBankingApp {
    private static final Scanner sc = new Scanner(System.in);
    private static final ArrayList<Account> accounts = new ArrayList<>();
    private static int nextAccountId = 1001;

    public static void main(String[] args) {
        try (sc) {
            System.out.println("=== Simple Banking Application ===");
            boolean running = true;
            
            while (running) {
                printMenu();
                String choice = sc.nextLine().trim();
                switch (choice) {
                    case "1" -> createAccount();
                    case "2" -> deposit();
                    case "3" -> withdraw();
                    case "4" -> checkBalance();
                    case "5" -> listAccounts();
                    case "6" -> {
                        running = false; System.out.println("Exiting Bank App.");
                    }
                    default -> System.out.println("Invalid choice, try again.");
                }
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n1) Create Account");
        System.out.println("2) Deposit");
        System.out.println("3) Withdraw");
        System.out.println("4) Check Balance");
        System.out.println("5) List Accounts");
        System.out.println("6) Exit");
        System.out.print("Enter choice: ");
    }

    private static void createAccount() {
        System.out.print("Enter account holder name: ");
        String name = sc.nextLine().trim();
        double initial = readDouble("Initial deposit (>=0): ");
        if (initial < 0) {
            System.out.println("Initial deposit cannot be negative.");
            return;
        }
        Account acc = new Account(nextAccountId++, name, initial);
        accounts.add(acc);
        System.out.println("Account created successfully. Account ID: " + acc.getId());
    }

    private static void deposit() {
        int id = readInt("Enter account ID: ");
        Optional<Account> opt = findAccountById(id);
        if (opt.isEmpty()) {
            System.out.println("Account not found.");
            return;
        }
        double amount = readDouble("Enter deposit amount (>0): ");
        if (amount <= 0) {
            System.out.println("Amount must be > 0.");
            return;
        }
        opt.get().deposit(amount);
        System.out.printf("Deposited %.2f. New balance: %.2f%n", amount, opt.get().getBalance());
    }

    private static void withdraw() {
        int id = readInt("Enter account ID: ");
        Optional<Account> opt = findAccountById(id);
        if (opt.isEmpty()) {
            System.out.println("Account not found.");
            return;
        }
        double amount = readDouble("Enter withdrawal amount (>0): ");
        if (amount <= 0) {
            System.out.println("Amount must be > 0.");
            return;
        }
        boolean ok = opt.get().withdraw(amount);
        if (ok) {
            System.out.printf("Withdrew %.2f. New balance: %.2f%n", amount, opt.get().getBalance());
        } else {
            System.out.println("Insufficient funds.");
        }
    }

    private static void checkBalance() {
        int id = readInt("Enter account ID: ");
        Optional<Account> opt = findAccountById(id);
        if (opt.isEmpty()) {
            System.out.println("Account not found.");
            return;
        }
        Account a = opt.get();
        System.out.printf("Account ID: %d, Holder: %s, Balance: %.2f%n", a.getId(), a.getHolderName(), a.getBalance());
    }

    private static void listAccounts() {
        if (accounts.isEmpty()) {
            System.out.println("No accounts available.");
            return;
        }
        System.out.println("Accounts:");
        for (Account a : accounts) {
            System.out.printf("ID: %d | Holder: %s | Balance: %.2f%n", a.getId(), a.getHolderName(), a.getBalance());
        }
    }

    private static Optional<Account> findAccountById(int id) {
        return accounts.stream().filter(a -> a.getId() == id).findFirst();
    }

    private static double readDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = sc.nextLine().trim();
            try {
                return Double.parseDouble(line);
            } catch (NumberFormatException e) {
                System.out.println("Enter a valid number.");
            }
        }
    }

    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = sc.nextLine().trim();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("Enter a valid integer.");
            }
        }
    }

    // Account class as inner static class
    private static class Account {
        private final int id;
        private final String holderName;
        private double balance;

        public Account(int id, String holderName, double initialBalance) {
            this.id = id;
            this.holderName = holderName;
            this.balance = initialBalance;
        }

        public int getId() { return id; }
        public String getHolderName() { return holderName; }
        public double getBalance() { return balance; }

        public void deposit(double amt) {
            if (amt > 0) balance += amt;
        }

        public boolean withdraw(double amt) {
            if (amt > 0 && balance >= amt) {
                balance -= amt;
                return true;
            }
            return false;
        }
    }
}

