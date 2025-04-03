package com.FinC.services;

import com.FinC.dtos.RevenueDto;
import com.FinC.models.Account;
import com.FinC.models.Expense;
import com.FinC.models.Revenue;
import com.FinC.repositories.AccountRepository;
import com.FinC.repositories.RevenueRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class RevenueService {

    @Autowired
    RevenueRepository revenueRepository;

    @Autowired
    AccountRepository accountRepository;

    public Revenue findById(UUID id){
        return revenueRepository.findById(id).orElseThrow(()-> new RuntimeException("Cannot be found"));
    }
    public List<Revenue> findAll(){
        return revenueRepository.findAll();
    }

    public List<Revenue> findByAccount(UUID accountId){
        return revenueRepository.findByAccount(accountRepository.findById(accountId).orElseThrow(()-> new RuntimeException("Cannot be found")));
    }
    public Revenue createRevenue(RevenueDto revenueDto){
        var revenue = new Revenue();
        BeanUtils.copyProperties(revenueDto,revenue);
        revenue.setAccount(accountRepository.findById(revenueDto.accountId()).orElseThrow(()-> new RuntimeException("Cannot be found")));
        return revenueRepository.save(revenue);
    }
    public List<Revenue> findByDate(UUID accountId, LocalDate startDate, LocalDate endDate){
        var account = accountRepository.findById(accountId).orElseThrow(()-> new RuntimeException("Cannot be found"));
        return revenueRepository.findByAccountAndDateBetween(account, startDate, endDate);
    }
    public Revenue updateRevenue(RevenueDto revenueDto,UUID id){
        var revenue = findById(id);
        BeanUtils.copyProperties(revenueDto,revenue);
        revenue.setAccount(accountRepository.findById(revenueDto.accountId()).orElseThrow(()-> new RuntimeException("Cannot be found")));
        return revenueRepository.save(revenue);
    }
    public void deleteRevenue(UUID id){
        var revenue = findById(id);
        revenueRepository.delete(revenue);
    }

}
