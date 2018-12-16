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

import com.google.inject.Inject;
import java.awt.CardLayout;
import javax.swing.JPanel;
import org.projectthaleia.factions.Empire;
import org.projectthaleia.spaceprobes.SpaceProbeGroup;
import org.projectthaleia.spaceprobes.SpaceProbeGroupInfoPanel;
import org.projectthaleia.universe.StarSystem;
import org.projectthaleia.universe.graphics.StarSystemInfoPanel;
import org.projectthaleia.universe.planet.Planet;
import org.projectthaleia.universe.planet.PlanetInfoPanel;

/**
 *
 * @author Simon Hardijanto
 */
public class InfoPanel extends JPanel
{
  
  public void display(final StarSystem _starSystem)
  {
    this.starSystemInfoPanel.setStarSystem(_starSystem);
    this.showPanel(STAR_SYSTEM);
  }
  
  public void display(final Planet _planet, final Empire _viewingEmpire)
  {
    this.planetInfoPanel.setPlanet(_planet, _viewingEmpire);
    this.showPanel(PLANET);
  }
  
  public void display(final SpaceProbeGroup _spaceProbeGroup)
  {
    this.spaceProbeGroupInfoPanel.setSpaceProbeGroup(_spaceProbeGroup);
    this.showPanel(SPACE_PROBE_GROUP);
  }
  
  //---------------- PROTECTED ----------------

  //-------------- PACKAGE PRIVATE ------------
  @Inject
  InfoPanel(final StarSystemInfoPanel _starSystemInfoPanel, 
            final SpaceProbeGroupInfoPanel _spaceProbeGroupInfoPanel,
            final PlanetInfoPanel _planetInfoPanel)
  {
    this.starSystemInfoPanel = _starSystemInfoPanel;
    this.spaceProbeGroupInfoPanel = _spaceProbeGroupInfoPanel;
    this.planetInfoPanel = _planetInfoPanel;
    this.initComponents();
  }
  
  PlanetInfoPanel getPlanetInfoPanel()
  {
    return this.planetInfoPanel;
  }

  //----------------  PRIVATE  ----------------
  private final StarSystemInfoPanel starSystemInfoPanel;
  private final SpaceProbeGroupInfoPanel spaceProbeGroupInfoPanel;
  private final PlanetInfoPanel planetInfoPanel;
  
  private static final String STAR_SYSTEM = "1";
  private static final String PLANET = "2";
  private static final String SPACE_PROBE_GROUP = "3";
  
  private void initComponents()
  {
    this.setLayout(new CardLayout());
    this.add(this.starSystemInfoPanel, STAR_SYSTEM);
    this.add(this.spaceProbeGroupInfoPanel, SPACE_PROBE_GROUP);
    this.add(this.planetInfoPanel, PLANET);
  }
  
  private void showPanel(final String _panelId)
  {
    CardLayout cl = (CardLayout) (this.getLayout());
    cl.show(this, _panelId);
  }
}
