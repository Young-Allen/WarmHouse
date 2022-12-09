package me.spring.utils;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

public class MailUtils {
	 
	  /**
	   * 发送邮件
	   * @param to      收件人
	   * @param subject   主题
	   * @param content   内容
	   * @throws Exception
	   */
	  public static void sendMsg(String to ,String subject ,String content) throws Exception{
	    // 创建属性文件
	    Properties props = new Properties();
	    // 设置主机地址   smtp.qq.com    smtp.sina.com  使用的本地易邮服务器
	    props.setProperty("smtp.qq.com", "smtp.163.com");
	    // 认证，提供用户名和密码进行校验
	    props.setProperty("mail.smtp.auth", "true");

	    //2.产生一个用于邮件发送的Session对象，连接服务器主机
	    Session session = Session.getInstance(props);
	    
	    //3.产生一个邮件的消息对象
	    MimeMessage message = new MimeMessage(session);
	    
	    //4.设置消息的发送者
	    Address fromAddr = new InternetAddress("wxd020513@163.com");
	    message.setFrom(fromAddr);
	    
	    //5.设置消息的接收者
	    Address toAddr = new InternetAddress(to);
	    //TO 直接发送  CC抄送    BCC密送
	    message.setRecipient(RecipientType.TO, toAddr);
	    
	    //6.设置主题
	    message.setSubject(subject);
	    //7.设置正文
	    message.setText(content);
	    
	    //8.准备发送，得到火箭
	    Transport transport = session.getTransport("smtp");
	    //9.设置火箭的发射目标 
	    /**
	     * 1. 主机地址
	     * 2. 发件人的邮箱帐号
	     * 3. 账号对应的密码
	     */
	    transport.connect("smtp.163.com", "wxd020513@163.com", "MXIJIQNJKMEADANT");
	    //10.发送
	    transport.sendMessage(message, message.getAllRecipients());
	    //11.关闭
	    transport.close();
	  }
}
