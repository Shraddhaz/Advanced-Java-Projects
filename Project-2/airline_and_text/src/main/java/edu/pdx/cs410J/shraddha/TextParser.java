package edu.pdx.cs410J.shraddha;

import edu.pdx.cs410J.AbstractAirline;
import edu.pdx.cs410J.AirlineParser;
import edu.pdx.cs410J.ParserException;

import java.io.*;
import java.util.Objects;
import java.util.StringTokenizer;

/**
 * Created by shraddhazingade on 7/15/17.
 */
public class TextParser implements AirlineParser {

    private File file;
    Airline currentAirline;

    String fNumber;
    String srcAirName;
    String departTime;
    String destAirName;
    String arriveTime;
    BufferedReader re = null;

    /**
     * Constructor for TextParser to set the parser for that given file.
     * @param fileName is the path where the file is to be parsed.
     * */
    public TextParser(String fileName) {

        this.file = new File(fileName);
    }


    /**
     * parse() is used to parse the whole file, break it in tokens and check every word.
     * @return AbstractAirline that is present in the file.
     * */
    @Override
    public AbstractAirline parse() throws ParserException {

        try{

            re = new BufferedReader(new FileReader(file));

            if(!re.ready()){
                throw new ArrayIndexOutOfBoundsException();
            }

            while(re.ready()) {

                String line = re.readLine();

                if (line.equals(null)) {
                    throw new ArrayIndexOutOfBoundsException();
                }
                else if(line.isEmpty()){
                    throw new ArrayIndexOutOfBoundsException();
                }
                else if (line.equals("")) {
                    continue;
                }

                String currentString = null;

                if(!line.contains(":")) {
                    throw new ArrayIndexOutOfBoundsException();
                }

                StringTokenizer st = new StringTokenizer(line,":");

                if(st.hasMoreTokens()) {
                    currentString = st.nextToken();
                }

                if (st.hasMoreTokens()) {
                    StringBuilder sb= new StringBuilder();

                    while (st.hasMoreTokens()) {
                        sb.append(st.nextToken() + " ");
                    }

                    if(currentString.equals("Airline")){
                        currentAirline = new Airline(sb.toString().trim());
                    }

                    if(sb.toString().trim().isEmpty()){
                        throw new ArrayIndexOutOfBoundsException();
                    }
                }

                if (currentString.equals("Airline")) {
                    this.parseAllFlights();
                }
            }

        }  catch (IOException e) {
            System.err.println("ERROR: IO Exception occurred.");
            System.exit(1);
        }  catch(ArrayIndexOutOfBoundsException e){
            System.err.println("ERROR: File is either empty or malformed");
            System.exit(1);
        }  catch (Exception e){
            System.err.println("ERROR: Parsing exception");
        }

        return this.currentAirline;
    }

/**
 * parseAllFlights() is used to parse all the flight information for all the flights in that airline.
 * */
    private void parseAllFlights(){

        Flight flight;

        for (int i = 0; i < 6; i++) {
            String line = null;
            try {
                if (!re.ready()||re.readLine()==null) {
                    System.err.println("ERROR: End of file.");
                }

                line = this.re.readLine();
            } catch (IOException ex) {
                System.err.println("ERROR: IOException: " + ex.getMessage());
            }

            if (line == (null)) {
                break;
            } else if (Objects.equals(line, "")) {
                continue;
            }

            String leftValue = null;
            String rightValue = null;

            StringTokenizer stringTokenizer = new StringTokenizer(line, ":");

            if (stringTokenizer.hasMoreTokens()) {
                leftValue = stringTokenizer.nextToken();
            } else {
                System.err.println("ERROR: No value specified");
            }

            if (stringTokenizer.hasMoreTokens()) {
                StringBuffer strBuff = new StringBuffer();

                if(leftValue.equals("Departure Time")||leftValue.equals("Arrival Time")){

                    while (stringTokenizer.hasMoreTokens()) {
                        strBuff.append(stringTokenizer.nextToken() + " ");
                    }
                    rightValue = strBuff.toString().trim();
                }

            } else
                System.err.println("ERROR: No value specified");

            if (leftValue.equals("Flight Number")) {
                fNumber = rightValue;
            }
            else if (leftValue.equals("Source Airport code")) {
                srcAirName = rightValue;
            }
            else if (leftValue.equals("Departure Time")) {
                departTime = rightValue;
            }
            else if (leftValue.equals("Destination Airport code")) {
                destAirName= rightValue;
            }
            else if (leftValue.equals("Arrival Time")) {
                arriveTime= rightValue;
            }
        }

        flight= new Flight(fNumber,srcAirName,departTime,destAirName,arriveTime);
        this.currentAirline.addFlight(flight);
    }

}

