package edu.pdx.cs410J.shraddha;

import edu.pdx.cs410J.ParserException;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * The main class for the CS410J airline Project
 *
 */
public class Project2 {
    private static Airline currentAir = new Airline();
    private static Flight currentFlight = new Flight();
    private static int n = 0;
    private static String fileName;
    private static boolean fileRead;
    private static boolean fileWrite;
    private static Airline oldAirline;


    /** @param arg[0] - The airline name
     * @param args[1] - flight number is alphanumeric
     * @param args[2] - Checking if length is three and all characters are letters
     * @param args[3] - Date in format MM/DD/YYYY
     * @param args[4] - Time in format HH:MM
     * @param args[5] - Checking if length is three and all characters are letters
     * @param args[6] - Date in format MM/DD/YYYY
     * @param args[7] - Time in format HH:MM
     * **/
    /**
     * @param args has all command line arguments given by user
     *
     * @throws IllegalArgumentException is thrown when there are not enough arguments or the wrong arguments
     ***/
    public static void main(String[] args) throws ParserException, IOException {


        try {
            if(args.length>0) {
                for (int i = 0; i < args.length; i++) {
                    if (args[i].equals("-README")) {
                        readMe();
                        System.exit(0);
                    }
                }
            }

            switch (args.length) {
                case 0:
                    throw new IllegalArgumentException("Missing command line arguments");
                case 1:
                    for (int i = 0; i < 1; i++) {
                        if (args[i].equals("-README")) {
                            readMe();
                            System.exit(0);
                        }
                    }
                    if (args[0].equals("-print")) {
                        throw new IllegalArgumentException("ERROR: Missing Arguments");
                    } else if (args[0].equals("-textFile")) {
                        throw new IllegalArgumentException("ERROR: Missing Arguments");
                    } else
                        throw new IllegalArgumentException("ERROR: Missing Flight Number");

                case 2://0,1
                    for (int i = 0; i < 2; i++) {
                        if (args[i].equals("-README")) {
                            readMe();
                            System.exit(0);
                        }
                    }
                    if (args[0].equals("-print")) {
                        throw new IllegalArgumentException("ERROR: Missing Arguments");
                    } else if (args[0].equals("-textFile")) {
                        throw new IllegalArgumentException("ERROR: Missing Command line arguments");
                    } else throw new IllegalArgumentException("ERROR: Missing Departure Airport Code");


                case 3: //0,1,2
                    for (int i = 0; i < 3; i++) {
                        if (args[i].equals("-README")) {
                            readMe();
                            System.exit(0);
                        }
                    }
                    if (args[0].equals("-print") && args[1].equals("-textFile")) {
                        throw new IllegalArgumentException("ERROR: Missing Arguments");
                    } else if(args[0].equals("-textFile") && args[2].equals("-print")){
                        throw new IllegalArgumentException("ERROR: Missing Arguments");
                    } else if (args[0].equals("-print")) {
                        throw new IllegalArgumentException("ERROR: Missing Arguments");
                    } else if (args[0].equals("-textFile")) {
                        throw new IllegalArgumentException("ERROR: Missing Arguments");
                    }  else throw new IllegalArgumentException("ERROR: Missing Departure Date");


                case 4: //0,1,2,3
                    for (int i = 0; i < 4; i++) {
                        if (args[i].equals("-README")) {
                            readMe();
                            System.exit(0);
                        }
                    }
                    if (args[0].equals("-print") && args[1].equals("-textFile")) {
                        throw new IllegalArgumentException("ERROR: Missing Arguments");
                    } else if(args[0].equals("-textFile") && args[2].equals("-print")){
                        throw new IllegalArgumentException("ERROR: Missing Arguments");
                    } else if (args[0].equals("-print")) {
                        throw new IllegalArgumentException("ERROR: Missing Arguments");
                    } else if (args[0].equals("-textFile")) {
                        throw new IllegalArgumentException("ERROR: Missing Arguments");
                    }  else
                        throw new IllegalArgumentException("ERROR: Missing Departure Time");


                case 5:
                    if (args[0].equals("-print") && args[1].equals("-textFile")) {
                        throw new IllegalArgumentException("ERROR: Missing Arguments");
                    } else if(args[0].equals("-textFile") && args[2].equals("-print")){
                        throw new IllegalArgumentException("ERROR: Missing Arguments");
                    } else if (args[0].equals("-print")) {
                        throw new IllegalArgumentException("ERROR: Missing Arguments");
                    } else if (args[0].equals("-textFile")) {
                        throw new IllegalArgumentException("ERROR: Missing Arguments");
                    }  else throw new IllegalArgumentException("ERROR: Missing Arrival Airport Code");

                case 6:

                    if (args[0].equals("-print") && args[1].equals("-textFile")) {
                        throw new IllegalArgumentException("ERROR: Missing Arguments");
                    } else if(args[0].equals("-textFile") && args[2].equals("-print")){
                        throw new IllegalArgumentException("ERROR: Missing Arguments");
                    } else if (args[0].equals("-print")) {
                        throw new IllegalArgumentException("ERROR: Missing Arguments");
                    } else if (args[0].equals("-textFile")) {
                        throw new IllegalArgumentException("ERROR: Missing Arguments");
                    }  else
                        throw new IllegalArgumentException("ERROR: Missing Arrival Date");

                case 7:
                    if (args[0].equals("-print") && args[1].equals("-textFile")) {
                        throw new IllegalArgumentException("ERROR: Missing Arguments");
                    } else if(args[0].equals("-textFile") && args[2].equals("-print")){
                        throw new IllegalArgumentException("ERROR: Missing Arguments");
                    } else if (args[0].equals("-textFile")) {
                        throw new IllegalArgumentException("ERROR: Missing Arguments");
                    } else if (args[0].equals("-print")) {
                        throw new IllegalArgumentException("ERROR: Missing Arguments");
                    }  else
                        throw new IllegalArgumentException("ERROR: Missing Arrival Time");

                case 8:

                    if (args[0].equals("-print") && args[1].equals("-textFile")) {
                        throw new IllegalArgumentException("ERROR: Missing Arguments");
                    } else if(args[0].equals("-textFile") && args[2].equals("-print")){
                        throw new IllegalArgumentException("ERROR: Missing Arguments");
                    } else if (args[0].equals("-textFile")) {
                        throw new IllegalArgumentException("ERROR: Missing Arguments");
                     } else if (args[0].equals("-print")) {
                        throw new IllegalArgumentException("ERROR: Missing Arrival Time");
                    }

                case 9:

                    if (args[0].equals("-print") && args[1].equals("-textFile")) {
                        throw new IllegalArgumentException("ERROR: Missing Arguments");
                    } else if(args[0].equals("-textFile") && args[2].equals("-print")){
                        throw new IllegalArgumentException("ERROR: Missing Arguments");
                    } else if (args[0].equals("-textFile")) {
                         throw new IllegalArgumentException("ERROR: Missing Arrival Time");
                    }

                case 10:

                    if (args[0].equals("-print") && args[1].equals("-textFile")) {
                        throw new IllegalArgumentException("ERROR: Missing Arguments");
                    } else if(args[0].equals("-textFile") && args[2].equals("-print")){
                        throw new IllegalArgumentException("ERROR: Missing Arguments");
                    }

                default:
                    if (args[0].startsWith("-")) {
                        if (args[0].equals("-print") && (!(args[1].equals("-textFile")))) {
                            if (args.length > 9) {
                                throw new IllegalArgumentException("ERROR: Extra Invalid Argument");
                            }
                            n = 1;
                        } else if (args[0].equals("-textFile") && (!(args[2].equals("-print")))) {
                            if (args.length > 10) {
                                throw new IllegalArgumentException("ERROR: Extra Invalid Argument");
                            }
                            n = 2;
                            fileWrite = true;
                            fileRead = false;
                            fileName = args[1];
                        } else if (args[0].equals("-print") && args[1].equals("-textFile")){
                            if (args.length > 11) {
                                throw new IllegalArgumentException("ERROR: Extra Invalid Argument");
                            }
                            n=3;
                            fileRead = true;
                            fileWrite=true;
                            fileName=args[2];
                        } else if(args[0].equals("-textFile") && args[2].equals("-print")){
                            if (args.length > 11) {
                                throw new IllegalArgumentException("ERROR: Extra Invalid Argument");
                            }
                            n=3;
                            fileRead = true;
                            fileWrite=true;
                            fileName=args[1];
                        }
                        else
                            throw new IllegalArgumentException("ERROR: Invalid Command line Option");
                    } else if (args.length > 8) {
                        throw new IllegalArgumentException("ERROR: Extra Invalid Argument");
                    }

                    try {

                        currentAir.name = args[n];

                        if (isFlightNoValid(args[n + 1]))
                            currentFlight.fNumber = args[n + 1];
                        else
                            throw new IllegalArgumentException("ERROR: Enter valid Flight number containing all integers");


                        if (isAirNameValid(args[n + 2]))
                            currentFlight.srcAirName = args[n + 2];
                        else
                            throw new IllegalArgumentException("ERROR: The departure airport code should have a length 3 and consist of all letters.");


                        if (isDateValid(args[n + 3]) && isTimeValid(args[n + 4]))
                            currentFlight.departTime = args[n + 3] + " " + args[n + 4];
                        else
                            throw new IllegalArgumentException("ERROR: Enter valid date of format mm/dd/yyyy and time of format (hh:mm) or (h:mm)");


                        if (isAirNameValid(args[n + 5]))
                            currentFlight.destAirName = args[n + 5];
                        else
                            throw new IllegalArgumentException("ERROR: The arrival airport code should have a length 3 and consist of all letters.");


                        if (isDateValid(args[n + 6]) && isTimeValid(args[n + 7]))
                            currentFlight.arriveTime = args[n + 6] + " " + args[n + 7];
                        else
                            throw new IllegalArgumentException("ERROR: Enter valid date of format mm/dd/yyyy and time of format (hh:mm) or (h:mm)");

                    } catch (IllegalArgumentException e) {
                        System.err.println(e.getMessage());
                        System.exit(1);
                    } catch (Exception e){
                        System.err.println("ERROR: Validation Exception");
                    }

                    addFlightToAir();

                    if (fileRead || n==1)
                        printMe();

            }

        }   catch(IllegalArgumentException e){
                System.err.println(e.getMessage());
                System.exit(1);
        }   catch (Exception e){
            System.err.println("ERROR: Exception occured while validating.");
            System.exit(1);
        }


        if (fileWrite || fileRead) {

            try {
                File file = new File(fileName);

                if((fileWrite) && (file.exists())){
                    TextParser parser = new TextParser(fileName);
                    oldAirline = (Airline) parser.parse();


                    if((currentAir.getName().equals(oldAirline.getName()))){
                        TextDumper dumper = new TextDumper(fileName);
                        dumper.dump(currentAir);
                    }else{
                        System.out.println("ERROR: Airline Mismatch");
                    }
                }
                else {
                    TextDumper dumper = new TextDumper(fileName);
                    dumper.dump(currentAir);
                }
            }

            catch (FileNotFoundException ex) {
                System.err.println("ERROR: File not found at : "+ fileName);
                System.exit(1);
            }
            catch (IOException ex) {
                System.err.println("ERROR: Exception while reading/writing file "+fileName);
                System.exit(1);
            }
            catch (ParserException ex) {
                System.err.println("ERROR: Exception while parsing " + fileName);
                System.exit(1);
            }
        }
        System.exit(0);
    }


    /**
     * addFlightToAir() is to add the given flight to the airline.
     */
    private static void addFlightToAir() {

        currentAir.addFlight(currentFlight);
    }

    /**isFlightNoValid(String arg) is to check if the Flight Number given is valid or not
     * @return Boolen to say if it is valid.
     * */
    private static boolean isFlightNoValid(String arg) {
        if(arg.matches("[0-9]+"))
            return true;
        else
            return false;
    }


    /**
     * isAirNameValid() is to check if the airline name is valid with 3 characters.
     * @param arg is the argument to check for airline name
     * @return Boolean
     */
    private static Boolean isAirNameValid(String arg) {
        if (arg.length() == 3 && arg.chars().allMatch(Character::isLetter))
            return true;
        else
            return false;
    }

    /**
     * printMe() is to print the Airline and Flight information.
     **/
    private static void printMe() {
        System.out.println("Airline is " + currentAir.getName());
        System.out.println(currentFlight.toString());
    }

    /**
     *
     * isDateValid() is to check if the date is valid with MM/DD/YYYY.
     * @param arg is the argument to check for date
     * @return Boolean
     */
    private static Boolean isDateValid(String arg) {
        try {
        if (arg == null || (!arg.matches("^\\d{1,2}/\\d{1,2}/\\d{4}$"))) {
            throw new IllegalArgumentException("ERROR: Date not valid.");
        }
        SimpleDateFormat sdf1 = new SimpleDateFormat("MM/dd/yyyy");
        sdf1.setLenient(false);

            sdf1.parse(arg);
            return true;
        } catch (ParseException e) {
            System.out.println("ERROR: Date not valid.");
            return false;
        }

    }

    /**
     * isTimeValid() is to check if the time is valid with HH:MM format.
     *
     * @param arg is the argument to check for time
     * @return Boolean
     */
    private static Boolean isTimeValid(String arg) {
        try {
            if (arg == null || (!arg.matches("^\\d{1,2}:\\d{2}$"))) {
                throw new IllegalArgumentException("ERROR: Time not valid.");

            }
            DateFormat df1 = new SimpleDateFormat("HH:mm");
            df1.setLenient(false);

            df1.parse(arg);
            return true;
        } catch (ParseException e) {
            System.out.println("ERROR: Time not valid.");
            return false;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    /**
     * readMe() is to print the commands that user can give.
     **/
    private static void readMe() {
        System.out.println("This is README file for the Airline Project\n" +
                "The command line arguments you give are in this order:\n" +
                "name              The name of the airline\n" +
                "flightNumber      The flight number\n" +
                "src               Three-letter code of departure airport\n" +
                "departTime        Departure date and time (24-hour time)\n" +
                "dest              Three-letter code of arrival airport\n" +
                "arriveTime        Arrival date and time (24-hour time)\n\n" +
                "Other options you can give are: \n" +
                "-textFile file    Where to read/write the airline info\n" +
                "-print            Prints a description of the new flight\n" +
                "-README           Prints a README for this project and exits\n");
    }
}


