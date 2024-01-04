import com.sun.source.tree.NewArrayTree;

import javax.crypto.Mac;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.time.Year;
import java.util.ArrayList;



public class VendingMachine {
    /**
     * number of horizontal products per row
     */
    private int X_DIMENSION;
    /**
     * number of vertical columns of products
     */
    private int Y_DIMENSION;
    /**
     * 2d product array set by x and y dimensions (X_DIMENSION)
     */
    private Product[][] products;
    private double revenue;

    /**
     * Generates the default products for the vending machine instantiation
     */
    public VendingMachine() {
        generateProducts();
    }

    /**
     * Creates an empty vending machine (no products) instantiation
     * @param X_DIMENSION
     * @param Y_DIMENSION
     */
    public VendingMachine(int X_DIMENSION, int Y_DIMENSION) {
        this.X_DIMENSION = X_DIMENSION;
        this.Y_DIMENSION = Y_DIMENSION;
        products = new Product[Y_DIMENSION][X_DIMENSION];
    }

    /**
     * Creates a vending machine with the products provided instantiation
     * @param X_DIMENSION
     * @param Y_DIMENSION
     * @param products list of products in the vending machine
     */
    public VendingMachine(int X_DIMENSION, int Y_DIMENSION, Product[][] products) {
        this.X_DIMENSION = X_DIMENSION;
        this.Y_DIMENSION = Y_DIMENSION;
        this.products = products;
    }

    /**
     * Generates a 4 x 5 array of products for the vending machine
     */
    public void generateProducts() {
        this.X_DIMENSION = 4;
        this.Y_DIMENSION = 5;
        Product[][] newProducts = new Product[Y_DIMENSION][X_DIMENSION];
        newProducts[0][0] = new Product("Doritos", "doritos.png",2.50, Product.MAX_STOCK);
        newProducts[0][1] = new Product("Sun Chips", "sun_chips.png",2.25 , Product.MAX_STOCK);
        newProducts[0][2] = new Product("Fritos", "fritos.png",3.00 , Product.MAX_STOCK);
        newProducts[0][3] = new Product("BBQ Chips", "bbq_chips.png",3.25 , Product.MAX_STOCK);
        newProducts[1][0] = new Product("Mars Bar", "mars_bar.png", 2.50 , Product.MAX_STOCK);
        newProducts[1][1] = new Product("O Henry", "o_henry.png", 2.00 , Product.MAX_STOCK);
        newProducts[1][2] = new Product("Crunchie Bar", "crunchie.png", 1.75 , Product.MAX_STOCK);
        newProducts[1][3] = new Product("Oreo Hersheys", "hershey.png", 2.00 , Product.MAX_STOCK);
        newProducts[2][0] = new Product("Gatorade", "gatorade.png", 3.00 , Product.MAX_STOCK);
        newProducts[2][1] = new Product("Coca Cola", "cola.png", 3.25 , Product.MAX_STOCK);
        newProducts[2][2] = new Product("Sprite", "sprite.png", 2.75 , Product.MAX_STOCK);
        newProducts[2][3] = new Product("Orange Juice", "orange_juice.png", 3.00, Product.MAX_STOCK);
        newProducts[3][0] = new Product("Fizzits","fizzits.png", 4.00 , Product.MAX_STOCK);
        newProducts[3][1] = new Product("Zoomers","zoomers.png", 4.25 , Product.MAX_STOCK);
        newProducts[3][2] = new Product("Jawbreakerz", "jawbreaker.png", 4.50 , Product.MAX_STOCK);
        newProducts[3][3] = new Product("Twisties", "twisties.png", 3.75 , Product.MAX_STOCK);
        newProducts[4][0] = new Product("Carrot Cake", "carrot_cake.png", 6.00 , Product.MAX_STOCK);
        newProducts[4][1] = new Product("Black Forest Cake","black_forest.png", 6.25 , Product.MAX_STOCK);
        newProducts[4][2] = new Product("Strawberry Shortcake","strawberry_shortcake.png", 6.50  , Product.MAX_STOCK);
        newProducts[4][3] = new Product("Ice Cream Cake", "icecream_cake.png", 5.75, Product.MAX_STOCK);
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
     * Returns a list of all the products
     * @return Product 2d array
     */
    public Product[][] getProducts() {
        return products;
    }
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
        SwingUtilities.invokeLater(() -> {
            VendingMachine vendingMachine = new VendingMachine();
            VendingMachineFrame vendingMachineFrame = new VendingMachineFrame(vendingMachine);
        });

    }
}







