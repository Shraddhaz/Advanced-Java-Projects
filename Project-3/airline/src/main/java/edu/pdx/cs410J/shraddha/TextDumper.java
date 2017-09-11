package edu.pdx.cs410J.shraddha;

import edu.pdx.cs410J.AbstractAirline;
import edu.pdx.cs410J.AirlineDumper;

import java.io.*;
import java.util.Iterator;

/**
 * Created by shraddhazingade on 7/15/17.
 */
public class TextDumper implements AirlineDumper {

    private File file;
    String path = new String();

    /**
     * Constructor for TextDumper  to set the filepath
     * @param fileName is the string having the filename.
     * */
    public TextDumper(String fileName) {

        this.file = new File(fileName);
    }

    /**
     * Dump is used for creating the file when it does not exist
     * @param Airline is given to write flight information of each of those flights that belong to that airline.
     * */
    @Override
    public void dump(AbstractAirline Airline) throws IOException {

        try{
            FileWriter w = new FileWriter(file, true);
            Iterator i = Airline.getFlights().iterator();
            while (i.hasNext()){
                Flight flight = (Flight) i.next();
                w.write("Airline: "+ Airline.getName()+ "\n");
                w.write("Flight Number: " + flight.fNumber + "\n");
                w.write("Source Airport code: " + flight.getSource () + "\n");
                w.write("Departure Time: " + flight.getDepartureString() + "\n");
                w.write("Destination Airport code: " + flight.getDestination() + "\n");
                w.write("Arrival Time: " + flight.getArrivalString() + "\n");
            }
            w.flush();
            w.close();
        }
        catch (IOException e){
            System.out.println("ERROR: File not found.");
            System.exit(1);
        }
        catch (Exception e){
            System.err.println("ERROR: Dumping exception");
        }

    }

}