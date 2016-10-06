package com.tlherr.Service;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConsoleService {

    public static String NUMBERS_ONLY = "^[0-9]*$";
    public static String CHARACTERS_ONLY = "^[a-zA-Z]*$";

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
    public static String getInput(String instructions, String regex) {

        //Keep this loop running while we are looking for user input. It will keep running until our conditions are
        //satisfied
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
                    System.out.println("Input provided was not as expected, please retry");
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





}
