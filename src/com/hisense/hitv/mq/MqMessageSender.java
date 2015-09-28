package com.hisense.hitv.mq;

import java.util.Map;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.Session;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

/**
 * 将邮件发送的结果信息返回给调用网元
 * @author wangheping
 */
public class MqMessageSender {
    private static final Log LOG = LogFactory.getLog(MqMessageSender.class);
    private JmsTemplate jmsTemplate;
    private String mqSenderName;

    /**
     * 默认构造体
     */
    public MqMessageSender() {
    }

    /**
     * 构造体
     * @param jmsTemplate 消息模板
     */
    public MqMessageSender(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    /**
     * 实现与ActiveMQ的信息发送功能
     * @param destPath 目标路径
     * @param sourcePath 源路径
     * @param destFileName 目标文件名
     * @param sourceFileName 原文件名
     * @param length 高度
     * @param width 宽度
     * @param format 目标文件格式
     * @throws org.springframework.jms.JmsException 异常
     */
    public void sendMqMessage(final Map<String, String> resultMap) throws org.springframework.jms.JmsException {
        jmsTemplate.setDefaultDestinationName(mqSenderName);
        jmsTemplate.send(new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                MapMessage message = session.createMapMessage();
                for (String str : resultMap.keySet()) {
                    message.setString(str, resultMap.get(str));
                }
                return message;
            }
        });
        LOG.info("sent Reply message to mailSender over!");
    }

    /**
     * @return the jmsTemplate
     */
    public JmsTemplate getJmsTemplate() {
        return jmsTemplate;
    }

    /**
     * @param jmsTemplate the jmsTemplate to set
     */
    public void setJmsTemplate(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    /**
     * @return the mqSenderName
     */
    public String getMqSenderName() {
        return mqSenderName;
    }

    /**
     * @param mqSenderName the mqSenderName to set
     */
    public void setMqSenderName(String mqSenderName) {
        this.mqSenderName = mqSenderName;
    }

}
