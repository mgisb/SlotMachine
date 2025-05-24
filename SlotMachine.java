import java.awt.event.*;
import javax.swing.JOptionPane;
import javax.swing.*;
import java.util.Random;
        
public class SlotMachine extends JFrame implements ActionListener {
    Random random = new Random();
    int money = 100;
    int betMoney = 0;
    
    JLabel moneyLabel;
    JLabel betLabel;
    
    String betInput = "";
        
    JTextField firstSlot;
    JTextField secondSlot;
    JTextField thirdSlot;
    
    char[] slotSymbols = {'♠','♥','♦','♣','7','X'};
    char[] chosenSlots = new char[3];
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String x = e.getActionCommand();
            if(x.equals("SPIN")) {
                spinButton();
            } else if(x.equals("BET")){
                betButton();
            } else if(x.equals("REWARDS")) {
                winningLines();
            }
    } 
    
    public void getSlots() {      // Spin Button
        // Loop method
        // Loops through the chosenSlots array
        for(int i = 0; i<=0; i++) {
            chosenSlots[i] = slotSymbols[random.nextInt(5)];
            updateSlot1Label();
            for(int j = 1; j<= 1; j++) {
                chosenSlots[j] = slotSymbols[random.nextInt(5)];
                updateSlot2Label();
                for(int k = 2; k<= 2; k++) {
                    chosenSlots[k] = slotSymbols[random.nextInt(5)];
                    updateSlot3Label();
                }
            }
        }
    }
    
    public void spinButton() {      // Simulates the slot machine
        getSlots();
        if(chosenSlots[0] == ('7') && chosenSlots[1] == ('7') && chosenSlots[2] == ('7')){
            this.money = getMoney()+(500*getBetMoney());
            updateMoneyLabel();
        } else if(chosenSlots[0] == ('♥') && chosenSlots[1] == ('♥') && chosenSlots[1] == ('2')){
            this.money = getMoney()+(30*getBetMoney());
            updateMoneyLabel();
        } else if(chosenSlots[0] == ('♦') && chosenSlots[1] == ('♦') && chosenSlots[2] == ('♦')) {
            this.money = getMoney()+(200*getBetMoney());
            updateMoneyLabel();
        } else if(chosenSlots[0] == ('♣') && chosenSlots[1] == ('♣') && chosenSlots[2] == ('♣')) {
            this.money = getMoney()+(50*getBetMoney());
            updateMoneyLabel();
        } else if(chosenSlots[0] == ('♠') && chosenSlots[1] == ('♠') || chosenSlots[0] == ('♠') && chosenSlots[2] == ('♠') || chosenSlots[1] == ('♠') && chosenSlots[2] == ('♠')) {
            this.money = getMoney()+(5*getBetMoney());
            updateMoneyLabel();
        } else {
            this.money = getMoney()-getBetMoney();
            updateMoneyLabel();
            checkMoneyAmt();
        }
    }
    
    public void betButton() {       // Bet Button
        String[] selections = {"Bet Half", "Bet All", "Bet $___"};
        var betWindow = JOptionPane.showOptionDialog(null, "Select an option below", "Bet", 0, 3, null, selections, selections[0]);
        
        if(betWindow == 0) {    // "Bet Half" option
            this.betMoney = this.getMoney()/2;
            updateBetLabel();
        } else if(betWindow == 1 ) {    // "Bet All" option
            this.betMoney = this.getMoney();
            updateBetLabel();
        } else if(betWindow == 2) {     // "Bet $___" option
            getValidBetInput();
        }
    }
    
    public void winningLines() {    // Displays the slot machine's rewards
        JOptionPane.showMessageDialog(null, "♠♠ = x5\n" + "♥♥♥ = x30\n" + "♣♣♣ = x50\n" + "♦♦♦ = x200\n" +"7 7 7 = x500", "Winning Lines", JOptionPane.PLAIN_MESSAGE);
    }
    
    public int getMoney() {         // Retrieves user's money
        return this.money;
    }
    
    public JLabel createMoneyLabel() {      // Creates the money label for the GUI
        return moneyLabel = new JLabel("MONEY: $" + getMoney(), SwingConstants.LEFT);
    }
    
    public void checkMoneyAmt() {   // Checks to see if user's money goes below 0
        if(getMoney() < 1) {
        JOptionPane.showMessageDialog(null, "You lose.\nYou ran out of money.", "Game Over", JOptionPane.ERROR_MESSAGE);
        System.exit(0);
        }
    }
    
    public JLabel createBetLabel() {        // Creates the bet label for the GUI
        return betLabel = new JLabel("BET: $" + getBetMoney(), SwingConstants.RIGHT);
    }
    
    public JTextField createFirstSlot() {       // Creates the first slot machine slot
      return firstSlot = new JTextField("X", JLabel.LEFT);
    }
    
    public JTextField createSecondSlot() {      // Creates the second slot machine slot
      return secondSlot = new JTextField("X", JLabel.CENTER);
    }
    
    public JTextField createThirdSlot() {   // Creates the third slot machine slot on the GUI
      return thirdSlot = new JTextField("X", JLabel.RIGHT );
    }
    
    public void setSlotsEditable() {        // Adjusts the slot machine's text fields to non-editable 
        firstSlot.setEditable(false);
        secondSlot.setEditable(false);
        thirdSlot.setEditable(false);
    }
    
    public void updateSlot1Label() {        // Updates the first slot label
        firstSlot.setText(Character.toString(chosenSlots[0]));
    }
    
    public void updateSlot2Label() {        // Updates the second slot label
        secondSlot.setText(Character.toString(chosenSlots[1]));
    }
    
    public void updateSlot3Label() {        // Updates the third slot label
        thirdSlot.setText(Character.toString(chosenSlots[2]));
    }
    
    public void updateMoneyLabel() {        // Updates the money label on the GUI
        moneyLabel.setText("MONEY: $" + Integer.toString(money));
    }
    
    public int getBetMoney() {              // Retrieves user's bet amount
        return this.betMoney;
    }
    
    public void updateBetLabel() {      // Updates the bet label on the GUI
        betLabel.setText("BET: $" + getBetMoney());
    }
    
    public void getValidBetInput() {        // Checks the validity of the user's bet input
        
        while(true)
        {
            // Exception handling
            try
            {
             betInput = JOptionPane.showInputDialog(null, "Enter how much you would like to bet", this.getMoney()); // Prompts user to input a bet amount
             int userBetNum = Integer.parseInt(betInput);
             
             
             if(userBetNum >= 0 && userBetNum <= getMoney()) {      // Checks if user's bet input is larger than 0 and less than their current money amount
                this.betMoney = userBetNum;
                updateBetLabel();
                break;
             } else if(userBetNum <= 0) {   // Checks if user's bet input is less than 0
                 JOptionPane.showMessageDialog(null, "Please enter a positive number.", "Error", JOptionPane.ERROR_MESSAGE);
                 // Recursive Function
                 getValidBetInput();
             } else if(userBetNum >= getMoney()){   // Checks if user's bet input is greater than their current money amount
                 JOptionPane.showMessageDialog(null, "You don't have enough money for this bet.", "Error", JOptionPane.ERROR_MESSAGE);
                 // Recursive Function
                 getValidBetInput();
             } 
            }
            catch(NumberFormatException e) {
                if(betInput == null) {      // Checks to see if user chose the 'Cancel' option
                   break;
             } else {
                JOptionPane.showMessageDialog(null, betInput + " is not a valid bet amount.","Error",JOptionPane.ERROR_MESSAGE);
                getValidBetInput();
                }
            }
          break;
        }
    }
    
}
