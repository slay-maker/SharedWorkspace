package org.usfirst.frc.team5608.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.VictorSP;
//import com.ctre.phoenix.motorcontrol.can.TalonSRX;
//import com.ctre.phoenix.motorcontrol.ControlMode;

@SuppressWarnings({ "deprecation", "unused" })
public class Robot extends SampleRobot {
	private Spark m1 = new Spark(1);
	private Spark m2 = new Spark(2);
	private VictorSP left = new VictorSP(0);
	private Spark right = new Spark(3);
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
				m1.set(liftSpeeed);
				m2.set(liftSpeeed);
			} else if(rightS.getRawButton(1)) {
				m1.set(liftSpeeed);
				m2.set(liftSpeeed);
			} else {
				m1.set(0);
				m2.set(0);
			}
			right.set(rightS.getY());
			left.set(leftS.getY());
			Timer.delay(.005);
		}
	}
	
	public double trigger(boolean in, double onVal) {
		if(in) return onVal;
		else return 0;
	}

	@Override
	public void test() {
	}
}
