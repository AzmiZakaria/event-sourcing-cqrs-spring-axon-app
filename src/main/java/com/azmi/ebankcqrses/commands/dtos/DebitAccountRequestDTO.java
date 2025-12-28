package com.azmi.ebankcqrses.commands.dtos;

public record DebitAccountRequestDTO(String accountID, double amount, String curency) {
}
