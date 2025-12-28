package com.azmi.ebankcqrses.commands.events;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AccountDebitedEvent {
    private String id;
    private double amount;
    private String currency;
}
