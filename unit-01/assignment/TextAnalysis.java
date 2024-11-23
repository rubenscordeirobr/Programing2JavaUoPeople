import java.util.HashMap;

/**
 * The TextAnalysis class analyzes a given text to compute various statistical
 * metrics,
 * such as:
 * - Word and character frequency counts.
 * - Unique word and character counts (case-sensitive and case-insensitive).
 * - Most frequent word and character identification (case-sensitive and
 * case-insensitive).
 *
 * Key Features:
 * - Handles multi-line and whitespace-separated text efficiently.
 * - Differentiates between case-sensitive and case-insensitive analyses for
 * words and characters.
 * - Tracks the number of lines, words, and characters in the provided text.
 *
 * Use Cases:
 * - Analyzing word and character patterns in large text data.
 * - Extracting frequency-based insights from textual information.
 *
 * Implementation Details:
 * - Uses HashMaps to store frequency counts for characters and words.
 * - The `analyze` method is the entry point to process and populate all
 * metrics.
 * - Provides getter methods to retrieve all computed statistics.
 *
 * Dependencies:
 * - Java's built-in HashMap for storing frequency counts.
 * - Character and String utilities for processing the text.
 */
public class TextAnalysis {

    // Maps for word and character frequency
    private HashMap<String, Integer> wordFrequencyMap = new HashMap<>();
    private HashMap<String, Integer> ignoreCaseWordFrequencyMap = new HashMap<>();
    private HashMap<Character, Integer> characterFrequencyMap = new HashMap<>();
    private HashMap<Character, Integer> ignoreCaseCharacterFrequencyMap = new HashMap<>();

    // The text to analyze
    private String text;

    // Statistical counters
    private int lineCount;
    private int characterCount;
    private int whitespaceCount;
    private int wordCount;

    // Unique counts
    private int uniqueWordCount;
    private int uniqueCharacterCount;
    private int uniqueIgnoreCaseWordCount;
    private int uniqueIgnoreCaseCharacterCount;

    // Most frequent elements
    private Character mostCharacterFrequency;
    private Character mostIgnoreCaseCharacterFrequency;
    private String mostWordFrequency;
    private String mostIgnoreCaseWordFrequency;

    // Counts of most frequent elements
    private int mostCharacterFrequencyCount;
    private int mostIgnoreCaseCharacterFrequencyCount;
    private int mostWordFrequencyCount;
    private int mostIgnoreCaseWordFrequencyCount;

    /**
     * Constructs a TextAnalysis object for the given text.
     *
     * @param text the text to analyze
     * @throws IllegalArgumentException if the text is null or empty
     */
    public TextAnalysis(String text) {

        if (text == null || text.isEmpty()) {
            throw new IllegalArgumentException("Text cannot be null or empty");
        }
        this.text = text;
    }

    /**
     * Analyzes the provided text to compute various statistical metrics.
     *
     * This method processes the text character by character to:
     * - Count lines, words, and characters.
     * - Track unique words and characters (case-sensitive and case-insensitive).
     * - Identify the most frequent words and characters (case-sensitive and
     * case-insensitive).
     *
     * Workflow:
     * 1. Iterates through the text to categorize each character.
     * 2. Maintains frequency counts using HashMaps for case-sensitive and
     * case-insensitive contexts.
     * 3. Calculates additional metrics like the most frequent words/characters.
     *
     * Results:
     * - Updates the internal fields and maps for all metrics.
     * - Fields are accessible via getter methods after this method is executed.
     */
    public void analyze() {

        StringBuilder currentWord = new StringBuilder(); // Temporary storage for constructing words
        this.lineCount = 1; // Initialize line count to account for the first line

        // Process each character in the text
        for (char c : text.toCharArray()) {
            if (!Character.isWhitespace(c)) {
                // If the character is not whitespace, append it to the current word
                currentWord.append(c);
                updateCaracterFrequency(c); // Update character frequency maps
            } else {
                processWord(currentWord);
                currentWord = new StringBuilder();

                // Increment line or whitespace count
                if (c == '\n') {
                    this.lineCount++;
                } else {
                    this.whitespaceCount++;
                }
            }
        }

        // Add the last word if present
        processWord(currentWord);

        // Update unique counts
        this.uniqueCharacterCount = characterFrequencyMap.size();
        this.uniqueIgnoreCaseCharacterCount = ignoreCaseCharacterFrequencyMap.size();
        this.uniqueWordCount = wordFrequencyMap.size();
        this.uniqueIgnoreCaseWordCount = ignoreCaseWordFrequencyMap.size();

        // Determine most frequent elements
        this.mostCharacterFrequency = getMostFrequencyInternal(characterFrequencyMap);
        this.mostIgnoreCaseCharacterFrequency = getMostFrequencyInternal(ignoreCaseCharacterFrequencyMap);
        this.mostWordFrequency = getMostFrequencyInternal(wordFrequencyMap);
        this.mostIgnoreCaseWordFrequency = getMostFrequencyInternal(ignoreCaseWordFrequencyMap);

        // Get counts for most frequent elements
        this.mostCharacterFrequencyCount = characterFrequencyMap.getOrDefault(mostCharacterFrequency, 0);
        this.mostIgnoreCaseCharacterFrequencyCount = ignoreCaseCharacterFrequencyMap
                .getOrDefault(mostIgnoreCaseCharacterFrequency, 0);
        this.mostWordFrequencyCount = wordFrequencyMap.getOrDefault(mostWordFrequency, 0);
        this.mostIgnoreCaseWordFrequencyCount = ignoreCaseWordFrequencyMap.getOrDefault(mostIgnoreCaseWordFrequency, 0);
    }

    /**
     * Updates frequency maps for a given character.
     *
     * @param c the character to be to the frequency maps
     *
     *          Logic:
     *          - Updates the case-sensitive character frequency map.
     *          - Updates the case-insensitive frequency map by converting the
     *          character to lowercase.
     *          - Increments the total character count.
     */
    private void updateCaracterFrequency(char c) {
        // Case-sensitive count
        characterFrequencyMap.put(c, characterFrequencyMap.getOrDefault(c, 0) + 1);
        characterCount++;

        // Case-insensitive count
        char lowerCaseChar = Character.toLowerCase(c);
        ignoreCaseCharacterFrequencyMap.put(lowerCaseChar,
                ignoreCaseCharacterFrequencyMap.getOrDefault(lowerCaseChar, 0) + 1);
    }

    /**
     * Processes a word if it is valid (non-empty) by adding it to the frequency
     * maps.
     *
     * @param currentWord the word being processed
     */
    private void processWord(StringBuilder currentWord) {
        if (currentWord.length() > 0) {
            String word = currentWord.toString();// Convert StringBuilder to a String
            updateWordFrequency(word);// Update word frequency maps
        }
    }

    /**
     * Updates frequency maps for a given word.
     *
     * @param word the word to be added to the frequency maps
     *
     *             Logic:
     *             - Updates the case-sensitive word frequency map.
     *             - Updates the case-insensitive frequency map by converting the
     *             word to lowercase.
     *             - Increments the total word count.
     */
    private void updateWordFrequency(String word) {
        // Case-sensitive count
        wordFrequencyMap.put(word, wordFrequencyMap.getOrDefault(word, 0) + 1);
        wordCount++;

        // Case-insensitive count
        String lowerCaseWord = word.toLowerCase();
        ignoreCaseWordFrequencyMap.put(lowerCaseWord, ignoreCaseWordFrequencyMap.getOrDefault(lowerCaseWord, 0) + 1);
    }

    /**
     * Finds the key with the highest frequency in a given frequency map.
     *
     * @param <TKey>       the type of keys in the frequency map (e.g., Character or
     *                     String)
     * @param frequencyMap the map containing elements and their frequencies
     * @return the key with the highest frequency, or null if the map is empty
     *
     *         Logic:
     *         - Iterates through the frequency map to identify the key with the
     *         maximum frequency.
     *         - Handles both characters and words, depending on the provided map.
     */
    private <TKey> TKey getMostFrequencyInternal(HashMap<TKey, Integer> frequencyMap) {
        int maxFrequency = 0;
        TKey mostFrequentKey = null;

        for (TKey key : frequencyMap.keySet()) {
            int frequency = frequencyMap.get(key);
            if (frequency > maxFrequency) {
                maxFrequency = frequency;
                mostFrequentKey = key;
            }
        }
        return mostFrequentKey;
    }

    // Public getter methods for accessing the analysis results

    /**
     * Gets the total number of characters in the analyzed text.
     *
     * @return the total character count
     */
    public int getCharacterCount() {
        return this.characterCount;
    }

    /**
     * Gets the total number of lines in the analyzed text.
     *
     * @return the total line count
     */
    public int getLineCount() {
        return this.lineCount;
    }

    /**
     * Gets the total number of whitespace characters in the analyzed text.
     *
     * @return the total whitespace count
     */
    public int getWhitespaceCount() {
        return this.whitespaceCount;
    }

    /**
     * Gets the total number of words in the analyzed text.
     *
     * @return the total word count
     */
    public int getWordCount() {
        return this.wordCount;
    }

    /**
     * Gets the number of unique words in the analyzed text.
     *
     * @return the unique word count
     */
    public int getUniqueWordCount() {
        return this.uniqueWordCount;
    }

    /**
     * Gets the number of unique characters in the analyzed text.
     *
     * @return the unique character count
     */
    public int getUniqueCharacterCount() {
        return this.uniqueCharacterCount;
    }

    /**
     * Gets the number of unique words in the analyzed text, ignoring case.
     *
     * @return the unique case-insensitive word count
     */
    public int getUniqueIgnoreCaseWordCount() {
        return this.uniqueIgnoreCaseWordCount;
    }

    /**
     * Gets the number of unique characters in the analyzed text, ignoring case.
     *
     * @return the unique case-insensitive character count
     */
    public int getUniqueIgnoreCaseCharacterCount() {
        return this.uniqueIgnoreCaseCharacterCount;
    }

    /**
     * Gets the most frequent character in the analyzed text.
     *
     * @return the character with the highest frequency
     */
    public Character getMostCharacterFrequency() {
        return this.mostCharacterFrequency;
    }

    /**
     * Gets the most frequent character in the analyzed text, ignoring case.
     *
     * @return the case-insensitive character with the highest frequency
     */
    public Character getMostIgnoreCaseCharacterFrequency() {
        return this.mostIgnoreCaseCharacterFrequency;
    }

    /**
     * Gets the most frequent word in the analyzed text.
     *
     * @return the word with the highest frequency
     */
    public String getMostWordFrequency() {
        return this.mostWordFrequency;
    }

    /**
     * Gets the most frequent word in the analyzed text, ignoring case.
     *
     * @return the case-insensitive word with the highest frequency
     */
    public String getMostIgnoreCaseWordFrequency() {
        return this.mostIgnoreCaseWordFrequency;
    }

    /**
     * Gets the frequency count of the most frequent character in the analyzed text.
     *
     * @return the count of the most frequent character
     */
    public int getMostCharacterFrequencyCount() {
        return this.mostCharacterFrequencyCount;
    }

    /**
     * Gets the frequency count of the most frequent character in the analyzed text,
     * ignoring case.
     *
     * @return the count of the most frequent case-insensitive character
     */
    public int getMostIgnoreCaseCharacterFrequencyCount() {
        return this.mostIgnoreCaseCharacterFrequencyCount;
    }

    /**
     * Gets the frequency count of the most frequent word in the analyzed text.
     *
     * @return the count of the most frequent word
     */
    public int getMostWordFrequencyCount() {
        return this.mostWordFrequencyCount;
    }

    /**
     * Gets the frequency count of the most frequent word in the analyzed text,
     * ignoring case.
     *
     * @return the count of the most frequent case-insensitive word
     */
    public int getMostIgnoreCaseWordFrequencyCount() {
        return this.mostIgnoreCaseWordFrequencyCount;
    }

    /**
     * Retrieves the frequency count of a specified character from the frequency
     * maps.
     *
     * @param c            the character whose frequency is to be retrieved.
     * @param isIgnoreCase whether the frequency should be retrieved
     *                     case-insensitively.
     *                     - If true, the case-insensitive frequency is returned.
     *                     - If false, the case-sensitive frequency is returned.
     * @return the frequency count of the specified character. If the character does
     *         not exist
     *         in the corresponding map, returns 0.
     */
    public int getCharacterFrequency(Character c, boolean isIgnoreCase) {
        if (isIgnoreCase) {
            // Convert to lowercase for case-insensitive lookup
            return ignoreCaseCharacterFrequencyMap.getOrDefault(Character.toLowerCase(c), 0);
        } else {
            // Retrieve the frequency directly for case-sensitive lookup
            return characterFrequencyMap.getOrDefault(c, 0);
        }
    }

    /**
     * Retrieves the frequency count of a specified word from the frequency maps.
     *
     * @param word         the word whose frequency is to be retrieved.
     * @param isIgnoreCase whether the frequency should be retrieved
     *                     case-insensitively.
     *                     - If true, the case-insensitive frequency is returned.
     *                     - If false, the case-sensitive frequency is returned.
     * @return the frequency count of the specified word. If the word does not exist
     *         in the corresponding map, returns 0.
     */
    public int getWordFrequency(String word, boolean isIgnoreCase) {
        if (isIgnoreCase) {
            // Convert to lowercase for case-insensitive lookup
            return ignoreCaseWordFrequencyMap.getOrDefault(word.toLowerCase(), 0);
        } else {
            // Retrieve the frequency directly for case-sensitive lookup
            return wordFrequencyMap.getOrDefault(word, 0);
        }
    }

}
