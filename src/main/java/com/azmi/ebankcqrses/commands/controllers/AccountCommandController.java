package com.azmi.ebankcqrses.commands.controllers;

import com.azmi.ebankcqrses.commands.commands.AddAcoountCommand;
import com.azmi.ebankcqrses.commands.commands.CreditAcoountCommand;
import com.azmi.ebankcqrses.commands.commands.DebitAcoountCommand;
import com.azmi.ebankcqrses.commands.dtos.AddNewAccountRequestDTO;
import com.azmi.ebankcqrses.commands.dtos.CreditAccountRequestDTO;
import com.azmi.ebankcqrses.commands.dtos.DebitAccountRequestDTO;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

@RestController
@RequestMapping("/commands")
public class AccountCommandController {
    private final EventStore eventStore;
    private CommandGateway commandGateway;

    public AccountCommandController(CommandGateway commandGateway, EventStore eventStore) {
        this.commandGateway = commandGateway;
        this.eventStore = eventStore;
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
    @PostMapping("/credit")
    public  CompletableFuture<String> creditAccountCommand(@RequestBody CreditAccountRequestDTO request){
        CompletableFuture<String> response = commandGateway.send(new CreditAcoountCommand(
                request.accountID(),
                request.amount(),
                request.curency()
        ));
        return response;
    }
    @PostMapping("/debit")
    public  CompletableFuture<String> debitAccountCommand(@RequestBody DebitAccountRequestDTO request){
        CompletableFuture<String> response = commandGateway.send(new DebitAcoountCommand(
                request.accountID(),
                request.amount(),
                request.curency()
        ));
        return response;
    }
    @ExceptionHandler(Exception.class)
    public String execptionHandler(Exception exception){
        return exception.getMessage();
    }
    @GetMapping("/events/{accountId}")
    public Stream eventStore(@PathVariable String accountId){
        return eventStore.readEvents(accountId).asStream();
    }
}
