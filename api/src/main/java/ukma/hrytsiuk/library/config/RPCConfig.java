package ukma.hrytsiuk.library.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.remoting.client.AmqpProxyFactoryBean;
import org.springframework.amqp.remoting.service.AmqpInvokerServiceExporter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ukma.hrytsiuk.library.amqp.config.AMQPSettings;
import ukma.hrytsiuk.library.amqp.rpc.RPCBookServiceInterface;

@Configuration
public class RPCConfig {

    private static final String REMOTING_EXCHANGE = "remoting.exchange";
    private static final String REMOTING_BINDING = "remoting.binding";

    @Bean
    Queue queue() {
        return new Queue(AMQPSettings.QUEUE_NAME);
    }

    @Bean
    DirectExchange exchange() {
        return new DirectExchange(REMOTING_EXCHANGE);
    }

    @Bean
    Binding binding(Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(REMOTING_BINDING);
    }

    @Bean
    RabbitTemplate amqpTemplate(ConnectionFactory factory) {
        RabbitTemplate template = new RabbitTemplate(factory);
        template.setRoutingKey(REMOTING_BINDING);
        template.setExchange(REMOTING_EXCHANGE);
        return template;
    }

    @Bean
    AmqpProxyFactoryBean amqpFactoryBean(AmqpTemplate amqpTemplate) {
        AmqpProxyFactoryBean factoryBean = new AmqpProxyFactoryBean();
        factoryBean.setServiceInterface(RPCBookServiceInterface.class);
        factoryBean.setAmqpTemplate(amqpTemplate);
        return factoryBean;
    }

}
