package ar.com.colo.camel.route;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CamelFileRoute extends RouteBuilder {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public void configure() {
		from("file://src/test/resources/data/inbox/?delete=true&recursive=true")
				.process(new Processor() {
					@Override
					public void process(Exchange exchange) throws Exception {
						logger.debug("El nombre del archivo procesado es {}",
								exchange.getIn().getHeader(Exchange.FILE_NAME));
					}
				})
				.to("file://src/test/resources/data/outbox/");
	}

}
