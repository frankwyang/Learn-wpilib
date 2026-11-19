// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Drivetrain extends SubsystemBase {

    // Step 10-A: Create motor controller objects for the left and right motors
    private Spark leftMotor1 = new Spark(Constants.LEFT_MOTOR_1_PORT);
    private Spark leftMotor2 = new Spark(Constants.LEFT_MOTOR_2_PORT);
    private Spark rightMotor1 = new Spark(Constants.RIGHT_MOTOR_1_PORT);
    private Spark rightMotor2 = new Spark(Constants.RIGHT_MOTOR_2_PORT);

    /** Creates a new Drivetrain. */
    public Drivetrain() {
        // step 10-C: Invert the right motors because they are typically mounted in the opposite direction of the left motors
        rightMotor1.setInverted(true); // Invert the right motors if necessary
        rightMotor2.setInverted(true); // Invert the right motors if necessary
    }

    // Step 10-B: Create a method to set the speed of the motors
    public void setMotorSpeeds(double leftSpeed, double rightSpeed) {
        leftMotor1.set(leftSpeed);
        leftMotor2.set(leftSpeed);
        rightMotor1.set(rightSpeed);
        rightMotor2.set(rightSpeed);
    }

    @Override
    public void periodic() {
        // step 10-D: Display the motor speeds on the SmartDashboard for debugging
        SmartDashboard.putNumber("Left Motor 1 Speed", leftMotor1.get());
        SmartDashboard.putNumber("Left Motor 2 Speed", leftMotor2.get());
        SmartDashboard.putNumber("Right Motor 1 Speed", rightMotor1.get());
        SmartDashboard.putNumber("Right Motor 2 Speed", rightMotor2.get());
    }
}
