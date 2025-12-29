package com.azmi.ebankcqrses.commands.dtos;

import com.azmi.ebankcqrses.commands.enums.AccountStatus;

public record UpdateAccountStatusRequestDTO(String accountID, AccountStatus accountStatus ) {
}
