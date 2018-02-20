package org.usfirst.frc.team5608.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SampleRobot;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.Timer;

@SuppressWarnings({ "deprecation", "unused" })
public class Robot extends SampleRobot {
	private TalonSRX m1 = new TalonSRX(3);
	private TalonSRX m2 = new TalonSRX(4);
	private TalonSRX left = new TalonSRX(1);
	private TalonSRX right = new TalonSRX(2);
	private Joystick leftS = new Joystick(0);
	private Joystick rightS = new Joystick(1);
	private double liftSpeeed = .7;
	
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
		while (isOperatorControl() && isEnabled()) {
			if(leftS.getRawButton(1)) {
				m1.set(ControlMode.PercentOutput, liftSpeeed);
				m2.set(ControlMode.PercentOutput, liftSpeeed);
			}
			else if(rightS.getRawButton(1)) {
				m1.set(ControlMode.PercentOutput, -liftSpeeed);
				m2.set(ControlMode.PercentOutput, -liftSpeeed);
			}
			else {
				m1.set(ControlMode.PercentOutput, 0);
				m2.set(ControlMode.PercentOutput, 0);
			}
			
			right.set(ControlMode.PercentOutput, -Math.pow(rightS.getY(),1));
			left.set(ControlMode.PercentOutput, Math.pow(leftS.getY(),1));
			Timer.delay(.005);
		}
	}
	
	public double trigger(boolean in) {
		if(in) return .3;
		else return 0;
	}

	@Override
	public void test() {
	}
}
