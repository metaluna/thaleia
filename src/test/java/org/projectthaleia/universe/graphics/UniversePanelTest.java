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

import java.awt.Graphics2D;
import org.junit.Before;
import org.junit.Test;
import org.projectthaleia.game.graphics.GameGraphics;
import org.projectthaleia.universe.Position;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 *
 * @author Simon Hardijanto
 */
public class UniversePanelTest
{
  private UniversePanel universe;
  private GameGraphics mockGameGraphics;
  
  @Before
  public void setUp()
  {
    this.mockGameGraphics = mock(GameGraphics.class);
    this.universe = new UniversePanel(this.mockGameGraphics);
  }
  
  @Test
  public void shouldPaintWithoutSystem()
          throws NullPointerException
  {
    Graphics2D mockG = mock(Graphics2D.class);
    this.universe.paint(mockG);
  }
  
  @Test
  public void shouldPaintSystem()
  {
    StarSystemGraphic mockStarSystemGraphic = mock(StarSystemGraphic.class);
    when(this.mockGameGraphics.getCurrentStarSystemGraphic()).thenReturn(mockStarSystemGraphic);
    Graphics2D mockG = mock(Graphics2D.class);

    this.universe.paintComponent(mockG);
    verify(this.mockGameGraphics).getCurrentStarSystemGraphic();
    verify(mockStarSystemGraphic).paint(eq(mockG), any(Position.class));
  }
  
  @Test
  public void shouldScrollViewEast()
  {
    int xTranslation = 5;
    Position exp = new Position(universe.getOffset().x - xTranslation, universe.getOffset().y);
    
    universe.scrollEast(xTranslation);
    
    Position got = universe.getOffset();
    assertEquals(exp, got);    
  }
  
  @Test
  public void shouldScrollViewWest()
  {
    int xTranslation = 5;
    Position exp = new Position(universe.getOffset().x + xTranslation, universe.getOffset().y);
    
    universe.scrollWest(xTranslation);
    
    Position got = universe.getOffset();
    assertEquals(exp, got);    
  }
  
  @Test
  public void shouldScrollViewNorth()
  {
    int yTranslation = 5;
    Position exp = new Position(universe.getOffset().x, universe.getOffset().y + yTranslation);
    
    universe.scrollNorth(yTranslation);
    
    Position got = universe.getOffset();
    assertEquals(exp, got);    
  }
  
  @Test
  public void shouldScrollViewSouth()
  {
    int yTranslation = 5;
    Position exp = new Position(universe.getOffset().x, universe.getOffset().y - yTranslation);
    
    universe.scrollSouth(yTranslation);
    
    Position got = universe.getOffset();
    assertEquals(exp, got);    
  }
  
  @Test(expected=NullPointerException.class)
  public void shouldNotSetCenterToNull()
  {
    universe.setCenter(null);
  }
  
  @Test
  public void shouldSetCenter()
  {
    Position exp = new Position(513, 724);
    
    universe.setCenter(exp);
    
    Position got = universe.getCenter();
    assertEquals(exp, got);
  }
}
