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

import java.util.List;
import org.hamcrest.collection.IsEmptyCollection;
import org.junit.Before;
import org.junit.Test;
import org.projectthaleia.colony.Colony;
import org.projectthaleia.factions.Empire;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 *
 * @author Simon Hardijanto
 */
public class PlanetTest
{
  private Planet planet;
  private final int semiMajorAxis = 42;
  private final float eccentricity = 0.0f;
  private final int sidericPeriod = 70;
  private final double startingPosition = 1.0;
  
  @Before
  public void setUp()
  {
    planet = new Planet(semiMajorAxis, eccentricity, sidericPeriod, startingPosition);
  }

  @Test
  public void shouldGetSemiMajorAxis()
  {
    assertEquals(semiMajorAxis, planet.getSemiMajorAxis());
  }

  @Test
  public void shouldGetEccentricity()
  {
    assertEquals(eccentricity, planet.getEccentricity(), 0);
  }

  @Test
  public void shouldGetSidericPeriod()
  {
    assertEquals(sidericPeriod, planet.getSidericPeriod());
  }

  @Test
  public void shouldInitiallyGetStartingPosition()
  {
    assertEquals(startingPosition, planet.getPositionInRad(), 0);
  }
  
  @Test
  public void shouldUpdatePosition()
  {
    int daysPassed = 7;
    long secondsPassed = 60*60*24*daysPassed;
    double expPosition = this.startingPosition + 2*Math.PI * ((double)daysPassed/(double)this.sidericPeriod);
    
    planet.update(secondsPassed);
    
    double gotPosition = planet.getPositionInRad();
    assertEquals(expPosition, gotPosition, 0);
  }
  
  @Test
  public void shouldAddColony()
  {
    Colony colony = mock(Colony.class);

    planet.addColony(colony);
  }
  
  @Test(expected=NullPointerException.class)
  public void shouldNotAddNullColony()
  {
    planet.addColony(null);
  }
  
  @Test
  public void shouldGetColonies()
  {
    Colony colony = mock(Colony.class);
    planet.addColony(colony);
    
    List<Colony> colonies = planet.getColonies();
    
    assertThat(colonies, hasItem(colony));
  }
  
  @Test
  public void shouldGetColoniesOfEmpire()
  {
    Empire empire = mock(Empire.class);
    Colony colony = mock(Colony.class);
    when(colony.getOwner()).thenReturn(empire);
    planet.addColony(colony);
    
    Colony gotColony=  planet.getColonyOf(empire);
    
    assertThat(gotColony, is(colony));
  }
  
  @Test
  public void shouldNotGetColoniesOfDifferentEmpire()
  {
    Empire empire = mock(Empire.class);
    Empire otherEmpire = mock(Empire.class);
    Colony otherColony = mock(Colony.class);
    when(otherColony.getOwner()).thenReturn(otherEmpire);
    planet.addColony(otherColony);
    
    Colony gotColony = planet.getColonyOf(empire);
    
    assertNull(gotColony);
  }
}
