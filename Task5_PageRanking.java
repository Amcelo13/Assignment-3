import java.util.*;
import java.io.*;

/**
 * Task 5: Page Ranking Using Frequency Count and Boyer-Moore Algorithm
 * Assignment 3
 */
public class Task5_PageRanking {
    
    private Map<String, String> pages;
    
    class PageScore implements Comparable<PageScore> {
        String pageName;
        int score;
        Map<String, Integer> keywordCounts;
        
        PageScore(String pageName, int score) {
            this.pageName = pageName;
            this.score = score;
            this.keywordCounts = new HashMap<>();
        }
        
        @Override
        public int compareTo(PageScore other) {
            return Integer.compare(other.score, this.score);
        }
    }
    
    public Task5_PageRanking() {
        pages = new LinkedHashMap<>();
    }
    
    public void loadPages(String basePath) {
        String[] csvFiles = {"swiftride_data 2.csv", "prabh.csv", "kayak_scraped_data.csv", "nikhil.csv", "happy.csv"};
        
        for (String fileName : csvFiles) {
            String filePath = basePath + "/" + fileName;
            StringBuilder content = new StringBuilder();
            
            try (Scanner scanner = new Scanner(new File(filePath))) {
                while (scanner.hasNextLine()) {
                    content.append(scanner.nextLine().toLowerCase()).append(" ");
                }
                pages.put(fileName, content.toString());
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
        
        if (m == 0 || n == 0 || m > n) return 0;
        
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
    
    public List<PageScore> rankPages(String... keywords) {
        List<PageScore> rankings = new ArrayList<>();
        
        for (Map.Entry<String, String> page : pages.entrySet()) {
            String pageName = page.getKey();
            String content = page.getValue();
            int totalScore = 0;
            
            PageScore pageScore = new PageScore(pageName, 0);
            
            for (String keyword : keywords) {
                String normalizedKeyword = keyword.toLowerCase();
                int count = boyerMooreSearch(content, normalizedKeyword);
                totalScore += count;
                pageScore.keywordCounts.put(keyword, count);
            }
            
            pageScore.score = totalScore;
            rankings.add(pageScore);
        }
        
        Collections.sort(rankings);
        return rankings;
    }
    
    public void displayRankings(String... keywords) {
        System.out.println("\n=== Page Ranking Results ===");
        System.out.print("Keywords: ");
        for (int i = 0; i < keywords.length; i++) {
            System.out.print("\"" + keywords[i] + "\"");
            if (i < keywords.length - 1) System.out.print(", ");
        }
        System.out.println("\n");
        
        List<PageScore> rankings = rankPages(keywords);
        
        int rank = 1;
        for (PageScore pageScore : rankings) {
            System.out.println("Rank " + rank + ": " + pageScore.pageName);
            System.out.println("  Total Score: " + pageScore.score);
            System.out.println("  Keyword breakdown:");
            for (Map.Entry<String, Integer> entry : pageScore.keywordCounts.entrySet()) {
                System.out.println("    - \"" + entry.getKey() + "\": " + entry.getValue() + " occurrence(s)");
            }
            System.out.println();
            rank++;
        }
    }
    
    public void displayStatistics() {
        System.out.println("\n=== Page Ranking Statistics ===");
        System.out.println("Total pages indexed: " + pages.size());
        
        int totalContent = 0;
        for (String content : pages.values()) {
            totalContent += content.length();
        }
        System.out.println("Total content size: " + totalContent + " characters");
    }
    
    public static void main(String[] args) {
        System.out.println("=== Task 5: Page Ranking Using Boyer-Moore Algorithm ===\n");
        
        Task5_PageRanking pageRanking = new Task5_PageRanking();
        
        System.out.println("Loading pages (CSV files)...");
        pageRanking.loadPages(".");
        pageRanking.displayStatistics();
        
        System.out.println("\n=== Test 1: Single Keyword ===");
        pageRanking.displayRankings("car");
        
        System.out.println("\n=== Test 2: Multiple Keywords ===");
        pageRanking.displayRankings("car", "rental");
        
        System.out.println("\n=== Test 3: Location-based Keywords ===");
        pageRanking.displayRankings("toronto", "canada");
        
        System.out.println("\n=== Test 4: Brand Keywords ===");
        pageRanking.displayRankings("budget", "honda");
        
        System.out.println("\n=== Test 5: Complex Query ===");
        pageRanking.displayRankings("car", "rental", "price", "location");
    }
}
