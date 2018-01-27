package org.usfirst.frc.team5608.robot.command;

import org.usfirst.frc.team5608.robot.command.Command;
import org.usfirst.frc.team5608.robot.command.InstantCommand;

/**
 * This command will execute once, then finish immediately afterward.
 *
 * <p>Subclassing {@link InstantCommand} is shorthand for returning true from
 * {@link Command isFinished}.
 */
public class InstantCommand extends Command {
  public InstantCommand() {
  }

  /**
   * Creates a new {@link InstantCommand InstantCommand} with the given name.
   * @param name the name for this command
   */
  public InstantCommand(String name) {
    super(name);
  }

  protected boolean isFinished() {
    return true;
  }
}