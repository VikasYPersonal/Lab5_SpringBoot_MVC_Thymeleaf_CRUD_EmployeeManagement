package com.gl.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gl.entity.Employee;
import com.gl.service.EmployeeService;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

	@GetMapping("/list")
	public String listEmployee(Model theModel) {

		// get employee from database
		List<Employee> employees = employeeService.findAll();

		// add to the spring model
		theModel.addAttribute("employees", employees);

		return "employees/list-employees";
	}

	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {

		// create model attribute to bind form data
		Employee emp = new Employee();

		theModel.addAttribute("employee", emp);

		return "employees/employee-form";
	}

	@PostMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("empId") int theId, Model theModel) {

		// get the employee from the service
		Employee emp = employeeService.findById(theId);

		// set employee as a model attribute to pre-populate the form
		theModel.addAttribute("employee", emp);

		// send over to our form
		return "employees/employee-update-form";
	}

	@PostMapping("/save")
	public String saveBook(@ModelAttribute("employee") Employee emp) {

		// save the employee
		employeeService.save(emp);

		// use a redirect to prevent duplicate submissions
		return "redirect:/employee/list";
	}

	@PostMapping("/delete")
	public String delete(@RequestParam("empId") int theId) {

		// delete the employee
		employeeService.deleteById(theId);

		// use a redirect to prevent duplicate submissions
		return "redirect:/employee/list";

	}
}
