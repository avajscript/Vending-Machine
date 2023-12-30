import com.sun.source.tree.NewArrayTree;

import javax.crypto.Mac;
import javax.swing.*;
import java.lang.reflect.Array;
import java.time.Year;
import java.util.ArrayList;

class Product {
    /**
     * Name of product
     */
    private String name;
    /**
     * Price of product
     */
    private double price;
    public static int MAX_STOCK = 20;
    /**
     * How many of this product left in stock
     */
    private int stockCount;
    public Product(String name, double price, int stockCount) {
        this.name = name;
        this.price = price;
        this.stockCount = stockCount;
    }

    /**
     * Reduces stock count if items are in stock
     * @return true if in stock, false if not
     */
    public boolean purchaseProduct() {
        if (stockCount > 0) {
           stockCount--;
           return true;
        }
        return false;
    }

    public boolean inStock() {
        if (stockCount > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Get string representation of this product object
     * @return "name, price, stockCount"
     */
    public String toString() {
        return String.format("%s, %.2f, %d", name, price, stockCount);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStockCount() {
        return stockCount;
    }
    public void setStockCount(int stockCount) {
        this.stockCount = stockCount;
    }

}

public class VendingMachine {
    private int X_DIMENSION;
    private int Y_DIMENSION;
    private Product[][] products;
    private double revenue;

    public VendingMachine() {
        generateProducts();
    }
    public VendingMachine(int X_DIMENSION, int Y_DIMENSION) {
        this.X_DIMENSION = X_DIMENSION;
        this.Y_DIMENSION = Y_DIMENSION;
        products = new Product[Y_DIMENSION][X_DIMENSION];
    }


    public VendingMachine(int X_DIMENSION, int Y_DIMENSION, Product[][] products) {
        this.X_DIMENSION = X_DIMENSION;
        this.Y_DIMENSION = Y_DIMENSION;
        this.products = products;
    }

    public void generateProducts() {
        this.X_DIMENSION = 4;
        this.Y_DIMENSION = 5;
        Product[][] newProducts = new Product[Y_DIMENSION][X_DIMENSION];
        newProducts[0][0] = new Product("Doritos",2.50 , Product.MAX_STOCK);
        newProducts[0][1] = new Product("Sun Chips", 2.25 , Product.MAX_STOCK);
        newProducts[0][2] = new Product("Fritos", 3.00 , Product.MAX_STOCK);
        newProducts[0][3] = new Product("BBQ Chips", 3.25 , Product.MAX_STOCK);
        newProducts[1][0] = new Product("Mars Bar", 2.50 , Product.MAX_STOCK);
        newProducts[1][1] = new Product("O Henry", 2.00 , Product.MAX_STOCK);
        newProducts[1][2] = new Product("Crunchie Bar", 1.75 , Product.MAX_STOCK);
        newProducts[1][3] = new Product("Oreo Hersheys", 2.00 , Product.MAX_STOCK);
        newProducts[2][0] = new Product("Gatorade", 3.00 , Product.MAX_STOCK);
        newProducts[2][1] = new Product("Coca Cola", 3.25 , Product.MAX_STOCK);
        newProducts[2][2] = new Product("Sprite", 2.75 , Product.MAX_STOCK);
        newProducts[2][3] = new Product("Orange Juice", 3.00, Product.MAX_STOCK);
        newProducts[3][0] = new Product("Fizzits",4.00 , Product.MAX_STOCK);
        newProducts[3][1] = new Product("Zoomers",4.25 , Product.MAX_STOCK);
        newProducts[3][2] = new Product("Jawbreakerz", 4.50 , Product.MAX_STOCK);
        newProducts[3][3] = new Product("Twisties", 3.75 , Product.MAX_STOCK);
        newProducts[4][0] = new Product("Carrot Cake", 6.00 , Product.MAX_STOCK);
        newProducts[4][1] = new Product("Black Forest Cake",6.25 , Product.MAX_STOCK);
        newProducts[4][2] = new Product("Strawberry Shortcake",6.50  , Product.MAX_STOCK);
        newProducts[4][3] = new Product("Ice Cream Cake", 5.75, Product.MAX_STOCK);
        this.products = newProducts;
    }

    /**
     * Adds a product to the products array
     * @param product the product to be added
     * @param dx the second dimension of array (x-coord)
     * @param dy the first dimension of array (y-coord)
     * @return true if adding product was successful, false if not
     * Usually false if out of array bounds
     */
    public boolean addProduct(Product product, int dx, int dy) {
        boolean newState = true;
        // not new if product isn't null
        try {
            products[dy][dx] = product;
        } catch(Exception e) {
            System.out.println(e);
            newState = false;
        }

        return newState;
    }

    /**
     * Checks to see if the product is available for purchase
     * and the user has provided enough money
     * @param product the product to check if purchasable
     * @param payment the payment quantity for the product
     * @return true if product is purchasable, false otherwise
     */
    public boolean checkProductPurchasable(Product product, double payment) {
        boolean purchaseState = true;
        if (!(payment >= product.getPrice() && product.inStock())) {
            purchaseState = false;
        }
        return purchaseState;
    }
    /**
     * Processes a product purchase
     * @param product the product to be purchased
     * @param payment the amount of money given for the product
     * @return the payment amount. If equal to given payment, then the payment failed
     */
    public double purchaseProduct(Product product, double payment) {
        // if payment more than product price, process payment
        // and increase revenue
        if(checkProductPurchasable(product, payment)) {
            product.purchaseProduct();
            revenue += product.getPrice();
            payment -= product.getPrice();
        }
        return payment;
    }

    // getters and setters

    /**
     * Returns the X_DIMENSION
     * @return the 2nd dimension of the Products[][] array (x-coord)
     */
    public int getX_DIMENSION() {
        return X_DIMENSION;
    }

    /**
     * Returns the X_DIMENSION
     * @return the 1st dimension of the Products[][] array (y-coord)
     */
    public int getY_DIMENSION() {
        return Y_DIMENSION;
    }

    public static void main(String[] args) {
        VendingMachine vendingMachine = new VendingMachine();
        VendingMachineFrame vendingMachineFrame = new VendingMachineFrame();
    }
}

class VendingMachineFrame extends JFrame {
    private MachinePanel machinePanel;
    private VendingMachine vendingMachine;
    public VendingMachineFrame(VendingMachine vendingMachine) {
        setTitle("Vending Machine");

        machinePanel = new MachinePanel(vendingMachine.getX_DIMENSION(), vendingMachine.getY_DIMENSION());
        setSize(400, 600);
        setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private class MachinePanel extends JPanel {
        int X_DIMENSION;
        int Y_DIMENSION;
        public MachinePanel(int X_DIMENSION, int Y_DIMENSION) {
            this.X_DIMENSION = X_DIMENSION;
            this.Y_DIMENSION = Y_DIMENSION;
        }
    }
}
