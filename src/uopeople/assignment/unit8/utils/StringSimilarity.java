package uopeople.assignment.unit8.utils;

 
/**
 * The StringSimilarity class provides utility methods to calculate the similarity
 * between two strings based on the Levenshtein Edit Distance. It is useful for tasks
 * such as fuzzy matching or finding approximate string matches.
 * 
 * Responsibilities:
 * - Compute the similarity score between two strings (0 to 1).
 * 
 * References:
 * - Levenshtein Distance: https://en.wikipedia.org/wiki/Levenshtein_distance
 * - Example implementation: http://rosettacode.org/wiki/Levenshtein_distance#Java
 * 
 */

public class StringSimilarity {
    /**
     * Calculates the similarity (a number within 0 and 1) between two strings.
     */
    public static double similarity(String s1, String s2) {

        s1 = s1.trim().toLowerCase();
        s2 = s2.trim().toLowerCase();

        String longer = s1, shorter = s2;
        if (s1.length() < s2.length()) { 
            longer = s2;
            shorter = s1;
        }

        int longerLength = longer.length();
        if (longerLength == 0) {
            return 1.0;
        }
        return (longerLength - editDistance(longer, shorter)) / (double) longerLength;

    }

     /**
     * Calculates the Levenshtein Edit Distance between two strings.
     * The edit distance is the minimum number of edits (insertions, deletions, or substitutions)
     * required to transform one string into another.
     * 
     * @param s1 the first string.
     * @param s2 the second string.
     * @return the edit distance as an integer.
     * 
     * References:
     * - Implementation adapted from: http://rosettacode.org/wiki/Levenshtein_distance#Java
     */
    private static int editDistance(String s1, String s2) {

        int[] costs = new int[s2.length() + 1];
        for (int i = 0; i <= s1.length(); i++) {
            int lastValue = i;
            for (int j = 0; j <= s2.length(); j++) {
                if (i == 0)
                    costs[j] = j;
                else {
                    if (j > 0) {
                        int newValue = costs[j - 1];
                        if (s1.charAt(i - 1) != s2.charAt(j - 1))
                            newValue = Math.min(Math.min(newValue, lastValue),
                                    costs[j]) + 1;
                        costs[j - 1] = lastValue;
                        lastValue = newValue;
                    }
                }
            }
            if (i > 0)
                costs[s2.length()] = lastValue;
        }
        return costs[s2.length()];
    }
}
