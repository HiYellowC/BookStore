package com.hyc.Hibernate;

import static org.junit.Assert.*;

import java.io.StringWriter;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.junit.Test;

import com.hyc.Dao.UserDao;
import com.hyc.Mail.MailSenderInfo;
import com.hyc.Mail.SimpleMailSender;

public class debug {

	@Test
	public void test() {
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
	    
		ctx.put("title", "BookStore");
		ctx.put("username", "hehe");
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
      	mailInfo.setToAddress("619602456@qq.com");    
      	mailInfo.setSubject("����������� �� hohoTT");    
      	mailInfo.setContent(sw.toString());    
      	//�������Ҫ�������ʼ�   
      	SimpleMailSender sms = new SimpleMailSender();   
      	//sms.sendTextMail(mailInfo);//���������ʽ    
      	
      	
		sms.sendHtmlMail(mailInfo);//����html��ʽ
	}

}
