package com.FinC.controllers;

import com.FinC.dtos.AccountAndDateDto;
import com.FinC.dtos.AccountDto;
import com.FinC.models.Account;
import com.FinC.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/account")
public class AccountController {


    @Autowired
    AccountService accountService;


    @GetMapping
    public ResponseEntity<List<Account>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(accountService.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Account> findById(@PathVariable(name = "id") UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(accountService.findById(id));
    }

    @GetMapping("/pdf")
    public ResponseEntity<byte[]> pdf(@RequestBody AccountAndDateDto accountAndDateDto){
        byte[] pdfBytes = accountService.pdf(accountAndDateDto);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=relatorio.pdf");
        headers.add(HttpHeaders.CONTENT_TYPE, "application/pdf");
        return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestBody AccountDto accountDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(accountService.createAccount(accountDto));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Account> updateAccount(@PathVariable(name = "id")UUID id,
                                                 @RequestBody AccountDto accountDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(accountService.updateAccount(accountDto,id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Account> deleteAccount(@PathVariable(name = "id")UUID id){
        accountService.deleteAccount(id);
        return ResponseEntity.noContent().build();
    }
}
