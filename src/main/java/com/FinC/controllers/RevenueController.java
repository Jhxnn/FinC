package com.FinC.controllers;

import com.FinC.dtos.AccountAndDateDto;
import com.FinC.dtos.RevenueDto;
import com.FinC.models.Expense;
import com.FinC.models.Revenue;
import com.FinC.services.RevenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/revenue")
public class RevenueController {

    @Autowired
    RevenueService revenueService;

    @GetMapping
    public ResponseEntity<List<Revenue>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(revenueService.findAll());
    }

    @GetMapping("/account/{accountId}")
    public ResponseEntity<List<Revenue>> findByAccount(@PathVariable(name = "accountIdt add")UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(revenueService.findByAccount(id));
    }
    @GetMapping("/date")
    public ResponseEntity<List<Revenue>> findByDate(@RequestBody AccountAndDateDto accountAndDateDto){
        return ResponseEntity.status(HttpStatus.OK).body(revenueService.findByDate(
                accountAndDateDto.accountId(),
                accountAndDateDto.startDate()
                ,accountAndDateDto.endDate()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Revenue> findById(@PathVariable(name = "id") UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(revenueService.findById(id));
    }
    @PostMapping
    public ResponseEntity<Revenue> createRevenue(@RequestBody RevenueDto revenueDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(revenueService.createRevenue(revenueDto));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Revenue> updateRevenue(@PathVariable(name = "id")UUID id,
                                                 @RequestBody RevenueDto revenueDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(revenueService.updateRevenue(revenueDto,id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Revenue> deleteRevenue(@PathVariable(name = "id")UUID id){
        revenueService.deleteRevenue(id);
        return ResponseEntity.noContent().build();
    }
}
