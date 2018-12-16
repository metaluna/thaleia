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

import org.junit.Before;
import org.junit.Test;
import org.projectthaleia.colony.ColonyPanel;
import org.projectthaleia.universe.graphics.UniversePanel;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 *
 * @author Simon Hardijanto
 */
public class MainPanelTest 
{
  private MainPanel mainPanel;
  private UniversePanel universePanel;
  private ColonyPanel colonyPanel;
  
  @Before
  public void setUp()
  {
    universePanel = mock(UniversePanel.class);
    colonyPanel = mock(ColonyPanel.class);
    mainPanel = new MainPanel(universePanel, colonyPanel);
  }
  
  @Test
  public void shouldFirstDisplayUniverse()
  {
    verify(universePanel).setVisible(true);
  }

  @Test
  public void shouldDisplayUniverse()
  {
    mainPanel.displayUniverse();
    
    verify(universePanel, times(2)).setVisible(true);
  }

  @Test
  public void shouldDisplayColony()
  {
    mainPanel.displayColony();
    
    verify(colonyPanel).setVisible(true);
  }
  
  @Test
  public void shouldDisplayPreviousScreenOnScreenClosedEvent()
  {
    mainPanel.displayColony();
    
    mainPanel.screenClosed();
    
    verify(universePanel, times(2)).setVisible(true);
  }

}