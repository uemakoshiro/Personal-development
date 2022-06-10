package com.example.service;

import java.util.ArrayList;

import com.example.entity.Category;

public interface CategoryService {

    public ArrayList<Category> SelectAll(String id);
    
    public int Insert(String category, String userId);
}
