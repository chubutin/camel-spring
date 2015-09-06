package ar.com.colo.camel.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JmsToMongoProcessor implements Processor {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public void process(Exchange exchange) throws Exception {
		logger.debug("Processor JmsToMongoProcessor with body {}", exchange.getIn().getBody());
		String body = exchange.getIn().getBody(String.class);
		
		exchange.getOut().setBody(body);

	}
}
