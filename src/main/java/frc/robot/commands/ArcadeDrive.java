// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Drivetrain;

/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
public class ArcadeDrive extends Command {
    // Step 12-A: Create a Drive command that takes the Drivetrain subsystem as a dependency
    private final Drivetrain drivetrain;
    // Step 12-C: We need to create functions to get the forward and rotation each time the command is run.
    // This come in the form of Supplier<Double> objects that will be passed in when the command is created.    
    private Supplier<Double> forwardSupplier;
    private Supplier<Double> rotationSupplier;

    public ArcadeDrive(Drivetrain drivetrain, 
            Supplier<Double> forwardSupplier, Supplier<Double> rotationSupplier) {
        // Step 12-B: Store the Drivetrain subsystem reference in a member variable
        this.drivetrain = drivetrain;
        this.forwardSupplier = forwardSupplier;
        this.rotationSupplier = rotationSupplier;
        // We also need to add the Drivetrain subsystem as a requirement so that the command scheduler knows that
        // this command uses the Drivetrain subsystem and can prevent other commands from using it at the same time
        addRequirements(drivetrain);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        // Step 12-D: Get the real time forward and rotation values from the suppliers each time the command is run
        double realTimeForward = forwardSupplier.get() * 0.6;
        double realTimeRotation = rotationSupplier.get() * 0.3;
        double leftSpeed = realTimeForward + realTimeRotation;
        double rightSpeed = realTimeForward - realTimeRotation;
        drivetrain.setMotorSpeeds(leftSpeed, rightSpeed);  
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
}
