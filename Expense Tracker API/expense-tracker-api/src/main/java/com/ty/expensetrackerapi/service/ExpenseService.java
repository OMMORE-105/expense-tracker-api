package com.ty.expensetrackerapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ty.expensetrackerapi.dto.ExpenseDTO;
import com.ty.expensetrackerapi.entity.Expense;
import com.ty.expensetrackerapi.repository.ExpenseRepository;

@Service
public class ExpenseService {
	
	@Autowired
    private ExpenseRepository repo;
	
	//  create
	public ExpenseDTO createExpense(ExpenseDTO dto) {

	    Expense expense = new Expense();
	    expense.setTitle(dto.getTitle());
	    expense.setAmount(dto.getAmount());
	    expense.setCategory(dto.getCategory());
	    expense.setDate(dto.getDate());

	    Expense saved = repo.save(expense);

	    ExpenseDTO responseDto = new ExpenseDTO();
	    responseDto.setTitle(saved.getTitle());
	    responseDto.setAmount(saved.getAmount());
	    responseDto.setCategory(saved.getCategory());
	    responseDto.setDate(saved.getDate());

	    return responseDto;
	}
	
	//  get all
    public List<ExpenseDTO> getAllExpenses() {
    	 List<Expense> expenses = repo.findAll();

         return expenses.stream().map(this::mapToDTO).toList();
    }
    
    //  delete
    public void deleteExpense(Long id) {
    	if (!repo.existsById(id)) {
            throw new RuntimeException("Expense not found with ID: " + id);
        }

        repo.deleteById(id);
    }
    
    //  update
    public ExpenseDTO updateExpense(Long id, ExpenseDTO dto) {

        Expense expense = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Expense not found"));

        expense.setTitle(dto.getTitle());
        expense.setAmount(dto.getAmount());
        expense.setCategory(dto.getCategory());
        expense.setDate(dto.getDate());

        Expense updated = repo.save(expense);

        return mapToDTO(updated);
    }
    
    
    //  mapper
    private ExpenseDTO mapToDTO(Expense expense) {

        ExpenseDTO dto = new ExpenseDTO();
        dto.setTitle(expense.getTitle());
        dto.setAmount(expense.getAmount());
        dto.setCategory(expense.getCategory());
        dto.setDate(expense.getDate());

        return dto;
    }

}
