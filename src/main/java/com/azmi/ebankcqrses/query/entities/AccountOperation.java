package com.azmi.ebankcqrses.query.entities;

import com.azmi.ebankcqrses.commands.enums.AccountStatus;
import com.azmi.ebankcqrses.commands.enums.OperationType;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.Date;

@Entity @Getter
@Setter @AllArgsConstructor @NoArgsConstructor @Builder
public class AccountOperation {
    @Id
    private String id;
    private Instant date;
    private double ammount;
    @Enumerated(EnumType.STRING)
    private OperationType type;
    private String currency;
    @ManyToOne
    private Account account;
}
