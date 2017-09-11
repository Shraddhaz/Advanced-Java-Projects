package edu.pdx.cs410J.shraddha;

import edu.pdx.cs410J.AbstractFlight;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * The class for the airline Project to accept Flight details extended from the Abstract class AbstractFlight
 */
public class Flight extends AbstractFlight implements Comparable<Flight> {

  String fNumber;
  String srcAirName;
  String departTime;
  String destAirName;
  String arriveTime;



/**
 * Constructor as the setter function for setting the elements as null for all the elements
 * */
  public Flight() {
    this.fNumber = null;
    this.srcAirName = null;
    this.departTime = null;
    this. destAirName = null;
    this. arriveTime = null;
  }

  /**
   * Constructor as the setter function for setting the elements as the arguments for all the elements
   * @param fNumber is the string FlightNumber
   * @param srcAirName is the source airport code
   * @param departTime is the departure date and time
   * @param destAirName is the destination airport code
   * @param arriveTime is the arrival date and time
   * */
  public Flight(String fNumber, String srcAirName, String departTime, String destAirName, String arriveTime) {

    this.fNumber = fNumber;
    this.srcAirName = srcAirName;
    this.departTime = departTime;
    this. destAirName = destAirName;
    this. arriveTime = arriveTime;
  }


  /**
   * @return Returns Flight Number - Getter function
   */
  @Override
  public int getNumber() {

    return Integer.parseInt(fNumber);
  }



  /**
   * @return Returns source airline name - Getter function
   */
  @Override
  public String getSource() {

    return this.srcAirName;
  }


  /**
   * @return Returns destination airline name - Getter function
   */
  @Override
  public String getDestination() {

    return this.destAirName;
  }

  /**
   * @return Returns arriaval time and date of flight - Getter function
   */
  @Override
  public String getArrivalString() {
    int dateTimeFormat= DateFormat.SHORT;
    DateFormat df = DateFormat.getDateTimeInstance(dateTimeFormat,dateTimeFormat);
    Date date = getArrival();
    this.arriveTime= df.format(date);
    return this.arriveTime;
  }

  /**
   * @return Returns departure time and date - Getter function
   */
  @Override
  public String getDepartureString() {
    int dateTimeFormat= DateFormat.SHORT;
    DateFormat df = DateFormat.getDateTimeInstance(dateTimeFormat,dateTimeFormat);
    Date date = getDeparture();
    this.departTime= df.format(date);
    return this.departTime;
  }

  /**
   * getDeparture() returns the arrival date in Date format with MM/dd/yyyy hh:mm a format - Getter function
   * */
  @Override
  public Date getArrival() {
    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
    sdf.setLenient(false);
    Date parsedDate= null;
    try {
      parsedDate = sdf.parse(this.arriveTime);
    } catch (ParseException e) {
      System.err.println("ERROR: Arrival time is not valid \n"+ e);
      System.exit(1);
      e.printStackTrace();
    }
    return parsedDate;
  }

  /**
   * getDeparture() returns the departure date in Date format with MM/dd/yyyy hh:mm a format- Getter function
   * */
  @Override
  public Date getDeparture() {
    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
    sdf.setLenient(false);
    Date parsedDate= null;
    try {
      parsedDate = sdf.parse(this.departTime);
    } catch (ParseException e) {
      System.err.println("ERROR: Depart time is not valid \n"+ e);
      System.exit(1);
      e.printStackTrace();
    }
    return parsedDate;
  }

/**
 * @param flight is to compare the instance of calling flight to all flights in the class.
 * It is called implicitly when the set gets elements inserted in it
 * */
  @Override
  public int compareTo(Flight flight) {
    int difference;
    difference = this.getSource().compareTo(flight.getSource());

    if(difference == 0){
      difference = this.getDepartureString().compareTo(flight.getDepartureString());
    }

    return difference;

  }

  /**
   * Saying whether the object is an instance of the Flight class or not
   * @return boolean
   */
  public boolean equals(Object o){
    if(o instanceof Flight){
      return true;
    }
    return false;
  }

  /**
   * Returns departure time hashed
   * @return int with the hashcode
   */
  public int hashCode(){
    return this.getDeparture().hashCode();
  }

}

