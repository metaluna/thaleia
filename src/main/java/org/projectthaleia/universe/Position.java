/*
 * The MIT License
 *
 * Copyright 2011 Simon Hardijanto.
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
package org.projectthaleia.universe;

import org.projectthaleia.core.Immutable;

/**
 * Records the position of an object within a star system.
 * @author Simon Hardijanto
 */
@Immutable
public class Position
{
  public final int x;
  public final int y;
  
  public Position(int _x, int _y)
  {
    this.x = _x;
    this.y = _y;
  }
  
  public Position(final Position _other)
  {
    if (_other == null) {
      throw new NullPointerException("Cannot copy position of null object.");
    }
    
    this.x = _other.x;
    this.y = _other.y;
  }        

  @Override
  public boolean equals(final Object _obj)
  {
    if (_obj == this) {
      return true;
    }
    if (! (_obj instanceof Position)) {
      return false;
    }
    
    final Position other = (Position) _obj;
    
    if (this.x != other.x) {
      return false;
    }
    if (this.y != other.y) {
      return false;
    }
    return true;
  }

  @Override
  public int hashCode()
  {
    int hash = 7;
    hash = 43 * hash + this.x;
    hash = 43 * hash + this.y;
    return hash;
  }
  
  @Override
  public String toString()
  {
    return "" + this.x + "/" + this.y;
  }
}
