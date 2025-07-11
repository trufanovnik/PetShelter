package com.ensep.petshelter.services.auth;

import com.ensep.petshelter.dto.ShelterRegistrationRequest;
import com.ensep.petshelter.entities.Account;
import com.ensep.petshelter.entities.AccountType;
import com.ensep.petshelter.entities.Shelter;
import com.ensep.petshelter.exceptions.AccountExistException;
import com.ensep.petshelter.mapper.AccountMapper;
import com.ensep.petshelter.mapper.ShelterMapper;
import com.ensep.petshelter.repositories.AccountRepository;
import com.ensep.petshelter.repositories.ShelterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AccountMapper accountMapper;
    private final AccountRepository accountRepository;
    private final ShelterMapper shelterMapper;
    private final ShelterRepository shelterRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void registerShelter(ShelterRegistrationRequest request) {
        if (accountRepository.existByLogin(request.getEmail())){
            throw new AccountExistException("Приют с таким email уже существует");
        }
        Account account = accountMapper.shelterRequestToAccount(request);
        account.setPassword(passwordEncoder.encode(request.getPassword()));
        account.setAccountType(AccountType.SHELTER);
        account = accountRepository.save(account);

        Shelter shelter = shelterMapper.shelterRequestToShelter(request);
        shelter.setAccount(account);
        shelterRepository.save(shelter);
    }
}
