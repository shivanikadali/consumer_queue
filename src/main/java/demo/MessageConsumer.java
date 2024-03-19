package demo;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class MessageConsumer {
    private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;
    private static String queueName = "MESSAGE_QUEUE";

    public static void receivingFromQueue() throws JMSException {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createQueue(queueName);

        // messageconsumer is used for receiving based on type/property

        //msg routing based on properties
        javax.jms.MessageConsumer greetingsconsumer = session.createConsumer(destination, "Type='greetings'");
        javax.jms.MessageConsumer consumer = session.createConsumer(destination, "Type='information'");

        // we receive the message
        Message message=consumer.receive();

        //process 
            if (message instanceof TextMessage testMessage) {
                System.out.println("the information is  " + testMessage.getText() + "'");
            }
        
        Message message2=greetingsconsumer.receive();
            if (message2 instanceof TextMessage testMessage) {
                System.out.println("Receives the greetings  " + testMessage.getText() + "'");
            }
        connection.close();
    }

}

/*
 * 
 * // messageconsumer is used for receiving
 * javax.jms.MessageConsumer consumer = session.createConsumer(destination);
 * 
 * // we receive the message
 * Message message = consumer.receive();
 * if (message instanceof TextMessage testMessage) {
 * System.out.println("Received the message ' " + testMessage.getText() + "'");
 * }
 * connection.close();
 */
