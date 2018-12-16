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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.projectthaleia.core.Vector;
import org.projectthaleia.universe.Position;

/**
 * Space probes must belong to exactly one of these groups and cannot operate without
 * one.
 * <p>
 * In case of an unmoving group, its direction vector must return 
 * <strong>{@link org.projectthaleia.core.Vector#NOT_MOVING}</strong> and a speed 
 * of <strong>0</strong>.
 * </p>
 * @author Simon Hardijanto
 */
public class SpaceProbeGroup
{
  //public static final SpaceProbeGroup NO_GROUP = 
  //                    new SpaceProbeGroup("Without a group", new Position(0,0));
  /**
   * The name of the group.
   * @return the name of the group. Is never empty.
   */
  public String getName()
  {
    return this.name;
  }
  
  /**
   * Sets a new name.
   * @param _name the new name must not be empty.
   */
  public void setName(final String _name)
  {
    this.name = _name;
    validate();
  }
  
  /**
   * The current position.
   * @return is never null.
   */
  public Position getPosition()
  {
    return this.position;
  }
  
  /**
   * Sets a new position.
   * @param _position Must not be null.
   */
  public void setPosition(final Position _position)
  {
    this.position = _position;
    validate();
  }
  
  /**
   * Current direction of this group.
   * @return Never returns <strong>NULL</strong>. 
   */  
  public Vector getDirection()
  {
    return this.direction;
  }

  /** 
   * Sets a new course.
   * @param _direction Sets the new direction. Must be set to 
   * {@link org.projectthaleia.core.Vector#NOT_MOVING} if stopped.
   */
  public void setDirection(final Vector _direction)
  {
    this.direction = _direction;
    validate();
  }

  /** 
   * The current speed of the group.
   * @return Is never negative.
   */
  public int getCurrentSpeed()
  {
    return this.currentSpeed;
  }

  /**
   * Sets the current speed.
   * @param _speed Must be neither negative nor higher than the maximum speed 
   * of the slowest probe in the group.  Must be set to 0 if stopped.
   */
  public void setCurrentSpeed(final int _speed)
  {
    this.currentSpeed = _speed;
    validate();
  }

  /**
   * The maximum speed of the group as a whole.
   * @return Is never negative and equals the maximum speed of the slowest ship in the group.
   * If damaged, will return the modified maximum speed. 
   */
  public int getMaximumSpeed()
  {
    return this.maximumSpeed;
  }
  
  /**
   * Halts the space probe group at its current position. Convenience method that
   * sets the speed to 0 and the direction to 
   * {@link org.projectthaleia.core.Vector#NOT_MOVING}.
   */
  public void stop()
  {
    this.setCurrentSpeed(0);
    this.setDirection(Vector.NOT_MOVING);
  }
  
  /**
   * Returns <strong>true</strong> if this group is stopped.
   * @return <strong>true</strong> if stopped
   */
  public boolean isStopped()
  {
   return this.getCurrentSpeed() == 0 && this.getDirection() == Vector.NOT_MOVING;
  }

  /**
   * Adds a new member to the space probe group. Check beforehand if the new 
   * member is already in the group.
   * @param _member the new member must not be null.
   */
  public void addSpaceProbe(final SpaceProbe _member)
  {
    if (_member == null) {
      throw new NullPointerException("Can not add null to " + this.getName());
    }
    
    if (this.spaceProbes.contains(_member)) {
      throw new IllegalArgumentException("Can not add the same space probe (" 
              + _member.getName() + ") twice to " + this.getName());
    }
    this.spaceProbes.add(_member);
    this.recalculateMaximumSpeed();
  }
  
  /**
   * Removes a member of the space probe group.
   * @param _member must not be null and has to be a member of the group.
   */
  public void removeSpaceProbe(final SpaceProbe _member)
  {
    if (_member == null) {
      throw new NullPointerException("Can not remove null from " + this.getName());
    }
    
    boolean wasMember = this.spaceProbes.remove(_member);
    
    if (!wasMember) {
      throw new IllegalArgumentException("Could not remove " + _member.getName() 
              + "(" + _member.getClassName() + " class). Not a member of " 
              + this.getName());
    }
    
    this.recalculateMaximumSpeed();
  }
  
  public static SpaceProbeGroup generateSpaceProbeGroup()
  {
    final SpaceProbeGroup result = new SpaceProbeGroup("Test Force 1", new Position(50,50));
    final SpaceProbe spaceProbe = SpaceProbe.generateSpaceProbe(result);
    result.addSpaceProbe(spaceProbe);
    return result;
  }
  //---------------- PROTECTED ----------------

  //-------------- PACKAGE PRIVATE ------------
  SpaceProbeGroup(final String _name, final Position _position)
  {
    this.name = _name;
    this.position = _position;
    
    this.maximumSpeed = 0;
    this.direction = Vector.NOT_MOVING;
    this.currentSpeed = 0;
    this.spaceProbes = new ArrayList<SpaceProbe>();

    validate();
  }
  
  List<SpaceProbe> getSpaceProbes()
  {
    return Collections.unmodifiableList(this.spaceProbes);
  }
  
  //----------------  PRIVATE  ----------------
  private String name;
  private Position position;
  private int maximumSpeed;
  private Vector direction;
  private int currentSpeed;
  private final List<SpaceProbe> spaceProbes;
  
  private void validate()
  {
    if (this.getName() == null) {
      throw new NullPointerException("Name of the " + this.getName() + " must not be null.");
    }
    if (this.getName().trim().equals("")) {
      throw new IllegalArgumentException("Name of the " + this.getName() + " must not be empty.");
    }
    
    if (this.getDirection() == null) {
      throw new NullPointerException("Trying to set new direction to null on " 
              + this.getName() + ".");
    }

    if (this.getCurrentSpeed() < 0) {
      throw new IllegalArgumentException("Current speed of " + this.getName() 
              + " must not be negative.");
    }
    if (this.getCurrentSpeed() > this.getMaximumSpeed()) {
      throw new IllegalArgumentException("Current speed of " + this.getName() 
              + " must not be higher than maximum speed: "  
              + this.getCurrentSpeed() + " > " + this.getMaximumSpeed() + ".");
    }
    
    if(this.position == null) {
      throw new NullPointerException("Trying to set new position to null on "
              + this.getName() + ".");
    }
  }

  private void recalculateMaximumSpeed()
  {
    int lowestSpeed;
    if (spaceProbes.isEmpty()) {
      lowestSpeed = 0;
    } else {
      Iterator<SpaceProbe> it = spaceProbes.iterator();
      lowestSpeed = it.next().getMaximumSpeed();

      while(it.hasNext()) {
        lowestSpeed = Math.min(lowestSpeed, it.next().getMaximumSpeed());
      }
    }
    maximumSpeed = lowestSpeed;
  }
}
