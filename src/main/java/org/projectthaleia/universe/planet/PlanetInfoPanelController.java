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

package org.projectthaleia.universe.planet;

import com.google.inject.Inject;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.projectthaleia.game.graphics.MainPanel;

/**
 * Handles all events happening on the {@link PlanetInfoPanel}.
 * @author Simon Hardijanto
 */
public class PlanetInfoPanelController
{

  //---------------- PROTECTED ----------------

  //-------------- PACKAGE PRIVATE ------------
  @Inject
  public PlanetInfoPanelController(final PlanetInfoPanel _panel, 
                            final MainPanel _mainPanel)
  {
    this.panel = _panel;
    this.mainPanel = _mainPanel;
    
    this.registerListeners();
  }
  
  //----------------  PRIVATE  ----------------
  private final PlanetInfoPanel panel;
  private final MainPanel mainPanel;
  
  private void registerListeners()
  {
    this.panel.registerFoundColonyBtnListener(new FoundColonyBtnListener());
    this.panel.registerShowColonyBtnListener(new ShowColonyBtnListener());
  }
  
  private class FoundColonyBtnListener implements ActionListener
  {
    @Override
    public void actionPerformed(ActionEvent e)
    {
      throw new UnsupportedOperationException();
    }
  }

  private class ShowColonyBtnListener implements ActionListener
  {
    @Override
    public void actionPerformed(ActionEvent e)
    {
      mainPanel.displayColony();
    }
  }
 
}
