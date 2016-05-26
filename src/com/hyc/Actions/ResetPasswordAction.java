package com.hyc.Actions;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.hyc.Dao.UserDao;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ResetPasswordAction extends ActionSupport{
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
		String resetcode = request.getParameter("resetcode");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String confirmpassword = request.getParameter("confirmpassword");
		UserDao userDao = new UserDao();
		System.out.println("resetcode: " + resetcode);
		System.out.println("email: " + email);
		System.out.println("password: " + password);
		System.out.println("confirmpassword: " + confirmpassword);
		if(password.equals("") || !password.equals(confirmpassword)) {
			data.put("status", "fault");
			data.put("text", "�������벻һ��");
		}
		else if(!resetcode.equals((String)session.getAttribute("resetcode"))) {
			data.put("status", "fault");
			data.put("text", "��������ȷ��������");
		}
		else {
			System.out.println("4resetcode: " + resetcode + "password: " + password);
			data.put("status", "success");
			data.put("text", "�ɹ���������");
			userDao.resetPassword(password, userDao.getByEmail(email));
		}
		return SUCCESS;
	}
}
