package com.hisense.hitv.task.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.hisense.hitv.mq.MqMessageSender;
import com.hisense.hitv.mss.MQMailSenderService;
import com.hisense.hitv.mss.MailVO;
import com.hisense.hitv.mss.dao.IExceptionretryMailDao;
import com.hisense.hitv.task.IExceptionProcessorTask;
import com.hisense.hitv.utils.CalculateDate;
import com.hisense.hitv.utils.ErrorCode;
import com.hisense.hitv.utils.Params;

public class ExceptionProcessorTaskImpl implements IExceptionProcessorTask {
    private MQMailSenderService mQMailSenderService;
    private IExceptionretryMailDao exceptionretryMailDao;
    private MqMessageSender messageSender;
    private int retryTimes;
    private Params params;
    private static final Log log = LogFactory.getLog(ExceptionProcessorTaskImpl.class);

    // 从sqlite获取异常消息，处理完成后将成功的消息删除，剩下的消息保留（处理时要注意消息的有效时间，超过有效时间的直接删除即可）
    public void processExceptionTask() {
        log.info("Exception processor begin...!");
        // 从Sqlite获取异常的消息，进行处理
        List<MailVO> exceptionMessageList;
        try {
            exceptionMessageList = exceptionretryMailDao.getAllExceptionRetryMsg();
        } catch (SQLException e) {
            log.error("when getting data from sqlite,error occured!", e);
            return;
        }
        if (exceptionMessageList == null || exceptionMessageList.size() == 0) {
            log.info("Nothing to process,Over!");
            return;
        }
        for (MailVO m : exceptionMessageList) {
            log.info("processing sqlite mail message:" + m.toString());
            if (m.getCreatedDate() < (CalculateDate.today().getTime() - params.getExpireLong())) {
                // 如果消息已经过期，则删除本条记录
                log.info("Message got  from sqlite  expired! delete! id:" + m.getId());
                try {
                    exceptionretryMailDao.delete(m.getId());
                } catch (SQLException ee) {
                    log.error("when deleting data from sqlite,error occured!id:" + m.getId(), ee);
                }
                continue;
            }
            messageSender.setMqSenderName(m.getMqName());
            String resultCode;
            for (int i = 1; i <= retryTimes; i++) {
                resultCode =
                    mQMailSenderService.sendMQMail(m.getMailContent(), m.getMailSubject(), m.getMailToStr(),
                        m.getCcMailToStr(), m.getMqName(), params);
                log.info("resultCode is:" + resultCode);
                if (resultCode.equals(ErrorCode.SUCCESS)) {
                    log.info("send mail over!");
                    mQMailSenderService.sendResultBack(resultCode, m.getMailToStr(), m.getCcMailToStr());
                    // 删除本条记录
                    log.info("Message got  from sqlite  sucessfully processd! delete! id:" + m.getId());
                    try {
                        exceptionretryMailDao.delete(m.getId());
                    } catch (SQLException ee) {
                        log.error("when deleting data from sqlite,error occured!id:" + m.getId(), ee);
                    }
                    break;
                }
                if (resultCode.equals(ErrorCode.NO_MAIL_RECEIVER)) {
                    log.info("mqName is null,error!");
                    mQMailSenderService.sendResultBack(resultCode, m.getMailToStr(), m.getCcMailToStr());
                    try {
                        exceptionretryMailDao.delete(m.getId());
                    } catch (SQLException ee) {
                        log.error("when deleting data from sqlite,error occured!id:" + m.getId(), ee);
                    }
                    break;
                }
                if (resultCode.equals(ErrorCode.AUTHENTICATION_FAILED)) {
                    log.info("MailBox 's password maybe not right!check it!,error!id:" + m.getId());
                    break;
                }
            }
        }
        log.info("Exception processor Over!");
    }

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

    public MqMessageSender getMessageSender() {
        return messageSender;
    }

    public void setMessageSender(MqMessageSender messageSender) {
        this.messageSender = messageSender;
    }

    public int getRetryTimes() {
        return retryTimes;
    }

    public void setRetryTimes(int retryTimes) {
        this.retryTimes = retryTimes;
    }

    public Params getParams() {
        return params;
    }

    public void setParams(Params params) {
        this.params = params;
    }
}
