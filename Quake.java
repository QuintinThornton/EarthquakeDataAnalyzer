/**
 * Details of a single earthquake.
 * @ Quintin Thornton
 */
public class Quake {
    // define 'parameter' fields. use 'private' modifier for each field and set data types to 'double'
    private double latitude;
    private double longitude;
    private double depth;
    private double magnitude;

    // declare 'public' constructor and custom exception **** ensure right field are assigned to each value? ****
    public Quake(String quakeData) throws QuakeException {
        // use split method with limit to split the input string into an array of 4 substrings using comma as delimiter
        String[] parameters = quakeData.split(",", 4);
        // use 'for' loop to iterate over each element in 'parameters' array
        for (int i = 0; i < parameters.length; i++) {
            // remove any white spaces using '.trim()' method
            parameters[i] = parameters[i].trim();
        }

        // use try block to parse each parameter and assign to correct field
        try {
            // access and retrieve each element of each index
            // parse each substring to 'double' using the 'Double' class and 'parseDouble' static method
            this.latitude = Double.parseDouble(parameters[0]);
            this.longitude = Double.parseDouble(parameters[1]);
            this.depth = Double.parseDouble(parameters[2]);
            this.magnitude = Double.parseDouble(parameters[3]);
            // use catch block to catch any exceptions related to parsing fails thrown by the try block
        } catch (NumberFormatException e) {
            // throw 'QuakeException' with error message
            throw new QuakeException("Parsing failed.");
        }

        // call method passing two arguments which perform the basic latitude and longitude validations
        validateLatitudeLongitude(this.latitude, this.longitude);
    }


    // new constructor to help QuakeDataset class
    public Quake(double latitude, double longitude, double depth, double magnitude) throws QuakeException {
        this.latitude = latitude;
        this.longitude = longitude;
        this.depth = depth;
        this.magnitude = magnitude;
        validateLatitudeLongitude(this.latitude, this.longitude);
    }

    // create method only to be used in the Quake.java class for validating the latitude and longitude
    private void validateLatitudeLongitude(double latitude, double longitude) throws QuakeException {
        // use conditional if statements to check if values are within the range
        if (latitude < -90.0 || latitude > 90.0) {
            // throw a new 'QuakeException' and provide error message if values checked are invalid
            throw new QuakeException("Invalid latitude: " + latitude);
        }
        if (longitude < -180.0 || longitude > 180.0) {
            throw new QuakeException("Invalid longitude: " + longitude);
        }
    }

    // getter methods to provide access to the private fields of the class
    public double getLatitude() {

        return latitude;
    }
    public double getLongitude() {
        return longitude;
    }
    public double getDepth() {

        return depth;
    }
    public double getMagnitude() {

        return magnitude;
    }

    // define method signature
    public String toString() {
        // use static .format() method from String class to format the string
        return String.format("M%.1f, %.1f km, (%.4f\u00b0, %.4f\u00b0)", magnitude, depth, latitude, longitude);
    }
}
