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

class Product {
    /**
     * Name of product
     */
    private String name;
    /**
     * Price of product
     */
    private double price;
    /**
     * Name of the photo (pic.png), doesn't require full path
     */
    private String photoName;
    BufferedImage image;
    public static int MAX_STOCK = 20;
    public static String PHOTO_PATH = "photos/";
    /**
     * How many of this product left in stock
     */
    private int stockCount;
    public Product(String name, String photoName, double price, int stockCount) {
        this.name = name;
        this.photoName = photoName;
        this.price = price;
        this.stockCount = stockCount;

        try {
            image = ImageIO.read(new File(PHOTO_PATH + photoName));
        } catch(IOException e) {
            System.out.println(photoName);
            e.printStackTrace();
        }

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

    /**
     * Set the photo path
     * @param photoPath the path to where photos are stored
     */
    public void setPhotoPath(String photoPath) {
        Product.PHOTO_PATH = photoPath;
    }

    /**
     * Returns the photo path
     * @return the path to the directory where photos are stored
     */
    public String getPhotoPath() {
        return Product.PHOTO_PATH;
    }

    /**
     * Returns the image to be drawn typically
     * @return image of product
     */
    public BufferedImage getImage() {
        return image;
    }

}

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
        VendingMachine vendingMachine = new VendingMachine();
        VendingMachineFrame vendingMachineFrame = new VendingMachineFrame(vendingMachine);
    }
}

class VendingMachineFrame extends JFrame {
    private MachinePanel machinePanel;
    private VendingMachine vendingMachine;
    public VendingMachineFrame(VendingMachine vendingMachine) {
        setTitle("Vending Machine");

        machinePanel = new MachinePanel(vendingMachine.getX_DIMENSION(), vendingMachine.getY_DIMENSION(), vendingMachine.getProducts());
        add(machinePanel);
        setBackground(Color.WHITE);
        setSize(400, 600);

        setLayout(new FlowLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }


}

class MachinePanel extends JPanel {
    private int X_DIMENSION;
    private int Y_DIMENSION;
    public static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private int PRODUCT_DIMENSION = 64;
    private static final int X_GAP = 4;
    private static final int Y_GAP = 16;
    private static final int LABEL_HEIGHT = 12;
    private static final int OFFSET = 12;
    private int WIDTH;
    private int HEIGHT;
    private Product[][] products;


    public MachinePanel(int X_DIMENSION, int Y_DIMENSION, Product[][] products) {
        this.X_DIMENSION = X_DIMENSION;
        this.Y_DIMENSION = Y_DIMENSION;
        this.products = products;
        setLayout(new FlowLayout());
        // width is gaps and products sizes multiplied
        WIDTH = ( (X_DIMENSION - 1) * X_GAP ) + ( X_DIMENSION * PRODUCT_DIMENSION) + (OFFSET * 2);
        HEIGHT = ( (Y_DIMENSION - 1) * Y_GAP ) + ( Y_DIMENSION * PRODUCT_DIMENSION) + LABEL_HEIGHT + (OFFSET * 2);
        System.out.println("Width: " + WIDTH + ", Height: " + HEIGHT);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(DesignPalette.BACKGROUND);

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        int dx, dy;
        for (int y = 0; y < products.length; y++) {
            dy = ( y * PRODUCT_DIMENSION ) + ( y * Y_GAP ) + OFFSET;
            for (int x = 0; x < products[y].length; x++) {
                dx = ( x * PRODUCT_DIMENSION ) + ( X_GAP * x ) + OFFSET;
                g.drawImage(products[y][x].getImage(), dx, dy, PRODUCT_DIMENSION, PRODUCT_DIMENSION, null);
                g.setColor(DesignPalette.FOREGROUND);
                g.fillRect(dx, dy + PRODUCT_DIMENSION, PRODUCT_DIMENSION, LABEL_HEIGHT + 2);
                g.setColor(DesignPalette.BACKGROUND);
                g.setFont(new Font());
                g.drawString(products[y][x].getName(), dx, dy + PRODUCT_DIMENSION + LABEL_HEIGHT);
            }
        }
    }


}

class DesignPalette {
    // BLACK
    public static final Color BACKGROUND = new Color(52, 52, 52);
    // WHITE
    public static final Color FOREGROUND = new Color (251, 251, 251);
    // RED
    public static final Color PRIMARY = new Color(216, 0, 0);
//    public static final Color
//    public static final Color
//    public static final Color
//    public static final Color
//    public static final Color
//    public static final Color
}