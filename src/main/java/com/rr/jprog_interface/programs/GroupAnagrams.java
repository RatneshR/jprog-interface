package com.rr.jprog_interface.programs;

import java.util.*;

public class GroupAnagrams {
    /**
     * SOLUTION 1: Using Sorted Strings as Hash Keys
     *
     * Core Insight: All anagrams have the same letters, so when sorted,
     * they become identical strings.
     *
     * Example:
     * - "eat" sorted -> "aet"
     * - "tea" sorted -> "aet"
     * - "ate" sorted -> "aet"
     *
     * Time Complexity: O(n * k log k)
     *   - n: number of words
     *   - k: maximum length of a word
     *   - Sorting each word: O(k log k)
     *
     * Space Complexity: O(n * k)
     */
    public static List<List<String>> groupAnagramsWithSorting(String[] words) {
        // Hashmap to store groups of anagrams
        // Key: sorted version of the word
        // Value: list of original words that are anagrams

        Map<String, List<String>> anagramGroups = new HashMap<>();

        for (String word : words) {
            // STEP 1 : Convert word to character array
            char[] characters = word.toCharArray();

            // STEP 2 : Sort the characters
            Arrays.sort(characters);

            // STEP 3 : Convert back to string to use as key
            String sortedKey = new String(characters);

            // STEP 4 : Add word to the corresponding group
            // Using computeIfAbsent for concise code
            anagramGroups.computeIfAbsent(sortedKey, key -> new ArrayList<>()).add(word);
        }

        return new ArrayList<>(anagramGroups.values());
    }

    /**
     * SOLUTION 2: Using Character Frequency Count
     *
     * Core Insight: All anagrams have the same character frequency count.
     * We can use this frequency pattern as a unique key.
     *
     * Better for very long strings where sorting becomes expensive.
     *
     * Time Complexity: O(n * k)
     *   - n: number of words
     *   - k: maximum length of a word
     *   - Counting characters: O(k) per word
     *
     * Space Complexity: O(n * k)
     */
    public static List<List<String>> groupAnagramsWithFrequency(String[] words) {
        Map<String, List<String>> anagramGroups = new HashMap<>();

        for (String word : words) {
            String frequencyKey = buildCharacterFrequencyKey(word);
            anagramGroups.computeIfAbsent(frequencyKey, key -> new ArrayList<>()).add(word);
        }

        return new ArrayList<>(anagramGroups.values());
    }

    /**
     * Builds a unique key based on character frequency.
     * Example: "eat" -> "a1e1t1", "tea" -> "a1e1t1"
     */
    private static String buildCharacterFrequencyKey(String word) {
        // Array to count occurrences of each character
        // Index 0 = 'a', ...., Index 25 = 'z'
        int[] characterCount = new int[26];

        // Count each character in the word
        for (char character : word.toCharArray()) {
            // Convert character to array index: 'a' -> 0, 'b' -> 1, etc.
            int index = character - 'a';
            characterCount[index]++;
        }

        // Build the key string
        StringBuilder keyBuilder = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            if (characterCount[i] > 0) {
                // Append character and its count
                char currentChar = (char) ('a' + i);
                keyBuilder.append(currentChar).append(characterCount[i]);
            }
        }

        return keyBuilder.toString();
    }
}
