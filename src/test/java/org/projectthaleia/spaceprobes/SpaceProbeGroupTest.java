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

import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.projectthaleia.core.Vector;
import org.projectthaleia.universe.Position;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
/**
 *
 * @author Simon Hardijanto
 */
public class SpaceProbeGroupTest
{
  private SpaceProbeGroup spaceProbeGroup;
  private static final String GROUP_NAME = "Test Task Force 1";
  private Position groupPosition = new Position(23,42);

  @Before
  public void setUp()
  {
    spaceProbeGroup = new SpaceProbeGroup(GROUP_NAME, groupPosition);
  }

  // addSpaceProbe() tests -----------------------------------------------------
  @Test
  public void shouldAddASpaceProbe()
  {
    SpaceProbe mockSpaceProbe = mock(SpaceProbe.class);
    
    spaceProbeGroup.addSpaceProbe(mockSpaceProbe);
    
    assertThat(spaceProbeGroup.getSpaceProbes(), hasItem(mockSpaceProbe));
  }
  
  @Test(expected=NullPointerException.class)
  public void shouldNotAddNullAsAMember()
  {
    spaceProbeGroup.addSpaceProbe(null);
  }
  
  @Test(expected=IllegalArgumentException.class)
  public void shouldNotAddSameSpaceProbeTwice()
  {
    SpaceProbe mockSpaceProbe = mock(SpaceProbe.class);
    
    spaceProbeGroup.addSpaceProbe(mockSpaceProbe);
    spaceProbeGroup.addSpaceProbe(mockSpaceProbe);
  }

  // removeSpaceProbe() tests --------------------------------------------------
  @Test
  public void shouldRemoveSpaceProbe()
  {
    SpaceProbe mockSpaceProbe = mock(SpaceProbe.class);
    spaceProbeGroup.addSpaceProbe(mockSpaceProbe);
    
    spaceProbeGroup.removeSpaceProbe(mockSpaceProbe);
    
    assertThat(spaceProbeGroup.getSpaceProbes(), not(hasItem(mockSpaceProbe)));
  }
  
  @Test(expected=NullPointerException.class)
  public void shouldNotRemoveNull()
  {
    spaceProbeGroup.removeSpaceProbe(null);
  }
  
  @Test(expected=IllegalArgumentException.class)
  public void shouldNotRemoveMemberThatDoesNotExist()
  {
    SpaceProbe mockSpaceProbe = mock(SpaceProbe.class);
    
    spaceProbeGroup.removeSpaceProbe(mockSpaceProbe);
  }
  
  // maximum speed tests -------------------------------------------------------
  @Test
  public void shouldHaveAMaximumSpeedOfZeroWithoutMembers()
  {
    int expected = 0;
    int got = spaceProbeGroup.getMaximumSpeed();
    assertEquals(expected, got);
  }

  @Test
  public void shouldHaveAMaximumSpeedOfTheOnlyMember()
  {
    int expected = 55;
    
    addSpaceProbe(expected);
    
    int got = spaceProbeGroup.getMaximumSpeed();
    assertEquals(expected, got);
  }
  
  @Test
  public void shouldHaveAMaximumSpeedOfTheSlowestMember()
  {
    int fastSpeed = 5000;
    int slowSpeed = 1;
    
    SpaceProbe probe1 = addSpaceProbe(fastSpeed);
    SpaceProbe probe2 = addSpaceProbe(slowSpeed);
    
    assertEquals(fastSpeed, probe1.getMaximumSpeed());
    assertEquals(slowSpeed, probe2.getMaximumSpeed());
    assertThat(spaceProbeGroup.getSpaceProbes(), hasItem(probe2));
    
    assertEquals(slowSpeed, spaceProbeGroup.getMaximumSpeed());
  }

  @Test
  public void shouldResetMaximumSpeedWhenWithoutMembers()
  {
    SpaceProbe mockProbe = addSpaceProbe(55);
    spaceProbeGroup.removeSpaceProbe(mockProbe);
    
    int got = spaceProbeGroup.getMaximumSpeed();
    assertEquals(0, got);
  }

  // stop() tests --------------------------------------------------------------
  @Test
  public void shouldSetDirectionAndSpeedWhenStopped()
  {
    addSpaceProbe(1000);
    
    spaceProbeGroup.setCurrentSpeed(10);
    spaceProbeGroup.setDirection(new Vector(1,2));
    
    spaceProbeGroup.stop();
    
    assertEquals(0, spaceProbeGroup.getCurrentSpeed());
    assertEquals(Vector.NOT_MOVING, spaceProbeGroup.getDirection());
    assertTrue(spaceProbeGroup.isStopped());
  }
  
  // setDirection() tests ------------------------------------------------------
  @Test(expected=NullPointerException.class)
  public void shouldNotSetDirectionToNull()
  {
    spaceProbeGroup.setDirection(null);
  }
  
  @Test
  public void shouldSetDirection()
  {
    Vector dir = new Vector(1,2);
    
    spaceProbeGroup.setDirection(dir);
    
    assertEquals(dir, spaceProbeGroup.getDirection());
  }
  
  // setPosition() tests -------------------------------------------------------
  @Test(expected=NullPointerException.class)
  public void shouldNotSetPositioToNull()
  {
    spaceProbeGroup.setPosition(null);
  }
  
  @Test
  public void shouldSetPosition()
  {
    Position pos = new Position(2000,5000);
    
    spaceProbeGroup.setPosition(pos);
    
    assertEquals(pos, spaceProbeGroup.getPosition());
  }
  
  // setName() tests -----------------------------------------------------------
  @Test(expected=NullPointerException.class)
  public void shouldNotSetNameToNull()
  {
    spaceProbeGroup.setName(null);
  }
  
  @Test(expected=IllegalArgumentException.class)
  public void shouldNotSetNameToEmptryString()
  {
    spaceProbeGroup.setName("");
  }
  
  @Test
  public void shouldSetName()
  {
    String name = "Alien Control Unit 1";
    
    spaceProbeGroup.setName(name);
    
    assertEquals(name, spaceProbeGroup.getName());
  }
  
  // setCurrentSpeed() tests ---------------------------------------------------
  @Test(expected=IllegalArgumentException.class)
  public void shouldNotSetCurrentSpeedBelowZero()
  {
    spaceProbeGroup.setCurrentSpeed(-1);
  }
  
  @Test(expected=IllegalArgumentException.class)
  public void shouldNotSetCurrentSpeedAboveMaxSpeed()
  {
    int maxSpeed = 1000;
    addSpaceProbe(maxSpeed);
    
    spaceProbeGroup.setCurrentSpeed(maxSpeed + 1);
  }
  
  // addWaypoint() tests -------------------------------------------------------
  @Test
  public void addsWaypoint() {
    Position waypoint = new Position(1,2);
    
    spaceProbeGroup.addWaypoint(waypoint);
    
    List<Position> waypoints = spaceProbeGroup.getWaypoints();
    assertThat(waypoints, contains(waypoint));
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void doesNotAddNullWaypoint() {
    spaceProbeGroup.addWaypoint(null);
  }

  @Test
  public void doesNotAddDuplicatedLastWaypoint() {
    Position waypoint = new Position(1,2);
    
    spaceProbeGroup.addWaypoint(waypoint);
    spaceProbeGroup.addWaypoint(waypoint);
    
    List<Position> waypoints = spaceProbeGroup.getWaypoints();
    assertThat(waypoints, contains(waypoint));
    assertThat(waypoints.size(), is(1));
  }
  
  // update() ------------------------------------------------------------------
  @Test
  public void doesNotChangePositionOnUpdateWhenStopped() {
    spaceProbeGroup.update(60);
    
    assertThat(spaceProbeGroup.getPosition(), is(groupPosition));
  }
  
  @Test
  public void movesPositionOnUpdate() {
    addSpaceProbe(1000);
    spaceProbeGroup.setPosition(new Position(0,0));
    spaceProbeGroup.addWaypoint(new Position(10,0));
    spaceProbeGroup.setCurrentSpeed(spaceProbeGroup.getMaximumSpeed());
    
    spaceProbeGroup.update(1);
    
    assertThat(spaceProbeGroup.getCurrentSpeed(), is(1000));
    assertThat(spaceProbeGroup.getPosition(), is(new Position(1,0)));
  }
  
  @Test
  public void movesPositionOnMultipleUpdates() {
    addSpaceProbe(1000);
    spaceProbeGroup.setPosition(new Position(0,0));
    spaceProbeGroup.addWaypoint(new Position(10,0));
    spaceProbeGroup.setCurrentSpeed(spaceProbeGroup.getMaximumSpeed());
    
    spaceProbeGroup.update(1);
    
    assertThat(spaceProbeGroup.getCurrentSpeed(), is(1000));
    assertThat(spaceProbeGroup.getPosition(), is(new Position(1,0)));

    spaceProbeGroup.update(1);
    
    assertThat(spaceProbeGroup.getCurrentSpeed(), is(1000));
    assertThat(spaceProbeGroup.getPosition(), is(new Position(2,0)));
  }
  
  @Test
  public void stopsAtWaypointOnUpdate() {
    addSpaceProbe(2000);
    spaceProbeGroup.setPosition(new Position(0,0));
    spaceProbeGroup.addWaypoint(new Position(1,0));
    spaceProbeGroup.setCurrentSpeed(spaceProbeGroup.getMaximumSpeed());
    
    spaceProbeGroup.update(1);
    
    assertThat(spaceProbeGroup.isStopped(), is(true));
    assertThat(spaceProbeGroup.getPosition(), is(new Position(1,0)));
  }
  
  // ----------- UTIL ----------------------------------------------------------
  private SpaceProbe addSpaceProbe(int _speed)
  {
    SpaceProbe spaceProbe = mock(SpaceProbe.class);
    when(spaceProbe.getMaximumSpeed()).thenReturn(_speed);
    spaceProbeGroup.addSpaceProbe(spaceProbe);
    return spaceProbe;
  }

}
