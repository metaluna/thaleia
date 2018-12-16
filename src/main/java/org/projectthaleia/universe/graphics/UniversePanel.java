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

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import org.projectthaleia.game.graphics.GameGraphics;
import org.projectthaleia.universe.Position;

/**
 *
 * @author Simon Hardijanto
 */
public class UniversePanel extends JPanel
{
  @Override
  public void paintComponent(final Graphics _g)
  {
    final Graphics2D g = (Graphics2D) _g;
    
    // clear background
    g.setBackground(Color.BLACK);
    g.clearRect(0, 0, this.getWidth(), this.getHeight());
    
    final StarSystemGraphic starSystemGraphic = 
                                this.gameGraphics.getCurrentStarSystemGraphic();
    
    // don't continue if there's no star system set
    if (starSystemGraphic == null){
      return;
    }
    
    // paint planets etc in system
    final Position offset = new Position(this.center.x + this.scrolling.x,
                                         this.center.y + this.scrolling.y);
    starSystemGraphic.paint(g, offset);
  }
  
  //------------- PACKAGE PRIVATE -----------
  @Inject
  UniversePanel(@Assisted final GameGraphics _gameGraphics)
  {
    this.gameGraphics = _gameGraphics;
    this.center = new Position(0,0);
    this.scrolling = new Position(0,0);
    
    this.setOpaque(true);
    this.setFocusable(true);
    
  }

  /**
   * The current offset in pixels
   * @return the current offset in pixels
   */
  Position getOffset()
  {
    return this.scrolling;
  }
  
  /**
   * The center of the panel.
   * @return the center of the panel
   */
  Position getCenter()
  {
    return this.center;
  }
  
  /**
   * Sets the center of the panel.
   * @param _center the new center of the panel
   */
  void setCenter(final Position _center)
  {
    if (_center == null) {
      throw new NullPointerException("Trying to set center of universe panel to null.");
    }
    this.center = _center;
  }

  void scrollEast(final int _xTranslation)
  {
    this.scrolling = new Position(this.scrolling.x - _xTranslation, this.scrolling.y);
  }

  void scrollWest(final int _xTranslation)
  {
    this.scrolling = new Position(this.scrolling.x + _xTranslation, this.scrolling.y);
  }

  void scrollNorth(final int _yTranslation)
  {
    this.scrolling = new Position(this.scrolling.x, this.scrolling.y + _yTranslation);
  }

  void scrollSouth(final int _yTranslation)
  {
    this.scrolling = new Position(this.scrolling.x, this.scrolling.y - _yTranslation);
  }

  //---------------- PRIVATE ----------------
  private final GameGraphics gameGraphics;

  private Position center;
  private Position scrolling;

}
