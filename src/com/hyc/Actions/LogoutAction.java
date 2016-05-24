package com.hyc.Actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class LogoutAction extends ActionSupport{

	private static final long serialVersionUID = 1L;

	public String execute() {
		HttpServletRequest request = (HttpServletRequest)ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		HttpSession session = request.getSession();
		session.removeAttribute("user");
		return SUCCESS;
	}
}
