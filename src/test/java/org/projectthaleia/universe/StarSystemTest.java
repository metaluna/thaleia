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

import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.projectthaleia.spaceprobes.SpaceProbeGroup;
import org.projectthaleia.universe.planet.Planet;
import org.projectthaleia.universe.sun.Sun;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 *
 * @author Simon Hardijanto 
 */
public class StarSystemTest
{

  private StarSystem starSystem;
  private Sun mockSun;

  public StarSystemTest()
  {
  }

  @Before
  public void setUp()
  {
    mockSun = mock(Sun.class);
    starSystem = new StarSystem("Test System", mockSun);
  }

  @Test
  public void emptySystemShouldNotHavePlanets()
  {
    int expPlanetCount = 0;
    int gotPlanetCount = starSystem.getPlanetCount();
    assertEquals(expPlanetCount, gotPlanetCount);
  }

  @Test
  public void shouldAddPlanet()
  {
    Planet mockPlanet = mock(Planet.class);
    this.starSystem.addPlanet(mockPlanet);
    
    int expPlanetCount = 1;
    int gotPlanetCount = starSystem.getPlanetCount();
    assertEquals(expPlanetCount, gotPlanetCount);
    
    List planets = this.starSystem.getPlanets();
    assertEquals(mockPlanet, planets.get(0));
  }
  
  @Test
  public void shouldGetSun()
  {
    assertEquals(mockSun, starSystem.getSun());
  }
  
  @Test
  public void shouldNotifySystemChangedListenerOfAddedSpaceProbeGroup()
  {
    IStarSystemChangedListener mockListener = mock(IStarSystemChangedListener.class);
    starSystem.addStarSystemChangedListener(mockListener);
    
    SpaceProbeGroup mockGroup = mock(SpaceProbeGroup.class);
    starSystem.addSpaceProbeGroup(mockGroup);
    
    verify(mockListener).spaceProbeGroupAdded(mockGroup);
  }
  
  @Test
  public void shouldNotNotifyRemovedListener()
  {
    IStarSystemChangedListener mockListener = mock(IStarSystemChangedListener.class);
    starSystem.addStarSystemChangedListener(mockListener);
    starSystem.removeStarSystemChangedListener(mockListener);
    
    SpaceProbeGroup mockGroup = mock(SpaceProbeGroup.class);
    starSystem.addSpaceProbeGroup(mockGroup);
    
    verify(mockListener, times(0)).spaceProbeGroupAdded(mockGroup);
  }
  
  @Test
  public void shouldNotifySystemChangedListenerOfRemovedSpaceProbeGroup()
  {
    IStarSystemChangedListener mockListener = mock(IStarSystemChangedListener.class);
    starSystem.addStarSystemChangedListener(mockListener);
    SpaceProbeGroup mockGroup = mock(SpaceProbeGroup.class);
    starSystem.addSpaceProbeGroup(mockGroup);

    starSystem.removeSpaceProbeGroup(mockGroup);
    
    verify(mockListener).spaceProbeGroupRemoved(mockGroup);
  }
  
  @Test
  public void shouldUpdatePlanets()
  {
    Planet mockPlanet = mock(Planet.class);
    this.starSystem.addPlanet(mockPlanet);
    
    int exp = 1;
    this.starSystem.update(exp);
    
    verify(mockPlanet).update(exp);
  }
}
