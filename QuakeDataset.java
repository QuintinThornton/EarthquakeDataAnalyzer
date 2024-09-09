import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/**
 * An earthquake dataset derived from USGS data feeds.
 * @ Quintin Thornton
 */
public class QuakeDataset {

    // declare private earthquakes field to store a list of Quake objects then initialise as an empty ArrayList
    private List<Quake> earthquakes = new ArrayList<>();

    public QuakeDataset() {
        // already initialised in the declaration this will act as default constructor for the class

    }


    // readData() method that will read the data from the csv file based on severity and period
    // throw a QuakeException if the severity/period is invalid
    public void readData(String severity, String period) throws QuakeException {
        // define valid severity and period as lists
      List<String> validSeverity = List.of("significant", "4.5", "2.5", "1.0","all");
      List<String> validPeriod = List.of("hour", "day", "week","month");

      //check if severity is valid
      if (!validSeverity.contains(severity)) {
          throw new QuakeException("Invalid severity.");
      }
      // check if period is valid
      if (!validPeriod.contains(period)) {
          throw new QuakeException("Invalid period.");
      }

      // create a file name based on valid severity and period
      String csvFile = severity + "_" + period + ".csv";

      // try to open csvFile and create a new BufferRead to read over csvFile
      try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
          //skip header
          reader.readLine();

          // declare the variable that will store each line of earthquake information
          String earthquakeData;
          // loop over each line until the end of the csvFile
          while ((earthquakeData = reader.readLine()) != null) {
              String[] rawData = earthquakeData.split("[,\\t]");
              // skip index 0 and parse each rawData value after that to its corresponding data type, double, until index 4
              double latitude = Double.parseDouble(rawData[1]);
              double longitude = Double.parseDouble(rawData[2]);
              double depth = Double.parseDouble(rawData[3]);
              double magnitude = Double.parseDouble(rawData[4]);
              // create new Quake object called earthquake with all the parsed rawData
              Quake earthquake = new Quake(latitude, longitude, depth, magnitude);
              earthquakes.add(earthquake); // add Quake object to earthquakes list
          }
          // catch any IO exceptions i.e. if file isn't found and print the stack trace for better debugging
      } catch (IOException e) {
          e.printStackTrace();
      }

    }

    // size() method that returns the number of Quake objects currently stored
    public int size() {
        return earthquakes.size();
    }


    //  get method which will return the Quake object at a specific point in the list
    public Quake get(int quakePosition) throws QuakeException {

        // check if list of earthquakes is empty. If empty throw QuakeException
        if (earthquakes.isEmpty()) {
            throw new QuakeException("No Quake objects currently stored");
        }
        // validate whether the provided position is negative or not. If negative throw QuakeException
        if (quakePosition < 0 ) {
            throw new QuakeException("Position cannot be negative.");
        }
        // validate if the position is beyond the last index in the earthquakes list, If not throw QuakeException
        if (quakePosition >= earthquakes.size()) {
            throw new QuakeException("Position has to be less than the number of stored earthquakes: " + earthquakes.size());
        }

        // use get method to retrieve the Quake object at the specified index in the list
        // if all checks pass, return the Quake object at the specified position
        return earthquakes.get(quakePosition);
    }


    // Method which finds and returns the Quake object with the minimum depth from the list
    public Quake getMaximumMagnitude() throws QuakeException {

        // check if list of earthquakes is empty. If empty throw QuakeException
        if (earthquakes.isEmpty()){
            throw new QuakeException("No Quake objects currently stored");
    }

        // initialise variable to keep track of the index of the Quake object with max magnitude
         int maximumMagnitudeIndex = 0;

        // loop throw list of earthquakes, starting from index 1
        // assume the starting value, index 0, is the maximum
         for (int i = 1; i < earthquakes.size(); i++) {
             // now compare the 'initial' max magnitude with the current Quake object
             // if Quake object is bigger than the 'initial' max magnitude, update the maximumMagnitudeIndex
             if (earthquakes.get(i).getMagnitude() > earthquakes.get(maximumMagnitudeIndex).getMagnitude()) {
                 maximumMagnitudeIndex = i;
             }
         }
         // Once index for Quake is stored in maximumMagnitudeIndex and return value
         return earthquakes.get(maximumMagnitudeIndex);
    }


  // Method which finds and returns the Quake object with the minimum depth from the list
  public Quake getMinimumDepth() throws QuakeException {
        // check if the list of earthquakes is empty
        // If empty throw QuakeException
      if (earthquakes.isEmpty()){
          throw new QuakeException("No Quake objects currently stored");
      }

      // initialise variable to keep track of the index of the Quake object with min depth
      int minimumDepthIndex = 0;

      // loop throw list of earthquakes, starting from index 1
      // assume the starting value, index 0, is the minimum
      for (int i = 1; i < earthquakes.size(); i++) {
          // now compare the 'initial' min depth with the current Quake object
          // if Quake object value is smaller than the 'initial' min depth, update the minimumDepthIndex
          if (earthquakes.get(i).getDepth() < earthquakes.get(minimumDepthIndex).getDepth()) {
              minimumDepthIndex = i;
          }
      }
      // Once index for Quake is stored in minimumDepthIndex and return value
      return earthquakes.get(minimumDepthIndex);
  }

 
  // method that prints Quake details, one per line
    public void quakeDetails() {
        // check if the list of earthquakes is empty
        // If empty throw QuakeException
        if (earthquakes.isEmpty()){
            throw new QuakeException("No Quake objects currently stored");
        }

        // loop through each Quake object in the earthquakes list
        for (Quake quake : earthquakes) {
            // print the details of the current Quake object using the toString method
            System.out.println(quake.toString());
        }
    }

}
