package ar.com.colo.camel.route;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;

public class JmsMongoRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		from("activemq:queue:mongo.QUEUE")
			.log("Message Consumed")
			.marshal()
			.json(JsonLibrary.Jackson)
			.log("Message Mashalled")
			.to("jmsToMongoProcessor")
			.log("Message Processed")
			.to("mongodb:dataBaseBean?database=mongoTest&collection=testCollection&operation=save")
			.log("Message Persisted")
		.end();

	}

}
