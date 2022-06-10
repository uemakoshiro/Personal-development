package com.example.service;

import java.util.ArrayList;

import com.example.entity.Spending;

public interface SpendingService {

    public int Insert(Spending sp);
    
    public ArrayList<Spending> SelectDataMonth(String userId);
    
    public ArrayList<Spending> SelectDataMonth(String userId, Integer month);
    
    public ArrayList<Spending> SelectYearMonth(String userId);
    
    public Spending SelectId(Integer id);
    
    public int Delete(Integer id);
    
    public int Update(Spending sp);
    
    public ArrayList<Spending> GetGraphData(String userId, Integer month);
}