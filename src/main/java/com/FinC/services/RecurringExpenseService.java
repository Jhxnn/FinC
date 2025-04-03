package com.FinC.services;

import com.FinC.dtos.RecurringExpenseDto;
import com.FinC.models.Expense;
import com.FinC.models.RecurringExpense;
import com.FinC.models.Revenue;
import com.FinC.repositories.RecurringExpenseRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class RecurringExpenseService {

    @Autowired
    RecurringExpenseRepository recurringExpenseRepository;

    @Autowired
    AccountService accountService;

    @Autowired
    ApiAsaasService apiAsaasService;

    public RecurringExpense findById(UUID id){
        return recurringExpenseRepository.findById(id).orElseThrow(()-> new RuntimeException("Cannot be found"));
    }

    public List<RecurringExpense> findByAccount(UUID accountId){
        return recurringExpenseRepository.findByAccount(accountService.findById(accountId));
    }
    public List<RecurringExpense> findByDate(UUID accountId, LocalDate startDate, LocalDate endDate){
        var account = accountService.findById(accountId);
        return recurringExpenseRepository.findByAccountAndDateBetween(account, startDate, endDate);
    }

    public List<RecurringExpense> findAll(){
        return recurringExpenseRepository.findAll();
    }
    public RecurringExpense createRecurringExpense(RecurringExpenseDto recurringExpenseDto) throws IOException {
        var recurringExpense = new RecurringExpense();
        BeanUtils.copyProperties(recurringExpenseDto,recurringExpense);
        recurringExpense.setPix(apiAsaasService.gerarQrCode(recurringExpenseDto.pix(),recurringExpenseDto.value()));
        recurringExpense.setAccount(accountService.findById(recurringExpenseDto.accountId()));
        return recurringExpenseRepository.save(recurringExpense);
    }
    public RecurringExpense updateRecurringExpense(RecurringExpenseDto recurringExpenseDto,UUID id){
        var recurringExpense = findById(id);
        BeanUtils.copyProperties(recurringExpenseDto,recurringExpense);
        return recurringExpenseRepository.save(recurringExpense);
    }
    public void deleteRecurringExpense(UUID id){
        var recurringExpense = findById(id);
        recurringExpenseRepository.delete(recurringExpense);
    }

}
