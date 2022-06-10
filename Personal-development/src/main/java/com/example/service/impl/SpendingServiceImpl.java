package com.example.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.SpendingDao;
import com.example.entity.Spending;
import com.example.service.SpendingService;

@Service
public class SpendingServiceImpl implements SpendingService {

    @Autowired
    private SpendingDao spendingDao;

    public int Insert(Spending sp) {
        return spendingDao.Insert(sp);
    }
    
    public ArrayList<Spending> SelectDataMonth(String userId){
    	return spendingDao.SelectDataMonth(userId);
    }
    
    public ArrayList<Spending> SelectDataMonth(String userId, Integer month){
    	return spendingDao.SelectDataMonth(userId, month);
    }
    
    public ArrayList<Spending> SelectYearMonth(String userId){
    	return spendingDao.SelectYearMonth(userId);
    }
    
    public Spending SelectId(Integer id) {
    	return spendingDao.SelectId(id);
    }
    
    public int Delete(Integer id) {
    	return spendingDao.Delete(id);
    }
    
    public int Update(Spending sp) {
    	return spendingDao.Update(sp);
    }
    
    public ArrayList<Spending> GetGraphData(String userId, Integer month){
    	return spendingDao.GetGraphData(userId,month);
    }
}
