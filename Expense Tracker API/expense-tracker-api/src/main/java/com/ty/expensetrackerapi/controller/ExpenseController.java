package com.ty.expensetrackerapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ty.expensetrackerapi.dto.ExpenseDTO;
import com.ty.expensetrackerapi.dto.ResponseStructure;
import com.ty.expensetrackerapi.entity.Expense;
import com.ty.expensetrackerapi.service.ExpenseService;

@RestController
@RequestMapping("/expenses")
public class ExpenseController {
	
	@Autowired
    private ExpenseService service;

    @PostMapping("/add")
    public ResponseEntity<ResponseStructure<ExpenseDTO>> createExpense(@RequestBody ExpenseDTO expense) {

        ExpenseDTO savedExpense = service.createExpense(expense);

        ResponseStructure<ExpenseDTO> response = new ResponseStructure<>();
        response.setStatusCode(201);
        response.setMessage("Expense Created Successfully");
        response.setData(savedExpense); // ✅ use saved, not input

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/all")
    public ResponseEntity<ResponseStructure<List<ExpenseDTO>>> getAllExpenses() {

        List<ExpenseDTO> expenses = service.getAllExpenses();

        ResponseStructure<List<ExpenseDTO>> response = new ResponseStructure<>();
        response.setStatusCode(200);
        response.setMessage("Expenses Fetched Successfully");
        response.setData(expenses);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseStructure<String>> delete(@PathVariable Long id) {

        service.deleteExpense(id);

        ResponseStructure<String> response = new ResponseStructure<>();
        response.setStatusCode(200);
        response.setMessage("Expense Deleted Successfully");
        response.setData("Deleted ID: " + id);

        return ResponseEntity.ok(response);
    }
	
    @PutMapping("/{id}")
    public ResponseEntity<ResponseStructure<ExpenseDTO>> updateExpense(@PathVariable Long id, @RequestBody ExpenseDTO expense) {

        ExpenseDTO updatedExpense = service.updateExpense(id, expense);

        ResponseStructure<ExpenseDTO> response = new ResponseStructure<>();
        response.setStatusCode(200);
        response.setMessage("Expense Updated Successfully");
        response.setData(updatedExpense);

        return ResponseEntity.ok(response);
    }
    
    
}
