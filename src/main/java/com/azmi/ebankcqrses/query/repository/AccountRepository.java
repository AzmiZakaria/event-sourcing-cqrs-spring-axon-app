package com.azmi.ebankcqrses.query.repository;

import com.azmi.ebankcqrses.query.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, String> {
}
