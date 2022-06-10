package com.example.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.CategoryDao;
import com.example.entity.Category;
import com.example.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryDao categoryDao;

    public ArrayList<Category> SelectAll(String id) {
        return categoryDao.SelectAll(id);
    }
    
    public int Insert(String category, String userId) {
    	return categoryDao.Insert(category, userId);
    }
    
}

