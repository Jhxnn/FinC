package com.FinC.services;

import com.FinC.dtos.GoalDto;
import com.FinC.models.Goal;
import com.FinC.repositories.GoalRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class GoalService {

    @Autowired
    GoalRepository goalRepository;

    public Goal findById(UUID id){
        return goalRepository.findById(id).orElseThrow(()-> new RuntimeException("Cannot be found"));
    }
    public List<Goal> findAll(){
        return goalRepository.findAll();
    }
    public Goal createGoal(GoalDto goalDto){
        var goal = new Goal();
        BeanUtils.copyProperties(goalDto,goal);
        return goalRepository.save(goal);
    }
    public Goal updateGoal(GoalDto goalDto,UUID id){
        var goal = findById(id);
        BeanUtils.copyProperties(goalDto,goal);
        return goalRepository.save(goal);
    }
    public void deleteGoal(UUID id){
        var goal = findById(id);
        goalRepository.delete(goal);
    }

}