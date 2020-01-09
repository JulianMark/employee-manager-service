package com.employee.manager.Utils;

import org.springframework.stereotype.Component;

@Component
public class Utils {

    public static void validateRequest (Object request){
        if (request == null)
            throw new IllegalArgumentException("The post method body cannot be null");
    }

    public static void validateNotNullOrEmpty (String prop){
        if (prop == null)
            throw new IllegalArgumentException("Body property cannot be null");

        if (prop.isEmpty())
            throw new IllegalArgumentException("Body property cannot be empty");
    }

    public static void validateIdNumber (Integer numberId){
        if (numberId == null){
            throw new IllegalArgumentException("The id number cannot be null");
        }
        if (numberId <= 0){
            throw new IllegalArgumentException("The id number cannot be equal to or less than zero");
        }
    }

}
