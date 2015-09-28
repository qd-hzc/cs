package com.hisense.hitv.utils;

/**
 * 错误代码处理类
 * @author wangheping
 */
public interface ErrorCode {
    /**
     * 成功
     */
    public static final String SUCCESS = "1000";
    /**
     * 是否使用默认邮箱参数错误
     */
    public static final String IS_DEF_MAILBOX_ERR = "1001";
    /**
     * 发件箱HOST地址不能为空
     */
    public static final String NO_MAIL_HOST = "1002";
    /**
     * 附件过大
     */
    public static final String FILE_TOO_LARGE = "1003";
    /**
     * 附件数量过多
     */
    public static final String TOO_MANY_FILES = "1004";
    /**
     * 邮箱密码无法解密
     */
    public static final String DES_ENCRYPT_ERR = "1005";
    /**
     * 发件箱登录密码不能为空
     */
    public static final String NO_MAIL_LOGIN_PIN = "1006";
    /**
     * 是否SMTP验证参数错误
     */
    public static final String IS_SMTP_AUTH_ERR = "1007";
    /**
     * 收件人参数为空
     */
    public static final String NO_MAIL_RECEIVER = "1008";

    /**
     * 发送时间参数不存在
     */
    public static final String NO_MAIL_SEND_TIME = "1009";
    /**
     * 内部EXCEPTION
     */
    public static final String INTENAL_ERR = "1010";
    /**
     * 发件箱登录名不能为空
     */
    public static final String NO_MAIL_LOGIN_NAME = "1011";
    /**
     * 发送时间参数错误
     */
    public static final String MAIL_SEND_TIME_ERR = "1012";
    /**
     * MSS服务器IP为空
     */
    public static final String NO_MSS_IP = "1013";
    /**
     * log写入失败错误
     */
    public static final String LOG_ERR = "1014";
    /**
     * MSS服务器IP错误或者MSS服务器未开启
     */
    public static final String MSS_IP_ERR = "1015";
    /**
     * 附件文件不存在
     */
    public static final String FILES_NOT_EXIST = "1016";
    /**
     * 邮件不符合MAIL服务器规则，拒绝服务
     */
    public static final String SMTP_ERR = "1017";
    /**
     * 用户名或者密码错误
     */
    public static final String AUTHENTICATION_FAILED = "1018";
    /**
     * 发件人参数不能为空
     */
    public static final String NO_MAIL_SENDER = "1019";
    /**
     * 发件箱地址参数不能为空
     */
    public static final String NO_MAIL_SENDER_ADDRESS = "1020";
    /**
     * 系统环境不支持字符集
     */
    public static final String CHARACTOR_NOT_SUPPORTTED = "1021";
    /**
     * 邮件实体类为空
     */
    public static final String MAILVO_IS_NULL = "1022";
    /**
     * 邮件发送服务器连接超时
     */
    public static final String MAILSENDSERVER_TIMEOUT = "1023";
    /**
     * 收信人地址错误
     */
    public static final String MAIL_TO_PATTERN_ERROR = "1024";
    /**
     * 抄送人地址错误
     */
    public static final String CCMAIL_TO_PATTERN_ERROR = "1025";
    /**
     * 邮箱登录认证失败
     */
    public static final String AUTHENTICATION_PASS_FAILED = "1026";
    /**
     * 发件箱地址格式错误
     */
    public static final String HOST_ADDRESS_PATTERN_ERROR = "1027";
    /**
     * 连接邮件发送服务器失败，可能由于服务器host地址填写错误等导致
     */
    public static final String HOST_CONNECT_ERROR = "1028";
    /**
     * MQ连接错误
     */
    public static final String MQ_EXCEPTION_ERROR = "1029";
}
