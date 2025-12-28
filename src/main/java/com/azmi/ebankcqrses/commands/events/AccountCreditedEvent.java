package com.azmi.ebankcqrses.commands.events;

import com.azmi.ebankcqrses.commands.enums.AccountStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AccountCreditedEvent {
    private String id;
    private double amount;
    private String currency;
}
