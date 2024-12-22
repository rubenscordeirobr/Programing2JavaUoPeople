package uopeople.assignment.utils;

/**
 * Provides utility methods for working with enumerations.
 */
public class EnumUtils {

    /**
     * Returns a random value from the specified enumeration.
     * 
     * @param <T>   The type of the enumeration.
     * @param clazz The class of the enumeration.
     * @return A random value from the enumeration.
     */
    public static <T extends Enum<?>> T randomEnum(Class<T> clazz) {
        int x = (int) (Math.random() * clazz.getEnumConstants().length);
        return clazz.getEnumConstants()[x];
    }

}