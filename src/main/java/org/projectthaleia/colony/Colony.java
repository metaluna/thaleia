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
package org.projectthaleia.colony;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import org.projectthaleia.colony.buildings.BuildingQueue;
import org.projectthaleia.colony.buildings.IBuildable;
import org.projectthaleia.factions.Empire;
import org.projectthaleia.universe.planet.Planet;

/**
 *
 * @author Simon Hardijanto
 */
public class Colony
{
  /**
   * Name of the Colony
   */
  public String getName()
  {
    return this.name;
  }
  
  public void setName(final String _newName)
  {
    this.name = _newName;
  }
  
  public Planet getPlanet()
  {
    return this.planet;
  }
  
  /**
   * Population in 10k people.
   */
  public int getPopulation()
  {
    return this.population;
  }
  
  public BuildingQueue getBuildingQueue()
  {
    return this.buildingQueue;
  }
  
  public Empire getOwner()
  {
    return this.owner;
  }
  
  public void setOwner(final Empire _conqueror)
  {
    if (_conqueror == null) {
      throw new NullPointerException("Cannot set ownwer of colony " + 
              this + " to null.");
    }
    this.owner = _conqueror;
  }

  public void update()
  {
  }
  
  public int getFactoryOutput()
  {
    return 1;
  }

  public void addBuilding(final IBuildable _building, final int _amount)
  {
  }
 
  //---------------- PROTECTED ----------------
  
  //------------- PACKAGE PRIVATE -------------
  @Inject
  Colony(@Assisted final String _name, 
         @Assisted final Planet _planet,
         @Assisted final Empire _owner, 
         final BuildingQueue _buildingQueue)
  {
    this.name = _name;
    this.planet = _planet;
    this.owner = _owner;
    this.buildingQueue = _buildingQueue;
    
    this.population = 0;
  }
  
  
  void addPopulation(int _population)
  {
    if (_population < 1) {
      throw new IllegalArgumentException("Adding negative population is not allowed. Was " + _population);
    }
    this.population += _population;
  }
  
  //----------------  PRIVATE  ----------------
  /** Name of the colony*/
  private String name;
  /** Population in 10k people */
  private int population;
  /** The owning empire */
  private Empire owner;
  /** The planet the colony was built on */
  private final Planet planet;
  /** The building queue */
  private final BuildingQueue buildingQueue;

}
