package ar.com.colo.service.impl;

import ar.com.colo.service.PersonService;

public class PersonServiceImpl implements PersonService{

	@Override
	public String sayHello() {
		return "hello world!";
	}

	@Override
	public String answerYourName(String name) {
		return new StringBuffer("hello ").append(name).toString();
	}

}
