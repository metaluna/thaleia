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
package org.projectthaleia.universe;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;


/**
 *
 * @author Simon Hardijanto
 */
public class PositionTest
{
  private Position position;
  
  @Before
  public void setUp()
  {
    position = new Position(100,200);
  }
  
  @Test
  public void shouldCopyPosition()
  {
    int x = 23;
    int y = 42;
    Position pos2 = new Position(x,y);
    
    position = new Position(pos2);
    
    assertEquals(x, position.x);
    assertEquals(y, position.y);
  }
  
  @Test(expected=NullPointerException.class)
  public void shouldNotCreateWithoutPosition()
  {
    position = new Position(null);
    throw new AssertionError("Copy constructor of Position did not fail without another position.");
  }

  @Test
  public void shouldBeEqualIfObjectsAreEqual()
  {
    assertEquals(position, position);
  }
  
  @Test
  public void shouldBeEqualIfCoordinatesMatch()
  {
    Position pos2 = new Position(position);
    
    assertEquals(position, pos2);
  }
  
  @Test
  public void shouldNotBeEqualIfOtherIsNotAPosition()
  {
    assertThat(position, is(not(equalTo(new Object()))));
  }
  
  @Test
  public void shouldNotBeEqualIfCoordinatesAreDifferent()
  {
    int x = 23;
    int y = 42;
    Position pos2 = new Position(x,y);
    
    assertThat(position, is(not(equalTo(pos2))));
  }
  
  @Test
  public void shouldNotBeEqualIfXCoordinateIsDifferent()
  {
    int x = 23;
    int y = position.y;
    Position pos2 = new Position(x,y);
    
    assertThat(position, is(not(equalTo(pos2))));
  }

  @Test
  public void shouldNotBeEqualIfYCoordinateIsDifferent()
  {
    int x = position.x;
    int y = 42;
    Position pos2 = new Position(x,y);
    
    assertThat(position, is(not(equalTo(pos2))));
  }
  
  @Test
  public void shouldHaveSameHash()
  {
    Position pos2 = new Position(position);

    int hash1 = position.hashCode();
    int hash2 = pos2.hashCode();
    
    assertEquals(hash1, hash2);
  }

  @Test
  public void shouldHaveDifferentHashes()
  {
    Position pos2 = new Position(23,42);
    
    int hash1 = position.hashCode();
    int hash2 = pos2.hashCode();
    
    assertThat(hash1, is(not(hash2)));
  }

  @Test
  public void shouldPrintPositionAsString()
  {
    String exp = "" + position.x + "/" + position.y;
    
    String got = position.toString();
    
    assertEquals(exp, got);
  }
}
