# Task 6: Inverted Indexing - Quick Start Guide

## What This Does
Creates a search index like Google - find which documents contain specific words instantly!

## How to Run

```bash
javac Task6_InvertedIndexing.java
java Task6_InvertedIndexing
```

## Example Output

```
=== Task 6: Inverted Indexing Using Tries ===

Loading and indexing documents...
Building inverted index...
Indexing complete!

=== Inverted Index Statistics ===
Total documents indexed: 5
Total unique words indexed: 788

Top 10 Most Common Words (by document count):
  1. "car" - appears in 5 document(s)
  2. "rental" - appears in 4 document(s)
  3. "canada" - appears in 4 document(s)

=== Single Word Searches ===

=== Search Results for: "car" ===
Found in 5 document(s):
  - nikhil.csv (530 occurrence(s))
  - prabh.csv (120 occurrence(s))
  - kayak_scraped_data.csv (96 occurrence(s))
  - happy.csv (45 occurrence(s))
  - swiftride_data 2.csv (4 occurrence(s))

=== Multi-Word Searches ===

=== Search Results for: "car" AND "rental" ===
Found in 2 document(s):
  - nikhil.csv ("car": 530, "rental": 535)
  - kayak_scraped_data.csv ("car": 96, "rental": 66)

=== Performance Test ===
Inverted index search time: 45 microseconds
```

## How It Works
1. **Indexing**: Reads all documents, builds Trie
2. **Single Word**: Finds all documents with that word
3. **Multi-Word**: Finds documents with ALL words (AND operation)
4. **Results**: Sorted by frequency

## Features
- ⚡ Lightning fast O(L) lookup
- 🔍 Single and multi-word search
- 📊 Frequency counting
- 📈 Statistics

## Quick Test
```bash
javac Task6_InvertedIndexing.java && java Task6_InvertedIndexing
```

## Cool Fact
This is how search engines like Google work at their core!
