package ar.com.colo.camel.route;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

public class RestRoute extends RouteBuilder {

	public static final int portNum = 9191;

	@Override
	public void configure() throws Exception {

		// configure to use restlet on localhost with the given port
		restConfiguration().component("restlet").host("localhost")
				.port(portNum);

		rest("/say").id("routeRest").get("/hello").to("direct:helloGET");
		rest("/users").id("routeRest").get("/{id}/basicData")
				.to("direct:basicDataGET");

		from("direct:helloGET").process(new Processor() {

			@Override
			public void process(Exchange exchange) throws Exception {
				exchange.getOut().setBody("Hello World");
			}
		});

		from("direct:basicDataGET").process(new Processor() {

			@Override
			public void process(Exchange exchange) throws Exception {
				
				exchange.getOut().setBody(exchange.getIn().getHeader("id") + ";Summary");
			}
		});

	}

}
