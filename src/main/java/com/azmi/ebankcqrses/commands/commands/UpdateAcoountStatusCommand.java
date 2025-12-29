package com.azmi.ebankcqrses.commands.commands;

import com.azmi.ebankcqrses.commands.enums.AccountStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Getter @AllArgsConstructor
public class UpdateAcoountStatusCommand {
    @TargetAggregateIdentifier
    private String id;
    private AccountStatus status;
}
