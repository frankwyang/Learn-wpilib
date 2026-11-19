// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Drivetrain;

/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
public class DriveForwardTime extends Command {
    // Step 11-A: Create a Drive command that takes the Drivetrain subsystem as a dependency
    // We need to store a reference to the Drivetrain subsystem so we can control the motors in the execute() method
    private final Drivetrain drivetrain;
    // Step 11-C: Create member variables to store the duration and speed parameters
    private double duration;
    private double speed;
    // Step 11-F: Create a timer variable to track how long the command has been running
    private double startTime;

    public DriveForwardTime(Drivetrain drivetrain, double duration, double speed) {
        // Step 11-B: Store the Drivetrain subsystem reference in a member variable
        // We are requiring caller to pass in the Drivetrain subsystem when creating this command, 
        //so we can store it in a member variable for later use
        this.drivetrain = drivetrain;
        this.duration = duration;
        this.speed = speed;
        // We also need to add the Drivetrain subsystem as a requirement so that the command scheduler knows that 
        // this command uses the Drivetrain subsystem and can prevent other commands from using it at the same time
        addRequirements(drivetrain);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        // Step 11-G: Initialize the timer when the command starts
        startTime = Timer.getFPGATimestamp();
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        // Step 11-D: Set the motor speeds to move the robot forward at the specified speed
        drivetrain.setMotorSpeeds(speed, speed);
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        // Step 11-E: Stop the motors when the command ends
        drivetrain.setMotorSpeeds(0, 0);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        // Step 11-H: Check if the command has been running for the specified duration
        return (Timer.getFPGATimestamp() - startTime) >= duration; 
    }
}
