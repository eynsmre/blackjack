<h1>This game created by Yunus Emre Erkeles and Enes Gokden</h1>


<h2>Project Report: BlackJack Game</h2>

<h3>1. Introduction</h3>
The BlackJack project is a Java program that implements a simple text-based version of the popular card game Blackjack. The program allows a player to play against a computer dealer and place bets on each round. The goal of the game is to get a hand value as close to 21 as possible without exceeding it, while also beating the dealer's hand.

<h3>2. Program Structure</h3>
The program consists of two main classes: `BlackJack` and `Player`. The `BlackJack` class contains the main method and handles the game logic, while the `Player` class represents a player in the game.

<h3>3. Game Flow</h3>
The game follows a typical flow for a game of Blackjack. Here are the main steps:

- The game starts by initializing the necessary variables and components, such as the players (computer and human), the game options, and the UI settings.
- The main game loop begins, allowing the player to place bets and play multiple rounds until they run out of money or choose to quit.
- The player is prompted to enter their bet amount, which is validated against their available money. Invalid inputs are handled with appropriate error messages.
- The game proceeds with dealing the initial cards to the player and the computer. The player starts with two cards, and the computer starts with one visible card and one hidden card.
- If the player's initial score is 21 (a blackjack), he earns 1.5 times his money back.
- The player's turn begins, where they are presented with options to hit (draw another card), stand (end their turn), or double (double their bet and draw one more card). Input validation is performed to ensure the player has enough money to double their bet.
- After the player finishes their turn, the computer's turn begins. The computer continues drawing cards until its score reaches at least 17.
- The final scores of both the player and the computer are compared to determine the game outcome (win, lose, or tie). The player's money is updated accordingly.
- The game status, including the scores, bet amount, and remaining money, is displayed to the player.
- The player's and computer's panels are cleared for the next round.
- If the player's money is depleted, the game ends. Otherwise, the next round starts.
- When the game ends, a game over message is displayed, showing the final status and an image.

<h3>4. Player Class</h3>
The `Player` class represents a player in the game. It has attributes such as `money` (amount of money available), `bet` (current bet amount), `score` (current hand score), `message` (player-specific message), `panel` (UI panel for displaying cards), and `playing` (flag indicating if the player is currently playing). It also has methods for adding a card to the player's hand and handling Ace cards.

<h3>5. User Interface</h3>
The game utilizes Java's `JOptionPane` for creating dialog boxes to interact with the player. The UI settings are customized by modifying the UIManager properties to set background colors and image icons.

<h3>6. Conclusion</h3>
The BlackJack project provides a functional implementation of a simple Blackjack game in Java. Players can enjoy playing against a computer dealer, placing bets, and experiencing the thrill of trying to get as close to 21 as possible. The program demonstrates object-oriented programming concepts, user input validation, and graphical user interface components. Further enhancements and refinements can be made to improve the game's user experience and add additional features.
