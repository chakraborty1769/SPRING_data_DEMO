package com.academy.hdemo.service;


import com.academy.hdemo.dao.AccountDAO;
import com.academy.hdemo.dto.Account;
import com.academy.hdemo.exception.AccountNotFoundException;
import com.academy.hdemo.exception.InsufficientAmountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AccountService {

    @Autowired
    @Qualifier("AccountDAOImp2")
    AccountDAO accountDAO;

    public Account findById(long id) {
        return accountDAO.findByAccountId(id);
    }

    public void saveAccount(Account account) {
        accountDAO.save(account);
    }

    public void update(Account account) {
        accountDAO.update(account);
    }

    public void deleteAccount(Account account) {
        accountDAO.delete(account);
    }

    public List getAllAccounts() {
        return accountDAO.accountList();
    }
    public void transferAmount(Map<String, Long> transferDetail) {
        Long senderID = transferDetail.get("fromAccount");
        Long receiverID = transferDetail.get("toAccount");
        Long amount = transferDetail.get("transferAmount");

        Account sender = accountDAO.findByAccountId(senderID);
        Account receiver = accountDAO.findByAccountId(receiverID);


        if (sender == null && receiver == null) {
            throw new AccountNotFoundException("The sender and receiver does not exist!");
        } else if (sender == null) {
            throw new AccountNotFoundException("The sender account does not exist!");
        } else if (receiver == null) {
            throw new AccountNotFoundException("The receiver account does not exist!");
        } else {
            long currentSenderAmount = sender.getBalance();
            if (currentSenderAmount >= amount) {
                long newSenderAmount = currentSenderAmount - amount;
                sender.setBalance(newSenderAmount);
                accountDAO.update(sender);
            } else {
                throw new InsufficientAmountException("Insufficient storage! You cannot exceed your current balance!");
            }


            long currentReceiverAmount = receiver.getBalance();
            long newTakerAmount = currentReceiverAmount + amount;
            receiver.setBalance(newTakerAmount);
            accountDAO.update(receiver);

        }
    }

}
