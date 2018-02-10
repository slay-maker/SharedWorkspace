package org.usfirst.frc.team5608.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SampleRobot;
import org.usfirst.frc.team5608.robot.command.Command;
import org.usfirst.frc.team5608.robot.command.Scheduler;
import org.usfirst.frc.team5608.robot.subsystems.ExampleSubsystem;

@SuppressWarnings("deprecation")
public class Robot extends SampleRobot {
	public static final ExampleSubsystem kExampleSubsystem = new ExampleSubsystem();
	public static OI m_oi;
	Command m_ExampleCommand;

	@Override
	public void robotInit() {
		m_oi = new OI();
	}

	@Override
	public void disabled() {
		Scheduler.getInstance().run();
	}

	@Override
	public void operatorControl() {
		if (m_ExampleCommand != null) {
			m_ExampleCommand.cancel();
		}
		while (isOperatorControl() && isEnabled()) {
			Scheduler.getInstance().run();
		}
	}

	@Override
	public void autonomous() {
		String gameData = DriverStation.getInstance().getGameSpecificMessage();
		if (m_ExampleCommand != null) {
			m_ExampleCommand.start();
		}
		while (isAutonomous() && !isEnabled() && DriverStation.getInstance().getGameSpecificMessage().length() > 0) { //Ask: Why would we use the isAutonomous() call?
			gameData = DriverStation.getInstance().getGameSpecificMessage();
		}
		if (gameData.charAt(0) == 'L') {
			while (isAutonomous() && isEnabled()) {
				Scheduler.getInstance().run();
				//Left auto
			}
		} else {
			while (isAutonomous() && isEnabled()) {
				Scheduler.getInstance().run();
				//Right auto
			}
		}
	}

	@Override
	public void test() {
		while (isTest() && isEnabled()) {
			Scheduler.getInstance().run();
		}
	}
}
