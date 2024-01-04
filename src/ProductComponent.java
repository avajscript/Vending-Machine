import javax.swing.*;
import java.awt.*;

public class ProductComponent extends JPanel {
    public ProductComponent(Product product) {
        JLabel picLabel = new JLabel(new ImageIcon(product.getImage()));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = GridBagConstraints.REMAINDER;


        // create text panel
        JPanel textPanel = new JPanel();
        textPanel.setLayout(new GridBagLayout());
        // create text panel components
        JLabel productLabel = new JLabel(product.getName());
        productLabel.setOpaque(true);
        productLabel.setBackground(DesignPalette.FOREGROUND);
        JLabel priceLabel = new JLabel("$" + product.getPrice());
        priceLabel.setOpaque(true);
        priceLabel.setBackground(DesignPalette.FOREGROUND);
        // add components to text panel
        textPanel.add(productLabel, gbc);
        textPanel.add(priceLabel, gbc);

        // add components to main panel

        add(picLabel);
        add(textPanel);
        setBackground(DesignPalette.FOREGROUND);
        setBorder(BorderFactory.createLineBorder(DesignPalette.BACKGROUND));

    }
}
