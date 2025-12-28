package com.azmi.ebankcqrses.commands.commands;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Getter @AllArgsConstructor
public class AddAcoountCommand {
    @TargetAggregateIdentifier
    private String id;
    private double initialBallance;
    private String cuurency;
}
