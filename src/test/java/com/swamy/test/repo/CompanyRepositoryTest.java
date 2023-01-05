package com.swamy.test.repo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.TestPropertySource;

import com.swamy.model.Company;
import com.swamy.repo.CompanyRepository;

@TestMethodOrder(OrderAnnotation.class)
@TestPropertySource("classpath:test.properties")
@DataJpaTest
public class CompanyRepositoryTest {
	
	@Autowired
	private CompanyRepository companyRepository;
	
	@Autowired
	private TestEntityManager entityManager;

	private Company save;
	
	@Order(1)
	@Test
	public void testSaveCompany() {
		
		Company company = Company.builder()
		.cmpName("SUZUKI")
		.cmpProf(56000.00)
		.build();
		
		Company cmp = entityManager.merge(company);
		
		Company company2 = companyRepository.save(cmp);
		assertThat(company2.getCmpId()).isGreaterThan(0);
		System.out.println(company2);
	}
	
	@Order(2)
	@Test
	public void testGetAllCompanies() {
		
		Company company = Company.builder()
		.cmpName("SUZUKI")
		.cmpProf(56000.00)
		.build();
		
		Company cmp = entityManager.merge(company);
		
		List<Company>list = new ArrayList<>();
		list.add(cmp);
		
		list = companyRepository.findAll();
		assertThat(list.size()>0);
		
		System.out.println(list);
	}
	
	@Order(3)
	@Test
	public void testGetOneCompany() {
		
		Company company = Company.builder()
		.cmpName("SUZUKI")
		.cmpProf(56000.00)
		.build();
		
		Company cmp1 = entityManager.merge(company);

		Company cmp2 = companyRepository.save(cmp1);
		
		Company finalObj = entityManager.find(Company.class, cmp2.getCmpId());
		assertTrue(finalObj.getCmpId()>0);
		
		System.out.println(finalObj);
	}
	
	@Order(4)
	@Test
	public void testDeleteCompanyById() {
		
		Company company = Company.builder()
		.cmpName("SUZUKI")
		.cmpProf(56000.00)
		.build();
		
		Company cmp = entityManager.merge(company);
		
		Company finalObj = entityManager.find(Company.class, cmp.getCmpId());
		companyRepository.deleteById(finalObj.getCmpId());
		assertTrue(true);
		
		System.out.println(finalObj);
	}
	
	@Order(5)
	@Test
	public void testUpdateCompany() {
		
		Company company = Company.builder()
		.cmpName("SUZUKI")
		.cmpProf(56000.00)
		.build();
		
		Company cmp = entityManager.merge(company);
		
		Company finalObj = entityManager.find(Company.class, cmp.getCmpId());
		Company company2 = companyRepository.save(finalObj);
		assertThat(company2.getCmpId()>0);
		
		System.out.println(company2);
	}
}