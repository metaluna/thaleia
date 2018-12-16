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
package org.projectthaleia.universe.graphics;

import java.awt.Component;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import org.junit.Before;
import org.junit.Test;
import org.projectthaleia.factions.Empire;
import org.projectthaleia.game.Game;
import org.projectthaleia.game.graphics.GameGraphics;
import org.projectthaleia.game.graphics.InfoPanel;
import org.projectthaleia.spaceprobes.SpaceProbeGroup;
import org.projectthaleia.universe.Position;
import org.projectthaleia.universe.StarSystem;
import org.projectthaleia.universe.planet.Planet;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 *
 * @author Simon Hardijanto
 */
public class UniversePanelControllerTest
{
  UniversePanelController universePanelController;
  UniversePanel universePanel;
  InfoPanel infoPanel;
  GameGraphics gameGraphics;
  StarSystemGraphic starSystemGraphic;
  
  @Before
  public void setUp()
  {
    universePanel = mock(UniversePanel.class);
    infoPanel = mock(InfoPanel.class);
    gameGraphics = mock(GameGraphics.class);
    starSystemGraphic = mock(StarSystemGraphic.class);
    when(gameGraphics.getCurrentStarSystemGraphic()).thenReturn(starSystemGraphic);
    when(universePanel.getOffset()).thenReturn(new Position(0,0));
    when(universePanel.getCenter()).thenReturn(new Position(0,0));
    
    universePanelController = new UniversePanelController(universePanel, infoPanel, gameGraphics);
  }

  @Test
  public void shouldDisplayPlanetInfoOnMouseClickedWhenCentered()
  {
    // Given
    Position click = new Position(345,678);
    
    Planet planet = mock(Planet.class);
    when(starSystemGraphic.wasPlanetHit(click)).thenReturn(planet);
    Game game = mock(Game.class);
    when(gameGraphics.getGame()).thenReturn(game);
    Empire empire = mock(Empire.class);
    when(game.getEmpire()).thenReturn(empire);
    
    // When
    MouseEvent e = mock(MouseEvent.class);
    when(e.getX()).thenReturn(click.x);
    when(e.getY()).thenReturn(click.y);
    
    this.universePanelController.mouseClicked(e);
    
    // Then
    verify(infoPanel).display(planet, empire);
  }
  
  @Test
  public void shouldDisplayStarSystemInfoOnMouseClickedWhenCentered()
  {
    // Given
    Position click = new Position(345,678);
    StarSystem starSystem = mock(StarSystem.class);
    when(starSystemGraphic.getStarSystem()).thenReturn(starSystem);
    
    // When
    MouseEvent e = mock(MouseEvent.class);
    when(e.getX()).thenReturn(click.x);
    when(e.getY()).thenReturn(click.y);
    
    this.universePanelController.mouseClicked(e);
    
    // Then
    verify(infoPanel).display(starSystem);
  }
  
  @Test
  public void shouldDisplaySpaceProbeGroupInfoOnMouseClickedWhenCentered()
  {
    // Given
    Position click = new Position(345,678);
    
    SpaceProbeGroup spaceProbeGroup = mock(SpaceProbeGroup.class);
    when(starSystemGraphic.wasSpaceProbeGroupHit(click)).thenReturn(spaceProbeGroup);
    
    // When
    MouseEvent e = mock(MouseEvent.class);
    when(e.getX()).thenReturn(click.x);
    when(e.getY()).thenReturn(click.y);
    
    this.universePanelController.mouseClicked(e);
    
    // Then
    verify(infoPanel).display(spaceProbeGroup);
  }
  
  @Test
  public void shouldScrollEastOnKeyPressed()
  {
    KeyEvent e = mock(KeyEvent.class);
    when(e.getKeyCode()).thenReturn(KeyEvent.VK_RIGHT);
    
    universePanelController.keyPressed(e);
    
    verify(universePanel).scrollEast(anyInt());
  }

  @Test
  public void shouldScrollWestOnKeyPressed()
  {
    KeyEvent e = mock(KeyEvent.class);
    when(e.getKeyCode()).thenReturn(KeyEvent.VK_LEFT);
    
    universePanelController.keyPressed(e);
    
    verify(universePanel).scrollWest(anyInt());
  }

  @Test
  public void shouldScrollNorthOnKeyPressed()
  {
    KeyEvent e = mock(KeyEvent.class);
    when(e.getKeyCode()).thenReturn(KeyEvent.VK_UP);
    
    universePanelController.keyPressed(e);
    
    verify(universePanel).scrollNorth(anyInt());
  }

  @Test
  public void shouldScrollSouthOnKeyPressed()
  {
    KeyEvent e = mock(KeyEvent.class);
    when(e.getKeyCode()).thenReturn(KeyEvent.VK_DOWN);
    
    universePanelController.keyPressed(e);
    
    verify(universePanel).scrollSouth(anyInt());
  }

  @Test
  public void shouldRecenterWhenComponentResized()
  {
    Position expCenter = new Position(400, 500);
    when(universePanel.getWidth()).thenReturn(expCenter.x*2);
    when(universePanel.getHeight()).thenReturn(expCenter.y*2);
    
    ComponentEvent mockEvent = mock(ComponentEvent.class);
    
    universePanelController.componentResized(mockEvent);
    
    verify(universePanel).setCenter(expCenter);
  }

}