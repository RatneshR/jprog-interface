package com.rr.jprog_interface.programs;

import java.util.HashMap;
import java.util.Map;
import java.lang.Character;

public class LongestSubstringLength {
    public int lengthOfLongestSubstring(String s) {
        int left = 0;
        int max = 0;

        // Stores the last seen index of each character
        var lastIndex = new HashMap<Character, Integer>();

        // Iterate through the string with the right pointer
        for (int right = 0; right < s.length(); right++) {
            char currentChar = s.charAt(right);

            // If the character has been seen and is within the current window
            if(lastIndex.containsKey(currentChar) && lastIndex.get(currentChar) >= left) {
                // Move the left pointer to one position after the last seen index
                left = lastIndex.get(currentChar) + 1;
            }

            lastIndex.put(currentChar, right);

            max = Math.max(max, right - left + 1);
        }

        return max;
    }
}
