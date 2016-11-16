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

public class RKTest {

  public final static void show(double[] a) {
    for (int i = 0; i < a.length; i++) {
      System.out.print(a[i]);
      System.out.print("/");
    }

    System.out.println();
  }

  public final static void main(String[] args) {
    DiffEqu f = new SinCos();

    RungeKutta rk = new RungeKutta(f);

    double[] startY = new double[2];

    startY[0] = 0.0;
    startY[1] = 1.0;

    rk.setStart(0.0, startY);

    rk.run(100);

    System.out.println(rk.getX());
    show(rk.getY());

    System.out.println(rk.getN());
  }
}