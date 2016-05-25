package com.hyc.Actions;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import com.hyc.Dao.UserDao;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.hyc.Mail.*;

public class ForgotPasswordAction extends ActionSupport{
	
	private static final long serialVersionUID = 1L;
	
	private Map<String, Object> data;
	
	private HttpServletRequest request;
	HttpSession session;

	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}
	
	public void sendMail(String username, String email) {
		VelocityEngine ve = new VelocityEngine();
		ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
		ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
		 
		ve.init();
		 
		Template template = ve.getTemplate("template/emailTemplate.vm", "gbk");
		VelocityContext ctx = new VelocityContext();
		
		// 生成六位随机字符串
		String chars = "0123456789";  
	    String randomNum ="";
	    
		char[] rands = new char[6];  
		
	    for (int i = 0; i < 6; i++) { 
	    	int rand = (int) (Math.random() * 10);  
	        rands[i] = chars.charAt(rand);  
			randomNum += rands[i];
	    }  
	    
		session.setAttribute("resetcode", randomNum);
		ctx.put("title", "BookStore");
		ctx.put("username", username);
		ctx.put("randomNum", randomNum);
		 
		StringWriter sw = new StringWriter();
		 
		template.merge(ctx, sw);
		 
		System.out.println(sw.toString());
	 
		
		//这个类主要是设置邮件   
		MailSenderInfo mailInfo = new MailSenderInfo();    
		mailInfo.setMailServerHost("smtp.qq.com");    
		mailInfo.setMailServerPort("587");    
		mailInfo.setValidate(true);    
		mailInfo.setUserName("619602456@qq.com");
		
		// 开通smtp等服务时，设置你的邮箱授权码
		// 此时输入的为邮箱的授权码，而不是你的邮箱密码
		mailInfo.setPassword("waoskhknehznbegj"); 
      	mailInfo.setFromAddress("619602456@qq.com");    
      	mailInfo.setToAddress(email);    
      	mailInfo.setSubject("BookStore 登录信息找回");    
      	mailInfo.setContent(sw.toString());    
      	//这个类主要来发送邮件   
      	SimpleMailSender sms = new SimpleMailSender();   
      	//sms.sendTextMail(mailInfo);//发送文体格式    
      	
      	
		sms.sendHtmlMail(mailInfo);//发送html格式
	}
	
	public String execute() {
		data = new HashMap<String, Object>();
		request = (HttpServletRequest)ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		session = request.getSession();
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
			data.put("text", "重置码以发送至注册邮箱，请查收");
			String username = userDao.getByEmail(email).getUsername();
			sendMail(username, email);
		}
		return SUCCESS;
	}
}
