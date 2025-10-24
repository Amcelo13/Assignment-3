import java.util.*;
import java.io.*;

/**
 * Task 6: Inverted Indexing Using Tries
 * Assignment 3
 */
public class Task6_InvertedIndexing {
    
    private TrieNode root;
    private Map<String, Set<String>> invertedIndex;
    private Map<String, String> documents;
    
    class TrieNode {
        Map<Character, TrieNode> children;
        boolean isEndOfWord;
        Set<String> documentSet;
        
        TrieNode() {
            children = new HashMap<>();
            isEndOfWord = false;
            documentSet = new HashSet<>();
        }
    }
    
    class SearchResult {
        String word;
        Set<String> documents;
        Map<String, Integer> documentFrequency;
        
        SearchResult(String word) {
            this.word = word;
            this.documents = new HashSet<>();
            this.documentFrequency = new HashMap<>();
        }
    }
    
    public Task6_InvertedIndexing() {
        root = new TrieNode();
        invertedIndex = new HashMap<>();
        documents = new LinkedHashMap<>();
    }
    
    public void loadDocuments(String basePath) {
        String[] csvFiles = {"swiftride_data 2.csv", "prabh.csv", "kayak_scraped_data.csv", "nikhil.csv", "happy.csv"};
        
        System.out.println("Building inverted index...");
        
        for (String fileName : csvFiles) {
            String filePath = basePath + "/" + fileName;
            StringBuilder content = new StringBuilder();
            
            try (Scanner scanner = new Scanner(new File(filePath))) {
                if (scanner.hasNextLine()) {
                    scanner.nextLine();
                }
                
                while (scanner.hasNextLine()) {
                    content.append(scanner.nextLine().toLowerCase()).append(" ");
                }
                
                String documentContent = content.toString();
                documents.put(fileName, documentContent);
                indexDocument(fileName, documentContent);
                
            } catch (Exception e) {
                System.err.println("Error reading file: " + fileName);
            }
        }
        
        System.out.println("Indexing complete!");
    }
    
    private void indexDocument(String documentName, String content) {
        String[] words = content.split("[,\\s\\|\\-/]+");
        
        for (String word : words) {
            String cleanWord = word.replaceAll("[^a-zA-Z]", "").toLowerCase();
            if (cleanWord.length() > 2) {
                insertWordWithDocument(cleanWord, documentName);
                invertedIndex.putIfAbsent(cleanWord, new HashSet<>());
                invertedIndex.get(cleanWord).add(documentName);
            }
        }
    }
    
    private void insertWordWithDocument(String word, String documentName) {
        TrieNode current = root;
        
        for (char ch : word.toCharArray()) {
            current.children.putIfAbsent(ch, new TrieNode());
            current = current.children.get(ch);
        }
        
        current.isEndOfWord = true;
        current.documentSet.add(documentName);
    }
    
    public SearchResult search(String word) {
        String normalizedWord = word.toLowerCase();
        SearchResult result = new SearchResult(normalizedWord);
        
        TrieNode node = searchNode(normalizedWord);
        
        if (node != null && node.isEndOfWord) {
            result.documents = new HashSet<>(node.documentSet);
            
            for (String docName : result.documents) {
                int frequency = countWordInDocument(docName, normalizedWord);
                result.documentFrequency.put(docName, frequency);
            }
        }
        
        return result;
    }
    
    private TrieNode searchNode(String word) {
        TrieNode current = root;
        
        for (char ch : word.toCharArray()) {
            if (!current.children.containsKey(ch)) {
                return null;
            }
            current = current.children.get(ch);
        }
        
        return current;
    }
    
    private int countWordInDocument(String documentName, String word) {
        String content = documents.get(documentName);
        if (content == null) return 0;
        
        int count = 0;
        String[] words = content.split("[,\\s\\|\\-/]+");
        
        for (String w : words) {
            String cleanWord = w.replaceAll("[^a-zA-Z]", "").toLowerCase();
            if (cleanWord.equals(word)) {
                count++;
            }
        }
        
        return count;
    }
    
    public Set<String> searchMultiple(String... words) {
        if (words.length == 0) return new HashSet<>();
        
        Set<String> result = null;
        
        for (String word : words) {
            SearchResult searchResult = search(word);
            
            if (result == null) {
                result = new HashSet<>(searchResult.documents);
            } else {
                result.retainAll(searchResult.documents);
            }
        }
        
        return result != null ? result : new HashSet<>();
    }
    
    public void displaySearchResult(String word) {
        System.out.println("\n=== Search Results for: \"" + word + "\" ===");
        
        SearchResult result = search(word);
        
        if (result.documents.isEmpty()) {
            System.out.println("No documents found containing the word.");
        } else {
            System.out.println("Found in " + result.documents.size() + " document(s):");
            
            List<Map.Entry<String, Integer>> sortedDocs = new ArrayList<>(result.documentFrequency.entrySet());
            sortedDocs.sort((a, b) -> b.getValue().compareTo(a.getValue()));
            
            for (Map.Entry<String, Integer> entry : sortedDocs) {
                System.out.println("  - " + entry.getKey() + " (" + entry.getValue() + " occurrence(s))");
            }
        }
    }
    
    public void displayMultiSearchResult(String... words) {
        System.out.print("\n=== Search Results for: ");
        for (int i = 0; i < words.length; i++) {
            System.out.print("\"" + words[i] + "\"");
            if (i < words.length - 1) System.out.print(" AND ");
        }
        System.out.println(" ===");
        
        Set<String> result = searchMultiple(words);
        
        if (result.isEmpty()) {
            System.out.println("No documents found containing all words.");
        } else {
            System.out.println("Found in " + result.size() + " document(s):");
            for (String doc : result) {
                System.out.print("  - " + doc + " (");
                for (int i = 0; i < words.length; i++) {
                    System.out.print("\"" + words[i] + "\": " + countWordInDocument(doc, words[i].toLowerCase()));
                    if (i < words.length - 1) System.out.print(", ");
                }
                System.out.println(")");
            }
        }
    }
    
    public void displayStatistics() {
        System.out.println("\n=== Inverted Index Statistics ===");
        System.out.println("Total documents indexed: " + documents.size());
        System.out.println("Total unique words indexed: " + invertedIndex.size());
        
        List<Map.Entry<String, Set<String>>> sortedWords = new ArrayList<>(invertedIndex.entrySet());
        sortedWords.sort((a, b) -> Integer.compare(b.getValue().size(), a.getValue().size()));
        
        System.out.println("\nTop 10 Most Common Words (by document count):");
        for (int i = 0; i < Math.min(10, sortedWords.size()); i++) {
            Map.Entry<String, Set<String>> entry = sortedWords.get(i);
            System.out.println("  " + (i + 1) + ". \"" + entry.getKey() + "\" - appears in " + 
                             entry.getValue().size() + " document(s)");
        }
    }
    
    public static void main(String[] args) {
        System.out.println("=== Task 6: Inverted Indexing Using Tries ===\n");
        
        Task6_InvertedIndexing indexer = new Task6_InvertedIndexing();
        
        System.out.println("Loading and indexing documents...");
        indexer.loadDocuments(".");
        
        indexer.displayStatistics();
        
        System.out.println("\n=== Single Word Searches ===");
        String[] testWords = {"car", "rental", "budget", "toronto", "honda", "price"};
        for (String word : testWords) {
            indexer.displaySearchResult(word);
        }
        
        System.out.println("\n=== Multi-Word Searches ===");
        indexer.displayMultiSearchResult("car", "rental");
        indexer.displayMultiSearchResult("budget", "canada");
        indexer.displayMultiSearchResult("toronto", "location");
        
        System.out.println("\n=== Performance Test ===");
        long startTime = System.nanoTime();
        indexer.search("rental");
        long endTime = System.nanoTime();
        System.out.println("Inverted index search time: " + (endTime - startTime) / 1000 + " microseconds");
    }
}
