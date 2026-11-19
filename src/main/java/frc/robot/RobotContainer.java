// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.commands.ArcadeDrive;
import frc.robot.commands.DriveForwardTime;
import frc.robot.subsystems.Drivetrain;

public class RobotContainer {
    // Step 12-D: Create a Drivetrain subsystem object to control the robot's drivetrain
    // and Xbox controller object to get the joystick inputs for driving the robot
    private final Drivetrain drivetrain = new Drivetrain();
    private final CommandXboxController driverController = new CommandXboxController(0);

    // Step 13-A: Create a menu selection of autonomous modes
    private final SendableChooser<Command> autoChooser = new SendableChooser<>();
    private final SendableChooser<Command> blah = new SendableChooser<>();

    public RobotContainer() {
        // Step 12-E: Set the default command for the drivetrain to be the ArcadeDrive command
        drivetrain.setDefaultCommand(new ArcadeDrive(drivetrain, 
            () -> -driverController.getLeftY(), 
            () -> driverController.getRightX()));
        configureBindings();
        // Step 13-C: Call to create autonomous mode menu
        configureAutonomousChooser();
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

    // Step 13-B: Create autonomous mode menu allowing drive team to choose
    private void configureAutonomousChooser() {
        // setDefaultOption selects this mode automatically if no other option is chosen
        autoChooser.setDefaultOption("Drive Forward (Default)", 
                new DriveForwardTime(drivetrain, Constants.AUTO_FORWARD_DURATION, Constants.AUTO_FORWARD_SPEED));
        autoChooser.addOption("Do Nothing", Commands.none());
        SmartDashboard.putData("Auto Mode", autoChooser);
    }

    public Command getAutonomousCommand() {
        return autoChooser.getSelected();   
    }
}
