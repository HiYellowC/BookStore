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
		if(password.equals("") || confirmpassword.equals("") || !password.matches(confirmpassword)) {
			data.put("status", "fault");
			data.put("text", "两个密码不一致");
		}
		else if(resetcode.equals("") || !resetcode.matches((String)session.getAttribute("resetcode"))) {
			data.put("status", "fault");
			data.put("text", "请输入正确的重置码");
		}
		else {
			data.put("status", "success");
			data.put("text", "成功重置密码");
			userDao.resetPassword(password, userDao.getByEmail(email));
		}
		return SUCCESS;
	}
}
