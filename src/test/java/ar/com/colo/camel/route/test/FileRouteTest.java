package ar.com.colo.camel.route.test;

import java.io.File;

import org.apache.camel.Exchange;
import org.apache.camel.test.spring.CamelSpringTestSupport;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.AbstractXmlApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class FileRouteTest extends CamelSpringTestSupport {

	Logger logger = LoggerFactory.getLogger(getClass());

	protected AbstractXmlApplicationContext createApplicationContext() {
		return new ClassPathXmlApplicationContext(
				"classpath:META-INF/camel/camel-context-FileRoute.xml");
	}

	@Test
	public void testMoveFile() throws Exception {
		template.sendBodyAndHeader("file://src/test/resources/data/inbox",
				"Hello World", Exchange.FILE_NAME, "hello.txt");
		Thread.sleep(1000);
		File target = new File("src/test/resources/data/outbox/hello.txt");
		logger.debug("Path del archivo {}", target.getAbsolutePath());
		assertTrue("File not moved", target.exists());
		String content = context.getTypeConverter().convertTo(String.class,
				target);
		assertEquals("Hello World", content);
	}

	@Override
	public void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();

		// File file = new File("src/test/resources/data/inbox/fileTest.xml");
		// try {
		// file.createNewFile();
		// } catch (IOException e) {
		// Logger.getLogger(getClass()).error("Error al crear el archivo", e);
		// }

	}

	@Override
	public void tearDown() throws Exception {
		// TODO Auto-generated method stub
		super.tearDown();

	}
}
