import java.util.*;
import java.io.*;

/**
 * Task 1: Spell Checking Using Tries
 * Implements a spell checker that uses a Trie to store vocabulary
 * and provides alternative word suggestions using the Edit Distance algorithm.
 * 
 * Assignment 3
 */
public class Task1_SpellChecker {
    
    private TrieNode root;
    private Set<String> vocabulary;
    
    /**
     * Trie Node implementation
     */
    class TrieNode {
        Map<Character, TrieNode> children;
        boolean isEndOfWord;
        
        TrieNode() {
            children = new HashMap<>();
            isEndOfWord = false;
        }
    }
    
    /**
     * Initialize the spell checker
     */
    public Task1_SpellChecker() {
        root = new TrieNode();
        vocabulary = new HashSet<>();
    }
    
    /**
     * Load vocabulary from CSV files into Trie
     */
    public void loadVocabulary(String basePath) {
        String[] csvFiles = {
            "swiftride_data 2.csv",
            "prabh.csv",
            "kayak_scraped_data.csv",
            "nikhil.csv",
            "happy.csv"
        };
        
        for (String fileName : csvFiles) {
            String filePath = basePath + "/" + fileName;
            try (Scanner scanner = new Scanner(new File(filePath))) {
                if (scanner.hasNextLine()) {
                    scanner.nextLine(); // Skip header
                }
                
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    String[] words = line.split("[,\\s\\|\\-/]+");
                    
                    for (String word : words) {
                        String cleanWord = word.replaceAll("[^a-zA-Z]", "").toLowerCase();
                        if (cleanWord.length() > 2) {
                            insert(cleanWord);
                            vocabulary.add(cleanWord);
                        }
                    }
                }
            } catch (Exception e) {
                System.err.println("Error reading file: " + fileName);
            }
        }
    }
    
    /**
     * Insert a word into the Trie
     */
    public void insert(String word) {
        TrieNode current = root;
        for (char ch : word.toCharArray()) {
            current.children.putIfAbsent(ch, new TrieNode());
            current = current.children.get(ch);
        }
        current.isEndOfWord = true;
    }
    
    /**
     * Search for a word in the Trie
     */
    public boolean search(String word) {
        TrieNode current = root;
        for (char ch : word.toCharArray()) {
            if (!current.children.containsKey(ch)) {
                return false;
            }
            current = current.children.get(ch);
        }
        return current.isEndOfWord;
    }
    
    /**
     * Calculate Edit Distance between two words (Levenshtein Distance)
     */
    private int editDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        int[][] dp = new int[m + 1][n + 1];
        
        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j <= n; j++) {
            dp[0][j] = j;
        }
        
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = 1 + Math.min(dp[i - 1][j - 1], 
                                  Math.min(dp[i - 1][j], dp[i][j - 1]));
                }
            }
        }
        
        return dp[m][n];
    }
    
    /**
     * Get word suggestions based on Edit Distance
     */
    public List<String> getSuggestions(String word, int maxDistance) {
        List<String> suggestions = new ArrayList<>();
        
        for (String vocabWord : vocabulary) {
            if (editDistance(word.toLowerCase(), vocabWord) <= maxDistance) {
                suggestions.add(vocabWord);
            }
        }
        
        // Sort by edit distance
        suggestions.sort((a, b) -> Integer.compare(
            editDistance(word.toLowerCase(), a),
            editDistance(word.toLowerCase(), b)
        ));
        
        return suggestions;
    }
    
    /**
     * Check spelling and get suggestions
     */
    public void checkSpelling(String word) {
        String normalizedWord = word.toLowerCase();
        System.out.println("\nChecking word: \"" + word + "\"");
        
        if (search(normalizedWord)) {
            System.out.println("✓ Word exists in vocabulary");
        } else {
            System.out.println("✗ Word NOT found in vocabulary");
            System.out.println("\nSuggested alternatives:");
            
            List<String> suggestions = getSuggestions(normalizedWord, 2);
            int count = 0;
            for (String suggestion : suggestions) {
                if (count >= 5) break; // Limit to top 5 suggestions
                System.out.println("  - " + suggestion + " (Edit Distance: " + 
                                 editDistance(normalizedWord, suggestion) + ")");
                count++;
            }
            
            if (suggestions.isEmpty()) {
                System.out.println("  No suggestions found");
            }
        }
    }
    
    /**
     * Display statistics
     */
    public void displayStatistics() {
        System.out.println("\n=== Spell Checker Statistics ===");
        System.out.println("Total words in vocabulary: " + vocabulary.size());
    }
    
    /**
     * Main method for testing
     */
    public static void main(String[] args) {
        System.out.println("=== Task 1: Spell Checking Using Tries ===\n");
        
        String basePath = ".";
        Task1_SpellChecker spellChecker = new Task1_SpellChecker();
        
        System.out.println("Loading vocabulary from CSV files...");
        spellChecker.loadVocabulary(basePath);
        spellChecker.displayStatistics();
        
        // Test words
        String[] testWords = {
            "rental",
            "car",
            "rentl",      // Misspelled
            "budjet",     // Misspelled
            "toronto",
            "vehicl",     // Misspelled
            "canada",
            "pric",       // Misspelled
            "location"
        };
        
        System.out.println("\n=== Spell Checking Tests ===");
        for (String word : testWords) {
            spellChecker.checkSpelling(word);
        }
    }
}
