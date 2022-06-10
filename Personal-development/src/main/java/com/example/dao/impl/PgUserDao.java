package com.example.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.dao.UserDao;
import com.example.entity.User;

@Repository
public class PgUserDao implements UserDao {

	private static final String SELECT_ID_PASS = "SELECT login_id, name, role FROM users WHERE login_id = :id AND password = :pass";
	private static final String CREATE_USER = "INSERT INTO users(login_id, password, name, role) VALUES(:loginId, :pass, :name, 2)";
	
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    
    public User loginCheck(String id, String pass) {
    	String sql = SELECT_ID_PASS;
    	MapSqlParameterSource param = new MapSqlParameterSource();
    	param.addValue("id", id);
        param.addValue("pass", pass);
        
        List<User> userInfo = jdbcTemplate.query(sql, param, new BeanPropertyRowMapper<User>(User.class));

        return userInfo.isEmpty() ? null : userInfo.get(0);
    
    }
    
    public int CreateUser(String name, String loginId, String pass) {
    	int result = 0;
    	String sql = CREATE_USER;
    	MapSqlParameterSource param = new MapSqlParameterSource();
    	param.addValue("loginId", loginId);
    	param.addValue("pass", pass);
    	param.addValue("name", name);
    	try {
    		result = jdbcTemplate.update(sql, param);
    	}catch(RuntimeException e) {
    		e.printStackTrace();
    	}
    	
    	return result;
    }

}

