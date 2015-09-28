package com.hisense.hitv.utils;

import java.io.File;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;

import com.hisense.hitv.common.nms.NMSAdapter;

/**
 * @author wangheping
 */
public class Params implements InitializingBean {
    private static Log log = LogFactory.getLog(Params.class);
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
    // 限制大小,单位M，由Spring注入
    private String size;
    private String encryptionkey;
    private NMSAdapter nmsAdapter;
    private long expireLong;
    private String sqliteRunDBUrl;
    private String sqliteInitDBUrl;

    public String getSqliteRunDBUrl() {
        return sqliteRunDBUrl;
    }

    public void setSqliteRunDBUrl(String sqliteRunDBUrl) {
        this.sqliteRunDBUrl = sqliteRunDBUrl;
    }

    public String getSqliteInitDBUrl() {
        return sqliteInitDBUrl;
    }

    public void setSqliteInitDBUrl(String sqliteInitDBUrl) {
        this.sqliteInitDBUrl = sqliteInitDBUrl;
    }

    public long getExpireLong() {
        return expireLong;
    }

    public void setExpireLong(long expireLong) {
        this.expireLong = expireLong;
    }

    private long connectTimeout;

    public long getConnectTimeout() {
        return connectTimeout;
    }

    public void setConnectTimeout(long connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    /**
     * @return the encryptionkey
     */
    public String getEncryptionkey() {
        return encryptionkey;
    }

    /**
     * @param encryptionkey the encryptionkey to set
     */
    public void setEncryptionkey(String encryptionkey) {
        this.encryptionkey = encryptionkey;
    }

    /**
     * @param fileNumber the fileNumber to set
     */
    public void setFileNumber(String fileNumber) {
        this.fileNumber = fileNumber;
    }

    /**
     * @param hostMailAddress the hostMailAddress to set
     */
    public void setHostMailAddress(String hostMailAddress) {
        this.hostMailAddress = hostMailAddress;
    }

    /**
     * @param mailSender the mailSender to set
     */
    public void setMailSender(String mailSender) {
        this.mailSender = mailSender;
    }

    /**
     * @param isSmtpAuth the isSmtpAuth to set
     */
    public void setIsSmtpAuth(String isSmtpAuth) {
        this.isSmtpAuth = isSmtpAuth;
    }

    /**
     * @param hostMailLoginName the hostMailLoginName to set
     */
    public void setHostMailLoginName(String hostMailLoginName) {
        this.hostMailLoginName = hostMailLoginName;
    }

    /**
     * @param hostMailPin the hostMailPin to set
     */
    public void setHostMailPin(String hostMailPin) {
        this.hostMailPin = hostMailPin;
    }

    /**
     * @param mailServerHost the mailServerHost to set
     */
    public void setMailServerHost(String mailServerHost) {
        this.mailServerHost = mailServerHost;
    }

    /**
     * @param size the size to set
     */
    public void setSize(String size) {
        this.size = size;
    }

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
     * @return the size
     */
    public String getSize() {
        return size;
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
    public void afterPropertiesSet() throws Exception {

        // String sqliteRunFilePath = Constance.SQLITE_RUN_FILE_PATH;
        // String sqliteInitFilePath = Constance.SQLITE_INIT_FILE_PATH;
        // if (!LinuxUtils.isLinuxSystem()) {
        // sqliteRunFilePath = Constance.WIN_SQLITE_RUN_FILE_PATH;
        // sqliteInitFilePath = Constance.WIN_SQLITE_INIT_FILE_PATH;
        // }
        File sqlLiteFile = new File(sqliteRunDBUrl);
        if (!sqlLiteFile.exists()) {
            File sqlLiteInitFile = new File(sqliteInitDBUrl);
            if (!sqlLiteInitFile.exists()) {
                log.error(Constance.SQLITE_INIT_FILE_PATH + " file not exists!");
                // 如果文件不存在的话说明是代码问题，或者编译异常。应该退出执行
            } else {
                FileUtils.copyFileIgnoreOS(sqliteInitDBUrl, sqliteRunDBUrl);
            }
        }
        // nmsAdapter.startMonitor();
        // nmsAdapter.sendConfigParams();
    }

}
