package com.lhc.mail.task;

import com.lhc.mail.service.MailService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class TestTask1 extends QuartzJobBean {

    @Autowired
    private MailService mailService;
    @Value("${mail.from}")
    private String from;

    @Value("${mail.to}")
    private String sendTo;
    @Autowired
    private TemplateEngine templateEngine;
    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("TestQuartz01----" + sdf.format(new Date()));

        //创建邮件正文
        String[] filePath = new String[]{"C:\\Users\\lhc\\Desktop\\quartz_mysql.sql"};

        Map<String, Object> valueMap = new HashMap<>();
        valueMap.put("title", "邮件标题");
        valueMap.put("content", "邮件内容");
        valueMap.put("src","C:\\Users\\lhc\\Desktop\\Picture\\企鹅.jpg");
        valueMap.put("filePathList", filePath);
        mailService.sendTemplateMail(from,sendTo,valueMap);
    }
}
