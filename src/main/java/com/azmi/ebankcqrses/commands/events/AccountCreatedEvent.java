package com.azmi.ebankcqrses.commands.events;

import com.azmi.ebankcqrses.commands.enums.AccountStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class AccountCreatedEvent {
    private String id;
    private double initialBalance;
    private AccountStatus status;
    private String currency;
}
