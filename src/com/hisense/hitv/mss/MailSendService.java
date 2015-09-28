package com.hisense.hitv.mss;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.activation.URLDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.SendFailedException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import com.hisense.hitv.EncryptUtils;
import com.hisense.hitv.utils.ByteDataSource;
import com.hisense.hitv.utils.ErrorCode;
import com.hisense.hitv.utils.Params;
import com.hisense.hitv.utils.StringUtil;

/**
 * 实现邮件的发送功能
 * @author Edwyn Wang
 */
public class MailSendService {
    /**
     * UTF-8字符串
     */
    private static final String UTF8 = "UTF-8";
    /**
     * 分隔符号
     */
    private static final String TO_SUFFIX = ";";
    /**
     * 字符串1
     */
    private static final String ONE = "1";

    /**
     * 字符串0
     */
    private static final String ZERO = "0";
    /**
     * CC标志
     */
    private static final String CC = "cc";
    /**
     * TO标志
     */
    private static final String TO = "to";
    /**
     * mail.smtp.auth 字符串
     */
    private static final String MAIL_SMTP_AUTH = "mail.smtp.auth";
    private ArrayList<BodyPart> bodypartArrayList;
    private Multipart multipart;
    private MimeMessage mailMessage;
    private Session mailSession;
    private Properties mailProperties = System.getProperties();
    private InternetAddress mailFromAddress;
    private InternetAddress mailToAddress;

    // 允许最多附件数
    private String fileNumber;
    // 发件人地址
    private String hostMailAddress;
    // 发件人
    private String mailSender;
    // SMTP认证
    private String isSmtpAuth;
    // 邮箱用户名
    private String hostMailLoginName;
    // 邮箱密码
    private String hostMailPin;
    // 发件箱HOST ADDRESS
    private String mailServerHost;

    /**
     * @return the fileNumber
     */
    public String getFileNumber() {
        return fileNumber;
    }

    /**
     * @return the hostMailAddress
     */
    public String getHostMailAddress() {
        return hostMailAddress;
    }

    /**
     * @return the mailSender
     */
    public String getMailSender() {
        return mailSender;
    }

    /**
     * @return the isSmtpAuth
     */
    public String getIsSmtpAuth() {
        return isSmtpAuth;
    }

    /**
     * @return the hostMailLoginName
     */
    public String getHostMailLoginName() {
        return hostMailLoginName;
    }

    /**
     * @return the hostMailPin
     */
    public String getHostMailPin() {
        return hostMailPin;
    }

    /**
     * @return the mailServerHost
     */
    public String getMailServerHost() {
        return mailServerHost;
    }

    /**
     * 构造邮件发送参数
     * @param mailVO 邮件实体类
     * @param params 配置参数集合
     * @return 邮件设置结果
     * @throws MessagingException 异常
     * @throws NumberFormatException 异常
     * @throws UnsupportedEncodingException 异常
     */
    public String setMail(MailVO mailVO, Params params)
        throws NumberFormatException, MessagingException,
        UnsupportedEncodingException {

        if (Long.parseLong(mailVO.getFileNumberStr()) > Long.parseLong(mailVO
            .getFileNumberPropertity())) {
            return ErrorCode.TOO_MANY_FILES;
        }

        // 发件箱设置
        if (mailVO.getIsDefaultMailBoxStr() == null) {
            return ErrorCode.IS_DEF_MAILBOX_ERR;
        }
        if (!mailVO.getIsDefaultMailBoxStr().equals(ONE)
            && !mailVO.getIsDefaultMailBoxStr().equals(ZERO)) {
            return ErrorCode.IS_DEF_MAILBOX_ERR;
        }

        // 不使用默认邮箱
        if (mailVO.getIsDefaultMailBoxStr().equals(ZERO)) {
            String result =
                setMailBox(mailVO.getMailServerHost(), mailVO
                    .getHostMailLoginName(), mailVO.getHostMailPin(), mailVO
                    .getIsSmtpAuthStr(), mailVO.getHostMailAddress(), mailVO
                    .getMailSender(), params);
            if (!result.equals(ErrorCode.SUCCESS)) {
                return result;
            }
        } else {
            String result =
                setMailBox(mailVO.getMailServerHostPropertity(), mailVO
                    .getHostMailLoginNamePropertity(), mailVO
                    .getHostMailPinPropertity(), mailVO
                    .getIsSmtpAuthPropertity(), mailVO
                    .getHostMailAddressPropertity(), mailVO
                    .getMailSenderPropertity(), params);
            if (!result.equals(ErrorCode.SUCCESS)) {
                return result;
            }
        }

        // 设置邮件主题
        mailMessage.setSubject(mailVO.getMailSubject());
        if (StringUtil.isBlank(mailVO.getIsDefaultMailBoxStr())) {
            return ErrorCode.IS_DEF_MAILBOX_ERR;
        }
        // 设置邮件发送时间
        mailMessage.setSentDate(new Date((Long.parseLong(mailVO
            .getMailSendTimeStr()))));
        // 添加HTML内容
        this.addHtmlContext(mailVO.getMailContent());

        // 收件人不存在
        if (StringUtil.isBlank(mailVO.getMailToStr())) {
            return ErrorCode.NO_MAIL_RECEIVER;
        }
        String[] mto = mailVO.getMailToStr().split(TO_SUFFIX);
        for (String email : mto) {
            if (StringUtil.checkEmailPattern(email) == false) {
                return ErrorCode.MAIL_TO_PATTERN_ERROR;
            }
        }
        // 收件人不存在
        if (mailVO.getCcMailToStr() != null
            && !StringUtil.isBlank(mailVO.getCcMailToStr())) {
            String[] ccmto = mailVO.getCcMailToStr().split(TO_SUFFIX);
            for (String email : ccmto) {
                if (StringUtil.checkEmailPattern(email) == false) {
                    return ErrorCode.CCMAIL_TO_PATTERN_ERROR;
                }
            }
            this.setMailTo(ccmto, CC);
        }

        // 设置收件人
        this.setMailTo(mto, TO);
        return ErrorCode.SUCCESS;
    }

    /**
     * @param mailServerHost HOST
     * @param hostMailLoginName loginname
     * @param hostMailPin 邮箱密码
     * @param isSmtpAuth SMTP认证
     * @param hostMailAddress 邮箱地址
     * @param mailSender 发件人
     * @param params 配置参数集合
     * @return String 结果
     * @throws MessagingException 异常
     * @throws UnsupportedEncodingException
     * @throws UnsupportedEncodingException 异常
     */
    public String setMailBox(String mailServerHost, String hostMailLoginName,
        String hostMailPin, String isSmtpAuth, String hostMailAddress,
        String mailSender, Params params) throws MessagingException,
        UnsupportedEncodingException {
        this.mailServerHost = mailServerHost;
        this.hostMailLoginName = hostMailLoginName;
        this.hostMailPin =
            EncryptUtils.DESDecrypt(params.getEncryptionkey(), hostMailPin);
        if (this.hostMailPin == null) {
            return ErrorCode.DES_ENCRYPT_ERR;
        }
        mailProperties.put("mail.smtp.host", mailServerHost);
        if (isSmtpAuth.equals(ONE)) {
            // 设置smtp认证
            mailProperties.put(MAIL_SMTP_AUTH, "true");
        } else if (isSmtpAuth.equals(ZERO)) {
            mailProperties.put(MAIL_SMTP_AUTH, "false");
        } else {
            return ErrorCode.IS_SMTP_AUTH_ERR;
        }
        mailProperties.setProperty("mail.smtp.connectiontimeout", String.valueOf(params.getConnectTimeout()));
        mailProperties.put("mail.smtp.timeout", String.valueOf(params.getConnectTimeout()));
        mailSession = Session.getDefaultInstance(mailProperties);

        mailMessage = new MimeMessage(mailSession);
        multipart = new MimeMultipart();
        // 用来存放BodyPart，可以有多个BodyPart！
        bodypartArrayList = new ArrayList<BodyPart>();
        mailFromAddress = new InternetAddress(hostMailAddress, mailSender);
        mailMessage.setFrom(mailFromAddress);
        return ErrorCode.SUCCESS;
    }

    /**
     * 发送纯文本
     * @param textcontent 发送文本
     * @throws MessagingException 异常
     */
    public void addTextContext(String textcontent) throws MessagingException {
        BodyPart bodypart = new MimeBodyPart();
        bodypart.setContent(textcontent, "text/plain;charset=UTF-8");
        bodypartArrayList.add(bodypart);
    }

    /**
     * 发送Html邮件
     * @param htmlcontent HTML格式内容
     * @throws MessagingException 异常
     */
    public void addHtmlContext(String htmlcontent) throws MessagingException {
        BodyPart bodypart = new MimeBodyPart();
        bodypart.setContent(htmlcontent, "text/html;charset=UTF-8");
        bodypartArrayList.add(bodypart);
    }

    /**
     * 将文件添加为附件
     * @param fileName 附件文件名
     * @param displayFileName 在邮件中想要显示的文件名
     * @throws MessagingException 异常
     * @throws UnsupportedEncodingException 异常
     */
    public void addAttachment(String fileName, String displayFileName)
        throws MessagingException, UnsupportedEncodingException {
        BodyPart bodypart = new MimeBodyPart();
        FileDataSource fds = new FileDataSource(fileName);

        DataHandler dh = new DataHandler(fds);
        String displayfilename = "";
        // 对显示名称进行编码，否则会出现乱码！
        displayfilename = MimeUtility.encodeWord(displayFileName, UTF8, null);
        // 可以和原文件名不一致
        bodypart.setFileName(displayfilename);
        bodypart.setDataHandler(dh);
        bodypartArrayList.add(bodypart);
    }

    /**
     * 将byte[]作为文件添加为附件
     * @param filebyte 附件文件的字节数组
     * @param displayFileName 在邮件中想要显示的文件名
     * @throws MessagingException 异常
     * @throws UnsupportedEncodingException 异常
     */
    public void addAttachmentFrombyte(byte[] filebyte, String displayFileName)
        throws MessagingException, UnsupportedEncodingException {
        BodyPart bodypart = new MimeBodyPart();
        ByteDataSource fds = new ByteDataSource(filebyte, displayFileName);

        DataHandler dh = new DataHandler(fds);
        String displayfilename = "";
        // 对显示名称进行编码，否则会出现乱码！
        displayfilename = MimeUtility.encodeWord(displayFileName, UTF8, null);
        // 可以和原文件名不一致
        bodypart.setFileName(displayfilename);
        bodypart.setDataHandler(dh);
        bodypartArrayList.add(bodypart);
    }

    /**
     * 使用远程文件（使用URL）作为信件的附件
     * @param url 远程文件地址
     * @param displayFileName 显示名称
     * @throws MessagingException 异常
     * @throws MalformedURLException 异常
     * @throws UnsupportedEncodingException 异常
     */
    public void addAttachmentFromUrl(String url, String displayFileName)
        throws MessagingException, MalformedURLException,
        UnsupportedEncodingException {
        BodyPart bodypart = new MimeBodyPart();
        // 用远程文件作为信件的附件
        URLDataSource ur = new URLDataSource(new URL(url));
        // 注:这里用的参数只能为URL对象,不能为URL字串
        DataHandler dh = new DataHandler(ur);
        String displayfilename = "";
        displayfilename = MimeUtility.encodeWord(displayFileName, UTF8, null);
        // 可以和原文件名不一致
        bodypart.setFileName(displayfilename);
        bodypart.setDataHandler(dh);
        bodypartArrayList.add(bodypart);
    }

    /**
     * 设置收件人地址，收件人类型为to,cc,bcc(大小写不限)
     * @param mailTo 收件人地址
     * @param mailType 收件人，抄送人等
     * @throws MessagingException 异常
     */
    public void setMailTo(String[] mailTo, String mailType)
        throws MessagingException {
        for (int i = 0; i < mailTo.length; i++) {
            mailToAddress = new InternetAddress(mailTo[i]);
            if (mailType.equalsIgnoreCase(TO)) {
                mailMessage.addRecipient(Message.RecipientType.TO,
                    mailToAddress);
            } else if (mailType.equalsIgnoreCase(CC)) {
                mailMessage.addRecipient(Message.RecipientType.CC,
                    mailToAddress);
            } else if (mailType.equalsIgnoreCase("bcc")) {
                mailMessage.addRecipient(Message.RecipientType.BCC,
                    mailToAddress);
            }
        }
    }

    /**
     * 发送邮件
     * @throws MessagingException 异常
     * @throws SendFailedException 异常
     */
    public void sendMail() throws MessagingException, SendFailedException {
        for (int i = 0; i < bodypartArrayList.size(); i++) {
            multipart.addBodyPart((BodyPart) bodypartArrayList.get(i));
        }
        mailMessage.setContent(multipart);
        mailMessage.saveChanges();
        Transport transport = mailSession.getTransport("smtp");
        // 以smtp方式登录邮箱
        transport.connect(mailServerHost, hostMailLoginName, hostMailPin);
        transport.sendMessage(mailMessage, mailMessage.getAllRecipients());
        // 发送邮件,其中第二个参数是所有已设好的收件人地址
        transport.close();
    }

    /**
     * @param hostMailAddress 邮件服务器HOST地址
     */
    public void setHostMailAddress(String hostMailAddress) {
        this.hostMailAddress = hostMailAddress;
    }

    /**
     * @param mailSender 发件人
     */
    public void setMailSender(String mailSender) {
        this.mailSender = mailSender;
    }

    /**
     * @param isSmtpAuth 是否SMTP认证
     */
    public void setIsSmtpAuth(String isSmtpAuth) {
        this.isSmtpAuth = isSmtpAuth;
    }

    /**
     * @param hostMailLoginName 邮件服务器登录loginname
     */
    public void setHostMailLoginName(String hostMailLoginName) {
        this.hostMailLoginName = hostMailLoginName;
    }

    /**
     * @param hostMailPin 邮件服务器登录密码
     */
    public void setHostMailPin(String hostMailPin) {
        this.hostMailPin = hostMailPin;
    }

    /**
     * @param mailServerHost 邮件服务器地址
     */
    public void setMailServerHost(String mailServerHost) {
        this.mailServerHost = mailServerHost;
    }

    /**
     * @param fileNumber 附件个数
     */
    public void setFileNumber(String fileNumber) {
        this.fileNumber = fileNumber;
    }

}
