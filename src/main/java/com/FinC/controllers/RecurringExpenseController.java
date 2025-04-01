package com.FinC.controllers;

import com.FinC.dtos.RecurringExpenseDto;
import com.FinC.models.RecurringExpense;
import com.FinC.services.RecurringExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/recurringExpense")
public class RecurringExpenseController {

    @Autowired
    RecurringExpenseService recurringExpenseService;

    @GetMapping
    public ResponseEntity<List<RecurringExpense>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(recurringExpenseService.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<RecurringExpense> findById(@PathVariable(name = "id") UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(recurringExpenseService.findById(id));
    }
    @PostMapping
    public ResponseEntity<RecurringExpense> createRecurringExpense(@RequestBody RecurringExpenseDto recurringExpenseDto) throws IOException {
        return ResponseEntity.status(HttpStatus.CREATED).body(recurringExpenseService.createRecurringExpense(recurringExpenseDto));
    }
    @PutMapping("/{id}")
    public ResponseEntity<RecurringExpense> updateRecurringExpense(@PathVariable(name = "id")UUID id,
                                                                   @RequestBody RecurringExpenseDto recurringExpenseDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(recurringExpenseService.updateRecurringExpense(recurringExpenseDto,id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<RecurringExpense> deleteRecurringExpense(@PathVariable(name = "id")UUID id){
        recurringExpenseService.deleteRecurringExpense(id);
        return ResponseEntity.noContent().build();
    }
}
