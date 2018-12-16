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

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import org.projectthaleia.colony.Colony;

/**
 * The production waiting queue of a {@link org.projectthaleia.colony.Colony}.
 * @author Simon Hardijanto
 */
public class BuildingQueue
{

  /**
   * Adds a building to the waiting queue. It has to be inactive and will be 
   * placed in the last position.
   * If the active queue is emtpy, the added building will be worked on with 
   * 100% of the production capacity.
   * @param _building The building to add
   */
  public void add(final IQueueable _building)
  {
    if (_building == null) {
      throw new NullPointerException("Cannot add no building to the building "
              + "queue of colony " + this.colony);
    }
    
    if (_building.isActive()) {
      throw new IllegalArgumentException("Cannot add active building to the "
              + "queue of colony " + this.colony);
    }
    
    boolean success = this.queue.add(_building);

    if (!success) {
      throw new IllegalStateException("Unable to add " + _building 
              + " to the waiting queue of colony " + this.colony);
    }
    
    if (this.active.isEmpty()) {
      this.activateNextWaiting(this.lastUpdate);
    }    
    
    this.notifyListenersThatBuildingWasAdded(_building);
  }

  /**
   * Removes a building from the queue, be it active or inactive.
   * @param _building The building to remove from the queue
   */
  public void remove(final IQueueable _building)
  {
    if (_building == null) {
      throw new NullPointerException("Cannot remove no building from the building "
              + "queue of colony " + this.colony);
    }

    boolean success = this.queue.remove(_building);
    
    if (!success) {
      success = this.active.remove(_building);
    }
    
    if (!success) {
        throw new IllegalArgumentException("Unable to remove a non-existing " + 
                _building + " from the building queue of colony " + this.colony);
    }
    
    this.notifyListenersThatBuildingWasRemoved(_building);
  }

  /**
   * An unmodifiable list of inactive buildings in the queue.
   * @return the unmodifiable list of inactive buildings in the queue
   */
  public List<IQueueable> getInactiveBuildings()
  {
    return Collections.unmodifiableList(this.queue);
  }
  
  /**
   * An unmodifiable list of buildings being currently worked on.
   * @return the unmodifiable list of buildings being currently worked on
   */
  public List<IQueueable> getActiveBuildings()
  {
    return Collections.unmodifiableList(this.active);
  }

  /**
   * Moves an inactive building one position up the waiting queue. If it's 
   * already first, nothing happens.
   * @param _movedBuilding The building being moved up
   */
  public void moveUp(final IQueueable _movedBuilding)
  {
    for (ListIterator<IQueueable> it = this.queue.listIterator(); it.hasNext(); ) {
      IQueueable building = it.next();
      if (building == _movedBuilding) {
        it.previous();
        if (!it.hasPrevious()) {
          return;
        }
        IQueueable prevBuilding = it.previous();
        it.set(_movedBuilding);
        it.next();
        it.next();
        it.set(prevBuilding);
        this.notifyListenersThatQueueOrderChanged();
        return;
      }
    }
    
    throw new IllegalArgumentException("Cannot move building up the queue which "
            + "is not in the queue of colony " + this.colony);
  }
  
  /**
   * Moves an inactive building one position down the  waiting queue. If it's 
   * already last, nothing happens.
   * @param _movedBuilding The building being moved down
   */
  public void moveDown(final IQueueable _movedBuilding)
  {
    for (ListIterator<IQueueable> it = this.queue.listIterator(); it.hasNext(); ) {
      IQueueable building = it.next();
      if (building == _movedBuilding) {
        if (!it.hasNext()) {
          return;
        }
        IQueueable nextBuilding = it.next();
        it.set(_movedBuilding);
        it.previous();
        it.previous();
        it.set(nextBuilding);
        return;
      }
    }
    
    throw new IllegalArgumentException("Cannot move building down the queue which "
            + "is not in the queue of colony " + this.colony);
  }

  public void update(final Date _now)
  {
    this.lastUpdate = _now;
    
    boolean slotAvailable = false;
    
    for (IQueueable building : this.active) {
      if (building.getFinishedDate().before(_now) || building.getFinishedDate().equals(_now)) {
        this.finishProduction(building);
        slotAvailable = true;
      }
    }

    if (slotAvailable) {
      this.activateNextWaiting(_now);
    }
  }


  //---------------- PROTECTED ----------------

  //-------------- PACKAGE PRIVATE ------------
  @Inject
  BuildingQueue(@Assisted final Colony _colony)
  {
    this.colony     = _colony;
    
    this.queue      = Collections.synchronizedList(new LinkedList<IQueueable>());
    this.active     = Collections.synchronizedList(new LinkedList<IQueueable>());
    this.listeners  = new ArrayList<IBuildingQueueChangedListener>();
    
    this.validate();
  }
  
  void addListener(final IBuildingQueueChangedListener _listener)
  {
    this.listeners.add(_listener);
  }

  void removeListener(final IBuildingQueueChangedListener _listener)
  {
    this.listeners.remove(_listener);
  }
  
  //----------------  PRIVATE  ----------------
  private final List<IQueueable> queue;
  private final List<IQueueable> active;
  private final List<IBuildingQueueChangedListener> listeners;
  private final Colony colony;
  
  private Date lastUpdate;
  
  private void validate()
  {
    if (this.colony == null) {
      throw new NullPointerException("Cannot construct building queue "
              + "without a colony.");
    }
  }

  private void notifyListenersThatBuildingWasAdded(final IQueueable _building)
  {
    for (IBuildingQueueChangedListener l : this.listeners) {
      l.buildingWasAdded(_building);
    }
  }

  private void notifyListenersThatBuildingWasRemoved(final IQueueable _building)
  {
    for (IBuildingQueueChangedListener l : this.listeners) {
      l.buildingWasRemoved(_building);
    }
  }

  private void notifyListenersThatQueueOrderChanged()
  {
    for (IBuildingQueueChangedListener l : this.listeners) {
      l.buildingQueueOrderChanged();
    }
  }

  private void finishProduction(final IQueueable _finishedBuilding)
  {
    this.colony.addBuilding(_finishedBuilding.getProduct(), _finishedBuilding.getAmount());
  }

  private void activateNextWaiting(final Date _now)
  {
    if (!this.queue.isEmpty()) {
      IQueueable activated = this.queue.get(0);
      this.queue.remove(0);
      activated.setFactoryOutput(_now, 
              (int)((float)this.colony.getFactoryOutput() * this.getAvailableOutputPercentage()),
              this.getAvailableOutputPercentage());
      this.active.add(activated);
    }
  }

  private float getAvailableOutputPercentage()
  {
    return 1.00f;
  }

}
