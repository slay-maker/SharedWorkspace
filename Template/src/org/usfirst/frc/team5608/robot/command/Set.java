package org.usfirst.frc.team5608.robot.command;

import java.util.Enumeration;
import java.util.Vector;

@SuppressWarnings("all")
/**
 * A set.
 */
class Set {
  private Vector m_set = new Vector();

  public Set() {
  }

  public void add(Object o) {
    if (m_set.contains(o)) {
      return;
    }
    m_set.addElement(o);
  }

  public void add(Set s) {
    Enumeration stuff = s.getElements();
    for (Enumeration e = stuff; e.hasMoreElements(); ) {
      add(e.nextElement());
    }
  }

  public void clear() {
      m_set.clear();
  }

  public boolean contains(Object o) {
    return m_set.contains(o);
  }

  public Enumeration getElements() {
    return m_set.elements();
  }
}