package com.azmi.ebankcqrses.commands.aggregates;

import com.azmi.ebankcqrses.commands.commands.AddAcoountCommand;
import com.azmi.ebankcqrses.commands.commands.CreditAcoountCommand;
import com.azmi.ebankcqrses.commands.commands.DebitAcoountCommand;
import com.azmi.ebankcqrses.commands.enums.AccountStatus;
import com.azmi.ebankcqrses.commands.events.AccountActivatedEvent;
import com.azmi.ebankcqrses.commands.events.AccountCreatedEvent;
import com.azmi.ebankcqrses.commands.events.AccountCreditedEvent;
import com.azmi.ebankcqrses.commands.events.AccountDebitedEvent;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
@Slf4j
public class AccountAggregate {
    @AggregateIdentifier
    private String id;
    private double balance;
    private AccountStatus status;
    public AccountAggregate() {
    }

    @CommandHandler
    public AccountAggregate(AddAcoountCommand command) {
        log.info("CreateAccount Command Received");
        if (command.getInitialBallance() < 0) throw new IllegalArgumentException("Balance negative exception");
        AggregateLifecycle.apply(new AccountCreatedEvent(
                command.getId(),
                command.getInitialBallance(),
                AccountStatus.CREATED,
                command.getCurency()
        ));
        AggregateLifecycle.apply(new AccountActivatedEvent(
                command.getId(),
                AccountStatus.ACTIVATED
        ));
    }
    @EventSourcingHandler
    public void on(AccountCreatedEvent event){
        log.info("Account CreatedEvent occured");
        this.id =event.getId();
        this.balance = event.getInitialBalance();
        this.status = event.getStatus();
    }
    @EventSourcingHandler
    public void on(AccountActivatedEvent event){
        log.info("AccountActivatedEvent occured");
        this.id =event.getId();
            this.status = event.getStatus();
    }

    @CommandHandler
    public void handel(CreditAcoountCommand command) {
        log.info("CreditAcoountCommand  Received");
        if(!status.equals(AccountStatus.ACTIVATED)) throw new RuntimeException("Account"+command.getId()+" Status not Activated");
        if (command.getAmount() < 0) throw new IllegalArgumentException("Amount must be positive Exception");
        AggregateLifecycle.apply(new AccountCreditedEvent(
                command.getId(),
                command.getAmount(),
                command.getCurency()
        ));
    }
    @EventSourcingHandler
    public void on(AccountCreditedEvent event){
        log.info("AccountCreditedEvent occured");
        this.id =event.getId();
        this.balance = this.balance + event.getAmount();
    }
    
}
