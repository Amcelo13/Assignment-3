# Task 6: Inverted Indexing Using Tries - Report

## Overview
Implements an inverted index using Tries for fast multi-document word search.

## Inverted Index Concept

### What is an Inverted Index?
```
Word → Set of Documents containing that word

Example:
"car" → {nikhil.csv, prabh.csv, happy.csv}
"rental" → {nikhil.csv, kayak_scraped_data.csv}
```

## Implementation

### Data Structures
1. **Trie**: Stores words with document references
2. **HashMap**: Maps word → document set
3. **Document Map**: Maps filename → content

### Trie Node Structure
```java
class TrieNode {
    Map<Character, TrieNode> children;
    boolean isEndOfWord;
    Set<String> documentSet;  // Documents containing this word
}
```

## Algorithm

### Indexing Phase
```
For each document:
    For each word in document:
        Insert word into Trie
        Add document to word's document set
Time: O(W × L) where W = words, L = avg length
```

### Search Phase
```
1. Navigate Trie to word
2. Return document set
Time: O(L) where L = word length
```

### Multi-Word Search (AND operation)
```
1. Get document sets for each word
2. Find intersection of all sets
3. Return common documents
Time: O(K × L + D) where K = keywords, D = documents
```

## Performance

| Operation | Time Complexity |
|-----------|----------------|
| Index Document | O(W × L) |
| Search Single Word | O(L) |
| Search K Words | O(K × L + D) |
| Count Frequency | O(W) |

## Features
1. Fast O(L) word lookup
2. Multi-word AND queries
3. Document frequency statistics
4. Per-document word counts

## Test Results

### Single Word Search: "car"
```
Found in 5 document(s):
  - nikhil.csv (530 occurrences)
  - prabh.csv (120 occurrences)
  - kayak_scraped_data.csv (96 occurrences)
  - happy.csv (45 occurrences)
  - swiftride_data 2.csv (4 occurrences)
```

### Multi-Word Search: "car" AND "rental"
```
Found in 2 document(s):
  - nikhil.csv ("car": 530, "rental": 535)
  - kayak_scraped_data.csv ("car": 96, "rental": 66)
```

## Applications
- Search engines (Google, Elasticsearch)
- Database indexing
- Document retrieval systems
- Log analysis tools

## Advantages
1. **Fast Lookup**: O(L) search time
2. **Space Efficient**: Trie compression
3. **Multi-Query Support**: AND/OR operations
4. **Scalable**: Efficient for large document sets

## Comparison with Other Approaches

| Method | Search Time | Space | Multi-Word |
|--------|------------|-------|-----------|
| Linear Scan | O(D × W) | O(1) | Slow |
| HashMap Only | O(1) | O(V × D) | Manual |
| **Trie Index** | **O(L)** | **O(V × L)** | **Fast** |

## Conclusion
Trie-based inverted indexing provides the fastest word lookup with efficient multi-word query support, making it ideal for search engines and document retrieval systems.
