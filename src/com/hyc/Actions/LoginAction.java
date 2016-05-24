package com.hyc.Actions;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import com.hyc.Dao.UserDao;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport {
	
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
		String password = request.getParameter("password");
		UserDao userDao = new UserDao();
		if(userDao.usenameandpasswordIsExist(username, password)) {
			data.put("status", "success");
			session.setAttribute("user", username);
		}
		else {
			data.put("status", "fault");
		}
		return SUCCESS;
	}
}
