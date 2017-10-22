/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thermistor;
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
int reading = r.getAnalogPin(0).getValue();
sum += reading;
    }//Return the average reading
    return sum / readingCount;
    }
    public static RXTXRobot r; //Create RXTXRobot object 
    public static void main(String[] args) {
      r = new ArduinoNano();
             r.setPort("COM7");
             r.connect();
            //Get the average thermistor reading
            int thermistorReading = getThermistorReading();
            //Print the results
            System.out.println("The probe read the value: " + thermistorReading);
            System.out.println("In volts: " + (thermistorReading * (5.0/1023.0)));
    
    }
    
}
