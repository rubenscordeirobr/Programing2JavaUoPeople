 package uopeople.assignment.unit7.utils;

 /**
  * The InputUtil class provides utility methods for capturing and validating
  * user input, including generic handling for enums with descriptions.
  */
 public class InputUtil {
 
     private static final String[] QUIT_OPTIONS = { "q", "quit", "exit" };
   
     /**
      * Prompts the user with a message and retrieves a string input from the
      * console. If the user enters 'q' or 'Q', the program exits. If the input is blank,
      * the user is prompted again.
      *
      * @param message the message to display to the user
      * @return the validated user input
      */
     public static String getInputFromUser(String message) {
 
         PrintUtil.printInputUserMessage(message);
         String input = System.console().readLine();
 
         // Check for program exit input
 
         if (input == null) {
             PrintUtil.printFailMessage("Invalid input. Please try again.");
             return getInputFromUser(message); // Retry for valid input
         }
 
         // Check for program exit input
         if (isInputExit(input)) {
             System.out.println("Thank you for using the program.");
             System.exit(0);
         }
 
         if ("q".equalsIgnoreCase(input) ||
                 "quit".equalsIgnoreCase(input) ||
                 "exit".equalsIgnoreCase(input)) {
             System.out.println("Exiting the program.");
             System.exit(0);
         }
 
         // Validate input is not blank
         if (input.isBlank()) {
             PrintUtil.printFailMessage("Invalid input. Please try again.");
             return getInputFromUser(message); // Retry for valid input
         }
         return input;
     }
 
     /**
      * Prompts the user with a message and retrieves an integer input from the
      * console. Validates that the input is a number and falls within the specified range.
      *
      * @param message the message to display to the user
      * @return the validated integer input
      */
     public static int getIntInputFromUser(String message) {
         return getIntInputFromUser(message, 0, Integer.MAX_VALUE); // Default range
     }
 
     /**
      * Prompts the user with a message and retrieves an integer input within a
      * specified range. If the input is invalid (non-numeric or out of range), the user is prompted
      * again.
      *
      * @param message  the message to display to the user
      * @param minValue the minimum valid value
      * @param maxValue the maximum valid value
      * @return the validated integer input
      */
     public static int getIntInputFromUser(String message, int minValue, int maxValue) {
         String input = getInputFromUser(message);
 
         try {
             int number = Integer.parseInt(input);
 
             // Validate range
             if (number < minValue || number > maxValue) {
                 PrintUtil.printFailMessage(
                         "Invalid input. Please enter a number between " + minValue + " and " + maxValue + ".");
                 return getIntInputFromUser(message, minValue, maxValue); // Retry for valid range
             }
             return number;
         } catch (NumberFormatException e) {
             PrintUtil.printFailMessage("Invalid input. Please enter a valid number.");
             return getIntInputFromUser(message, minValue, maxValue); // Retry for numeric input
         }
     }
 
     /**
      * Prompts the user to select an option from an enum type that implements
      * {@link EnumDescription}. The method displays all enum constants with their descriptions
      * and allows the user to select one.
      *
      * @param <T>      the enum type that implements EnumDescription
      * @param enumType the class of the enum to display
      * @param message  a prompt message to display to the user
      * @return the selected enum value
      */
     public static <T extends Enum<T> & EnumDescription> T getEnumSelectionFromUser(Class<T> enumType, String message) {
         // Get all enum constants
         T[] enumConstants = enumType.getEnumConstants();
 
         // Display each enum constant with its description
         for (int i = 0; i < enumConstants.length; i++) {
             System.out.println((i + 1) + ". " + enumConstants[i].getName() + " - " + enumConstants[i].getDescription());
         }
 
         // Prompt user for selection
         int selectedValue = InputUtil.getIntInputFromUser(message, 1, enumConstants.length);
 
         // Return the selected enum value
         return enumConstants[selectedValue - 1];
     }
 
     /**
      * Checks if the given input matches any of the quit options.
      *
      * @param input the input to check
      * @return true if the input matches a quit option, false otherwise
      */
     private static boolean isInputExit(String input) {
         for (String option : QUIT_OPTIONS) {
             if (option.equalsIgnoreCase(input)) {
                 return true;
             }
         }
         return false;
     }
 }
 