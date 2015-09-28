package com.hisense.hitv.mss;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.hisense.hitv.utils.ErrorCode;

/**
 * 邮件服务被调用时，邮件的内容记录LOG处理类
 * @author wangheping
 */
public class LogHandler {
    private static final Log LOG = LogFactory.getLog("RECORD");
    private static final String mark = "  |";
    private static final String requestStr = "request:";

    /**
     * 用于记录每一个访问本服务的request请求内容
     * @param remoteAddress 访问服务的Ip信息
     * @param mailVO mail实体
     * @return String 处理结果
     */
    public String makeLog(String remoteAddress, MailVO mailVO) {
        String result =
            requestStr + mark + remoteAddress + "  |mailTo:" + mailVO.getMailToStr() + "  |ccMailTo:"
                + mailVO.getCcMailToStr() + "  |isDefaultMailBox:" + mailVO.getIsDefaultMailBoxStr()
                + "\r\n               |hostMailAddress:" + mailVO.getHostMailAddress() + "  |isSmtpAuth:"
                + mailVO.getIsSmtpAuthStr() + "  |hostMailLoginName:" + mailVO.getHostMailLoginName()
                + "\r\n               |mailServerHost:" + mailVO.getMailServerHost() + "  |mailSubject:"
                + mailVO.getMailSubject() + "  |mailContent:" + mailVO.getMailContent() + "  |mailSendTime:"
                + mailVO.getMailSendTimeStr() + "  |fileNumber:" + mailVO.getFileNumberStr();
        LOG.info(result);
        return ErrorCode.SUCCESS;
    }

    public void makeLog(String remoteAddress, String mailContent, String mailSubject, String mailTo, String ccMailTo,
        String mqName) {
        String result =
            requestStr + mark + remoteAddress + "  |mailTo:" + mailTo + "  |ccMailTo:" + ccMailTo + "  |mailSubject:"
                + mailSubject + "  |mailContent:" + mailContent;
        LOG.info(result);
    }

    /**
     * 用于记录每一个访问本服务的response内容
     * @param result 处理结果信息
     * @return String 处理结果
     */
    public String makeResponseLog(String result) {
        String resultInfo = "response:" + mark + result;
        LOG.info(resultInfo);
        return ErrorCode.SUCCESS;
    }

    /**
     * 当服务被调用，在初步解析阶段发生错误时，只记录服务被调用的IP地址信息
     * @param remoteAddress 访问服务的Ip信息
     * @return String 处理结果
     */
    public String makeErrorLog(String remoteAddress) {
        String result = requestStr + mark + remoteAddress;
        LOG.info(result);
        return ErrorCode.SUCCESS;
    }
}
