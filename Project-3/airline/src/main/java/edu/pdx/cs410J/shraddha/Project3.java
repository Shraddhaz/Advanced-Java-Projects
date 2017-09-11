package edu.pdx.cs410J.shraddha;

import edu.pdx.cs410J.AirportNames;
import edu.pdx.cs410J.ParserException;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

/**
 * The main class for the CS410J airline Project
 *
 */
public class Project3 {
    private static Airline currentAir = null;
    private static Flight currentFlight = null;
    private static int n = 0;
    private static String fileName;
    private static boolean fileRead;
    public static boolean fileWrite = false;
    public static boolean prettyPrint;
    public static String prettyFileName;
    private static Airline oldAirline;

    private static String arriveTime;
    private static String destAirName;
    private static String fNumber;
    private static String srcAirName;
    private static String departTime;
    private static String name;
    public static boolean prettyOutputFlag=false;


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

                default:
                    try {
                        if (args[0].startsWith("-")) {
                            if (args[0].equals("-print") && (!(args[1].equals("-textFile"))) && (!(args[1].equals("-pretty")))) {
                                if (args.length > 11) {
                                    throw new IllegalArgumentException("ERROR: 1Extra Invalid Argument");
                                }
                                if (args.length < 11) {
                                    throw new IllegalArgumentException("ERROR: Missing Arguments");
                                }
                                n = 1;
                                fileRead = true;
                            } else if (args[0].equals("-textFile") && (!(args[2].equals("-print"))) && (!(args[2].equals("-pretty")))) {
                                if (args.length > 12) {
                                    throw new IllegalArgumentException("ERROR: 2Extra Invalid Argument");
                                }
                                if (args.length < 12) {
                                    throw new IllegalArgumentException("ERROR: Missing Arguments");
                                }
                                n = 2;
                                fileWrite = true;
                                fileName = args[1];
                            } else if (args[0].equals("-pretty") && (!(args[2].equals("-print"))) && (!(args[2].equals("-textFile")))) {
                                if (args.length > 12) {
                                    throw new IllegalArgumentException("ERROR: 3Extra Invalid Argument");
                                }
                                if (args.length < 12) {
                                    throw new IllegalArgumentException("ERROR: Missing Arguments");
                                }
                                n = 2;
                                prettyFileName = args[1];
                                prettyPrint = true;
                            } else if (args[0].equals("-print") && args[1].equals("-textFile") && (!(args[3].equals("-pretty")))) {
                                if (args.length > 13) {
                                    throw new IllegalArgumentException("ERROR: 4Extra Invalid Argument");
                                }
                                if (args.length < 13) {
                                    throw new IllegalArgumentException("ERROR: Missing Arguments");
                                }
                                n = 3;
                                fileWrite = true;
                                fileName = args[2];
                                fileRead = true;
                            } else if (args[0].equals("-textFile") && args[2].equals("-print") && (!(args[3].equals("-pretty")))) {
                                if (args.length > 13) {
                                    throw new IllegalArgumentException("ERROR: 5Extra Invalid Argument");
                                }
                                if (args.length < 13) {
                                    throw new IllegalArgumentException("ERROR: Missing Arguments");
                                }
                                n = 3;
                                fileRead = true;
                                fileWrite = true;
                                fileName = args[1];
                            } else if (args[0].equals("-print") && (!(args[3].equals("-textFile"))) && (args[1].equals("-pretty"))) {
                                if (args.length > 13) {
                                    throw new IllegalArgumentException("ERROR: 6Extra Invalid Argument");
                                }
                                if (args.length < 13) {
                                    throw new IllegalArgumentException("ERROR: Missing Arguments");
                                }
                                n = 3;
                                fileRead=true;
                                prettyPrint = true;
                                prettyFileName = args[2];
                            } else if (args[0].equals("-pretty") && (!(args[3].equals("-textFile"))) && (args[2].equals("-print"))) {
                                if (args.length > 13) {
                                    throw new IllegalArgumentException("ERROR: 7Extra Invalid Argument");
                                }
                                if (args.length < 13) {
                                    throw new IllegalArgumentException("ERROR: Missing Arguments");
                                }
                                n = 3;
                                prettyPrint = true;
                                prettyFileName = args[1];
                                fileRead = true;
                            } else if (args[0].equals("-textFile") && (!(args[4].equals("-print"))) && (args[2].equals("-pretty"))) {
                                if (args.length > 14) {
                                    throw new IllegalArgumentException("ERROR: 8Extra Invalid Argument");
                                }
                                if (args.length < 14) {
                                    throw new IllegalArgumentException("ERROR: Missing Arguments");
                                }
                                n = 4;
                                fileWrite = true;
                                fileName = args[1];
                                prettyPrint = true;
                                prettyFileName = args[3];
                            } else if (args[0].equals("-pretty") && (!(args[4].equals("-print"))) && (args[2].equals("-textFile"))) {
                                if (args.length > 14) {
                                    throw new IllegalArgumentException("ERROR: 9Extra Invalid Argument");
                                }
                                if (args.length < 14) {
                                    throw new IllegalArgumentException("ERROR: Missing Arguments");
                                }
                                n = 4;
                                fileWrite = true;
                                fileName = args[3];
                                prettyPrint = true;
                                prettyFileName = args[1];
                            } else if (args[0].equals("-print") && args[1].equals("-textFile") && (args[3].equals("-pretty"))) {
                                if (args.length > 15) {
                                    throw new IllegalArgumentException("ERROR: 10Extra Invalid Argument");
                                }
                                if (args.length < 15) {
                                    throw new IllegalArgumentException("ERROR: Missing Arguments");
                                }
                                n = 5;
                                fileRead = true;
                                fileWrite = true;
                                fileName = args[2];
                                prettyPrint = true;
                                prettyFileName = args[4];
                            } else if (args[0].equals("-print") && args[1].equals("-pretty") && (args[3].equals("-textFile"))) {
                                if (args.length > 15) {
                                    throw new IllegalArgumentException("ERROR: 11Extra Invalid Argument");
                                }
                                if (args.length < 15) {
                                    throw new IllegalArgumentException("ERROR: Missing Arguments");
                                }
                                n = 5;
                                fileRead = true;
                                fileWrite = true;
                                fileName = args[4];
                                prettyPrint = true;
                                prettyFileName = args[2];
                            } else if (args[0].equals("-textFile") && args[2].equals("-print") && (args[3].equals("-pretty"))) {
                                if (args.length > 15) {
                                    throw new IllegalArgumentException("ERROR: 12Extra Invalid Argument");
                                }
                                if (args.length < 15) {
                                    throw new IllegalArgumentException("ERROR: Missing Arguments");
                                }
                                n = 5;
                                fileRead = true;
                                fileWrite = true;
                                fileName = args[1];
                                prettyPrint = true;
                                prettyFileName = args[4];
                            }else if (args[0].equals("-textFile") && args[2].equals("-pretty") && (args[4].equals("-print"))) {
                                if (args.length > 15) {
                                    throw new IllegalArgumentException("ERROR: 13Extra Invalid Argument");
                                }
                                if (args.length < 15) {
                                    throw new IllegalArgumentException("ERROR: Missing Arguments");
                                }
                                n = 5;
                                fileRead = true;
                                fileWrite = true;
                                fileName = args[1];
                                prettyPrint = true;
                                prettyFileName = args[3];
                            } else if (args[0].equals("-pretty") && args[2].equals("-print") && (args[3].equals("-textFile"))) {
                                if (args.length > 15) {
                                    throw new IllegalArgumentException("ERROR: 14Extra Invalid Argument");
                                }
                                if (args.length < 15) {
                                    throw new IllegalArgumentException("ERROR: Missing Arguments");
                                }
                                n = 5;
                                fileRead = true;
                                fileWrite = true;
                                fileName = args[4];
                                prettyPrint = true;
                                prettyFileName = args[1];
                            }else if (args[0].equals("-pretty") && args[2].equals("-textFile") && (args[4].equals("-print"))) {
                                if (args.length > 15) {
                                    throw new IllegalArgumentException("ERROR: 15Extra Invalid Argument");
                                }
                                if (args.length < 15) {
                                    throw new IllegalArgumentException("ERROR: Missing Arguments");
                                }
                                n = 5;
                                fileRead = true;
                                fileWrite = true;
                                fileName = args[3];
                                prettyPrint = true;
                                prettyFileName = args[1];
                            } else
                                throw new IllegalArgumentException("ERROR: Invalid Command line Option");
                        } else if (args.length > 10)
                            throw new IllegalArgumentException("ERROR: 16Extra Invalid Argument");
                        else if (args.length < 10) {
                            throw new IllegalArgumentException("ERROR: Missing Arguments");
                        }

                    }catch (ArrayIndexOutOfBoundsException e) {
                        System.err.println("ERROR: Need more arguments.");
                        System.exit(1);
                    }

                    try {

                        name = args[n];

                        if (isFlightNoValid(args[n + 1]))
                            fNumber = args[n + 1];
                        else
                            throw new IllegalArgumentException("ERROR: Enter valid Flight number containing all integers");


                        if (isAirNameValid(args[n + 2])){
                            if(AirportNames.getNamesMap().containsKey(args[n+2].toUpperCase())){
                                srcAirName = AirportNames.getName(args[n+2].toUpperCase());
                            }
                            else{
                                System.out.println("ERROR: Enter valid source airport code");
                                System.exit(1);
                            }
                        }
                        else
                            throw new IllegalArgumentException("ERROR: The departure airport code should have a length 3 and consist of all letters.");


                        if (isDateValid(args[n + 3]) && isTimeValid(args[n + 4]) && isAmPmValid(args[n+5]))
                            departTime = args[n + 3] + " " + args[n + 4] + " " + args[n + 5];
                        else
                            throw new IllegalArgumentException("ERROR: Enter valid date of format mm/dd/yyyy and time of format (hh:mm a) or (h:mm a)");


                        if (isAirNameValid(args[n + 6]))
                            if(AirportNames.getNamesMap().containsKey(args[n+6].toUpperCase())){
                                destAirName = AirportNames.getName(args[n + 6].toUpperCase());
                            }
                            else{
                                System.out.println("ERROR: Enter valid source airport code");
                                System.exit(1);
                            }
                        else
                            throw new IllegalArgumentException("ERROR: The arrival airport code should have a length 3 and consist of all letters.");

                        if (isDateValid(args[n + 7]) && isTimeValid(args[n + 8]) && isAmPmValid(args[n+9]))
                            arriveTime = args[n + 7] + " " + args[n + 8] + " " + args[n + 9];
                        else
                            throw new IllegalArgumentException("ERROR: Enter valid date of format mm/dd/yyyy and time of format (hh:mm a) or (h:mm a)");

                    } catch (IllegalArgumentException e) {
                        System.err.println(e.getMessage());
                        System.exit(1);
                    } catch (Exception e){
                        System.err.println("ERROR: Validation Exception");
                    }

                    currentAir = new Airline(name);
                    currentFlight = new Flight(fNumber,srcAirName,departTime,destAirName,arriveTime);
                    currentAir.addFlight(currentFlight);

                    if (fileRead) {
                        printMe();
                    }

                    if (fileWrite) {
                        writeMe();
                    }

                    if(prettyPrint){
                        prettyPrintMe();
                    }
            }

        }   catch(IllegalArgumentException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }   catch (Exception e){
            System.out.println();
            System.err.println("ERROR: Exception occurred while validating.");
            System.exit(1);
        }

        System.exit(0);
    }


    /**
     * The function is called when -textFile is present on the command line arguments.
     * It dumps/parses  data in the file given on the command line arguments.
     * It returns void.
     * */
    private static void writeMe() {

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
            fileWrite=false;
        }
    }


    /**
     * The function is called when -pretty is present on the command line arguments i.e.
     * When data needs be be added or vied in readable format.
     * It dumps/parses  data in the file given on the command line arguments.
     * It returns void.
     * */
    private static void prettyPrintMe() throws IOException {

        PrettyPrinter pretty;

        if(prettyFileName.equals("-")){
            System.out.printf("%22s%10s%30s%22s%22s%22s%22s","Airline","Flight", "Source", "Departure Time", "Destination", "Arrival Time", "Duration\n");

            long time = currentFlight.getArrival().getTime() - currentFlight.getDeparture().getTime();
            long convTime = MILLISECONDS.toMinutes(time);
            if(convTime > 0){
                System.out.printf("%22s%10s%30s%22s%22s%22s%22s",currentAir.getName() ,currentFlight.fNumber,currentFlight.getSource(),currentFlight.getDepartureString(), currentFlight.getDestination() , currentFlight.getArrivalString(), convTime +" min\n");
            }
            else
                throw new IllegalArgumentException("ERROR: The arrival time should be after the departure time.");

            return;
        }
        else if(prettyFileName.startsWith("-")){
            prettyOutputFlag = true;
            prettyFileName = prettyFileName.replace("-","").trim();
        }

            try {
            File file = new File(prettyFileName);
            if(file.exists()) {

                TextParser textParser = new TextParser(prettyFileName);
                oldAirline = (Airline) textParser.parse();

                if((currentAir.getName().equals(oldAirline.getName()))){
                    pretty = new PrettyPrinter(prettyFileName);
                    oldAirline.addFlight(currentFlight);
                    pretty.dump(oldAirline);
                }
                else{
                    System.out.println("ERROR: Airline Mismatch");
                    System.exit(1);
                }
            } else{
                    pretty = new PrettyPrinter(prettyFileName);
                    pretty.dump(currentAir);
            }

            if(prettyOutputFlag){
                FileReader fr = new FileReader(prettyFileName);
                BufferedReader br = new BufferedReader(fr);
                String s = null;
                while ((s=br.readLine())!=null)
                {
                    System.out.println(s);
                }
            }

            } catch (FileNotFoundException e) {
                System.out.println("ERROR: File not found." + "\n");
                System.exit(1);
            } catch (IOException e) {
                System.out.println("ERROR: I/O exception." + "\n");
                System.exit(1);
            } catch (ParserException e) {
                e.printStackTrace();
            }
        }

    /**isFlightNoValid(String arg) is to check if the Flight Number given is valid or not
     * @return Boolean to say if it is valid.
     * */
    public static boolean isFlightNoValid(String arg) {
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
    public static Boolean isAirNameValid(String arg) {
        if (arg.length() == 3 && arg.chars().allMatch(Character::isLetter))
            return true;
        else
            return false;
    }

    /**
     * printMe() is to print the Airline and Flight information.
     **/
    private static void printMe() {
        System.out.println("Airline: " + currentAir.getName());
        System.out.println(currentFlight.toString());
    }

    /**
     *
     * isDateValid() is to check if the date is valid with MM/DD/YYYY.
     * @param arg is the argument to check for date
     * @return Boolean
     */
    public static Boolean isDateValid(String arg) {
        try {
        if (arg == null || (!arg.matches("^\\d{1,2}/\\d{1,2}/\\d{2,4}$"))) {
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
     * isTimeValid() is to check if the time is valid with HH:mm format.
     *
     * @param arg is the argument to check for time
     * @return Boolean
     */
    public static Boolean isTimeValid(String arg) {
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
     * isAmPmValid() is to check if the time is valid with am/pm added at the end.
     * @param arg is the argument to check for time
     * @return Boolean
     */
    public static boolean isAmPmValid(String arg) {
        try {
            if (arg == null || (!arg.matches("(am|pm|AM|PM)"))) {
                throw new IllegalArgumentException("ERROR: Time not valid.");

            }
            DateFormat df1 = new SimpleDateFormat("a");
            df1.setLenient(false);

            df1.parse(arg);
            return true;
        } catch (ParseException e) {
            System.out.println("ERROR: Time not valid(AM/PM)");
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
                "-pretty file      Pretty print the airline’s flights to a text file or standard out\n"+
                "-textFile file    Where to read/write the airline info\n" +
                "-print            Prints a description of the new flight\n" +
                "-README           Prints a README for this project and exits\n");
    }
}

