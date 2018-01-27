package org.usfirst.frc.team5608.robot.command;

/**
 * A {@link StartCommand} will call the {@link Command#start() start()} method of another command
 * when it is initialized and will finish immediately.
 */
public class StartCommand extends InstantCommand {
  /**
   * The command to fork.
   */
  private Command m_commandToFork;

  /**
   * Instantiates a {@link StartCommand} which will start the given command whenever its {@link
   * Command#initialize() initialize()} is called.
   *
   * @param commandToStart the {@link Command} to start
   */
  public StartCommand(Command commandToStart) {
    super("Start(" + commandToStart + ")");
    m_commandToFork = commandToStart;
  }

  protected void initialize() {
    m_commandToFork.start();
  }
}