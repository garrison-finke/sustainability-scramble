
import java.util.*;
import java.util.stream.Collectors;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;

public class WordBank {
    
    private ArrayList<String> wordBank; // The collection of words that the game can pick from
    private Random generator;           // Random number generator
    
    public WordBank() {
        wordBank = new ArrayList<String>();
        generator = new Random();
    }
    
    public void readWords(Theme theme) {
        
        List<String> lines;
        String path = "/themes/" + theme + ".txt";
        try (InputStream in = getClass().getResourceAsStream(path)) {
            if (in == null) {
                throw new IllegalArgumentException();
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));
            lines = reader.lines().collect(Collectors.toList());
        } catch (IOException e) {
            throw new IllegalArgumentException();
        }

        for (String word : lines) {
            wordBank.add(word);
        }
    }
    
    // Get a word from the word bank
    public String getNext() {
        return wordBank.get(generator.nextInt(wordBank.size()));
    }

    // Get a scramble for a word or phrase
    public String scramble(String phrase) {

        StringBuilder scrambledPhrase = new StringBuilder();        // The scrambled version of the phrase

        String[] splitPhrase = phrase.split(" ");            // Split the phrase into its component words

        for (int i = 0; i < splitPhrase.length; i++) {
            
            StringBuilder word = new StringBuilder(splitPhrase[i]); // Get the current word in the unscrambled phrase
            StringBuilder scrambledWord = new StringBuilder();      // The scrambled version of the current word
            while (!word.toString().equals("")) {         // Pick one random character at a time from the unscrambled word and add it to the scrambled word
                int index = generator.nextInt(word.length());
                char character = word.charAt(index);
                word.deleteCharAt(index);
                scrambledWord = scrambledWord.append(character);
            }
            
            scrambledPhrase.append(scrambledWord);                 // Add the scrambled word to the scrambled phrase
            if (i < splitPhrase.length - 1) {
                scrambledPhrase.append(" ");                  // Add a space between words in the scrambled phrase
            }
        }
        
        return scrambledPhrase.toString();
    }
}
