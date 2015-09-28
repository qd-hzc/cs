package com.hisense.hitv.test;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import org.apache.activemq.ActiveMQConnectionFactory;

public class ReceiveMessage {

    private static final String url = "tcp://10.0.64.129:61616";
    private static final String QUEUE_NAME = "CSS_MAIL_RECEIVER1";

    public void receiveMessage() {
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

        }
    }

    protected void consumeMessagesAndClose(Connection connection,
        Session session, MessageConsumer consumer) throws JMSException {
        for (int i = 0; i < 1;) {
            Message message = consumer.receive(1000);
            if (message != null) {
                i++;
                onMessage(message);
            }
        }
        System.out.println("Closing connection");
        consumer.close();
        session.close();
        connection.close();
    }

    public void onMessage(Message message) {
        try {
            if (message instanceof MapMessage) {
                MapMessage mapMessage = (MapMessage) message;
                String mailContent = mapMessage.getString("resultCode");
                String mailTo = mapMessage.getString("emailTo");
                String ccMailTo = mapMessage.getString("ccMailTo");
                System.out.println("mailContent: " + mailContent);
                System.out.println("mailTo: " + mailTo);
                System.out.println("ccMailTo: " + ccMailTo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {
        ReceiveMessage rm = new ReceiveMessage();
        rm.receiveMessage();
    }
}
