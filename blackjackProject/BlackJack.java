import javax.swing.*;
import java.awt.*;

public class BlackJack extends Player{

  public BlackJack() {
    super(); // Call the superclass constructor
  }
  
  public static void main(String[] args) {

    boolean gameInProgress = true;
    Object[] gameOptions = { "Hit", "Stand", "Double" };
    UIManager.put("Panel.background", Color.decode("#0f1f07"));
    UIManager.put("OptionPane.background", Color.decode("#0f1f07"));
    UIManager.put("OptionPane.messageForeground", Color.WHITE);

    Player computer = new Player();
    Player player = new Player();

    /*
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

     */

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
                  + "<html><h2>You currently have " + player.getMoney() + " PLN.</h2></html>"
                  + "<html><h1>Please specify your bet!!!</html></h1>",
              "Bet Value",
              JOptionPane.PLAIN_MESSAGE,
              new ImageIcon(BlackJack.class.getResource("resimler/blackjack.png")),
              null, "");
          player.setBet(Integer.parseInt(betAmount.toString()));
          if (player.getBet() > player.getMoney()) {
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
      System.out.println("Bet = " + player.getBet());
      statusMessage = "Bet = " + player.getBet() + "\nYour money = " + player.getMoney();

      computer.setScore(0);
      //computerPlaying = true; //Zaten Player class içerisinde true halde
      // Bilgisayar 1. kart çekiliyor
      cardNo = (int) (Math.random() * 52) + 1;
      System.out.print("Computer\tcard value = " + cardNo + "\t");
      switch (cardNo % 13) {
        case 0:
          cardValue = 10;
          break; // K geldi cardValue = 10
        case 1:
          cardValue = 11;
          computer.setHasAce(true);
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
      computer.setScore(computer.getScore() + cardValue);
      computer.getPanel().add(new JLabel(new ImageIcon(BlackJack.class.getResource("resimler/k" + cardNo + ".png"))));
      System.out.println("Total point  = " + computer.getScore());
      // Bilgisayar 2. kağıt kapalı gösterilir.
      JLabel closedCard = new JLabel(new ImageIcon(BlackJack.class.getResource("resimler/k0.png")));
      computer.getPanel().add(closedCard);
      computer.setMessage("Computer = " + computer.getScore() + " score");

      player.setScore(0);
      player.setPlaying(true);

      // Oyuncu 1. kart
      cardNo = (int) (Math.random() * 52) + 1;
      System.out.print("Player\tcard number = " + cardNo + "\t");
      /*
      switch (cardNo % 13) {
        case 0:
          cardValue = 10;
          break; // K geldi cardValue = 10
        case 1:
          cardValue = 11;
          computer.setHasAce(true);
          break; // AS geldi cardValue = 11
        case 11:
        case 12:
          cardValue = 10;
          break; // J veya Q geldi cardValue = 10
        default:
          cardValue = cardNo % 13;
          break;
      }

       */
      player.addCard(cardNo);
      System.out.print("Card value = " + cardValue + "\t");
      //player.setScore(player.getScore() + cardValue);
      //player.getPanel().add(new JLabel(new ImageIcon(BlackJack.class.getResource("resimler/k" + cardNo + ".png"))));
      System.out.println("Total value  = " + player.getScore());

      // Oyuncu 2. kart
      cardNo = (int) (Math.random() * 52) + 1;
      System.out.print("Player\tcard value= " + cardNo + "\t");
      /*
      switch (cardNo % 13) {
        case 0:
          cardValue = 10;
          break; // K geldi cardValue = 10
        case 1:
          cardValue = 11;
          computer.setHasAce(true);
          break; // AS geldi cardValue = 11
        case 11:
        case 12:
          cardValue = 10;
          break; // J veya Q geldi cardValue = 10
        default:
          cardValue = cardNo % 13;
          break;
      }
       */
      System.out.print("Card value = " + cardValue + "\t");
      player.setScore(player.getScore() + cardValue);
      player.getPanel().add(new JLabel(new ImageIcon(BlackJack.class.getResource("resimler/k" + cardNo + ".png"))));
      System.out.println("Total score  = " + player.getScore());
      player.setMessage("Player = " + player.getScore() + " score");

      if (player.getScore() == 21)
        player.setBet((player.getBet() * 1.5));

      if (player.getScore() >= 21) { // oyuncu bitirdi kart çekmeye gerek yok
        player.setPlaying(false);
        computer.setPlaying(false);
      }
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
        if (cardChoice == 1) { // BEKLE seçildi cardNo almaz
          player.setPlaying(false);
        } else {
          if (cardChoice == 2) {
            if (player.getMoney() < player.getBet() * 2) {
              JOptionPane.showMessageDialog(null,
                  "<html><h2>You don't have enough money to double your bet!</h2></html>",
                  "Insufficient Funds",
                  JOptionPane.ERROR_MESSAGE);
              continue;
            }
            player.setBet(player.getBet() * 2);
            player.setPlaying(false);
          }
          // Oyuncu Yeni kart
          cardNo = (int) (Math.random() * 52) + 1;
          switch (cardNo % 13) {
            case 0:
              cardValue = 10;
              break; // K geldi cardValue = 10
            case 1:
              if (player.getScore() <= 10) {
                cardValue = 11;
                computer.setHasAce(true);
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
          player.setScore(player.getScore()+ cardValue);
          player.getPanel().add(new JLabel(new ImageIcon(BlackJack.class.getResource("resimler/k" + cardNo + ".png"))));
          if (player.hasAce() && player.getScore() > 21) { // oyuncuda AS var ama puanı 21'den büyük. AS=1 sayılır
            player.setScore(player.getScore() - 10);
            player.setHasAce(false);
          }
          System.out.println("Total score  = " + player.getScore());
          player.setMessage("Player = " + player.getScore() + " score");
        } // oyuncu AS kontrolü bitti
        if (player.getScore() >= 21) {
          player.setPlaying(false);
          computer.setPlaying(false);
        }
      } // oyuncu aşaması bitti

      // bilgisayar oynamaya başladı. Kapalı kağıtı kaldıralım
      computer.getPanel().remove(closedCard);
      while (betError) {
        if (computer.getScore() < 17) {
          // bilgisayar yeni kart
          cardNo = (int) (Math.random() * 52) + 1;
          switch (cardNo % 13) {
            case 0:
              cardValue = 10;
              break; // K geldi cardValue = 10
            case 1:
              if (computer.getScore() <= 10) {
                cardValue = 11;
                computer.setHasAce(true);
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
          computer.setScore(computer.getScore() + cardValue);
          computer.getPanel().add(new JLabel(new ImageIcon(BlackJack.class.getResource("resimler/k" + cardNo + ".png"))));
          if (computer.hasAce() && computer.getScore() > 21) { // bilgisayarda AS var ama puanı 21'den büyük. AS=1 sayılır
            computer.setScore(computer.getScore() - 10);
            computer.setHasAce(false);
          }
          System.out.println("Total Score = " + computer.getScore());
          computer.setMessage("Computer = " + computer.getScore() + " score");
        } else {
          betError = false;
        }
      } // bilgisayar bitirdi
      // Durum Kontrol
      String status;
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
          player.setMoney(player.getMoney() + player.getBet());
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
      JOptionPane.showMessageDialog(null, gamePanel, "SKOR", JOptionPane.ERROR_MESSAGE,
          new ImageIcon(BlackJack.class.getResource("resimler/blackjack.png")));
      computer.getPanel().removeAll();
      player.getPanel().removeAll();
      if (player.getMoney() <= 0) { // en küçük bahisten daha az para varsa
        statusMessage = "<html><h2>You don't have enough money</h2></html>"
            + "\n<html><h1>Your money =" + player.getMoney() + " PLN</h1></html>";
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
            originalIcon.getImage().getScaledInstance(desiredWidth, desiredHeight, Image.SCALE_SMOOTH)));

  }
}
