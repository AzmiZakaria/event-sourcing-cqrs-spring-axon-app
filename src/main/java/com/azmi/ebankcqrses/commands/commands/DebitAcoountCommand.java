package com.azmi.ebankcqrses.commands.commands;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Getter @AllArgsConstructor
public class DebitAcoountCommand {
    @TargetAggregateIdentifier
    private String id;
    private double amount;
    private String curency;
}
