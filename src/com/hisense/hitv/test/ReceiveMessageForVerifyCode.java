package com.hisense.hitv.test;

import com.hisense.hitv.sms.client.JsonReqClient;
import com.hisense.hitv.utils.MyPropertyPlaceholderConfigurerUtil;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.jms.*;

/**
 * create by FelixYin/Paul
 */
public class ReceiveMessageForVerifyCode {

    private static final Log log = LogFactory.getLog(ReceiveMessageForVerifyCode.class);

    private MyPropertyPlaceholderConfigurerUtil properties;

    public MyPropertyPlaceholderConfigurerUtil getProperties() {
        return properties;
    }

    public void setProperties(MyPropertyPlaceholderConfigurerUtil properties) {
        this.properties = properties;
    }

    /**
     * 接收信息
     */
    public void receiveMessage() {
        String url = properties.getProperties().getProperty("mqUrlMessage");//"tcp://192.168.1.133:61616";//properties.getProperties().getProperty("mqUrlMessage");
        String  QUEUE_NAME = properties.getProperties().getProperty("mqqueuename");//"CSS_VERIFY_CODE_RECEIVER1";//properties.getProperties().getProperty("mqqueuename");
        Connection connection = null;
        try {
            try {
                ActiveMQConnectionFactory connectionFactory =
                    new ActiveMQConnectionFactory(url);
                connection = connectionFactory.createConnection();
            } catch (Exception e) {
                e.printStackTrace();
            }
            connection.start();
            Session session =
                connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createQueue(QUEUE_NAME);
            MessageConsumer consumer = session.createConsumer(destination);
            consumeMessagesAndClose(connection, session, consumer);
        } catch (Exception e) {
            log.error(e);
        }
    }

    protected void consumeMessagesAndClose(Connection connection,
        Session session, MessageConsumer consumer) throws JMSException {
        while(true){
            Message message = consumer.receive(1000);
            if (message != null) {
                onMessageHandler(message);
            }
        }
//        System.out.println("Closing connection");
//        consumer.close();
//        session.close();
//        connection.close();
    }

    /**
     * 发送短信
     * @param message
     */
    public void onMessageHandler(Message message) {
        try {
            if (message instanceof MapMessage) {
                MapMessage mapMessage = (MapMessage) message;
                String phone = mapMessage.getString("phone");
//                TODO 发短信，发完不管。
                String accountSid = "8d41f8301363b5ea99294d600d79ab20";
                String token = "25d3464c932c585882a931170223ef88";
                String appId = "0c5b05bd992e40d58e17cf282ede0495";
                String templateId = "3853";
                String code = mapMessage.getString("code");
                System.out.println(code);
                String result = new JsonReqClient().templateSMS(accountSid, token, appId, templateId, phone, code);
                System.out.println(result);
                StringBuffer sb =new StringBuffer( );
                sb.append("{");
                sb.append("\"seqCode\":");
                sb.append(code);
                sb.append(",");
                sb.append("\"phone\":");
                sb.append(phone);
                sb.append(",");
                sb.append("\"result\":");
                sb.append(result.contains("000000"));
                sb.append("}");
                System.out.println(sb);
            }
        } catch (Exception e) {
            log.error(e);
            System.out.println(e);
        }
    }

    /**
     * 启动函数
     * @param args
     */
    public static void main(String args[]) {
        ReceiveMessageForVerifyCode rm = new ReceiveMessageForVerifyCode();
        rm.receiveMessage();
    }
}
