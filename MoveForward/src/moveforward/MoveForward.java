/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moveforward;
import rxtxrobot.*; 
/**
 *
 * @author Cris
 */
public class MoveForward {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        RXTXRobot r = new ArduinoNano(); // Create RXTXRobot object 
		r.setPort("COM7"); // Set port to COM7
		r.connect(); 
		r.runMotor(RXTXRobot.MOTOR1, 500, RXTXRobot.MOTOR2, -450,0); // Run both motors forward indefinitely 
		r.sleep(5000); // Pause execution for 5 seconds, but the motors keep running. 
		r.runMotor(RXTXRobot.MOTOR1,0,RXTXRobot.MOTOR2,0,0); // Stop both motors 
		r.close(); 
    }
    
}
