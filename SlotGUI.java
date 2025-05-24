
import javax.swing.*;
import java.awt.*;


public class SlotGUI extends JFrame {
     SlotMachine slotMethods = new SlotMachine();
    
     JButton Spin;
     JButton Bet;
     JButton Rewards;

    
    public void prepWindow() {
       
      JFrame machineGUI = new JFrame("Slot Machine");
      machineGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      JPanel slotsRow = new JPanel( new GridLayout( 1, 3 ) );
      JPanel buttonsRow = new JPanel( new GridLayout( 1, 3 ) );
      JPanel moneyRow = new JPanel(new GridLayout( 1, 3) );
      
      machineGUI.add( slotsRow, BorderLayout.CENTER );
      machineGUI.add( buttonsRow, BorderLayout.SOUTH );
      machineGUI.add( moneyRow, BorderLayout.NORTH );
      
      Spin = new JButton("SPIN");
      Bet = new JButton("BET");
      Rewards = new JButton("REWARDS");
      
      
      moneyRow.add(slotMethods.createMoneyLabel());
      moneyRow.add(slotMethods.createBetLabel());
      slotsRow.add(slotMethods.createFirstSlot());
      slotsRow.add(slotMethods.createSecondSlot());
      slotsRow.add(slotMethods.createThirdSlot());
      buttonsRow.add(Spin);
      buttonsRow.add(Bet);
      buttonsRow.add(Rewards);
      
      slotMethods.setSlotsEditable();
      
      Spin.addActionListener(slotMethods);
      Bet.addActionListener(slotMethods);
      Rewards.addActionListener(slotMethods);
      
      machineGUI.setPreferredSize(new Dimension(350, 100));
      machineGUI.pack();
      machineGUI.setLocationRelativeTo(null);
      machineGUI.setVisible(true);
    }
    
    public static void main(String[] args) {
        SlotGUI runGUI = new SlotGUI();
        runGUI.prepWindow();
    }
}
