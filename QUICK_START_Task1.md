# Task 1: Spell Checking - Quick Start Guide

## What This Does
Checks if words are spelled correctly and suggests alternatives if they're not.

## How to Run

### Step 1: Navigate to Directory
```bash
cd "Assignment 3"
```

### Step 2: Compile
```bash
javac Task1_SpellChecker.java
```

### Step 3: Run
```bash
java Task1_SpellChecker
```

## Expected Output

```
=== Task 1: Spell Checking Using Tries ===

Loading vocabulary from CSV files...

=== Spell Checker Statistics ===
Total words in vocabulary: 788

=== Spell Checking Tests ===

Checking word: "rental"
✓ Word exists in vocabulary

Checking word: "car"
✓ Word exists in vocabulary

Checking word: "rentl"
✗ Word NOT found in vocabulary

Suggested alternatives:
  - rental (Edit Distance: 1)
  - rent (Edit Distance: 1)
```

## How It Works

1. **Loads vocabulary** from 5 CSV files into a Trie
2. **Checks each word** in the Trie (O(m) time)
3. **If misspelled**, calculates Edit Distance to all words
4. **Returns top suggestions** sorted by similarity

## Key Features

- ✓ Fast word lookup using Trie
- ✓ Intelligent suggestions using Edit Distance
- ✓ Handles common typos
- ✓ Sorted by similarity

## Common Use Cases

- Check spelling: `"rental"` → ✓ Found
- Fix typo: `"rentl"` → Suggests "rental"
- Find alternatives: `"budjet"` → Suggests "budget"

## Customization

To check your own words, modify the `testWords` array in `main()`:

```java
String[] testWords = {
    "your",
    "custom",
    "words"
};
```

## Performance

- **Vocabulary Size**: ~788 unique words
- **Search Time**: < 1 microsecond per word
- **Suggestion Time**: ~10-50 milliseconds (depends on vocabulary size)

## Troubleshooting

**Problem**: "File not found"
**Solution**: Make sure CSV files are in the same directory

**Problem**: No suggestions appear
**Solution**: Try increasing the `maxDistance` parameter

## Quick Test
```bash
javac Task1_SpellChecker.java && java Task1_SpellChecker
```

## Done!
You should see spell checking results for all test words.
