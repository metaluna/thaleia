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
import java.util.ArrayDeque;
import java.util.Deque;
import javax.swing.JComponent;
import org.projectthaleia.colony.ColonyPanel;
import org.projectthaleia.universe.graphics.UniversePanel;


/**
 * Container for all screens that can be shown in the main view.
 * @author Simon Hardijanto
 */
public class MainPanel extends JComponent implements ScreenClosedListener
{
  // ScreeClosedListener implementation ----------------------------------------
  @Override
  public void screenClosed()
  {
    this.displayLast();
  }
  // End of ScreenClosedListener -----------------------------------------------
  
  public void displayUniverse()
  {
    this.display(Screen.UNIVERSE);
  }
  
  public void displayColony()
  {
    this.display(Screen.COLONY);
  }
  //---------------- PROTECTED ----------------

  //-------------- PACKAGE PRIVATE ------------
  @Inject
  MainPanel(final UniversePanel _universePanel, final ColonyPanel _colonyPanel)
  {
    this.setLayout(new CardLayout());
    this.add(_universePanel, Screen.UNIVERSE.toString());
    this.add(_colonyPanel, Screen.COLONY.toString());
    
    this.screenStack = new ArrayDeque<Screen>();
    this.universePanel = _universePanel;
    
    this.display(Screen.UNIVERSE);
  }
  
  UniversePanel getUniversePanel()
  {
    return this.universePanel;
  }
  
  //----------------  PRIVATE  ----------------
  private final Deque<Screen> screenStack;
  private final UniversePanel universePanel;
  
  private void display(final Screen _screen)
  {
    this.screenStack.push(_screen);
    this.displayTop();
  }
  
  private void displayLast()
  {
    this.screenStack.pop();
    this.displayTop();
  }
  
  private void displayTop()
  {
    Screen screen = this.screenStack.peek();
    CardLayout cl = (CardLayout) this.getLayout();
    cl.show(this, screen.toString());
  }
}
