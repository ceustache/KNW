/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package navigation_1;
import rxtxrobot.*; 
import java.util.Scanner;
/**
 *
 * @author Cris
 */
public class Navigation_1 {
    
    //VARIABLES/PINS
    
             public static int side;
             public static int sonarPinF=0;
             public static int sonarPinR=0;
             public static int sonarPinL=0;
             public static int irPin=0;
             public static int motorPin1=6;
             public static int motorPin2=5;
             public static int servoPin=0;
             public static int thermPin=0;
             public static int conducPin=0;
             public static double distanceF;
             public static double distanceR;
             public static double distanceL;
             public static double distanceIR;
             public static int gap = 5;//gap needed to stop DOUBLE CHECK
    
    //METHODS START
             
    //MOVE FORWARD METHOD
    public static void forward()
    {
        r.runMotor(RXTXRobot.MOTOR1, 500, RXTXRobot.MOTOR2, -500,0); // Run both motors forward indefinitely
    }
    
    //STOP METHOD
        public static void stop()
    {
        r.runMotor(RXTXRobot.MOTOR1, 0, RXTXRobot.MOTOR2, 0,0); // Stop both motors by setting speed to 0
    }
        
    //RIGHT TURN METHOD
    public static void turnRight()
    {
      r.runMotor(RXTXRobot.MOTOR1, 500, RXTXRobot.MOTOR2, 0,0); // Run both motors forward indefinitely
        r.sleep(3000); // Pause execution for 5 seconds, but the motors keep running. Test to find out time for turn. 
        stop();   
    }
    
    //LEFT TURN METHOD
    public static void turnLeft()
    {
        r.runMotor(RXTXRobot.MOTOR1, 0, RXTXRobot.MOTOR2, -500,0); // Run both motors forward indefinitely
        r.sleep(3000); // Pause execution for 5 seconds, but the motors keep running. Test to find out time for turn. 
        stop(); 
        
        
    }
    
    //FORWARD UNTIL WALL
    public static void untilWall()
    {
        while(distanceF>=gap)
               {
                   surroundings();
                   forward();
               }
        stop();
    }
    
    // TRACK SURROUNDINGS METHOD
    public static void surroundings()
    {
        r.refreshAnalogPins(); // Cache the Analog pin information 
        distanceF = r.getPing(sonarPinF);
        distanceR = r.getPing(sonarPinR);
        distanceL = r.getPing(sonarPinL);
       distanceIR = r.getPing(irPin);     
    }
    
    //GET THERMISTOR READING
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
    
    //CHECK TEMP METHOD
    public static void checkTemp()
    {
        int thermistorReading = getThermistorReading();
            //get the temp
            double slope=-4.550703907;
            double intercept=794.5506019;
            double temp=(thermistorReading-intercept)/slope;
            //Print the results
            System.out.println("---------------------------------------------");
            System.out.println("Thermistor");
            System.out.println("---------------------------------------------");
            System.out.println("The probe read the value: " + thermistorReading);
            System.out.println("In volts: " + (thermistorReading * (5.0/1023.0)));
            System.out.println("In degrees c: " + temp);
            System.out.println("---------------------------------------------");
    }
    
    //MOVE SERVO
    public static void moveServo(int degree)
    {      
       r.moveServo(RXTXRobot.SERVO1, degree); // Move Servo 1 to location 30 
    }
    
    //CHECK CONDUCTIVITY
    public static void conductivity()
    {      
       r.refreshAnalogPins(); // Cache the Analog pin information 
                r.refreshDigitalPins();
                int reading;
                reading=r.getConductivity();
                System.out.println("---------------------------------------------");
                System.out.println("Conductivity Reading: "+reading);
                System.out.println("---------------------------------------------");
    }
    
    //METHODS END
    public static RXTXRobot r; //Create RXTXRobot object 
    
    public static void main(String[] args) {//MAIN START
            
            //SETUP
             Scanner key= new Scanner(System.in);
             
             r = new ArduinoNano();
             r.setPort("COM5");
             r.setVerbose(true); // Turn on debugging messages 
             r.connect();
             //r.attachServo(RXTXRobot.SERVO1, servoPin); //Connect the servos to the Arduino 
             r.attachMotor(RXTXRobot.MOTOR1, motorPin1);
             r.attachMotor(RXTXRobot.MOTOR2, motorPin2);
             //NAVIGATION START!
             
           System.out.println("Enter side: ");
           String side=key.next();
           
           if(side.equals("right"))//right side
           {
               //start to barricade
               untilWall();//forward until wall
               turnLeft();//turn left
               untilWall();//forward until barricade
               //go to plaform and get temp
               while(distanceF<=gap)
               {
                   stop();      //stop until barricade is lifted
               }
               untilWall(); //move forward onto platform DOUBLE CHECK HOW TO GET ONTO PLATFORM AND STOP!!!
               checkTemp();
               //wall gap
               turnRight();
               while(distanceR<=7)//DOUBLE CHECK DISTANCE FROM WALL!
               {
                   forward();  //go forward until the robot sees a gap in the wall
               }
               stop();//stop at gap
               turnRight();//turn into gap
               //turn series
               untilWall();//forward until wall
               turnRight();// turn right
               untilWall();
               turnLeft();
               untilWall();
               turnLeft();
               //cross bridge
               untilWall();
               //sandbox
               turnLeft();
               untilWall();
               conductivity();
               r.close();
           }
           else//left side
           {
               //start to barricade
               untilWall();//forward until wall
               turnRight();//turn left
               untilWall();//forward until barricade
               //go to plaform and get temp
               while(distanceF<=gap)
               {
                   stop();      //stop until barricade is lifted
               }
               untilWall(); //move forward onto platform DOUBLE CHECK HOW TO GET ONTO PLATFORM AND STOP!!!
               checkTemp();
               //wall gap
               turnLeft();
               while(distanceL<=7)//DOUBLE CHECK DISTANCE FROM WALL!
               {
                   forward();  //go forward until the robot sees a gap in the wall
               }
               stop();//stop at gap
               turnLeft();//turn into gap
               //turn series
               untilWall();//forward until wall
               turnLeft();// turn right
               untilWall();
               turnRight();
               untilWall();
               turnRight();
               //cross bridge
               untilWall();
               //sandbox
               turnRight();
               untilWall();
               conductivity();
               r.close();
           }
           //NAVIGATION END!
    }//MAIN END
    
}
