package uopeople.assignment.unit6.enums;

/**
 * The EnumDescription interface defines a blueprint for enumerations that
 * provide both a name and a description for each enum constant.
 * <p>
 * This interface is intended to enhance enumerations by adding descriptive
 * information, which can be useful for displaying meaningful data to users.
 * It can be implemented by any enum type that requires a name and description
 * to represent its constants.
 * </p>
 */
public interface EnumDescription {

    /**
     * Retrieves the name of the enum constant.
     *
     * @return a String representing the name of the enum constant.
     */
    String getName();

    /**
     * Retrieves a description of the enum constant.
     *
     * @return a String representing the description of the enum constant.
     */
    String getDescription();
}
