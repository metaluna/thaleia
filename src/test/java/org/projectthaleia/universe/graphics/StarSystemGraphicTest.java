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
import org.projectthaleia.spaceprobes.SpaceProbeGraphicFactory;
import org.projectthaleia.spaceprobes.SpaceProbeGroup;
import org.projectthaleia.spaceprobes.SpaceProbeGroupGraphic;
import org.projectthaleia.universe.Position;
import org.projectthaleia.universe.StarSystem;
import org.projectthaleia.universe.sun.Sun;
import org.projectthaleia.universe.sun.SunGraphic;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 *
 * @author Simon Hardijanto
 */
public class StarSystemGraphicTest
{
  private StarSystemGraphic starSystemGraphic;
  private StarSystem mockStarSystem;
  private StarSystemGraphicFactory mockStarSystemGraphicFactory;
  private SpaceProbeGraphicFactory mockSpaceProbeGraphicFactory;
  
  @Before
  public void setUp()
  {
    mockStarSystemGraphicFactory = mock(StarSystemGraphicFactory.class);
    mockSpaceProbeGraphicFactory = mock(SpaceProbeGraphicFactory.class);
    mockStarSystem = mock(StarSystem.class);
    starSystemGraphic = new StarSystemGraphic(mockStarSystemGraphicFactory, mockSpaceProbeGraphicFactory, mockStarSystem);
  }

  @Test(expected=NullPointerException.class)
  public void shouldNotPaintWithoutGraphicsContext()
  {
    Position offset = new Position(0,0);
    
    starSystemGraphic.paint(null, offset);
  }
  
  @Test(expected=NullPointerException.class)
  public void shouldNotPaintWithoutOffset()
  {
    Graphics2D mockG = mock(Graphics2D.class);
    
    starSystemGraphic.paint(mockG, null);
  }
  
  @Test
  public void shouldCreateSunGraphic()
  {
    Sun mockSun = mock(Sun.class);
    when(mockStarSystem.getSun()).thenReturn(mockSun);
    
    starSystemGraphic = new StarSystemGraphic(mockStarSystemGraphicFactory, mockSpaceProbeGraphicFactory, mockStarSystem);
    
    verify(mockStarSystemGraphicFactory).create(mockSun);
  }
  
  @Test
  public void shouldPaintSun()
  {
    //given
    Sun mockSun = mock(Sun.class);
    when(mockStarSystem.getSun()).thenReturn(mockSun);
    SunGraphic mockSunGraphic = mock(SunGraphic.class);
    when(mockStarSystemGraphicFactory.create(mockSun)).thenReturn(mockSunGraphic);
    
    starSystemGraphic = new StarSystemGraphic(mockStarSystemGraphicFactory, mockSpaceProbeGraphicFactory, mockStarSystem);
    
    //when
    Graphics2D mockGraphics = mock(Graphics2D.class);
    Position mockPosition = mock(Position.class);
    starSystemGraphic.paint(mockGraphics, mockPosition);
    
    //then
    verify(mockSunGraphic).paint(mockGraphics, mockPosition);
  }

  @Test
  public void shouldGetStarSystem()
  {
    StarSystem gotStarSystem = starSystemGraphic.getStarSystem();
    assertEquals(mockStarSystem, gotStarSystem);
  }
  
  @Test
  public void shouldCreateSpaceProbeGraphic()
  {
    SpaceProbeGroup mockGroup = mock(SpaceProbeGroup.class);
    
    starSystemGraphic.spaceProbeGroupAdded(mockGroup);
    
    verify(mockSpaceProbeGraphicFactory).create(mockGroup);
  }
  
  @Test(expected=NullPointerException.class)
  public void shouldThrowExceptionWhenNullSpaceProbeIsAdded()
  {
    starSystemGraphic.spaceProbeGroupAdded(null);
  }
  
  @Test
  public void shouldRemoveSpaceProbeGraphic()
  {
    SpaceProbeGroup mockGroup = mock(SpaceProbeGroup.class);
    SpaceProbeGroupGraphic mockGroupGraphic = mock(SpaceProbeGroupGraphic.class);
    when(mockSpaceProbeGraphicFactory.create(mockGroup)).thenReturn(mockGroupGraphic);
    when(mockGroupGraphic.getSpaceProbeGroup()).thenReturn(mockGroup);
    
    starSystemGraphic.spaceProbeGroupAdded(mockGroup);
    
    starSystemGraphic.spaceProbeGroupRemoved(mockGroup);
  }

  @Test(expected=NullPointerException.class)
  public void shouldThrowExceptionWhenNullSpaceProbeIsRemoved()
  {
    starSystemGraphic.spaceProbeGroupRemoved(null);
  }
  
  @Test
  public void shouldPaintAddedSpaceProbe()
  {
    //given
    SpaceProbeGroup mockGroup = mock(SpaceProbeGroup.class);
    SpaceProbeGroupGraphic mockGroupGraphic = mock(SpaceProbeGroupGraphic.class);
    when(mockSpaceProbeGraphicFactory.create(mockGroup)).thenReturn(mockGroupGraphic);
    Sun mockSun = mock(Sun.class);
    when(mockStarSystem.getSun()).thenReturn(mockSun);
    SunGraphic mockSunGraphic = mock(SunGraphic.class);
    when(mockStarSystemGraphicFactory.create(mockSun)).thenReturn(mockSunGraphic);
    
    starSystemGraphic = new StarSystemGraphic(mockStarSystemGraphicFactory, mockSpaceProbeGraphicFactory, mockStarSystem);
    starSystemGraphic.spaceProbeGroupAdded(mockGroup);
    
    //when
    Graphics2D mockGraphics = mock(Graphics2D.class);
    Position mockPosition = mock(Position.class);
    starSystemGraphic.paint(mockGraphics, mockPosition);
    
    //then
    verify(mockGroupGraphic).paint(mockGraphics, mockPosition);
  }
}
