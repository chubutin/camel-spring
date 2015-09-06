package ar.com.colo.camel.route.test;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

import org.apache.camel.test.spring.CamelSpringTestSupport;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.AbstractXmlApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class RestletRouteTest extends CamelSpringTestSupport {

	Logger logger = LoggerFactory.getLogger(getClass());

	protected AbstractXmlApplicationContext createApplicationContext() {
		return new ClassPathXmlApplicationContext(
				"classpath:META-INF/camel/camel-context-restlet.xml");
	}

	@Test
	public void testHttpGet() {
		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		try {
//			URI uri = URI.create ("/colo/name");
			URI uri = new URI("http", null, "localhost", 80, "/colo/name", null, null);
			HttpGet httpGetRequest = new HttpGet(uri);
			HttpResponse httpResponse = httpClient.execute(httpGetRequest);

			System.out.println("----------------------------------------");
			System.out.println(httpResponse.getStatusLine());
			System.out.println("----------------------------------------");

			HttpEntity entity = httpResponse.getEntity();

			byte[] buffer = new byte[1024];
			if (entity != null) {
				InputStream inputStream = entity.getContent();
				try {
					int bytesRead = 0;
					BufferedInputStream bis = new BufferedInputStream(
							inputStream);
					while ((bytesRead = bis.read(buffer)) != -1) {
						String chunk = new String(buffer, 0, bytesRead);
						System.out.println(chunk);
					}
				} catch (Exception e) {
					e.printStackTrace();
					fail(e.getMessage());
				} finally {
					try {
						inputStream.close();
					} catch (Exception ignore) {
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		} finally {
			try {
				httpClient.close();
			} catch (IOException e) {
				System.out.println("EXCEPTION ON CLOSE CONNECTION");
			}
		}
	}
}
