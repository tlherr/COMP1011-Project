package com.tlherr.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Service providing helper methods for validating/checking user input into application
 */
public class InputService {

    public static String CHARACTERS_ONLY = "^[a-zA-Z]*$";
    public static String DECIMAL = "[0-9]+([,.][0-9]{1,2})?";
    public static String NUMERIC_ONLY = "^[0-9]*$";
    public static String ALPHANUMERIC_WORDS = "([a-zA-Z0-9 .,])+";
    public static String PHONE_NUMBER = "\\b\\d{3}[-.]?\\d{3}[-.]?\\d{4}\\b";
    public static String GENDER = "([M|m|F|f]{1})";
    public static String EMAIL_BASIC = ".+\\@.+\\..+";

    /**
     * This method will create a scanner and get user input until it matches specified conditions.
     * It is up to other methods to type check, this method will only run a regex check
     * @return String text the user has entered.
     */
    public static Boolean validate(String input, String regex) {

            if(input.length()==0) {
                return false;
            }

            try {
                Pattern p = Pattern.compile(regex);
                Matcher m = p.matcher(input);
                return m.matches();
            } catch(InputMismatchException | IllegalStateException | NumberFormatException ex) {
                return false;
            }
    }

    public static Boolean validate(Integer input, String regex) {
        try {
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(String.valueOf(input));
            return m.matches();
        } catch(InputMismatchException | IllegalStateException | NumberFormatException ex) {
            return false;
        }

    }

    public static Boolean validate(Float input, String regex) {
        try {
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(String.valueOf(input));
            return m.matches();
        } catch(InputMismatchException | IllegalStateException | NumberFormatException ex) {
            return false;
        }

    }

    public static Boolean validate(Date input, String regex) {
        try {
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(input.toString());
            return m.matches();
        } catch(InputMismatchException | IllegalStateException | NumberFormatException ex) {
            return false;
        }

    }
}
