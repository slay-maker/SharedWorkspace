/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved. */
/* Open Source Software - may be modified and shared by FRC teams. The code */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project. */
/*----------------------------------------------------------------------------*/
package org.usfirst.frc.team5608.robot;
import edu.wpi.first.wpilibj.SampleRobot;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
@SuppressWarnings({ "deprecation", "unused" })
public class Robot extends SampleRobot {
	private TalonSRX left = new TalonSRX(1);
	private TalonSRX right = new TalonSRX(2);
	private Joystick leftS = new Joystick(0);
	private Joystick rightS = new Joystick(1);
	public Robot() {
	}
	@Override
	public void robotInit() {
	}
	@Override
	public void autonomous() {
	}
	@Override
	public void operatorControl() {
		double l;
		double r;
		while (isOperatorControl() && isEnabled()) {
			l = leftS.getY();
			r = rightS.getY();
			if(l < 0) {
				left.set(ControlMode.PercentOutput, -Math.pow(l, 2));
			} else {
				left.set(ControlMode.PercentOutput, Math.pow(-l, 2));
			}
			if(r < 0) {
				right.set(ControlMode.PercentOutput, Math.pow(r, 2));
			} else {
				right.set(ControlMode.PercentOutput, -Math.pow(-r, 2));
			}
		}
	}
	@Override
	public void test() {
	}
}
