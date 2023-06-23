import javax.swing.*;

public class Player {
    private double money;
    private double bet;
    private int score;
    private String message;
    private JPanel panel;
    private boolean playing;
    private boolean hasAce;

    /**
     * Constructs a new Player object.
     * Initializes the player's money to 200.
     * Initializes the player's score to 0.
     * Creates a new JPanel for the player's panel.
     * Sets the playing status to true.
     * Sets the hasAce status to false.
     *
     */
    public Player() {
        money = 200;
        score = 0;
        panel = new JPanel();
        playing = true;
        hasAce = false;
    }

    /**
     * Retrieves the amount of money the player has.
     *
     * @return The amount of money.
     */
    public double getMoney() {
        return money;
    }

    /**
     * Sets the amount of money for the player.
     *
     * @param money The amount of money to set.
     */
    public void setMoney(double money) {
        this.money = money;
    }

    /**
     * Retrieves the current bet amount.
     *
     * @return The current bet amount.
     */
    public double getBet() {
        return bet;
    }

    /**
     * Sets the current bet amount for the player.
     *
     * @param bet The bet amount to set.
     */
    public void setBet(double bet) {
        this.bet = bet;
    }

    /**
     * Retrieves the player's current score.
     *
     * @return The player's score.
     */
    public int getScore() {
        return score;
    }

    /**
     * Sets the player's score.
     *
     * @param score The score to set.
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Retrieves the player's message.
     *
     * @return The player's message.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the player's message.
     *
     * @param message The message to set.
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Retrieves the player's panel.
     *
     * @return The player's panel.
     */
    public JPanel getPanel() {
        return panel;
    }

    /**
     * Sets the player's panel.
     *
     * @param panel The panel to set.
     */
    public void setPanel(JPanel panel) {
        this.panel = panel;
    }

    /**
     * Checks if the player is currently playing.
     *
     * @return true if the player is playing, false otherwise.
     */
    public boolean isPlaying() {
        return playing;
    }

    /**
     * Sets the playing status for the player.
     *
     * @param playing The playing status to set.
     */
    public void setPlaying(boolean playing) {
        this.playing = playing;
    }

    /**
     * Checks if the player has an Ace card.
     *
     * @return true if the player has an Ace card, false otherwise.
     */
    public boolean hasAce() {
        return hasAce;
    }

    /**
     * Sets whether the player has an Ace card.
     *
     * @param hasAce The status to set.
     */
    public void setHasAce(boolean hasAce) {
        this.hasAce = hasAce;
    }

    /**
     * Adds a card to the player's hand.
     * Generates a random card number and assigns a value based on its rank.
     * Updates the score accordingly.
     * Adjusts the score if the player has an Ace card and the score exceeds 21.
     */
    public void addCard() {
        int cardNo;
        cardNo = (int) (Math.random() * 52) + 1;
        int cardValue;
        switch (cardNo % 13) {
            case 0:
                cardValue = 10;
                break; // K - cardValue = 10
            case 1:
                cardValue = 11;
                setHasAce(true);
                break; // ACE - cardValue = 11
            case 11:
            case 12:
                cardValue = 10;
                break; // J or Q - cardValue = 10
            default:
                cardValue = cardNo % 13;
                break;
        }
        score += cardValue;
        if (hasAce() && getScore() > 21) {
            // The player has an Ace, but the score is greater than 21.
            // Treat the Ace as 1 instead of 11.
            setScore(getScore() - 10);
            setHasAce(false);
        }
        panel.add(new JLabel(new ImageIcon(BlackJack.class.getResource("resimler/k" + cardNo + ".png"))));
        System.out.print("Card value = " + cardValue + "\t");
        System.out.println("Total value = " + getScore());
    }
}
