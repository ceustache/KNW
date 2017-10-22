/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servodegree;
import rxtxrobot.*; 
import java.util.Scanner;
/**
 *
 * @author Cris
 */
public class ServoDegree {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner key= new Scanner(System.in);
        RXTXRobot r = new ArduinoNano(); // Create RXTXRobot object 
        int degree=0;
		r.setPort("COM7"); // Set the port to COM7
		r.setVerbose(true); // Turn on debugging messages 
		r.connect(); 
		r.attachServo(RXTXRobot.SERVO1, 9); //Connect the servos to the Arduino 
		r.moveServo(RXTXRobot.SERVO1, 90); // Move Servo 1 to location 30 
                System.out.println("Enter Degrees: ");
                degree=key.nextInt();
                r.moveServo(RXTXRobot.SERVO1, degree); // Move Servo 1 to user degree
		r.close(); 
    }
    
}
