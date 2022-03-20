package com;

import org.junit.jupiter.api.Test;

public class TestEstimatorSafeScenarios
{


    Estimator estimator;


    @Test
    public void testNormalCase()
    {
        estimator = new Estimator(InvestType.safe,10000.0,365,
                0.14,0.11,0.15,0.012,0.012,0.02);

        System.out.println(estimator.computeGain());
    }
}
