import java.util.*;
import java.io.*;

/**
 * Task 4: Search Frequency Tracking Using KMP Algorithm
 * Assignment 3
 */
public class Task4_SearchFrequency {
    
    private Map<String, Integer> searchFrequency;
    private Map<String, String> fileContents;
    private List<SearchRecord> searchHistory;
    
    class SearchRecord {
        String word;
        long timestamp;
        int occurrences;
        
        SearchRecord(String word, long timestamp, int occurrences) {
            this.word = word;
            this.timestamp = timestamp;
            this.occurrences = occurrences;
        }
    }
    
    public Task4_SearchFrequency() {
        searchFrequency = new HashMap<>();
        fileContents = new HashMap<>();
        searchHistory = new ArrayList<>();
    }
    
    public void loadFiles(String basePath) {
        String[] csvFiles = {"swiftride_data 2.csv", "prabh.csv", "kayak_scraped_data.csv", "nikhil.csv", "happy.csv"};
        
        for (String fileName : csvFiles) {
            String filePath = basePath + "/" + fileName;
            StringBuilder content = new StringBuilder();
            
            try (Scanner scanner = new Scanner(new File(filePath))) {
                while (scanner.hasNextLine()) {
                    content.append(scanner.nextLine().toLowerCase()).append(" ");
                }
                fileContents.put(fileName, content.toString());
            } catch (Exception e) {
                System.err.println("Error reading file: " + fileName);
            }
        }
    }
    
    private int[] computeLPSArray(String pattern) {
        int m = pattern.length();
        int[] lps = new int[m];
        int len = 0;
        int i = 1;
        
        lps[0] = 0;
        
        while (i < m) {
            if (pattern.charAt(i) == pattern.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len != 0) {
                    len = lps[len - 1];
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }
        
        return lps;
    }
    
    public int kmpSearch(String text, String pattern) {
        int n = text.length();
        int m = pattern.length();
        int count = 0;
        
        if (m == 0 || n == 0 || m > n) return 0;
        
        int[] lps = computeLPSArray(pattern);
        int i = 0, j = 0;
        
        while (i < n) {
            if (pattern.charAt(j) == text.charAt(i)) {
                i++;
                j++;
            }
            
            if (j == m) {
                count++;
                j = lps[j - 1];
            } else if (i < n && pattern.charAt(j) != text.charAt(i)) {
                if (j != 0) {
                    j = lps[j - 1];
                } else {
                    i++;
                }
            }
        }
        
        return count;
    }
    
    public int search(String word) {
        String normalizedWord = word.toLowerCase();
        searchFrequency.put(normalizedWord, searchFrequency.getOrDefault(normalizedWord, 0) + 1);
        
        int totalOccurrences = 0;
        for (String content : fileContents.values()) {
            totalOccurrences += kmpSearch(content, normalizedWord);
        }
        
        searchHistory.add(new SearchRecord(normalizedWord, System.currentTimeMillis(), totalOccurrences));
        return totalOccurrences;
    }
    
    public int getSearchFrequency(String word) {
        return searchFrequency.getOrDefault(word.toLowerCase(), 0);
    }
    
    public List<Map.Entry<String, Integer>> getTopSearches(int n) {
        List<Map.Entry<String, Integer>> sortedEntries = new ArrayList<>(searchFrequency.entrySet());
        sortedEntries.sort((a, b) -> b.getValue().compareTo(a.getValue()));
        return sortedEntries.subList(0, Math.min(n, sortedEntries.size()));
    }
    
    public void displayStatistics() {
        System.out.println("\n=== Search Frequency Statistics ===");
        System.out.println("Total unique searches: " + searchFrequency.size());
        System.out.println("Total search operations: " + searchHistory.size());
        
        System.out.println("\nTop 10 Most Searched Words:");
        List<Map.Entry<String, Integer>> topSearches = getTopSearches(10);
        int rank = 1;
        for (Map.Entry<String, Integer> entry : topSearches) {
            System.out.println(rank + ". \"" + entry.getKey() + "\" - searched " + entry.getValue() + " time(s)");
            rank++;
        }
    }
    
    public void displaySearchResult(String word) {
        System.out.println("\n=== Search Result for: \"" + word + "\" ===");
        int occurrences = search(word);
        int frequency = getSearchFrequency(word);
        System.out.println("Occurrences in documents: " + occurrences);
        System.out.println("Search frequency: " + frequency);
    }
    
    public static void main(String[] args) {
        System.out.println("=== Task 4: Search Frequency Tracking Using KMP Algorithm ===\n");
        
        Task4_SearchFrequency tracker = new Task4_SearchFrequency();
        
        System.out.println("Loading CSV files...");
        tracker.loadFiles(".");
        System.out.println("Files loaded: " + tracker.fileContents.size());
        
        String[] searchWords = {"car", "car", "car", "rental", "rental", "budget", "budget", "budget", "canada", "toronto", "toronto", "price", "location", "honda"};
        
        System.out.println("\n=== Performing Searches ===");
        for (String word : searchWords) {
            tracker.displaySearchResult(word);
        }
        
        tracker.displayStatistics();
        
        System.out.println("\n=== Algorithm Performance Test ===");
        long startTime = System.nanoTime();
        tracker.kmpSearch(tracker.fileContents.values().iterator().next(), "rental");
        long endTime = System.nanoTime();
        System.out.println("KMP search time: " + (endTime - startTime) / 1000 + " microseconds");
    }
}
