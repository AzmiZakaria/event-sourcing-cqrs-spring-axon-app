package com.azmi.ebankcqrses.commands.events;

import com.azmi.ebankcqrses.commands.enums.AccountStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AccountStatusUpdateEvent {
    private String id;
    private AccountStatus status;
}
