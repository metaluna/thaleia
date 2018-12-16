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
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 *
 * @author Simon Hardijanto
 */
public class MKClassificationTest
{
  private MKClassification mKClassification;
  private SpectralType spectralType;
  private float spectralClass;
  private Luminosity luminosity;
  
  @Before
  public void setUp()
  {
    this.spectralType = SpectralType.G;
    this.spectralClass = 5;
    this.luminosity = Luminosity.V;
    
    this.mKClassification = new MKClassification(spectralType, spectralClass, luminosity);
  }

  @Test
  public void shouldGetSpectralType()
  {
    assertEquals(spectralType, mKClassification.getSpectralType());
  }

  @Test
  public void shouldGetSpectralClass()
  {
    assertEquals(spectralClass, mKClassification.getSpectralClass(), 0);
  }

  @Test
  public void shouldGetLuminosity()
  {
    assertEquals(luminosity, mKClassification.getLuminosity());
  }

  @Test
  public void shouldGetSpectralTypesColor()
  {
    Color expColor = new Color(255,252,161);
    Color gotColor = this.mKClassification.getColor();
    assertEquals(expColor, gotColor);
  }

  @Test
  public void shouldPrintString()
  {
    String expString = "G5V";
    String gotString = this.mKClassification.toString();
    assertEquals(expString, gotString);
  }
}
