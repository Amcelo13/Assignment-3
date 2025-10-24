# Task 2: Word Completion - Quick Start Guide

## What This Does
Autocomplete words based on prefix - like Google search suggestions!

## How to Run

```bash
javac Task2_WordCompletion.java
java Task2_WordCompletion
```

## Example Output

```
=== Task 2: Word Completion Using Tries ===

Loading vocabulary from CSV files...

=== Statistics ===
Total words in Trie: 788

=== Word Completion Tests ===

Prefix: "car"
Found 15 completion(s):
  1. car
  2. card
  3. cards
  4. care
  5. cargo
  6. carlingview
  ... and 9 more
```

## How It Works
1. Type a prefix (e.g., "car")
2. Trie finds all words starting with that prefix
3. Results returned in alphabetical order

## Quick Test
```bash
javac Task2_WordCompletion.java && java Task2_WordCompletion
```
