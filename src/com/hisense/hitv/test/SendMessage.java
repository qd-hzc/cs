package com.hisense.hitv.test;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.MessageProducer;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;

// 发送TextMessage
public class SendMessage {

    private static final String url = "tcp://192.168.1.133:61616";;
    private static final String QUEUE_NAME = "MAIL_PROCESS_REQUEST1";
    protected String expectedBody = "<hello>world!</hello>";

    public void sendMessage() throws JMSException {
        Connection connection = null;
        try {
            ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
            connection = connectionFactory.createConnection();
            connection.start();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createQueue(QUEUE_NAME);
            MessageProducer producer = session.createProducer(destination);
            MapMessage mapMessage = session.createMapMessage();
            mapMessage.setString("mailContent", "不是他宇宙无敌第一个小战士"+(System.currentTimeMillis()-2));
            mapMessage.setString("mailSubject", "这是一封测试邮件，第二封"+System.currentTimeMillis());
            mapMessage.setString("mailTo", "wanghp@topedu.info");
            //mapMessage.setString("ccMailTo", "wanghp@topedu.info");
            mapMessage.setString("mqName", "122121212121");
            producer.send(mapMessage);
            System.out.println("邮件已经发送");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
    }

    public static void main(String args[]) throws JMSException {
        SendMessage a = new SendMessage();
        a.sendMessage();
//        while (true) {
//            a.sendMessage();
//            try {
//                Thread.currentThread().sleep(30000);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
    }
}
