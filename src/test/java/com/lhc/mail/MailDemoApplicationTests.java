package com.lhc.mail;

import com.lhc.mail.service.MailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class MailDemoApplicationTests {

    @Autowired
    private MailService mailService;
    // 获取JavaMailSender bean
    @Autowired
    private JavaMailSender javaMailSender;
    @Value("${mail.from}")
    private String from;

    @Value("${mail.to}")
    private String sendTo;
    @Autowired
    private TemplateEngine templateEngine;
    @Test
    public void contextLoads() {
    }


    @Test
    public void testSend() {
        String to = "yimcarson@qq.com";
        mailService.sendSimpleMail("1013217387@qq.com","1481784416@qq.com","这是一封测试邮件！","这是一封测试邮件！");
    }




    // 获取配置文件的username
    @Value("${spring.mail.username}")
    private String username;

    /**
     * 一个简单的邮件发送
     */
    @Test
    public void sendSimpleMail1(){
        SimpleMailMessage message = new SimpleMailMessage();
        // 设定邮件参数
        message.setFrom(username); //发送者
        message.setTo("1481784416@qq.com"); //接受者
        message.setSubject("主题:邮件"); //主题
        message.setText("邮件内容"); //邮件内容

        // 发送邮件
        javaMailSender.send(message);
    }

    /**
     * 文本邮件
     */
    public void sendSimpleMail(){
        mailService.sendSimpleMail(from,sendTo,"这是一封测试邮件！","这是一封测试邮件！");
    }

    /**
     * 网页邮件
     * @throws Exception
     */
    @Test
    public void sendHtmlMail() throws Exception {
        String content="<html>\n" +
                "<body>\n" +
                "    <h3>hello world ! 这是一封html邮件!</h3>\n" +
                "</body>\n" +
                "</html>";
        mailService.sendHtmlMail(from,sendTo,"test simple mail",content);
    }

    /**
     * 带附件的邮件
     */
    @Test
    public void sendAttachmentsMail() {
        String filePath="C:\\Users\\lhc\\Desktop\\测试附件.sql";
        mailService.sendAttachmentsMail(from,sendTo, "主题：带附件的邮件", "有附件，请查收！", filePath);
    }

    /**
     * 带图片的邮件
     */
    @Test
    public void sendInlineResourceMail() {
        String rscId = "neo006";
        String content="<html><body>这是有图片的邮件：<img src=\'cid:" + rscId + "\' ></body></html>";
        String imgPath = "C:\\Users\\lhc\\Desktop\\企鹅.jpg";
        //嵌入式资源使用Content-ID(上例中是rscId)来插入到mime信件中去。你加入文本和资源的顺序是非常重要的。首先，你加入文本，随后是资源。如果顺序弄反了，它将无法正常运作！
        mailService.sendInlineResourceMail(from,sendTo,"主题：这是有图片的邮件", content, imgPath, rscId);
    }

    /**
     * 模板邮件
     */
    public void sendTemplateMail() {
        //创建邮件正文
        Context context = new Context();
        context.setVariable("id", "006");
        String emailContent = templateEngine.process("MailTemplate", context);
        mailService.sendHtmlMail(from,sendTo,"主题：这是模板邮件",emailContent);
    }

    /**
     * 模板邮件
     */
    @Test
    public void sendTemplateMail2() {
        //创建邮件正文
        String[] filePath = new String[]{"C:\\Users\\lhc\\Desktop\\测试附件.sql"};

        Map<String, Object> valueMap = new HashMap<>();
        valueMap.put("title", "邮件标题");
        valueMap.put("content", "邮件内容");
        valueMap.put("src","C:\\Users\\lhc\\Desktop\\企鹅.jpg");
        valueMap.put("filePathList", filePath);
        mailService.sendTemplateMail(from,sendTo,valueMap);
    }


}
