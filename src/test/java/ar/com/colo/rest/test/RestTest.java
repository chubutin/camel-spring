package ar.com.colo.rest.test;

import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.test.spring.CamelSpringTestSupport;
import org.junit.Test;
import org.springframework.context.support.AbstractXmlApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ar.com.colo.camel.route.RestRoute;

public class RestTest extends CamelSpringTestSupport {

	@Produce(uri = "restlet:http://127.0.0.1:9191/say/hello")
	protected ProducerTemplate templateHello;

	@Produce(uri = "restlet:http://127.0.0.1:9191/say/bye")
	protected ProducerTemplate templateBye;

	protected AbstractXmlApplicationContext createApplicationContext() {
		return new ClassPathXmlApplicationContext(
				"classpath:META-INF/camel/camel-context-restlet.xml");
	}

	@Test
	public void testHelloGet() throws Exception {
		Object response = templateHello.requestBody("http://localhost:"
				+ RestRoute.portNum + "/say/hello", null, String.class);
		assertEquals("Hello World", response);
	}
	
	@Test
	public void testbasicDataGet() throws Exception {
		Object response = templateHello.requestBody("http://localhost:"
				+ RestRoute.portNum + "/users/666/basicData", null, String.class);
		assertEquals("666;Summary", response);
	}

}
