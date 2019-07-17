package ru.shcheglov.cloud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientDAO {

    @Autowired
    private ClientRepository repo;

    public ClientEntity create(String name) {
        ClientEntity client = new ClientEntity();
        client.setName(name);
        return repo.save(client);
    }

    public boolean update(Integer id, String name) {
        ClientEntity client = repo.getOne(id);
        if (client == null) {
            return false;
        } else {
            client.setName(name);
            repo.save(client);
            return true;
        }
    }
}
