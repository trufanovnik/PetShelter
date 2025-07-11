package com.ensep.petshelter.repositories;

import com.ensep.petshelter.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByLogin(String login);

    default Account findByLoginOrThrow(String login) {
        return findByLogin(login).orElseThrow(()->
                new UsernameNotFoundException("Аккаунт с логином " + login + " не найден"));
    }
}
