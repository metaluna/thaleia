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
import java.text.DecimalFormat;

/**
 *
 * @author Simon Hardijanto
 */
public class MKClassification
{
  public SpectralType getSpectralType()
  {
    return this.spectralType;
  }
  
  public float getSpectralClass()
  {
    return this.spectralClass;
  }
  
  public Luminosity getLuminosity()
  {
    return this.luminosity;
  }
  
  public Color getColor()
  {
    return this.spectralType.color;
  }
  
  @Override
  public String toString()
  {
    // cuts off any .0 of the spectral classes float value
    String spectralClassStr = new DecimalFormat("0.#").format(this.spectralClass);
    String result = this.spectralType.toString() + 
                    spectralClassStr + 
                    this.luminosity.toString();
    return result;
  }
  
  //--------------- PROTECTED ---------------

  //------------- PACKAGE PRIVATE -----------
  MKClassification(final SpectralType _spectralType, 
                   final float _spectralClass , 
                   final Luminosity _luminosity)
  {
    this.spectralType = _spectralType;
    this.spectralClass = _spectralClass;
    this.luminosity = _luminosity;
  }
  
  //---------------- PRIVATE ----------------
  private final SpectralType spectralType;
  private final float spectralClass;
  private final Luminosity luminosity;

}
