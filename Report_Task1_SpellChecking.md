# Task 1: Spell Checking Using Tries - Report

## Overview
This task implements a spell checker that uses a Trie data structure to store vocabulary and provides word suggestions using the Edit Distance (Levenshtein Distance) algorithm.

## Implementation Details

### Data Structures Used

#### 1. Trie (Prefix Tree)
- **Purpose**: Efficient storage and retrieval of vocabulary words
- **Structure**: Tree where each node represents a character
- **Advantages**:
  - O(m) time complexity for search (m = word length)
  - Space-efficient for large vocabularies with common prefixes
  - Fast prefix-based operations

#### 2. HashSet
- **Purpose**: Store complete vocabulary for Edit Distance comparisons
- **Advantages**: O(1) average lookup time

### Algorithms

#### 1. Trie Insert
```
Time Complexity: O(m) where m = word length
Space Complexity: O(m) per word
```

#### 2. Trie Search
```
Time Complexity: O(m) where m = word length
Space Complexity: O(1)
```

#### 3. Edit Distance (Levenshtein Distance)
- **Purpose**: Calculate similarity between two words
- **Method**: Dynamic Programming
- **Operations counted**:
  - Insertion
  - Deletion
  - Substitution

```
Time Complexity: O(m × n) where m, n are word lengths
Space Complexity: O(m × n) for DP table
```

**Formula**:
```
dp[i][j] = min(
    dp[i-1][j] + 1,      // deletion
    dp[i][j-1] + 1,      // insertion
    dp[i-1][j-1] + cost  // substitution (cost = 0 if match, 1 otherwise)
)
```

### Features Implemented

1. **Vocabulary Loading**
   - Reads from 5 CSV files
   - Cleans and normalizes words
   - Filters words with length > 2
   - Stores in Trie and HashSet

2. **Spell Checking**
   - Checks if word exists in Trie
   - Returns boolean result
   - O(m) lookup time

3. **Word Suggestions**
   - Calculates Edit Distance for all vocabulary words
   - Returns top-N suggestions sorted by similarity
   - Configurable maximum distance threshold

### Performance Analysis

| Operation | Time Complexity | Space Complexity |
|-----------|----------------|------------------|
| Insert Word | O(m) | O(m) |
| Search Word | O(m) | O(1) |
| Edit Distance | O(m × n) | O(m × n) |
| Get Suggestions | O(V × m × n) | O(S) |

Where:
- m, n = word lengths
- V = vocabulary size
- S = number of suggestions

### Test Results

The program tests with various words including:
- Correctly spelled words: "rental", "car", "toronto", "canada", "location"
- Misspelled words: "rentl", "budjet", "vehicl", "pric"

**Example Output**:
```
Checking word: "rentl"
✗ Word NOT found in vocabulary

Suggested alternatives:
  - rental (Edit Distance: 1)
  - rent (Edit Distance: 1)
  - rentals (Edit Distance: 2)
```

### Advantages

1. **Fast Lookups**: O(m) trie search
2. **Accurate Suggestions**: Edit Distance provides good similarity metric
3. **Scalable**: Efficient for large vocabularies
4. **Flexible**: Adjustable distance threshold

### Limitations

1. **Suggestion Generation**: O(V) iterations through vocabulary
2. **Memory Usage**: Stores full vocabulary in memory
3. **Edit Distance Cost**: O(m × n) for each comparison

### Possible Improvements

1. **BK-Tree**: Faster suggestion generation using metric tree
2. **Trie Pruning**: Limit Edit Distance calculations using trie structure
3. **Caching**: Store frequent Edit Distance calculations
4. **N-gram Index**: Additional fuzzy matching capability
5. **Phonetic Algorithms**: Soundex/Metaphone for sound-alike suggestions

### Real-World Applications

1. **Text Editors**: Word processors, IDEs
2. **Search Engines**: Query spell correction
3. **Mobile Keyboards**: Autocorrect functionality
4. **Form Validation**: User input verification
5. **Data Cleaning**: Standardizing text data

## Conclusion

The Trie-based spell checker provides efficient word validation and meaningful suggestions through Edit Distance calculation. The implementation balances performance and accuracy, making it suitable for real-world applications with moderate vocabulary sizes.

## Files
- `Task1_SpellChecker.java` - Main implementation
- `QUICK_START_Task1.md` - Quick start guide
