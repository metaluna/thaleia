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

import org.junit.Test;

import static org.junit.Assert.*;

/**
 *
 * @author Simon Hardijanto
 */
public class LuminosityTest
{
  private Luminosity luminosity;
  
  @Test
  public void shouldPrintCapitalLetters()
  {
    assertEquals("0", Luminosity.N.toString());
    assertEquals("I", Luminosity.I.toString());
    assertEquals("II", Luminosity.II.toString());
    assertEquals("III", Luminosity.III.toString());
    assertEquals("IV", Luminosity.IV.toString());
    assertEquals("V", Luminosity.V.toString());
    assertEquals("VI", Luminosity.VI.toString());
    assertEquals("VII", Luminosity.VII.toString());
  }
}
