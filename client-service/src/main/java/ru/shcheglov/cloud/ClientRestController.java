package ru.shcheglov.cloud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ClientRestController {

    @Autowired
    private ClientDAO dao;

    @Autowired
    private ClientRepository repo;

    @RequestMapping("/greeting")
    public String greetings() {
        return "Hello from ClientService!";
    }

    @RequestMapping("/create")
    public Client create(@RequestParam String name) {
        return dao.create(name);
    }

    @RequestMapping("/update/{id}")
    public ResponseEntity update(@PathVariable Integer id, @RequestParam String name) {
        if (dao.update(id, name)) {
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping("/delete/{id}")
    public void delete(@PathVariable("id") Integer id) {
        repo.deleteById(id);
    }

    @RequestMapping("/get")
    public List<? extends Client> get() {
        return repo.findAll();
    }

    @RequestMapping("/get/{id}")
    public Client get(@PathVariable("id") Integer id) {
        return repo.getOne(id);
    }
}
