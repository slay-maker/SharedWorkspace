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
	private static final String kDefaultJoystick = "Logitech Extreme";
	private static final String kXboxJoystick = "Xbox";
	private Joystick m_joystickExtreme = new Joystick(0);
	private XboxController m_joystickXbox = new XboxController(1);
	private SendableChooser<String> m_joystickChooser = new SendableChooser<>();
	private TalonSRX m_leftWheelMotor = new TalonSRX(CANMap.kLeftWheelMotor.getPort());
	private TalonSRX m_rightWheelMotor = new TalonSRX(CANMap.kRightWheel.getPort());
	private TalonSRX m_armMotor = new TalonSRX(CANMap.kArms.getPort());
	private TalonSRX m_cartMotor1 = new TalonSRX(CANMap.kLifting1.getPort());
	private TalonSRX m_cartMotor2 = new TalonSRX(CANMap.kLifting2.getPort());
	private TalonSRX m_climbingMotor = new TalonSRX(CANMap.kClimbing.getPort());
	private TalonSRX m_leftGrabberMotor = new TalonSRX(CANMap.kLeftGrabber.getPort());
	private TalonSRX m_rightGrabberMotor = new TalonSRX(CANMap.kRightGrabber.getPort());
	private final double m_deadband = 0.02;
	private final double m_maxOutput = 1.0;
	private static final boolean kDriveSquared = false;
	private enum CANMap {
		kPDP(0), kArms(3), kLeftWheelMotor(5), kRightWheel(7), kLifting1(9), kLifting2(6), kClimbing(4), kLeftGrabber(1), kRightGrabber(2);
		int m_portNumber;
		CANMap(int port){
			this.m_portNumber = port;
		}
		public int getPort() {
			return this.m_portNumber;
		}
	}

	//	private void otherThanDrive() {
	//		if(liftingTrigger) {
	//			m_lifting1.set(ControlMode.PercentOutput, kLiftingOutput);
	//			m_lifting2.set(ControlMode.PercentOutput, kLiftingOutput);
	//		}
	//		if()
	//	}

	public Robot() {}

	@Override
	public void robotInit() {
		m_joystickChooser.addDefault("Logitech Extreme", kDefaultJoystick);
		m_joystickChooser.addObject("Xbox", kXboxJoystick);
		SmartDashboard.putData("Auto modes", m_joystickChooser);
	}

	@Override
	public void operatorControl() {
		String selectedJoystick = m_joystickChooser.getSelected();
		System.out.println("Auto selected: " + selectedJoystick);
		switch (selectedJoystick) {
		case kXboxJoystick:
			while (isOperatorControl() && isEnabled()) {
				arcadeDrive(m_joystickXbox.getRawAxis(5), combine(m_joystickXbox.getRawAxis(9), m_joystickXbox.getRawAxis(10)));
				Timer.delay(0.005);
			}
			break;
		default:
		case kDefaultJoystick:
			while (isOperatorControl() && isEnabled()) {
				arcadeDrive(m_joystickExtreme.getRawAxis(0), m_joystickExtreme.getRawAxis(2));
				Timer.delay(0.005);
			}
			break;
		}
	}

	@Override
	public void test() {

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

	private void arcadeDrive(double xSpeed, double zRotation) {
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
		m_leftWheelMotor.set(ControlMode.PercentOutput, limit(leftMotorOutput) * m_maxOutput);
		m_rightWheelMotor.set(ControlMode.PercentOutput, -limit(rightMotorOutput) * m_maxOutput);
	}
}
