package com.example.entity;

import java.sql.Date;

import org.springframework.stereotype.Repository;

@Repository
public class Spending {
	private Integer id;
	private String userId;
	private Integer category;
	private Integer expense;
	private Date date;
	private String memo;
	private String categoryName;
	private Integer year;
	private Integer month;
	private Integer sumAmount;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Integer getCategory() {
		return category;
	}
	public void setCategory(Integer category) {
		this.category = category;
	}
	public Integer getExpense() {
		return expense;
	}
	public void setExpense(Integer expense) {
		this.expense = expense;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public Integer getMonth() {
		return month;
	}
	public void setMonth(Integer month) {
		this.month = month;
	}
	public Integer getSumAmount() {
		return sumAmount;
	}
	public void setSumAmount(Integer sumAmount) {
		this.sumAmount = sumAmount;
	}
}