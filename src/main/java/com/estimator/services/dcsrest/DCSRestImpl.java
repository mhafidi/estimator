package com.estimator.services.dcsrest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DCSRestImpl implements IRestService {


    private final DCSClient dcsClient;

    @Autowired
    public DCSRestImpl(DCSClient dcsClient) {
        this.dcsClient = dcsClient;
    }


    @Override
    public String connectWallet(String blockchain, String publicKey, String privateKey) {
        return dcsClient.connectWallet(blockchain,publicKey,privateKey);
    }
}
