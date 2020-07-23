// Gus Reiber
// 7/22/2020
// TA: 	Sumant Guha
// Assignment 5

// Guess in integers 
// be answered in boolean.
// Truer words not spoken

// testing gitHub

import java.util.*;

class Main {

  // MIN and MAX are easy to change...
  public static final int MIN = 1;
  public static final int MAX = 100;

  // Private constants for easy localization and cleaner code...
  private static final String HAIKU_A    = "\nGuess in integers \n be answered in boolean. \n Truer words not spoken\n";
  private static final String HAIKU_B    = "\nIn lazy summertime \n  elusive code executes \n    past aprehension\n";
  private static final String HAIKU_C    = "\nGolden shine has turds \n by moonlit shores' shallow waters \n That which I polish\n";
  
  private static final String RULES      = "I'm thinking of a number between ##MIN## and ##MAX##...";
  private static final String GUESS      = "Your guess? ";
  private static final String HIGHER     = "It's higher.";
  private static final String LOWER      = "It's lower.";
  private static final String RIGHT_IN_  = "You got it right in ";
  private static final String _GUESSES   = " guesses!";
  private static final String AGAIN      = "Do you want to play again? "; 
  private static final String OVERALL    = "Overall results:";
  private static final String GAMES      = "Total games = ";
  private static final String GUESSES    = "Total guesses = ";
  private static final String AVG        = "Guesses/game = ";
  private static final String BEST       = "Best game = ";

  private static final char REPLAY       = 'Y';

  public static void main(String[] args) {
    // things we need...
    Random r    = new Random();
    Scanner cIn = new Scanner(System.in);

    // things we track...
    boolean playGame    = true;
    int gameCount       = 0;
    int totalGuessCount = 0;
    int best            = 0;

    // what we do...
    printHaiku(r);
    while(playGame){

      printRules(MIN,MAX);
      int correct       = getRand(r);
      int guessCount    = loopQuestions(correct, cIn);
      playGame          = askPlayAgain(guessCount, cIn);
      totalGuessCount  += guessCount;
      best              = (best != 0 && best < guessCount)?
                            best : guessCount;
      gameCount++;
    }

    printSummary(gameCount, totalGuessCount, best);

  }
  // does what it says...
  public static void printHaiku(Random r){
    int h = r.nextInt(3);
    String haiku = HAIKU_A;
    if(h == 1) haiku = HAIKU_B;
    if(h == 2)haiku = HAIKU_C;
    System.out.println(haiku);
  }

  // print the welcome message from string constants. Using replaceAll for clean language translation. Takes min and max.
  public static void printRules(int MIN, int MAX){
    String rules = RULES.replaceAll("##MIN##", MIN + "").replaceAll("##MAX##", MAX + "");
    System.out.println(rules);
  }

  // get random number according to static rules
  public static int getRand(Random r){
    int correct = r.nextInt(MAX) + MIN;
    // EZ dump of correct answer...
    // System.out.println("====> I'm thinking this.... " + correct);
    return correct;
  }

  // I ask, you answer...
  public static int askGuess(Scanner cIn){
    System.out.print(GUESS);
    int guess = cIn.nextInt();
    return guess;
  }

  // simple boolean resolution, but makes while statement more readable. renders response to last guess by side-effect.
  public static boolean isCorrect(int lastGuess, int correct){
    if(lastGuess != correct)
      System.out.println((correct > lastGuess)?HIGHER:LOWER);
    
    return (lastGuess == correct);
  }

  // backbone of the game... you get to keep asking until you find the correct answer.
  public static int loopQuestions(int correct, Scanner cIn){
    int lastGuess = askGuess(cIn);
    int guessCount = 1;
    while(!isCorrect(lastGuess,correct)){
      lastGuess = askGuess(cIn);
      guessCount++;
    }
    return guessCount;
  }

  // should we play again? since this renders the summary by side-effect, it needs the guess count and the scanner object
  public static boolean askPlayAgain(int guessCount, Scanner cIn){
    System.out.println(RIGHT_IN_ + guessCount + _GUESSES);
    System.out.print(AGAIN);
    // Get the uppercase version of the first char to compare to REPLAY constant
    char again = Character.toUpperCase(cIn.next().charAt(0));
    System.out.println();

    return again == Character.toUpperCase(REPLAY);
  }

  // prints out my game summary in a nice way, requires counts and best ints
  public static void printSummary(int gameCount, int totalGuessCount, int best){
    System.out.println(OVERALL);
    System.out.println(GAMES + gameCount);
    System.out.println(GUESSES + totalGuessCount);
    System.out.println(AVG + (double)(totalGuessCount/gameCount));
    System.out.println(BEST + best);
    System.out.println();
  }
}