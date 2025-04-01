package com.FinC.services;

import com.FinC.dtos.RecurringExpenseDto;
import com.FinC.models.RecurringExpense;
import com.FinC.repositories.RecurringExpenseRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class RecurringExpenseService {

    @Autowired
    RecurringExpenseRepository recurringExpenseRepository;

    @Autowired
    ApiAsaasService apiAsaasService;

    public RecurringExpense findById(UUID id){
        return recurringExpenseRepository.findById(id).orElseThrow(()-> new RuntimeException("Cannot be found"));
    }
    public List<RecurringExpense> findAll(){
        return recurringExpenseRepository.findAll();
    }
    public RecurringExpense createRecurringExpense(RecurringExpenseDto recurringExpenseDto) throws IOException {
        var recurringExpense = new RecurringExpense();
        BeanUtils.copyProperties(recurringExpenseDto,recurringExpense);
        recurringExpense.setPix(apiAsaasService.gerarQrCode(recurringExpenseDto.pix(),recurringExpenseDto.value()));
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
