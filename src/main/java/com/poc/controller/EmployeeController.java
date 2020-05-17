package com.poc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.poc.dynamo.model.EmpInfo;
import com.poc.service.EmployeeService;

@RestController
public class EmployeeController {

	final static Logger logger = LoggerFactory.getLogger(EmployeeService.class);

	@Autowired
	EmployeeService employeeService;

	@GetMapping("/")
	public String sayHello() {
		return "Hello Dynamodb";
	}

	@GetMapping("emplyee")
	public ResponseEntity<Iterable<EmpInfo>> getEmployee() {
		Iterable<EmpInfo> employes = employeeService.findAll();
		employes.forEach(x -> logger.info(x.toString()));

		return ResponseEntity.ok(employes);
	}

	@PostMapping("emplyee")
	public ResponseEntity<EmpInfo> getEmployee(@RequestBody EmpInfo empInfo) {
		return ResponseEntity.ok(employeeService.save(empInfo));
	}

}
