package com.web.archetype.bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import com.web.archetype.service.AuthenticationService;

@ManagedBean
@RequestScoped
@Controller
public class LoginBean {
	
	@Autowired
	@Qualifier("authenticationService")
	private AuthenticationService authenticationService;
	
	private String username;
	
	private String password;
	
	public String login() {
		boolean success = authenticationService.login(username, password);
		if (!success) {
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Login ou senha inválidos");
			FacesContext.getCurrentInstance().addMessage(null, facesMessage);
			return null;
		}
		return "user/home";
	}
	
	public String logout() {
		authenticationService.logout();
		return "login";
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
