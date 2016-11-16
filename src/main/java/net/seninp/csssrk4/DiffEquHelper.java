package net.seninp.csssrk4;

/*
 *  Project: Differential Equations in Astronomy
 *  Copyright (C) 2000  Rudolf Schürer
 *
 *  This program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program; if not, write to the Free Software
 *  Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
 */

/**
 * DiffEquHelper performs the basic operations required by every instance of DiffEqu.
 */

public abstract class DiffEquHelper implements DiffEqu {
  protected double[] outVector;

  public double[] getOutVector() {
    return outVector;
  };

  protected DiffEquHelper(int dim) {
    outVector = new double[dim];
  }
}