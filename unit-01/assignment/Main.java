/**
 * The Main class serves as the entry point for the TextAnalysis application.
 * It provides a user interface to input an essay, perform text analysis,
 * and interactively display analysis results based on user-selected options.
 */
public class Main {

    // Static instance of TextAnalysis to perform operations on the user-provided
    // essay text
    private static TextAnalysis textAnalysis;

    /**
     * The main method serves as the entry point for the application.
     * It initializes the TextAnalysis object and displays the menu for user
     * interaction.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {

        // Get the essay text from the user
        String essayText = getEssayTextFromUser();
        // Create an instance of the TextAnalysis class
        textAnalysis = new TextAnalysis(essayText);

        // Perform the analysis on the essay text
        textAnalysis.analyze();

        // Display the menu for user interaction
        showMenu();
    }

    /**
     * Displays the analysis menu to the user and processes their selections.
     * The menu allows the user to view various statistics about the analyzed text
     * or search for specific word or character frequencies.
     */
    public static void showMenu() {

        while (true) {
            // Display menu options
            System.out.println("\nPlease select an option from the menu for analysis:");
            System.out.println("0. Exit");
            System.out.println("1. Character Count");
            System.out.println("2. Word Count");
            System.out.println("3. Unique Words Count");
            System.out.println("4. Unique Characters Count");
            System.out.println("5. Most Frequent Character");
            System.out.println("6. Most Frequent Word");
            System.out.println("7. Search Word Frequency");
            System.out.println("8. Search Character Frequency");

            // Get user input for menu selection
            int option = getInputFromUser("Please enter an option: ");

            // Process the selected option
            switch (option) {
                case 0:
                    System.out.println("Exiting the program. Goodbye!");
                    return; // Exit the program
                case 1:
                    System.out.println("Total Characters: " + textAnalysis.getCharacterCount());
                    break;
                case 2:
                    System.out.println("Total Words: " + textAnalysis.getWordCount());
                    break;
                case 3:
                    System.out.println("Unique Words Count: " + textAnalysis.getUniqueWordCount());
                    System.out.println("Unique Words (ignore-case): " + textAnalysis.getUniqueIgnoreCaseWordCount());
                    break;
                case 4:
                    System.out.println("Unique Characters Count: " + textAnalysis.getUniqueCharacterCount());
                    System.out.println(
                            "Unique Characters (ignore-case): " + textAnalysis.getUniqueIgnoreCaseCharacterCount());
                    break;
                case 5:
                    System.out.println("Most Frequent Character: " + textAnalysis.getMostCharacterFrequency()
                            + " (Count: " + textAnalysis.getMostCharacterFrequencyCount() + ")");
                    System.out.println("Most Frequent Character (ignore-case): "
                            + textAnalysis.getMostIgnoreCaseCharacterFrequency()
                            + " (Count: " + textAnalysis.getMostIgnoreCaseCharacterFrequencyCount() + ")");
                    break;
                case 6:
                    System.out.println("Most Frequent Word: " + textAnalysis.getMostWordFrequency()
                            + " (Count: " + textAnalysis.getMostWordFrequencyCount() + ")");
                    System.out.println(
                            "Most Frequent Word (ignore-case): " + textAnalysis.getMostIgnoreCaseWordFrequency()
                                    + " (Count: " + textAnalysis.getMostIgnoreCaseWordFrequencyCount() + ")");
                    break;
                case 7:
                    searchWordFrequency();
                    break;
                case 8:
                    searchCharacterFrequency();
                    break;
                default:
                    PrintUtil.printFailMessage("Invalid option. Please select a valid option from the menu.");
                    break;
            }
        }
    }

    /**
     * Prompts the user to input the five paragraphs of an essay and concatenates
     * them.
     *
     * @return the concatenated essay text
     */
    public static String getEssayTextFromUser() {

        System.out.println("Please enter the five essay paragraph: ");

        String introduction = getTextFromUser("Please enter the introduction paragraph: ");
        String body1 = getTextFromUser("Please enter the body1 paragraph: ");
        String body2 = getTextFromUser("Please enter the body2 paragraph: ");
        String body3 = getTextFromUser("Please enter the body3 paragraph: ");
        String conclusion = getTextFromUser("Please enter the conclusion paragraph: ");

        // Concatenate all the paragraphs into a single string
        StringBuilder sb = new StringBuilder();
        sb.append(introduction).append("\n")
                .append(body1).append("\n")
                .append(body2).append("\n")
                .append(body3).append("\n")
                .append(conclusion);

        return sb.toString();
    }

    /**
     * Prompts the user to enter a paragraph and validates the input.
     *
     * @param message the prompt message to display to the user
     * @return a valid paragraph entered by the user
     */
    public static String getTextFromUser(String message) {

        System.out.println(message);
        String text = System.console().readLine();

        if (text == null || text.isEmpty() || text.length() < 10) {
            PrintUtil.printFailMessage(
                    "Invalid input. Please enter a paragraph at least 10 characters.");
            return getTextFromUser(message);
        }
        return text;
    }

    /**
     * Prompts the user to enter an integer value and handles invalid inputs.
     *
     * @param message the prompt message to display to the user
     * @return a valid integer input from the user
     */
    private static int getInputFromUser(String message) {
        int option = 0;
        try {
            System.out.println(message);
            option = Integer.parseInt(System.console().readLine());
        } catch (NumberFormatException e) {
            PrintUtil.printFailMessage("Invalid input. Please enter a number.");
            return getInputFromUser(message);
        }
        return option;
    }

    /**
     * Allows the user to search for a word's frequency in the analyzed text.
     * The method prompts the user for a word and displays its frequency,
     * both case-sensitive and ignore-case.
     */
    private static void searchWordFrequency() {
        // Consume the leftover newline
        System.out.print("Enter the word to search: ");
        String word = System.console().readLine();

        if (ValidationUtil.isInvalidWord(word)) {
            PrintUtil.printFailMessage("Invalid input. Please enter a valid word. Don't use spaces.");
            return;
        }

        int frequencyCaseSensitive = textAnalysis.getWordFrequency(word, false);
        int frequencyIgnoreCase = textAnalysis.getWordFrequency(word, true);

        System.out.println("Frequency of \"" + word + "\": " + frequencyCaseSensitive);
        System.out.println("Frequency of \"" + word + "\" (ignore-case): " + frequencyIgnoreCase);
    }

    /**
     * Allows the user to search for a character's frequency in the analyzed text.
     * The method prompts the user for a character and displays its frequency,
     * both case-sensitive and ignore-case.
     */
    public static void searchCharacterFrequency() {

        System.out.print("Enter the character to search: ");
        char ch = System.console().readLine().charAt(0);

        if (ValidationUtil.isInvalidCharacter(ch)) {
            PrintUtil.printFailMessage("Invalid input. Please enter a valid character.");
            return;
        }

        int frequencyCaseSensitive = textAnalysis.getCharacterFrequency(ch, false);
        int frequencyIgnoreCase = textAnalysis.getCharacterFrequency(ch, true);

        System.out.println("Frequency of '" + ch + "': " + frequencyCaseSensitive);
        System.out.println("Frequency of '" + ch + "' (ignore-case): " + frequencyIgnoreCase);
    }

}
