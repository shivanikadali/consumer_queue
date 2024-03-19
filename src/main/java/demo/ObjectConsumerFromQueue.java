package demo;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class ObjectConsumerFromQueue {
    private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;
    private static String queueName = "OBJECT_QUEUE";

    public static void receivingFromQueue() throws JMSException {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createQueue(queueName);
        // messageconsumer is used for receiving
        MessageConsumer consumer = session.createConsumer(destination);
        // we receive the message
        Message message = consumer.receive();
        if (message instanceof ObjectMessage objectMessage) {
            // ObjectMessage objectMessage = (ObjectMessage) message;
            Employee employee = (Employee) objectMessage.getObject();
            System.out.println("Received object " + employee);
            employee.setDesignation("SE2");
            System.out.println("Modified object " + employee);
        }
        connection.close();
        System.out.println("connection is closed");
    }
}
