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
package org.projectthaleia.game.graphics;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import org.projectthaleia.universe.graphics.UniversePanel;
import org.projectthaleia.universe.graphics.UniversePanelController;
import org.projectthaleia.universe.planet.PlanetInfoPanelController;

/**
 *
 * @author Simon Hardijanto
 */
public class GameFrame extends JFrame
{
  public UniversePanel getUniversePanel()
  {
    return this.mainPanel.getUniversePanel();
  }
  
  //------------- PACKAGE-PRIVATE ------------
  @Inject
  GameFrame(final InfoPanel _infoPanel,
            final MainPanel _mainPanel,
            @Assisted final GameGraphics _gameGraphics)
  {
    super("Project Thaleia");
    this.infoPanel = _infoPanel;
    this.mainPanel = _mainPanel;
    this.initComponents(_gameGraphics);
  }
  
  //---------------- PRIVATE ----------------
  private final InfoPanel infoPanel;
  private final MainPanel mainPanel;
  
  private void initComponents(final GameGraphics _gameGraphics)
  {
    this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    this.setMinimumSize(new Dimension(640,480));
    
    this.mainPanel.setPreferredSize(new Dimension(800,600));
    this.add(this.mainPanel, BorderLayout.CENTER);
    
    this.infoPanel.display(_gameGraphics.getCurrentStarSystemGraphic().getStarSystem());
    this.add(this.infoPanel, BorderLayout.EAST);
    
    new PlanetInfoPanelController(this.infoPanel.getPlanetInfoPanel(), this.mainPanel);
    
    final UniversePanel universePanel = this.mainPanel.getUniversePanel();
    UniversePanelController controller = 
        new UniversePanelController(universePanel, this.infoPanel, _gameGraphics);
    universePanel.addMouseListener(controller);
    universePanel.addKeyListener(controller);
    universePanel.addComponentListener(controller);
    
    this.pack();
  }

}
