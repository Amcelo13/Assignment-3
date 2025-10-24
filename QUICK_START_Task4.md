# Task 4: Search Frequency Tracking - Quick Start Guide

## What This Does
Tracks how often you search for words and counts their occurrences using KMP algorithm.

## How to Run

```bash
javac Task4_SearchFrequency.java
java Task4_SearchFrequency
```

## Example Output

```
=== Task 4: Search Frequency Tracking Using KMP Algorithm ===

Loading CSV files...
Files loaded: 5

=== Performing Searches ===

=== Search Result for: "car" ===
Occurrences in documents: 776
Search frequency: 1

=== Search Result for: "car" ===
Occurrences in documents: 776
Search frequency: 2

=== Search Frequency Statistics ===
Total unique searches: 8
Total search operations: 14

Top 10 Most Searched Words:
1. "car" - searched 3 time(s)
2. "budget" - searched 3 time(s)
3. "rental" - searched 2 time(s)

=== Algorithm Performance Test ===
KMP search time: 150 microseconds
```

## How It Works
1. KMP builds LPS (failure function) array
2. Searches without backtracking
3. Tracks every search in HashMap
4. Shows statistics

## Quick Test
```bash
javac Task4_SearchFrequency.java && java Task4_SearchFrequency
```
