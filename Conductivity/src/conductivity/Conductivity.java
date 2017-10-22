/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conductivity;
import rxtxrobot.*; 
/**
 *
 * @author Cris
 */
public class Conductivity {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
         RXTXRobot r = new ArduinoNano(); //Create RXTXRobot object 
               /*int digital_1=12;
               int digital_2=13;
               int analog_1=4;
               int analog_2=5;*/
		r.setPort("COM7"); // Sets the port to COM7
		r.connect(); 
		r.refreshAnalogPins(); // Cache the Analog pin information 
                r.refreshDigitalPins();
                int reading;
                reading=r.getConductivity();
                System.out.println("Reading: "+reading);
    }
    
}
