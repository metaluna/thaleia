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
package org.projectthaleia.spaceprobes;

import org.junit.Before;
import org.junit.Test;
import org.projectthaleia.universe.Position;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 *
 * @author Simon Hardijanto
 */
public class SpaceProbeGroupGraphicTest
{
  private SpaceProbeGroupGraphic spaceProbeGroupGraphic;
  private SpaceProbeGroup mockSpaceProbeGroup;
  
  @Before
  public void setUp()
  {
    mockSpaceProbeGroup = mock(SpaceProbeGroup.class);
    spaceProbeGroupGraphic = new SpaceProbeGroupGraphic(mockSpaceProbeGroup);
  }

  @Test
  public void shouldNotHit()
  {
    Position groupPos = new Position(27,45);
    when(mockSpaceProbeGroup.getPosition()).thenReturn(groupPos);
    Position clickPos = new Position(45,27);
    
    boolean result = spaceProbeGroupGraphic.hit(clickPos);
    
    assertFalse(result);    
  }
  
  @Test
  public void shouldHit()
  {
    Position groupPos = new Position(27,45);
    when(mockSpaceProbeGroup.getPosition()).thenReturn(groupPos);
    Position clickPos = new Position(groupPos);
    
    boolean result = spaceProbeGroupGraphic.hit(clickPos);
    
    assertEquals(true, result);    
    
  }
}
