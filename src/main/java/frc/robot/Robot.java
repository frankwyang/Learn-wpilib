// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

public class Robot extends TimedRobot {
    private Command m_autonomousCommand;

    private final RobotContainer m_robotContainer;

    // Step 1: Create motor controller objects for the left and right motors
    // Port numbers are just examples; replace them with the actual port numbers for your motors
    private Spark leftMotor1 = new Spark(0);
    private Spark leftMotor2 = new Spark(1);
    private Spark rightMotor1 = new Spark(2);
    private Spark rightMotor2 = new Spark(3);

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
         // Step 2: Set the speed of the left and right motors to move the robot forward
        // The speed value can be adjusted as needed (0.5 is 50% power)
        // Note: The right motors are set to negative because they are typically inverted in a differential drive setup
        // However, the Robot will go forever. We need to tie the motors to a joystick or controller input to control the robot's movement. For now, this will just make the robot move forward at a constant speed.
        leftMotor1.set(0.5);
        leftMotor2.set(0.5);
        rightMotor1.set(-0.5);
        rightMotor2.set(-0.5);
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
