package com.rr.jprog_interface.programs;

public class MinimumWindowSubstring {
    public String minWindow(String s, String t) {
            if (s == null || t == null || s.length() < t.length()) return "";

            // Frequency map for required chars
            int[] need = new int[128];
            for (char c : t.toCharArray()) {
                need[c]++;
            }

            int required = t.length();
            int left = 0, minLen = Integer.MAX_VALUE, start = 0;

            for (int right = 0; right < s.length(); right++) {
                char rc = s.charAt(right);

                // If this char is needed, reduce required count
                if (need[rc] > 0) {
                    required--;
                }

                // Decrease count in need[] for the window
                need[rc]--;

                // When all characters are satisfied
                while (required == 0) {
                    if (right - left + 1 < minLen) {
                        minLen = right - left + 1;
                        start = left;
                    }

                    // Try to shrink from left
                    char lc = s.charAt(left);
                    need[lc]++;

                    // If this char was required, window became invalid
                    if (need[lc] > 0) {
                        required++;
                    }

                    left++;
                }
            }

            return minLen == Integer.MAX_VALUE ? "" : s.substring(start, start + minLen);
        }
}
