package com.lhc.mail.service;

import java.util.Map;

public interface MailService {
    public void sendSimpleMail(String from,String to, String subject, String content);
    public void sendHtmlMail(String from,String to, String subject, String content);
    public void sendAttachmentsMail(String from,String to, String subject, String content, String filePath);
    public void sendInlineResourceMail(String from,String to, String subject, String content, String rscPath, String rscId);
    public void sendTemplateMail(String from, String to, Map<String, Object> valueMap);
}
