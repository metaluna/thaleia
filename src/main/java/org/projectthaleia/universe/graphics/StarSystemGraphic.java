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
import java.awt.Graphics2D;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.projectthaleia.spaceprobes.SpaceProbeGraphicFactory;
import org.projectthaleia.spaceprobes.SpaceProbeGroup;
import org.projectthaleia.spaceprobes.SpaceProbeGroupGraphic;
import org.projectthaleia.universe.IStarSystemChangedListener;
import org.projectthaleia.universe.Position;
import org.projectthaleia.universe.StarSystem;
import org.projectthaleia.universe.planet.Planet;
import org.projectthaleia.universe.planet.PlanetGraphic;
import org.projectthaleia.universe.sun.Sun;
import org.projectthaleia.universe.sun.SunGraphic;

/**
 *
 * @author Simon Hardijanto
 */
public class StarSystemGraphic implements IStarSystemChangedListener
{

  // Implements IStarSystemChangedListener -------------------------------------
  @Override
  public void spaceProbeGroupAdded(final SpaceProbeGroup _spaceProbeGroup)
  {
    if (_spaceProbeGroup == null) {
      throw new NullPointerException("Space probe group added to " + this 
              + " must not be null.");
    }
    
    SpaceProbeGroupGraphic groupGraphic = this.spaceProbeGraphicFactory.create(_spaceProbeGroup);
    this.spaceProbeGroupGraphics.add(groupGraphic);
  }

  @Override
  public void spaceProbeGroupRemoved(final SpaceProbeGroup _spaceProbeGroup)
  {
    if (_spaceProbeGroup == null) {
      throw new NullPointerException("Space probe group being removed from " 
              + this + " must not be null.");
    }

    for (Iterator<SpaceProbeGroupGraphic> it = this.spaceProbeGroupGraphics.iterator(); it.hasNext(); ) {
      SpaceProbeGroupGraphic groupGraphic = it.next();
      if (groupGraphic.getSpaceProbeGroup() == _spaceProbeGroup) {
        it.remove();
        break;
      }
    }
  }

  // End of IStarSystemChangedListener -----------------------------------------
  
  public StarSystem getStarSystem()
  {
    return starSystem;
  }
  
  public void paint(final Graphics2D _g, final Position _offset)
  {
    if (_g == null) {
      throw new NullPointerException("Graphics context must not be null.");
    }
    if (_offset == null) {
      throw new NullPointerException("Offset must not be null.");
    }
    
    // paint sun
    this.sunGraphic.paint(_g, _offset);
    
    // paint planets
    for (IStarSystemGraphic graphic : this.planetGraphics) {
      graphic.paint(_g, _offset);
    }
    
    // paint space probe groups
    for (IStarSystemGraphic group : this.spaceProbeGroupGraphics) {
      group.paint(_g, _offset);
    }
  }
  
  /**
   * Tests wether the sun is at the given position.
   * @param _position the position to check
   * @return the sun or <strong>null</strong>
   */
  public Sun wasSunHit(final Position _position)
  {
    if (this.sunGraphic.hit(_position)) {
      return this.sunGraphic.getSun();
    }
    
    return null;
  }

  /**
   * Tests wether there is a planet at the given position.
   * @param _position the position to check
   * @return the planet at the given position or <strong>null</strong>
   */
  public Planet wasPlanetHit(final Position _position)
  {
    
    for (PlanetGraphic pg : this.planetGraphics) {
      if (pg.hit(_position)) {
        return pg.getPlanet();
      }
    }
    return null;
  }
  
  /**
   * Tests wether there is a space probe group at the given position.
   * @param _position the position to check
   * @return  the space probe group or <strong>null</strong>
   */
  public SpaceProbeGroup wasSpaceProbeGroupHit(final Position _position)
  {
    for (SpaceProbeGroupGraphic spgg : this.spaceProbeGroupGraphics) {
      if (spgg.hit(_position)) {
        return spgg.getSpaceProbeGroup();
      }
    }
    
    return null;
  }

  //------------ PACKAGE PRIVATE --------------
  @Inject
  StarSystemGraphic(final StarSystemGraphicFactory _starSystemGraphicFactory, 
                    final SpaceProbeGraphicFactory _spaceProbeGroupGraphicFactory, 
                    @Assisted final StarSystem _starSystem)
  {
    this.starSystemGraphicFactory = _starSystemGraphicFactory;
    this.spaceProbeGraphicFactory = _spaceProbeGroupGraphicFactory;
    this.starSystem = _starSystem;
    this.planetGraphics = new LinkedList<PlanetGraphic>();
    this.spaceProbeGroupGraphics = new LinkedList<SpaceProbeGroupGraphic>();
    
    this.sunGraphic = this.starSystemGraphicFactory.create(this.starSystem.getSun());
    this.createPlanetGraphics();
    this.createSpaceProbeGroupGraphics();
  }
  
  //----------------  PRIVATE  ----------------
  private final StarSystemGraphicFactory starSystemGraphicFactory;
  private final SpaceProbeGraphicFactory spaceProbeGraphicFactory;
  private final StarSystem starSystem;
  private final SunGraphic sunGraphic;
  private final List<PlanetGraphic> planetGraphics;
  private final List<SpaceProbeGroupGraphic> spaceProbeGroupGraphics;

  private void createPlanetGraphics()
  {
    for (Planet p : this.starSystem.getPlanets()) {
      PlanetGraphic planetGraphic = this.starSystemGraphicFactory.create(p);
      this.planetGraphics.add(planetGraphic);
    }
  }
  
  private void createSpaceProbeGroupGraphics()
  {
    for (SpaceProbeGroup g : this.starSystem.getSpaceProbeGroups()) {
      SpaceProbeGroupGraphic groupGraphic = this.spaceProbeGraphicFactory.create(g);
      this.spaceProbeGroupGraphics.add(groupGraphic);
    }
  }
}
