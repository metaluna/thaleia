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

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import org.projectthaleia.game.graphics.GameGraphics;
import org.projectthaleia.game.graphics.InfoPanel;
import org.projectthaleia.spaceprobes.SpaceProbeGroup;
import org.projectthaleia.universe.Position;
import org.projectthaleia.universe.planet.Planet;

/**
 *
 * @author Simon Hardijanto
 */
public class UniversePanelController extends MouseAdapter implements KeyListener, ComponentListener
{

  // MouseAdapter overrides ----------------------------------------------------
  @Override
  public void mouseClicked(final MouseEvent _e)
  {
    final StarSystemGraphic graphics = this.gameGraphics.getCurrentStarSystemGraphic();
    
    final Position click = this.translateClickedPosition(_e);
    
    SpaceProbeGroup spaceProbeGroup = graphics.wasSpaceProbeGroupHit(click);
    if (spaceProbeGroup != null) {
      this.infoPanel.display(spaceProbeGroup);
      return;
    }
    
    Planet planet = graphics.wasPlanetHit(click);
    if (planet != null) {
      this.infoPanel.display(planet, this.gameGraphics.getGame().getEmpire());
      return;
    }
    
    this.infoPanel.display(this.gameGraphics.getCurrentStarSystemGraphic().getStarSystem());
  }

  // KeyListener implementation ------------------------------------------------
  @Override
  public void keyTyped(final KeyEvent _e) {}

  @Override
  public void keyPressed(final KeyEvent _e)
  {
    if (_e.getKeyCode() == KeyEvent.VK_RIGHT) {
      this.universePanel.scrollEast(SCROLL_AMOUNT);
    } else if (_e.getKeyCode() == KeyEvent.VK_LEFT) {
      this.universePanel.scrollWest(SCROLL_AMOUNT);
    } else if (_e.getKeyCode() == KeyEvent.VK_UP) {
      this.universePanel.scrollNorth(SCROLL_AMOUNT);
    } else if (_e.getKeyCode() == KeyEvent.VK_DOWN) {
      this.universePanel.scrollSouth(SCROLL_AMOUNT);
    }
  }

  @Override
  public void keyReleased(final KeyEvent _e) {}

  // ComponentListener implementation ------------------------------------------
  @Override
  public void componentResized(final ComponentEvent e)
  {
    this.universePanel.setCenter(this.recalculateCenter());
  }
  @Override public void componentMoved(final ComponentEvent e) {}
  @Override public void componentShown(final ComponentEvent e) {}
  @Override public void componentHidden(final ComponentEvent e) {}

  // misc ----------------------------------------------------------------------
  public UniversePanelController(final UniversePanel _universePanel, final InfoPanel _infoPanel, final GameGraphics _gameGraphics)
  {
    this.universePanel = _universePanel;
    this.infoPanel = _infoPanel;
    this.gameGraphics = _gameGraphics;
  }
  
  //---------------- PROTECTED ----------------

  //-------------- PACKAGE PRIVATE ------------
  //----------------  PRIVATE  ----------------
  private static final int SCROLL_AMOUNT = 5;
  
  private final UniversePanel universePanel;
  private final InfoPanel infoPanel;
  private final GameGraphics gameGraphics;
  
  private Position translateClickedPosition(final MouseEvent _e)
  {
    int x = _e.getX();
    int y = _e.getY();
    
    //offset
    final Position offset = this.universePanel.getOffset();
    x -= offset.x;
    y -= offset.y;
    
    final Position center = this.universePanel.getCenter();
    x -= center.x;
    y -= center.y;
    
    //TODO Zoom
    
    return new Position(x, y);
  }

  private Position recalculateCenter()
  {
    int x = this.universePanel.getWidth()/2;
    int y = this.universePanel.getHeight()/2;
    return new Position(x, y);
  }
}
