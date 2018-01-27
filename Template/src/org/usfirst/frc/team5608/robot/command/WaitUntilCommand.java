package org.usfirst.frc.team5608.robot.command;

import edu.wpi.first.wpilibj.Timer;

/**
 * WaitUntilCommand - waits until an absolute game time. This will wait until the game clock reaches
 * some value, then continue to the next command.
 */
public class WaitUntilCommand extends Command {
  private double m_time;

  public WaitUntilCommand(double time) {
    super("WaitUntil(" + time + ")");
    m_time = time;
  }

  /**
   * Check if we've reached the actual finish time.
   */
  public boolean isFinished() {
    return Timer.getMatchTime() >= m_time;
  }
}