import java.util.Scanner;
import java.util.Random;

public class NaziyaSyed_Task2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int totalScore = 0;
        boolean playAgain = true;

        System.out.println("===================================");
        System.out.println("   WELCOME TO NUMBER GUESSING GAME");
        System.out.println("===================================");

        while (playAgain) {
            int numberToGuess = random.nextInt(100) + 1; // 1 to 100
            int maxAttempts = 10;
            int attemptsUsed = 0;
            boolean guessedCorrectly = false;

            System.out.println("\nI'm thinking of a number between 1 and 100.");
            System.out.println("You have " + maxAttempts + " attempts to guess it.");

            while (attemptsUsed < maxAttempts && !guessedCorrectly) {
                System.out.print("\nAttempt " + (attemptsUsed + 1) + "/" + maxAttempts + " - Enter your guess: ");

                if (!scanner.hasNextInt()) {
                    System.out.println("Please enter a valid number.");
                    scanner.next(); // discard invalid input
                    continue;
                }

                int guess = scanner.nextInt();
                attemptsUsed++;

                if (guess < 1 || guess > 100) {
                    System.out.println("Please guess a number within 1 and 100.");
                } else if (guess < numberToGuess) {
                    System.out.println("Too low! Try a higher number.");
                } else if (guess > numberToGuess) {
                    System.out.println("Too high! Try a lower number.");
                } else {
                    guessedCorrectly = true;
                    int roundScore = Math.max((maxAttempts - attemptsUsed + 1) * 10, 10);
                    totalScore += roundScore;
                    System.out.println("\nCorrect! The number was " + numberToGuess + ".");
                    System.out.println("You guessed it in " + attemptsUsed + " attempt(s).");
                    System.out.println("Points earned this round: " + roundScore);
                }
            }

            if (!guessedCorrectly) {
                System.out.println("\nOut of attempts! The number was " + numberToGuess + ".");
            }

            System.out.println("Total Score: " + totalScore);

            System.out.print("\nDo you want to play another round? (yes/no): ");
            String response = scanner.next().trim().toLowerCase();
            playAgain = response.equals("yes") || response.equals("y");
        }

        System.out.println("\n===================================");
        System.out.println("Thanks for playing! Final Score: " + totalScore);
        System.out.println("===================================");

        scanner.close();
    }
}
