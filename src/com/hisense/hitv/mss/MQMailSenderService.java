package com.hisense.hitv.mss;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.mail.AuthenticationFailedException;
import javax.mail.MessagingException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.hisense.hitv.mq.MqMessageSender;
import com.hisense.hitv.utils.ErrorCode;
import com.hisense.hitv.utils.MailVOUtils;
import com.hisense.hitv.utils.Params;
import com.sun.mail.smtp.SMTPSendFailedException;

public class MQMailSenderService {
    private LogHandler logHandler = new LogHandler();
    private static final Log LOG = LogFactory.getLog(MQMailSenderService.class);
    private MqMessageSender messageSender;
    private MailSendService mailSendService;

    public String sendMQMail(String mailContent, String mailSubject, String mailTo, String ccMailTo, String mqName, Params params) {
        MailVO mailVO = new MailVO();
        if (mailTo == null || mailTo.equals("")) {
            LOG.info("mailTo is null,error!");
            return ErrorCode.NO_MAIL_RECEIVER;
        }
        if (ccMailTo != null && !ccMailTo.equals("")) {
            mailVO.setCcMailToStr(ccMailTo);
        }
        mailVO.setMailToStr(mailTo);
        mailVO.setMailContent(mailContent);
        mailVO.setMailSendTimeStr(String.valueOf(new Date().getTime()));
        if (mailSubject != null && !mailSubject.equals("")) {
            mailVO.setMailSubject(mailSubject);
        }
        // 设置从配置文件读取进来的参数信息
        MailVOUtils.setMailVODefaultValus(mailVO, params);
        mailVO.setFileNumberStr("0");
        mailVO.setFileNumberPropertity("0");
        mailVO.setIsDefaultMailBoxStr("1");
        try {
            String resultCode = mailSendService.setMail(mailVO, params);
            if (!resultCode.equals(ErrorCode.SUCCESS)) {
                return resultCode;
            }
            mailSendService.sendMail();
        } catch (SMTPSendFailedException e) {
            LOG.error(e);
            return ErrorCode.SMTP_ERR;
        } catch (AuthenticationFailedException e) {
            LOG.error(e);
            return ErrorCode.AUTHENTICATION_FAILED;
        } catch (MessagingException e) {
            LOG.error(e);
            return ErrorCode.INTENAL_ERR;
        } catch (UnsupportedEncodingException e) {
            LOG.error(e);
            return ErrorCode.CHARACTOR_NOT_SUPPORTTED;
        } catch (Exception e) {
            LOG.error(e);
            return ErrorCode.INTENAL_ERR;
        }
        return ErrorCode.SUCCESS;
    }

    public void sendResultBack(String resultCode, String emailToStr, String ccMailToStr) {
        logHandler.makeResponseLog(resultCode);
        if (messageSender.getMqSenderName() != null && !messageSender.getMqSenderName().equals("")) {
            Map<String, String> resultMap = new HashMap<String, String>();
            resultMap.put("resultCode", resultCode);
            resultMap.put("emailTo", emailToStr);
            resultMap.put("ccMailTo", ccMailToStr);
            messageSender.sendMqMessage(resultMap);
        }
    }

    public MqMessageSender getMessageSender() {
        return messageSender;
    }

    public void setMessageSender(MqMessageSender messageSender) {
        this.messageSender = messageSender;
    }

    public MailSendService getMailSendService() {
        return mailSendService;
    }

    public void setMailSendService(MailSendService mailSendService) {
        this.mailSendService = mailSendService;
    }

}
