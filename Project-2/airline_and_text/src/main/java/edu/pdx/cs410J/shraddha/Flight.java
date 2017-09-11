package edu.pdx.cs410J.shraddha;

import edu.pdx.cs410J.AbstractFlight;


/**
 * The class for the airline Project to accept Flight details extended from the Abstract class AbstractFlight
 */
public class Flight extends AbstractFlight {

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
   * @return Returns departure time and date - Getter function
   */
  @Override
  public String getDepartureString() {

    return this.departTime;
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

    return this.arriveTime;
  }
}
