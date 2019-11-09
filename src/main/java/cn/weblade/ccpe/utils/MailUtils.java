package cn.weblade.ccpe.utils;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailUtils {

    /**
     * 发送邮件的方法
     * @param to 给谁发送邮件
     * @param code  邮件激活码
     */
    public static void sendMail(String to,String code) throws MessagingException {
        //1.创建连接对象，连接到邮件服务器
        Properties props = new Properties();
        props.setProperty("mail.smtp.port","465");//端口
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");//如果使用了465，就必须用这句
        props.setProperty("mail.transport.protocol","smtp");//声明使用协议
        props.setProperty("mail.smtp.host","smtp.163.com");//确定邮箱的服务器地址
        props.setProperty("mail.smtp.auth","true");//需要请求验证


        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("chuchen15768679269@163.com","163password");
            }
        });
        //2.创建邮件对象
        Message message = new MimeMessage(session);
        //2.1设置发件人

            message.setFrom(new InternetAddress("chuchen15768679269@163.com"));

        //2.2设置收件人

            message.setRecipient(Message.RecipientType.TO,new InternetAddress(to));

        //2.3设置邮件的主题
        message.setSubject("来自鸡儿小课堂的激活邮件");

        //2.4设置邮件的正文
        message.setContent("来自鸡儿小课堂的激活邮件，激活请点击http://localhost:8080/registerCheck?code="+code
               ,"text/html;charset=UTF-8");

        //3.发送一封激活邮件
        Transport.send(message);
    }

}
