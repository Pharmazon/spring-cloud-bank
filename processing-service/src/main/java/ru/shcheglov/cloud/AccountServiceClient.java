package ru.shcheglov.cloud;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@FeignClient(name = "AccountService", fallback = AccountServiceFallback.class)
public interface AccountServiceClient {

    @RequestMapping("/checkout/{id}")
    boolean checkout(@PathVariable("id") Integer accountId,
                     @RequestParam("sum") BigDecimal sum);
}

