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
package org.projectthaleia.game;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import java.awt.Component;

/**
 *
 * @author Simon Hardijanto
 */
public class GameLoop implements Runnable
{
  
  @Override
  public void run()
  {
    // frameskip counter
    int loops;
    long nextTick = now();
    long tickBegin, timeLeft;
    
    while (this.game.isRunning()) {
      
      loops = 0;
      tickBegin = now();
      
      if (!this.game.isPaused()) {
        
        while (now() > nextTick && loops < this.gameSpeed.maxFrameskip) {
          
          this.game.update(this.gameSpeed.gameTimePerTick);
          nextTick += this.gameSpeed.skipTicks;
          ++loops;
          
        }
        
      }
      
      this.canvas.repaint();
      
//      timeLeft = this.gameSpeed.skipTicks-(now()-tickBegin)/100000;
//      try {
//        Thread.sleep(timeLeft);
//      } catch (InterruptedException e) {
//        System.err.print(e);
//      }
    }
    
    System.exit(0);
  }
  
  //--------------- PROTECTED ---------------

  //------------- PACKAGE PRIVATE -----------
  @Inject
  GameLoop(@Assisted final Game _game, @Assisted final Component _canvas)
  {
    this.game = _game;
    this.canvas = _canvas;
    this.gameSpeed = GameSpeed.FASTEST;
  }

  //---------------- PRIVATE ----------------
  private enum GameSpeed
  {
    NORMAL  (30, 60*60*24/30),
    FASTER  (30, 60*60*24/15),
    FASTEST (30, 60*60*24/7);
    
    GameSpeed(final int _ticksPerSecond, final int _gameTimePerTick)
    {
      this.ticksPerSecond = _ticksPerSecond;
      this.gameTimePerTick = _gameTimePerTick;
      this.maxFrameskip = this.ticksPerSecond/5;
      this.skipTicks = 1000000000L/this.ticksPerSecond;
    }
    
    /** No of game updates per second */
    final int ticksPerSecond;
    /** No of passed seconds in-game per update */
    final int gameTimePerTick;
    /** Length of one game update cycle */
    final long skipTicks;
    /** Number of skipped frames */
    final int maxFrameskip;
  }
          
  private final Game game;
  private final Component canvas;
  private final GameSpeed gameSpeed;
  
  private long now()
  {
    return System.nanoTime();
  }

}
