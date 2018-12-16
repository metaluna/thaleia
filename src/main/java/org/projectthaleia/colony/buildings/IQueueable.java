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

/**
 * Interface for objects that can be placed in a production waiting queue. For
 * now this is only used in the {@link org.projectthaleia.colony.buildings.BuildingQueue} 
 * of a {@link org.projectthaleia.colony.Colony}.
 * @author Simon Hardijanto
 */
public interface IQueueable
{
  /**
   * The total cost of the task, meaning the cost of one building multiplied by
   * the amount of buildings.
   * @return the total cost
   */
  int getProductionCost();
  /**
   * The date the task is completely finished, meaning all buildings are done.
   * @return the date the task is completely finished.
   */
  Date getFinishedDate();
  /**
   * The building type that is to be produced.
   * @return the building type
   */
  IBuildable getProduct();
  /**
   * The number of buildings.
   * @return the number of buildings
   */
  int getAmount();
  /**
   * (Re-)Starts the building task or changes the amount of production output
   * thas is to be used. If the task is locked changing the production percentage
   * is not allowed, even though internally only the output per day is used.
   * @param _now the day has begun
   * @param _outputPerDay the amount of industrial capacity points 
   * @param _productionPercentage the percentage of the industrial capacity
   */
  void setFactoryOutput(Date _now, int _outputPerDay, float _productionPercentage);
  /**
   * Is this building task queued or being currently worked on?
   * @return wether this task is being worked on
   */
  boolean isActive();
  /**
   * Pauses the task. This will automatically release the lock that forbids
   * changing the production percentage.
   * @param _now the day from which on work pauses
   */
  void halt(Date _now);
  /**
   * The fraction of industrial capacity that is being used
   * @return the fraction of industrial capacity that is being used
   */
  float getProductionPercentage();
  /**
   * Can the production percentage be changed? If this queueable is paused it
   * will always return <code>false</code>.
   * @return wether the production percentage may be changed
   */
  boolean isProductionPercentageLocked();
  /**
   * Locks or unlocks changing the production percentage. This may not be set 
   * if this queueable is paused.
   * @param _isLocked wether changing the production percentage is allowed
   */
  void setProductionPercentageLock(boolean _isLocked);
}
