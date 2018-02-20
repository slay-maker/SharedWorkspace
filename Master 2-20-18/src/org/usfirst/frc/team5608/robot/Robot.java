/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team5608.robot;

import edu.wpi.first.wpilibj.SampleRobot;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

@SuppressWarnings("deprecation")
public class Robot extends SampleRobot {
	private enum CANMap {
		kPDP(0), kLeftWheel(1), kRightWheel(2);
		int m_portNumber;
		CANMap(int port){
			this.m_portNumber = port;
		}
		public int getPort() {
			return this.m_portNumber;
		}
	}
	private static final String kDefaultJoystick = "Logitech Extreme";
	private static final String kXboxJoystick = "Xbox";
	private Joystick m_joystickExtreme = new Joystick(0);
	private XboxController m_joystickXbox = new XboxController(1);
	private SendableChooser<String> m_joystickChooser = new SendableChooser<>();
	private TalonSRX m_leftWheel = new TalonSRX(CANMap.kLeftWheel.getPort());
	private TalonSRX m_rightWheel = new TalonSRX(CANMap.kRightWheel.getPort());
	private final double m_deadband = 0.02;
	private final double m_maxOutput = 1.0;
	private static final boolean kDriveSquared = false;

	public Robot() {}

	@Override
	public void robotInit() {
		m_joystickChooser.addDefault("Logitech Extreme", kDefaultJoystick);
		m_joystickChooser.addObject("Xbox", kXboxJoystick);
		SmartDashboard.putData("Auto modes", m_joystickChooser);
	}

	@Override
	public void operatorControl() {
		String joystickSelected = m_joystickChooser.getSelected();
		System.out.println("Auto selected: " + joystickSelected);
		switch (joystickSelected) {
			case kXboxJoystick:
				while (isOperatorControl() && isEnabled()) {
					arcadeDrive(m_joystickXbox.getRawAxis(5), combine(m_joystickXbox.getRawAxis(9), m_joystickXbox.getRawAxis(10)));
					Timer.delay(0.005);
				}
				break;
			case kDefaultJoystick:
			default:
				while (isOperatorControl() && isEnabled()) {
					arcadeDrive(m_joystickExtreme.getRawAxis(0), m_joystickExtreme.getRawAxis(2));
					Timer.delay(0.005);
				}
				break;
		}
	}
	
	private double combine(double neg, double pos) {
		return 0;
	}
	
	private double limit(double value) {
	    if (value > 1.0) {
	      return 1.0;
	    }
	    if (value < -1.0) {
	      return -1.0;
	    }
	    return value;
	  }
	
	private double applyDeadband(double value, double deadband) {
	    if (Math.abs(value) > deadband) {
	      if (value > 0.0) {
	        return (value - deadband) / (1.0 - deadband);
	      } else {
	        return (value + deadband) / (1.0 - deadband);
	      }
	    } else {
	      return 0.0;
	    }
	  }
	
	public void arcadeDrive(double xSpeed, double zRotation) {
	    xSpeed = limit(xSpeed);
	    xSpeed = applyDeadband(xSpeed, m_deadband);
	    zRotation = limit(zRotation);
	    zRotation = applyDeadband(zRotation, m_deadband);
	    if (kDriveSquared) {
	      xSpeed = Math.copySign(xSpeed * xSpeed, xSpeed);
	      zRotation = Math.copySign(zRotation * zRotation, zRotation);
	    }
	    double leftMotorOutput;
	    double rightMotorOutput;
	    double maxInput = Math.copySign(Math.max(Math.abs(xSpeed), Math.abs(zRotation)), xSpeed);
	    if (xSpeed >= 0.0) {
	      if (zRotation >= 0.0) {
	        leftMotorOutput = maxInput;
	        rightMotorOutput = xSpeed - zRotation;
	      } else {
	        leftMotorOutput = xSpeed + zRotation;
	        rightMotorOutput = maxInput;
	      }
	    } else {
	      if (zRotation >= 0.0) {
	        leftMotorOutput = xSpeed + zRotation;
	        rightMotorOutput = maxInput;
	      } else {
	        leftMotorOutput = maxInput;
	        rightMotorOutput = xSpeed - zRotation;
	      }
	    }
	    m_leftWheel.set(ControlMode.PercentOutput, limit(leftMotorOutput) * m_maxOutput);
	    m_rightWheel.set(ControlMode.PercentOutput, -limit(rightMotorOutput) * m_maxOutput);
	  }
}
