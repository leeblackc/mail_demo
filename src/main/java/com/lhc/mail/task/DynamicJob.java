package com.lhc.mail.task;

import com.lhc.mail.service.MailService;
import com.lhc.mail.uitl.StringUtils;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@DisallowConcurrentExecution
public class DynamicJob implements Job {

    @Autowired
    private MailService mailService;
    @Value("${mail.from}")
    private String from;

    @Value("${mail.to}")
    private String sendTo;
    @Autowired
    private TemplateEngine templateEngine;
    private Logger logger = LoggerFactory.getLogger(DynamicJob.class);
    /**
     * 核心方法,Quartz Job真正的执行逻辑.
     * @param executorContext executorContext JobExecutionContext中封装有Quartz运行所需要的所有信息
     * @throws JobExecutionException execute()方法只允许抛出JobExecutionException异常
     */
    @Override
    public void execute(JobExecutionContext executorContext) throws JobExecutionException {
        //JobDetail中的JobDataMap是共用的,从getMergedJobDataMap获取的JobDataMap是全新的对象
        JobDataMap map = executorContext.getMergedJobDataMap();
        String jarPath = map.getString("jarPath");
        String parameter = map.getString("parameter");
        String vmParam = map.getString("vmParam");
        logger.info("Running Job name : {} ", map.getString("name"));
        logger.info("Running Job description : " + map.getString("JobDescription"));
        logger.info("Running Job group: {} ", map.getString("group"));
        logger.info("Running Job cron : " + map.getString("cronExpression"));
        logger.info("Running Job jar path : {} ", jarPath);
        logger.info("Running Job parameter : {} ", parameter);
        logger.info("Running Job vmParam : {} ", vmParam);
        long startTime = System.currentTimeMillis();
        /*if (!StringUtils.getStringUtil.isEmpty(jarPath)) {
            File jar = new File(jarPath);
            if (jar.exists()) {
                ProcessBuilder processBuilder = new ProcessBuilder();
                processBuilder.directory(jar.getParentFile());
                List<String> commands = new ArrayList<>();
                commands.add("java");
                if (!StringUtils.getStringUtil.isEmpty(vmParam)) {
                    commands.add(vmParam);
                }
                commands.add("-jar");
                commands.add(jarPath);
                if (!StringUtils.getStringUtil.isEmpty(parameter)) {
                    commands.add(parameter);
                }
                processBuilder.command(commands);
                logger.info("Running Job details as follows >>>>>>>>>>>>>>>>>>>>: ");
                logger.info("Running Job commands : {}  ", StringUtils.getStringUtil.getListString(commands));
                try {
                    Process process = processBuilder.start();
                    logProcess(process.getInputStream(), process.getErrorStream());
                } catch (IOException e) {
                    throw new JobExecutionException(e);
                }
            } else {
                throw new JobExecutionException("Job Jar not found >>  " + jarPath);
            }
        }*/
        if("sendEmail".equals(parameter)){
            String[] filePath = new String[]{"C:\\Users\\lhc\\Desktop\\quartz_mysql.sql"};

            Map<String, Object> valueMap = new HashMap<>();
            valueMap.put("title", "邮件标题");
            valueMap.put("content", "邮件内容");
            valueMap.put("src","C:\\Users\\lhc\\Desktop\\Picture\\企鹅.jpg");
            valueMap.put("filePathList", filePath);
            mailService.sendTemplateMail(from,sendTo,valueMap);
        }
        long endTime = System.currentTimeMillis();
        logger.info(">>>>>>>>>>>>> Running Job has been completed , cost time :  " + (endTime - startTime) + "ms\n");
    }
    //打印Job执行内容的日志
    private void logProcess(InputStream inputStream, InputStream errorStream) throws IOException {
        String inputLine;
        String errorLine;
        BufferedReader inputReader = new BufferedReader(new InputStreamReader(inputStream));
        BufferedReader errorReader = new BufferedReader(new InputStreamReader(errorStream));
        while ((inputLine = inputReader.readLine()) != null) {
            logger.info(inputLine);
        }
        while ((errorLine = errorReader.readLine()) != null) {
            logger.error(errorLine);
        }
    }
}
