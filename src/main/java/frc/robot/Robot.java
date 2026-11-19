// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

public class Robot extends TimedRobot {
    private Command m_autonomousCommand;

    private final RobotContainer m_robotContainer;

    // Step 3: Create a joystick object to control the robot
    private CommandXboxController driverController = new CommandXboxController(0);

    // Step 8: Create a variable to store the start time of the autonomous mode
    private double startTimer;

    public Robot() {
        m_robotContainer = new RobotContainer();
    }

    @Override
    public void robotPeriodic() {
        CommandScheduler.getInstance().run();
    }

    @Override
    public void disabledInit() {
    }

    @Override
    public void disabledPeriodic() {
    }

    @Override
    public void disabledExit() {
    }

    @Override
    public void autonomousInit() {
        // step 9: Initialize the start timer
        startTimer = Timer.getFPGATimestamp(); // Initialize the start timer

        m_autonomousCommand = m_robotContainer.getAutonomousCommand();

        if (m_autonomousCommand != null) {
            CommandScheduler.getInstance().schedule(m_autonomousCommand);
        }
    }

    @Override
    public void autonomousPeriodic() {
        // Step 7: We want the robot to move forward for 3 seconds at 60% speed and then stop. We can use a simple timer to achieve this.
        // We still need to start the timer only when the autonomous mode starts, so we will 
        // use the Timer class to get the current time and compare it to the start time.  
        double time = Timer.getFPGATimestamp(); // Get the current time in seconds since the robot started

        // step 10: Use the start timer to control the autonomous movement by subtracting the start time from the current time to get the elapsed time
        double elapsedTime = time - startTimer;

        if (elapsedTime < 3.0) { // If the time is less than 3 seconds, move forward
            System.out.println("Autonomous Elapsed time: " + elapsedTime); // Print the elapsed time to the console for debugging
            leftMotor1.set(0.6); // Set the speed of the first left motor
            leftMotor2.set(0.6); // Set the speed of the second left motor
            rightMotor1.set(-0.6); // Set the speed of the first right motor (inverted)
            rightMotor2.set(-0.6); // Set the speed of the second right motor (inverted)
        } else { // If the time is greater than or equal to 3 seconds, stop the robot
            leftMotor1.set(0.0); // Stop the first left motor
            leftMotor2.set(0.0); // Stop the second left motor
            rightMotor1.set(0.0); // Stop the first right motor
            rightMotor2.set(0.0); // Stop the second right motor
        }
    }

    @Override
    public void autonomousExit() {
    }

    @Override
    public void teleopInit() {
        if (m_autonomousCommand != null) {
            m_autonomousCommand.cancel();
        }
    }

    @Override
    public void teleopPeriodic() {
        // Step 4: Read the controller input to control the robot's movement
        // Get the controller values for forward/backward movement and turning
        // The left controller Y-axis controls forward/backward movement, and the right controller X-axis controls turning.
        // The values are scaled down to 60% for speed and 30% for turning to make the robot easier to control.
        // Note: The controller values range from -1.0 to 1.0, where -1.0 is full backward, 0 is neutral, and 1.0 is full forward.
        double speed = driverController.getLeftY() * 0.6; // Get the Y-axis value from the controller (forward/backward movement)
        double turn = driverController.getRightX() * 0.3; // Get the X-axis value from the controller (left/right turning)

         // Step 5: Calculate the speed for the left and right motors based on the controller input
        // The left motors will move at a speed of (speed + turn), and the right motors will move at a speed of (speed - turn).
        double leftSpeed = speed + turn; // Calculate the speed for the left motors
        double rightSpeed = speed - turn; // Calculate the speed for the right motors

        // Step 6: Set the speed of the left and right motors based on the calculated values
        // The left motors are set to positive speed, and the right motors are set to negative speed because they are typically inverted in a differential drive setup
        // Make sure to comment out code from Step 2 to avoid conflicting motor commands.
        // This is the end of teleop of the robot. The robot will now respond to joystick input for movement and turning.
        leftMotor1.set(leftSpeed); // Set the speed of the first left motor
        leftMotor2.set(leftSpeed); // Set the speed of the second left motor
        rightMotor1.set(-rightSpeed); // Set the speed of the first right motor (inverted)
        rightMotor2.set(-rightSpeed); // Set the speed of the second right motor (inverted)
        System.out.println("Teleop Left Speed: " + leftSpeed + ", \t\t Right Speed: " + rightSpeed); // Print the left and right speeds to the console for debugging
    }

    @Override
    public void teleopExit() {
    }

    @Override
    public void testInit() {
        CommandScheduler.getInstance().cancelAll();
    }

    @Override
    public void testPeriodic() {
    }

    @Override
    public void testExit() {
    }
}
