/**
 * 
 */
package com.poc.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poc.dynamo.model.EmpInfo;
import com.poc.dynamo.repos.EmployeeRepo;

/**
 * @author Bharat2010
 *
 */
@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {

	final static Logger logger = LoggerFactory.getLogger(EmployeeService.class);
	@Autowired
	EmployeeRepo employeeRepo;

	@Override
	public Optional<EmpInfo> findByEmpId(String empId) {
		return employeeRepo.findById(empId);
	}

	@Override
	public Iterable<EmpInfo> findAll() {
		return employeeRepo.findAll();
	}

	@Override
	public EmpInfo save(EmpInfo empInfo) {
		return employeeRepo.save(empInfo);
	}
}
