package edu.pdx.cs410J.shraddha;

import edu.pdx.cs410J.AbstractAirline;
import edu.pdx.cs410J.AirlineParser;
import edu.pdx.cs410J.AirportNames;
import edu.pdx.cs410J.ParserException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;
import java.util.StringTokenizer;

import static edu.pdx.cs410J.shraddha.Project3.*;

/**
 * Created by shraddhazingade on 7/15/17.
 */
public class TextParser implements AirlineParser {

    private File file;
    Airline currentAirline = null;
    Flight flight = null;

    String fNumber;
    String srcAirName;
    String departTime;
    String destAirName;
    String arriveTime;
    BufferedReader re = null;

    /**
     * Constructor for TextParser to set the parser for that given file.
     *
     * @param fileName is the path where the file is to be parsed.
     */
    public TextParser(String fileName) {

        this.file = new File(fileName);
    }


    /**
     * parse() is used to parse the whole file, break it in tokens and check every word.
     *
     * @return AbstractAirline that is present in the file.
     */
    @Override
    public AbstractAirline parse() throws ParserException {

        try {

            re = new BufferedReader(new FileReader(file));

            if (!re.ready()) {
                throw new ArrayIndexOutOfBoundsException();
            }

            while (re.ready()) {

                String line = re.readLine();

                if (line.equals(null)) {
                    throw new ArrayIndexOutOfBoundsException();
                } else if (line.isEmpty()) {
                    throw new ArrayIndexOutOfBoundsException();
                } else if (line.equals("")) {
                    continue;
                }

                if (!prettyPrint || fileWrite) {

                    String currentString = null;

                    if (!line.contains(":")) {
                        throw new ArrayIndexOutOfBoundsException();
                    }

                    StringTokenizer st = new StringTokenizer(line, ":");

                    if (st.hasMoreTokens()) {
                        currentString = st.nextToken();
                    }

                    if (st.hasMoreTokens()) {
                        StringBuilder sb = new StringBuilder();

                        while (st.hasMoreTokens()) {
                            sb.append(st.nextToken() + " ");
                        }

                        if (currentString.equals("Airline")) {
                            currentAirline = new Airline(sb.toString().trim());
                        }

                        if (sb.toString().trim().isEmpty()) {
                            System.out.println("5 - Throwing exception");
                            throw new ArrayIndexOutOfBoundsException();
                        }
                    }

                    if (currentString.equals("Airline")) {
                        this.parseAllFlights();
                    }
                } else {
                    currentAirline = new Airline(line.trim());
                    String currentString = null;
                    currentString = re.readLine();
                    currentString = re.readLine();
                    while (currentString != null) {
                        String tokens[] = currentString.split("\\.");
                        fNumber = tokens[0].trim();
                        srcAirName = tokens[1].trim();
                        departTime = tokens[2].trim();
                        destAirName = tokens[3].trim();
                        arriveTime = tokens[4].trim();
                        flight = new Flight(fNumber, srcAirName, departTime, destAirName, arriveTime);
                        currentAirline.addFlight(flight);
                        currentString = re.readLine();
                    }
                }
            }

        } catch (IOException e) {
            System.err.println("ERROR: IO Exception occurred.");
            System.exit(1);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("ERROR: File is either empty or malformed");
            System.exit(1);
        } catch (Exception e) {
            System.err.println("ERROR: Parsing exception");
        }

        return this.currentAirline;
    }

    /**
     * parseAllFlights() is used to parse all the flight information for all the flights in that airline.
     */
    private void parseAllFlights() {

        for (int i = 0; i < 5; i++) {
            String line = null;
            try {
                if (!re.ready()) {
                    throw new IOException("ERROR: End of file.");
                }
                line = this.re.readLine();
            } catch (IOException ex) {
                System.err.println("ERROR: IOException: " + ex.getMessage());
                System.exit(1);
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

                if (leftValue.equals("Departure Time") || leftValue.equals("Arrival Time")) {

                    while (stringTokenizer.hasMoreTokens()) {
                        strBuff.append(stringTokenizer.nextToken() + " ");
                    }
                    rightValue = strBuff.toString().trim();
                } else {
                    strBuff.append(stringTokenizer.nextToken());
                    rightValue = strBuff.toString().trim();
                }

            } else
                System.err.println("ERROR: No value specified");

            try {
                if (leftValue.equals("Flight Number")) {

                    if (isFlightNoValid(rightValue))
                        fNumber = rightValue;
                    else
                        throw new IllegalArgumentException("ERROR: File malformed");
                } else if (leftValue.equals("Source Airport code")) {

                    if (AirportNames.getNamesMap().containsValue(rightValue))
                        srcAirName = rightValue;
                    else
                        throw new IllegalArgumentException("ERROR: File malformed");

                } else if (leftValue.equals("Departure Time")) {

                    departTime = rightValue;

                } else if (leftValue.equals("Destination Airport code")) {

                    if (AirportNames.getNamesMap().containsValue(rightValue))
                        destAirName = rightValue;
                    else
                        throw new IllegalArgumentException("ERROR: File malformed");

                } else if (leftValue.equals("Arrival Time")) {

                    arriveTime = rightValue;
                }

            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                System.exit(1);
            }
        }

        String[] tokens1 = departTime.split(" ");

        if (isDateValid(tokens1[0]) && isTimeValid(tokens1[1] + ":" + tokens1[2]) && isAmPmValid(tokens1[3])) {
            String value1 = tokens1[0] + " " + tokens1[1] + ":" + tokens1[2] + " " + tokens1[3];
            departTime = value1;
        } else
            throw new IllegalArgumentException("ERROR: File malformed");

        String[] tokens2 = arriveTime.split(" ");

        if (isDateValid(tokens2[0]) && isTimeValid(tokens2[1] + ":" + tokens2[2]) && isAmPmValid(tokens2[3])) {
            String value2 = tokens2[0] + " " + tokens2[1] + ":" + tokens2[2] + " " + tokens2[3];
            arriveTime = value2;
        } else
            throw new IllegalArgumentException("ERROR: File malformed");


        this.flight = new Flight(fNumber, srcAirName, departTime, destAirName, arriveTime);
        this.currentAirline.addFlight(flight);

    }
}



