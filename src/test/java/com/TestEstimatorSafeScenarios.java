package com;

import org.junit.jupiter.api.Test;

public class TestEstimatorSafeScenarios
{


    Estimator estimator;


    @Test
    public void testNormalCase()
    {
        estimator = new Estimator(InvestType.safe,10000.0,365,
                0.14,0.11,0.15,0.012,0.012,0.01);

        System.out.println("total capital is: "+estimator.computeGain(false));
    }

    @Test
    public void testWorstCase()
    {
        estimator = new Estimator(InvestType.safe,10000.0,365,
                0.14,0.11,0,0.012,0.012,0);

        System.out.println("total capital is: "+estimator.computeGain(true));
    }

    @Test
    public void testOptimisticCase()
    {
        estimator = new Estimator(InvestType.safe,20000.0,365,
                0.14,0.18,0.11,0.012,0.02,0.012);

        System.out.println("total capital is: "+estimator.computeGain(true));
    }
}
