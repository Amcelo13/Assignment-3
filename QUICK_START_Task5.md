# Task 5: Page Ranking - Quick Start Guide

## What This Does
Ranks pages/documents based on how many times keywords appear - like a mini search engine!

## How to Run

```bash
javac Task5_PageRanking.java
java Task5_PageRanking
```

## Example Output

```
=== Task 5: Page Ranking Using Boyer-Moore Algorithm ===

Loading pages (CSV files)...

=== Page Ranking Statistics ===
Total pages indexed: 5
Total content size: 234567 characters

=== Test 2: Multiple Keywords ===

=== Page Ranking Results ===
Keywords: "car", "rental"

Rank 1: nikhil.csv
  Total Score: 1065
  Keyword breakdown:
    - "car": 530 occurrence(s)
    - "rental": 535 occurrence(s)

Rank 2: prabh.csv
  Total Score: 120
  Keyword breakdown:
    - "car": 120 occurrence(s)
    - "rental": 0 occurrence(s)
```

## How It Works
1. Enter keywords (e.g., "car", "rental")
2. Boyer-Moore counts occurrences in each page
3. Pages ranked by total keyword count
4. Results shown with breakdown

## Use Cases
- Find most relevant document
- Multi-keyword search
- Content analysis

## Quick Test
```bash
javac Task5_PageRanking.java && java Task5_PageRanking
```
