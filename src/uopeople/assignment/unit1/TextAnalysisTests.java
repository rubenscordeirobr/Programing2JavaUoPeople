package uopeople.assignment.unit1;
/**
 * Comprehensive test suite for the TextAnalysis class.
 * This class leverages modern Java features, such as streams, to validate the
 * behavior and accuracy of text analysis methods. By employing streams,
 * these tests ensure concise and expressive validation logic while maintaining
 * clarity.
 *
 * The tests cover a wide range of functionalities, including:
 * - Character, word, and line counts.
 * - Identification of unique characters and words (case-sensitive and
 * case-insensitive).
 * - Determination of the most frequent characters and words.
 *
 * Each test method implements its validation logic using Java Streams,
 * demonstrating the power and elegance of functional programming paradigms in
 * Java.
 */

public class TextAnalysisTests {

    // Instance of TextAnalysis to test
    private TextAnalysis textAnalysis;

    // Sample text to be analyzed
    private String text;

    public static void main(String[] args) {
        // Run all tests

        System.out.println("Test 1: ");
        TextAnalysisTests testHelloUoPeople = new TextAnalysisTests("Hello uoPeople\nHELLO PEOPLE");
        testHelloUoPeople.test();

        System.out.println("Test 2: ");
        TextAnalysisTests textHello = new TextAnalysisTests("Hello HELLO hello!");
        textHello.test();

        textHello.testGetCharacterFrequency('H', true, 3);
        textHello.testGetCharacterFrequency('H', false, 2);

        System.out.println("Test 3: ");
        TextAnalysisTests textJavaJAVA = new TextAnalysisTests("Java JAVA java programming");
        textJavaJAVA.test();

        textJavaJAVA.testGetWordFrequency("java", true, 3);
        textJavaJAVA.testGetWordFrequency("java", false, 1);

    }

    public TextAnalysisTests(String text) {
        this.text = text;
    }

    /**
     * Executes all test methods.
     */
    public void test() {
        // Initialize the TextAnalysis instance with the test text
        textAnalysis = new TextAnalysis(text);
        textAnalysis.analyze(); // Perform the analysis on the text

        // Run tests for each analysis feature
        testGetCharacterCount();
        testGetWordCount();
        testGetLineCount();
        testGetWhitespaceCount();
        testGetUniqueCharacterCount();
        testGetUniqueIgnoreCharacterCount();
        testGetUniqueWordCount();
        testGetUniqueIgnoreCaseWordCount();
        testMostCharacterFrequency();
        testMostIgnoreCaseCharacterFrequency();
        testMostWordFrequency();
        testMostIgnoreCaseWordFrequency();

    }

    
    /**
     * Tests the `getCharacterFrequency` method of the `TextAnalysis` class.
     * This method verifies whether the frequency count of a specified character,
     * considering case sensitivity based on the `isIgnoreCase` flag, matches the
     * expected value.
     *
     * @param character    the character whose frequency is to be tested
     * @param isIgnoreCase if true, the character frequency is checked
     *                     case-insensitively;
     *                     if false, it is checked case-sensitively
     * @param expected     the expected frequency count of the character
     */
    public void testGetCharacterFrequency(char character, boolean isIgnoreCase, int expected) {
        // Retrieve the actual frequency from the TextAnalysis instance
        int actual = textAnalysis.getCharacterFrequency(character, isIgnoreCase);
        // Assert that the actual frequency matches the expected frequency
        AssertUtil.assertEqual(expected, actual, "getCharacterFrequency");
    }

    /**
     * Tests the `getWordFrequency` method of the `TextAnalysis` class.
     * This method verifies whether the frequency count of a specified word,
     * considering case sensitivity based on the `isIgnoreCase` flag, matches the
     * expected value.
     *
     * @param word         the word whose frequency is to be tested
     * @param isIgnoreCase if true, the word frequency is checked
     *                     case-insensitively;
     *                     if false, it is checked case-sensitively
     * @param expected     the expected frequency count of the word
     */
    public void testGetWordFrequency(String word, boolean isIgnoreCase, int expected) {
        // Retrieve the actual frequency from the TextAnalysis instance
        int actual = textAnalysis.getWordFrequency(word, isIgnoreCase);
        // Assert that the actual frequency matches the expected frequency
        AssertUtil.assertEqual(expected, actual, "getWordFrequency");
    }

    /**
     * Test for the getCharacterCount() method.
     * Verifies the total number of non-whitespace characters in the text.
     * 
     * Stream Explanation:
     * - `chars()`: Converts the string to an IntStream of character codes.
     * - `filter(c -> !Character.isWhitespace(c))`: Excludes whitespace characters.
     * - `count()`: Counts the remaining characters.
     */
    private void testGetCharacterCount() {
        int expected = (int) this.text.chars()
                .filter(c -> !Character.isWhitespace(c))
                .count();
        int actual = this.textAnalysis.getCharacterCount();
        AssertUtil.assertEqual(expected, actual, "getCharacterCount");
    }

    /**
     * Test for the getWordCount() method.
     * Verifies the total number of words in the text.
     * 
     * Explanation:
     * - `split("\\s+")`: Splits the string into words using whitespace as a
     * delimiter.
     * - `length`: Counts the number of resulting elements in the array.
     */
    private void testGetWordCount() {
        int expected = this.text.split("\\s+").length;
        int actual = this.textAnalysis.getWordCount();
        AssertUtil.assertEqual(expected, actual, "getWordCount");
    }

    /**
     * Test for the getLineCount() method.
     * Verifies the total number of lines in the text.
     * 
     * Explanation:
     * - `split("\n")`: Splits the string into lines using the newline character as
     * a delimiter.
     * - `length`: Counts the number of resulting elements in the array.
     */
    private void testGetLineCount() {
        int expected = this.text.split("\n").length;
        int actual = this.textAnalysis.getLineCount();
        AssertUtil.assertEqual(expected, actual, "getLineCount");
    }

    /**
     * Test for the getWhitespaceCount() method.
     * Verifies the total number of whitespace characters (excluding newlines) in
     * the text.
     * 
     * Stream Explanation:
     * - `chars()`: Converts the string to an IntStream of character codes.
     * - `filter(c -> Character.isWhitespace(c) && c != '\n')`: Includes only
     * whitespace characters that are not newlines.
     * - `count()`: Counts the filtered characters.
     */
    private void testGetWhitespaceCount() {
        int expected = (int) this.text.chars()
                .filter(c -> Character.isWhitespace(c) && c != '\n')
                .count();
        int actual = textAnalysis.getWhitespaceCount();
        AssertUtil.assertEqual(expected, actual, "getWhitespaceCount");
    }

    /**
     * Test for the getUniqueCharacterCount() method.
     * Verifies the total number of unique characters (case-sensitive) in the text.
     * 
     * Stream Explanation:
     * - `chars()`: Converts the string to an IntStream of character codes.
     * - `filter(c -> !Character.isWhitespace(c))`: Excludes whitespace characters.
     * - `distinct()`: Keeps only unique characters.
     * - `count()`: Counts the unique characters.
     */
    private void testGetUniqueCharacterCount() {
        int expected = (int) this.text.chars()
                .filter(c -> !Character.isWhitespace(c))
                .distinct()
                .count();
        int actual = textAnalysis.getUniqueCharacterCount();
        AssertUtil.assertEqual(expected, actual, "getUniqueCharacterCount");
    }

    /**
     * Test for the getUniqueIgnoreCaseCharacterCount() method.
     * Verifies the total number of unique characters (case-insensitive) in the
     * text.
     * 
     * Stream Explanation:
     * - `chars()`: Converts the string to an IntStream of character codes.
     * - `filter(c -> !Character.isWhitespace(c))`: Excludes whitespace characters.
     * - `map(Character::toLowerCase)`: Converts all characters to lowercase for
     * case-insensitivity.
     * - `distinct()`: Keeps only unique characters.
     * - `count()`: Counts the unique characters.
     */
    private void testGetUniqueIgnoreCharacterCount() {
        int expected = (int) this.text.chars()
                .filter(c -> !Character.isWhitespace(c))
                .map(Character::toLowerCase)
                .distinct()
                .count();
        int actual = textAnalysis.getUniqueIgnoreCaseCharacterCount();
        AssertUtil.assertEqual(expected, actual, "getUniqueIgnoreCaseCharacterCount");
    }

    /**
     * Test for the getUniqueWordCount() method.
     * Verifies the total number of unique words (case-sensitive) in the text.
     * 
     * Stream Explanation:
     * - `Arrays.stream(split("\\s+"))`: Splits the string into words and creates a
     * stream.
     * - `distinct()`: Filters out duplicate words.
     * - `count()`: Counts the unique words.
     */
    private void testGetUniqueWordCount() {
        int expected = (int) java.util.Arrays.stream(this.text.split("\\s+"))
                .distinct()
                .count();
        int actual = textAnalysis.getUniqueWordCount();
        AssertUtil.assertEqual(expected, actual, "getUniqueWordCount");
    }

    /**
     * Test for the getUniqueIgnoreCaseWordCount() method.
     * Verifies the total number of unique words (case-insensitive) in the text.
     */
    private void testGetUniqueIgnoreCaseWordCount() {
        int expected = (int) java.util.Arrays.stream(this.text.split("\\s+"))
                .map(String::toLowerCase)
                .distinct()
                .count();
        int actual = textAnalysis.getUniqueIgnoreCaseWordCount();
        AssertUtil.assertEqual(expected, actual, "getUniqueIgnoreCaseWordCount");
    }

    /**
     * Test for the getMostCharacterFrequency() method.
     * Verifies the character with the highest frequency (case-sensitive).
     * 
     * Stream Explanation:
     * - `chars().mapToObj(c -> (char) c)`: Converts IntStream to Stream<Character>.
     * - `collect(groupingBy(c -> c, counting()))`: Groups characters and counts
     * their occurrences.
     * - `max(comparingByValue())`: Finds the character with the maximum frequency.
     */
    private void testMostCharacterFrequency() {
        char expected = this.text.chars()
                .mapToObj(c -> (char) c)
                .collect(java.util.stream.Collectors.groupingBy(c -> c, java.util.stream.Collectors.counting()))
                .entrySet()
                .stream()
                .max(java.util.Map.Entry.comparingByValue())
                .get()
                .getKey();
        char actual = textAnalysis.getMostCharacterFrequency();
        AssertUtil.assertEqual(expected, actual, "getMostCharacterFrequency");
    }

    /**
     * Test for the getMostIgnoreCaseCharacterFrequency() method.
     * Verifies the character with the highest frequency (case-insensitive) in the
     * text.
     *
     * Stream Explanation:
     * - `chars()`: Converts the string to an IntStream of character codes.
     * - `mapToObj(c -> (char) c)`: Maps each integer code to its corresponding
     * character, converting to a Stream<Character>.
     * - `collect(groupingBy(c -> Character.toLowerCase(c), counting()))`:
     * - Groups characters by their lowercase representation.
     * - Counts the frequency of each character in the group.
     * - `entrySet().stream()`: Creates a Stream of Map.Entry<Character, Long> for
     * the grouped data.
     * - `max(comparingByValue())`: Finds the entry with the maximum frequency
     * (highest count).
     * - `get().getKey()`: Retrieves the key (character) of the entry with the
     * highest count.
     */
    private void testMostIgnoreCaseCharacterFrequency() {
        char expected = this.text.chars()
                .mapToObj(c -> (char) c)
                .collect(java.util.stream.Collectors.groupingBy(c -> Character.toLowerCase(c),
                        java.util.stream.Collectors.counting()))
                .entrySet()
                .stream()
                .max(java.util.Map.Entry.comparingByValue())
                .get()
                .getKey();
        char actual = textAnalysis.getMostIgnoreCaseCharacterFrequency();
        AssertUtil.assertEqual(expected, actual, "getMostIgnoreCaseCharacterFrequency");
    }

    /**
     * Test for the getMostWordFrequency() method.
     * Verifies the word with the highest frequency (case-sensitive) in the text.
     *
     * Stream Explanation:
     * - `Arrays.stream(split("\\s+"))`:
     * - Splits the string into words using whitespace as a delimiter and creates a
     * Stream<String>.
     * - `collect(groupingBy(w -> w, counting()))`:
     * - Groups words by their exact representation (case-sensitive).
     * - Counts the frequency of each word in the group.
     * - `entrySet().stream()`: Creates a Stream of Map.Entry<String, Long> for the
     * grouped data.
     * - `max(comparingByValue())`: Finds the entry with the maximum frequency
     * (highest count).
     * - `get().getKey()`: Retrieves the key (word) of the entry with the highest
     * count.
     */
    private void testMostWordFrequency() {
        String expected = java.util.Arrays.stream(this.text.split("\\s+"))
                .collect(java.util.stream.Collectors.groupingBy(w -> w, java.util.stream.Collectors.counting()))
                .entrySet()
                .stream()
                .max(java.util.Map.Entry.comparingByValue())
                .get()
                .getKey();
        String actual = textAnalysis.getMostWordFrequency();
        AssertUtil.assertEqual(expected, actual, "getMostWordFrequency");
    }

    /**
     * Test for the getMostIgnoreCaseWordFrequency() method.
     * Verifies the word with the highest frequency (case-insensitive) in the text.
     *
     * Stream Explanation:
     * - `Arrays.stream(split("\\s+"))`:
     * - Splits the string into words using whitespace as a delimiter and creates a
     * Stream<String>.
     * - `collect(groupingBy(w -> w.toLowerCase(), counting()))`:
     * - Groups words by their lowercase representation for case-insensitivity.
     * - Counts the frequency of each word in the group.
     * - `entrySet().stream()`: Creates a Stream of Map.Entry<String, Long> for the
     * grouped data.
     * - `max(comparingByValue())`: Finds the entry with the maximum frequency
     * (highest count).
     * - `get().getKey()`: Retrieves the key (word) of the entry with the highest
     * count.
     */
    private void testMostIgnoreCaseWordFrequency() {
        String expected = java.util.Arrays.stream(this.text.split("\\s+"))
                .collect(java.util.stream.Collectors.groupingBy(w -> w.toLowerCase(),
                        java.util.stream.Collectors.counting()))
                .entrySet()
                .stream()
                .max(java.util.Map.Entry.comparingByValue())
                .get()
                .getKey();
        String actual = textAnalysis.getMostIgnoreCaseWordFrequency();
        AssertUtil.assertEqual(expected, actual, "getMostIgnoreCaseWordFrequency");
    }

}
