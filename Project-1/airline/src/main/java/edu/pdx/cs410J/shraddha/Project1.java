package edu.pdx.cs410J.shraddha;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Objects;

/**
 * The main class for the CS410J airline Project
 */
public class Project1 {
  private static Airline currentAir = new Airline();
  private static Flight currentFlight = new Flight();
  private static int i = 0;



  /**
 * @param args has all command line arguments given by user
 * @throws IllegalArgumentException is thrown when there are not enough arguments or the wrong arguments
 ***/

  public static void main(String[] args) throws IllegalArgumentException {

    try {
      if (args.length == 0) {
        throw new IllegalArgumentException("ERROR: Missing command line arguments");
      } else if (args.length == 1 && Objects.equals(args[0], "-README")) {
        readMe();
        System.exit(0);
      } else if ((args.length == 1) && (Objects.equals(args[0], "-print"))) {
          throw new IllegalArgumentException("ERROR: Insufficient arguments. Retry with all arguments.");
      } else if ((args.length == 1) && ((args[0]!="-README") || (args[0]!="-print"))) {
        throw new IllegalArgumentException("ERROR: Invalid argument.");
      } else if ((args.length > 1) && ((Objects.equals(args[0], "-README")) || (Objects.equals(args[1], "-README")))) {
        readMe();
        System.exit(0);
      } else if ((args.length > 1) && (Objects.equals(args[0], "-print")) && (args.length < 9)) {
          throw new IllegalArgumentException("ERROR: Not enough arguments.");
      }
      else if ((args.length > 1) && (!Objects.equals(args[0], "-print")) && (args.length < 8)) {
          throw new IllegalArgumentException("ERROR: Not enough arguments.");
      }
      else if((args.length > 9 && (Objects.equals(args[0], "-print"))) || ((args.length>8) && (!Objects.equals(args[0], "-print")))){
            throw new IllegalArgumentException("ERROR: Additional arguments given");
      }
      else if (args.length > 1 && (Objects.equals(args[0], "-print")) && (args.length == 9)) {
            i = 1;
      }
        else {
            i = 0;
      }



      try {

      /** @param arg[0] - The airline name
       * @param args[1] - flight number is alphanumeric
       * @param args[2] - Checking if length is three and all characters are letters
       * @param args[3] - Date in format MM/DD/YYYY
       * @param args[4] - Time in format HH:MM
       * @param args[5] - Checking if length is three and all characters are letters
       * @param args[6] - Date in format MM/DD/YYYY
       * @param args[7] - Time in format HH:MM
       * */

          currentAir.name = args[i];

          if(isFlightNoValid(args[i+1]))
            currentFlight.fNumber = args[i + 1];
          else
              throw new IllegalArgumentException("ERROR: Enter valid Flight number containing all integers");


          if (isAirNameValid(args[i + 2]))
              currentFlight.srcAirName = args[i + 2];
          else
              throw new IllegalArgumentException("ERROR: The departure airport code should have a length 3 and consist of all letters.");


          if (isDateValid(args[i + 3]) && isTimeValid(args[i + 4]))
              currentFlight.departTime = args[i + 3] + " " + args[i + 4];
          else
              throw new IllegalArgumentException("ERROR: Enter valid date of format mm/dd/yyyy and time of format (hh:mm) or (h:mm)");


          if (isAirNameValid(args[i + 5]))
            currentFlight.destAirName = args[i + 5];
          else
            throw new IllegalArgumentException("ERROR: The arrival airport code should have a length 3 and consist of all letters.");


          if (isDateValid(args[i + 6]) && isTimeValid(args[i + 7]))
              currentFlight.arriveTime = args[i + 6] + " " + args[i + 7];
          else
              throw new IllegalArgumentException("ERROR: Enter valid date of format mm/dd/yyyy and time of format (hh:mm) or (h:mm)");

        } catch (IllegalArgumentException e) {
          System.err.println(e.getMessage());
          System.exit(1);
        }
        addFlightToAir();

        if(i==1)
            printMe();

        System.exit(0);
      }
     catch (IllegalArgumentException e) {
      System.err.println(e.getMessage());
      System.exit(1);
    }
    System.exit(0);
  }

  private static boolean isFlightNoValid(String arg) {
    if(arg.matches("[0-9]+"))
      return true;
    else
      return false;
    }


  /**
   * addFlightToAir() is to add the given flight to the airline.
   * */
  private static void addFlightToAir() {
    currentAir.addFlight(currentFlight);
  }

  /**
   * isAirNameValid() is to check if the airline name is valid with 3 characters.
   * @return Boolean
   * @param arg is the argument to check for airline name
   * */
  private static Boolean isAirNameValid(String arg) {
    if(arg.length()==3 && arg.chars().allMatch(Character::isLetter))
      return true;
    else
      return false;
  }

  /**
   * isDateValid() is to check if the date is valid with MM/DD/YYYY.
   * @return Boolean
   * @param arg is the argument to check for date
   * */
  private static Boolean isDateValid(String arg){
    if(arg == null || (!arg.matches("^\\d{1,2}/\\d{1,2}/\\d{4}$"))){
      return false;
    }
    SimpleDateFormat sdf1 = new SimpleDateFormat("MM/dd/yyyy");
    sdf1.setLenient(false);

    try {
      sdf1.parse(arg);
      return true;
    } catch (ParseException e) {
      System.out.println("ERROR: Date not valid.");
      return false;
    }

  }


  /**
   * isTimeValid() is to check if the time is valid with HH:MM format.
   * @return Boolean
   * @param arg is the argument to check for time
   * */
  private static Boolean isTimeValid(String arg){
    try{
    if(arg == null || (!arg.matches("^\\d{1,2}:\\d{2}$"))){
      throw new IllegalArgumentException("ERROR: Time not valid.");

    }

    DateFormat df1 = new SimpleDateFormat("HH:mm");

    df1.setLenient(false);

      df1.parse(arg);
      return true;
    } catch (ParseException e) {
      System.out.println("ERROR: Time not valid.");
      return false;
    }
      catch (IllegalArgumentException e){
      System.out.println(e.getMessage());
      return false;
    }
  }



  /**
   * printMe() is to print the Airline and Flight information.
   **/
  private static void printMe(){
    System.out.println("Airline is " + currentAir.getName() + "\n" +
              currentFlight.toString()+"\n");
  }

  /**
   * readMe() is to print the commands that user can give.
   **/
  private static void readMe(){
    System.out.println("This is README file for the Airline Project\n" +
            "This is an application to view/insert the airline and its corresponding flights\n" +
            "The commond line arguments you give are in this order:\n" +
            "name              The name of the airline\n" +
            "flightNumber      The flight number\n" +
            "src               Three-letter code of departure airport\n" +
            "departTime        Departure date and time (24-hour time)\n" +
            "dest              Three-letter code of arrival airport\n" +
            "arriveTime        Arrival date and time (24-hour time)\n" +
            "Other options you can give are: \n" +
            "-print            Prints a description of the new flight\n" +
            "-README           Prints a README for this project and exits\n");
  }
}
