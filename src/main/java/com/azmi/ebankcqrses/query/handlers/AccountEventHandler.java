package com.azmi.ebankcqrses.query.handlers;

import com.azmi.ebankcqrses.commands.enums.OperationType;
import com.azmi.ebankcqrses.commands.events.*;
import com.azmi.ebankcqrses.query.entities.Account;
import com.azmi.ebankcqrses.query.entities.AccountOperation;
import com.azmi.ebankcqrses.query.repository.AccountOperationRepository;
import com.azmi.ebankcqrses.query.repository.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.EventMessage;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component @Slf4j
public class AccountEventHandler {
    private AccountRepository accountRepository;
    private AccountOperationRepository accountOperationRepository;

    public AccountEventHandler(AccountRepository accountRepository, AccountOperationRepository accountOperationRepository) {
        this.accountRepository = accountRepository;
        this.accountOperationRepository = accountOperationRepository;
    }

    @EventHandler
    public void on(AccountCreatedEvent event, EventMessage eventMessage){
        log.info("Query Side AccountCreatedEvent received");
        Account account = Account.builder()
                .id(event.getId())
                .balance(event.getInitialBalance())
                .status(event.getStatus())
                .createdAt(eventMessage.getTimestamp())
                .currency(event.getCurrency())
                .build();
        accountRepository.save(account);
    }
    @EventHandler
    public void on(AccountActivatedEvent event, EventMessage eventMessage){
        log.info("Query Side AccountActivatedEvent received");

        Account account= accountRepository.findById(event.getId()).get();
        account.setStatus(event.getStatus());
        accountRepository.save(account);

    }
    @EventHandler
    public void on(AccountDebitedEvent event, EventMessage eventMessage){
        log.info("Query Side AccountDebitedEvent received");

        Account account= accountRepository.findById(event.getId()).get();
        AccountOperation accountOperation = AccountOperation.builder()
        .id(UUID.randomUUID().toString())
        .ammount(event.getAmount())
        .date(eventMessage.getTimestamp())
        .type(OperationType.DEBIT)
        .currency(event.getCurrency())
        .account(account)
        .build();
        accountOperationRepository.save(accountOperation);
        account.setBalance(account.getBalance() - event.getAmount());
        accountRepository.save(account);

    }
    @EventHandler
    public void on(AccountCreditedEvent event, EventMessage eventMessage){
        log.info("Query Side AccountCreditedEvent received");

        Account account= accountRepository.findById(event.getId()).get();
        AccountOperation accountOperation = AccountOperation.builder()
                .id(UUID.randomUUID().toString())
                .ammount(event.getAmount())
                .date(eventMessage.getTimestamp())
                .type(OperationType.CREDIT)
                .currency(event.getCurrency())
                .account(account).build();
        accountOperationRepository.save(accountOperation);
        account.setBalance(account.getBalance() + event.getAmount());
        accountRepository.save(account);

    }
    @EventHandler
    public void on(AccountStatusUpdateEvent event, EventMessage eventMessage){
        log.info("Query Side AccountStatusUpdateEvent received");

        Account account= accountRepository.findById(event.getId()).get();
        account.setStatus(event.getStatus());
        accountRepository.save(account);

    }
}
