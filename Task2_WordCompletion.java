import java.util.*;
import java.io.*;

/**
 * Task 2: Word Completion Using Tries
 * Implements a word completion feature using a Trie
 * 
 * Assignment 3
 */
public class Task2_WordCompletion {
    
    private TrieNode root;
    
    class TrieNode {
        Map<Character, TrieNode> children;
        boolean isEndOfWord;
        
        TrieNode() {
            children = new HashMap<>();
            isEndOfWord = false;
        }
    }
    
    public Task2_WordCompletion() {
        root = new TrieNode();
    }
    
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
                    scanner.nextLine();
                }
                
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    String[] words = line.split("[,\\s\\|\\-/]+");
                    
                    for (String word : words) {
                        String cleanWord = word.replaceAll("[^a-zA-Z]", "").toLowerCase();
                        if (cleanWord.length() > 2) {
                            insert(cleanWord);
                        }
                    }
                }
            } catch (Exception e) {
                System.err.println("Error reading file: " + fileName);
            }
        }
    }
    
    public void insert(String word) {
        TrieNode current = root;
        for (char ch : word.toCharArray()) {
            current.children.putIfAbsent(ch, new TrieNode());
            current = current.children.get(ch);
        }
        current.isEndOfWord = true;
    }
    
    public List<String> findWordsWithPrefix(String prefix) {
        List<String> results = new ArrayList<>();
        TrieNode current = root;
        
        for (char ch : prefix.toLowerCase().toCharArray()) {
            if (!current.children.containsKey(ch)) {
                return results;
            }
            current = current.children.get(ch);
        }
        
        collectWords(current, prefix.toLowerCase(), results);
        Collections.sort(results);
        
        return results;
    }
    
    private void collectWords(TrieNode node, String currentWord, List<String> results) {
        if (node.isEndOfWord) {
            results.add(currentWord);
        }
        
        for (Map.Entry<Character, TrieNode> entry : node.children.entrySet()) {
            collectWords(entry.getValue(), currentWord + entry.getKey(), results);
        }
    }
    
    public void getCompletions(String prefix, int maxResults) {
        System.out.println("\nPrefix: \"" + prefix + "\"");
        
        List<String> completions = findWordsWithPrefix(prefix);
        
        if (completions.isEmpty()) {
            System.out.println("No completions found");
        } else {
            System.out.println("Found " + completions.size() + " completion(s):");
            int count = 0;
            for (String word : completions) {
                if (count >= maxResults) break;
                System.out.println("  " + (count + 1) + ". " + word);
                count++;
            }
            if (completions.size() > maxResults) {
                System.out.println("  ... and " + (completions.size() - maxResults) + " more");
            }
        }
    }
    
    public int countWords() {
        return countWordsHelper(root);
    }
    
    private int countWordsHelper(TrieNode node) {
        int count = node.isEndOfWord ? 1 : 0;
        for (TrieNode child : node.children.values()) {
            count += countWordsHelper(child);
        }
        return count;
    }
    
    public static void main(String[] args) {
        System.out.println("=== Task 2: Word Completion Using Tries ===\n");
        
        Task2_WordCompletion wordCompletion = new Task2_WordCompletion();
        
        System.out.println("Loading vocabulary from CSV files...");
        wordCompletion.loadVocabulary(".");
        
        System.out.println("\n=== Statistics ===");
        System.out.println("Total words in Trie: " + wordCompletion.countWords());
        
        String[] testPrefixes = {"car", "rent", "can", "tor", "bud", "pri", "loc", "veh"};
        
        System.out.println("\n=== Word Completion Tests ===");
        for (String prefix : testPrefixes) {
            wordCompletion.getCompletions(prefix, 10);
        }
    }
}
