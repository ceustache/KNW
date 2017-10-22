/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sonar;
import rxtxrobot.*; 
/**
 *
 * @author Cris
 */
public class Sonar {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
            RXTXRobot r = new ArduinoNano(); //Create RXTXRobot object 
               int sonarPin=10;
		r.setPort("COM7"); // Sets the port to COM7
		r.connect(); 
		r.refreshAnalogPins(); // Cache the Analog pin information 
		double distance = r.getPing(sonarPin);
		System.out.println("Distance: "+distance+"cm");		
		r.close(); 

    }
    
}
