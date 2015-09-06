package ar.com.colo.camel.route.test;

import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.spring.CamelSpringTestSupport;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.AbstractXmlApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class JmsRouteTest extends CamelSpringTestSupport {

	Logger logger = LoggerFactory.getLogger(getClass());

	protected AbstractXmlApplicationContext createApplicationContext() {
		return new ClassPathXmlApplicationContext(
				"classpath:META-INF/camel/camel-context-JmsRoute.xml",
				"classpath:META-INF/camel/activemq-context.xml",
				"classpath:META-INF/camel/camel-activemq-context.xml");
	}

	@Test
	public void testSendJms() throws Exception {

		MockEndpoint mockEndpoint = getMockEndpoint("mock:mockEndpoint");
		mockEndpoint.expectedMessageCount(1);
		
		Thread.sleep(5000);
		
		template.requestBody("vm:foo", "Mensaje de prueba");

		mockEndpoint.assertIsSatisfied(1000);

	}
}
