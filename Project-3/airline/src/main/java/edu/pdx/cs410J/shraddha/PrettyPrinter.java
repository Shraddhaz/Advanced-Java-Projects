package edu.pdx.cs410J.shraddha;

import edu.pdx.cs410J.AbstractAirline;
import edu.pdx.cs410J.AirlineDumper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;


/**
 * Created by shraddhazingade on 7/23/17.
 */
public class PrettyPrinter implements AirlineDumper {

    private File file;
    private static boolean flag;
    /**
     * Constructor for TextDumper  to set the filepath
     * @param fileName is the string having the filename.
     * */
    public PrettyPrinter(String fileName) throws IOException {
        this.file = new File(fileName);
    }

    /**
     * Dump is used for creating the file when it does not exist in pretty format
     * @param abstractAirline is given to write flight information of each of those flights that belong to that airline but in a pretty format.
     * */
    @Override
    public void dump(AbstractAirline abstractAirline) throws IOException {

        FileWriter w = new FileWriter(file, false);

        Airline airline = (Airline) abstractAirline;
        SortedSet<Flight> sortedTree = new TreeSet<>();
        sortedTree.addAll(airline.getFlights());
        Iterator i = sortedTree.iterator();

        try {
                w.write(airline.getName()+"\n");
                w.write(String.format("%10s%30s%22s%22s%22s%22s\n","Flight", "Source", "Departure Time", "Destination","Arrival Time", "Duration"));
                while (i.hasNext()) {
                    Flight flight = (Flight) i.next();
                    long convTime = TimeUnit.MILLISECONDS.toMinutes(flight.getArrival().getTime() - flight.getDeparture().getTime());
                    if(convTime > 0){
                        w.write(String.format("%10s%30s%22s%22s%22s%22s\n",flight.fNumber+".", flight.getSource()+".", flight.getDepartureString() + ".", flight.getDestination() + ".", flight.getArrivalString() + ".", convTime + " min"));
                    }
                    else{
                        throw new IllegalArgumentException("ERROR: The arrival time should be after the departure time.");
                    }
                }
                w.flush();
                w.close();
        }
        catch(IOException e){
            System.out.println("ERROR: File not found.");
            System.exit(1);
        }
        catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
            System.exit(1);
        }
        catch(Exception e){
            System.err.println("ERROR: Dumping exception");
            System.exit(1);
        }
        

    }
}
