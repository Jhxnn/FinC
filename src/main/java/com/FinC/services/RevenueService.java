package com.FinC.services;

import com.FinC.dtos.RevenueDto;
import com.FinC.models.Expense;
import com.FinC.models.Revenue;
import com.FinC.repositories.RevenueRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RevenueService {

    @Autowired
    RevenueRepository revenueRepository;

    @Autowired
    AccountService accountService;

    public Revenue findById(UUID id){
        return revenueRepository.findById(id).orElseThrow(()-> new RuntimeException("Cannot be found"));
    }
    public List<Revenue> findAll(){
        return revenueRepository.findAll();
    }

    public List<Revenue> findByAccount(UUID accountId){
        return revenueRepository.findByAccount(accountService.findById(accountId));
    }
    public Revenue createRevenue(RevenueDto revenueDto){
        var revenue = new Revenue();
        BeanUtils.copyProperties(revenueDto,revenue);
        return revenueRepository.save(revenue);
    }
    public Revenue updateRevenue(RevenueDto revenueDto,UUID id){
        var revenue = findById(id);
        BeanUtils.copyProperties(revenueDto,revenue);
        return revenueRepository.save(revenue);
    }
    public void deleteRevenue(UUID id){
        var revenue = findById(id);
        revenueRepository.delete(revenue);
    }

}
