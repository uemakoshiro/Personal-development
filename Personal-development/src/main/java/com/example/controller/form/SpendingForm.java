package com.example.controller.form;

import java.sql.Date;

public class SpendingForm {
	private Integer id;
	private String userId;
	private Integer category;
	private Integer expense;
	private Date date;
	private String memo;
	
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
}