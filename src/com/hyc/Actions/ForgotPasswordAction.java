package com.hyc.Actions;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.hyc.Dao.UserDao;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ForgotPasswordAction extends ActionSupport{
	
	private static final long serialVersionUID = 1L;
	
	private Map<String, Object> data;

	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}
	
	public String execute() {
		data = new HashMap<String, Object>();
		HttpServletRequest request = (HttpServletRequest)ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		HttpSession session = request.getSession();
		String email = request.getParameter("email");
		String checkcode = request.getParameter("checkcode");
		UserDao userDao = new UserDao();
		if(!checkcode.equals(session.getAttribute("checkcode"))) {
			data.put("status", "fault");
			data.put("text", "请填写正确的验证码");
		}
		else if(!userDao.emailIsExist(email)) {
			data.put("status", "fault");
			data.put("text", "请填写正确的注册电子邮件地址");
		}
		else {
			data.put("status", "success");
		}
		return SUCCESS;
	}
}
