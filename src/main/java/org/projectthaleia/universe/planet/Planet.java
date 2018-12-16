/*
 * The MIT License
 *
 * Copyright 2011 Simon Hardijanto
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
package org.projectthaleia.universe.planet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.projectthaleia.colony.Colony;
import org.projectthaleia.factions.Empire;
import org.projectthaleia.universe.Position;

/**
 *
 * @author Simon Hardijanto
 */
public class Planet
{
  /**
   * The orbit's semi major axis in kilometers.
   */
  public int getSemiMajorAxis()
  {
    return this.semiMajorAxis;
  }
  
  /**
   * The orbit's eccentricity
   * @return a number between 0 and 1
   */
  public float getEccentricity()
  {
    return this.eccentricity;
  }
  
  /**
   * The time it takes the planet to return to its initial position in standard days.
   * @return the time it takes in days
   */
  public int getSidericPeriod()
  {
    return this.sidericPeriod;
  }
  
  /**
   * The current position of the planet on its orbit in radians.
   * @return the position in radians
   */
  public double getPositionInRad()
  {
    return this.position;
  }
  
  /**
   * The current position of the planet in km on a 2-dimensional plane through
   * the star system.
   * @return the position in km
   */
  public Position getPosition()
  {
    final int radius = this.getSemiMajorAxis();
    final double rads = this.getPositionInRad();
    final int x = (int) (radius * Math.cos(rads));
    final int y = (int) (radius * Math.sin(rads));
    Position result = new Position(x, y);
    
    return result;
  }
  
  public void update(final long _delta)
  {
    double timeInDays = _delta/(double) (60*60*24);
    double progress = timeInDays/this.sidericPeriod;
    double progressInRadians = progress * 2*Math.PI;
    this.position += progressInRadians;
  }
  
  public void addColony(final Colony _colony)
  {
    if (_colony == null) {
      throw new NullPointerException("Cannot add null as a colony to " + this);
    }
    
    this.colonies.add(_colony);
  }

  public List<Colony> getColonies()
  {
    return Collections.unmodifiableList(this.colonies);
  }

  /**
   * Returns the colony of an empire if there is one.
   * Otherwise <code>null</code> will be returned.
   * @param _empire the colony's owner
   * @return the colony or <code>null</code>
   */
  public Colony getColonyOf(final Empire _empire)
  {
    for (Colony c : this.colonies) {
      if (c.getOwner() == _empire) {
        return c;
      }
    }
    
    return null;
  }
  
  public static Planet generatePlanet()
  {
    Planet p = new Planet(100, 0, 365, 0);
    return p;
  }
  
  //------------- PACKAGE PRIVATE -----------  
  Planet(int _semiMajorAxis, float _eccentricity, int _sidericPeriod, double _startingPosition)
  {
    this.semiMajorAxis = _semiMajorAxis;
    this.eccentricity = _eccentricity;
    this.sidericPeriod = _sidericPeriod;
    this.startingPosition = _startingPosition;
    this.colonies = new ArrayList<Colony>();
    
    this.position = this.startingPosition;
    
    validate();
  }
  
  //---------------- PRIVATE ----------------
  /** Unit: km */
  private final int semiMajorAxis;
  /** Range: 0 - 1 */
  private final float eccentricity;
  /** Unit: days */
  private final int sidericPeriod;
  /** Unit: radians */
  private final double startingPosition;

  private final List<Colony> colonies;
  
  private double position;
  
  private void validate()
  {
    if (this.semiMajorAxis <= 0) {
      throw new IllegalArgumentException(
              "Semi major axis of the planet's orbit must not be < 0. Was " + this.semiMajorAxis);
    }
    if (this.eccentricity < 0 || this.eccentricity > 1) {
      throw new IllegalArgumentException(
              "Eccentricity of the planet's orbit has to between 0 and 1. Was " + this.eccentricity);
    }
    if (this.sidericPeriod <= 0) {
      throw new IllegalArgumentException(
              "Sideric period of the planet's orbit must not be < 0. Was " + this.sidericPeriod);
    }
    if (this.startingPosition < 0 || this.startingPosition > Math.PI*2) {
      throw new IllegalArgumentException(
              "Starting position has to be between 0 and " + Math.PI*2 + " (2π). Was " + this.startingPosition);
    }
  }

}
