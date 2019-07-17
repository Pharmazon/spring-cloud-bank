package ru.shcheglov.cloud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class AccountDAO {
    private static Lock balanceLock = new ReentrantLock();

    @Autowired
    private AccountRepository repo;

    public void create(Integer clientId) {
        AccountEntity account = new AccountEntity();
        account.setClientId(clientId);
        account.setBalance(BigDecimal.ZERO);
        repo.save(account);
    }

    public boolean addBalance(Integer id, BigDecimal balance) {
        balanceLock.lock();
        try {
            AccountEntity account = repo.getOne(id);
            if (account != null) {
                account.setBalance(account.getBalance().add(balance));
                if (account.getBalance().compareTo(BigDecimal.ZERO) >= 0) {
                    repo.save(account);
                    return true;
                }
            }
        } finally {
             balanceLock.unlock();
        }
        return false;
    }
}
