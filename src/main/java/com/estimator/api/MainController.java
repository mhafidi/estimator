package com.estimator.api;


import com.estimator.core.MainControllerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@RestController
@RequestMapping("/main-control")
public class MainController {
    private static final Logger mainControllerLogger = LoggerFactory.getLogger(MainController.class);
    @Autowired
    MainControllerService mainControllerService;

    @GetMapping("/wallet/connect/{blockchain}/public_key/{public_key}/private_key/{private_key}")
    public String connectWallet(@PathVariable(value="blockchain")String blockchain,@PathVariable(value="public_key") String public_key,
                           @PathVariable(value="private_key") String private_key)
    {
        mainControllerLogger.info("Add Wallet Blockchain ["+blockchain+"] public key ["+public_key+"]");
        return mainControllerService.connectWallet(blockchain, public_key, private_key);
    }


}
