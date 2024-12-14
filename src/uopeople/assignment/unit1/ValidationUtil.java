package uopeople.assignment.unit1;
/**
 * Utility class for validation-related methods.
 * Provides methods to validate words and characters based on specific rules.
 */
public class ValidationUtil {

    /**
     * Checks if a given word is invalid.
     * A word is considered invalid if:
     * - It is null.
     * - It is empty.
     * - It contains any whitespace characters.
     *
     * @param word the word to validate
     * @return true if the word is invalid, false otherwise
     */
    public static boolean isInvalidWord(String word) {
        // Check for null, empty string, or the presence of whitespace characters
        return word == null ||
                word.isEmpty() ||
                word.chars().anyMatch(c -> Character.isWhitespace(c));
    }

    /**
     * Checks if a given character is invalid.
     * A character is considered invalid if:
     * - It is not a letter.
     * - It is not a digit.
     *
     * @param ch the character to validate
     * @return true if the character is invalid, false otherwise
     */
    public static boolean isInvalidCharacter(char ch) {
        // Check if the character is neither a letter nor a digit
        return !Character.isLetter(ch) &&
                !Character.isDigit(ch);
    }
}
