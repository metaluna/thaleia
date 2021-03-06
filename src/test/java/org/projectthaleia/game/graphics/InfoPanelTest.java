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
package org.projectthaleia.game.graphics;

import org.junit.Before;
import org.junit.Test;
import org.projectthaleia.factions.Empire;
import org.projectthaleia.spaceprobes.SpaceProbeGroup;
import org.projectthaleia.spaceprobes.SpaceProbeGroupInfoPanel;
import org.projectthaleia.universe.StarSystem;
import org.projectthaleia.universe.graphics.StarSystemInfoPanel;
import org.projectthaleia.universe.planet.Planet;
import org.projectthaleia.universe.planet.PlanetInfoPanel;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 *
 * @author Simon Hardijanto
 */
public class InfoPanelTest
{
  private InfoPanel infoPanel;
  private StarSystemInfoPanel mockStarSystemInfoPanel;
  private PlanetInfoPanel mockPlanetInfoPanel;
  private SpaceProbeGroupInfoPanel mockSpaceProbeGroupInfoPanel;
          
  @Before
  public void setUp()
  {
    mockStarSystemInfoPanel = mock(StarSystemInfoPanel.class);
    mockPlanetInfoPanel = mock(PlanetInfoPanel.class);
    mockSpaceProbeGroupInfoPanel = mock(SpaceProbeGroupInfoPanel.class);
    infoPanel = new InfoPanel(mockStarSystemInfoPanel, 
                              mockSpaceProbeGroupInfoPanel, 
                              mockPlanetInfoPanel);
  }

  @Test
  public void shouldDisplayStarSystem()
  {
    StarSystem mockStarSystem = mock(StarSystem.class);
    
    infoPanel.display(mockStarSystem);
    
    verify(mockStarSystemInfoPanel).setStarSystem(mockStarSystem);
    verify(mockStarSystemInfoPanel).setVisible(true);
  }

  @Test
  public void shouldDisplayPlanet()
  {
    Planet mockPlanet = mock(Planet.class);
    Empire mockEmpire = mock(Empire.class);
    
    infoPanel.display(mockPlanet, mockEmpire);
    
    verify(mockPlanetInfoPanel).setPlanet(mockPlanet, mockEmpire);
    verify(mockPlanetInfoPanel).setVisible(true);
  }

  @Test
  public void shouldDisplaySpaceProbeGroup()
  {
    SpaceProbeGroup mockSpaceProbeGroup = mock(SpaceProbeGroup.class);
    
    infoPanel.display(mockSpaceProbeGroup);
    
    verify(mockSpaceProbeGroupInfoPanel).setSpaceProbeGroup(mockSpaceProbeGroup);
    verify(mockSpaceProbeGroupInfoPanel).setVisible(true);
  }
}
