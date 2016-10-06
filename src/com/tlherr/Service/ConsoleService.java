package com.tlherr.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConsoleService {

    public static String CHARACTERS_ONLY = "^[a-zA-Z]*$";
    public static String ALPHANUMERIC_WORDS = "([a-zA-Z0-9 .,])+";
    public static String PHONE_NUMBER = "\\b\\d{3}[-.]?\\d{3}[-.]?\\d{4}\\b";
    public static String GENDER = "([M|m|F|f]{1})";
    public static String EMAIL_BASIC = ".+\\@.+\\..+";

    /**
     * Given a hashmap (data structure for menu options
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
     * This method will create a scanner and get user input until it matches specified conditions.
     * It is up to other methods to type check, this method will only run a regex check
     * @return String text the user has entered.
     */
    public static String getStringInput(String instructions, String regex, String hint) {
        while(true) {
            System.out.println(instructions);
            Scanner scanner = new Scanner(System.in);
            try {
                //Get some value from the user
                String input = scanner.nextLine();

                Pattern p = Pattern.compile(regex);
                Matcher m = p.matcher(input);

                //Check if this value matches our regex
                if(m.matches()) {
                    return input;
                } else {
                    System.out.println("Input provided was not as expected, please retry. Expecting: "+hint);Fixe
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

    public static Integer getIntegerInput(String instructions, String hint) {
        while(true) {
            System.out.println(instructions);
            Scanner scanner = new Scanner(System.in);
            try {
                //Get some value from the user
                String input = scanner.nextLine();

                Integer result = Integer.parseInt(input);

                //Check if this value matches our regex
                if(result>0) {
                    return result;
                } else {
                    System.out.println("Input provided was not as expected, please retry. Expecting: "+hint);


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

    public static Float getFloatInput(String instructions, String hint) {
        while(true) {
            System.out.println(instructions);
            Scanner scanner = new Scanner(System.in);
            try {
                //Get some value from the user
                String input = scanner.nextLine();
                return Float.parseFloat(input);
            } catch(InputMismatchException | IllegalStateException | NumberFormatException | NullPointerException ex) {
                System.out.println(
                        "Input Error (Expecting format similar to: "+hint+"), please retry"
                );
            }
        }
    }


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
                        "Input Error (Expecting format: "+hint+"), please retry"
                );
            }
        }

    }




}
