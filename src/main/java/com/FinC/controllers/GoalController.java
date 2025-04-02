package com.FinC.controllers;

import com.FinC.dtos.GoalDto;
import com.FinC.models.Expense;
import com.FinC.models.Goal;
import com.FinC.services.GoalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/goal")
public class GoalController {

    @Autowired
    GoalService goalService;

    @GetMapping
    public ResponseEntity<List<Goal>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(goalService.findAll());
    }

    @GetMapping("/account/{accountId}")
    public ResponseEntity<List<Goal>> findByAccount(@PathVariable(name = "id")UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(goalService.findByAccount(id));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Goal> findById(@PathVariable(name = "id") UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(goalService.findById(id));
    }
    @PostMapping
    public ResponseEntity<Goal> createGoal(@RequestBody GoalDto goalDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(goalService.createGoal(goalDto));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Goal> updateGoal(@PathVariable(name = "id")UUID id,
                                           @RequestBody GoalDto goalDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(goalService.updateGoal(goalDto,id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Goal> deleteGoal(@PathVariable(name = "id")UUID id){
        goalService.deleteGoal(id);
        return ResponseEntity.noContent().build();
    }
}
