package com.hisense.hitv.utils;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Linux 命令执行工具
 * @author wangheping
 */
public class LinuxUtils {
    private static Log LOG = LogFactory.getLog(LinuxUtils.class);

    private LinuxUtils() {
    }

    /**
     * 执行命令
     * @param command 命令
     */
    public static void executeCommand(String command) {
        if (!System.getProperty("os.name").toLowerCase().contains("windows")) {
            Runtime rt = Runtime.getRuntime();
            try {
                LOG.debug("command is runing。。。。：" + command);
                rt.exec(command);
                LOG.debug("command is over!");
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    /**
     * 判断是否为linux操作系统
     * @return
     */
    public static boolean isLinuxSystem() {
        if (Constance.SYSTEM_OS_NAME != null
            && Constance.SYSTEM_OS_NAME.toLowerCase().contains(Constance.WINDOWS_OS_NAME)) {
            return false;
        } else {
            return true;
        }
    }
}
