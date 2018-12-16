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

package org.projectthaleia.colony;

import com.google.inject.Inject;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import org.projectthaleia.colony.buildings.BuildingQueuePanel;

/**
 * The View on a Colony.
 * @author Simon Hardijanto
 */
public class ColonyPanel extends JPanel
{
  /** Sets the colony.
   * @param _colony the colony to display
   */
  public void setColony(final Colony _colony)
  {
    if (_colony == null) {
      throw new NullPointerException("Cannot set colony of colony panel " + this + " to null.");
    }
    
    if (this.colony != _colony) {
      this.colony = _colony;
      this.buildingQueuePanel.setBuildingQueue(_colony.getBuildingQueue());
    }
  }
  
  //---------------- PROTECTED ----------------

  //-------------- PACKAGE PRIVATE ------------
  @Inject
  ColonyPanel(final BuildingQueuePanel _buildungQueuePanel)
  {
    if (_buildungQueuePanel == null) {
      throw new NullPointerException("Cannot construct a colony panel "
                                   + "without a building queue panel.");
    }
    this.buildingQueuePanel = _buildungQueuePanel;
    this.tabbedPane = new JTabbedPane();
    this.initComponents();
  }
  
  //----------------  PRIVATE  ----------------
  private Colony colony;
 
  private final BuildingQueuePanel buildingQueuePanel;
  private final JTabbedPane tabbedPane;

  private void initComponents()
  {
    this.tabbedPane.addTab("Construction", this.buildingQueuePanel);
    
    this.add(this.tabbedPane, BorderLayout.CENTER);
  }
}
