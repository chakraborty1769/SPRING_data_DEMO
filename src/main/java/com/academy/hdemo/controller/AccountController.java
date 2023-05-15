package com.academy.hdemo.controller;

import com.academy.hdemo.dto.Account;
import com.academy.hdemo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController()
@RequestMapping("/account")
public class AccountController {

    @Autowired
    AccountService accountService;

    @GetMapping("/detail")
    public Account detail(@RequestParam long accountId) {
        return accountService.findById(accountId);
    }

    @PostMapping("/new")
    public void newAccount(@RequestBody Account account) {
        accountService.saveAccount(account);
    }

    @PutMapping
    void updateAccount(@RequestBody Account account) {
        accountService.update(account);
    }

    @DeleteMapping("/delete")
    void delete(@RequestBody Account account) {
        accountService.deleteAccount(account);
    }

    @GetMapping()
    public List allAccount() {
        return accountService.getAllAccounts();
    }
    @PostMapping("/transfer")
    void transfer(@RequestBody Map<String, Long>transferDetail){
        accountService.transferAmount(transferDetail);
    }
    @GetMapping("/getAll")
    public List <Account> getAllAccount() {
        return accountService.getAllAccounts();
    }

}
