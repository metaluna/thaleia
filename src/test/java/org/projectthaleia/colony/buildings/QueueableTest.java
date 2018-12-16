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

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 *
 * @author Simon Hardijanto
 */
public class QueueableTest
{
  private Queueable queueable;
  private IBuildable building;
  private int buildingAmount;
  private int buildingCost;
  
  @Before
  public void setUp()
  {
    building = mock(IBuildable.class);
    buildingAmount = 1;
    buildingCost = 1000;
    when(building.getCost()).thenReturn(buildingCost);
    queueable = new Queueable(building, buildingAmount);
  }

  @Test
  public void shouldGetProductionCost()
  {
    int expTotalCost = buildingAmount * buildingCost;
    int gotTotalCost = queueable.getProductionCost();
    assertThat(gotTotalCost, is(expTotalCost));
  }

  @Test
  public void shouldBeInactive()
  {
    assertThat(queueable.isActive(), is(false));
  }
  
  @Test
  public void shouldNotHaveLockedProductionPercentage()
  {
    assertThat(queueable.isProductionPercentageLocked, is(false));
  }
  
  // halt() --------------------------------------------------------------------
  @Test(expected=IllegalStateException.class)
  public void shouldNotHaltWhenAlreadyInactive()
  {
    Date mockDate = mock(Date.class);
    queueable.halt(mockDate);
  }
  
  @Test(expected=NullPointerException.class)
  public void shouldNotHaltWithoutDate()
  {
    queueable.halt(null);
  }
  
  @Test(expected=IllegalArgumentException.class)
  public void shouldNotHaltInThePast()
  {
    Date start = new Date(100);
    queueable.setFactoryOutput(start, 100, 1.0f);
    Date past = new Date(99);
    queueable.halt(past);
  }
  
  @Test
  public void shouldBeActiveAfterWorkHasBegun()
  {
    Date start = mock(Date.class);
    queueable.setFactoryOutput(start, 100, 1.0f);
    assertThat(queueable.isActive(), is(true));
  }
  
  @Test
  public void shouldBeInactiveAgainWhenWorkWasHalted()
  {
    Date start = mock(Date.class);
    queueable.setFactoryOutput(start, 100, 1.0f);
    queueable.halt(start);
    assertThat(queueable.isActive(), is(false));
  }
  
  // working -------------------------------------------------------------------
  @Test(expected=IllegalStateException.class)
  public void shouldNotGetFinishedDateBeforeWorkStarted()
  {
    queueable.getFinishedDate();
  }
  
  @Test
  public void shouldGetFinishedDateAfterWorkHasBegun()
  {
    final int output = 100;
    final int daysTilFinished = (buildingAmount*buildingCost)/output;
    final Date start = new Date();
    final Calendar calendar = new GregorianCalendar();
    calendar.setTime(start);
    calendar.add(Calendar.DATE, daysTilFinished);
    final Date exp = calendar.getTime();
    
    queueable.setFactoryOutput(start, output, 1.0f);
    
    final Date got = queueable.getFinishedDate();
    
    assertThat(got, is(exp));
  }
  
  @Test
  public void shouldUpdateFinishedDateAfterWorkOutputWasChanged()
  {
    final int output1       = 100;
    final int daysOnOutput1 = 5;
    final int output2       = 500;
    final int daysOnOutput2 = 1;
    final int daysTilFinished = 6;
    final Date start = new Date();
    final Calendar calendar = new GregorianCalendar();
    calendar.setTime(start);
    calendar.add(Calendar.DATE, daysTilFinished);
    final Date exp = calendar.getTime();
    
    queueable.setFactoryOutput(start, output1, 0.2f);

    calendar.setTime(start);
    calendar.add(Calendar.DATE, daysOnOutput1);
    Date changeDate = calendar.getTime();
    queueable.setFactoryOutput(changeDate, output2, 1.0f);
    
    final Date got = queueable.getFinishedDate();
    
    assertThat(got, is(exp));   
  }
  
  @Test
  public void shouldResumeWorkAfterBeingHalted()
  {
    final int output       = 100;
    final int daysWorking = 5;
    final int daysPausing = 1;
    final int daysTilFinished = daysWorking * 2 + daysPausing;
    
    final Date start = new Date(0);
    final Date exp = new Date(start.getTime() + daysTilFinished*MS_PER_DAY);
    queueable.setFactoryOutput(start, output, 1.0f);

    final Date haltDate = new Date(start.getTime() + daysWorking*MS_PER_DAY);
    queueable.halt(haltDate);
    
    final Date resumeDate = new Date(haltDate.getTime() + daysPausing*MS_PER_DAY);
    queueable.setFactoryOutput(resumeDate, output, 1.0f);
    
    final Date got = queueable.getFinishedDate();
    
    assertThat(got, is(exp));
    
  }
  
  // changing the output used --------------------------------------------------
  @Test(expected=IllegalArgumentException.class)
  public void shouldNotChangeOutputIfNewTimeLiesInThePast()
  {
    Date start = new Date(100);
    queueable.setFactoryOutput(start, 100, 1.0f);
    Date past = new Date(99);
    queueable.setFactoryOutput(past, 100, 1.0f);
  }

  @Test(expected=NullPointerException.class)
  public void shouldNotSetFactoryOutputWithoutADate()
  {
    queueable.setFactoryOutput(null, 1, 1.0f);
  }
  
  @Test(expected=IllegalArgumentException.class)
  public void shouldNotSetFactoryOutputToZero()
  {
    Date mockDate = mock(Date.class);
    queueable.setFactoryOutput(mockDate, 0, 1.0f);
  }
  
  @Test(expected=IllegalArgumentException.class)
  public void shouldNotSetFactoryOutputToANegativeNumber()
  {
    Date mockDate = mock(Date.class);
    queueable.setFactoryOutput(mockDate, -1, 1.0f);
  }
  
  @Test(expected=IllegalStateException.class)
  public void shouldNotChangeProductionPercentageWhenLocked()
  {
    Date mockDate = mock(Date.class);
    queueable.setFactoryOutput(mockDate, 1, 1.0f);
    
    queueable.setProductionPercentageLock(true);
    
    queueable.setFactoryOutput(mockDate, 1, 0.9f);
  }
  
  // getProduct() --------------------------------------------------------------
  @Test
  public void shouldGetProduct()
  {
    IBuildable expBuilding = building;
    IBuildable gotBuilding = queueable.getProduct();
    assertEquals(expBuilding, gotBuilding);
  }
  
  // isProductionPercentageLocked() --------------------------------------------
  @Test
  public void shouldAlwaysReportProductionPercentageUnlockedWhenHalted()
  {
    Date mockDate = mock(Date.class);
    queueable.setFactoryOutput(mockDate, 1, 1.0f);
    queueable.halt(mockDate);
    
    assertThat(queueable.isProductionPercentageLocked, is(false));
  }
  
  @Test
  public void shouldReleaseProductionPercentageLockWhenHaltedAndResumed()
  {
    Date mockDate = mock(Date.class);
    queueable.setFactoryOutput(mockDate, 1, 1.0f);
    queueable.setProductionPercentageLock(true);
    
    queueable.halt(mockDate);
    
    queueable.setFactoryOutput(mockDate, 1, 1.0f);
    
    assertThat(queueable.isProductionPercentageLocked(), is(false));
  }
    
  // -------------------- PRIVATE ----------------------------------------------
  private static final long MS_PER_DAY = 1000*60*60*24;

}
