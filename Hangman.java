// @author Joseph Walker

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Hangman {

	public static void main(String[] args) throws FileNotFoundException {
		
		Scanner keyboard = new Scanner(System.in);
		
// Prompt for user to select how many players: 
		
		System.out.println("1 or 2 players?");
		String players = keyboard.nextLine();
		
// We create a new String which allows us to utilize the words list.		
		
		String word;
		
		if (players.equals("1")) {
		
		Scanner scan = new Scanner(new File("/Users/josephwalker/Desktop/words.txt"));

		List<String> words = new ArrayList<>();

// while loop to add each word from imported file to our List
		
		while (scan.hasNext()) {
		   words.add(scan.nextLine());
		}
		
// Instantiate our random object for random word selection 
		
		Random rand = new Random();
		
//  We can grab random words from our words List by passing our rand object, calling 
// the nextInt method, and then passing the size of our words list as an arg.
		
		word = words.get(rand.nextInt(words.size()));
	}
		else {
			System.out.println("Player 1, please enter your word.");
			word = keyboard.nextLine();
			System.out.println("\n\\n\n\n\n\n\n\n\n\n\n\n\n\\n\n\n\n\n\n\n\n\n\n");
			System.out.println("Ready for player 2! Good luck!");
		}
				
// Creating a new list of the alphabet which will allow us to mark off all chars(letters)
// in which the user guesses. We use "Character" for the char Wrapper class.
		
		List<Character> playerGuesses = new ArrayList<>();
		
// while loop so we can continue adding guesses for user
		
		Integer wrongCount = 0;

		while(true) {
			printHangedMan(wrongCount);
			
			if (wrongCount >= 6) {
				System.out.println("You lose!");
				System.out.println("The word was: " + word);
				break;
			}
			
			printWordState(word, playerGuesses);
			if(!getPlayerGuess(keyboard, word, playerGuesses)){
				wrongCount++;
			}
			
// if our printWordState boolean method returns "true" (meaning ALL letters 
// have been correctly guessed) we will break out of while loop and complete game
			
			if (printWordState(word, playerGuesses)) {
				System.out.println("You win!");
				break;
			}
			
			System.out.println("Please enter your guess for the word: ");
			if(keyboard.nextLine().equals(word)) {
				System.out.println("You win!");
				break;
			}
			else {
				System.out.println("Nope! Try again.");
			}
		}
	}

	private static void printHangedMan(Integer wrongCount) {
	    System.out.println(" -------");
	    System.out.println(" |     |");
	    if (wrongCount >= 1) {
	      System.out.println(" O");
	    }
		
	    if (wrongCount >= 2) {
	      System.out.print("\\ ");
	      if (wrongCount >= 3) {
	        System.out.println("/");
	      }
	      else {
	        System.out.println("");
	      }
	    }
	    
	    if (wrongCount >= 4) {
	      System.out.println(" |");
	    }
		
	    if (wrongCount >= 5) {
	      System.out.print("/ ");
	      if (wrongCount >= 6) {
	        System.out.println("\\");
	      }
	      else {
	        System.out.println("");
	      }
	    }
	    System.out.println("");
	    System.out.println("");
	  }

// Method to get player's guess.		
// letterGuess will serve as variable to hold user input. To proof the parsing of only 
// one letter, we will call the add method and pass letterGuess as an arg, using charAt
// to extract the first index (0) only, regardless of input length.
		
	public static boolean getPlayerGuess(Scanner keyboard, String word, List<Character> playerGuesses) {
		
		System.out.println("Please enter a letter: ");
		String letterGuess = keyboard.nextLine();
		playerGuesses.add(letterGuess.charAt(0));
		
		return word.contains(letterGuess);
	}

// Simple method which uses for loop to iterates through random word selected. If player 
// guesses the  char contained in the word, char will be displayed (single print statement). 
// Else,we will print a dash to show char has NOT been guessed yet. 
		
	private static boolean printWordState(String word, List<Character> playerGuesses) {
		
// int correctCount variable will track correct number of guesses
		
		int correctCount = 0;
		for (int i = 0; i < word.length(); i++) {
			if (playerGuesses.contains(word.charAt(i))) {
				System.out.print(word.charAt(i));
				correctCount++;
			}
			
			else {
				System.out.print("-");
			}
		}
		System.out.println();
		
		return (word.length() == correctCount);
	}
}
