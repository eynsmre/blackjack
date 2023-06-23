import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

// The BlackJack class extends the Player class
public class BlackJack {


  // The main method of the program
  public static void main(String[] args) {
    boolean gameInProgress = true; // Flag to keep track of the game progress
    Object[] gameOptions = {"Hit", "Stand", "Double"}; // Options for the game
    // Setting the background and color properties for UI components
    UIManager.put("Panel.background", Color.decode("#0f1f07"));
    UIManager.put("OptionPane.background", Color.decode("#0f1f07"));
    UIManager.put("OptionPane.messageForeground", Color.WHITE);

    Player computer = new Player(); // Create a computer player
    Player player = new Player(); // Create a human player

    int cardNo, cardValue; // Variables to store card information
    String statusMessage = ""; // Message to display game status
    Object[] gamePanel = new Object[5]; // Array to hold game components

      // Main game loop
      do {
        player.setPlaying(true);
        computer.setPlaying(true);
        player.setScore(0);
        computer.setScore(0);

        boolean betError = false; // Flag to handle bet validation
        // Loop to handle player's bet input
        do {
          try {
            Object betAmount = JOptionPane.showInputDialog(null,
                    "<html><h1>BlackJack Game</h1></html>\n"
                            + "Whoever gets 21 wins\n"
                            + "The one with the cards closest to a total of 21 wins.\n"
                            + "The computer's cards must have a total of at least 17.\n"
                            + "<html><h2>You currently have " + player.getMoney() + " PLN.</h2></html>"
                            + "<html><h1>Please specify your bet!!!</html></h1>",
                    "Bet Value",
                    JOptionPane.PLAIN_MESSAGE,
                    new ImageIcon(BlackJack.class.getResource("resimler/blackjack.png")),
                    null, "");
            player.setBet(Integer.parseInt(betAmount.toString())); // Set player's bet amount
            if (player.getBet() > player.getMoney()) {
              JOptionPane.showMessageDialog(null,
                      "<html><h2>You don't have enough money! </h2></html>",
                      "WRONG VALUE",
                      JOptionPane.ERROR_MESSAGE);
              betError = true; // Set bet error flag
            } else {
              betError = false; // Reset bet error flag
            }
          } catch (NumberFormatException error) {
            JOptionPane.showMessageDialog(null,
                    "<html><h2>You must enter a number!</h2></html>",
                    "Wrong value!",
                    JOptionPane.ERROR_MESSAGE, null);
            betError = true; // Set bet error flag
          } catch (NullPointerException error) {
            JOptionPane.showMessageDialog(null,
                    "<html><h1>You have exited the game!</h1></html>", "EXIT",
                    JOptionPane.WARNING_MESSAGE, null);
            System.exit(0); // Exit the program
          }
        } while (betError);

        statusMessage = "Bet = " + player.getBet() + "\nYour money = " + player.getMoney();

        computer.addCard(); // Add a card to the computer's hand

        JLabel closedCard = new JLabel(new ImageIcon(BlackJack.class.getResource("resimler/k0.png")));
        JPanel closedPanel = computer.getPanel();
        closedPanel.add(closedCard);
        computer.setPanel(closedPanel);
        computer.setMessage("Computer = " + computer.getScore() + " score");

        player.addCard(); // Add two cards to the player's hand
        player.addCard();

        player.setMessage("Player = " + player.getScore() + " score");

        if (player.getScore() == 21)
          player.setBet((int) (player.getBet() * 1.5)); // Increase the bet if player has blackjack

        if (player.getScore() >= 21) {
          player.setPlaying(false);
          computer.setPlaying(false);
        }


        // Loop to handle player's turn
        while (player.isPlaying()) {
          gamePanel[0] = computer.getMessage();
          gamePanel[1] = computer.getPanel();
          gamePanel[2] = statusMessage;
          gamePanel[3] = player.getPanel();
          gamePanel[4] = player.getMessage();
          int cardChoice = JOptionPane.showOptionDialog(null, gamePanel, "BlackJack Game",
                  JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                  new ImageIcon(BlackJack.class.getResource("resimler/blackjack.png")),
                  gameOptions, gameOptions[0]);

          if (cardChoice == 1) {
            player.setPlaying(false); // Player chooses to stand
          } else {
            if (cardChoice == 2) {
              if (player.getMoney() < player.getBet() * 2) {
                JOptionPane.showMessageDialog(null,
                        "<html><h2>You don't have enough money to double your bet!</h2></html>",
                        "Insufficient Funds", JOptionPane.ERROR_MESSAGE);
                continue; // Continue to the next iteration of the loop
              }
              player.setBet(player.getBet() * 2); // Double the player's bet
              player.setPlaying(false);
            }
            player.addCard(); // Player chooses to hit
            player.setMessage("Player = " + player.getScore() + " score");
          }
          if (player.getScore() == 21) {
            player.setPlaying(false);
          }
          if (player.getScore() > 21) {
            player.setPlaying(false);
            computer.setPlaying(false);
          }
        }
        System.out.println("Player's total Score = " + player.getScore());




        JPanel computerPanel = computer.getPanel();
        computerPanel.remove(closedCard);
        computer.setPanel(computerPanel);

        // Loop to handle computer's turn
        while (computer.isPlaying()) {
          if (computer.getScore() < 17) {

            computer.addCard(); // Add the card to the computer's hand

            //System.out.print("Computer\tcard value = " + cardNo + "\tCard value = " + cardNo % 13 + "\t");
            if (computer.hasAce() && computer.getScore() > 21) {
              computer.setScore(computer.getScore() - 10); // Adjust the score if computer has an Ace
              computer.setHasAce(false);
            }
            computer.setMessage("Computer = " + computer.getScore() + " score");
          } else {
            computer.setPlaying(false);
          }
        }
        System.out.println("Computer total Score = " + computer.getScore());

        String status;
        // Determine the game status and update player's money accordingly
        if (player.getScore() == 21 || computer.getScore() > 21) {
          player.setMoney(player.getMoney() + player.getBet());
          status = "YOU WIN";
        } else if (player.getScore() > 21 || computer.getScore() == 21) {
          player.setMoney(player.getMoney() - player.getBet());
          status = "YOU LOSE";
        } else if (player.getScore() == computer.getScore()) {
          status = "IT'S A TIE";
        } else {
          if (player.getScore() < computer.getScore()) {
            player.setMoney(player.getMoney() - player.getBet());
            status = "YOU LOSE";
          } else {
            player.setMoney(player.getMoney() + player.getBet());
            status = "YOU WIN";
          }
        }
        statusMessage = "Bet = " + player.getBet() + "\n<html><h1>!!! " + status + " !!!</h1></html>\nYour money = " + player.getMoney();
        gamePanel[0] = computer.getMessage();
        gamePanel[1] = computer.getPanel();
        gamePanel[2] = statusMessage;
        gamePanel[3] = player.getPanel();
        gamePanel[4] = player.getMessage();
        JOptionPane.showMessageDialog(null, gamePanel, "SCORE", JOptionPane.ERROR_MESSAGE,
                new ImageIcon(BlackJack.class.getResource("resimler/blackjack.png")));
        computer.getPanel().removeAll();
        player.getPanel().removeAll();
        if (player.getMoney() <= 0) {
          statusMessage = "<html><h2>You don't have enough money</h2></html>"
                  + "\n<html><h1>Your money = " + player.getMoney() + " PLN</h1></html>";
          gameInProgress = false; // End the game if the player runs out of money
        }
      } while (gameInProgress);

      int desiredWidth = 412;
      int desiredHeight = 208;

      // Setting the background and color properties for the final game over message
      UIManager.put("Panel.background", Color.decode("#231d1d"));
      UIManager.put("OptionPane.background", Color.decode("#231d1d"));
      UIManager.put("OptionPane.messageForeground", Color.WHITE);

      ImageIcon originalIcon = new ImageIcon(BlackJack.class.getResource("resimler/bitisLogo.png"));
      JOptionPane.showMessageDialog(null,
              statusMessage, "Game Over!",
              JOptionPane.ERROR_MESSAGE,
              new ImageIcon(
                      originalIcon.getImage().getScaledInstance(desiredWidth, desiredHeight, Image.SCALE_SMOOTH)));

  }
}
