package ar.com.colo.spring.service;

import ar.com.colo.spring.model.Employee;

public class EmployeeService {

	private Employee employee;

	public Employee getEmployee() {
		return this.employee;
	}

	public void setEmployee(Employee e) {
		this.employee = e;
	}
	
}
