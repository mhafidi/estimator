package com;

import org.apache.log4j.Logger;

public class Estimator
{

    double initialValueInvestmentInUSD;
    double initialValueInvestmentInUST;
    double initialValueInvestmentInUSDForTITANO;
    double initialValueInvestmentInUSDForLibero;
    double resultValueInUSD;
    int numberOfDays;

    double initialTITANOValue;
    double averageTITANOBuyingValue;
    double sellingTITANOValue;

    double initialLiberoValue;
    double averageLiberoBuyingValue;
    double sellingLiberoValue;

    InvestType investType;


    static Logger logger = Logger.getLogger(Estimator.class);

    public Estimator(InvestType investType, double initialValueInvestmentInUSD, int numberOfDays,
                     double initialTITANOValue, double averageTITANOBuyingValue, double sellingTITANOValue,
                     double initialLiberoValue, double averageLiberoBuyingValue, double sellingLiberoValue)
    {
        this.investType = investType;
        this.initialValueInvestmentInUSD = initialValueInvestmentInUSD;
        this.numberOfDays = numberOfDays;
        this.initialTITANOValue = initialTITANOValue;
        this.averageTITANOBuyingValue = averageTITANOBuyingValue;
        this.sellingTITANOValue = sellingTITANOValue;
        this.initialLiberoValue = initialLiberoValue;
        this.averageLiberoBuyingValue = averageLiberoBuyingValue;
        this.sellingLiberoValue = sellingLiberoValue;
    }

    public double computeGain()
    {
        initialization();
        switch (investType) {
            case safe:
                safeCompoundCalculation();
                break;
            case aggressive:
                aggressiveCompoundCalculation();
                break;
            case moderate:
                moderateCompoundCalculation();
                break;
        }

        return resultValueInUSD;
    }

    private void moderateCompoundCalculation() {
    }

    private void aggressiveCompoundCalculation() {

    }

    private void safeCompoundCalculation()
    {
        int tmpNumberOf20min = numberOfDays * 72;
        double lastUSTStakedValue = initialValueInvestmentInUST - Constants.FIXED_FEE_TRANSACTIONS_UST;
        double lastTitanoStakedValue;
        double lastLiberoStakedValue;
        double tmpValueToBeDistributed;

        double lastUSTStakedValueBeforePuncture = lastUSTStakedValue;
        while(tmpNumberOf20min>0)
        {
            lastUSTStakedValue = lastUSTStakedValue + Constants.RATE_GAIN_UST_ANCHOR_APY*lastUSTStakedValue/(100*365*24*3);
            //if((lastUSTStakedValue-lastUSTStakedValueBeforePuncture)>)

        }
    }

    private void initialization()
    {
        logger.info("Initialization of the investment profile:"+investType);
        switch (investType)
        {
            case safe:
                //transfer money to ust terra station " applied fee is 1ust"
                logger.info("transfer money to ust terra station wallet \" applied fee is 1 ust\"");
                initialValueInvestmentInUST = initialValueInvestmentInUSD -1.0;
                initialValueInvestmentInUSDForTITANO = 0.0;
                initialValueInvestmentInUSDForLibero = 0.0;





                break;
            case moderate:
                System.out.println("not supported");
                break;

            case aggressive:
                System.out.println("under development");

                break;

        }
    }


}
