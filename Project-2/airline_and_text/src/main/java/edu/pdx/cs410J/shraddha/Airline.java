package edu.pdx.cs410J.shraddha;

import edu.pdx.cs410J.AbstractAirline;
import edu.pdx.cs410J.AbstractFlight;

import java.util.ArrayList;
import java.util.Collection;

/**
 * The class for the airline Project to accept Airline extended from the Abstract class AbstractAirline
 */
public class Airline extends AbstractAirline {

    ArrayList<Flight> flightArrayList = new ArrayList<>();
    String name;

    public Airline(String airName) {
        this.flightArrayList = new ArrayList<>();
        this.name=airName;
    }

    /**
     * The Airpline constructor with no parameter
     * */
    public Airline() {

        this.flightArrayList = new ArrayList<>();
    }

    /**
     * @return Returns airline name - Getter function
     */
    @Override
    public String getName() {

        return this.name;
    }

    /**
     * @param flight Add the flight for that airline - Getter function
     */
    @Override
    public void addFlight(AbstractFlight flight) {
        this.flightArrayList.add((Flight) flight); //flightArrayList.add((Flight) flight);
    }

    /**
     * @return collection of flights for that airline - Getter function
     */
    @Override
    public Collection getFlights() {
        return this.flightArrayList;
    }
}
