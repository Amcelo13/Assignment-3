# Task 4: Search Frequency Tracking Using KMP - Report

## Overview
Tracks how often words are searched using KMP (Knuth-Morris-Pratt) algorithm.

## KMP Algorithm

### LPS Array (Longest Proper Prefix which is Suffix)
- Preprocessing: Build LPS array in O(m)
- Avoids re-comparing characters
- No backtracking in text

### Algorithm Steps
```
1. Compute LPS array
2. Scan text left to right
3. Use LPS to skip comparisons
4. Count all matches
```

## Time Complexity

| Operation | Complexity |
|-----------|-----------|
| LPS Computation | O(m) |
| Search | O(n) |
| Total | O(n + m) |

## Features
1. Search frequency tracking with HashMap
2. KMP pattern matching
3. Search history with timestamps
4. Top searched words statistics
5. Performance metrics

## Implementation Highlights

### LPS Array Example
```
Pattern: "ABABC"
LPS:     [0,0,1,2,0]
```

### Search Tracking
- HashMap stores: word → frequency
- List stores: complete search history
- Timestamp for each search

## Test Results
```
"car" - searched 3 times
"rental" - searched 2 times
"budget" - searched 3 times
```

## Applications
- Search analytics
- User behavior tracking
- Popular query identification
- Trend analysis

## Advantages
- O(n + m) guaranteed time
- No backtracking
- Predictable performance
- Memory efficient

## Conclusion
KMP provides linear-time string matching with efficient search frequency tracking.
