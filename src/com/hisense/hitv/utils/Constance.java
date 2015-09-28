package com.hisense.hitv.utils;

public class Constance {
    public static final String SQLITE_RUN_FILE_PATH = "/home/kylin/tools/apache-tomcat-6.0.37/webapps/cs/data/run/mail.db";
    public static final String SQLITE_INIT_FILE_PATH = "/home/kylin/tools/apache-tomcat-6.0.37/webapps/cs/data/init/mail.db";

    public static final String WIN_SQLITE_RUN_FILE_PATH =
        "/Program Files/workSpace/cs/data/run/mail.db";
    public static final String WIN_SQLITE_INIT_FILE_PATH =
        "/Program Files/workSpace/cs/data/init/mail.db";

    public static final String URL_SEPRATOR = "/";
    public static final String DIR_SEPRATOR = System.getProperty("file.separator");
    public static final String SYSTEM_OS_NAME = System.getProperty("os.name");

    public static final String TEN_NUMBER_REGEX = "\\d{10}";
    public static final String WINDOWS_OS_NAME = "windows";

    public static final String LINUX_COMMAND_CHMOD755 = "cp ";
    public static final String LINUX_COMMAND_CP = "chmod 755 ";
    public static final String SPACE = " ";
    public static final String NMS_BEAN_NAME = "nmsAdapter";
    public static final String LOG4J_STOUT_THREAD_NAME = "log4j.appender.stdout.Threshold";
    public static final String LOG4J_STOUT_CONFIG_FILE_PATH = "/usr/MsgDispatchUnit/config/log4j.properties";
    public static final String FEATURECODE_STRING = "featurecode";
}
