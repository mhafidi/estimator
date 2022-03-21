package com;

import org.apache.log4j.Logger;

import static com.Constants.MIN_VALUE_CAN_BE_SWAPPED_FROM_UST_TO_TITANO_AND_LIBERO;
import static com.Constants.RATE_VARIABLE_BRIDGE_FEE;

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

    public double computeGain(boolean hold)
    {
        initialization();
        switch (investType) {
            case safe:
                safeCompoundCalculation(hold);
                break;
            case aggressive:
                aggressiveCompoundCalculation(hold);
                break;
            case moderate:
                moderateCompoundCalculation(hold);
                break;
        }

        return resultValueInUSD;
    }

    private void moderateCompoundCalculation(boolean hold) {
    }

    private void aggressiveCompoundCalculation(boolean hold) {

    }

    private void safeCompoundCalculation(boolean hold)
    {
        int tmpNumberOf20min = numberOfDays * 72;
        double lastUSTStakedValue = initialValueInvestmentInUST - Constants.FIXED_FEE_TRANSACTIONS_UST;
        double lastTitanoStakedValue = 0.0;
        double lastTitanoStakedValueBeforePuncture = 0.0;
        double lastLiberoStakedValue = 0.0;
        double lastLiberoStakedValueBeforePuncture = 0.0;
        double tmpValueToBeDistributedInUSD,tmpValueInTokenToBeDistributed,tmpValueInUSTToBeDistributed;
        double gainedValuePerCycle;
        double bridgeFees,allFees;

        int numberOfTimesSoldTokens=0;
        int numberOfTimesBuyingTokens=0;
        double lastUSTStakedValueBeforePuncture = lastUSTStakedValue;
        while(tmpNumberOf20min > 0)
        {
            //ust staking earnings calculation  && Swap to risky tokens when it is possible
            gainedValuePerCycle = Constants.RATE_GAIN_UST_ANCHOR_APY * lastUSTStakedValue/(100*365*24*3);
            lastUSTStakedValue = lastUSTStakedValue + gainedValuePerCycle;
            lastUSTStakedValueBeforePuncture = lastUSTStakedValueBeforePuncture + gainedValuePerCycle/100;


            tmpValueToBeDistributedInUSD = lastUSTStakedValue-lastUSTStakedValueBeforePuncture;
            tmpValueToBeDistributedInUSD = tmpValueToBeDistributedInUSD - Constants.FIXED_FEE_TRANSACTIONS_UST;
            bridgeFees = Math.max(Constants.MIN_FIXED_BRIDGE_FEE,tmpValueToBeDistributedInUSD*RATE_VARIABLE_BRIDGE_FEE/100);
            tmpValueToBeDistributedInUSD = tmpValueToBeDistributedInUSD - bridgeFees;
            allFees = bridgeFees + Constants.FIXED_FEE_TRANSACTIONS_UST;

            if( tmpValueToBeDistributedInUSD > MIN_VALUE_CAN_BE_SWAPPED_FROM_UST_TO_TITANO_AND_LIBERO )
            {

                numberOfTimesBuyingTokens++;
                lastUSTStakedValue = lastUSTStakedValue - tmpValueToBeDistributedInUSD -allFees;
                lastUSTStakedValueBeforePuncture = lastUSTStakedValue;


                tmpValueInTokenToBeDistributed = ((tmpValueToBeDistributedInUSD/2) -
                        Constants.RATE_TITANO_RATE_BUY * (tmpValueToBeDistributedInUSD/2)/100)/averageTITANOBuyingValue;
                lastTitanoStakedValue = lastTitanoStakedValue + tmpValueInTokenToBeDistributed;
                lastTitanoStakedValueBeforePuncture = lastTitanoStakedValueBeforePuncture + tmpValueInTokenToBeDistributed;

                tmpValueInTokenToBeDistributed = ((tmpValueToBeDistributedInUSD/2) -
                        Constants.RATE_LIBERO_BUY * (tmpValueToBeDistributedInUSD/2)/100)/averageLiberoBuyingValue;
                lastLiberoStakedValue = lastLiberoStakedValue + tmpValueInTokenToBeDistributed;
                lastLiberoStakedValueBeforePuncture = lastLiberoStakedValueBeforePuncture + tmpValueInTokenToBeDistributed;


            }

            //titano staking earning calculation && Swap back to ust when it is possible
            gainedValuePerCycle = Constants.RATE_GAIN_TITANO_APY * lastTitanoStakedValue/(100*365*24*3*100);
            lastTitanoStakedValue = lastTitanoStakedValue + gainedValuePerCycle;
            lastTitanoStakedValueBeforePuncture = lastTitanoStakedValueBeforePuncture + gainedValuePerCycle/100;

            tmpValueInTokenToBeDistributed = lastTitanoStakedValue - lastTitanoStakedValueBeforePuncture;
            allFees = tmpValueInTokenToBeDistributed * Constants.RATE_TITANO_RATE_SELL/100;
            tmpValueInTokenToBeDistributed = tmpValueInTokenToBeDistributed - allFees;
            tmpValueToBeDistributedInUSD = tmpValueInTokenToBeDistributed * sellingTITANOValue;
            bridgeFees = Math.max(Constants.MIN_FIXED_BRIDGE_FEE,tmpValueInTokenToBeDistributed*RATE_VARIABLE_BRIDGE_FEE/100);
            tmpValueInUSTToBeDistributed = tmpValueToBeDistributedInUSD - Constants.FIXED_FEE_TRANSACTIONS_UST - bridgeFees; //can be staked directly in ust
            if(tmpValueInUSTToBeDistributed > Constants.MIN_VALUE_CAN_BE_SWAPPED_FROM_TITANO_OR_LIBERO_TO_UST_IN_USD && !hold)
            {
                lastTitanoStakedValue = lastTitanoStakedValue - tmpValueInTokenToBeDistributed - allFees;
                lastTitanoStakedValueBeforePuncture = lastTitanoStakedValue;

                lastUSTStakedValue = lastUSTStakedValue + tmpValueInUSTToBeDistributed;
                lastUSTStakedValueBeforePuncture = lastUSTStakedValueBeforePuncture + tmpValueInUSTToBeDistributed;
                numberOfTimesSoldTokens++;
            }

            //libero staking earning calculation && Swap Back when it is possible
            gainedValuePerCycle = Constants.RATE_GAIN_Libero_APY * lastTitanoStakedValue/(100*365*24*3*100);
            lastLiberoStakedValue = lastLiberoStakedValue + gainedValuePerCycle;
            lastLiberoStakedValueBeforePuncture = lastLiberoStakedValueBeforePuncture + gainedValuePerCycle/100;

            tmpValueInTokenToBeDistributed = lastLiberoStakedValue - lastLiberoStakedValueBeforePuncture;
            allFees = tmpValueInTokenToBeDistributed * Constants.RATE_LIBERO_SELL/100;
            tmpValueInTokenToBeDistributed = tmpValueInTokenToBeDistributed - allFees;
            tmpValueToBeDistributedInUSD = tmpValueInTokenToBeDistributed * sellingLiberoValue;
            bridgeFees = Math.max(Constants.MIN_FIXED_BRIDGE_FEE,tmpValueInTokenToBeDistributed*RATE_VARIABLE_BRIDGE_FEE/100);
            tmpValueInUSTToBeDistributed = tmpValueToBeDistributedInUSD - Constants.FIXED_FEE_TRANSACTIONS_UST - bridgeFees; //can be staked directly in ust
            if(tmpValueInUSTToBeDistributed > Constants.MIN_VALUE_CAN_BE_SWAPPED_FROM_TITANO_OR_LIBERO_TO_UST_IN_USD && !hold)
            {
                lastLiberoStakedValue = lastLiberoStakedValue - tmpValueInTokenToBeDistributed - allFees;
                lastLiberoStakedValueBeforePuncture = lastLiberoStakedValue;

                lastUSTStakedValue = lastUSTStakedValue + tmpValueInUSTToBeDistributed;
                lastUSTStakedValueBeforePuncture = lastUSTStakedValueBeforePuncture + tmpValueInUSTToBeDistributed;
                numberOfTimesSoldTokens++;
            }


            if(logger.isDebugEnabled())
            {
                logger.debug("Current UST Staked value is ["+lastUSTStakedValue+" ust($)]");
                logger.debug("Current TITANO Staked is ["+lastTitanoStakedValue+" titano]");
                logger.debug("Current LIBERO Deposit is ["+lastLiberoStakedValue+" libero]");
            }



            tmpNumberOf20min--;
        }
        System.out.println("Number of UST: "+ lastUSTStakedValue);
        System.out.println("Number of TITANO: "+ lastTitanoStakedValue);
        System.out.println("Number of Libero: "+ lastLiberoStakedValue);
        System.out.println("Number of times tokens sold: "+numberOfTimesSoldTokens);
        System.out.println("Number of times tokens Bought: "+numberOfTimesBuyingTokens);
        resultValueInUSD = lastUSTStakedValue + lastTitanoStakedValue * sellingTITANOValue - Constants.RATE_TITANO_RATE_SELL * lastTitanoStakedValue * sellingTITANOValue/100
        +lastLiberoStakedValue * sellingLiberoValue - Constants.RATE_LIBERO_SELL * lastLiberoStakedValue * sellingLiberoValue/100;
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
