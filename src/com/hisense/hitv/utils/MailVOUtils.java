package com.hisense.hitv.utils;

import com.hisense.hitv.mss.MailVO;

public class MailVOUtils {
    public static void setMailVODefaultValus(MailVO mailVO, Params params) {
        // 设置从配置文件读取进来的参数信息
        mailVO.setFileNumberPropertity(params.getFileNumber());
        mailVO.setHostMailAddressPropertity(params.getHostMailAddress());
        mailVO.setMailSenderPropertity(params.getMailSender());
        mailVO.setIsSmtpAuthPropertity(params.getIsSmtpAuth());
        mailVO.setHostMailLoginNamePropertity(params.getHostMailLoginName());
        mailVO.setHostMailPinPropertity(params.getHostMailPin());
        mailVO.setMailServerHostPropertity(params.getMailServerHost());
    }
}
