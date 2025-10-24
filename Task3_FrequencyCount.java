import java.util.*;
import java.io.*;

/**
 * Task 3: Frequency Count Using Boyer-Moore Algorithm
 * Implements a frequency count feature that shows the number of occurrences 
 * of a word in files using the Boyer-Moore algorithm
 * 
 * Assignment 3
 */
public class Task3_FrequencyCount {
    
    private Map<String, String> fileContents;
    
    public Task3_FrequencyCount() {
        fileContents = new HashMap<>();
    }
    
    public void loadFiles(String basePath) {
        String[] csvFiles = {
            "swiftride_data 2.csv",
            "prabh.csv",
            "kayak_scraped_data.csv",
            "nikhil.csv",
            "happy.csv"
        };
        
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
    
    private int[] buildBadCharTable(String pattern) {
        int[] table = new int[256];
        int m = pattern.length();
        
        Arrays.fill(table, -1);
        
        for (int i = 0; i < m; i++) {
            table[(int) pattern.charAt(i)] = i;
        }
        
        return table;
    }
    
    public int boyerMooreSearch(String text, String pattern) {
        int n = text.length();
        int m = pattern.length();
        int count = 0;
        
        if (m == 0 || n == 0 || m > n) {
            return 0;
        }
        
        int[] badChar = buildBadCharTable(pattern);
        int s = 0;
        
        while (s <= (n - m)) {
            int j = m - 1;
            
            while (j >= 0 && pattern.charAt(j) == text.charAt(s + j)) {
                j--;
            }
            
            if (j < 0) {
                count++;
                s += (s + m < n && (int)text.charAt(s + m) < 256) ? m - badChar[(int)text.charAt(s + m)] : 1;
            } else {
                int charIndex = (int)text.charAt(s + j);
                s += Math.max(1, j - (charIndex < 256 ? badChar[charIndex] : -1));
            }
        }
        
        return count;
    }
    
    public int countInFile(String fileName, String word) {
        String content = fileContents.get(fileName);
        if (content == null) {
            return 0;
        }
        
        return boyerMooreSearch(content, word.toLowerCase());
    }
    
    public Map<String, Integer> countAcrossAllFiles(String word) {
        Map<String, Integer> results = new LinkedHashMap<>();
        
        for (String fileName : fileContents.keySet()) {
            int count = countInFile(fileName, word);
            results.put(fileName, count);
        }
        
        return results;
    }
    
    public int getTotalCount(String word) {
        int total = 0;
        for (int count : countAcrossAllFiles(word).values()) {
            total += count;
        }
        return total;
    }
    
    public void displayFrequencyCount(String word) {
        System.out.println("\n=== Frequency Count for: \"" + word + "\" ===");
        
        Map<String, Integer> fileCounts = countAcrossAllFiles(word);
        int totalCount = 0;
        
        for (Map.Entry<String, Integer> entry : fileCounts.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " occurrence(s)");
            totalCount += entry.getValue();
        }
        
        System.out.println("\nTotal occurrences: " + totalCount);
    }
    
    public static void main(String[] args) {
        System.out.println("=== Task 3: Frequency Count Using Boyer-Moore Algorithm ===\n");
        
        Task3_FrequencyCount frequencyCounter = new Task3_FrequencyCount();
        
        System.out.println("Loading CSV files...");
        frequencyCounter.loadFiles(".");
        
        System.out.println("Files loaded: " + frequencyCounter.fileContents.size());
        
        String[] testWords = {"car", "rental", "budget", "canada", "toronto", "price", "location", "honda"};
        
        System.out.println("\n=== Frequency Count Tests ===");
        for (String word : testWords) {
            frequencyCounter.displayFrequencyCount(word);
        }
        
        System.out.println("\n=== Algorithm Performance Test ===");
        long startTime = System.nanoTime();
        frequencyCounter.getTotalCount("rental");
        long endTime = System.nanoTime();
        System.out.println("Boyer-Moore search time: " + (endTime - startTime) / 1000 + " microseconds");
    }
}
