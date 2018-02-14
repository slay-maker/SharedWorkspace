package org.usfirst.frc.team5608.robot;
import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.Timer;
@SuppressWarnings({ "deprecation", "unused" })
public class Robot extends SampleRobot {
	RobotDrive m_robotDrive;
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
			m_robotDrive.tankDrive();
		}
	}
	
	@Override
	public void test() {
	}
}
