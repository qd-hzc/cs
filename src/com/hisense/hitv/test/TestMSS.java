package com.hisense.hitv.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.hisense.hitv.mss.entity.MailVO;
import com.hisense.hitv.mss.service.MailService;

/**
 * @author wangheping
 */
public class TestMSS {

    String mailContent =
        "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD"
            + "/xhtml1-transitional.dtd\"><html><head><meta http-equiv=\"Content-Type\" content=\"text/html;"
            + " charset=utf-8\"></meta></head><body><div><p>这是邮件内容</p></div></body></html>";
    // 邮件服务Servlet地址
    String url = "http://127.0.0.1:8080/cs/mailservice/sendmail";
    String mailSender = "海信大电视，大大的电视";
    String mailServierHost = "mail.hisense.com";
    String mailSubject = "测试邮件";
    String hostMailAddress = "wangheping@hisense.com";
    String hostMailLoginName = "wangheping";
    String hostMailPin =
        "EB84A0D70D8BB5131019A21C535D0C7C618744BC083FCBFF618744BC083FCBFF618744BC083FCBFF618744BC083FCBFF618744BC083FCBFF618744BC083FCBFF";
    String mailReceiver = "wangheping@hisense.com";
    String ccMailReceiver = "34149578@qq.com";
    String attacheMent = "d:\\新建 文本文档.txt";
    int isSmtpAuth = 1;
    int isDefaultMailBox = 1;

    public TestMSS() {
        // 收件人
        List<String> mailTo = new ArrayList<String>();
        // 附件内容都在此LIST内
        mailTo.add(mailReceiver);
        MailVO mail = new MailVO();
        mail.setMailContent(mailContent);
        mail.setMailTo(mailTo);
        mail.setMailSender("王小海");
        mail.setMailSendTime((new Date()).getTime());
        mail.setMailServerHost(mailServierHost);
        mail.setMailSubject(mailSubject);
        mail.setIsDefaultMailBox(1);
        mail.setMailUrl(url);
        mail.setIsSmtpAuth(isSmtpAuth);
        MailService req = new MailService(mail);
        String response = req.sendMail();
        System.out.println("0,一个正常的收件人，结果：" + response);
    }

    // 1个收件人
    public static void main(String args[]) {
        new TestMSS();
    }
    // // 1个收件人
    // @Test
    // public void testOneReceiver() {
    // // 收件人
    // List<String> mailTo = new ArrayList<String>();
    //
    // // 抄送列表
    // List<String> ccmailTo = new ArrayList<String>();
    //
    // // 附件内容都在此LIST内
    // List<String> files = new ArrayList<String>();
    // mailTo.add(mailReceiver);
    // ccmailTo.add(ccMailReceiver);
    // files.add(attacheMent);
    // MailVO mail = new MailVO();
    // mail.setAttachment(files);
    // mail.setCcMailTo(ccmailTo);
    // mail.setMailContent(mailContent);
    // mail.setMailTo(mailTo);
    // mail.setMailSender(mailSender);
    // mail.setMailSendTime((new Date()).getTime());
    // mail.setMailServerHost(mailServierHost);
    // mail.setMailSubject(mailSubject);
    // mail.setIsDefaultMailBox(0);
    // mail.setMailUrl(url);
    // mail.setHostMailAddress(hostMailAddress);
    // mail.setHostMailLoginName(hostMailLoginName);
    // mail.setHostMailPin(hostMailPin);
    // mail.setIsSmtpAuth(isSmtpAuth);
    // MailService req = new MailService(mail);
    // String response = req.sendMail();
    // System.out.println("4,一个正常的收件人，结果：" + response);
    // }

    // // 1个收件人
    // @Test
    // public void testMultiReceivers() {
    // // 收件人
    // List<String> mailTo = new ArrayList<String>();
    //
    // // 抄送列表
    // List<String> ccmailTo = new ArrayList<String>();
    //
    // // 附件内容都在此LIST内
    // List<String> files = new ArrayList<String>();
    // String mailReceiver2 = "fengtian824@163.com";
    // String mailReceiver3 = "176694118@qq.com";
    // mailTo.add(mailReceiver);
    // mailTo.add(mailReceiver2);
    // mailTo.add(mailReceiver3);
    // ccmailTo.add(ccMailReceiver);
    // files.add(attacheMent);
    // MailVO mail = new MailVO();
    // mail.setAttachment(files);
    // mail.setCcMailTo(ccmailTo);
    // mail.setMailContent(mailContent);
    // mail.setMailTo(mailTo);
    // mail.setMailSender(mailSender);
    // mail.setMailSendTime((new Date()).getTime());
    // mail.setMailServerHost(mailServierHost);
    // mail.setMailSubject(mailSubject);
    // mail.setIsDefaultMailBox(isDefaultMailBox);
    // mail.setMailUrl(url);
    // mail.setHostMailAddress(hostMailAddress);
    // mail.setHostMailLoginName(hostMailLoginName);
    // mail.setHostMailPin(hostMailPin);
    // mail.setIsSmtpAuth(isSmtpAuth);
    // MailService req = new MailService(mail);
    // String response = req.sendMail();
    // System.out.println("5,多个正常的收件人，结果：" + response);
    // }
    //
    // // 抄送人List参数含有null元素
    // @Test
    // public void testNoCCMailTo1() {
    // // 收件人
    // List<String> mailTo = new ArrayList<String>();
    //
    // // 抄送列表
    // List<String> ccmailTo = new ArrayList<String>();
    //
    // // 附件内容都在此LIST内
    // List<String> files = new ArrayList<String>();
    // mailTo.add(mailReceiver);
    // // ----------//
    // ccmailTo.add(null);
    // // ----------//
    // files.add(attacheMent);
    // MailVO mail = new MailVO();
    // mail.setAttachment(files);
    // mail.setCcMailTo(ccmailTo);
    // mail.setMailContent(mailContent);
    // mail.setMailTo(mailTo);
    // mail.setMailSender(mailSender);
    // mail.setMailSendTime((new Date()).getTime());
    // mail.setMailServerHost(mailServierHost);
    // mail.setMailSubject(mailSubject);
    // mail.setIsDefaultMailBox(isDefaultMailBox);
    // mail.setMailUrl(url);
    // mail.setHostMailAddress(hostMailAddress);
    // mail.setHostMailLoginName(hostMailLoginName);
    // mail.setHostMailPin(hostMailPin);
    // mail.setIsSmtpAuth(isSmtpAuth);
    // MailService req = new MailService(mail);
    // String response = req.sendMail();
    // System.out.println("6,抄送人List参数含有null元素，结果：" + response);
    // }
    //
    // // 抄送人List为空
    // @Test
    // public void testNoCCMailTo2() {
    // // 收件人
    // List<String> mailTo = new ArrayList<String>();
    //
    // // 抄送列表
    // List<String> ccmailTo = new ArrayList<String>();
    //
    // // 附件内容都在此LIST内
    // List<String> files = new ArrayList<String>();
    // mailTo.add(mailReceiver);
    // ccmailTo.add(ccMailReceiver);
    // files.add(attacheMent);
    // MailVO mail = new MailVO();
    // mail.setAttachment(files);
    // // ----------//
    // mail.setCcMailTo(null);
    // // ----------//
    // mail.setMailContent(mailContent);
    // mail.setMailTo(mailTo);
    // mail.setMailSender(mailSender);
    // mail.setMailSendTime((new Date()).getTime());
    // mail.setMailServerHost(mailServierHost);
    // mail.setMailSubject(mailSubject);
    // mail.setIsDefaultMailBox(isDefaultMailBox);
    // mail.setMailUrl(url);
    // mail.setHostMailAddress(hostMailAddress);
    // mail.setHostMailLoginName(hostMailLoginName);
    // mail.setHostMailPin(hostMailPin);
    // mail.setIsSmtpAuth(isSmtpAuth);
    // MailService req = new MailService(mail);
    // String response = req.sendMail();
    // System.out.println("7,抄送人List为null，结果：" + response);
    // }
    //
    // // 多个抄送人
    // @Test
    // public void testMultiCCMailTos() {
    // // 收件人
    // List<String> mailTo = new ArrayList<String>();
    //
    // // 抄送列表
    // List<String> ccmailTo = new ArrayList<String>();
    //
    // // 附件内容都在此LIST内
    // List<String> files = new ArrayList<String>();
    // mailTo.add(mailReceiver);
    // // ----------//
    // ccmailTo.add("176694118@qq.com");
    // ccmailTo.add("fengtian824@163.com");
    // // ----------//
    // files.add(attacheMent);
    // MailVO mail = new MailVO();
    // mail.setAttachment(files);
    // mail.setCcMailTo(ccmailTo);
    // mail.setMailContent(mailContent);
    // mail.setMailTo(mailTo);
    // mail.setMailSender(mailSender);
    // mail.setMailSendTime((new Date()).getTime());
    // mail.setMailServerHost(mailServierHost);
    // mail.setMailSubject(mailSubject);
    // mail.setIsDefaultMailBox(isDefaultMailBox);
    // mail.setMailUrl(url);
    // mail.setHostMailAddress(hostMailAddress);
    // mail.setHostMailLoginName(hostMailLoginName);
    // mail.setHostMailPin(hostMailPin);
    // mail.setIsSmtpAuth(isSmtpAuth);
    // MailService req = new MailService(mail);
    // String response = req.sendMail();
    // System.out.println("8,多个抄送人，结果：" + response);
    // }
    //
    // // HTML邮件内容
    // @Test
    // public void testHTMLContent() {
    // // 收件人
    // List<String> mailTo = new ArrayList<String>();
    //
    // // 抄送列表
    // List<String> ccmailTo = new ArrayList<String>();
    //
    // // 附件内容都在此LIST内
    // List<String> files = new ArrayList<String>();
    // mailTo.add(mailReceiver);
    // ccmailTo.add(ccMailReceiver);
    // files.add(attacheMent);
    // MailVO mail = new MailVO();
    // mail.setAttachment(files);
    // mail.setCcMailTo(ccmailTo);
    // // ----------//
    // String mailContent =
    // "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD"
    // +
    // "/xhtml1-transitional.dtd\"><html><head><meta http-equiv=\"Content-Type\" content=\"text/html;"
    // +
    // " charset=utf-8\"></meta></head><body><div><p>这是邮件内容</p></div><p><font size=6>这些是测试HTML格式用的文字"
    // + "</font></p></body></html>";
    // // ----------//
    // mail.setMailContent(mailContent);
    // mail.setMailTo(mailTo);
    // mail.setMailSender(mailSender);
    // mail.setMailSendTime((new Date()).getTime());
    // mail.setMailServerHost(mailServierHost);
    // mail.setMailSubject(mailSubject);
    // mail.setIsDefaultMailBox(isDefaultMailBox);
    // mail.setMailUrl(url);
    // mail.setHostMailAddress(hostMailAddress);
    // mail.setHostMailLoginName(hostMailLoginName);
    // mail.setHostMailPin(hostMailPin);
    // mail.setIsSmtpAuth(isSmtpAuth);
    // MailService req = new MailService(mail);
    // String response = req.sendMail();
    // System.out.println("9,HTML邮件内容，结果：" + response);
    // }
    //
    // // 无邮件内容1
    // @Test
    // public void testNoContent1() {
    // // 收件人
    // List<String> mailTo = new ArrayList<String>();
    //
    // // 抄送列表
    // List<String> ccmailTo = new ArrayList<String>();
    //
    // // 附件内容都在此LIST内
    // List<String> files = new ArrayList<String>();
    // mailTo.add(mailReceiver);
    // ccmailTo.add(ccMailReceiver);
    // files.add(attacheMent);
    // MailVO mail = new MailVO();
    // mail.setAttachment(files);
    // mail.setCcMailTo(ccmailTo);
    // // ----------//
    // mail.setMailContent(null);
    // // ----------//
    // mail.setMailTo(mailTo);
    // mail.setMailSender(mailSender);
    // mail.setMailSendTime((new Date()).getTime());
    // mail.setMailServerHost(mailServierHost);
    // mail.setMailSubject(mailSubject);
    // mail.setIsDefaultMailBox(isDefaultMailBox);
    // mail.setMailUrl(url);
    // mail.setHostMailAddress(hostMailAddress);
    // mail.setHostMailLoginName(hostMailLoginName);
    // mail.setHostMailPin(hostMailPin);
    // mail.setIsSmtpAuth(isSmtpAuth);
    // MailService req = new MailService(mail);
    // String response = req.sendMail();
    // System.out.println("10,邮件内容为null，结果：" + response);
    // }
    //
    // // 无邮件内容2
    // @Test
    // public void testNoContent2() {
    // // 收件人
    // List<String> mailTo = new ArrayList<String>();
    // // 抄送列表
    // List<String> ccmailTo = new ArrayList<String>();
    // // 附件内容都在此LIST内
    // List<String> files = new ArrayList<String>();
    // mailTo.add(mailReceiver);
    // ccmailTo.add(ccMailReceiver);
    // files.add(attacheMent);
    // MailVO mail = new MailVO();
    // mail.setAttachment(files);
    // mail.setCcMailTo(ccmailTo);
    // // ----------//
    // mail.setMailContent("");
    // // ----------//
    // mail.setMailTo(mailTo);
    // mail.setMailSender(mailSender);
    // mail.setMailSendTime((new Date()).getTime());
    // mail.setMailServerHost(mailServierHost);
    // mail.setMailSubject(mailSubject);
    // mail.setIsDefaultMailBox(isDefaultMailBox);
    // mail.setMailUrl(url);
    // mail.setHostMailAddress(hostMailAddress);
    // mail.setHostMailLoginName(hostMailLoginName);
    // mail.setHostMailPin(hostMailPin);
    // mail.setIsSmtpAuth(isSmtpAuth);
    // MailService req = new MailService(mail);
    // String response = req.sendMail();
    // System.out.println("11,邮件内容为空字符串，结果：" + response);
    // }
    //
    // // 普通文本格式邮件内容
    // @Test
    // public void testTextMailContent() {
    // // 收件人
    // List<String> mailTo = new ArrayList<String>();
    // // 抄送列表
    // List<String> ccmailTo = new ArrayList<String>();
    // // 附件内容都在此LIST内
    // List<String> files = new ArrayList<String>();
    // mailTo.add(mailReceiver);
    // ccmailTo.add(ccMailReceiver);
    // files.add(attacheMent);
    // MailVO mail = new MailVO();
    // mail.setAttachment(files);
    // mail.setCcMailTo(ccmailTo);
    // // ----------//
    // mail.setMailContent("这些文字是测试普通文本邮件内容用的");
    // // ----------//
    // mail.setMailTo(mailTo);
    // mail.setMailSender(mailSender);
    // mail.setMailSendTime((new Date()).getTime());
    // mail.setMailServerHost(mailServierHost);
    // mail.setMailSubject(mailSubject);
    // mail.setIsDefaultMailBox(isDefaultMailBox);
    // mail.setMailUrl(url);
    // mail.setHostMailAddress(hostMailAddress);
    // mail.setHostMailLoginName(hostMailLoginName);
    // mail.setHostMailPin(hostMailPin);
    // mail.setIsSmtpAuth(isSmtpAuth);
    // MailService req = new MailService(mail);
    // String response = req.sendMail();
    // System.out.println("11,邮件内容为普通文本（非HTML），结果：" + response);
    // }
    //
    // // 无邮件主题(主题为null)
    // @Test
    // public void testNoMailSubject() {
    // // 收件人
    // List<String> mailTo = new ArrayList<String>();
    // // 抄送列表
    // List<String> ccmailTo = new ArrayList<String>();
    // // 附件内容都在此LIST内
    // List<String> files = new ArrayList<String>();
    // mailTo.add(mailReceiver);
    // ccmailTo.add(ccMailReceiver);
    // files.add(attacheMent);
    // MailVO mail = new MailVO();
    // mail.setAttachment(files);
    // mail.setCcMailTo(ccmailTo);
    // mail.setMailContent(mailContent);
    // mail.setMailTo(mailTo);
    // mail.setMailSender(mailSender);
    // mail.setMailSendTime((new Date()).getTime());
    // mail.setMailServerHost(mailServierHost);
    // // ----------//
    // mail.setMailSubject(null);
    // // ----------//
    // mail.setIsDefaultMailBox(isDefaultMailBox);
    // mail.setMailUrl(url);
    // mail.setHostMailAddress(hostMailAddress);
    // mail.setHostMailLoginName(hostMailLoginName);
    // mail.setHostMailPin(hostMailPin);
    // mail.setIsSmtpAuth(isSmtpAuth);
    // MailService req = new MailService(mail);
    // String response = req.sendMail();
    // System.out.println("12,邮件主题为null，结果：" + response);
    // }
    //
    // // 无邮件主题(主题为空字符串)
    // @Test
    // public void testNoMailSubjectString() {
    // // 收件人
    // List<String> mailTo = new ArrayList<String>();
    // // 抄送列表
    // List<String> ccmailTo = new ArrayList<String>();
    // // 附件内容都在此LIST内
    // List<String> files = new ArrayList<String>();
    // mailTo.add(mailReceiver);
    // ccmailTo.add(ccMailReceiver);
    // files.add(attacheMent);
    // MailVO mail = new MailVO();
    // mail.setAttachment(files);
    // mail.setCcMailTo(ccmailTo);
    // mail.setMailContent(mailContent);
    // mail.setMailTo(mailTo);
    // mail.setMailSender(mailSender);
    // mail.setMailSendTime((new Date()).getTime());
    // mail.setMailServerHost(mailServierHost);
    // // ----------//
    // mail.setMailSubject("");
    // // ----------//
    // mail.setIsDefaultMailBox(isDefaultMailBox);
    // mail.setMailUrl(url);
    // mail.setHostMailAddress(hostMailAddress);
    // mail.setHostMailLoginName(hostMailLoginName);
    // mail.setHostMailPin(hostMailPin);
    // mail.setIsSmtpAuth(isSmtpAuth);
    // MailService req = new MailService(mail);
    // String response = req.sendMail();
    // System.out.println("13,邮件主题为空字符串，结果：" + response);
    // }
    //
    // // 发件人昵称为null
    // @Test
    // public void testNoSenderNickName1() {
    // // 收件人
    // List<String> mailTo = new ArrayList<String>();
    // // 抄送列表
    // List<String> ccmailTo = new ArrayList<String>();
    // // 附件内容都在此LIST内
    // List<String> files = new ArrayList<String>();
    // mailTo.add(mailReceiver);
    // ccmailTo.add(ccMailReceiver);
    // files.add(attacheMent);
    // MailVO mail = new MailVO();
    // mail.setAttachment(files);
    // mail.setCcMailTo(ccmailTo);
    // mail.setMailContent(mailContent);
    // mail.setMailTo(mailTo);
    // mail.setMailSender(mailSender);
    // mail.setMailSendTime((new Date()).getTime());
    // mail.setMailServerHost(mailServierHost);
    // mail.setMailSubject(mailSubject);
    // mail.setIsDefaultMailBox(isDefaultMailBox);
    // mail.setMailUrl(url);
    // mail.setHostMailAddress(hostMailAddress);
    // mail.setHostMailLoginName(hostMailLoginName);
    // mail.setHostMailPin(hostMailPin);
    // mail.setIsSmtpAuth(isSmtpAuth);
    // MailService req = new MailService(mail);
    // String response = req.sendMail();
    // System.out.println("14,配置文件中发件人昵称为空，结果：" + response);
    // }
    //
    // // 发件人昵称为null
    // @Test
    // public void testNoSenderNickName2() {
    // // 收件人
    // List<String> mailTo = new ArrayList<String>();
    // // 抄送列表
    // List<String> ccmailTo = new ArrayList<String>();
    // // 附件内容都在此LIST内
    // List<String> files = new ArrayList<String>();
    // mailTo.add(mailReceiver);
    // ccmailTo.add(ccMailReceiver);
    // files.add(attacheMent);
    // MailVO mail = new MailVO();
    // mail.setAttachment(files);
    // mail.setCcMailTo(ccmailTo);
    // mail.setMailContent(mailContent);
    // mail.setMailTo(mailTo);
    // // ----------//
    // mail.setMailSender(null);
    // mail.setIsDefaultMailBox(0);
    // // ----------//
    // mail.setMailSendTime((new Date()).getTime());
    // mail.setMailServerHost(mailServierHost);
    // mail.setMailSubject(mailSubject);
    // mail.setMailUrl(url);
    // mail.setHostMailAddress(hostMailAddress);
    // mail.setHostMailLoginName(hostMailLoginName);
    // mail.setHostMailPin(hostMailPin);
    // mail.setIsSmtpAuth(isSmtpAuth);
    // MailService req = new MailService(mail);
    // String response = req.sendMail();
    // System.out.println("15,不适用配置文件，发件人昵称为空，结果：" + response);
    // }
    //
    // // 发件人昵称为空字符串
    // @Test
    // public void testNoSenderNickName3() {
    // // 收件人
    // List<String> mailTo = new ArrayList<String>();
    // // 抄送列表
    // List<String> ccmailTo = new ArrayList<String>();
    // // 附件内容都在此LIST内
    // List<String> files = new ArrayList<String>();
    // mailTo.add(mailReceiver);
    // ccmailTo.add(ccMailReceiver);
    // files.add(attacheMent);
    // MailVO mail = new MailVO();
    // mail.setAttachment(files);
    // mail.setCcMailTo(ccmailTo);
    // mail.setMailContent(mailContent);
    // mail.setMailTo(mailTo);
    // // ----------//
    // mail.setMailSender("");
    // mail.setIsDefaultMailBox(0);
    // // ----------//
    // mail.setMailSendTime((new Date()).getTime());
    // mail.setMailServerHost(mailServierHost);
    // mail.setMailSubject(mailSubject);
    // mail.setMailUrl(url);
    // mail.setHostMailAddress(hostMailAddress);
    // mail.setHostMailLoginName(hostMailLoginName);
    // mail.setHostMailPin(hostMailPin);
    // mail.setIsSmtpAuth(isSmtpAuth);
    // MailService req = new MailService(mail);
    // String response = req.sendMail();
    // System.out.println("16,不使用配置文件，发件人昵称为空字符串，结果：" + response);
    // }
    //
    // // 有long型发送时间参数
    // @Test
    // public void testLongTypeSendTime() {
    // // 收件人
    // List<String> mailTo = new ArrayList<String>();
    // // 抄送列表
    // List<String> ccmailTo = new ArrayList<String>();
    // // 附件内容都在此LIST内
    // List<String> files = new ArrayList<String>();
    // mailTo.add(mailReceiver);
    // ccmailTo.add(ccMailReceiver);
    // files.add(attacheMent);
    // MailVO mail = new MailVO();
    // mail.setAttachment(files);
    // mail.setCcMailTo(ccmailTo);
    // mail.setMailContent(mailContent);
    // mail.setMailTo(mailTo);
    // mail.setMailSender(mailSender);
    // mail.setIsDefaultMailBox(1);
    // mail.setMailSendTime((new Date()).getTime());
    // mail.setMailServerHost(mailServierHost);
    // mail.setMailSubject(mailSubject);
    // mail.setMailUrl(url);
    // mail.setHostMailAddress(hostMailAddress);
    // mail.setHostMailLoginName(hostMailLoginName);
    // mail.setHostMailPin(hostMailPin);
    // mail.setIsSmtpAuth(isSmtpAuth);
    // MailService req = new MailService(mail);
    // String response = req.sendMail();
    // System.out.println("17,有long型发送时间参数，结果：" + response);
    // }
    //
    // // 不设置发送时间
    // @Test
    // public void testNoSendTime() {
    // // 收件人
    // List<String> mailTo = new ArrayList<String>();
    // // 抄送列表
    // List<String> ccmailTo = new ArrayList<String>();
    // // 附件内容都在此LIST内
    // List<String> files = new ArrayList<String>();
    // mailTo.add(mailReceiver);
    // ccmailTo.add(ccMailReceiver);
    // files.add(attacheMent);
    // MailVO mail = new MailVO();
    // mail.setAttachment(files);
    // mail.setCcMailTo(ccmailTo);
    // mail.setMailContent(mailContent);
    // mail.setMailTo(mailTo);
    // mail.setMailSender(mailSender);
    // mail.setIsDefaultMailBox(isDefaultMailBox);
    // // ----------//
    // // mail.setMailSendTime((new Date()).getTime());
    // // ----------//
    // mail.setMailServerHost(mailServierHost);
    // mail.setMailSubject(mailSubject);
    // mail.setMailUrl(url);
    // mail.setHostMailAddress(hostMailAddress);
    // mail.setHostMailLoginName(hostMailLoginName);
    // mail.setHostMailPin(hostMailPin);
    // mail.setIsSmtpAuth(isSmtpAuth);
    // MailService req = new MailService(mail);
    // String response = req.sendMail();
    // System.out.println("18,不设置发送时间参数，结果：" + response);
    // }
    //
    // // 没有附件（附件list为NUll）
    // @Test
    // public void testNoAttacheMent1() {
    // // 收件人
    // List<String> mailTo = new ArrayList<String>();
    // // 抄送列表
    // List<String> ccmailTo = new ArrayList<String>();
    // // 附件内容都在此LIST内
    // List<String> files = new ArrayList<String>();
    // mailTo.add(mailReceiver);
    // ccmailTo.add(ccMailReceiver);
    // files.add(attacheMent);
    // MailVO mail = new MailVO();
    // // ----------//
    // mail.setAttachment(null);
    // // ----------//
    // mail.setCcMailTo(ccmailTo);
    // mail.setMailContent(mailContent);
    // mail.setMailTo(mailTo);
    // mail.setMailSender(mailSender);
    // mail.setIsDefaultMailBox(isDefaultMailBox);
    // mail.setMailSendTime((new Date()).getTime());
    // mail.setMailServerHost(mailServierHost);
    // mail.setMailSubject(mailSubject);
    // mail.setMailUrl(url);
    // mail.setHostMailAddress(hostMailAddress);
    // mail.setHostMailLoginName(hostMailLoginName);
    // mail.setHostMailPin(hostMailPin);
    // mail.setIsSmtpAuth(isSmtpAuth);
    // MailService req = new MailService(mail);
    // String response = req.sendMail();
    // System.out.println("19,没有附件（附件list为NUll），结果：" + response);
    // }
    //
    // // 没有附件（附件list不含内容）
    // @Test
    // public void testNoAttacheMent2() {
    // // 收件人
    // List<String> mailTo = new ArrayList<String>();
    // // 抄送列表
    // List<String> ccmailTo = new ArrayList<String>();
    // // 附件内容都在此LIST内
    // List<String> files = new ArrayList<String>();
    // mailTo.add(mailReceiver);
    // ccmailTo.add(ccMailReceiver);
    // // ----------//
    // // files.add(attacheMent);
    // // ----------//
    // MailVO mail = new MailVO();
    // mail.setAttachment(files);
    // mail.setCcMailTo(ccmailTo);
    // mail.setMailContent(mailContent);
    // mail.setMailTo(mailTo);
    // mail.setMailSender(mailSender);
    // mail.setIsDefaultMailBox(isDefaultMailBox);
    // mail.setMailSendTime((new Date()).getTime());
    // mail.setMailServerHost(mailServierHost);
    // mail.setMailSubject(mailSubject);
    // mail.setMailUrl(url);
    // mail.setHostMailAddress(hostMailAddress);
    // mail.setHostMailLoginName(hostMailLoginName);
    // mail.setHostMailPin(hostMailPin);
    // mail.setIsSmtpAuth(isSmtpAuth);
    // MailService req = new MailService(mail);
    // String response = req.sendMail();
    // System.out.println("20,没有附件（附件list不含内容），结果：" + response);
    // }
    //
    // // 附件不存在
    // @Test
    // public void testAttacheMentError() {
    // // 收件人
    // List<String> mailTo = new ArrayList<String>();
    // // 抄送列表
    // List<String> ccmailTo = new ArrayList<String>();
    // // 附件内容都在此LIST内
    // List<String> files = new ArrayList<String>();
    // mailTo.add(mailReceiver);
    // ccmailTo.add(ccMailReceiver);
    // // ----------//
    // files.add("c:\\asdfasdfasdfasdfsadf");
    // // ----------//
    // MailVO mail = new MailVO();
    // mail.setAttachment(files);
    // mail.setCcMailTo(ccmailTo);
    // mail.setMailContent(mailContent);
    // mail.setMailTo(mailTo);
    // mail.setMailSender(mailSender);
    // mail.setIsDefaultMailBox(isDefaultMailBox);
    // mail.setMailSendTime((new Date()).getTime());
    // mail.setMailServerHost(mailServierHost);
    // mail.setMailSubject(mailSubject);
    // mail.setMailUrl(url);
    // mail.setHostMailAddress(hostMailAddress);
    // mail.setHostMailLoginName(hostMailLoginName);
    // mail.setHostMailPin(hostMailPin);
    // mail.setIsSmtpAuth(isSmtpAuth);
    // MailService req = new MailService(mail);
    // String response = req.sendMail();
    // System.out.println("21,附件不存在，结果：" + response);
    // }
    //
    // // 含有6个附件
    // @Test
    // public void testSixAttacheMent() {
    // // 收件人
    // List<String> mailTo = new ArrayList<String>();
    // // 抄送列表
    // List<String> ccmailTo = new ArrayList<String>();
    // // 附件内容都在此LIST内
    // List<String> files = new ArrayList<String>();
    // mailTo.add(mailReceiver);
    // ccmailTo.add(ccMailReceiver);
    // // ----------//
    // files.add("d:\\新建 文本文档.txt");
    // files.add("d:\\新建 文本文档1.txt");
    // files.add("d:\\新建 文本文档2.txt");
    // files.add("d:\\新建 文本文档3.txt");
    // files.add("d:\\新建 文本文档4.txt");
    // files.add("d:\\新建 文本文档5.txt");
    // // ----------//
    // MailVO mail = new MailVO();
    // mail.setAttachment(files);
    // mail.setCcMailTo(ccmailTo);
    // mail.setMailContent(mailContent);
    // mail.setMailTo(mailTo);
    // mail.setMailSender(mailSender);
    // mail.setIsDefaultMailBox(isDefaultMailBox);
    // mail.setMailSendTime((new Date()).getTime());
    // mail.setMailServerHost(mailServierHost);
    // mail.setMailSubject(mailSubject);
    // mail.setMailUrl(url);
    // mail.setHostMailAddress(hostMailAddress);
    // mail.setHostMailLoginName(hostMailLoginName);
    // mail.setHostMailPin(hostMailPin);
    // mail.setIsSmtpAuth(isSmtpAuth);
    // MailService req = new MailService(mail);
    // String response = req.sendMail();
    // System.out.println("22,含有6个附件，结果：" + response);
    // }
    //
    // // 含有1个附件11M附件
    // @Test
    // public void testLargeAttachement() {
    // // 收件人
    // List<String> mailTo = new ArrayList<String>();
    // // 抄送列表
    // List<String> ccmailTo = new ArrayList<String>();
    // // 附件内容都在此LIST内
    // List<String> files = new ArrayList<String>();
    // mailTo.add(mailReceiver);
    // ccmailTo.add(ccMailReceiver);
    // // ----------//
    // files.add("d:\\新建 文本文档 (2).txt");
    // // ----------//
    // MailVO mail = new MailVO();
    // mail.setAttachment(files);
    // mail.setCcMailTo(ccmailTo);
    // mail.setMailContent(mailContent);
    // mail.setMailTo(mailTo);
    // mail.setMailSender(mailSender);
    // mail.setIsDefaultMailBox(isDefaultMailBox);
    // mail.setMailSendTime((new Date()).getTime());
    // mail.setMailServerHost(mailServierHost);
    // mail.setMailSubject(mailSubject);
    // mail.setMailUrl(url);
    // mail.setHostMailAddress(hostMailAddress);
    // mail.setHostMailLoginName(hostMailLoginName);
    // mail.setHostMailPin(hostMailPin);
    // mail.setIsSmtpAuth(isSmtpAuth);
    // MailService req = new MailService(mail);
    // String response = req.sendMail();
    // System.out.println("23,含有1个附件11M附件，结果：" + response);
    // }
    //
    // // 服务端URL错误
    // @Test
    // public void testErrorURL() {
    // // 收件人
    // List<String> mailTo = new ArrayList<String>();
    // // 抄送列表
    // List<String> ccmailTo = new ArrayList<String>();
    // // 附件内容都在此LIST内
    // List<String> files = new ArrayList<String>();
    // files.add("d:\\新建 文本文档.txt");
    // mailTo.add(mailReceiver);
    // ccmailTo.add(ccMailReceiver);
    // MailVO mail = new MailVO();
    // mail.setAttachment(files);
    // mail.setCcMailTo(ccmailTo);
    // mail.setMailContent(mailContent);
    // mail.setMailTo(mailTo);
    // mail.setMailSender(mailSender);
    // mail.setIsDefaultMailBox(isDefaultMailBox);
    // mail.setMailSendTime((new Date()).getTime());
    // mail.setMailServerHost(mailServierHost);
    // mail.setMailSubject(mailSubject);
    // // ----------//
    // mail.setMailUrl("http://123:8080/cs/mailservice/sendmail");
    // // ----------//
    // mail.setHostMailAddress(hostMailAddress);
    // mail.setHostMailLoginName(hostMailLoginName);
    // mail.setHostMailPin(hostMailPin);
    // mail.setIsSmtpAuth(isSmtpAuth);
    // MailService req = new MailService(mail);
    // String response = req.sendMail();
    // System.out.println("24,服务端URL错误，结果：" + response);
    // }
    //
    // // 不使用默认邮箱
    // @Test
    // public void testNoDefaultMailBox() {
    // // 收件人
    // List<String> mailTo = new ArrayList<String>();
    // // 抄送列表
    // List<String> ccmailTo = new ArrayList<String>();
    // // 附件内容都在此LIST内
    // List<String> files = new ArrayList<String>();
    // files.add("d:\\新建 文本文档.txt");
    // mailTo.add(mailReceiver);
    // ccmailTo.add(ccMailReceiver);
    // MailVO mail = new MailVO();
    // mail.setAttachment(files);
    // mail.setCcMailTo(ccmailTo);
    // mail.setMailContent(mailContent);
    // mail.setMailTo(mailTo);
    // mail.setMailSender(mailSender);
    // mail.setMailSendTime((new Date()).getTime());
    // // ----------//
    // mail.setIsDefaultMailBox(0);
    // mail.setMailServerHost(mailServierHost);
    // mail.setMailSubject(mailSubject);
    // mail.setMailUrl(url);
    // mail.setHostMailAddress(hostMailAddress);
    // mail.setHostMailLoginName(hostMailLoginName);
    // mail.setHostMailPin(hostMailPin);
    // mail.setIsSmtpAuth(isSmtpAuth);
    // // ----------//
    // MailService req = new MailService(mail);
    // String response = req.sendMail();
    // System.out.println("25,不使用默认邮箱，结果：" + response);
    // }
    //
    // // 不使用默认邮箱，使用能DES解析的密码，但是密码错误
    // @Test
    // public void testNoDefaultMailBox1() {
    // // 收件人
    // List<String> mailTo = new ArrayList<String>();
    // // 抄送列表
    // List<String> ccmailTo = new ArrayList<String>();
    // // 附件内容都在此LIST内
    // List<String> files = new ArrayList<String>();
    // files.add("d:\\新建 文本文档.txt");
    // mailTo.add(mailReceiver);
    // ccmailTo.add(ccMailReceiver);
    // MailVO mail = new MailVO();
    // mail.setAttachment(files);
    // mail.setCcMailTo(ccmailTo);
    // mail.setMailContent(mailContent);
    // mail.setMailTo(mailTo);
    // mail.setMailSender(mailSender);
    // mail.setMailSendTime((new Date()).getTime());
    // // ----------//
    // mail.setIsDefaultMailBox(0);
    // mail.setMailServerHost(mailServierHost);
    // mail.setMailSubject(mailSubject);
    // mail.setMailUrl(url);
    // mail.setHostMailAddress(hostMailAddress);
    // mail.setHostMailLoginName(hostMailLoginName);
    // mail.setHostMailPin(EncryptUtils.DESEncrypt("ecf8427e5d933e61", "343434",
    // 64));
    // mail.setIsSmtpAuth(isSmtpAuth);
    // // ----------//
    // MailService req = new MailService(mail);
    // String response = req.sendMail();
    // System.out.println("26,不使用默认邮箱,使用能DES解析的密码，但是密码错误，结果：" + response);
    // }
    //
    // // 不使用默认邮箱，使用不能DES解析的密码
    // @Test
    // public void testNoDefaultMailBox2() {
    // // 收件人
    // List<String> mailTo = new ArrayList<String>();
    // // 抄送列表
    // List<String> ccmailTo = new ArrayList<String>();
    // // 附件内容都在此LIST内
    // List<String> files = new ArrayList<String>();
    // files.add("d:\\新建 文本文档.txt");
    // mailTo.add(mailReceiver);
    // ccmailTo.add(ccMailReceiver);
    // MailVO mail = new MailVO();
    // mail.setAttachment(files);
    // mail.setCcMailTo(ccmailTo);
    // mail.setMailContent(mailContent);
    // mail.setMailTo(mailTo);
    // mail.setMailSender(mailSender);
    // mail.setMailSendTime((new Date()).getTime());
    // // ----------//
    // mail.setIsDefaultMailBox(0);
    // mail.setMailServerHost(mailServierHost);
    // mail.setMailSubject(mailSubject);
    // mail.setMailUrl(url);
    // mail.setHostMailAddress(hostMailAddress);
    // mail.setHostMailLoginName(hostMailLoginName);
    // mail.setHostMailPin("");
    // mail.setIsSmtpAuth(isSmtpAuth);
    // // ----------//
    // MailService req = new MailService(mail);
    // String response = req.sendMail();
    // System.out.println("27,不使用默认邮箱，使用不能DES解析的密码，结果：" + response);
    // }
    //
    // // 不使用默认邮箱，登录名错误
    // @Test
    // public void testLoginNameError() {
    // // 收件人
    // List<String> mailTo = new ArrayList<String>();
    // // 抄送列表
    // List<String> ccmailTo = new ArrayList<String>();
    // // 附件内容都在此LIST内
    // List<String> files = new ArrayList<String>();
    // files.add("d:\\新建 文本文档.txt");
    // mailTo.add(mailReceiver);
    // ccmailTo.add(ccMailReceiver);
    // MailVO mail = new MailVO();
    // mail.setAttachment(files);
    // mail.setCcMailTo(ccmailTo);
    // mail.setMailContent(mailContent);
    // mail.setMailTo(mailTo);
    // mail.setMailSender(mailSender);
    // mail.setMailSendTime((new Date()).getTime());
    // // ----------//
    // mail.setIsDefaultMailBox(0);
    // mail.setMailServerHost(mailServierHost);
    // mail.setMailSubject(mailSubject);
    // mail.setMailUrl(url);
    // mail.setHostMailAddress(hostMailAddress);
    // mail.setHostMailLoginName("");
    // mail.setHostMailPin(hostMailPin);
    // mail.setIsSmtpAuth(isSmtpAuth);
    // // ----------//
    // MailService req = new MailService(mail);
    // String response = req.sendMail();
    // System.out.println("28,不使用默认邮箱，登录名错误，结果：" + response);
    // }
    //
    // // 不使用默认邮箱，SMTP认证参数为0
    // @Test
    // public void testSMTPAuthZero() {
    // // 收件人
    // List<String> mailTo = new ArrayList<String>();
    // // 抄送列表
    // List<String> ccmailTo = new ArrayList<String>();
    // // 附件内容都在此LIST内
    // List<String> files = new ArrayList<String>();
    // files.add("d:\\新建 文本文档.txt");
    // mailTo.add(mailReceiver);
    // ccmailTo.add(ccMailReceiver);
    // MailVO mail = new MailVO();
    // mail.setAttachment(files);
    // mail.setCcMailTo(ccmailTo);
    // mail.setMailContent(mailContent);
    // mail.setMailTo(mailTo);
    // mail.setMailSender(mailSender);
    // mail.setMailSendTime((new Date()).getTime());
    // // ----------//
    // mail.setIsDefaultMailBox(0);
    // mail.setMailServerHost(mailServierHost);
    // mail.setMailSubject(mailSubject);
    // mail.setMailUrl(url);
    // mail.setHostMailAddress(hostMailAddress);
    // mail.setHostMailLoginName(hostMailLoginName);
    // mail.setHostMailPin(hostMailPin);
    // mail.setIsSmtpAuth(1);
    // // ----------//
    // MailService req = new MailService(mail);
    // String response = req.sendMail();
    // System.out.println("29,不使用默认邮箱，SMTP认证参数为0，结果：" + response);
    // }
    //
    // // 不使用默认邮箱，SMTP认证参数为4
    // @Test
    // public void testSMTPAuthOther() {
    // // 收件人
    // List<String> mailTo = new ArrayList<String>();
    // // 抄送列表
    // List<String> ccmailTo = new ArrayList<String>();
    // // 附件内容都在此LIST内
    // List<String> files = new ArrayList<String>();
    // files.add("d:\\新建 文本文档.txt");
    // mailTo.add(mailReceiver);
    // ccmailTo.add(ccMailReceiver);
    // MailVO mail = new MailVO();
    // mail.setAttachment(files);
    // mail.setCcMailTo(ccmailTo);
    // mail.setMailContent(mailContent);
    // mail.setMailTo(mailTo);
    // mail.setMailSender(mailSender);
    // mail.setMailSendTime((new Date()).getTime());
    // // ----------//
    // mail.setIsDefaultMailBox(0);
    // mail.setMailServerHost(mailServierHost);
    // mail.setMailSubject(mailSubject);
    // mail.setMailUrl(url);
    // mail.setHostMailAddress(hostMailAddress);
    // mail.setHostMailLoginName(hostMailLoginName);
    // mail.setHostMailPin(hostMailPin);
    // mail.setIsSmtpAuth(4);
    // // ----------//
    // MailService req = new MailService(mail);
    // String response = req.sendMail();
    // System.out.println("30,不使用默认邮箱，SMTP认证参数为4，结果：" + response);
    // }
    //
    // // 不使用默认邮箱，登录名与邮箱地址不相符
    // @Test
    // public void testHostNotMatchLoginName() {
    // // 收件人
    // List<String> mailTo = new ArrayList<String>();
    // // 抄送列表
    // List<String> ccmailTo = new ArrayList<String>();
    // // 附件内容都在此LIST内
    // List<String> files = new ArrayList<String>();
    // files.add("d:\\新建 文本文档.txt");
    // mailTo.add(mailReceiver);
    // ccmailTo.add(ccMailReceiver);
    // MailVO mail = new MailVO();
    // mail.setAttachment(files);
    // mail.setCcMailTo(ccmailTo);
    // mail.setMailContent(mailContent);
    // mail.setMailTo(mailTo);
    // mail.setMailSender(mailSender);
    // mail.setMailSendTime((new Date()).getTime());
    // // ----------//
    // mail.setIsDefaultMailBox(0);
    // mail.setMailServerHost(mailServierHost);
    // mail.setMailSubject(mailSubject);
    // mail.setMailUrl(url);
    // mail.setHostMailAddress("wangheping1@hisense.com");
    // mail.setHostMailLoginName("wangheping");
    // mail.setHostMailPin(hostMailPin);
    // mail.setIsSmtpAuth(isSmtpAuth);
    // // ----------//
    // MailService req = new MailService(mail);
    // String response = req.sendMail();
    // System.out.println("31,不使用默认邮箱，登录名与邮箱地址不相符，结果：" + response);
    // }
    //
    // // 不使用默认邮箱，发件箱地址为空
    // @Test
    // public void testHostMailAddressNull() {
    // // 收件人
    // List<String> mailTo = new ArrayList<String>();
    // // 抄送列表
    // List<String> ccmailTo = new ArrayList<String>();
    // // 附件内容都在此LIST内
    // List<String> files = new ArrayList<String>();
    // files.add("d:\\新建 文本文档.txt");
    // mailTo.add(mailReceiver);
    // ccmailTo.add(ccMailReceiver);
    // MailVO mail = new MailVO();
    // mail.setAttachment(files);
    // mail.setCcMailTo(ccmailTo);
    // mail.setMailContent(mailContent);
    // mail.setMailTo(mailTo);
    // mail.setMailSender(mailSender);
    // mail.setMailSendTime((new Date()).getTime());
    // // ----------//
    // mail.setIsDefaultMailBox(0);
    // mail.setMailServerHost(mailServierHost);
    // mail.setMailSubject(mailSubject);
    // mail.setMailUrl(url);
    // mail.setHostMailAddress(null);
    // mail.setHostMailLoginName(hostMailLoginName);
    // mail.setHostMailPin(hostMailPin);
    // mail.setIsSmtpAuth(isSmtpAuth);
    // // ----------//
    // MailService req = new MailService(mail);
    // String response = req.sendMail();
    // System.out.println("31,不使用默认邮箱，发件箱地址为空，结果：" + response);
    // }
    //
    // // 不使用默认邮箱，且发件箱host为空
    // @Test
    // public void testHostNull() {
    // // 收件人
    // List<String> mailTo = new ArrayList<String>();
    // // 抄送列表
    // List<String> ccmailTo = new ArrayList<String>();
    // // 附件内容都在此LIST内
    // List<String> files = new ArrayList<String>();
    // files.add("d:\\新建 文本文档.txt");
    // mailTo.add(mailReceiver);
    // ccmailTo.add(ccMailReceiver);
    // MailVO mail = new MailVO();
    // mail.setAttachment(files);
    // mail.setCcMailTo(ccmailTo);
    // mail.setMailContent(mailContent);
    // mail.setMailTo(mailTo);
    // mail.setMailSender(mailSender);
    // mail.setMailSendTime((new Date()).getTime());
    // // ----------//
    // mail.setIsDefaultMailBox(0);
    // mail.setMailServerHost("");
    // mail.setMailSubject(mailSubject);
    // mail.setMailUrl(url);
    // mail.setHostMailAddress(hostMailAddress);
    // mail.setHostMailLoginName(hostMailLoginName);
    // mail.setHostMailPin(hostMailPin);
    // mail.setIsSmtpAuth(isSmtpAuth);
    // // ----------//
    // MailService req = new MailService(mail);
    // String response = req.sendMail();
    // System.out.println("32,不使用默认邮箱，且发件箱host为空，结果：" + response);
    // }
    //
    // // 不使用默认邮箱，且发件箱host错误
    // @Test
    // public void testHostError() {
    // // 收件人
    // List<String> mailTo = new ArrayList<String>();
    // // 抄送列表
    // List<String> ccmailTo = new ArrayList<String>();
    // // 附件内容都在此LIST内
    // List<String> files = new ArrayList<String>();
    // files.add("d:\\新建 文本文档.txt");
    // mailTo.add(mailReceiver);
    // ccmailTo.add(ccMailReceiver);
    // MailVO mail = new MailVO();
    // mail.setAttachment(files);
    // mail.setCcMailTo(ccmailTo);
    // mail.setMailContent(mailContent);
    // mail.setMailTo(mailTo);
    // mail.setMailSender(mailSender);
    // mail.setMailSendTime((new Date()).getTime());
    // // ----------//
    // mail.setIsDefaultMailBox(0);
    // mail.setMailServerHost("abc");
    // mail.setMailSubject(mailSubject);
    // mail.setMailUrl(url);
    // mail.setHostMailAddress(hostMailAddress);
    // mail.setHostMailLoginName(hostMailLoginName);
    // mail.setHostMailPin(hostMailPin);
    // mail.setIsSmtpAuth(isSmtpAuth);
    // // ----------//
    // MailService req = new MailService(mail);
    // String response = req.sendMail();
    // System.out.println("33,不使用默认邮箱，且发件箱host错误，结果：" + response);
    // }
    //
    // // 默认邮箱使用 参数的值为3
    // @Test
    // public void testErrorIsDefaultMailBox() {
    // // 收件人
    // List<String> mailTo = new ArrayList<String>();
    // // 抄送列表
    // List<String> ccmailTo = new ArrayList<String>();
    // // 附件内容都在此LIST内
    // List<String> files = new ArrayList<String>();
    // files.add("d:\\新建 文本文档.txt");
    // mailTo.add(mailReceiver);
    // ccmailTo.add(ccMailReceiver);
    // MailVO mail = new MailVO();
    // mail.setAttachment(files);
    // mail.setCcMailTo(ccmailTo);
    // mail.setMailContent(mailContent);
    // mail.setMailTo(mailTo);
    // mail.setMailSender(mailSender);
    // mail.setMailSendTime((new Date()).getTime());
    // // ----------//
    // mail.setIsDefaultMailBox(23523523);
    // mail.setMailServerHost(mailServierHost);
    // mail.setMailSubject(mailSubject);
    // mail.setMailUrl(url);
    // mail.setHostMailAddress(hostMailAddress);
    // mail.setHostMailLoginName(hostMailLoginName);
    // mail.setHostMailPin(hostMailPin);
    // mail.setIsSmtpAuth(isSmtpAuth);
    // // ----------//
    // MailService req = new MailService(mail);
    // String response = req.sendMail();
    // System.out.println("34,默认邮箱使用  参数的值为3，结果：" + response);
    // }
    //
    // // 服务器关闭测试
    // @Test
    // public void testMSSServerDown() {
    // // 收件人
    // List<String> mailTo = new ArrayList<String>();
    // // 抄送列表
    // List<String> ccmailTo = new ArrayList<String>();
    // // 附件内容都在此LIST内
    // List<String> files = new ArrayList<String>();
    // files.add("d:\\新建 文本文档.txt");
    // mailTo.add(mailReceiver);
    // ccmailTo.add(ccMailReceiver);
    // MailVO mail = new MailVO();
    // mail.setAttachment(files);
    // mail.setCcMailTo(ccmailTo);
    // mail.setMailContent(mailContent);
    // mail.setMailTo(mailTo);
    // mail.setMailSender(mailSender);
    // mail.setMailSendTime((new Date()).getTime());
    // // ----------//
    // mail.setIsDefaultMailBox(isDefaultMailBox);
    // mail.setMailServerHost(mailServierHost);
    // mail.setMailSubject(mailSubject);
    // mail.setMailUrl(url);
    // mail.setHostMailAddress(hostMailAddress);
    // mail.setHostMailLoginName(hostMailLoginName);
    // mail.setHostMailPin(hostMailPin);
    // mail.setIsSmtpAuth(isSmtpAuth);
    // // ----------//
    // MailService req = new MailService(mail);
    // String response = req.sendMail();
    // System.out.println("36,服务器关闭测试，结果：" + response);
    // }
}
