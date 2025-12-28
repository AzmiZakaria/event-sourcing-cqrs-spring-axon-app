package com.azmi.ebankcqrses.commands.controllers;

import com.azmi.ebankcqrses.commands.commands.AddAcoountCommand;
import com.azmi.ebankcqrses.commands.dtos.AddNewAccountRequestDTO;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/commands")
public class AccountCommandController {
    private CommandGateway commandGateway;

    public AccountCommandController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PostMapping("/add")
    public  CompletableFuture<String> addNewAccountCommand(@RequestBody AddNewAccountRequestDTO request){
        CompletableFuture<String> response = commandGateway.send(new AddAcoountCommand(
                UUID.randomUUID().toString(),
                request.initialBallance(),
                request.cuurency()
        ));
        return response;
    }
}
