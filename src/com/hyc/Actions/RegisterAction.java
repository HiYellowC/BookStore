package com.hyc.Actions;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.hyc.Dao.UserDao;
import com.hyc.Hibernate.User;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class RegisterAction extends ActionSupport{

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
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String passwordconfirm = request.getParameter("passwordconfirm");
		String checkcode = request.getParameter("checkcode").toLowerCase();
//		System.out.println("username is " + username);
//		System.out.println("email is " + email);
//		System.out.println("password is " + password);
//		System.out.println("passwordconfirm is " + passwordconfirm);
//		System.out.println("checkcode is " + checkcode);
//		System.out.println("nowcheckcode is " + session.getAttribute("checkcode"));
		UserDao userDao = new UserDao();
		if(username.length() < 3 || username.length() > 16) {
			data.put("status", "fault");
			data.put("text", "���û��������Ϲ淶");
		}
		else if(userDao.usernameIsExist(username)) {
			data.put("status", "fault");
			data.put("text", "���û����ѱ�ע��");
		}
		else if(!email.matches("^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+\\.[a-zA-Z0-9_-]+$")) {
			data.put("status", "fault");
			data.put("text", "����д��ȷ������");
		}
		else if(userDao.emailIsExist(email)) {
			data.put("status", "fault");
			data.put("text", "�������ѱ�ע��");
		}
		else if(!password.equals(passwordconfirm)) {
			data.put("status", "fault");
			data.put("text", "�������벻һ��");
		}
		else if(!checkcode.equals(session.getAttribute("checkcode"))) {
			data.put("status", "fault");
			data.put("text", "����д��ȷ����֤��");
		}
		else {
			data.put("status", "success");
			data.put("text", "ע��ɹ�");
			User user = new User(username, passwordconfirm, email);
			userDao.save(user);
		}
		return SUCCESS;
	}
}
