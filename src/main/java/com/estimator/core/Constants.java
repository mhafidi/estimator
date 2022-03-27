package com.estimator.core;

public class Constants {

    //fees
    public static final double MIN_FIXED_BRIDGE_FEE = 1.0;
    public static final double RATE_VARIABLE_BRIDGE_FEE = 0.1;
    public static final double FIXED_FEE_TRANSACTIONS_UST = 0.25;
    public static final double RATE_TITANO_RATE_BUY = 13.0;
    public static final double RATE_TITANO_RATE_SELL = 25.0;
    public static final double RATE_LIBERO_BUY = 18.0;
    public static final double RATE_LIBERO_SELL = 25.0;

    //interests
    public static final double RATE_GAIN_UST_ANCHOR_APY = 19.0;
    public static final double RATE_GAIN_TITANO_APY = 102483.58;
    public static final double RATE_GAIN_Libero_APY = 158893.59;


    //other constants
    public static final double MIN_BUYING_EXCHANGE_VALUE_TO_TITANO_LIBERO_IN_USD = 4.0;
    public static final double MIN_VALUE_CAN_BE_SWAPPED_FROM_UST_TO_TITANO_AND_LIBERO =
            MIN_BUYING_EXCHANGE_VALUE_TO_TITANO_LIBERO_IN_USD*2  + FIXED_FEE_TRANSACTIONS_UST;
    public static final double MIN_VALUE_CAN_BE_SWAPPED_FROM_TITANO_OR_LIBERO_TO_UST_IN_USD = 20.0;





}
