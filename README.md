Game Rules:

Blackjack is a card game where the goal is to have a hand value as close to 21 as possible without exceeding it. The game is played between a player and a computer dealer.

The player starts with 200 PLN and can place bets.
The player can choose to "Push" (get another card), "Wait" (end their turn), or "Double it" (double their bet and get one more card).
The computer dealer must have a hand value of at least 17.
The player and computer are initially dealt two cards each.
The face cards (King, Queen, Jack) are worth 10 points, and the Ace can be worth 1 or 11 points.
If the player or computer has an Ace and their hand value exceeds 21, the Ace is counted as 1 point.
If the player's hand value exceeds 21, they lose the bet. If the computer's hand value exceeds 21, the player wins the bet.
If the player and computer have the same hand value, it is a tie.
If the player's hand value is higher than the computer's hand value, the player wins the bet. Otherwise, the player loses the bet.
The game continues until the player runs out of money or chooses to exit the game.



<h2>Explanation of the Code:<h2>

The code is written in Java and implements a simplified version of the Blackjack game. Let's go through the important parts of the code:

Import Statements:

The code imports necessary classes and packages for GUI components and color management.
Class Definition:

The code defines a class named "BlackJack."
main() Method:

The main() method is the entry point of the program.
It initializes variables and objects required for the game, such as gameInProgress, gameOptions, computerScore, playerMoney, etc.
It contains a do-while loop that represents the main game loop. The loop continues as long as the game is in progress.
Within the loop, the code prompts the player to place a bet and handles input validation for the bet amount.
It simulates the dealing of cards to the player and the computer dealer, calculates their scores, and updates the GUI accordingly.
It provides options for the player to choose their next move (push, wait, or double their bet) using JOptionPane dialogs.
It simulates the computer dealer's turn by drawing cards until its score reaches at least 17.
It evaluates the final scores, determines the outcome (win, lose, or tie), updates the player's money, and displays the result using a GUI dialog.
It clears the card panels and checks if the player has enough money to continue playing. If not, it ends the game.
The main game loop continues until the player decides to exit the game or runs out of money.
Finally, it displays a final game message using a GUI dialog.
Card Drawing and GUI:

The code uses ImageIcon and JLabel to represent and display card images.
It utilizes JOptionPane dialogs to interact with the player, display game information, and show the final results.
It uses UIManager to customize the look and feel of the dialogs by setting background colors and message foreground color.
Overall, the code provides a simple command-line version of the Blackjack game, allowing the player to place bets, make decisions, and play against a computer dealer. It utilizes GUI dialogs to enhance the user experience and visual representation of the game.
