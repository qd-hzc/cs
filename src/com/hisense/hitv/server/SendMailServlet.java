package com.hisense.hitv.server;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.mail.AuthenticationFailedException;
import javax.mail.MessagingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.hisense.hitv.mss.LogHandler;
import com.hisense.hitv.mss.MailSendService;
import com.hisense.hitv.mss.MailParamService;
import com.hisense.hitv.mss.MailVO;
import com.hisense.hitv.utils.ErrorCode;
import com.hisense.hitv.utils.MailVOUtils;
import com.hisense.hitv.utils.Params;
import com.sun.mail.smtp.SMTPSendFailedException;

/**
 * 邮件发送的服务类，实际上是一个servlet，真正实现服务功能
 * @author wangheping
 */
public class SendMailServlet extends HttpServlet {

    private static final Log LOG = LogFactory.getLog(SendMailServlet.class);
    private static final long serialVersionUID = 290053520185043817L;
    private static final String log_content = "When Making Request Info Logs,Error Occored!";

    private void returnVlueMakeLog(String result, PrintWriter out, LogHandler loghandler) {
        loghandler.makeResponseLog(result);
        out.print(result);
    }

    /**
     *{@inheritDoc}
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        // 基本信息LOG记录器
        LogHandler loghandler = new LogHandler();
        PrintWriter out = response.getWriter();
        ServletContext application;
        application = getServletContext();
        // 系统配置文件中的几个参数
        Params params = (Params) WebApplicationContextUtils.getWebApplicationContext(application).getBean("params");
        MailParamService mailParamService = new MailParamService();
        // 设置附件最大值
        mailParamService.setSize(params.getSize());
        // 设置附件允许类型
        mailParamService.setSuffix("*");
        // Servlet输入流
        ServletInputStream sis = request.getInputStream();

        String result = mailParamService.setSourceFile(sis);
        if (!result.equals(ErrorCode.SUCCESS)) {
            String logResult = loghandler.makeErrorLog(request.getRemoteAddr());
            if (!logResult.equals(ErrorCode.SUCCESS)) {
                LOG.error(log_content);
            }
            loghandler.makeResponseLog(result);
            out.print(result);
            return;
        }

        MailVO mailVO = mailParamService.getMailVO();
        // 设置从配置文件读取进来的参数信息
        MailVOUtils.setMailVODefaultValus(mailVO, params);
        
        String logResult = loghandler.makeLog(request.getRemoteAddr(), mailVO);
        if (!logResult.equals(ErrorCode.SUCCESS)) {
            LOG.error(log_content);
        }

        MailSendService mailSendService = new MailSendService();
        try {
            result = mailSendService.setMail(mailVO, params);
            if (!result.equals(ErrorCode.SUCCESS)) {
                returnVlueMakeLog(result, out, loghandler);
                LOG.error("When making prepare for sending mail," + result + "error occored!");
                return;
            }
            Object[] saFilebyte = mailParamService.getFilebyte();
            String[] saSourceFile = mailParamService.getSourceFile();
            int iCount = mailParamService.getCount();
            for (int i = 0; i < iCount; i++) {
                byte[] bb = (byte[]) saFilebyte[i];
                String str = saSourceFile[i];
                File f = new File(str);
                if (bb != null) {
                    mailSendService.addAttachmentFrombyte(bb, f.getName());
                }
            }
            try {
                mailSendService.sendMail();
            } catch (AuthenticationFailedException e) {
                LOG.error(e);
                returnVlueMakeLog(ErrorCode.AUTHENTICATION_FAILED, out, loghandler);
                return;
            } catch (SMTPSendFailedException e) {
                LOG.error(e);
                returnVlueMakeLog(ErrorCode.AUTHENTICATION_PASS_FAILED, out, loghandler);
                return;
            } catch (MessagingException e) {
                LOG.error(e);
                returnVlueMakeLog(ErrorCode.HOST_CONNECT_ERROR, out, loghandler);
                return;
            }
            returnVlueMakeLog(ErrorCode.SUCCESS, out, loghandler);
        } catch (NumberFormatException e) {
            LOG.error(e);
            returnVlueMakeLog(ErrorCode.MAIL_SEND_TIME_ERR, out, loghandler);
        } catch (SMTPSendFailedException e) {
            LOG.error(e);
            returnVlueMakeLog(ErrorCode.SMTP_ERR, out, loghandler);
        } catch (AuthenticationFailedException e) {
            LOG.error(e);
            returnVlueMakeLog(ErrorCode.AUTHENTICATION_FAILED, out, loghandler);
        } catch (MessagingException e) {
            LOG.error(e);
            returnVlueMakeLog(ErrorCode.INTENAL_ERR, out, loghandler);
        } catch (UnsupportedEncodingException e) {
            LOG.error(e);
            returnVlueMakeLog(ErrorCode.CHARACTOR_NOT_SUPPORTTED, out, loghandler);
        }
    }
}
