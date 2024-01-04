import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

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