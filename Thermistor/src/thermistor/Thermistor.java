
import rxtxrobot.*; 

/**
 *
 * @author Cris
 */
public class Thermistor {
    
    //This function reads the voltage coming into analog pin 0 ten times
//takes the average, then returns the result.
    public static int getThermistorReading() {
        int sum = 0;
        int readingCount = 10;
    //Read the analog pin values ten times, adding to sum each time
    for (int i = 0; i < readingCount; i++) {
//Refresh the analog pins so we get new readings
r.refreshAnalogPins();
int reading = r.getAnalogPin(5).getValue();
sum += reading;
    }//Return the average reading
    return sum / readingCount;
    }
    public static RXTXRobot r; //Create RXTXRobot object 
    public static void main(String[] args) {
      r = new ArduinoNano();
             r.setPort("COM5");
             r.connect();
            //Get the average thermistor reading
            int thermistorReading = getThermistorReading();
            //get the temp
            double slope=-4.550703907;
            double intercept=794.5506019;
            double temp=(thermistorReading-intercept)/slope;
            //Print the results
            System.out.println("The probe read the value: " + thermistorReading);
            System.out.println("In volts: " + (thermistorReading * (5.0/1023.0)));
            System.out.println("In degrees c: " + temp);
    
    }
    
}
