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
package org.projectthaleia.universe.graphics;

import org.junit.Before;
import org.junit.Test;
import org.projectthaleia.universe.StarSystem;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 *
 * @author Simon Hardijanto
 */
public class SystemGraphicsLibraryTest
{
  private SystemGraphicsLibrary systemGraphicsLib;
  private StarSystemGraphicFactory mockFactory;
  
  @Before
  public void setUp()
  {
    mockFactory = mock(StarSystemGraphicFactory.class);
    systemGraphicsLib = new SystemGraphicsLibrary(mockFactory);
  }
  
  @Test(expected=NullPointerException.class)
  public void shouldNotCreateWithoutStarSystemsGraphicFactory()
  {
    systemGraphicsLib = new SystemGraphicsLibrary(null);
    throw new AssertionError("Validation of SystemGraphicsLibrary failed.");
  }

  @Test
  public void shouldGetNewStarSystem()
  {
    final StarSystem mockStarSystem = mock(StarSystem.class);
    final StarSystemGraphic mockGraphic = mock(StarSystemGraphic.class);
    when(mockFactory.create(mockStarSystem)).thenReturn(mockGraphic);
    
    StarSystemGraphic starSystemGraphic = systemGraphicsLib.get(mockStarSystem);
    
    assertEquals(mockGraphic, starSystemGraphic);
  }
  
  @Test
  public void shouldGetSameStarSystemGraphics()
  {
    final StarSystem mockStarSystem = mock(StarSystem.class);
    final StarSystemGraphic mockGraphic = mock(StarSystemGraphic.class);
    when(mockFactory.create(mockStarSystem)).thenReturn(mockGraphic);
    
    StarSystemGraphic starSystemGraphic1 = systemGraphicsLib.get(mockStarSystem);
    StarSystemGraphic starSystemGraphic2 = systemGraphicsLib.get(mockStarSystem);
    
    assertNotNull(starSystemGraphic1);
    assertEquals(starSystemGraphic1, starSystemGraphic2);
  }
  
  @Test
  public void shouldGetDifferentStarSystemGraphics()
  {
    final StarSystem mockStarSystem1 = mock(StarSystem.class);
    final StarSystem mockStarSystem2 = mock(StarSystem.class);
    
    final StarSystemGraphic mockGraphic1 = mock(StarSystemGraphic.class);
    final StarSystemGraphic mockGraphic2 = mock(StarSystemGraphic.class);
    when(mockFactory.create(mockStarSystem1)).thenReturn(mockGraphic1);
    when(mockFactory.create(mockStarSystem2)).thenReturn(mockGraphic2);
    
    StarSystemGraphic starSystemGraphic1 = systemGraphicsLib.get(mockStarSystem1);
    StarSystemGraphic starSystemGraphic2 = systemGraphicsLib.get(mockStarSystem2);
    
    assertNotNull(starSystemGraphic1);
    assertNotSame(starSystemGraphic1, starSystemGraphic2);
  }
  
  @Test
  public void shouldAddNewSystemGraphicAsStarSystemChangedListener()
  {
    final StarSystem mockStarSystem = mock(StarSystem.class);
    final StarSystemGraphic mockGraphic = mock(StarSystemGraphic.class);
    when(mockFactory.create(mockStarSystem)).thenReturn(mockGraphic);
    
    StarSystemGraphic starSystemGraphic = systemGraphicsLib.get(mockStarSystem);
    
    verify(mockStarSystem).addStarSystemChangedListener(mockGraphic);
  }
}
