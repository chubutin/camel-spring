package ar.com.colo.camel.route;

import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JmsRoute extends RouteBuilder {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public void configure() {
		from("activemq:queue:incomingOrders").log("Enviando !!!").id("producerJMSRoute");
		
	}
}
