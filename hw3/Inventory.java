import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The Inventory class represents an inventory management system for electronic devices.
 * It stores devices in categorized lists and provides various operations to manage the inventory.
 */
public class Inventory {
    private LinkedList<ArrayList<Device>> deviceLists;
    /**
     * Constructs a new Inventory object with an empty device list.
     */
    public Inventory() {
        deviceLists = new LinkedList<>();
    }
    /**
     * Adds a new device to the inventory.
     * @param device The device to add.
     *  the time complexity is O(N), where N is the number of categories.
     */
    public void addDevice(Device device) {
        String category = device.getCategory();
        boolean categoryFound = false;
        
        // Search for existing category
        for (ArrayList<Device> deviceList : deviceLists) {
            if (deviceList.get(0).getCategory().equals(category)) {
                deviceList.add(device);
                categoryFound = true;
                break;
            }
        }

        // If category not found, create a new category
        if (!categoryFound) {
            ArrayList<Device> newDeviceList = new ArrayList<>();
            newDeviceList.add(device);
            deviceLists.add(newDeviceList);
        }

        System.out.println(category + ", " + device.getName() + ", " + device.getPrice() + "0$, " + device.getQuantity() + " amount added to inventory.");

    }
    /**
     * Removes a device from the inventory by its name.
     * @param deviceName The name of the device to remove.
     * the time complexity is O(N'2), where N is the number of categories.
     */
    public void removeDevice(String deviceName) {
        boolean deviceFound = false;
        
        // Search for the device in all lists
        for (ArrayList<Device> deviceList : deviceLists) {
            for (Device device : deviceList) {
                if (device.getName().equals(deviceName)) {
                    deviceList.remove(device);
                    deviceFound = true;
                    System.out.println(deviceName + " removed from inventory.");
                    break;
                }
            }
        }
        
        if (!deviceFound) {
            System.out.println("Device not found in inventory.");
        }
    }
    /**
     * Updates the details of a device in the inventory.
     * @param deviceName The name of the device to update.
     * @param newPrice The new price of the device.
     * @param newQuantity The new quantity of the device.
     * the time complexity is O(N'2), where N is the total number of devices.
     */
    public void updateDevice(String deviceName, double newPrice, int newQuantity) {
        boolean deviceFound = false;
        
        // Search for the device in all lists
        for (ArrayList<Device> deviceList : deviceLists) {
            for (Device device : deviceList) {
                if (device.getName().equals(deviceName)) {
                    device.setPrice(newPrice);
                    device.setQuantity(newQuantity);
                    deviceFound = true;
                    System.out.println(deviceName + " details updated: Price - " + newPrice + "$, Quantity - " + newQuantity);
                    break;
                }
            }
        }
        
        if (!deviceFound) {
            System.out.println("Device not found in inventory.");
        }
    }
    /**
     * Lists all devices in the inventory.
     * the time complexity is O(N'2), where N is the total number of devices.
     * O(N^2), where N is the total number of devices.
     */
    public void listAllDevices() {
        System.out.println("Device List:");
        int count = 1;
        for (ArrayList<Device> deviceList : deviceLists) {
            for (Device device : deviceList) {
                System.out.println(count + ". Category: " + device.getCategory() + ", Name: " + device.getName() + ", Price: " + device.getPrice() + "0$, Quantity: " + device.getQuantity());
                count++;
            }
        }
    }
    /**
     * Finds and displays the cheapest device in the inventory.
     * O(N^2), where N is the total number of devices.
     */
    public void findCheapestDevice() {
        Device cheapestDevice = null;
        double minPrice = Double.MAX_VALUE;
        
        // Search for the device with the minimum price
        for (ArrayList<Device> deviceList : deviceLists) {
            for (Device device : deviceList) {
                if (device.getPrice() < minPrice) {
                    minPrice = device.getPrice();
                    cheapestDevice = device;
                }
            }
        }
        
        if (cheapestDevice != null) {
            System.out.println("The cheapest device is:");
            System.out.println("Category: " + cheapestDevice.getCategory() + ", Name: " + cheapestDevice.getName() + ", Price: " + cheapestDevice.getPrice() + "0$, Quantity: " + cheapestDevice.getQuantity());
        } else {
            System.out.println("No devices found in inventory.");
        }
    }
    /**
     * Sorts all devices in the inventory by price.
     * O(N log N), where N is the total number of devices.
     */
    public void sortDevicesByPrice() {
        ArrayList<Device> allDevices = new ArrayList<>();
        
        // Collect all devices into a single list
        for (ArrayList<Device> deviceList : deviceLists) {
            allDevices.addAll(deviceList);
        }
        
        // Sort devices by price
        Collections.sort(allDevices, Comparator.comparing(Device::getPrice));
        
        System.out.println("Devices sorted by price:");
        int count = 1;
        for (Device device : allDevices) {
            System.out.println(count + ". Category: " + device.getCategory() + ", Name: " + device.getName() + ", Price: " + device.getPrice() + "0$, Quantity: " + device.getQuantity());
            count++;
        }
    }
    /**
     * Calculates and displays the total value of the inventory.
     * O(N^2), where N is the total number of devices.
     */
    public void calculateTotalInventoryValue() {
        double totalValue = 0;
        
        // Calculate total value of all devices in inventory
        for (ArrayList<Device> deviceList : deviceLists) {
            for (Device device : deviceList) {
                totalValue += device.getPrice() * device.getQuantity();
            }
        }
        
        System.out.println("Total inventory value: $" + totalValue + "0");
    }
    /**
     * Restocks a device in the inventory by increasing its quantity.
     * @param deviceName The name of the device to restock.
     * @param quantityToAdd The quantity to add to the existing stock.
     * O(N^2), where N is the total number of devices.
     */
    public void restockDevice(String deviceName, int quantityToAdd) {
        boolean deviceFound = false;
        
        // Search for the device in all lists
        for (ArrayList<Device> deviceList : deviceLists) {
            for (Device device : deviceList) {
                if (device.getName().equals(deviceName)) {
                    device.setQuantity(device.getQuantity() + quantityToAdd);
                    deviceFound = true;
                    System.out.println(deviceName + " restocked. New quantity: " + device.getQuantity());
                    break;
                }
            }
        }
        
        if (!deviceFound) {
            System.out.println("Device not found in inventory.");
        }
    }

    /**
     * Adds a new device to the inventory based on user input.
     * @param scanner The Scanner object used for user input.
     * O(1), where N is the total number of devices.
     */
    public void addDevice(Scanner scanner) {
        System.out.print("Enter category name: ");
        String category = scanner.nextLine();
        System.out.print("Enter device name: ");
        String name = scanner.nextLine();
        System.out.print("Enter price: ");
        String priceStr = scanner.nextLine();
        double price = parsePrice(priceStr);
        System.out.print("Enter quantity: ");
        int quantity = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
        Device device;
        
        if ("TV".equals(category)) {
            device = new TV(name, price, quantity);
        } else if ("Laptop".equals(category)) {
            device = new Laptop(name, price, quantity);
        } else if ("Smartwatch".equals(category)) {
            device = new Smartwatch(name, price, quantity);
        } else if ("Headphones".equals(category)) {
            device = new Headphones(name, price, quantity);
        } else if ("Smartphone".equals(category)) {
            device = new Smartphone(name, price, quantity);
        } else {
            System.out.println("Invalid category. Device not added to inventory.");
            return; // Exit method if category is invalid
        }
    
        addDevice(device);

    }

    /**
     * Parses the price input string and extracts the price value.
     * @param priceStr The price input string.
     * @return The parsed price value as a double.
     * O(1), where N is the total number of devices.
     */
    private double parsePrice(String priceStr) {
        // Check if the price string ends with a currency symbol ('$')
        if (priceStr.endsWith("$")) {
            // If it ends with '$', remove the currency symbol and parse the remaining string
            priceStr = priceStr.substring(0, priceStr.length() - 1);
        }
        // Try parsing the modified price string as a double
        try {
            return Double.parseDouble(priceStr);
        } catch (NumberFormatException e) {
            // If parsing fails, print a message and return a default value indicating failure
            System.out.println("Invalid price format. Please enter a valid price.");
            return -1; // Return a default value indicating failure
        }
    }

    /**
     * Removes a device from the inventory based on user input.
     * @param scanner The Scanner object used for user input.
     * O(1), where N is the total number of devices.
     */
    public void removeDevice(Scanner scanner) {
        System.out.print("Enter the name of the device to remove: ");
        String deviceName = scanner.nextLine();
        removeDevice(deviceName);
    }

    /**
     * Updates the details of a device in the inventory based on user input.
     * @param scanner The Scanner object used for user input.
     * O(1), where N is the total number of devices.
     */
    public void updateDevice(Scanner scanner) {
        System.out.print("Enter the name of the device to update: ");
        String deviceName = scanner.nextLine();
        System.out.print("Enter new price (leave blank to keep current price): ");
        String newPriceStr = scanner.nextLine().replace("$", ""); // Remove currency symbol
        double newPrice = (newPriceStr.isEmpty()) ? -1 : Double.parseDouble(newPriceStr);
        System.out.print("Enter new quantity (leave blank to keep current quantity): ");
        String newQuantityStr = scanner.nextLine();
        int newQuantity = (newQuantityStr.isEmpty()) ? -1 : Integer.parseInt(newQuantityStr);
    
        updateDevice(deviceName, newPrice, newQuantity);
    }
    

    /**
     * Restocks a device in the inventory based on user input.
     * @param scanner The Scanner object used for user input.
     * O(1), where N is the total number of devices.
     */
    public void restockDevice(Scanner scanner) {
        System.out.print("Enter the name of the device to restock: ");
        String deviceName = scanner.nextLine();
        System.out.print("Do you want to add or remove stock? (Add/Remove): ");
        String action = scanner.nextLine();
        
        if (action.equalsIgnoreCase("Add")) {
            System.out.print("Enter the quantity to add: ");
            int quantityToAdd = scanner.nextInt();
            restockDevice(deviceName, quantityToAdd);
        } else if (action.equalsIgnoreCase("Remove")) {
            System.out.print("Enter the quantity to remove: ");
            int quantityToRemove = scanner.nextInt();
            restockDevice(deviceName, -quantityToRemove); // Negative quantity to indicate removal
        } else {
            System.out.println("Invalid action. Please enter 'Add' or 'Remove'.");
        }
    }
    /**
     * Exports the inventory report to a text file.
     * O(N^2), where N is the total number of devices.
     */
    public void exportInventoryReport() {
        String fileName = "inventory_report.txt";
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            // Write the header of the inventory report
            writer.println("Electronics Shop Inventory Report");
            writer.println("Generated on: " + getCurrentDate());
            writer.println("---------------------------------------");
            writer.println("| No. | Category | Name | Price | Quantity |");
            writer.println("---------------------------------------");

            // Write each device entry
            int count = 1;
            for (ArrayList<Device> deviceList : deviceLists) {
                for (Device device : deviceList) {
                    writer.println("| " + count + " | " + device.getCategory() + " | " + device.getName() + " | $" + device.getPrice() + " | " + device.getQuantity() + " |");
                    count++;
                }
            }

            writer.println("---------------------------------------");

            // Write summary
            writer.println("Summary:");
            writer.println("- Total Number of Devices: " + getTotalDeviceCount());
            writer.println("- Total Inventory Value: $" + getTotalInventoryValue());
            writer.println("End of Report");

            System.out.println("Inventory report exported to " + fileName);
        } catch (IOException e) {
            System.out.println("Error occurred while exporting inventory report: " + e.getMessage());
        }
    }

    /**
     * Gets the current date in a formatted string.
     * @return The current date as a formatted string.
     * O(1), where N is the total number of devices.
     */
    private String getCurrentDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");
        return dateFormat.format(new Date());
    }

    /**
     * Calculates the total number of devices in the inventory.
     * @return The total number of devices.
     * O(N), where N is the total number of devices.
     */
    private int getTotalDeviceCount() {
        int totalDevices = 0;
        for (ArrayList<Device> deviceList : deviceLists) {
            totalDevices += deviceList.size();
        }
        return totalDevices;
    }

    /**
     * Calculates the total value of the inventory.
     * @return The total inventory value.
     * O(N^2), where N is the total number of devices.
     */
    private double getTotalInventoryValue() {
        double totalValue = 0;
        for (ArrayList<Device> deviceList : deviceLists) {
            for (Device device : deviceList) {
                totalValue += device.getPrice() * device.getQuantity();
            }
        }
        return totalValue;
    }
}
