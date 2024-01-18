package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.example.entity.Account;
import com.example.repository.AccountRepository;

@Component
public class AccountService {
    AccountRepository accountRepository;
    @Autowired
    public AccountService(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    public Account insertAccount(Account account){
        boolean accountNameExists = accountRepository.existsByUsername(account.getUsername());
        int pwLength = account.getPassword().length();
        if(accountNameExists){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Username already exists");
        } else if(pwLength<4){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ya messed up");
        }
        return accountRepository.save(account);
    }

    public Account loginAccount(Account account){
        Account loginnedAcc =  accountRepository.findByUsernameAndPassword(account.getUsername(), account.getPassword());
        if(loginnedAcc == null){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        } else{
            return loginnedAcc;
        }
    }
}