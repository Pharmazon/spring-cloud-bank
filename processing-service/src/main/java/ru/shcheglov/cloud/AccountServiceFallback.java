package ru.shcheglov.cloud;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@Component
public class AccountServiceFallback implements AccountServiceClient {

//    @Override
//    public boolean checkout(Integer accountId, BigDecimal sum) {
//        return false;
//    }

    @Override
    public boolean checkout(@PathVariable("id") Integer accountId,
                            @RequestParam("sum") BigDecimal sum) {
        return false;
    }

}


