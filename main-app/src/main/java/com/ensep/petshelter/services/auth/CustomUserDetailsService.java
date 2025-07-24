package com.ensep.petshelter.services.auth;

import com.ensep.petshelter.config.security.CustomUserDetails;
import com.ensep.petshelter.entities.Account;
import com.ensep.petshelter.repositories.AccountRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final AccountRepository accountRepository;

    public CustomUserDetailsService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String login) {
        Account account = accountRepository.findByLoginOrThrow(login);
        return new CustomUserDetails(account);
    }
}
