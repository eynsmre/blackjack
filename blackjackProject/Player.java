import javax.swing.*;

public class Player {
    private double money;
    private double bet;
    private int score;
    private String message;
    private JPanel panel;
    private boolean playing;
    private boolean hasAce;

    public Player() {
        money = 200;
        score = 0;
        panel = new JPanel();
        playing = true;
        hasAce = false;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public double getBet() {
        return bet;
    }

    public void setBet(double bet) {
        this.bet = bet;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
    public String getMessage(){ return message; }
    public void setMessage(String message){this.message = message; }
    public JPanel getPanel() {
        return panel;
    }

    public void setPanel(JPanel panel) {
        this.panel = panel;
    }

    public boolean isPlaying() {
        return playing;
    }

    public void setPlaying(boolean playing) {
        this.playing = playing;
    }

    public boolean hasAce() {
        return hasAce;
    }

    public void setHasAce(boolean hasAce) {
        this.hasAce = hasAce;
    }

    public void addCard(int cardNo) {
        int cardValue;
        switch (cardNo % 13) {
            case 0:
                cardValue = 10;
                break; // K geldi cardValue = 10
            case 1:
                cardValue = 11;
                hasAce = true;
                break; // AS geldi cardValue = 11
            case 11:
            case 12:
                cardValue = 10;
                break; // J veya Q geldi cardValue = 10
            default:
                cardValue = cardNo % 13;
                break;
        }
        score += cardValue;
        panel.add(new JLabel(new ImageIcon(BlackJack.class.getResource("resimler/k" + cardNo + ".png"))));
    }
}
