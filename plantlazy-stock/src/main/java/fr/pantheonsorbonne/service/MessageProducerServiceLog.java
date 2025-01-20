package fr.pantheonsorbonne.service;

import fr.pantheonsorbonne.dto.MessageLogDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.ProducerTemplate;


@ApplicationScoped
public class MessageProducerServiceLog {

    @Inject
    ProducerTemplate producerTemplate;

    public void sendMessageToRoute(String endpointUri, MessageLogDTO messageLogDTO) {
        Exchange exchange = producerTemplate.getCamelContext().getEndpoint(endpointUri).createExchange();
        Message message = exchange.getIn();

        message.setBody(messageLogDTO);

        producerTemplate.send(endpointUri, exchange);
    }
}
