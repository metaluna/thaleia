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
package org.projectthaleia.universe.graphics;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import java.util.HashMap;
import java.util.Map;
import org.projectthaleia.universe.StarSystem;

/**
 *
 * @author Simon Hardijanto
 */
@Singleton
public class SystemGraphicsLibrary
{
  
  public StarSystemGraphic get(final StarSystem _starSystem)
  {
    StarSystemGraphic systemGraphic = this.starSystemsLibrary.get(_starSystem);
    
    if (systemGraphic == null) {
      systemGraphic = this.createNewSystemGraphic(_starSystem);
    }
    
    return systemGraphic;
  }

  //------------ PACKAGE PRIVATE --------------
  @Inject
  SystemGraphicsLibrary(final StarSystemGraphicFactory _starSystemGraphicFactory)
  {
    this.starSystemGraphicFactory = _starSystemGraphicFactory;
    this.starSystemsLibrary = new HashMap<StarSystem, StarSystemGraphic>();
    
    validate();
  }

  //----------------  PRIVATE  ----------------
  private final StarSystemGraphicFactory starSystemGraphicFactory;
  private final Map<StarSystem, StarSystemGraphic> starSystemsLibrary;

  private void validate()
  {
    if (this.starSystemGraphicFactory == null) {
      throw new NullPointerException(
              "System graphics library must have a graphic factory. System graphics lib: " + this);
    }
  }

  private StarSystemGraphic createNewSystemGraphic(final StarSystem _starSystem)
  {
    final StarSystemGraphic result = this.starSystemGraphicFactory.create(_starSystem);
    _starSystem.addStarSystemChangedListener(result);
    this.starSystemsLibrary.put(_starSystem, result);
    return result;
  }
}
