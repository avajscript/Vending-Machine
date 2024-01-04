import javax.swing.*;
import java.awt.*;

class MachinePanel extends JPanel {
    private int X_DIMENSION;
    private int Y_DIMENSION;
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
                // draw the products
                g.drawImage(products[y][x].getImage(), dx, dy, PRODUCT_DIMENSION, PRODUCT_DIMENSION, null);
                // draw the text label
                g.setColor(DesignPalette.FOREGROUND);
                g.fillRect(dx, dy + PRODUCT_DIMENSION, PRODUCT_DIMENSION, LABEL_HEIGHT + 2);
                g.setColor(DesignPalette.BACKGROUND);
                g.setFont(DesignPalette.SMALL_FONT);
                g.drawString(products[y][x].getName(), dx, dy + PRODUCT_DIMENSION + LABEL_HEIGHT);
                // draw the product number
                g.setColor(DesignPalette.PRIMARY);
                g.fillRect(dx, dy, LABEL_HEIGHT + LABEL_HEIGHT / 2, LABEL_HEIGHT + 2);
                g.setColor(DesignPalette.FOREGROUND);
                String yLab = y > 0 ? (String) "" + y : "";
                g.drawString((String) "" + yLab + (x + 1), dx, dy + LABEL_HEIGHT);
            }
        }
    }
}