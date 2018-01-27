package org.usfirst.frc.team5608.robot.command;

/**
 * This command will only finish if whatever {@link CommandGroup} it is in has no active children.
 * If it is not a part of a {@link CommandGroup}, then it will finish immediately. If it is itself
 * an active child, then the {@link CommandGroup} will never end.
 *
 * <p>This class is useful for the situation where you want to allow anything running in parallel
 * to finish, before continuing in the main {@link CommandGroup} sequence.
 */
public class WaitForChildren extends Command {
  protected boolean isFinished() {
    return getGroup() == null || getGroup().m_children.isEmpty();
  }
}