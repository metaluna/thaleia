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
public class SpaceProbeGroupGraphic implements IStarSystemGraphic
{

  // Implements IStarSystemGraphic --------------------------------------------
  @Override
  public void paint(final Graphics2D _g, final Position _offset)
  {
    final int radius = GROUP_RADIUS;
    final int width  = radius*2;
    _g.setColor(Color.CYAN);
    
    // print circle
    final int x = this.spaceProbeGroup.getPosition().x+_offset.x;
    final int y = this.spaceProbeGroup.getPosition().y+_offset.y;
    _g.fillOval(x-radius, y-radius, width, width);
    
    // print name
    final int fontHeight = 5;
    _g.drawString(this.spaceProbeGroup.getName(), x+width, y+width+fontHeight);
  }

  @Override
  public boolean hit(final Position _click)
  {
    Position pos = this.spaceProbeGroup.getPosition();
    
    final int radius = GROUP_RADIUS;
    final int width  = radius*2;
    Rectangle hitbox = new Rectangle(pos.x - radius, pos.y - radius, width, width);

    return hitbox.contains(_click.x, _click.y);
  }
  // End of IStarSystemGraphic ------------------------------------------------

  public SpaceProbeGroup getSpaceProbeGroup()
  {
    return this.spaceProbeGroup;
  }
  
  //---------------- PROTECTED ----------------

  //-------------- PACKAGE PRIVATE ------------
  @Inject
  SpaceProbeGroupGraphic(@Assisted final SpaceProbeGroup _spaceProbeGroup)
  {
    this.spaceProbeGroup = _spaceProbeGroup;
  }
  
  //----------------  PRIVATE  ----------------
  private static final int GROUP_RADIUS = 4;
  
  private final SpaceProbeGroup spaceProbeGroup;
}
