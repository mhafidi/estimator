package com.estimator.core;

import com.estimator.services.dcsrest.IRestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MainControllerService
{
    private static final Logger logger = LoggerFactory.getLogger(MainControllerService.class);
    protected final IRestService iRestService;

    @Autowired
    public MainControllerService(IRestService iRestService)
    {
        this.iRestService = iRestService;
    }

    public String connectWallet(String blockchain,String publicKey,String privateKey)
    {
        String response;
        try {
            response = iRestService.connectWallet(blockchain, publicKey, privateKey);
            logger.info(response);
        }
        catch (Exception e)
        {
            response="Internal Error occurred: DEFI Communication service is unreachable";
        }
        return response;
    }
}
