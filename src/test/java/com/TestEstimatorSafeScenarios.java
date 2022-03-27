package com;

import com.estimator.core.Estimator;
import com.estimator.core.InvestType;
import org.junit.jupiter.api.Test;

public class TestEstimatorSafeScenarios
{


    Estimator estimator;


    @Test
    public void testNormalCase()
    {
        estimator = new Estimator(InvestType.safe,100000.0,365,
                0.14,0.11,0.0015,0.012,0.012,0.0001);

        System.out.println("total capital is: "+estimator.computeGain(true));
    }

    @Test
    public void testWorstCase()
    {
        estimator = new Estimator(InvestType.safe,100000.0,365,
                0.14,0.11,0,0.012,0.012,0);

        System.out.println("total capital is: "+estimator.computeGain(true));
    }

    @Test
    public void testOptimisticCase()
    {
        estimator = new Estimator(InvestType.safe,100000.0,365,
                0.14,0.18,0.11,0.012,0.02,0.012);

        System.out.println("total capital is: "+estimator.computeGain(true));
    }
}
