# Task 3: Frequency Count - Quick Start Guide

## What This Does
Counts how many times a word appears in all documents using fast Boyer-Moore algorithm.

## How to Run

```bash
javac Task3_FrequencyCount.java
java Task3_FrequencyCount
```

## Example Output

```
=== Task 3: Frequency Count Using Boyer-Moore Algorithm ===

Loading CSV files...
Files loaded: 5

=== Frequency Count Tests ===

=== Frequency Count for: "car" ===
swiftride_data 2.csv: 45 occurrence(s)
prabh.csv: 120 occurrence(s)
kayak_scraped_data.csv: 96 occurrence(s)
nikhil.csv: 530 occurrence(s)
happy.csv: 45 occurrence(s)

Total occurrences: 776

=== Algorithm Performance Test ===
Boyer-Moore search time: 250 microseconds
```

## How It Works
1. Boyer-Moore builds a "skip table"
2. Searches right-to-left for efficiency
3. Skips characters intelligently
4. Counts all occurrences

## Performance
- **Average Case**: Faster than naive search
- **Skip Distance**: Up to pattern length
- **Best For**: Long patterns

## Quick Test
```bash
javac Task3_FrequencyCount.java && java Task3_FrequencyCount
```
