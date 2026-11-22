
import java.util.*;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class WordBank {
    
    private List<String> words;
    private Map<String, String> dictionary; // The collection of words that the game can pick from
    private Random generator;           // Random number generator
    
    public WordBank() {
        words = new ArrayList<>();
        dictionary = new HashMap<String, String>();
        generator = new Random();
    }
    
    public void readWords(String theme) {
        List<String> lines;
        String path = "/assets/themes/" + theme + ".txt";
        try (InputStream in = getClass().getResourceAsStream(path)) {
            if (in == null) {
                throw new IllegalArgumentException();
            }
            lines = new String(in.readAllBytes(), StandardCharsets.UTF_8).lines().toList();
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }

        for (String line : lines) {
            String word = line.split("\\|")[0].strip();
            String definition = line.split("\\|")[1].strip();
            dictionary.put(word, definition);
        }

        words.addAll(dictionary.keySet());
    }
    
    // Get a word from the word bank
    public String getNext() {
        return words.get(generator.nextInt(words.size()));
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
