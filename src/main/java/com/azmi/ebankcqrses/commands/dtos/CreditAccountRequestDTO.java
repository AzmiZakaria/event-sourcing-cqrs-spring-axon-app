package com.azmi.ebankcqrses.commands.dtos;

public record CreditAccountRequestDTO(String accountID, double amount, String curency) {
}
