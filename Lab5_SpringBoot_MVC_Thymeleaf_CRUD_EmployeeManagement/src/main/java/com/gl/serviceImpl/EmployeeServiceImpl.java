package com.gl.serviceImpl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gl.dao.EmployeeRepository;
import com.gl.entity.Employee;
import com.gl.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;

	@Override
	public List<Employee> findAll() {
		List<Employee> employees = employeeRepository.findAll();
		return employees;
	}

	@Override
	public Employee findById(int theId) {
		Optional<Employee> result = employeeRepository.findById(theId);
		Employee emp = null;
		if (result.isPresent()) {
			emp = result.get();
		} else {
			throw new RuntimeException("Did not find Emp id - " + theId);
		}
		return emp;
	}

	@Override
	public void save(Employee emp) {
		employeeRepository.save(emp);
	}

	@Override
	public void deleteById(int theId) {
		employeeRepository.deleteById(theId);
	}

}
