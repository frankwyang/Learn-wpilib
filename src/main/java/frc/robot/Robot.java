// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

public class Robot extends TimedRobot {
    private Command m_autonomousCommand;

    private final RobotContainer m_robotContainer;

    // Step 1: Create motor controller objects for the left and right motors
    // Port numbers are just examples; replace them with the actual port numbers for your motors
    private Spark leftMotor1 = new Spark(0);
    private Spark leftMotor2 = new Spark(1);
    private Spark rightMotor1 = new Spark(2);
    private Spark rightMotor2 = new Spark(3);

    // Step 3: Create a joystick object to control the robot
    private CommandXboxController driverController = new CommandXboxController(0);

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
        m_autonomousCommand = m_robotContainer.getAutonomousCommand();

        if (m_autonomousCommand != null) {
            CommandScheduler.getInstance().schedule(m_autonomousCommand);
        }
    }

    @Override
    public void autonomousPeriodic() {
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

        // Step 2: Set the speed of the left and right motors to move the robot forward
        // The speed value can be adjusted as needed (0.5 is 50% power)
        // Note: The right motors are set to negative because they are typically inverted in a differential drive setup
        // However, the Robot will go forever. We need to tie the motors to a joystick or controller input to control the robot's movement. For now, this will just make the robot move forward at a constant speed.
        // leftMotor1.set(0.5);
        // leftMotor2.set(0.5);
        // rightMotor1.set(-0.5);
        // rightMotor2.set(-0.5);
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
