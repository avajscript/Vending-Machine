import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class VendingInput extends JPanel {
    JLabel itemLabel;
    private String input = "";
    public static final double[] coins = { .5, .10, .25, 1, 2 };
    private double coinTotal = 0;
    JLabel coinInput;
    public VendingInput() {

        // grid alignment for main container
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = GridBagConstraints.REMAINDER;

        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.weightx = 1;
        gbc2.fill = GridBagConstraints.HORIZONTAL;
        gbc2.gridwidth = GridBagConstraints.REMAINDER;
        gbc2.insets = new Insets(8, 0, 8, 0);

        JPanel panelSeperator = new JPanel();
        panelSeperator.setLayout(new GridBagLayout());
        panelSeperator.setBackground(DesignPalette.BACKGROUND);


        JPanel itemPanel = new JPanel();
        itemPanel.setLayout(new GridBagLayout());
        // top label
        JLabel topLabel = new JLabel("Select Item");
        // button holder
        JPanel btnPanel = new JPanel();
        btnPanel.setLayout(new GridLayout(3, 3));

        // add buttons to button holder
        for (int i = 0; i < 9; i++) {
            JButton btn = new JButton((String) "" + (i + 1));
            btn.addActionListener(buttonListener());
            btn.setBorder(new EmptyBorder(4, 4, 4, 4));
            btnPanel.add(btn);

        }
        // label for user input
        itemLabel = new JLabel(" ");
        itemLabel.setHorizontalAlignment(JLabel.RIGHT);
        itemLabel.setOpaque(true);
        itemLabel.setBackground(DesignPalette.FOREGROUND);

        JButton delBtn = new JButton("Clear");
        delBtn.setBorder(new EmptyBorder(4, 4, 4, 4));
        delBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // remove last char from user input if it's not empty
                if (!input.isEmpty()) {
                    input = input.substring(0, input.length() - 1);
                    if (input.length() == 1){
                        input = "";
                        itemLabel.setText(" ");
                    } else {
                        itemLabel.setText(input);
                    }
                }
            }
        });
        // add elements to item panel
        itemPanel.add(topLabel, gbc);
        itemPanel.add(btnPanel, gbc);
        itemPanel.add(itemLabel, gbc);
        itemPanel.add(delBtn, gbc);


        JPanel coinPanel = new JPanel();
        coinPanel.setLayout(new GridBagLayout());

        // label for coin entry
        JLabel coinLabel = new JLabel("Add coins");

        // panel for coins
        JPanel coinBtnPanel = new JPanel();
        coinBtnPanel.setLayout(new GridLayout(3, 2));

        // add coin buttons to coin panel
        for (double coinPrice : coins) {
            JButton coinBtn = new JButton("$ " + coinPrice);
            coinBtn.setBorder(new EmptyBorder(4, 4, 4, 4));
            coinBtn.addActionListener(coinButtonListener());
            coinBtnPanel.add(coinBtn);
        }

        // coin label
        coinInput = new JLabel("" + coinTotal);
        coinInput.setOpaque(true);
        coinInput.setBackground(DesignPalette.FOREGROUND);
        coinInput.setHorizontalAlignment(JLabel.RIGHT);

        JButton coinDelBtn = new JButton("Delete");
        coinDelBtn.setBorder(new EmptyBorder(4, 4, 4, 4));
        coinDelBtn.addActionListener(new ActionListener() {
            /**
             * Reset coin total and clear the coinInput text field
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                coinTotal = 0;
                coinInput.setText("" + coinTotal);
            }
        });

        // add elements to coin panel
        coinPanel.add(coinLabel, gbc);
        coinPanel.add(coinBtnPanel, gbc);
        coinPanel.add(coinInput, gbc);
        coinPanel.add(coinDelBtn, gbc);

        // purchase button
        JButton purchaseBtn = new JButton("Purchase");

        setLayout(new GridBagLayout());

        //add elements to panel seperator (for gaps)
        panelSeperator.add(itemPanel, gbc2);
        panelSeperator.add(coinPanel, gbc2);
        panelSeperator.add(purchaseBtn);

        // add components
        add(panelSeperator);
        setVisible(true);
    }

    public ActionListener coinButtonListener () {
        return new ActionListener() {
            /**
             * Increase the coin price basaed on the button clicked and update the text field
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                double coinPrice = Double.parseDouble(e.getActionCommand().substring(1));
                coinTotal += coinPrice;
                coinInput.setText("$" + coinTotal);
            }
        };
    }
    public ActionListener buttonListener () {
        return new ActionListener() {
            // updates the selected vending machine product code Ex. (12) and sets the text field
            @Override
            public void actionPerformed(ActionEvent e) {
                input += e.getActionCommand();
                itemLabel.setText(input);
            }
        };


    }
}