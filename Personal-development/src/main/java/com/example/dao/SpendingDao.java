package com.example.dao;

import java.util.ArrayList;

import com.example.entity.Spending;

public interface SpendingDao {
	
	public int Insert(Spending sp);
	
	//今月のデータを取得
	public ArrayList<Spending> SelectDataMonth(String userId);
	
	//指定された月のデータを取得
	public ArrayList<Spending> SelectDataMonth(String userId, Integer month);
	
	public ArrayList<Spending> SelectYearMonth(String userId);
	
	public Spending SelectId(Integer id);
	
	public int Delete(Integer id);
	
	public int Update(Spending sp);
	
	public ArrayList<Spending> GetGraphData(String userId, Integer month);
}