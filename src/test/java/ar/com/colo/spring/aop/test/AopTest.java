package ar.com.colo.spring.aop.test;

import junit.framework.TestCase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ar.com.colo.spring.service.EmployeeService;

public class AopTest extends TestCase {
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	public void testAspects() {
		
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(
				"classpath:META-INF/spring/aop.xml");
		EmployeeService employeeService = ctx.getBean("employeeService",
				EmployeeService.class);

		logger.debug(employeeService.getEmployee().getName());

		employeeService.getEmployee().setName("Pankaj");

		try{ 
		
		employeeService.getEmployee().throwException();

		} catch(RuntimeException ex){
			logger.debug("Exception succesful catched in test");
		}
		ctx.close();
	}
}
