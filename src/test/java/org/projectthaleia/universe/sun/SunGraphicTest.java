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
package org.projectthaleia.universe.sun;

import java.awt.Color;
import java.awt.Graphics2D;
import org.junit.Before;
import org.junit.Test;
import org.projectthaleia.universe.Position;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 *
 * @author Simon Hardijanto
 */
public class SunGraphicTest
{
  private SunGraphic sunGraphic;
  private Sun mockSun;
  
  @Before
  public void setUp()
  {
    mockSun = mock(Sun.class);
    sunGraphic = new SunGraphic(mockSun);
  }

  @Test
  public void shouldSetColorOnPaint()
  {
    Color expColor = Color.PINK;
    when(mockSun.getColor()).thenReturn(expColor);
    MKClassification mockMKClassification = mock(MKClassification.class);
    when(mockSun.getMKClassification()).thenReturn(mockMKClassification);
    Graphics2D mockG = mock(Graphics2D.class);
    Position offset = new Position(0, 0);
    
    sunGraphic.paint(mockG, offset);
    
    verify(mockSun).getColor();
    verify(mockG).setColor(expColor);
  }

  @Test
  public void shouldGetSun()
  {
    Sun gotSun = sunGraphic.getSun();
    assertEquals(mockSun, gotSun);
  }
}
