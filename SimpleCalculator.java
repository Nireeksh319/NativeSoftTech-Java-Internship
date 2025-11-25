import java.util.Scanner;

public class SimpleCalculator {

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            boolean running = true;
            
            System.out.println("=== SIMPLE CALCULATOR ===");
            
            while (running) {
                System.out.println("\nChoose operation:");
                System.out.println("1) Add");
                System.out.println("2) Subtract");
                System.out.println("3) Multiply");
                System.out.println("4) Divide");
                System.out.println("5) Power (a^b)");
                System.out.println("6) Modulus");
                System.out.println("7) Exit");
                System.out.print("Enter choice: ");
                
                String choice = sc.nextLine().trim();
                
                switch(choice) {
                    case "1" -> performOperation(sc, "add");
                    
                    case "2" -> performOperation(sc, "sub");
                    
                    case "3" -> performOperation(sc, "mul");
                    
                    case "4" -> performOperation(sc, "div");
                    
                    case "5" -> performOperation(sc, "pow");
                    
                    case "6" -> performOperation(sc, "mod");
                    
                    case "7" -> {
                        running = false;
                        System.out.println("Goodbye!");
                    }
                    
                    default -> System.out.println("Invalid choice! Please enter 1-7.");
                }
            }
        }
    }

    // Read a number safely
    private static double readDouble(Scanner sc, String label) {
        while (true) {
            System.out.print("Enter " + label + ": ");
            try {
                return Double.parseDouble(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid number! Try again.");
            }
        }
    }

    // Perform selected operation
    private static void performOperation(Scanner sc, String type) {
        double a = readDouble(sc, "a");
        double b = readDouble(sc, "b");
        double result;

        switch (type) {
            case "add" -> {
                result = a + b;
                System.out.println("Result: " + result);
            }

            case "sub" -> {
                result = a - b;
                System.out.println("Result: " + result);
            }

            case "mul" -> {
                result = a * b;
                System.out.println("Result: " + result);
            }

            case "div" -> {
                if (b == 0) {
                    System.out.println("Error: Cannot divide by zero!");
                } else {
                    result = a / b;
                    System.out.println("Result: " + result);
                }
            }

            case "pow" -> {
                result = Math.pow(a, b);
                System.out.println("Result: " + result);
            }

            case "mod" -> {
                if (b == 0) {
                    System.out.println("Error: Cannot take modulus with zero!");
                } else {
                    result = a % b;
                    System.out.println("Result: " + result);
                }
            }
        }
    }
}
