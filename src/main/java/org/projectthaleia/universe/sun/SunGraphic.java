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

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import java.awt.Graphics2D;
import org.projectthaleia.universe.Position;
import org.projectthaleia.universe.graphics.IStarSystemGraphic;

/**
 *
 * @author Simon Hardijanto
 */
public class SunGraphic implements IStarSystemGraphic
{
  
  // Implements IStarSystemGraphic --------------------------------------------
  @Override
  public void paint(final Graphics2D _g, final Position _offset)
  {
    final int radius = SUN_RADIUS;
    final int width  = radius*2;
    _g.setColor(this.sun.getColor());
    _g.fillOval(_offset.x-radius, _offset.y-radius, width, width);
    
    final int fontHeight = 5;
    _g.drawString(this.sun.getMKClassification().toString(), _offset.x+width, _offset.y+width+fontHeight);
  }

  @Override
  public boolean hit(final Position _position)
  {
    return false;
  }
  // End of IStarSystemGraphic ------------------------------------------------
  
  public Sun getSun()
  {
    return this.sun;
  }

  //--------------- PROTECTED ---------------

  //------------- PACKAGE PRIVATE -----------
  @Inject
  SunGraphic(@Assisted final Sun _sun)
  {
    this.sun = _sun;
  }
  
  //---------------- PRIVATE ----------------
  private static final int SUN_RADIUS = 4;

  private final Sun sun;

}
