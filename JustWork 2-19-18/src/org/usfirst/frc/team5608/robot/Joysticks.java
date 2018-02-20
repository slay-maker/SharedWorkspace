package org.usfirst.frc.team5608.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;

public class Joysticks {
	public enum JoystickType{
		Attack, Extreme, Gamepad, Xbox
	}
	
	public enum ButtonAssignments{
		//TODO
	}
	
	@SuppressWarnings("unused")
	public Joysticks(int port, JoystickType type) {
		switch(type) {
		case Attack:
			Joystick Attack = new Joystick(port);
			break;
		case Extreme:
			Joystick Extreme = new Joystick(port);
			break;
		case Gamepad:
			XboxController Gamepad = new XboxController(port);
			break;
		case Xbox:
			XboxController Xbox = new XboxController(port);
			break;
		default:
			throw new Error("Joystick type must be defined in SmartDashboard");
		}
	}
	
	@SuppressWarnings("unused")
	public Joysticks(int port1, int port2, JoystickType type1, JoystickType type2) {
		switch(type1) {
		case Attack:
			Joystick Attack = new Joystick(port1);
			break;
		case Extreme:
			Joystick Extreme = new Joystick(port1);
			break;
		case Gamepad:
			XboxController Gamepad = new XboxController(port1);
			break;
		case Xbox:
			XboxController Xbox = new XboxController(port1);
			break;
		default:
			throw new Error("Joystick type must be defined in SmartDashboard");
		}
		switch(type2) {
		case Attack:
			Joystick Attack = new Joystick(port2);
			break;
		case Extreme:
			Joystick Extreme = new Joystick(port2);
			break;
		case Gamepad:
			XboxController Gamepad = new XboxController(port2);
			break;
		case Xbox:
			XboxController Xbox = new XboxController(port2);
			break;
		default:
			throw new Error("Joystick type must be defined in SmartDashboard");
		}
	}
}
