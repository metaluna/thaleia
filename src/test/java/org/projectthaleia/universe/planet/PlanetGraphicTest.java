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
package org.projectthaleia.universe.planet;

import java.awt.Graphics2D;
import org.junit.Before;
import org.junit.Test;
import org.projectthaleia.universe.Position;

import static org.mockito.Mockito.*;

/**
 *
 * @author Simon Hardijanto
 */
public class PlanetGraphicTest
{
  PlanetGraphic planetGraphic;
  Planet mockPlanet;
  
  @Before
  public void setUp()
  {
    mockPlanet = mock(Planet.class);
    planetGraphic = new PlanetGraphic(mockPlanet);
  }

  @Test
  public void shouldPaintOrbit()
  {
    int radius = 5;
    when(mockPlanet.getSemiMajorAxis()).thenReturn(radius);
    Graphics2D mockG = mock(Graphics2D.class);
    Position offset = new Position(0,0);
    
    planetGraphic.paint(mockG, offset);
    
    verify(mockG).drawOval(-radius,-radius,2*radius,2*radius);
  }

  @Test
  public void shouldPaintPlanet()
  {
    int radius = 5;
    when(mockPlanet.getSemiMajorAxis()).thenReturn(radius);
    final double pos = Math.PI;
    when(mockPlanet.getPositionInRad()).thenReturn(pos);
    Graphics2D mockG = mock(Graphics2D.class);
    Position offset = new Position(0,0);

    planetGraphic.paint(mockG, offset);

    int planetRadius = 4;
    verify(mockG).fillOval(-radius-planetRadius,-planetRadius,2*planetRadius,2*planetRadius);
  }
  
  @Test
  public void shouldPaintAfterUpdate()
  {
    final int radius = 5;
    when(mockPlanet.getSemiMajorAxis()).thenReturn(radius);
    double firstPos = 0;
    double secondPos = Math.PI;
    when(mockPlanet.getPositionInRad()).thenReturn(firstPos).thenReturn(secondPos);
    final Graphics2D mockG = mock(Graphics2D.class);
    final Position offset = new Position(0,0);

    planetGraphic.paint(mockG, offset);
    planetGraphic.paint(mockG, offset);

    final int planetRadius = 4;
    verify(mockG).fillOval(radius-planetRadius,-planetRadius,2*planetRadius,2*planetRadius);
    verify(mockG).fillOval(-radius-planetRadius,-planetRadius,2*planetRadius,2*planetRadius);
    
  }
}
