package com.azmi.ebankcqrses.commands.aggregates;

import com.azmi.ebankcqrses.commands.commands.AddAcoountCommand;
import com.azmi.ebankcqrses.commands.enums.AccountStatus;
import com.azmi.ebankcqrses.commands.events.AccountCreatedEvent;
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
    }
        @EventSourcingHandler
        public void on(AccountCreatedEvent event){
            log.info("Account CreatedEvent occured");
            this.id =event.getId();
            this.balance = event.getInitialBalance();
            this.status = event.getStatus();
    }
}
