/**
 * Program to display earthquake details from a USGS dataset.
 * @ Quintin Thornton
 */
public class QuakeInfo {
  public static void main(String[] args) {
    // check if 2 command line arguments, level and period, have been provided
    // if the no of arguments is not equal to 2, display usage message
    if (args.length != 2) {
      System.out.println("Usage: java QuakeInfo <level> <period>");
      // exit main and end program if correct number of arguments is not provided
      return;
    }

    // extract both command line arguments at specified index and assign correct variable
    String level = args[0];
    String period = args[1];

    // try block to handle any errors
    try {
      // create new QuakeDataset class instance which will store appropriately process earthquake info
      QuakeDataset quakeDataset = new QuakeDataset();

      // readData method call on quakeDataset object
      // take 2 arguments which will be used to read and load the correct earthquake info from file
      quakeDataset.readData(level, period);

      // print the number of earthquakes loaded into the quakeDataset by using size method to return the Quake object size
      System.out.println(quakeDataset.size() + " quakes in dataset");
      // call quakeDetails method of the quakeDataset object which will print out the details of each earthquake in the dataset
      quakeDataset.quakeDetails();

      // call the getMinimumDepth method to return the Quake object with the min depth
      // store in shallowestQuake variable
      Quake shallowestQuake = quakeDataset.getMinimumDepth();
      // call the getMaximumMagnitude method to return the Quake object with the max magnitude
      // store in strongestQuake variable
      Quake strongestQuake = quakeDataset.getMaximumMagnitude();

      // call the toString method of shallowestQuake object to print out details of the shallowest quake
      System.out.println("Shallowest : " + shallowestQuake);
      // call the toString method of strongestQuake object to print out details of the strongest quake
      System.out.println("Strongest : " + strongestQuake);
      // catch block to handle any QuakeException exceptions
      // if any are thrown within the try block, program jumps to this block
    } catch (QuakeException e) {
      // Handle any QuakeException by displaying the error message associated with QuakeException
      System.out.println(e.getMessage());
    }  // end the program
  }
}

