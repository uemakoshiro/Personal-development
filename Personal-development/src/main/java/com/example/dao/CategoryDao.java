package com.example.dao;

import java.util.ArrayList;

import com.example.entity.Category;

public interface CategoryDao {
	
	public ArrayList<Category> SelectAll(String id);
	
	public int Insert(String category, String userId);
}