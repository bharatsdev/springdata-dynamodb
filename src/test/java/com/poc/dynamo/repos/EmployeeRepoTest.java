package com.poc.dynamo.repos;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.poc.PocDynamoApplication;
import com.poc.dynamo.model.EmpInfo;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringBootTest(classes = PocDynamoApplication.class)
@TestPropertySource(properties = { "amazon.aws.accesskey=key", "amazon.aws.secretkey=key1" })
class EmployeeRepoTest {

	@Autowired
	EmployeeRepo employeeRepo;

	@Test
	public void givenItemWithNameandAddress_whenRunFindAll_thenItemIsFound() {
		EmpInfo employee = new EmpInfo("Test,", "Rampur", true);
		employeeRepo.save(employee);
		List<EmpInfo> result = (List<EmpInfo>) employeeRepo.findAll();
		result.forEach(x -> System.out.println(x.toString()));

//		assertThat(result.size(), is(greaterThan(0)));
//		assertThat(result.get(0).getCost(), is(equalTo(EXPECTED_COST)));
	}
}
