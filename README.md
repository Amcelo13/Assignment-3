# Assignment 3 - Advanced Data Structures and Algorithms

This assignment implements 6 different tasks using advanced data structures and string matching algorithms.

## Overview

Each task demonstrates a specific algorithm or data structure:
- **Task 1**: Spell Checking using Tries
- **Task 2**: Word Completion using Tries
- **Task 3**: Frequency Count using Boyer-Moore Algorithm
- **Task 4**: Search Frequency Tracking using KMP Algorithm
- **Task 5**: Page Ranking using Boyer-Moore Algorithm  
- **Task 6**: Inverted Indexing using Tries

## Dataset

The project uses the same 5 CSV files from Assignment 2:
- `swiftride_data 2.csv`
- `prabh.csv`
- `kayak_scraped_data.csv`
- `nikhil.csv`
- `happy.csv`

## Tasks

### Task 1: Spell Checking Using Tries
Implements a spell checker that stores vocabulary in a Trie and suggests alternatives using Edit Distance (Levenshtein Distance) algorithm.

**Features:**
- Trie-based vocabulary storage
- Edit Distance algorithm for word similarity
- Top-N spelling suggestions
- O(m) search time where m = word length

### Task 2: Word Completion Using Tries
Implements autocomplete/word completion functionality using a Trie data structure.

**Features:**
- Prefix-based word search
- Returns all words starting with given prefix
- Sorted completion results
- Efficient O(p + n) where p = prefix length, n = result count

### Task 3: Frequency Count Using Boyer-Moore Algorithm
Counts word occurrences across documents using the Boyer-Moore string matching algorithm.

**Features:**
- Boyer-Moore pattern matching
- Bad character heuristic
- Per-file and total frequency counts
- O(n/m) average case performance

### Task 4: Search Frequency Tracking Using KMP Algorithm
Tracks how often different words are searched using KMP (Knuth-Morris-Pratt) algorithm.

**Features:**
- KMP pattern matching with LPS array
- Search history tracking
- Frequency statistics
- O(n + m) time complexity

### Task 5: Page Ranking Using Boyer-Moore Algorithm
Ranks documents/pages based on keyword frequency using Boyer-Moore algorithm.

**Features:**
- Multi-keyword ranking
- Boyer-Moore for pattern matching
- Scored ranking system
- Detailed keyword breakdown per page

### Task 6: Inverted Indexing Using Tries
Implements an inverted index for fast document retrieval using Tries.

**Features:**
- Trie-based inverted index
- Multi-word AND queries
- Document frequency statistics
- Fast O(m) lookup time

## Quick Start

1. Navigate to the Assignment 3 directory
2. Compile any task: `javac TaskN_*.java`
3. Run the task: `java TaskN_*`

Example:
```bash
cd "Assignment 3"
javac Task1_SpellChecker.java
java Task1_SpellChecker
```

## Individual Task Documentation

Each task has its own detailed documentation:
- `Report_Task1_SpellChecking.md`
- `Report_Task2_WordCompletion.md`
- `Report_Task3_FrequencyCount.md`
- `Report_Task4_SearchFrequency.md`
- `Report_Task5_PageRanking.md`
- `Report_Task6_InvertedIndexing.md`

## Quick Start Guides

Each task has a quick start guide:
- `QUICK_START_Task1.md`
- `QUICK_START_Task2.md`
- `QUICK_START_Task3.md`
- `QUICK_START_Task4.md`
- `QUICK_START_Task5.md`
- `QUICK_START_Task6.md`

## Algorithms Implemented

1. **Trie Data Structure** - Tasks 1, 2, 6
2. **Edit Distance (Levenshtein)** - Task 1
3. **Boyer-Moore String Matching** - Tasks 3, 5
4. **KMP String Matching** - Task 4
5. **Inverted Index** - Task 6

## Time Complexity Summary

| Task | Algorithm | Time Complexity |
|------|-----------|----------------|
| Task 1 | Trie + Edit Distance | O(m) search + O(n×m²) suggestions |
| Task 2 | Trie | O(p + k) where k = results |
| Task 3 | Boyer-Moore | O(n/m) average |
| Task 4 | KMP | O(n + m) |
| Task 5 | Boyer-Moore | O(n/m) average |
| Task 6 | Trie + Inverted Index | O(m) search |

## File Structure

```
Assignment 3/
├── Task1_SpellChecker.java
├── Task2_WordCompletion.java
├── Task3_FrequencyCount.java
├── Task4_SearchFrequency.java
├── Task5_PageRanking.java
├── Task6_InvertedIndexing.java
├── Report_Task1_SpellChecking.md
├── Report_Task2_WordCompletion.md
├── Report_Task3_FrequencyCount.md
├── Report_Task4_SearchFrequency.md
├── Report_Task5_PageRanking.md
├── Report_Task6_InvertedIndexing.md
├── QUICK_START_Task1.md
├── QUICK_START_Task2.md
├── QUICK_START_Task3.md
├── QUICK_START_Task4.md
├── QUICK_START_Task5.md
├── QUICK_START_Task6.md
├── README.md
├── swiftride_data 2.csv
├── prabh.csv
├── kayak_scraped_data.csv
├── nikhil.csv
└── happy.csv
```

## Requirements

- Java 8 or higher
- CSV files in the same directory
- No external dependencies required

## Author

Assignment 3 - Advanced Computing Concepts
University of Windsor
