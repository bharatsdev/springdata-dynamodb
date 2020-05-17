/**
 * 
 */
package com.poc.service;

import java.util.Optional;

import com.poc.dynamo.model.EmpInfo;

/**
 * @author Bharat2010
 *
 */
public interface EmployeeService {
	Optional<EmpInfo> findByEmpId(String empId);

	Iterable<EmpInfo> findAll();

	EmpInfo save(EmpInfo empInfo);

}
