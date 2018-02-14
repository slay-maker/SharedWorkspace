package org.usfirst.frc.team5608.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.Joystick;

public class RobotDrive {
	public TalonSRX m_leftWheel = new TalonSRX(1);
	public TalonSRX m_rightWheel = new TalonSRX(2);
	public Joystick m_leftJoystick = new Joystick(0);
	public Joystick m_rightJoystick = new Joystick(1);
	private double m_lVal;
	private double m_rVal;
	
	public RobotDrive() {
		
	}
	
	public void tankDrive() {
		m_lVal = m_leftJoystick.getY();
		m_rVal = m_rightJoystick.getY();
		if(m_lVal < 0) {
			m_leftWheel.set(ControlMode.PercentOutput, -Math.pow(m_lVal, 2));
		} else {
			m_leftWheel.set(ControlMode.PercentOutput, Math.pow(-m_lVal, 2));
		}
		if(m_rVal < 0) {
			m_rightWheel.set(ControlMode.PercentOutput, Math.pow(m_rVal, 2));
		} else {
			m_rightWheel.set(ControlMode.PercentOutput, -Math.pow(-m_rVal, 2));
		}
	}
}
