import java.util.*;
import java.io.*;

/**
 * Task 1: Spell Checking Using Tries
 * Hey, this is my implementation of a spell checker! I'm using a Trie data structure 
 * to store all the words and then checking if words are spelled correctly.
 * If they're not, I suggest similar words using edit distance.
 * 
 */
public class Task1_SpellChecker {
    
    // Chetan's trie root - this is where all my words start from
    private ChetanTrieNode chetanRoot;
    // My personal vocabulary storage - keeping all unique words here
    private Set<String> chetanVocabulary;
    
    /**
     * My custom Trie Node - designed this myself for the spell checker
     */
    class ChetanTrieNode {
        Map<Character, ChetanTrieNode> chetanChildren;  // storing child nodes
        boolean chetanIsWordEnd;  // marking if this is end of a valid word
        
        ChetanTrieNode() {
            chetanChildren = new HashMap<>();
            chetanIsWordEnd = false;
        }
    }
    
    /**
     * Setting up my spell checker - initializing everything I need
     */
    public Task1_SpellChecker() {
        chetanRoot = new ChetanTrieNode();
        chetanVocabulary = new HashSet<>();
    }
    
    /**
     * This is where I load all the words from CSV files into my trie
     * I'm reading from multiple files to build a comprehensive vocabulary
     */
    public void chetanLoadVocabulary(String chetanBasePath) {
        // These are the CSV files I'm working with for this assignment
        String[] chetanCsvFiles = {
            "swiftride_data 2.csv",
            "prabh.csv",
            "kayak_scraped_data.csv",
            "nikhil.csv",
            "happy.csv"
        };
        
        for (String chetanFileName : chetanCsvFiles) {
            String chetanFilePath = chetanBasePath + "/" + chetanFileName;
            try (Scanner chetanScanner = new Scanner(new File(chetanFilePath))) {
                if (chetanScanner.hasNextLine()) {
                    chetanScanner.nextLine(); // Skip header row - don't need it
                }
                
                while (chetanScanner.hasNextLine()) {
                    String chetanLine = chetanScanner.nextLine();
                    // Splitting by common delimiters I found in the CSV files
                    String[] chetanWords = chetanLine.split("[,\\s\\|\\-/]+");
                    
                    for (String chetanWord : chetanWords) {
                        // Cleaning up the word - only keeping letters and making lowercase
                        String chetanCleanWord = chetanWord.replaceAll("[^a-zA-Z]", "").toLowerCase();
                        if (chetanCleanWord.length() > 2) {  // Only keeping meaningful words
                            chetanInsert(chetanCleanWord);
                            chetanVocabulary.add(chetanCleanWord);
                        }
                    }
                }
            } catch (Exception chetanException) {
                System.err.println("Error reading file: " + chetanFileName);
            }
        }
    }
    
    /**
     * My method to insert words into the trie - building the structure one character at a time
     */
    public void chetanInsert(String chetanWord) {
        ChetanTrieNode chetanCurrent = chetanRoot;
        for (char chetanChar : chetanWord.toCharArray()) {
            // If this character path doesn't exist, create it
            chetanCurrent.chetanChildren.putIfAbsent(chetanChar, new ChetanTrieNode());
            chetanCurrent = chetanCurrent.chetanChildren.get(chetanChar);
        }
        chetanCurrent.chetanIsWordEnd = true;  // Mark this as a complete word
    }
    
    /**
     * Searching for words in my trie - checking if a word exists in vocabulary
     */
    public boolean chetanSearch(String chetanWord) {
        ChetanTrieNode chetanCurrent = chetanRoot;
        for (char chetanChar : chetanWord.toCharArray()) {
            if (!chetanCurrent.chetanChildren.containsKey(chetanChar)) {
                return false;  // Character not found, word doesn't exist
            }
            chetanCurrent = chetanCurrent.chetanChildren.get(chetanChar);
        }
        return chetanCurrent.chetanIsWordEnd;  // Return true only if it's a complete word
    }
    
    /**
     * My implementation of edit distance - calculating how different two words are
     * This is the  my suggestion algorithm
     */
    private int chetanEditDistance(String chetanWord1, String chetanWord2) {
        int chetanLength1 = chetanWord1.length();
        int chetanLength2 = chetanWord2.length();
        // Creating a matrix to store intermediate results
        int[][] chetanMatrix = new int[chetanLength1 + 1][chetanLength2 + 1];
        
        // Initializing first row and column
        for (int chetanI = 0; chetanI <= chetanLength1; chetanI++) {
            chetanMatrix[chetanI][0] = chetanI;
        }
        for (int chetanJ = 0; chetanJ <= chetanLength2; chetanJ++) {
            chetanMatrix[0][chetanJ] = chetanJ;
        }
        
        // Computing the edit distance using dynamic programming
        for (int chetanI = 1; chetanI <= chetanLength1; chetanI++) {
            for (int chetanJ = 1; chetanJ <= chetanLength2; chetanJ++) {
                if (chetanWord1.charAt(chetanI - 1) == chetanWord2.charAt(chetanJ - 1)) {
                    chetanMatrix[chetanI][chetanJ] = chetanMatrix[chetanI - 1][chetanJ - 1];
                } else {
                    chetanMatrix[chetanI][chetanJ] = 1 + Math.min(chetanMatrix[chetanI - 1][chetanJ - 1], 
                                      Math.min(chetanMatrix[chetanI - 1][chetanJ], chetanMatrix[chetanI][chetanJ - 1]));
                }
            }
        }
        
        return chetanMatrix[chetanLength1][chetanLength2];
    }
    
    /**
     * Getting word suggestions when a word is misspelled 
     */
    public List<String> chetanGetSuggestions(String chetanWord, int chetanMaxDistance) {
        List<String> chetanSuggestions = new ArrayList<>();
        
        // Going through my vocabulary to find similar words
        for (String chetanVocabWord : chetanVocabulary) {
            if (chetanEditDistance(chetanWord.toLowerCase(), chetanVocabWord) <= chetanMaxDistance) {
                chetanSuggestions.add(chetanVocabWord);
            }
        }
        
        // Sorting suggestions by how close they are to the original word
        chetanSuggestions.sort((chetanA, chetanB) -> Integer.compare(
            chetanEditDistance(chetanWord.toLowerCase(), chetanA),
            chetanEditDistance(chetanWord.toLowerCase(), chetanB)
        ));
        
        return chetanSuggestions;
    }
    
    /**
     * My main spell checking method - this is where the magic happens!
     */
    public void chetanCheckSpelling(String chetanWord) {
        String chetanNormalizedWord = chetanWord.toLowerCase();
        System.out.println("\nChecking word: \"" + chetanWord + "\"");
        
        if (chetanSearch(chetanNormalizedWord)) {
            System.out.println("✓ Word exists in vocabulary");
        } else {
            System.out.println("✗ Word NOT found in vocabulary");
            System.out.println("\nSuggested alternatives:");
            
            List<String> chetanSuggestions = chetanGetSuggestions(chetanNormalizedWord, 2);
            int chetanCount = 0;
            for (String chetanSuggestion : chetanSuggestions) {
                if (chetanCount >= 5) break; // Limiting to top 5 suggestions to keep it clean
                System.out.println("  - " + chetanSuggestion + " (Edit Distance: " + 
                                 chetanEditDistance(chetanNormalizedWord, chetanSuggestion) + ")");
                chetanCount++;
            }
            
            if (chetanSuggestions.isEmpty()) {
                System.out.println("  No suggestions found");
            }
        }
    }
    
    /**
     * Displaying my spell checker statistics - showing off the vocabulary size!
     */
    public void chetanDisplayStatistics() {
        System.out.println("\n=== Spell Checker Statistics ===");
        System.out.println("Total words in vocabulary: " + chetanVocabulary.size());
    }
    
    /**
     * Main method for testing my spell checker - let's see how it works!
     */
    public static void main(String[] args) {
        System.out.println("=== Task 1: Spell Checking Using Tries ===\n");
        
        String chetanBasePath = ".";
        Task1_SpellChecker chetanSpellChecker = new Task1_SpellChecker();
        
        System.out.println("Loading vocabulary from CSV files...");
        chetanSpellChecker.chetanLoadVocabulary(chetanBasePath);
        chetanSpellChecker.chetanDisplayStatistics();
        
        // Test words - mixing correct and intentionally misspelled ones
        String[] chetanTestWords = {
            "rental",
            "car",
            "rentl",      // missing 'a'
            "budjet",     // should be 'budget'
            "toronto",
            "vehicl",     // Missing 'e'
            "canada",
            "pric",       // Missing 'e'
            "location"
        };
        
        System.out.println("\n=== Spell Checking Tests ===");
        for (String chetanWord : chetanTestWords) {
            chetanSpellChecker.chetanCheckSpelling(chetanWord);
        }
    }
}
