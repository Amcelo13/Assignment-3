# Task 3: Frequency Count Using Boyer-Moore - Report

## Overview
Counts word occurrences using the Boyer-Moore string matching algorithm.

## Boyer-Moore Algorithm

### Bad Character Heuristic
- Preprocessing: Build bad character table in O(m + σ) where σ = alphabet size
- Search: Skip characters based on last occurrence
- Average case: O(n/m) - sublinear!

### Implementation
```
1. Build bad character table
2. Align pattern with text
3. Compare right to left
4. Skip on mismatch using bad character rule
```

## Performance

| Case | Time Complexity |
|------|----------------|
| Preprocessing | O(m + 256) |
| Best Case | O(n/m) |
| Average Case | O(n/m) |
| Worst Case | O(nm) |

## Features
- Per-file frequency counts
- Total frequency across all files
- Boyer-Moore optimization
- Performance benchmarking

## Test Results
- "car": 776 total occurrences
- "rental": 626 total occurrences
- "budget": 352 total occurrences

## Applications
- Text analysis
- Search engines
- Document processing
- Pattern matching

## Conclusion
Boyer-Moore provides efficient string matching with sublinear average performance.
