package ru.shcheglov.cloud;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ProcessingRestController {

    private ProcessingRepository repo;
    private AccountServiceClient accountServiceClient;
    private CardServiceClient cardServiceClient;

    @Autowired
    public ProcessingRestController(ProcessingRepository repo,
                                    AccountServiceClient accountServiceClient,
                                    CardServiceClient cardServiceClient) {
        this.repo = repo;
        this.accountServiceClient = accountServiceClient;
        this.cardServiceClient = cardServiceClient;
    }

    @RequestMapping("/greeting")
    public String greetings() {
        return "Hello from ProcessingService!";
    }

    @RequestMapping("/issue/{accountId}")
    public String issueNewCard(@PathVariable Integer accountId) {
        final String card = cardServiceClient.createCard();
        if (card == null) {
            return "CARD_SERVICE_NOT_AVAILABLE";
        }
        ProcessingEntity pe = new ProcessingEntity();
        pe.setCard(card);
        pe.setAccountId(accountId);
        repo.save(pe);
        return card;
    }

    @RequestMapping("/checkout/{card}")
    public boolean checkout(@PathVariable String card,
                            @RequestParam BigDecimal sum) {
        ProcessingEntity pe = repo.findByCard(card);
        if (pe == null) {
            return false;
        }
        return accountServiceClient.checkout(pe.getAccountId(), sum);
    }

    @RequestMapping("/get")
    public Map<Integer, String> getByAccount(@RequestParam("account_id") List<Integer> accountIdList) {
        List<ProcessingEntity> list = repo.findByAccountIdIn(accountIdList);
        Map<Integer, String> map = new HashMap<Integer, String>();
        for (ProcessingEntity pe: list) {
            map.put(pe.getAccountId(), pe.getCard());
        }
        return map;
    }

    @HystrixCommand(fallbackMethod = "testFallback")
    @RequestMapping("/test")
    public String testHystrix(Boolean fail) {
        if (Boolean.TRUE.equals(fail)) {
            throw new RuntimeException();
        }
        return "OK";
    }

    private String testFallback(Boolean fail) {
        return "FAILED";
    }

}
