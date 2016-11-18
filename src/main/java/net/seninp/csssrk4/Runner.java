package net.seninp.csssrk4;

import org.apache.commons.math3.ode.nonstiff.ClassicalRungeKuttaIntegrator;
import org.apache.commons.math3.ode.sampling.StepNormalizer;

public class Runner {

  public static void main(String[] args) {

    // FirstOrderIntegrator dp853 = new DormandPrince853Integrator(1.0E-8, 10E-5, 1.0E-20, 1.0E-20);
    ClassicalRungeKuttaIntegrator integrator = new ClassicalRungeKuttaIntegrator(0.001);

    LorenzEquations equations = new LorenzEquations(28, 10, 8 / 3);

    LorenzStepHandler lz = new LorenzStepHandler();

    integrator.addStepHandler(new StepNormalizer(0.01, lz));

    integrator.integrate(equations, 0, new double[] { 10, -2, 50 }, 100, new double[3]);

  }

}
