/**
 * 
 */
package com.poc.dynamo.repos;

/**
 * @author Bharat2010
 *
 */

import java.util.Optional;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import com.poc.dynamo.model.EmpInfo;

@EnableScan
public interface EmployeeRepo extends CrudRepository<EmpInfo, String> {
	Optional<EmpInfo> findByEmpId(String empId);

}
