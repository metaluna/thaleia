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
package org.projectthaleia.universe.planet;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import org.projectthaleia.universe.Position;
import org.projectthaleia.universe.graphics.IStarSystemGraphic;

/**
 *
 * @author Simon Hardijanto
 */
public class PlanetGraphic implements IStarSystemGraphic
{
  @Override
  public void paint(final Graphics2D _g, final Position _offset)
  {
    paintOrbit(_g, _offset);
    paintPlanet(_g, _offset);
  }

  @Override
  public boolean hit(final Position _click)
  {
    Position pos = this.planet.getPosition();
    
    final int radius = PLANET_RADIUS;
    final int width  = radius*2;
    Rectangle hitbox = new Rectangle(pos.x - radius, pos.y - radius, width, width);
    
    return hitbox.contains(_click.x, _click.y);
  }

  public Planet getPlanet()
  {
    return this.planet;
  }

  //---------------- PROTECTED ----------------
  
  //------------- PACKAGE PRIVATE -----------
  @Inject
  PlanetGraphic(@Assisted final Planet _planet)
  {
    this.planet = _planet;
  }

  //----------------  PRIVATE  ----------------
  private static final int PLANET_RADIUS = 4;
  private static final Color PLANET_COLOR = Color.GREEN;
  private static final Color ORBIT_COLOR = PLANET_COLOR;

  private final Planet planet;

  private void paintOrbit(final Graphics2D _g, final Position _offset)
  {
    final int radius = this.planet.getSemiMajorAxis();
    final int width  = radius*2;
    
    _g.setColor(ORBIT_COLOR);
    _g.drawOval(_offset.x-radius, _offset.y-radius, width, width);
  }

  private void paintPlanet(final Graphics2D _g, final Position _offset)
  {
    final int radius = this.planet.getSemiMajorAxis();
    final double rads = this.planet.getPositionInRad();
    final int x = (int) (radius * Math.cos(rads));
    final int y = (int) (radius * Math.sin(rads));
    
    _g.setColor(PLANET_COLOR);
    
    // print circle
    _g.fillOval(_offset.x + x - PLANET_RADIUS, _offset.y + y - PLANET_RADIUS, 
                PLANET_RADIUS*2, PLANET_RADIUS*2);
    
    // print name
    final int fontHeight = 5;
    final int nameX = x + _offset.x + 2*PLANET_RADIUS;
    final int nameY = y + _offset.y + 2*PLANET_RADIUS + fontHeight;
    _g.drawString("Planet", nameX, nameY);
  }
  
}
