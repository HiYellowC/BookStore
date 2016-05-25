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
		
		// ������λ����ַ���
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
	 
		
		//�������Ҫ�������ʼ�   
		MailSenderInfo mailInfo = new MailSenderInfo();    
		mailInfo.setMailServerHost("smtp.qq.com");    
		mailInfo.setMailServerPort("587");    
		mailInfo.setValidate(true);    
		mailInfo.setUserName("619602456@qq.com");
		
		// ��ͨsmtp�ȷ���ʱ���������������Ȩ��
		// ��ʱ�����Ϊ�������Ȩ�룬�����������������
		mailInfo.setPassword("waoskhknehznbegj"); 
      	mailInfo.setFromAddress("619602456@qq.com");    
      	mailInfo.setToAddress(email);    
      	mailInfo.setSubject("BookStore ��¼��Ϣ�һ�");    
      	mailInfo.setContent(sw.toString());    
      	//�������Ҫ�������ʼ�   
      	SimpleMailSender sms = new SimpleMailSender();   
      	//sms.sendTextMail(mailInfo);//���������ʽ    
      	
      	
		sms.sendHtmlMail(mailInfo);//����html��ʽ
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
			data.put("text", "����д��ȷ����֤��");
		}
		else if(!userDao.emailIsExist(email)) {
			data.put("status", "fault");
			data.put("text", "����д��ȷ��ע������ʼ���ַ");
		}
		else {
			data.put("status", "success");
			data.put("text", "�������Է�����ע�����䣬�����");
			String username = userDao.getByEmail(email).getUsername();
			sendMail(username, email);
		}
		return SUCCESS;
	}
}
