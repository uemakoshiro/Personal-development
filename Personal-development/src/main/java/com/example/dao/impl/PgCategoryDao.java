package com.example.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.dao.CategoryDao;
import com.example.entity.Category;

@Repository
public class PgCategoryDao implements CategoryDao {

	private static final String SELECT_ALL = "SELECT * FROM categories WHERE user_id = :id";
	private static final String INSERT = "INSERT INTO categories(name, user_id) VALUES(:category, :userId)";
	
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public ArrayList<Category> SelectAll(String id){
    	String sql = SELECT_ALL;
    	MapSqlParameterSource param = new MapSqlParameterSource();
    	param.addValue("id", id);
    	
    	List<Category> categoryList = jdbcTemplate.query(sql, param, new BeanPropertyRowMapper<Category>(Category.class));

        return categoryList.isEmpty() ? null : (ArrayList<Category>) categoryList;
    }
    
    public int Insert(String category, String userId) {
    	int result = 0;
    	String sql = INSERT;
    	MapSqlParameterSource param = new MapSqlParameterSource();
    	param.addValue("category", category);
    	param.addValue("userId", userId);
    	try {
        	result = jdbcTemplate.update(sql, param);
    	}catch(RuntimeException e) {
    		e.printStackTrace();
    	}
    	return result;
    }
    

}

