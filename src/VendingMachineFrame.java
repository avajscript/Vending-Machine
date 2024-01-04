import javax.swing.*;
import java.awt.*;

class VendingMachineFrame extends JFrame {
    private static final int SCREEN_WIDTH = 500;
    private static final int SCREEN_HEIGHT = 600;

    private MachinePanel machinePanel;
    private VendingInput vendingInput;
    private VendingMachine vendingMachine;
    public VendingMachineFrame(VendingMachine vendingMachine) {
        setTitle("Vending Machine");

        JPanel vendingMachineHolder = new JPanel();
        vendingMachineHolder.setLayout(new FlowLayout());
        vendingMachineHolder.setBackground(DesignPalette.BACKGROUND);

        machinePanel = new MachinePanel(vendingMachine.getX_DIMENSION(), vendingMachine.getY_DIMENSION(), vendingMachine.getProducts());
        vendingInput = new VendingInput();
        Bag bag = new Bag();

        vendingMachineHolder.add(machinePanel);
        vendingMachineHolder.add(vendingInput);
        add(vendingMachineHolder);
        add(bag);
        setBackground(Color.WHITE);
        setSize(SCREEN_WIDTH, SCREEN_HEIGHT);

        setLayout(new FlowLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }


}