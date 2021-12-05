package ukma.hrytsiuk.library.config;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.remoting.service.AmqpInvokerServiceExporter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ukma.hrytsiuk.library.amqp.config.AMQPSettings;
import ukma.hrytsiuk.library.amqp.rpc.RPCBookServiceInterface;

@Configuration
public class RPCConfig {

    @Bean
    Queue queue() {
        return new Queue(AMQPSettings.QUEUE_NAME);
    }

    @Bean
    AmqpInvokerServiceExporter exporter(RPCBookServiceInterface implementation, AmqpTemplate template) {
        AmqpInvokerServiceExporter exporter = new AmqpInvokerServiceExporter();
        exporter.setServiceInterface(RPCBookServiceInterface.class);
        exporter.setService(implementation);
        exporter.setAmqpTemplate(template);
        return exporter;
    }

    @Bean
    SimpleMessageListenerContainer listener(
            ConnectionFactory factory, AmqpInvokerServiceExporter exporter, Queue queue
    ) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(factory);
        container.setMessageListener(exporter);
        container.setQueueNames(queue.getName());
        return container;
    }

}
