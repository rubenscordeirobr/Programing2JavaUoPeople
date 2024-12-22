package uopeople.assignment.utils;

import java.lang.reflect.ParameterizedType;

public class TypeUtil {
    
    public static Class<?> getGenericType(Object obj) {
        //check is has a generic superclass
        ParameterizedType parameterizedType = (ParameterizedType) obj.getClass().getGenericSuperclass();
        if (!(parameterizedType instanceof ParameterizedType)) {
            return null;
        }
 
        return (Class<?>) parameterizedType.getActualTypeArguments()[0];
    }

     
       
}
