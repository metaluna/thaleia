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
package org.projectthaleia.universe;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.projectthaleia.spaceprobes.SpaceProbeGroup;
import org.projectthaleia.universe.planet.Planet;
import org.projectthaleia.universe.sun.Sun;

/**
 *
 * @author Simon Hardijanto
 */
public class StarSystem
{
  
  public String getName()
  {
    return this.name;
  }
  
  public void setName(final String _name)
  {
    if (_name == null) {
      throw new NullPointerException("Name of star system " + this 
              + " must not be null.");
    }
    if (_name.trim().isEmpty()) {
      throw new NullPointerException("Name of star system " + this 
              + " must not be empty.");
    }
    
    this.name = _name;
  }
  
  /**
   * The number of planets in the system.
   * @return the number of planets. Is never negative.
   */
  public int getPlanetCount()
  {
    return this.planets.size();
  }

  /**
   * Adds a new planet to the system.
   * @param _planet the new planet. Must not be already present in the system.
   */
  public void addPlanet(final Planet _planet)
  {
    this.planets.add(_planet);
  }
  
  /**
   * A list of planets present in the system.
   * @return the list of all planets.
   */
  public List<Planet> getPlanets()
  {
    return Collections.unmodifiableList(this.planets);
  }
  
  /**
   * The primary sun of the system.
   * @return the primary sun
   */
  public Sun getSun()
  {
    return this.sun;
  }
  
  /**
   * Updates all objects in the system a certain step in time.
   * @param _delta the milliseconds passed since the last update
   */
  public void update(long _delta)
  {
    for (Planet p : this.planets) {
      p.update(_delta);
    }
    
  }
  
  /**
   * Adds a group of space probes to the system.
   * @param _group the created or moved group
   */
  public void addSpaceProbeGroup(final SpaceProbeGroup _group)
  {
    if (_group == null) {
      throw new NullPointerException("Cannot add null from space probe groups "
              + "in " + this);
    }
    
    this.spaceProbeGroups.add(_group);
    
    //notify listeners
    for (IStarSystemChangedListener listener : this.starSystemChangedListeners) {
      listener.spaceProbeGroupAdded(_group);
    }
  }
  
  /**
   * Removes a group of space probes from the system.
   * @param _group the deleted or removed group
   */
  public void removeSpaceProbeGroup(final SpaceProbeGroup _group)
  {
    if (_group == null) {
      throw new NullPointerException("Cannot remove null from space probe groups "
              + "in " + this);
    }

    boolean wasFound = this.spaceProbeGroups.remove(_group);
    
    if (!wasFound) {
      throw new IllegalArgumentException("Trying to remove space probe group in " 
              + this + " that is not present in this star system.");
    }

    //notify listeners
    for (IStarSystemChangedListener listener : this.starSystemChangedListeners) {
      listener.spaceProbeGroupRemoved(_group);
    }
  }
  
  /**
   * A list of all space probe groups present in the system.
   * @return an unmodifiable list of space probe groups
   */
  public List<SpaceProbeGroup> getSpaceProbeGroups()
  {
    return Collections.unmodifiableList(this.spaceProbeGroups);
  }

  /**
   * Adds a listener who needs to be notified about changes in the system.
   * @param _listener the new listener
   */
  public void addStarSystemChangedListener(final IStarSystemChangedListener _listener)
  {
    if (_listener == null) {
      throw new NullPointerException("IStarSystemChangedListener being added to "
              + this + " must not be null.");
    }
    this.starSystemChangedListeners.add(_listener);
  }
  
  /**
   * Removes a subscribed listener.
   * @param _listener the listener to be removed
   */
  public void removeStarSystemChangedListener(final IStarSystemChangedListener _listener)
  {
    if (_listener == null) {
      throw new NullPointerException("IStarSystemChangedListener being removed "
              + "from " + this + " must not be null.");
    }
    this.starSystemChangedListeners.remove(_listener);
  }
  
  /**
   * Creates a basic solar system.
   * @return the star system
   */
  public static StarSystem generateStarSystem()
  {
    final Sun sun = Sun.generateSun();
    final Planet p = Planet.generatePlanet();
    
    StarSystem result = new StarSystem("Test System", sun);
    result.addPlanet(p);
    return result;
  }
  
  //------------ PACKAGE PRIVATE ------------
  @Inject
  StarSystem(@Assisted String _name, @Assisted final Sun _sun)
  {
    this.name = _name;
    this.sun = _sun;
    this.planets = new ArrayList<Planet>();
    this.spaceProbeGroups = new ArrayList<SpaceProbeGroup>();
    
    this.starSystemChangedListeners = new ArrayList<IStarSystemChangedListener>(1);
            
    validate();
  }

  //---------------- PRIVATE ----------------
  private final List<Planet> planets;
  private final Sun sun;
  private final List<SpaceProbeGroup> spaceProbeGroups;

  private final List<IStarSystemChangedListener> starSystemChangedListeners;
  
  private String name;
  
  private void validate()
  {
    if (this.sun == null) {
      throw new NullPointerException(
              "A star system's sun must not be null. Star system: " + this);
    }
  }

}
