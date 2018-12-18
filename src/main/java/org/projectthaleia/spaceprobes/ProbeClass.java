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

package org.projectthaleia.spaceprobes;

import org.projectthaleia.core.Immutable;

/**
 * 
 * @author Simon Hardijanto
 */
@Immutable
public final class ProbeClass
{

  /** 
   * The name of this ship class.
   * @return the name of this ship class
   */
  public String getName()
  {
    return this.name;
  }
  
  /**
   * The size of this ship class. The unit is arbitrary and not defined.
   * @return the size of the ship class
   */
  public int getSize()
  {
    return this.size;
  }
  
  /**
   * The maximum speed of ship's of this class in m/s.
   * @return the maximum speed in m/s
   */
  public int getSpeed()
  {
    return this.speed;
  }
  
  public static ProbeClass generateProbeClass()
  {
    final ProbeClass result = new ProbeClass("Test Cruiser", 500, 8000);
    return result;
  }
  
  //---------------- PROTECTED ----------------
  
  //-------------- PACKAGE PRIVATE ------------
  ProbeClass(final String _name, final int _size, final int _speed)
  {
    this.name = _name;
    this.size = _size;
    this.speed = _speed;
  }
  
  //----------------  PRIVATE  ----------------
  /** Name of the class */
  private final String name;
  /** Size of the class in an arbitrary, not clearly defined unit */
  private final int size;
  /** Speed in m/s */
  private final int speed;
}
