// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.commands.ArcadeDrive;
import frc.robot.subsystems.Drivetrain;

public class RobotContainer {
    // Step 12-D: Create a Drivetrain subsystem object to control the robot's drivetrain
    // and Xbox controller object to get the joystick inputs for driving the robot
    private final Drivetrain drivetrain = new Drivetrain();
    private final CommandXboxController driverController = new CommandXboxController(0);

    public RobotContainer() {
        // Step 12-E: Set the default command for the drivetrain to be the ArcadeDrive command
        drivetrain.setDefaultCommand(new ArcadeDrive(drivetrain, 
            () -> -driverController.getLeftY(), 
            () -> driverController.getRightX()));
        configureBindings();
    }

    private void configureBindings() {
        // Step 12-F: Configure the button bindings for the Xbox controller
        SmartDashboard.putBoolean("Button A", false);
        SmartDashboard.putBoolean("Button B", false);
        SmartDashboard.putBoolean("Button X", false);
        SmartDashboard.putBoolean("Button Y", false);
        driverController.a().onTrue(Commands.runOnce(() -> SmartDashboard.putBoolean("Button A", true)));
        driverController.a().onFalse(Commands.runOnce(() -> SmartDashboard.putBoolean("Button A", false)));
        driverController.b().onTrue(Commands.runOnce(() -> SmartDashboard.putBoolean("Button B", true)));
        driverController.b().onFalse(Commands.runOnce(() -> SmartDashboard.putBoolean("Button B", false)));
        driverController.x().onTrue(Commands.runOnce(() -> SmartDashboard.putBoolean("Button X", true)));
        driverController.x().onFalse(Commands.runOnce(() -> SmartDashboard.putBoolean("Button X", false)));
        driverController.y().onTrue(Commands.runOnce(() -> SmartDashboard.putBoolean("Button Y", true)));
        driverController.y().onFalse(Commands.runOnce(() -> SmartDashboard.putBoolean("Button Y", false)));
    }

    public Command getAutonomousCommand() {
        return Commands.print("No autonomous command configured");   
    }
}
