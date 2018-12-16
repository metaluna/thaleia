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

package org.projectthaleia.astrogation;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import org.projectthaleia.spaceprobes.SpaceProbeGroup;
import org.projectthaleia.universe.Position;
import org.projectthaleia.universe.planet.Planet;

/**
 * Plots a course to intercept a planet on its orbit.
 * @author Simon Hardijanto
 */
public class PlanetCoursePlotter implements ICoursePlotter
{

  // ICoursePlotter implementation
  @Override
  public Position plot()
  {
    int x = 0;
    int y = 0;
    final Position start = this.spaceProbeGroup.getPosition();
    return new Position(x,y);
  }
  // End of ICoursePlotter implementation
  
  //---------------- PROTECTED ----------------

  //-------------- PACKAGE PRIVATE ------------
  @Inject
  PlanetCoursePlotter(@Assisted final SpaceProbeGroup _spaceProbeGroup, @Assisted final Planet _planet)
  {
    this.spaceProbeGroup = _spaceProbeGroup;
    this.planet = _planet;
  }

  //----------------  PRIVATE  ----------------
  private final SpaceProbeGroup spaceProbeGroup;
  private final Planet planet;
}
