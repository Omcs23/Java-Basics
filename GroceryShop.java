
import java.util.Scanner;

public class GroceryShop {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] items = {"Milk", "Bread", "Rice", "Sugar", "Salt",
            "Oil", "Eggs", "Tea", "Coffee", "Butter"};
        float[] prices = {40.0f, 25.0f, 60.0f, 45.0f, 20.0f,
            150.0f, 6.0f, 120.0f, 200.0f, 80.0f};
        int[] stock = {10, 20, 15, 10, 25, 5, 30, 10, 8, 12};

        System.out.println("Welcome to Grocery Shop!");

        while (true) {
            System.out.println("\nPress Enter to start shopping or type 'Exit' to leave:");
            String sessionInput = sc.nextLine();
            if (sessionInput.equalsIgnoreCase("Exit")) {
                System.out.println("Thank you for visiting!");
                break;
            }

            float totalBill = 0;

            while (true) {
                System.out.println("\nEnter item name (or type 'Complete' to finish shopping):");
                String selectedItem = sc.nextLine();

                if (selectedItem.equalsIgnoreCase("Complete")) {
                    break;
                }

                int index = searchItem(items, selectedItem);

                if (index == -1) {
                    System.out.println("Item not available.");
                } else if (stock[index] <= 0) {
                    System.out.println("Sorry, " + items[index] + " is out of stock!");
                } else {
                    int quantity = 0;
                    boolean validInput = false;

                    while (!validInput) {
                        System.out.println("Enter quantity:");
                        try {
                            quantity = Integer.parseInt(sc.nextLine());
                            if (quantity <= 0) {
                                System.out.println("Please enter a positive quantity.");
                            } else {
                                validInput = true;
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input. Enter a numeric value.");
                        }
                    }

                    if (quantity > stock[index]) {
                        System.out.println("Only " + stock[index] + " available. Adjusting quantity.");
                        quantity = stock[index];
                    }

                    float itemCost = prices[index] * quantity;
                    totalBill += itemCost;
                    stock[index] -= quantity;

                    System.out.println(quantity + " x " + items[index] + " added. Cost: " + itemCost);
                }
            }

            totalBill = applyDiscount(totalBill);

            System.out.println("\nYour total bill: " + totalBill);
            System.out.println("Average item price: " + calculateAverage(prices));
        }

        sc.close();
    }

    public static int searchItem(String[] items, String name) {
        for (int i = 0; i < items.length; i++) {
            if (items[i].equalsIgnoreCase(name)) {
                return i;
            }
        }
        return -1;
    }

    public static float calculateAverage(float[] prices) {
        float sum = 0;
        for (float price : prices) {
            sum += price;
        }
        return sum / prices.length;
    }

    public static float applyDiscount(float total) {
        if (total >= 5000) {
            total *= 0.8f;
            System.out.println("20% discount applied!");
        } else if (total >= 1000) {
            total *= 0.9f;
            System.out.println("10% discount applied!");
        }
        return total;
    }
}
