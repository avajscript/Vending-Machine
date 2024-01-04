import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Bag extends JPanel{
    ArrayList<ProductComponent> productComponents;
    public Bag() {

        // heading title
        JLabel headingLabel = new JLabel("Bag");
        headingLabel.setFont(DesignPalette.HEADING_FONT);

        // add the products to product list to be rendered
//        for (int y = 0; y < products.length; y++) {
//            for (int x = 0; x < products[y].length; x++) {
//                ProductComponent productComponent = new ProductComponent(products[y][x]);
//                productComponents.add(productComponent);
//            }
//        }

       Product product = new Product("Lollipop", "jawbreaker.png", 9.99, 10);
       Product product2 = new Product("Cake", "carrot_cake.png", 14.99, 7);
       ProductComponent productComponent = new ProductComponent(product);
       ProductComponent productComponent2 = new ProductComponent(product2);

       JPanel productHolder = new JPanel();
       productHolder.setLayout(new FlowLayout(FlowLayout.LEFT));
       productHolder.add(productComponent);
       productHolder.add(productComponent2);


        // add components
        add(headingLabel);
        add(productHolder);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setVisible(true);
    }

    public static void main(String[] args) {
        new Bag();
    }
}
