package net.seninp.csssrk4;

import org.apache.commons.math3.ode.nonstiff.ClassicalRungeKuttaIntegrator;
import org.apache.commons.math3.ode.sampling.FixedStepHandler;
import org.apache.commons.math3.ode.sampling.StepNormalizer;

public class Lorenz implements FixedStepHandler {

  public static void main(String[] args) {
    new Lorenz();
  }

  public Lorenz() {

    LorenzEquations equations = new LorenzEquations(10, 28, 8 / 3);

    // using fourth-order Runge-Kutta numerical integration with time step of 0.001
    ClassicalRungeKuttaIntegrator integrator = new ClassicalRungeKuttaIntegrator(0.001);

    // output results every 0.01 time steps
    integrator.addStepHandler(new StepNormalizer(0.01, this));

    // run integrator
    integrator.integrate(equations, 0, // start time
        new double[] { 10, -2, 50 }, // initial conditions
        100, // end time
        new double[3]); // storage

  }

  public void handleStep(double t, double[] y, double[] yDot, boolean isLast) {
    // TODO Auto-generated method stub

  }

  public void init(double arg0, double[] arg1, double arg2) {
    // TODO Auto-generated method stub

  }

}
