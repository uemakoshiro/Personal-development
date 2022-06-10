package com.example.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.dao.SpendingDao;
import com.example.entity.Spending;

@Repository
public class PgSpendingDao implements SpendingDao {

	private static final String INSERT = "INSERT INTO spending(user_id, category, expense, date, memo) VALUES(:user, :category, :expense, :date, :memo)";
	private static final String SELECT = "SELECT s.id, s.user_id, s.category, s.expense, s.date, s.memo, c.name AS category_name FROM spending s JOIN categories c ON s.category = c.id WHERE s.user_id = :userId ";
	private static final String SELECT_FLAG = "SELECT extract(year from date) AS year, extract(month from date) AS month, count(*) FROM spending WHERE user_id = :userId GROUP BY extract(month from date), extract(year from date) ORDER BY year, extract(month from date) DESC";
	private static final String SELECT_ID = "SELECT s.id, s.user_id, s.category, s.expense, s.date, s.memo, c.name AS category_name FROM spending s JOIN categories c ON s.category = c.id WHERE s.id = :id";
	private static final String DELETE = "DELETE FROM spending WHERE id = :id";
	private static final String UPDATE = "UPDATE spending SET date = :date, category = :category, expense = :expense, memo = :memo WHERE id = :id";
	private static final String GRAPH = "SELECT extract(year from date) AS year, extract(month from date) AS month, c.name category_name, sum(s.expense) sum_amount FROM spending s JOIN categories c ON s.category = c.id WHERE s.user_id = :userId AND extract(month from date) = :month GROUP BY extract(month from date), extract(year from date), c.name ORDER BY sum(s.expense) DESC";
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public int Insert(Spending sp){
    	int result = 0;
    	String sql = INSERT;
    	MapSqlParameterSource param = new MapSqlParameterSource();
    	param.addValue("user", sp.getUserId());
    	param.addValue("category", sp.getCategory());
    	param.addValue("expense", sp.getExpense());
    	param.addValue("date", sp.getDate());
    	param.addValue("memo", sp.getMemo());

    	try {
    		result = jdbcTemplate.update(sql, param);
    	}catch(RuntimeException e){
    		e.printStackTrace();
    	}
        return result;
    }
    
    public ArrayList<Spending> SelectDataMonth(String userId){
    	String sql = SELECT;
    	sql += "AND extract(month from s.date) = extract(month from current_timestamp) ORDER BY s.date";
    	MapSqlParameterSource param = new MapSqlParameterSource();
    	param.addValue("userId", userId);
    	List<Spending> spendingList = jdbcTemplate.query(sql, param, new BeanPropertyRowMapper<Spending>(Spending.class));
        return spendingList.isEmpty() ? null : (ArrayList<Spending>) spendingList;
    
    }
    
    public ArrayList<Spending> SelectDataMonth(String userId, Integer month){
    	String sql = SELECT;
    	sql += "AND extract(month from s.date) = :month ORDER BY s.date";
    	MapSqlParameterSource param = new MapSqlParameterSource();
    	param.addValue("userId", userId);
    	param.addValue("month", month);
    	List<Spending> spendingList = jdbcTemplate.query(sql, param, new BeanPropertyRowMapper<Spending>(Spending.class));

        return spendingList.isEmpty() ? null : (ArrayList<Spending>) spendingList;
    
    }
    
    public ArrayList<Spending> SelectYearMonth(String userId){
    	String sql = SELECT_FLAG;
    	MapSqlParameterSource param = new MapSqlParameterSource();
    	param.addValue("userId", userId);
    	List<Spending> yearMonthList = jdbcTemplate.query(sql, param, new BeanPropertyRowMapper<Spending>(Spending.class));

        return yearMonthList.isEmpty() ? null : (ArrayList<Spending>) yearMonthList;
    }
    
    public Spending SelectId(Integer id) {
    	String sql = SELECT_ID;
    	MapSqlParameterSource param = new MapSqlParameterSource();
    	param.addValue("id", id);
    	List<Spending> spending = jdbcTemplate.query(sql, param, new BeanPropertyRowMapper<Spending>(Spending.class));

        return spending.isEmpty() ? null : ((ArrayList<Spending>) spending).get(0);
    }
    
    public int Delete(Integer id) {
    	int result = 0;
    	String sql = DELETE;
    	MapSqlParameterSource param = new MapSqlParameterSource();
    	param.addValue("id", id);
    	try {
    		result = jdbcTemplate.update(sql, param);
    	}catch(RuntimeException e){
    		e.printStackTrace();
    	}
        return result;
    }
    
    public int Update(Spending sp) {
    	int result = 0;
    	String sql = UPDATE;
    	MapSqlParameterSource param = new MapSqlParameterSource();
    	param.addValue("date", sp.getDate());
    	param.addValue("category", sp.getCategory());
    	param.addValue("expense", sp.getExpense());
    	param.addValue("memo", sp.getMemo());
    	param.addValue("id", sp.getId());
    	try {
    		result = jdbcTemplate.update(sql, param);
    	}catch(RuntimeException e){
    		e.printStackTrace();
    	}
    	return result;
    }
    
    public ArrayList<Spending> GetGraphData(String userId, Integer month){
    	String sql = GRAPH;
    	MapSqlParameterSource param = new MapSqlParameterSource();
    	param.addValue("userId", userId);
    	param.addValue("month", month);
    	List<Spending> spending = jdbcTemplate.query(sql, param, new BeanPropertyRowMapper<Spending>(Spending.class));

        return spending.isEmpty() ? null : (ArrayList<Spending>) spending;
    }

}
