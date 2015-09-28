package com.hisense.hitv.mss;

import java.util.List;

/**
 * 邮件实体类
 * @author wangheping
 */
public class MailVO {
    private long id;
    private String mailContent;
    private String mailSubject;
    private String mailSender;
    private String hostMailAddress;
    private String hostMailLoginName;
    private String hostMailPin;
    private String mailServerHost;
    private int isDefaultMailBox;
    private int isSmtpAuth;
    private List<String> mailTo;
    private List<String> ccMailTo;
    private List<String> attachment;
    private long mailSendTime;
    private String mailUrl;

    private String mailSendTimeStr;
    private String isDefaultMailBoxStr;
    private String isSmtpAuthStr;
    private String mailToStr;
    private String ccMailToStr;
    private String attachmentStr;
    private String fileNumberStr;

    private String fileNumberPropertity;
    private String hostMailAddressPropertity;
    private String mailSenderPropertity;
    private String isSmtpAuthPropertity;
    private String hostMailLoginNamePropertity;
    private String hostMailPinPropertity;
    private String mailServerHostPropertity;
    private String mqName;
    private long createdDate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMqName() {
        return mqName;
    }

    public void setMqName(String mqName) {
        this.mqName = mqName;
    }

    public long getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(long createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * @return the hostMailAddressPropertity
     */
    public String getHostMailAddressPropertity() {
        return hostMailAddressPropertity;
    }

    /**
     * @param hostMailAddressPropertity the hostMailAddressPropertity to set
     */
    public void setHostMailAddressPropertity(String hostMailAddressPropertity) {
        this.hostMailAddressPropertity = hostMailAddressPropertity;
    }

    /**
     * @return the fileNumberPropertity
     */
    public String getFileNumberPropertity() {
        return fileNumberPropertity;
    }

    /**
     * @param fileNumberPropertity the fileNumberPropertity to set
     */
    public void setFileNumberPropertity(String fileNumberPropertity) {
        this.fileNumberPropertity = fileNumberPropertity;
    }

    /**
     * @return the mailSenderPropertity
     */
    public String getMailSenderPropertity() {
        return mailSenderPropertity;
    }

    /**
     * @param mailSenderPropertity the mailSenderPropertity to set
     */
    public void setMailSenderPropertity(String mailSenderPropertity) {
        this.mailSenderPropertity = mailSenderPropertity;
    }

    /**
     * @return the isSmtpAuthPropertity
     */
    public String getIsSmtpAuthPropertity() {
        return isSmtpAuthPropertity;
    }

    /**
     * @param isSmtpAuthPropertity the isSmtpAuthPropertity to set
     */
    public void setIsSmtpAuthPropertity(String isSmtpAuthPropertity) {
        this.isSmtpAuthPropertity = isSmtpAuthPropertity;
    }

    /**
     * @return the hostMailLoginNamePropertity
     */
    public String getHostMailLoginNamePropertity() {
        return hostMailLoginNamePropertity;
    }

    /**
     * @param hostMailLoginNamePropertity the hostMailLoginNamePropertity to set
     */
    public void setHostMailLoginNamePropertity(String hostMailLoginNamePropertity) {
        this.hostMailLoginNamePropertity = hostMailLoginNamePropertity;
    }

    /**
     * @return the hostMailPinPropertity
     */
    public String getHostMailPinPropertity() {
        return hostMailPinPropertity;
    }

    /**
     * @param hostMailPinPropertity the hostMailPinPropertity to set
     */
    public void setHostMailPinPropertity(String hostMailPinPropertity) {
        this.hostMailPinPropertity = hostMailPinPropertity;
    }

    /**
     * @return the mailServerHostPropertity
     */
    public String getMailServerHostPropertity() {
        return mailServerHostPropertity;
    }

    /**
     * @param mailServerHostPropertity the mailServerHostPropertity to set
     */
    public void setMailServerHostPropertity(String mailServerHostPropertity) {
        this.mailServerHostPropertity = mailServerHostPropertity;
    }

    /**
     * @return the fileNumberStr
     */
    public String getFileNumberStr() {
        return fileNumberStr;
    }

    /**
     * @param fileNumberStr the fileNumberStr to set
     */
    public void setFileNumberStr(String fileNumberStr) {
        this.fileNumberStr = fileNumberStr;
    }

    /**
     * @return the mailSendTimeStr
     */
    public String getMailSendTimeStr() {
        return mailSendTimeStr;
    }

    /**
     * @param mailSendTimeStr the mailSendTimeStr to set
     */
    public void setMailSendTimeStr(String mailSendTimeStr) {
        this.mailSendTimeStr = mailSendTimeStr;
    }

    /**
     * @return the isDefaultMailBoxStr
     */
    public String getIsDefaultMailBoxStr() {
        return isDefaultMailBoxStr;
    }

    /**
     * @param isDefaultMailBoxStr the isDefaultMailBoxStr to set
     */
    public void setIsDefaultMailBoxStr(String isDefaultMailBoxStr) {
        this.isDefaultMailBoxStr = isDefaultMailBoxStr;
    }

    /**
     * @return the isSmtpAuthStr
     */
    public String getIsSmtpAuthStr() {
        return isSmtpAuthStr;
    }

    /**
     * @param isSmtpAuthStr the isSmtpAuthStr to set
     */
    public void setIsSmtpAuthStr(String isSmtpAuthStr) {
        this.isSmtpAuthStr = isSmtpAuthStr;
    }

    /**
     * @return the mailToStr
     */
    public String getMailToStr() {
        return mailToStr;
    }

    /**
     * @param mailToStr the mailToStr to set
     */
    public void setMailToStr(String mailToStr) {
        this.mailToStr = mailToStr;
    }

    /**
     * @return the ccMailToStr
     */
    public String getCcMailToStr() {
        return ccMailToStr;
    }

    /**
     * @param ccMailToStr the ccMailToStr to set
     */
    public void setCcMailToStr(String ccMailToStr) {
        this.ccMailToStr = ccMailToStr;
    }

    /**
     * @return the attachmentStr
     */
    public String getAttachmentStr() {
        return attachmentStr;
    }

    /**
     * @param attachmentStr the attachmentStr to set
     */
    public void setAttachmentStr(String attachmentStr) {
        this.attachmentStr = attachmentStr;
    }

    /**
     * @return the mailContent
     */
    public String getMailContent() {
        return mailContent;
    }

    /**
     * @param mailContent the mailContent to set
     */
    public void setMailContent(String mailContent) {
        this.mailContent = mailContent;
    }

    /**
     * @return the mailSubject
     */
    public String getMailSubject() {
        return mailSubject;
    }

    /**
     * @param mailSubject the mailSubject to set
     */
    public void setMailSubject(String mailSubject) {
        this.mailSubject = mailSubject;
    }

    /**
     * @return the mailSender
     */
    public String getMailSender() {
        return mailSender;
    }

    /**
     * @param mailSender the mailSender to set
     */
    public void setMailSender(String mailSender) {
        this.mailSender = mailSender;
    }

    /**
     * @return the hostMailAddress
     */
    public String getHostMailAddress() {
        return hostMailAddress;
    }

    /**
     * @param hostMailAddress the hostMailAddress to set
     */
    public void setHostMailAddress(String hostMailAddress) {
        this.hostMailAddress = hostMailAddress;
    }

    /**
     * @return the hostMailLoginName
     */
    public String getHostMailLoginName() {
        return hostMailLoginName;
    }

    /**
     * @param hostMailLoginName the hostMailLoginName to set
     */
    public void setHostMailLoginName(String hostMailLoginName) {
        this.hostMailLoginName = hostMailLoginName;
    }

    /**
     * @return the hostMailPin
     */
    public String getHostMailPin() {
        return hostMailPin;
    }

    /**
     * @param hostMailPin the hostMailPin to set
     */
    public void setHostMailPin(String hostMailPin) {
        this.hostMailPin = hostMailPin;
    }

    /**
     * @return the mailServerHost
     */
    public String getMailServerHost() {
        return mailServerHost;
    }

    /**
     * @param mailServerHost the mailServerHost to set
     */
    public void setMailServerHost(String mailServerHost) {
        this.mailServerHost = mailServerHost;
    }

    /**
     * @return the isDefaultMailBox
     */
    public int getIsDefaultMailBox() {
        return isDefaultMailBox;
    }

    /**
     * @param isDefaultMailBox the isDefaultMailBox to set
     */
    public void setIsDefaultMailBox(int isDefaultMailBox) {
        this.isDefaultMailBox = isDefaultMailBox;
    }

    /**
     * @return the isSmtpAuth
     */
    public int getIsSmtpAuth() {
        return isSmtpAuth;
    }

    /**
     * @param isSmtpAuth the isSmtpAuth to set
     */
    public void setIsSmtpAuth(int isSmtpAuth) {
        this.isSmtpAuth = isSmtpAuth;
    }

    /**
     * @return the mailTo
     */
    public List<String> getMailTo() {
        return mailTo;
    }

    /**
     * @param mailTo the mailTo to set
     */
    public void setMailTo(List<String> mailTo) {
        this.mailTo = mailTo;
    }

    /**
     * @return the ccMailTo
     */
    public List<String> getCcMailTo() {
        return ccMailTo;
    }

    /**
     * @param ccMailTo the ccMailTo to set
     */
    public void setCcMailTo(List<String> ccMailTo) {
        this.ccMailTo = ccMailTo;
    }

    /**
     * @return the attachment
     */
    public List<String> getAttachment() {
        return attachment;
    }

    /**
     * @param attachment the attachment to set
     */
    public void setAttachment(List<String> attachment) {
        this.attachment = attachment;
    }

    /**
     * @return the mailSendTime
     */
    public long getMailSendTime() {
        return mailSendTime;
    }

    /**
     * @param mailSendTime the mailSendTime to set
     */
    public void setMailSendTime(long mailSendTime) {
        this.mailSendTime = mailSendTime;
    }

    /**
     * @return the mailUrl
     */
    public String getMailUrl() {
        return mailUrl;
    }

    /**
     * @param mailUrl the mailUrl to set
     */
    public void setMailUrl(String mailUrl) {
        this.mailUrl = mailUrl;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("Id:");
        sb.append(this.getId());
        sb.append(" MailSubject:");
        sb.append(this.getMailSubject() == null ? "null" : this.getMailSubject());
        sb.append(" Mailto:");
        sb.append(this.getMailToStr() == null ? "null" : this.getMailToStr());
        sb.append(" CCMailto:");
        sb.append(this.getCcMailToStr() == null ? "null" : this.getCcMailToStr());
        return sb.toString();
    }

}
