package com.hisense.hitv.mq;

import java.sql.SQLException;
import java.util.Date;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

import com.hisense.hitv.sms.client.JsonReqClient;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.hisense.hitv.common.nms.NMSAdapter;
import com.hisense.hitv.mss.LogHandler;
import com.hisense.hitv.mss.MQMailSenderService;
import com.hisense.hitv.mss.MailVO;
import com.hisense.hitv.mss.dao.IExceptionretryMailDao;
import com.hisense.hitv.utils.ErrorCode;
import com.hisense.hitv.utils.Params;

/**
 * 接收MQ消息模块
 *
 * @author wangheping
 */
public class MqMessageListener implements MessageListener {
    private static final Log LOG = LogFactory.getLog(MqMessageListener.class);
    private String mainFilePath;
    private String mpServerPath;
    private MqMessageSender messageSender;
    private Params params;
    private NMSAdapter nmsAdapter;
    private MQMailSenderService mQMailSenderService;
    private int retryTimes;
    private IExceptionretryMailDao exceptionretryMailDao;
    private LogHandler logHandler = new LogHandler();

    public MQMailSenderService getmQMailSenderService() {
        return mQMailSenderService;
    }

    public void setmQMailSenderService(MQMailSenderService mQMailSenderService) {
        this.mQMailSenderService = mQMailSenderService;
    }

    public IExceptionretryMailDao getExceptionretryMailDao() {
        return exceptionretryMailDao;
    }

    public void setExceptionretryMailDao(IExceptionretryMailDao exceptionretryMailDao) {
        this.exceptionretryMailDao = exceptionretryMailDao;
    }

    public int getRetryTimes() {
        return retryTimes;
    }

    public void setRetryTimes(int retryTimes) {
        this.retryTimes = retryTimes;
    }

    /**
     * @return the nmsAdapter
     */
    public NMSAdapter getNmsAdapter() {
        return nmsAdapter;
    }

    /**
     * @param nmsAdapter the nmsAdapter to set
     */
    public void setNmsAdapter(NMSAdapter nmsAdapter) {
        this.nmsAdapter = nmsAdapter;
    }

    @Override
    public void onMessage(Message message) {
        LOG.info("has received messages!!!!!!!");
        String mailContent = null;
        String mailSubject = null;
        String mailTo = null;
        String ccMailTo = null;
        String mqName = null;
        try {
            if (message instanceof MapMessage) {
                MapMessage mapMessage = (MapMessage) message;
                String phone = mapMessage.getString("phone");
                if (StringUtils.isBlank(phone)) {//发送邮件
                    mailContent = mapMessage.getString("mailContent");
                    mailSubject = mapMessage.getString("mailSubject");
                    mailTo = mapMessage.getString("mailTo");
                    ccMailTo = mapMessage.getString("ccMailTo");
                    mqName = mapMessage.getString("mqName");
                    messageSender.setMqSenderName(mqName);
                    StringBuffer paramRecord = new StringBuffer();
                    paramRecord.append("mailContent:").append(mailContent == null ? "null" : mailContent)
                            .append("  mailSubject:").append(mailSubject == null ? "null" : mailSubject).append("  mailTo:")
                            .append(mailTo == null ? "null" : mailTo).append("  ccMailTo:")
                            .append(ccMailTo == null ? "null" : ccMailTo).append("  mqName:")
                            .append(mqName == null ? "null" : mqName);
                    LOG.info(paramRecord.toString());
                    logHandler.makeLog("", mailContent, mailSubject, mailTo, ccMailTo, mqName);
                    String resultCode;
                    for (int i = 1; i <= retryTimes; i++) {
                        resultCode =
                                mQMailSenderService.sendMQMail(mailContent, mailSubject, mailTo, ccMailTo, mqName, params);
                        LOG.info("resultCode is:" + resultCode);
                        if (resultCode.equals(ErrorCode.SUCCESS)) {
                            LOG.info("send mail over!");
                            mQMailSenderService.sendResultBack(resultCode, mailTo, ccMailTo);
                            return;
                        }
                        if (resultCode.equals(ErrorCode.NO_MAIL_RECEIVER)) {
                            LOG.info("mqName is null,error!");
                            mQMailSenderService.sendResultBack(resultCode, mailTo, ccMailTo);
                            return;
                        }
                        if (resultCode.equals(ErrorCode.AUTHENTICATION_FAILED)) {
                            LOG.info("MailBox 's password maybe not right!check it!,error!");
                            break;
                        }
                    }
                    MailVO mailVO = new MailVO();
                    mailVO.setMailContent(mailContent);
                    mailVO.setMailSubject(mailSubject);
                    mailVO.setMailToStr(mailTo);
                    mailVO.setCcMailToStr(ccMailTo);
                    mailVO.setMqName(mqName);
                    mailVO.setCreatedDate(new Date().getTime());
                    // 如果超过重试次数仍然没有成功，需要记录到sqlite中
                    try {
                        LOG.info("mail sending failed!insert it to sqlite!");
                        exceptionretryMailDao.insert(mailVO);
                    } catch (SQLException e) {
                        LOG.error("SQLException occured!can not write to sqlite!", e);
                    }
                }else{//发送短信验证码
                    String accountSid = "8d41f8301363b5ea99294d600d79ab20";
                    String token = "25d3464c932c585882a931170223ef88";
                    String appId = "0c5b05bd992e40d58e17cf282ede0495";
                    String templateId = "3853";
                    String code = mapMessage.getString("code");
                    System.out.println(code);
                    String result = new JsonReqClient().templateSMS(accountSid, token, appId, templateId, phone, code);
                    System.out.println(result);
                    StringBuffer sb =new StringBuffer( );
                    sb.append("{");
                    sb.append("\"seqCode\":");
                    sb.append(code);
                    sb.append(",");
                    sb.append("\"phone\":");
                    sb.append(phone);
                    sb.append(",");
                    sb.append("\"result\":");
                    sb.append(result.contains("000000"));
                    sb.append("}");
                    System.out.println(sb);
                }

            }
        } catch (JMSException e) {
            LOG.error("when getting mail property error occured!", e);
            LOG.error(e);
            mQMailSenderService.sendResultBack(ErrorCode.MQ_EXCEPTION_ERROR, mailTo, ccMailTo);
        }
    }

    /**
     * @return the mainFilePath
     */
    public String getMainFilePath() {
        return mainFilePath;
    }

    /**
     * @param mainFilePath the mainFilePath to set
     */
    public void setMainFilePath(String mainFilePath) {
        this.mainFilePath = mainFilePath;
    }

    /**
     * @return the mpServerPath
     */
    public String getMpServerPath() {
        return mpServerPath;
    }

    /**
     * @param mpServerPath the mpServerPath to set
     */
    public void setMpServerPath(String mpServerPath) {
        this.mpServerPath = mpServerPath;
    }

    /**
     * @return the messageSender
     */
    public MqMessageSender getMessageSender() {
        return messageSender;
    }

    /**
     * @param messageSender the messageSender to set
     */
    public void setMessageSender(MqMessageSender messageSender) {
        this.messageSender = messageSender;
    }

    /**
     * @return the params
     */
    public Params getParams() {
        return params;
    }

    /**
     * @param params the params to set
     */
    public void setParams(Params params) {
        this.params = params;
    }

}
