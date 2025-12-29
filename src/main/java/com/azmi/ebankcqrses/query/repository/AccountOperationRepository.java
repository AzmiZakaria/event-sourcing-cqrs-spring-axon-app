package com.azmi.ebankcqrses.query.repository;

import com.azmi.ebankcqrses.query.entities.Account;
import com.azmi.ebankcqrses.query.entities.AccountOperation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountOperationRepository extends JpaRepository<AccountOperation, String> {
    List<AccountOperation> findByAccountId(String id);
}
