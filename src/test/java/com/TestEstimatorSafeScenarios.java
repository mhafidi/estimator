package com;

import org.junit.jupiter.api.Test;

public class TestEstimatorSafeScenarios
{


    Estimator estimator;


    @Test
    public void testNormalCase()
    {
        estimator = new Estimator(InvestType.safe,10000.0,365,
                0.14,0.11,0.0015,0.012,0.012,0.001);

        System.out.println(estimator.computeGain());
    }
}
