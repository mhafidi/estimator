package com.estimator.core;

public enum InvestType
{
    aggressive("aggressive"),
    moderate("moderate"),
    safe("safe");
    String investType;

    InvestType(String investType)
    {
        this.investType = investType;
    }
    @Override
    public String toString()
    {
        return "StrategyType{" +
                "strategyType='" + investType + '\'' +
                '}';
    }
}
