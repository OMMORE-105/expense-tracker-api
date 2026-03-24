package com.ty.expensetrackerapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ty.expensetrackerapi.entity.Expense;

public interface ExpenseRepository extends JpaRepository<Expense, Long>  {

}
