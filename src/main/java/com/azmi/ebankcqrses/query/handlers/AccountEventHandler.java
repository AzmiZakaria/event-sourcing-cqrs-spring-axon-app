package com.azmi.ebankcqrses.query.handlers;

import com.azmi.ebankcqrses.commands.events.AccountCreatedEvent;
import com.azmi.ebankcqrses.commands.events.AccountCreditedEvent;
import com.azmi.ebankcqrses.query.entities.Account;
import com.azmi.ebankcqrses.query.repository.AccountOperationRepository;
import com.azmi.ebankcqrses.query.repository.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.EventMessage;
import org.springframework.stereotype.Component;

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
}
