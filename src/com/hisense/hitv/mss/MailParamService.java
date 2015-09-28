package com.hisense.hitv.mss;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Hashtable;

import javax.servlet.ServletInputStream;

import com.hisense.hitv.utils.ErrorCode;

/**
 * 文件上传类,对文件进行上传,最多可同时上传255个文件 将从客户端传来的文件保存在byte[]中，便于其他用途
 * 比如直接保存在数据库或加入发送邮件的附件中， 就不需要临时文件了
 * @author Edwyn Wang
 */
public class MailParamService {
    private long  totalFileSize = 0L;

    /**
     * 255用于限定数组大小
     */
    private final int arraySize = 255;
    /**
     * 4096用于流缓冲大小
     */
    private final int streamBufferSize = 4096;
    /**
     * 1024
     */
    private final int kSize = 1024;
    /**
     * 45
     */
    private final int formMarkSize = 45;
    /**
     * 10
     */
    private final int suffixSize = 10;
    /**
     * 0
     */
    private final int numberZero = 0;
    /**
     * 1
     */
    private final int numberOne = 1;
    /**
     * 2
     */
    private final int numberTwo = 2;
    /**
     * 3
     */
    private final int numberThree = 3;
    /**
     * 4
     */
    private final int numberFour = 4;
    /**
     * 6
     */
    private final int numberSix = 6;
    /**
     * .
     */
    private final String suffixPoint = ".";
    /**
     * name="
     */
    private final String suffixName = "name=\"";
    /**
     * name="
     */
    private final String suffixFileName = "filename=\"";
    // 源文件名
    private String[] sourceFile = new String[arraySize];
    /**
     * UTF-8字符串
     */
    private final String utf8 = "UTF-8";
    // 文件后缀名
    private String[] suffix = new String[arraySize];
    private final String suffixAxtar = "*";
    // ".gif.jpg.png"; //可上传的文件后缀名,"*"表示可以上传任何文件
    private String canSuffix = suffixAxtar;

    // 描述状态
    private String[] description = new String[arraySize];
    // 限制大小,单位M，由Spring注入
    private String size;
    // 已传输文件数目
    private int count = 0;
    // 字节流存放数组
    private byte[] b = new byte[streamBufferSize];
    // 存放上传文件内容为byte[]的一个数组
    private Object[] filebyte = new Object[arraySize];
    private boolean successful = true;
    private Hashtable<String, String> fields = new Hashtable<String, String>();
    private MailVO mailVO;

    /**
     * 
     */
    public MailParamService() {
    }

    /**
     * @return the mailVO
     */
    public MailVO getMailVO() {
        return mailVO;
    }

    /**
     * @param mailVO the mailVO to set
     */
    public void setMailVO(MailVO mailVO) {
        this.mailVO = mailVO;
    }

    /**
     * @param size the size to set
     */
    public void setSize(String size) {
        this.size = size;
    }

    /**
     * 设置上传文件的后缀名
     * @param canSuffix 文件后缀名参数
     */
    public void setSuffix(String canSuffix) {
        this.canSuffix = canSuffix;
    }

    /**
     * 文件上传与表单各个参数取得程序
     * @param sis Servlet数据流
     * @return String
     * @throws IOException 异常
     */
    public String setSourceFile(ServletInputStream sis) throws IOException {

        int a = 0;
        int k = 0;
        String s = "";
        while ((a = sis.readLine(b, 0, b.length)) != -1) {
            s = new String(b, 0, a, utf8);
            if ((s.indexOf(suffixFileName)) != -1) {
                k = s.indexOf(suffixFileName);
                // 取得文件数据
                s = s.substring(k + suffixSize);
                k = s.indexOf("\"");
                s = s.substring(0, k);
                sourceFile[count] = s;
                k = s.lastIndexOf(suffixPoint);
                suffix[count] = s.substring(k + 1);
                if (canTransfer(count)) {
                    byte[] byteN = transferFile(count, sis);
                    if (byteN != null) {
                        filebyte[count] = byteN;
                    } else {
                        return ErrorCode.FILE_TOO_LARGE;
                    }
                }
                ++count;
            } else if ((s.indexOf(suffixName)) != -1) {
                k = s.indexOf(suffixName);
                // 普通表单输入元素，获取输入元素名字
                String fieldName = s.substring(k + numberSix, s.length() - numberThree);
                sis.readLine(b, 0, b.length);
                StringBuffer fieldValue = new StringBuffer(b.length);
                while ((a = sis.readLine(b, 0, b.length)) != -1) {
                    s = new String(b, 0, a - 2, utf8);
                    if ((b[numberZero] == formMarkSize) && (b[numberOne] == formMarkSize)
                        && (b[numberTwo] == formMarkSize) && (b[numberThree] == formMarkSize)
                        && (b[numberFour] == formMarkSize)) {
                        break;
                    } else {
                        fieldValue.append(s);
                    }
                }
                fields.put(fieldName, fieldValue.toString());
                if (fields != null && fields.size() != 0) {
                    mailVO = makeMailVOFromFieldMap(fields);
                } else {
                    return ErrorCode.INTENAL_ERR;
                }
            }
            if (!successful) {
                return ErrorCode.FILE_TOO_LARGE;
            }
        }
        return ErrorCode.SUCCESS;
    }

    /**
     * 从参数，参数值Map构建邮件实体
     * @param map 包含参数，参数值内容的map
     * @return Mail实体
     */
    private MailVO makeMailVOFromFieldMap(Hashtable<String, String> map) {
        MailVO mail = new MailVO();
        mail.setFileNumberStr(map.get("fileNumber"));
        // 收件人
        mail.setMailToStr(map.get("mailTo"));
        // 是否使用默认邮箱
        mail.setIsDefaultMailBoxStr(map.get("isDefaultMailBox"));
        // 发件人地址
        mail.setHostMailAddress(map.get("hostMailAddress"));
        // 发件人
        mail.setMailSender(map.get("mailSender"));
        // SMTP认证
        mail.setIsSmtpAuthStr(map.get("isSmtpAuth"));
        // 邮箱用户名
        mail.setHostMailLoginName(map.get("hostMailLoginName"));
        // 邮箱密码
        mail.setHostMailPin(map.get("hostMailPin"));
        // 发件箱HOST ADDRESS
        mail.setMailServerHost(map.get("mailServerHost"));
        // 邮件主题
        mail.setMailSubject(map.get("mailSubject"));
        // 邮件内容
        mail.setMailContent(map.get("mailContent"));
        // 抄送地址
        mail.setCcMailToStr(map.get("ccMailTo"));
        // 邮件发送时间
        mail.setMailSendTimeStr(map.get("mailSendTime"));
        return mail;
    }

    /**
     * 取得上传文件数
     * @return int
     */
    public int getCount() {
        return count;
    }

    /**
     * 获取附件文件流
     * @return String[]
     */
    public String[] getSourceFile() {
        return sourceFile;
    }

    /**
     * 取得上传状态描述
     * @return String[]
     */
    public String[] getDescription() {
        return description;
    }

    /**
     * 取得上传文件的字节流，使用byte[]存储。
     * @return Object[]
     */
    public Object[] getFilebyte() {
        return filebyte;
    }

    /**
     * 取得上传文件的后缀名。
     * @return String[]
     */
    public String[] getSuffix() {
        return suffix;
    }

    // 判断上传文件的类型
    private boolean canTransfer(int i) {
        suffix[i] = suffix[i].toLowerCase();
        // 这个是用来传图片的，各位可以把后缀名改掉或者不要这个条件
        if ((sourceFile[i].equals(""))) {
            return false;
            // 可以上传任何类型文件
        } else if (canSuffix.equals(suffixAxtar)) {
            return true;
        } else if (!(canSuffix.indexOf(suffixPoint + suffix[i]) >= 0)) {
            description[i] = "ERR: File suffix is wrong.";
            return false;
        } else {
            return true;
        }

    }

    // 上传文件转换
    private byte[] transferFile(int i, ServletInputStream sis) {
        ByteArrayOutputStream bufoutstream = new ByteArrayOutputStream();
        try {
            int a = 0;
            long hastransfered = 0; // 表示已经传输的字节数
            String s = "";
            while ((a = sis.readLine(b, 0, b.length)) != -1) {
                s = new String(b, 0, a);

                if ((s.indexOf("Content-Type:")) != -1) {
                    break;
                }
            }
            sis.readLine(b, 0, b.length);

            while ((a = sis.readLine(b, 0, b.length)) != -1) {
                s = new String(b, 0, a);
                if ((b[numberZero] == formMarkSize) && (b[numberOne] == formMarkSize)
                    && (b[numberTwo] == formMarkSize) && (b[numberThree] == formMarkSize)
                    && (b[numberFour] == formMarkSize)) {
                    break;
                }
                bufoutstream.write(b, 0, a);
                hastransfered += a;
                if (hastransfered >= Long.parseLong(size) * kSize * kSize) {
                    description[count] =
                        "ERR: The file " + sourceFile[count] + " is too large.Whole process is interrupted.";
                    successful = false;
                    break;
                }
            }
            totalFileSize = totalFileSize + hastransfered;
            if (totalFileSize >= Long.parseLong(size) * kSize * kSize) {
                description[count] = "Right: The total files are too big";
                successful = false;
                sis.close();
                return null;
            }
            if (successful) {
                description[count] = "Right: The file " + sourceFile[count] + " has been transfered successfully.";
            }

            if (!successful) {
                sis.close();
                return null;
            }
        } catch (IOException e) {
            description[i] = e.toString();
            return null;
        } catch (NumberFormatException e) {
            description[i] = e.toString();
            return null;
        }
        return bufoutstream.toByteArray();
    }
}
