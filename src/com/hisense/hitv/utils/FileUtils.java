package com.hisense.hitv.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 用于一些基本的文件操作
 * @author wangheping
 */
public class FileUtils {
    private static final Log log = LogFactory.getLog(FileUtils.class);

    /**
     * 复制单个文件
     * @param srcPath 原路径
     * @param destPath 目的路径
     */
    public static void doCopyFile(String srcPath, String destPath) {
        File srcFile = new File(srcPath);
        File destFile = new File(destPath);
        FileInputStream input = null;
        FileOutputStream output = null;
        try {
            input = new FileInputStream(srcFile);
            output = new FileOutputStream(destFile);
            byte[] buffer = new byte[4096];
            int n = 0;
            while (-1 != (n = input.read(buffer))) {
                output.write(buffer, 0, n);
            }
        } catch (IOException e) {
            log.error("when copying file error occured!  FileUtils.doCopyFile", e);
        } finally {
            try {
                if (output != null) {
                    output.close();
                }
                if (input != null) {
                    input.close();
                }
            } catch (IOException ioe) {
                log.error("when copying file error occured!   FileUtils.doCopyFile", ioe);
            }
        }
    }

    /**
     * 忽略操作系统的copy文件
     * @param srcPath 要copy的原文件地址
     * @param destPath copy的目标地址
     */
    public static void copyFileIgnoreOS(String srcPath, String destPath) {
        if (Constance.SYSTEM_OS_NAME != null
            && Constance.SYSTEM_OS_NAME.toLowerCase().contains(Constance.WINDOWS_OS_NAME)) {
            doCopyFile(srcPath, destPath);
        } else {
            LinuxUtils.executeCommand(Constance.LINUX_COMMAND_CHMOD755 + srcPath + Constance.SPACE + destPath);
            LinuxUtils.executeCommand(Constance.LINUX_COMMAND_CP + destPath);
        }
    }

}
