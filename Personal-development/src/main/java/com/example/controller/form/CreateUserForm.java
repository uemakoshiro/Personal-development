package com.example.controller.form;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotBlank;

public class CreateUserForm extends UserForm {
	@NotBlank
	private String name;
	@NotBlank
	private String passwordConfirmation;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPasswordConfirmation() {
		return passwordConfirmation;
	}
	public void setPasswordConfirmation(String passwordConfirmation) {
		this.passwordConfirmation = passwordConfirmation;
	}
	
	@AssertTrue(message = "Passwordの値が一致しません")
	public boolean isPasswordValid() {
		if(pass == null || pass.isEmpty()) {
			return true;
		}
		
		return pass.equals(passwordConfirmation);
	}
}