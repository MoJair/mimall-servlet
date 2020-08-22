package com.mi.util;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MyUtils {
	//定义一些全局变量
	public String myEmail = "m1639269322@163.com";			//发件人的邮箱
	public String smtpHost = "smtp.163.com";	//邮件主机地址
	public String myPwd = "QQKDFKTAAKVJGWDL";   //这个密码是授权码
	
	/**
	 * 发送邮箱的方法
	 * @param email 收件人的邮箱地址
	 */
	public void sendMail(String email){
		//发邮件，先产生随机码
		Random r = new Random();
		String code = "";
		for(int i=0; i<6; i++){
			code += r.nextInt(10);
		}
		//存一下验证码，方便用户注册验证
		//Data.code = code;
		
		System.out.println("验证码"+code);
		try {
			//开始发送邮件
			Properties props = new Properties();
			props.setProperty("mail.transport.protocol", "smtp");   //定义传输协议
			props.setProperty("mail.smtp.host", smtpHost);   //定义传输协议
			props.setProperty("mail.smtp.auth", "true");   //定义传输协议
			//根据配置，来得到会话对象
			Session session = Session.getInstance(props);
			//通过会话对象来得到邮件对象
			MimeMessage message = new MimeMessage(session);
			//设置发件人
			message.setFrom(new InternetAddress(myEmail,"微信客户端", "UTF-8"));
			//设置收件人
			message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(email, "用户", "UTF-8"));
			//设置收件的主题
			message.setSubject("欢迎使用微信", "UTF-8");
			//设置内容
			message.setContent("您的微信注册验证码为：" + code, "text/html;charset=UTF-8");
			//设置发件时间
			message.setSentDate(new Date() ); //立即发送
			message.saveChanges();
			//开始发送邮件
			Transport transport = session.getTransport();
			transport.connect(myEmail, myPwd);
			transport.sendMessage(message, message.getAllRecipients() );
			//发送成功，关闭连接
			transport.close();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}


	}
	

	
	
	public void rsendMail(String email, String account, String pwd){
		try {
			//开始发送邮件
			Properties props = new Properties();
			props.setProperty("mail.transport.protocol", "smtp");   //定义传输协议
			props.setProperty("mail.smtp.host", smtpHost);   //定义传输协议
			props.setProperty("mail.smtp.auth", "true");   //定义传输协议
			//根据配置，来得到会话对象
			Session session = Session.getInstance(props);
			//通过会话对象来得到邮件对象
			MimeMessage message = new MimeMessage(session);
			//设置发件人
			message.setFrom(new InternetAddress(myEmail,"微信安全中心", "UTF-8"));
			//设置收件人
			message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(email, "用户", "UTF-8"));
			//设置收件的主题
			message.setSubject("微信账号密码重置提醒", "UTF-8");
			//设置内容
			message.setContent("您的微信账号为：" +account+ "密码为：" +pwd+ "温馨提示：建议您尽快登录账号并修改密码，以免信息泄露！", "text/html;charset=UTF-8");
			//设置发件时间
			message.setSentDate(new Date() ); //立即发送
			message.saveChanges();
			//开始发送邮件
			Transport transport = session.getTransport();
			transport.connect(myEmail, myPwd);
			transport.sendMessage(message, message.getAllRecipients() );
			//发送成功，关闭连接
			transport.close();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
	}

}
