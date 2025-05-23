package com.FinC.services;

import com.FinC.dtos.ExpenseDto;
import com.FinC.models.Expense;
import com.FinC.models.Revenue;
import com.FinC.repositories.AccountRepository;
import com.FinC.repositories.ExpenseRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class ExpenseService {

    @Autowired
    ExpenseRepository expenseRepository;

    @Autowired
    AccountRepository accountRepository;

    public Expense findById(UUID id){
        return expenseRepository.findById(id).orElseThrow(()-> new RuntimeException("Cannot be found"));
    }
    public List<Expense> findAll(){
        return expenseRepository.findAll();
    }
    public List<Expense> findByAccount(UUID accountId){
        return expenseRepository.findByAccount(accountRepository.findById(accountId).orElseThrow(()-> new RuntimeException("Cannot be found")));
    }

    public List<Expense> findByDate(UUID accountId, LocalDate startDate, LocalDate endDate){
        var account = accountRepository.findById(accountId).orElseThrow(()-> new RuntimeException("Cannot be found"));
        return expenseRepository.findByAccountAndDateBetween(account, startDate, endDate);
    }

    public Expense createExpense(ExpenseDto expenseDto){
        var expense = new Expense();
        BeanUtils.copyProperties(expenseDto,expense);
        expense.setAccount(accountRepository.findById(expenseDto.accountId()).orElseThrow(()-> new RuntimeException("Cannot be found")));
        return expenseRepository.save(expense);
    }
    public Expense updateExpense(ExpenseDto expenseDto,UUID id){
        var expense = findById(id);
        BeanUtils.copyProperties(expenseDto,expense);
        return expenseRepository.save(expense);
    }
    public void deleteExpense(UUID id){
        var expense = findById(id);
        expenseRepository.delete(expense);
    }

}
