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
import java.util.Date;

/**
 * Implementation of {@link IQueueable} for buildings that can be produced in a 
 * {@link org.projectthaleia.colony.Colony}.
 * @author Simon Hardijanto
 */
public class Queueable implements IQueueable
{

  @Override
  public int getProductionCost()
  {
    return this.totalCost;
  }

  @Override
  public Date getFinishedDate()
  {
    if (!this.isActive) {
      throw new IllegalStateException("Must not ask for date finished when inactive.");
    }
    
    return new Date(this.dateFinished.getTime());
  }

  @Override
  public IBuildable getProduct()
  {
    return this.buildable;
  }
  
  @Override
  public int getAmount()
  {
    return this.amount;
  }

  @Override
  public void setFactoryOutput(final Date _now, final int _outputPerDay, final float _productionPercentage)
  {
    if (_now == null) {
      throw new NullPointerException("Cannot start production without a date: " 
              + this);
    }
    if (_outputPerDay < 1) {
      throw new IllegalArgumentException("Cannot start production with an "
              + "output < 1. Was " + _outputPerDay + "(" + this + ")");
    }
    
    if (this.lastUpdated != null) {
      if (this.isProductionPercentageLocked && 
          this.productionPercentage != _productionPercentage) {
        throw new IllegalStateException("Cannot change the percentage of "
              + "production output when the queueable " + this + " is locked.");
      }
      calculateWorkDoneUntil(_now);
    }
    
    final double daysTilFinished = (double) this.totalCost / (double) _outputPerDay;
    final long timeTilFinished = (long)(daysTilFinished * MS_PER_DAY);
    this.dateFinished = new Date(_now.getTime() + timeTilFinished);
    this.lastUpdated = new Date(_now.getTime());
    this.lastOutputPerDay = _outputPerDay;
    this.isActive = true;
    this.productionPercentage = _productionPercentage;
  }

  @Override
  public boolean isActive()
  {
    return this.isActive;
  }
  
  @Override
  public void halt(final Date _now)
  {
    if (_now == null) {
      throw new NullPointerException("Cannot halt production without a date: " 
              + this);
    }
    if (!this.isActive) {
      throw new IllegalStateException("Cannot halt already inactive production: " 
              + this);
    }
    
    calculateWorkDoneUntil(_now);
    this.dateFinished = null;
    this.lastUpdated = null;
    this.lastOutputPerDay = 0;
    this.isActive = false;
    this.isProductionPercentageLocked = false;
  }

  @Override
  public float getProductionPercentage()
  {
    return this.productionPercentage;
  }
  
  @Override
  public boolean isProductionPercentageLocked()
  {
    boolean result = this.isProductionPercentageLocked;
    return result;
  }
  
  @Override
  public void setProductionPercentageLock(final boolean _isLocked)
  {
    this.isProductionPercentageLocked = _isLocked;
  }
  
  //---------------- PROTECTED ----------------

  //-------------- PACKAGE PRIVATE ------------
  @Inject
  Queueable(@Assisted final IBuildable _buildable, @Assisted final int _amount)
  {
    this.buildable = _buildable;
    this.amount = _amount;
    this.totalCost = _buildable.getCost() * _amount;
  }

  //----------------  PRIVATE  ----------------
  final static long MS_PER_DAY = 1000*60*60*24;
  
  final IBuildable buildable;
  
  int amount;
  int totalCost;
  boolean isActive;
  Date lastUpdated;
  int lastOutputPerDay;
  Date dateFinished;
  float productionPercentage;
  boolean isProductionPercentageLocked;
  
  private void calculateWorkDoneUntil(final Date _now)
  {
    if (_now.before(lastUpdated)) {
      throw new IllegalArgumentException("Trying to resume work on "
              + "a date in the past: " + _now + " < " + this.lastUpdated 
              + " ( " + this + ")");
    }
    final long timePassed = _now.getTime() - this.lastUpdated.getTime();
    final double daysDone = timePassed/MS_PER_DAY;
    this.totalCost -= this.lastOutputPerDay*daysDone;
  }

}
