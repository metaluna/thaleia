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
package org.projectthaleia.universe.sun;

import java.awt.Color;

/**
 *
 * @author Simon Hardijanto
 */
public enum SpectralType
{
  O(new Color(154,175,255), 30000, 50000),
  B(new Color(202,215,255), 10000, 28000),
  A(new Color(248,247,255), 7500, 9750),
  F(new Color(252,255,211), 6000, 7350),
  G(new Color(255,252,161), 5000, 5900),
  K(new Color(255,163,81), 3500, 4850),
  M(new Color(255,97,81), 2000, 3350);

  public final Color color;
  public final int minTemp;
  public final int maxTemp;
  
  //--------------- PROTECTED ---------------

  //------------- PACKAGE PRIVATE -----------

  //---------------- PRIVATE ----------------
  private SpectralType(Color _color, int _minTemp, int _maxTemp)
  {
    this.color = _color;
    this.minTemp = _minTemp;
    this.maxTemp = _maxTemp;
  }
}
