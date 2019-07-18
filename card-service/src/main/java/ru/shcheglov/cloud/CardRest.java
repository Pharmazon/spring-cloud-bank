package ru.shcheglov.cloud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CardRest {

    @Autowired
    private CardNumberGenerator generator;

    @RequestMapping("create")
    public String createNewCard() {
        return generator.generate();
    }

    @RequestMapping("/greeting")
    public String greetings() {
        return "Hello from CardService!";
    }
}
