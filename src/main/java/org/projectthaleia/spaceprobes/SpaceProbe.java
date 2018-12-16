/*
 * The MIT License
 *
 * Copyright 2012 Simon Hardijanto.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package org.projectthaleia.spaceprobes;

/**
 * Basic space probe. A space probe always belongs to exactly one 
 * {@link SpaceProbeGroup} and cannot be operated without one.
 * @author Simon Hardijanto
 */
public class SpaceProbe
{

  // Attributes taken from ProbeClass ----------------------------------------

  /**
   * Name of the probe's class
   * @return the probe's class name. Is never empty.
   */
  public String getClassName()
  {
    return this.probeClass.getName();
  }
  
  /**
   * Size of the ship class
   * @return the size of the ship. Is never negative.
   */
  public int getSize()
  {
    return this.probeClass.getSize();
  }
  
  /**
   * Maximum speed of this ship. Will be reduced if damaged.
   * @return the maximum speed of this ship. Is never negative.
   */
  public int getMaximumSpeed()
  {
    return this.probeClass.getSpeed();
  }
  
  // Own attributes ---------------------------------------------------------
  /**
   * Name of the ship
   * @return the name of this ship. Is never empty.
   */
  public String getName()
  {
    return this.name;
  }
  
  /**
   * Changes the name of the ship
   * @param _name the new name. Must not be empty.
   */
  public void setName(final String _name)
  {
    this.name = _name;
    
    validate();
  }
  
  /**
   * The group this ship belongs to currently.
   * @return the group of this ship. Cannot move if set to 
   * {@link org.projectthaleia.spaceprobes.SpaceProbeGroup#NO_GROUP}.
   */
  public SpaceProbeGroup getGroup()
  {
    return this.group;
  }
  
  /**
   * Moves this ship to another space probe group.
   * @param _group the new group.
   */
  public void setGroup(SpaceProbeGroup _group)
  {
    if (_group == null) {
      throw new NullPointerException(this.getName() +  "'s group must not be "
              + "set to null. Was " + this.getGroup().getName());
    }
    
    this.group = _group;
  }
  
  public static SpaceProbe generateSpaceProbe(final SpaceProbeGroup _group)
  {
    final ProbeClass probeClass = ProbeClass.generateProbeClass();
    final SpaceProbe result = new SpaceProbe(probeClass, probeClass.getName() + "-A", _group);
    return result;
  }
  
  //---------------- PROTECTED ----------------

  //-------------- PACKAGE PRIVATE ------------
  SpaceProbe(final ProbeClass _probeClass, final String _name, final SpaceProbeGroup _spaceProbeGroup)
  {
    this.probeClass = _probeClass;
    this.name = _name;
    this.group = _spaceProbeGroup;
    
    validate();
  }
 
  //----------------  PRIVATE  ----------------
  private final ProbeClass probeClass;
  private String name;
  private SpaceProbeGroup group;
  
  private void validate()
  {
    if (this.getName() == null) {
      throw new NullPointerException("Name of a ship must not be null (" 
              + this.getClassName() + ").");
    }
    if (this.getName().trim().equals("")) {
      throw new IllegalArgumentException("Name of a ship must not be empty (" 
              + this.getClassName() + ").");
    }
  }
          
}
