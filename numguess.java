import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class numguess {

    // --- Configuration ---
    // You can easily change the game's settings here
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 100;
    private static final int MAX_ATTEMPTS = 10;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int roundsWon = 0;
        int roundsPlayed = 0;
        boolean playAgain = true;

        System.out.println("=====================================");
        System.out.println("   Welcome to the Guessing Game!   ");
        System.out.println("=====================================");

        // 6. Add the option for multiple rounds
        while (playAgain) {
            roundsPlayed++;
            
            // Start a new round and check if the player won
            boolean userWon = playRound(scanner);
            if (userWon) {
                roundsWon++;
            }

            // 7. Display the user's score
            System.out.println("\n--- Current Score ---");
            System.out.println("Rounds Played: " + roundsPlayed);
            System.out.println("Rounds Won:    " + roundsWon);

            // Ask the user if they want to play another round
            System.out.print("\nPlay again? (yes/no): ");
            String userResponse = scanner.next();
            playAgain = userResponse.equalsIgnoreCase("yes");
        }

        System.out.println("\nThanks for playing! Goodbye.");
        scanner.close(); // Close the scanner to prevent resource leaks
    }

    /**
     * Manages a single round of the number guessing game.
     * @param scanner The Scanner object to read user input.
     * @return true if the user wins the round, false otherwise.
     */
    private static boolean playRound(Scanner scanner) {
        // 1. Generate a random number within the specified range
        int secretNumber = ThreadLocalRandom.current().nextInt(MIN_NUMBER, MAX_NUMBER + 1);
        int attempts = 0;

        System.out.println("\nI have generated a new number.");
        System.out.printf("Guess the number between %d and %d. You have %d attempts.\n", MIN_NUMBER, MAX_NUMBER, MAX_ATTEMPTS);
        
        // 4. Repeat until the user guesses correctly or runs out of attempts
        while (attempts < MAX_ATTEMPTS) {
            System.out.println("---------------------------------");
            int remainingAttempts = MAX_ATTEMPTS - attempts;
            System.out.printf("Attempt #%d of %d. (%d left)\n", (attempts + 1), MAX_ATTEMPTS, remainingAttempts);

            // 2. Prompt the user to enter their guess
            System.out.print("Enter your guess: ");
            int guess;
            try {
                guess = scanner.nextInt();
            } catch (java.util.InputMismatchException e) {
                // Handle cases where the input is not a number
                System.out.println("Invalid input! Please enter a whole number.");
                scanner.next(); // Clear the invalid input from the scanner
                continue; // Skips the rest of the loop and asks for input again
            }

            attempts++;

            // 3. Compare the user's guess and provide feedback
            if (guess < secretNumber) {
                System.out.println("Too low! Try a higher number.");
            } else if (guess > secretNumber) {
                System.out.println("Too high! Try a lower number.");
            } else {
                // The guess is correct
                System.out.printf("\n🎉 Congratulations! You guessed the number %d correctly!\n", secretNumber);
                System.out.printf("You took %d attempt(s).\n", attempts);
                return true; // User won
            }
        }

        // 5. This part is reached if the while loop finishes (user ran out of attempts)
        System.out.println("\n💥 Game Over! You've run out of attempts.");
        System.out.printf("The secret number was %d.\n", secretNumber);
        return false; // User lost
    }
}