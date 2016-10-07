package com.tlherr.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Console service is in charge of getting/validating input from the user
 * String inputs are validated using regex checks
 * Integers are validated to be positive
 * Floats are validated by parsing
 * Dates are validated by non lenient dateformatter parsing
 *
 * If exceptions are thrown during validating the user is notified, given a hint of what is expected
 * and given a chance to reenter a value
 */
public class ConsoleService {

    /**
     * Matches only upper/lower case characters
     * Used mostly for names
     */
    public static String CHARACTERS_ONLY = "^[a-zA-Z]*$";
    /**
     * Matches only upper/lower case characters as well as numeric digits including commas and periods
     * Used for addresses ex (123 Fake Street RR#1 Barrie ON OHO2HO)
     */
    public static String ALPHANUMERIC_WORDS = "([a-zA-Z0-9 .,#])+";
    /**
     * Matches only on the following format 111-111-1111 or 111.111.1111
     * Used exclusively for phone numbers
     */
    public static String PHONE_NUMBER = "\\b\\d{3}[-.]?\\d{3}[-.]?\\d{4}\\b";
    /**
     * Matches only on upper/lower case M or F
     * Used exclusively for gender description
     */
    public static String GENDER = "([M|m|F|f]{1})";
    /**
     * Matches any characters + @ symbol + any characters + period + any characters
     * ex. test@test.com   3243243@234234.com
     * Used exclusively for email addresses
     */
    public static String EMAIL_BASIC = ".+\\@.+\\..+";



    public static String DEFAULT_HINT_DATE = "2002-02-20";

    public static String DEFAULT_HINT_FLOAT = "203.50";


    /**
     * Given a hashmap (data structure for menu options) generate a regex expression that will only match when one of the
     * keys from the map is used. Example if our keys are 1,2,3 the following will be generated "^[1|2|3]{1}$"
     * @param map Map of options (key value pair)
     * @return String regular expression that matches only the given keys from the hashmap
     */
    public static String generateRegex(HashMap<Integer, String> map) {
        String regex = "^[";
        Iterator it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();

            if (!it.hasNext()) {
                regex+=pair.getKey();
            } else {
                regex+=pair.getKey()+"|";
            }
        }
        regex+="]{1}$";

        return regex;
    }

    /**
     * Get a string from a user that matches a specified pattern
     * If the pattern does not match inform the user, provide a hint of what is expected and reask for input
     *
     * @param instructions String text telling the user what to enter
     * @param regex String regular expression to validate that the user entered correctly
     * @param hint String example data to show the user what we are expecting
     * @return String text the user has entered.
     */
    public static String getStringInput(String instructions, String regex, String hint) {
        while(true) {
            System.out.println(instructions);
            Scanner scanner = new Scanner(System.in);
            try {
                String input = scanner.nextLine();

                Pattern p = Pattern.compile(regex);
                Matcher m = p.matcher(input);

                //Check if this input matches our regex
                if(m.matches()) {
                    return input;
                } else {
                    System.out.println("Input provided was not as expected, please retry. Expecting format similar to: "
                            +hint);
                }
            } catch(InputMismatchException | IllegalStateException | NumberFormatException ex) {
                System.out.println(
                        "Input Error ("+
                        ex.getMessage()!=null?ex.getMessage():"No Further Details"+
                        "), please retry"
                );
            }
        }
    }

    /**
     * Get an integer from the user
     * Ensure this value is non zero or positive
     *
     * @param instructions String text telling the user what to enter
     * @param hint String example data to show the user what we are expecting
     * @return Integer number that the user entered
     */
    public static Integer getIntegerInput(String instructions, String hint) {
        while(true) {
            System.out.println(instructions);
            Scanner scanner = new Scanner(System.in);
            try {
                String input = scanner.nextLine();
                Integer result = Integer.parseInt(input);

                if(result>0) {
                    return result;
                } else {
                    System.out.println("Input provided was not as expected, please retry. Expecting format similar to: "
                            +hint);
                }
            } catch(InputMismatchException | IllegalStateException | NumberFormatException ex) {
                System.out.println(
                        "Input Error ("+
                                ex.getMessage()!=null?ex.getMessage():"No Further Details"+
                                "), please retry"
                );
            }
        }
    }

    /**
     * Get a float from the user
     * Any valid float will be accepted
     *
     * @param instructions String text telling the user what to enter
     * @param hint String example data to show the user what we are expecting
     * @return Float number that the user entered
     */
    public static Float getFloatInput(String instructions, String hint) {
        while(true) {
            System.out.println(instructions);
            Scanner scanner = new Scanner(System.in);
            try {
                String input = scanner.nextLine();
                return Float.parseFloat(input);
            } catch(InputMismatchException | IllegalStateException | NumberFormatException | NullPointerException ex) {
                System.out.println(
                        "Input Error (Expecting format similar to: "+hint+"), please retry"
                );
            }
        }
    }

    /**
     * Get a date from the user
     * Expecting a date in the form yyyy-mm-dd
     * @param instructions String text telling the user what to enter
     * @param hint String example data to show the user what we are expecting
     * @return Date date that the user entered
     */
    public static Date getDateInput(String instructions, String hint) {
        System.out.println(instructions);
        Scanner scanner = new Scanner(System.in);

        while(true) {
            try {
                String input = scanner.nextLine();

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                dateFormat.setLenient(false);
                return dateFormat.parse(input);

            } catch(InputMismatchException | IllegalStateException | NumberFormatException | ParseException ex) {
                System.out.println(
                        "Input Error (Expecting format similar to: "+hint+"), please retry"
                );
            }
        }
    }
}
