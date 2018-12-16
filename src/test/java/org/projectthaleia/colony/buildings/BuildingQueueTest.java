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
package org.projectthaleia.colony.buildings;

import java.util.Date;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.projectthaleia.colony.Colony;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 *
 * @author Simon Hardijanto
 */
public class BuildingQueueTest
{
  BuildingQueue queue;
  Colony colony;
  
  @Before
  public void setUp()
  {
    colony = mock(Colony.class);
    queue = new BuildingQueue(colony);
  }
  
  @Test
  public void shouldHaveEmptyInactiveQueue()
  {
    boolean isEmpty = queue.getInactiveBuildings().isEmpty();
    assertThat(isEmpty, is(true));
  }

  @Test
  public void shouldHaveEmptyActiveQueue()
  {
    boolean isEmpty = queue.getActiveBuildings().isEmpty();
    assertThat(isEmpty, is(true));
  }

  // Adding a building ---------------------------------------------------------
  @Test
  public void shouldAddBuilding()
  {
    IQueueable building1 = mock(IQueueable.class);
    IQueueable building2 = mock(IQueueable.class);
    queue.add(building1);
    queue.add(building2);
    assertThat(queue.getInactiveBuildings(), hasItem(building2));
  }
  
  @Test
  public void shouldAddFirstBuildingToActiveQueue()
  {
    IQueueable building = mock(IQueueable.class);
    
    queue.add(building);
    
    assertThat(queue.getActiveBuildings(), hasItem(building));
  }
  
  @Test(expected=NullPointerException.class)
  public void shouldNotAddNull()
  {
    queue.add(null);
  }
  
  @Test(expected=IllegalArgumentException.class)
  public void shouldNotAddActiveBuilding()
  {
    IQueueable building = mock(IQueueable.class);
    when(building.isActive()).thenReturn(Boolean.TRUE);
    
    queue.add(building);
  }
  
  // Removing a building -------------------------------------------------------
  @Test
  public void shouldRemoveInactiveBuilding()
  {
    IQueueable building1 = mock(IQueueable.class);
    IQueueable building2 = mock(IQueueable.class);
    queue.add(building1);
    queue.add(building2);
    
    queue.remove(building2);
    
    assertThat(queue.getInactiveBuildings(), not(hasItem(building2)));
  }
  
  @Test
  public void shouldRemoveActiveBuilding()
  {
    IQueueable building1 = mock(IQueueable.class);
    IQueueable building2 = mock(IQueueable.class);
    queue.add(building1);
    queue.add(building2);
    
    queue.remove(building1);
    
    assertThat(queue.getActiveBuildings(), not(hasItem(building1)));
  }
  
  @Test(expected=IllegalArgumentException.class)
  public void shouldNotRemoveNonExistantBuilding()
  {
    IQueueable building = mock(IQueueable.class);
    queue.remove(building);
  }
  
  @Test(expected=NullPointerException.class)
  public void shouldNotRemoveNull()
  {
    queue.remove(null);
  }
  
  // Moving in the waiting queue -----------------------------------------------
  @Test
  public void shouldMoveBuildingUpTheQueue()
  {
    IQueueable building1 = mock(IQueueable.class);
    IQueueable building2 = mock(IQueueable.class);

    queue.add(mock(IQueueable.class));
    queue.add(building1);
    queue.add(building2);
    
    queue.moveUp(building2);
    
    assertThat(queue.getInactiveBuildings(), contains(building2, building1));
  }
  
  @Test(expected=IllegalArgumentException.class)
  public void shouldNotMoveBuildingIfNonExistant()
  {
    IQueueable building = mock(IQueueable.class);
    
    queue.moveUp(building);
  }
  
  @Test(expected=IllegalArgumentException.class)
  public void shouldNotMoveBuildingUpTheQueueIfItIsActive()
  {
    IQueueable building1 = mock(IQueueable.class);
    IQueueable building2 = mock(IQueueable.class);
    when(building2.isActive()).thenReturn(Boolean.TRUE);

    queue.add(building1);
    queue.add(building2);
    
    queue.moveUp(building2);
  }
  
  @Test
  public void shouldNotChangeOrderOfQueueIfBuildingMovedUpIsFirst()
  {
    IQueueable building1 = mock(IQueueable.class);
    IQueueable building2 = mock(IQueueable.class);
    queue.add(mock(IQueueable.class));
    queue.add(building1);
    queue.add(building2);
    
    queue.moveUp(building1);
    
    assertThat(queue.getInactiveBuildings(), contains(building1, building2));
  }
  
  @Test
  public void shouldMoveBuildingDownTheQueue()
  {
    IQueueable building1 = mock(IQueueable.class);
    IQueueable building2 = mock(IQueueable.class);
    queue.add(mock(IQueueable.class));
    queue.add(building1);
    queue.add(building2);
    
    queue.moveDown(building1);
    
    assertThat(queue.getInactiveBuildings(), contains(building2, building1));
  }
  
  @Test(expected=IllegalArgumentException.class)
  public void shouldNotMoveBuildingDownIfNonExistant()
  {
    IQueueable building = mock(IQueueable.class);
    queue.moveDown(building);
  }
  
  @Test
  public void shouldNotChangeOrderOfQueueIfBuildingMovedDownIsLast()
  {
    IQueueable building1 = mock(IQueueable.class);
    IQueueable building2 = mock(IQueueable.class);
    queue.add(mock(IQueueable.class));
    queue.add(building1);
    queue.add(building2);
    
    queue.moveDown(building2);
    
    assertThat(queue.getInactiveBuildings(), contains(building1, building2));
  }
  
  // Update --------------------------------------------------------------------
  @Test
  public void shouldMoveWaitingBuildingToTheActiveQueue()
  {
    Date now = mock(Date.class);
    IQueueable building1 = mock(IQueueable.class);
    when(building1.getFinishedDate()).thenReturn(now);
    IQueueable building2 = mock(IQueueable.class);
    
    queue.add(building1);
    queue.add(building2);
    
    queue.update(now);
    
    assertThat(queue.getInactiveBuildings(), not(hasItem(building2)));
    assertThat(queue.getActiveBuildings(), hasItem(building2));
    verify(building2).setFactoryOutput(Mockito.any(Date.class), anyInt(),anyFloat());
  }
  
  @Test
  public void shouldAddFinishedBuildingToColony()
  {
    Date now = mock(Date.class);
    IBuildable buildingType = mock(IBuildable.class);
    IQueueable building = mock(IQueueable.class);
    when(building.getFinishedDate()).thenReturn(now);
    when(building.getProduct()).thenReturn(buildingType);
    queue.add(building);
    
    queue.update(now);
    
    verify(colony).addBuilding(Mockito.any(IBuildable.class),anyInt());
  }
  
  @Test
  public void shouldNotFinishBuilding()
  {
    long time = 1;
    
    Date now = mock(Date.class);
    when(now.getTime()).thenReturn(time);
    
    Date later = mock(Date.class);
    when(later.getTime()).thenReturn(time+1);

    IQueueable building = mock(IQueueable.class);
    when(building.getFinishedDate()).thenReturn(later);
    queue.add(building);
    
    queue.update(now);
    
    verify(colony, never()).addBuilding(Mockito.any(IBuildable.class), anyInt());
  }
  
  // Notifying listeners -------------------------------------------------------
  @Test
  public void shouldNotifyListenersOfAddedBuilding()
  {
    IBuildingQueueChangedListener listener = mock(IBuildingQueueChangedListener.class);
    queue.addListener(listener);
    
    IQueueable building = mock(IQueueable.class);
    queue.add(building);
    
    verify(listener).buildingWasAdded(building);
  }
  
  @Test
  public void shouldNotifyListenersOfRemovedBuilding()
  {
    IBuildingQueueChangedListener listener = mock(IBuildingQueueChangedListener.class);
    queue.addListener(listener);
    
    IQueueable building = mock(IQueueable.class);
    queue.add(building);
    
    queue.remove(building);
    
    verify(listener).buildingWasRemoved(building);
  }
  
  @Test
  public void shouldNotifyListenersOfChangedOrder()
  {
    IBuildingQueueChangedListener listener = mock(IBuildingQueueChangedListener.class);
    queue.addListener(listener);

    IQueueable building1 = mock(IQueueable.class);
    IQueueable building2 = mock(IQueueable.class);
    queue.add(mock(IQueueable.class));
    queue.add(building1);
    queue.add(building2);
    
    queue.moveUp(building2);
    
    verify(listener).buildingQueueOrderChanged();
  }
}
