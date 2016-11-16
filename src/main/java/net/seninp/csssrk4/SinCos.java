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
 * A simple differential equation for testing the Runge-Kutta-Algorithm.
 *
 * The solution is
 *
 * f_0(x) = sin (x) f_1(x) = cos (x)
 */

class SinCos extends DiffEquHelper {

  public SinCos() {
    super(2);
  }

  public void eval(double x, double[] y) {
    outVector[0] = y[1];
    outVector[1] = -y[0];
  }
}