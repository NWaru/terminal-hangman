import java.util.Scanner;

public class Hangman {
    public static void main(String[] args) {
	System.out.println(randomWord); 
	//setUpGame();
	playGame();

	reader.close();
    }  
    static HangmanLexicon stub;
    static Scanner reader;

    static String randomWord = getRandomWord();
    static String hiddenWord;
    static int guesses = 8; 

    public static String getRandomWord() {
	stub = new HangmanLexicon();
	int randomNumber = (int)(Math.random() * stub.getWordCount());
	String randomWord = stub.getWord(randomNumber);
    
	return(randomWord);
    }

    public static String setDashes() {
	String dashes = ""; 

	for(char letters : randomWord.toCharArray()) {
	    dashes += "_"; 
	}
	return(dashes); 
    }

    public static void setUpGame() {
	hiddenWord = setDashes();
	System.out.println("Welcome to Hangman!");
	System.out.println("The word currently looks like this: " +
			   hiddenWord);   
	System.out.println("You have " + guesses + " guesses left");
    }

    public static String getGuess() {
	reader = new Scanner(System.in);
	System.out.println("Guess a letter: "); 
	String guess = reader.next(); 
      
 	return(guess); 
    }

    public static String updateHiddenWord(String guess, String randomWord, String hiddenWord) {
	if(randomWord.length() <= 0) {
	    return("");
	}
	else {
	    if(guess.toUpperCase().equals(randomWord.substring(0,1))) {
		return(guess.toUpperCase() + updateHiddenWord(guess, randomWord.substring(1),
							      hiddenWord.substring(1))); 
	    }
	    else {
		return(hiddenWord.substring(0,1) + updateHiddenWord(guess, randomWord.substring(1),
								  hiddenWord.substring(1)));
	    }
	}
    }

    public static void playGame() {
	hiddenWord = setDashes();
	String guess; 
	System.out.println("Welcome to Hangman!");
	
	while(guesses > 0 && !(hiddenWord.equals(randomWord))) {
	    System.out.println("--------------------------------------------------"); 
	    System.out.println("The word currently looks like this: " + hiddenWord);
	    System.out.println("You have " + guesses + " guesses left");
	    
	    while(true) {
		guess = getGuess();
		if(guess.length() > 1) {
		    System.out.println("Your guess must be a single character");
		}
		else {
		    break;
		}
	    }
	    for(int i = 0; i < randomWord.length(); i++) {
		if(guess.toUpperCase().equals(randomWord.substring(i, i+1))) {
		    hiddenWord = updateHiddenWord(guess, randomWord, hiddenWord);
		    break;
		}
		if(i == guess.length() - 1) {
		    guesses--; 
		}
	    }
	}
	System.out.println("--------------------------------------------------");
	
	if(guesses <= 0) {
	    System.out.println("You have run out of guesses; you have been hanged.");
	    System.out.println("The word was: " + randomWord); 
	}
	else {
	    System.out.println("The word is: " + randomWord); 
	    System.out.println("Rejoice! You guessed the word and have not been hanged!");
	}
    }
}

