import javax.swing.*;
import java.awt.*;

public class BlackJack {

  public static void main(String[] args) {

    boolean gameInProgress = true;
    Object[] gameOptions = { "Hit", "Stand", "Double" };
    UIManager.put("Panel.background", Color.decode("#0f1f07"));
    UIManager.put("OptionPane.background", Color.decode("#0f1f07"));
    UIManager.put("OptionPane.messageForeground", Color.WHITE);

    int computerScore;
    String computerMessage;
    JPanel computerPanel = new JPanel();
    boolean computerPlaying;
    boolean computerAce = false;

    int playerMoney = 200;
    int playerScore;
    int bet = 0;
    String playerMessage;
    JPanel playerPanel = new JPanel();
    boolean playerPlaying;
    boolean playerAce = false;

    int cardNo, cardValue;
    String statusMessage = "";
    Object[] gamePanel = new Object[5];

    do {
      System.out.println("*************  NEW GAME  *************");
      boolean betError = false;
      do {
        try {
          Object betAmount = JOptionPane.showInputDialog(null,
              "<html><h1>BlackJack Game</h1></html>\n"
                  + "Whoever gets 21 wins\n"
                  + "The one with the cards closest to a total of 21 wins.\n"
                  + "The computer's cards must have a total of at least 17.\n"
                  + "<html><h2>You currently have " + playerMoney + " PLN.</h2></html>"
                  + "<html><h1>Please specify your bet!!!</html></h1>",
              "Bet Value",
              JOptionPane.PLAIN_MESSAGE,
              new ImageIcon(BlackJack.class.getResource("resimler/blackjack.png")),
              null, "");
          bet = Integer.parseInt(betAmount.toString());
          if (bet > playerMoney) {
            JOptionPane.showMessageDialog(null,
                "<html><h2>You don't have enough money! </h2></html>",
                "WRONG VALUE",
                JOptionPane.ERROR_MESSAGE);
            betError = true;
          } else {
            betError = false;
          }
        } catch (NumberFormatException error) {
          JOptionPane.showMessageDialog(null,
              "<html><h2>You must enter a number!</h2></html>",
              "Wrong value!",
              JOptionPane.ERROR_MESSAGE, null);
          betError = true;
        } catch (NullPointerException error) {
          JOptionPane.showMessageDialog(null,
              "<html><h1>You have exited the game!</h1></html>", "EXIT",
              JOptionPane.WARNING_MESSAGE, null);
          System.exit(0);
        }
      } while (betError);
      System.out.println("Bet = " + bet);
      statusMessage = "Bet = " + bet + "\nYour money = " + playerMoney;

      computerScore = 0;
      computerPlaying = true;
      // Bilgisayar 1. kart çekiliyor
      cardNo = (int) (Math.random() * 52) + 1;
      System.out.print("Computer\tcard value = " + cardNo + "\t");
      switch (cardNo % 13) {
        case 0:
          cardValue = 10;
          break; // K geldi cardValue = 10
        case 1:
          cardValue = 11;
          computerAce = true;
          break; // AS geldi cardValue = 11
        case 11:
        case 12:
          cardValue = 10;
          break; // J veya Q geldi cardValue = 10
        default:
          cardValue = cardNo % 13;
          break;
      }
      System.out.print("Card Point  = " + cardValue + "\t");
      computerScore += cardValue;
      computerPanel.add(new JLabel(new ImageIcon(BlackJack.class.getResource("resimler/k" + cardNo + ".png"))));
      System.out.println("Total point  = " + computerScore);
      // Bilgisayar 2. kağıt kapalı gösterilir.
      JLabel closedCard = new JLabel(new ImageIcon(BlackJack.class.getResource("resimler/k0.png")));
      computerPanel.add(closedCard);
      computerMessage = "Computer = " + computerScore + " score";

      playerScore = 0;
      playerPlaying = true;
      // Oyuncu 1. kart
      cardNo = (int) (Math.random() * 52) + 1;
      System.out.print("Player\tcard number = " + cardNo + "\t");
      switch (cardNo % 13) {
        case 0:
          cardValue = 10;
          break; // K geldi cardValue = 10
        case 1:
          cardValue = 11;
          computerAce = true;
          break; // AS geldi cardValue = 11
        case 11:
        case 12:
          cardValue = 10;
          break; // J veya Q geldi cardValue = 10
        default:
          cardValue = cardNo % 13;
          break;
      }
      System.out.print("Card value = " + cardValue + "\t");
      playerScore += cardValue;
      playerPanel.add(new JLabel(new ImageIcon(BlackJack.class.getResource("resimler/k" + cardNo + ".png"))));
      System.out.println("Total value  = " + playerScore);

      // Oyuncu 2. kart
      cardNo = (int) (Math.random() * 52) + 1;
      System.out.print("Player\tcard value= " + cardNo + "\t");
      switch (cardNo % 13) {
        case 0:
          cardValue = 10;
          break; // K geldi cardValue = 10
        case 1:
          cardValue = 11;
          computerAce = true;
          break; // AS geldi cardValue = 11
        case 11:
        case 12:
          cardValue = 10;
          break; // J veya Q geldi cardValue = 10
        default:
          cardValue = cardNo % 13;
          break;
      }
      System.out.print("Card value = " + cardValue + "\t");
      playerScore += cardValue;
      playerPanel.add(new JLabel(new ImageIcon(BlackJack.class.getResource("resimler/k" + cardNo + ".png"))));
      System.out.println("Total score  = " + playerScore);
      playerMessage = "Player = " + playerScore + " score";

      if (playerScore == 21)
        bet *= 1.5;

      if (playerScore >= 21) { // oyuncu bitirdi kart çekmeye gerek yok
        playerPlaying = false;
        computerPlaying = false;
      }
      while (playerPlaying) {
        gamePanel[0] = computerMessage;
        gamePanel[1] = computerPanel;
        gamePanel[2] = statusMessage;
        gamePanel[3] = playerPanel;
        gamePanel[4] = playerMessage;
        int cardChoice = JOptionPane.showOptionDialog(null, gamePanel, "BlackJack Game",
            JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
            new ImageIcon(BlackJack.class.getResource("resimler/blackjack.png")),
            gameOptions, gameOptions[0]);
        if (cardChoice == 1) { // BEKLE seçildi cardNo almaz
          playerPlaying = false;
        } else {
          if (cardChoice == 2) {
            if (playerMoney < bet * 2) {
              JOptionPane.showMessageDialog(null,
                  "<html><h2>You don't have enough money to double your bet!</h2></html>",
                  "Insufficient Funds",
                  JOptionPane.ERROR_MESSAGE);
              continue;
            }
            bet *= 2;
            playerPlaying = false;
          }
          // Oyuncu Yeni kart
          cardNo = (int) (Math.random() * 52) + 1;
          switch (cardNo % 13) {
            case 0:
              cardValue = 10;
              break; // K geldi cardValue = 10
            case 1:
              if (playerScore <= 10) {
                cardValue = 11;
                computerAce = true;
              } else {
                cardValue = 1;
              }
              break; // AS geldi cardValue = 11
            case 11:
            case 12:
              cardValue = 10;
              break; // J veya Q geldi cardValue = 10
            default:
              cardValue = cardNo % 13;
              break;
          }
          System.out.print("Player\tcard value = " + cardNo + "\tcard point = " + cardValue + "\t");
          playerScore += cardValue;
          playerPanel.add(new JLabel(new ImageIcon(BlackJack.class.getResource("resimler/k" + cardNo + ".png"))));
          if (playerAce && playerScore > 21) { // oyuncuda AS var ama puanı 21'den büyük. AS=1 sayılır
            playerScore -= 10;
            playerAce = false;
          }
          System.out.println("Total score  = " + playerScore);
          playerMessage = "Player = " + playerScore + " score";
        } // oyuncu AS kontrolü bitti
        if (playerScore >= 21) {
          playerPlaying = false;
          computerPlaying = false;
        }
      } // oyuncu aşaması bitti

      // bilgisayar oynamaya başladı. Kapalı kağıtı kaldıralım
      computerPanel.remove(closedCard);
      while (computerPlaying) {
        if (computerScore < 17) {
          // bilgisayar yeni kart
          cardNo = (int) (Math.random() * 52) + 1;
          switch (cardNo % 13) {
            case 0:
              cardValue = 10;
              break; // K geldi cardValue = 10
            case 1:
              if (playerScore <= 10) {
                cardValue = 11;
                computerAce = true;
              } else {
                cardValue = 1;
              }
              break; // AS geldi cardValue = 11
            case 11:
            case 12:
              cardValue = 10;
              break; // J veya Q geldi
            default:
              cardValue = cardNo % 13;
              break; //
          }
          System.out.print("Computer\tcard value = " + cardNo + "\tCard value = " + cardValue + "\t");
          computerScore += cardValue;
          computerPanel.add(new JLabel(new ImageIcon(BlackJack.class.getResource("resimler/k" + cardNo + ".png"))));
          if (computerAce && computerScore > 21) { // bilgisayarda AS var ama puanı 21'den büyük. AS=1 sayılır
            computerScore -= 10;
            computerAce = false;
          }
          System.out.println("Total Score = " + computerScore);
          computerMessage = "Computer = " + computerScore + " score";
        } else {
          computerPlaying = false;
        }
      } // bilgisayar bitirdi
      // Durum Kontrol
      String status;
      if (playerScore == 21 || computerScore > 21) {
        playerMoney += bet;
        status = "YOU WIN";
      } else if (playerScore > 21 || computerScore == 21) {
        playerMoney -= bet;
        status = "YOU LOSE";
      } else if (playerScore == computerScore) {
        status = "IT'S A TIE";
      } else {
        if (playerScore < computerScore) {
          playerMoney -= bet;
          status = "YOU LOSE";
        } else {
          playerMoney += bet;
          status = "YOU WIN";
        }
      }
      statusMessage = "Bet = " + bet + "\n<html><h1>!!! " + status + " !!!</h1></html>\nYour money = " + playerMoney;
      gamePanel[0] = computerMessage;
      gamePanel[1] = computerPanel;
      gamePanel[2] = statusMessage;
      gamePanel[3] = playerPanel;
      gamePanel[4] = playerMessage;
      JOptionPane.showMessageDialog(null, gamePanel, "SKOR", JOptionPane.ERROR_MESSAGE,
          new ImageIcon(BlackJack.class.getResource("resimler/blackjack.png")));
      computerPanel.removeAll();
      playerPanel.removeAll();
      if (playerMoney <= 0) { // en küçük bahisten daha az para varsa
        statusMessage = "<html><h2>You don't have enough money</h2></html>"
            + "\n<html><h1>Your money =" + playerMoney + " PLN</h1></html>";
        gameInProgress = false;
      }
    } while (gameInProgress);

    int desiredWidth = 412;
    int desiredHeight = 208;

    UIManager.put("Panel.background", Color.decode("#231d1d"));
    UIManager.put("OptionPane.background", Color.decode("#231d1d"));
    UIManager.put("OptionPane.messageForeground", Color.WHITE);

    ImageIcon originalIcon = new ImageIcon(BlackJack.class.getResource("resimler/bitisLogo.png"));
    JOptionPane.showMessageDialog(null,
        statusMessage, "Game over !",
        JOptionPane.ERROR_MESSAGE,
        new ImageIcon(
            originalIcon.getImage().getScaledInstance(desiredWidth, desiredHeight, java.awt.Image.SCALE_SMOOTH)));

  }
}
