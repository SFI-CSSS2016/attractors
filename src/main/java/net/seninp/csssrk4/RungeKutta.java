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
 * This class provides the classical 4th order Runge Kutta algorithm.
 *
 * An object of type DiffEqu has to be provided to specify the system of differential equations to
 * be solved.
 */

public class RungeKutta {

  private DiffEqu f; // Differential equations to be solved
  private double[] y; // current position - Y vector
  private double x; // current position - x
  private int dim; // Number of equations

  private final double[] yInVector;
  private final double[] outVector;

  // The following arrays are used temporarily in step()

  private final double[] k1;
  private final double[] k2;
  private final double[] k3;

  private double h = 1.0;
  private int n = 0; // number of steps performed so far

  private double maxError = 0.02; // 0.002 for full TI52 precesion
  private double minError = maxError / 4;

  /**
   * Creates a new Runge Kutta engine for a given differential equation.
   */

  public RungeKutta(DiffEqu f) {
    this.f = f;

    // Remember result vectof of f and number of equations

    outVector = f.getOutVector();

    dim = outVector.length;

    // Allocate all required arrays

    yInVector = new double[dim];
    y = new double[dim];

    k1 = new double[dim];
    k2 = new double[dim];
    k3 = new double[dim];
  }

  /**
   * This method is used to set the initial values for the problem
   *
   * This method can be used multiple times to restart the calculation with new initial values.
   */

  public final void setStart(double startX, double[] startY) {
    // Assign parameters to the the current values

    x = startX;
    System.arraycopy(startY, 0, y, 0, dim);

    // Reset number of steps to 0

    n = 0;
  }

  /**
   * Adjusts the accuracy rate
   */

  public final void setError(double x) {
    maxError = x;
    minError = maxError / 4;
  }

  /**
   * Returns the current x value
   */

  public final double getX() {
    return x;
  }

  /**
   * Returns the current Y vector.
   */
  public final double[] getY() {
    return y;
  }

  /**
   * Returns the number of Runge Kutta steps performed so far
   */
  public final int getN() {
    return n;
  }

  /**
   * Performs a singel Runge Kutta step
   *
   * A calculation of an error estimate is performed and h is adjusted accordingly. If h had to be
   * decreased, the current values for Y and x remain unchanged.
   */
  public void step() {
    // Increase number of steps performed

    n++;

    // k1

    System.arraycopy(y, 0, yInVector, 0, dim);

    f.eval(x, yInVector);

    System.arraycopy(outVector, 0, k1, 0, dim);

    // k2

    for (int i = 0; i < dim; i++)
      yInVector[i] = y[i] + 0.5 * h * k1[i];

    f.eval(x + 0.5 * h, yInVector);

    System.arraycopy(outVector, 0, k2, 0, dim);

    // k3

    for (int i = 0; i < dim; i++)
      yInVector[i] = y[i] + 0.5 * h * k2[i];

    f.eval(x + 0.5 * h, yInVector);

    System.arraycopy(outVector, 0, k3, 0, dim);

    // Calculate error

    double normK = 0.0;
    double max = 0.0;

    for (int i = 0; i < dim; i++) {
      double diffK1K2 = k1[i] - k2[i];

      normK += diffK1K2 * diffK1K2;

      double absK = Math.abs(k2[i] - k3[i]);

      if (absK > max)
        max = absK;
    }

    double error = minError;

    if (normK > 0.0)
      error = max / Math.sqrt(normK);

    // Discard step if error is too large

    if (error > maxError) {
      h /= 2;

      return;
    }

    // k4

    for (int i = 0; i < dim; i++) {
      yInVector[i] = y[i] + h * k3[i];
    }

    f.eval(x + h, yInVector);

    // Update x and y

    x += h;

    for (int i = 0; i < dim; i++) {
      y[i] += h * (k1[i] + 2 * (k2[i] + k3[i]) + outVector[i]) / 6;
    }

    // Increase h, if error was very small

    if (error < minError)
      h *= 2;
  }

  /**
   * This method performs multiple steps to reach a certain value for x.
   *
   * The value for h is adjusted during the last step to ensure the x is reached exactly.
   */

  public void run(double goal) {
    // Perform normal steps until we get close to the goal

    while (x + h < goal)
      step();

    // Remember of value of h

    double old_h = h;

    // Perform steps to reach the goal
    // A loop has to be used because it's possible that h has to be
    // decreased during this last step

    while (Math.abs((x - goal) / goal) > 1E-13) {
      h = Math.min(goal - x, h);

      step();
    }

    // Restore old value for h

    h = old_h;
  }
}
