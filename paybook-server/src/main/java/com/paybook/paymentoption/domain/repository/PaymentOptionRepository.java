package com.paybook.paymentoption.domain.repository;

import com.paybook.paymentoption.domain.PaymentOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentOptionRepository extends JpaRepository<PaymentOption, Long> {
}
