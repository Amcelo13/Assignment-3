# Task 5: Page Ranking Using Boyer-Moore - Report

## Overview
Ranks documents based on keyword frequency using Boyer-Moore algorithm for pattern matching.

## Page Ranking Algorithm

### Scoring System
- Each keyword occurrence = +1 point
- Multiple keywords: sum all occurrences
- Pages sorted by total score (descending)

### Boyer-Moore for Counting
- Fast pattern matching
- Bad character heuristic
- Sublinear average case

## Implementation

### Features
1. Multi-keyword ranking
2. Per-keyword breakdown
3. Sorted results
4. Score transparency

### Algorithm Flow
```
For each page:
    For each keyword:
        Count occurrences using Boyer-Moore
        Add to page score
    Sort pages by score
```

## Performance

| Operation | Complexity |
|-----------|-----------|
| Single Keyword Count | O(n/m) avg |
| K Keywords, P Pages | O(K × P × n/m) |
| Sorting | O(P log P) |

## Test Results

### Single Keyword: "car"
```
Rank 1: nikhil.csv - Score: 530
Rank 2: prabh.csv - Score: 120
Rank 3: kayak_scraped_data.csv - Score: 96
```

### Multiple Keywords: "car", "rental"
```
Rank 1: nikhil.csv - Score: 1065 (car: 530, rental: 535)
Rank 2: prabh.csv - Score: 120 (car: 120, rental: 0)
```

## Applications
- Search engines (Google, Bing)
- Document relevance ranking
- Content recommendation
- Information retrieval

## Advantages
- Fast keyword counting
- Transparent scoring
- Multi-keyword support
- Scalable to large documents

## Conclusion
Boyer-Moore-based page ranking provides efficient, transparent document scoring for search relevance.
