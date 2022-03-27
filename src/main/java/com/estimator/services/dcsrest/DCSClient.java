package com.estimator.services.dcsrest;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(name="DcService", url ="http://localhost:8080")
public interface DCSClient {


    @RequestMapping(method = RequestMethod.POST, path = "/wallet/connect", consumes = MediaType.APPLICATION_JSON_VALUE)
    String connectWallet(@RequestParam("blockchain") String blockchain, @RequestParam("publicKey") String publicKey,
                         @RequestParam("privateKey") String privateKey);


}
