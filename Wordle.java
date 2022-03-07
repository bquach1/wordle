//Bruce Quach
package wordle;

import java.util.*;
import java.io.*;

public class Wordle {
    private static String word = "";
    private static int score = 0;
    
    public static void generateWord() throws FileNotFoundException, IOException {
        
        try {
        BufferedReader reader = new BufferedReader(new FileReader("word-list.txt"));
        String line = reader.readLine();
        List<String> words = new ArrayList<String>();
        while(line != null) {
            String[] wordsLine = line.split(" ");
            for(String word : wordsLine) {
                words.add(word);
            }
            line = reader.readLine();
        }

        Random rand = new Random(System.currentTimeMillis());
        String randomWord = words.get(rand.nextInt(words.size()));
        word = randomWord.toUpperCase();
        //System.out.println(word);
        } catch (Exception e) {
            // Handle this
        }
    }
    
    public static void check(String userGuess) {
        
        char[] savedGuess = new char[userGuess.length()];
        char[] wordChar = word.toCharArray();
        
        for (int i = 0; i < word.length(); i++) {
            for (int j = 0; j < word.length(); j++) {
                if (userGuess.charAt(i) == word.charAt(i)) {
                    savedGuess[i] = userGuess.charAt(i);
                }
                else if ((word.indexOf(userGuess.charAt(i)) >= 0) &&
                         (word.indexOf(userGuess.charAt(i)) <= 4)) {
                    savedGuess[i] = '?';
                }
                else {
                    savedGuess[i] = '/';
                }   
            }
        }
        System.out.print(savedGuess);
    }
    
    public static void guess() {
        
        for (int g = 1; g <= 6; g++) {
            
            System.out.println("Enter a 5-letter word; guess number " + g);
          
            Scanner scan = new Scanner(System.in);
            String guess = scan.nextLine().toUpperCase();
        
            if(guess.length() == 5) {
                check(guess);
                if(guess.equals(word)) {
                    System.out.println("\nYou got the word in " + g + 
                                       " tries!");
                    score++;
                    System.out.println("You've successfully completed the " + 
                                       "Wordable " + score + " times.");
                    
                    Scanner replayScan = new Scanner(System.in);
                    System.out.println("Would you like to play again?");
                    String confirm = replayScan.nextLine();
                    System.out.println();
                    
                    if(confirm.toUpperCase().equals("YES")) {
                        guess();
                    }
                    else {
                        break;
                    }
                }
                else {
                    System.out.println("\nTry again.");
                }
            }
            else if (guess.toUpperCase().equals("QUIT")) {
                System.out.println("Thanks for playing!");
                break;
            }
            else {
                System.out.println("Your word has to be 5 letters long!\n");
                g--;
            }           
        }        
    }
    
    public static void main(String[] args) throws Exception{
        generateWord();
        guess();
    }    
}
