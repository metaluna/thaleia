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

import org.junit.Before;
import org.junit.Test;
import org.projectthaleia.colony.buildings.BuildingQueue;
import org.projectthaleia.factions.Empire;
import org.projectthaleia.universe.planet.Planet;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 *
 * @author Simon Hardijanto
 */
public class ColonyTest
{
  private Colony colony;
  private Planet planet;
  private BuildingQueue buildingQueue;
  private Empire owner;

  @Before
  public void setUp()
  {
    planet = mock(Planet.class);
    buildingQueue = mock(BuildingQueue.class);
    owner = mock(Empire.class);
    colony = new Colony("Test Colony", planet, owner, buildingQueue);
  }

  @Test
  public void shouldGetName()
  {
    String expName = "Test Colony";
    String gotName = this.colony.getName();
    assertEquals(expName, gotName);
    
    String newName = "Other Colony";
    this.colony.setName(newName);
    
    expName = newName;
    gotName = this.colony.getName();
    assertEquals(expName, gotName);
  }
  
  @Test
  public void shouldGetPlanet()
  {
    assertThat(colony.getPlanet(), is(planet));
  }

  @Test
  public void shouldGetPopulation()
  {
    int expPopulation = 0;
    int gotPopulation = this.colony.getPopulation();
    assertEquals(expPopulation, gotPopulation);
  }
  
  @Test(expected=IllegalArgumentException.class)
  public void shouldNotAddNegativePopulation()
  {
    this.colony.addPopulation(-1);
  }
  
  @Test
  public void shouldAddPopulation()
  {
    int expPopulation = 2000;
    this.colony.addPopulation(expPopulation);
    
    int gotPopulation = this.colony.getPopulation();
    assertEquals(expPopulation, gotPopulation);
  }
  
  @Test
  public void shouldNotGrowOnUpdate()
  {
    int expPopulation = 0;
    
    this.colony.update();
    
    int gotPopulation = this.colony.getPopulation();
    assertEquals(expPopulation, gotPopulation);
  }
  
  @Test
  public void shouldGetOwnwer()
  {
    assertThat(colony.getOwner(), is(owner));
  }
  
  @Test
  public void shouldSetOwner()
  {
    Empire conqueror = mock(Empire.class);
    
    colony.setOwner(conqueror);
    
    assertThat(colony.getOwner(), is(conqueror));
  }
  
  @Test(expected=NullPointerException.class)
  public void shouldNotSetOwnerToNull()
  {
    colony.setOwner(null);
  }
}
