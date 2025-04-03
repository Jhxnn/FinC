package com.FinC.controllers;

import com.FinC.dtos.AccountAndDateDto;
import com.FinC.dtos.ExpenseDto;
import com.FinC.models.Expense;
import com.FinC.services.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/expense")
public class ExpenseController {

    @Autowired
    ExpenseService expenseService;

    @GetMapping
    public ResponseEntity<List<Expense>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(expenseService.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Expense> findById(@PathVariable(name = "id") UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(expenseService.findById(id));
    }
    @GetMapping("/account/{accountId}")
    public ResponseEntity<List<Expense>> findByAccount(@PathVariable(name = "accountId")UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(expenseService.findByAccount(id));
    }

    @GetMapping("/date")
    public ResponseEntity<List<Expense>> findByDate(@RequestBody AccountAndDateDto accountAndDateDto){
        return ResponseEntity.status(HttpStatus.OK).body(expenseService.findByDate(
                accountAndDateDto.accountId(),
                accountAndDateDto.startDate()
                ,accountAndDateDto.endDate()));
    }

    @PostMapping
    public ResponseEntity<Expense> createExpense(@RequestBody ExpenseDto expenseDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(expenseService.createExpense(expenseDto));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Expense> updateExpense(@PathVariable(name = "id")UUID id,
                                                 @RequestBody ExpenseDto expenseDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(expenseService.updateExpense(expenseDto,id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Expense> deleteExpense(@PathVariable(name = "id")UUID id){
        expenseService.deleteExpense(id);
        return ResponseEntity.noContent().build();
    }
}
