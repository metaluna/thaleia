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
public class Sun
{
  
  public Color getColor()
  {
    return this.mKClassification.getColor();
  }
  
  public MKClassification getMKClassification()
  {
    return this.mKClassification;
  }
  
  public static Sun generateSun()
  {
    Sun sun = new Sun(new MKClassification(SpectralType.M, 5, Luminosity.IV));
    return sun;
  }
  
  //--------------- PROTECTED ---------------
  
  //------------- PACKAGE PRIVATE -----------
  Sun(final MKClassification _mkClassification)
  {
    this.mKClassification = _mkClassification;
    
    validate();
  }
  
  //---------------- PRIVATE ----------------
  private final MKClassification mKClassification;
  
  private void validate()
  {
    if (this.mKClassification == null) {
      throw new NullPointerException(
              "MK classification of a sun must not be null. Sun: " + this);
    }
  }
}
