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
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import org.projectthaleia.game.Game;
import org.projectthaleia.universe.graphics.StarSystemGraphic;
import org.projectthaleia.universe.graphics.SystemGraphicsLibrary;

/**
 *
 * @author Simon Hardijanto
 */
public class GameGraphics implements WindowListener
{

  @Override
  public void windowOpened(WindowEvent e)
  {
  }

  @Override
  public void windowClosing(WindowEvent e)
  {
    this.game.quit();
  }

  @Override
  public void windowClosed(WindowEvent e)
  {
    this.game.quit();
  }

  @Override
  public void windowIconified(WindowEvent e)
  {
  }

  @Override
  public void windowDeiconified(WindowEvent e)
  {
  }

  @Override
  public void windowActivated(WindowEvent e)
  {
  }

  @Override
  public void windowDeactivated(WindowEvent e)
  {
  }
  
  public StarSystemGraphic getCurrentStarSystemGraphic()
  {
    return this.currentStarSystemGraphic;
  }
  
  public Game getGame()
  {
    return this.game;
  }
  
  //------------- PACKAGE PRIVATE -----------
  @Inject
  GameGraphics(@Assisted final Game _game, final SystemGraphicsLibrary _sysGraphicsLibrary)
  {
    this.game = _game;
    this.sysGraphicsLibrary = _sysGraphicsLibrary;
    this.currentStarSystemGraphic = this.sysGraphicsLibrary.get(this.game.getHomeSystem());
  }
  
  //---------------- PRIVATE ----------------
  private final Game game;
  private final SystemGraphicsLibrary sysGraphicsLibrary;
  
  private StarSystemGraphic currentStarSystemGraphic;

}
