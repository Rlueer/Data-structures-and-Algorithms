/**
 * The ElectronicDevice class represents an electronic device.
 * It implements the Device interface.
 */
public class ElectronicDevice implements Device {
    private String category;
    private String name;
    private double price;
    private int quantity;

    /**
     * Constructs a new ElectronicDevice object with the specified properties.
     * @param category The category of the device.
     * @param name The name of the device.
     * @param price The price of the device.
     * @param quantity The quantity of the device.
     */
    public ElectronicDevice(String category, String name, double price, int quantity) {
        this.category = category;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    /**
     * Gets the category of the device.
     * @return The category of the device.
     */
    @Override
    public String getCategory() {
        return category;
    }

    /**
     * Gets the name of the device.
     * @return The name of the device.
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Gets the price of the device.
     * @return The price of the device.
     */
    @Override
    public double getPrice() {
        return price;
    }

    /**
     * Gets the quantity of the device.
     * @return The quantity of the device.
     */
    @Override
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the name of the device.
     * @param name The new name of the device.
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the price of the device.
     * @param price The new price of the device.
     */
    @Override
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Sets the quantity of the device.
     * @param quantity The new quantity of the device.
     */
    @Override
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
