package net.seninp.csssrk4;

import org.apache.commons.math3.ode.FirstOrderDifferentialEquations;

public class LorenzEquations implements FirstOrderDifferentialEquations {

  public double prandtl; // d
  public double rayleigh; // p
  public double beta; // B

  public LorenzEquations(double d, double p, double B) {
    this.prandtl = d;
    this.rayleigh = p;
    this.beta = B;
  }

  public void computeDerivatives(double t, double[] y, double[] yDot) {
    yDot[0] = prandtl * (y[1] - y[0]); // dx/dt = d(y-x)
    yDot[1] = y[0] * (rayleigh - y[2]) - y[1]; // dy/dt = x(p-z)-y
    yDot[2] = y[0] * y[1] - beta * y[2]; // dz/dt = xy - Bz
  }

  public int getDimension() {
    return 3;
  }

  // called by the integrator (via StepNormalizer) after each time step
  public void handleStep(double t, double[] y, boolean isLast) {
    System.out.println("" + t + "\t" + y[0] + "\t" + y[1] + "\t" + y[2]);
  }

  public void init(double arg0, double[] arg1, double arg2) {
    // TODO Auto-generated method stub
  }

}