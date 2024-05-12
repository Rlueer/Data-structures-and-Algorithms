import java.util.Scanner;

/**
 * The Menu class provides a text-based menu for interacting with an Electronics Inventory Management System.
 * It allows users to perform various operations such as adding, removing, updating, listing, and managing inventory.
 */

public class Menu {
    /**
     * Main method to run the Electronics Inventory Management System.
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Inventory inventory = new Inventory(); // Assume Inventory class is implemented elsewhere

        int choice;
        do {
            // Display the menu options
            System.out.println("Welcome to the Electronics Inventory Management System!");
            System.out.println("Please select an option:");
            System.out.println("1. Add a new device");
            System.out.println("2. Remove a device");
            System.out.println("3. Update device details");
            System.out.println("4. List all devices");
            System.out.println("5. Find the cheapest device");
            System.out.println("6. Sort devices by price");
            System.out.println("7. Calculate total inventory value");
            System.out.println("8. Restock a device");
            System.out.println("9. Export inventory report");
            System.out.println("0. Exit");

            // Read user choice
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            // Perform the corresponding action based on the user's choice
            switch (choice) {
                case 1:
                    // Add a new device
                    inventory.addDevice(scanner);
                    break;
                case 2:
                    // Remove a device
                    inventory.removeDevice(scanner);
                    break;
                case 3:
                    // Update device details
                    inventory.updateDevice(scanner);
                    break;
                case 4:
                    // List all devices
                    inventory.listAllDevices();
                    break;
                case 5:
                    // Find the cheapest device
                    inventory.findCheapestDevice();
                    break;
                case 6:
                    // Sort devices by price
                    inventory.sortDevicesByPrice();
                    break;
                case 7:
                    // Calculate total inventory value
                    inventory.calculateTotalInventoryValue();
                    break;
                case 8:
                    // Restock a device
                    inventory.restockDevice(scanner);
                    break;
                case 9:
                    // Export inventory report
                    inventory.exportInventoryReport();
                    break;
                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
                    break;
            }
        } while (choice != 0);

        scanner.close(); // Close the scanner
    } 
}
