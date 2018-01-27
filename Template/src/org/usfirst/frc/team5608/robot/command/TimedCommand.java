package org.usfirst.frc.team5608.robot.command;

/**
 * A {@link TimedCommand} will wait for a timeout before finishing.
 * {@link TimedCommand} is used to execute a command for a given amount of time.
 */
public class TimedCommand extends Command {
  /**
   * Instantiates a TimedCommand with the given name and timeout.
   *
   * @param name the name of the command
   * @param timeout the time the command takes to run (seconds)
   */
  public TimedCommand(String name, double timeout) {
    super(name, timeout);
  }

  /**
   * Instantiates a TimedCommand with the given timeout.
   *
   * @param timeout the time the command takes to run (seconds)
   */
  public TimedCommand(double timeout) {
    super(timeout);
  }

  /**
  * Ends command when timed out.
  */
  protected boolean isFinished() {
    return isTimedOut();
  }
}
