package com.swamy.test.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.swamy.model.Company;
import com.swamy.repo.CompanyRepository;
import com.swamy.service.ICompanyService;

@SpringBootTest
public class ICompanyServiceTest {

	@Autowired
	private ICompanyService companyService;
	
	@MockBean
	private CompanyRepository companyRepository;
	
	@Test
	public void testSaveCompany() {
		
		Company company = Company.builder()
		.cmpName("TATA")
		.cmpProf(56000.00)
		.build();
		
		when(companyRepository.save(company)).thenReturn(company);
		Integer cmpId = companyService.saveCompany(company);

		assertEquals(cmpId, company.getCmpId());
	}
	
	@Test
	public void testGetAllCompanies() {
		
		Company company = Company.builder()
		.cmpName("TATA")
		.cmpProf(56000.00)
		.build();
		
		List<Company>list = new ArrayList<Company>();
		list.add(company);
		
		when(companyRepository.findAll()).thenReturn(list);
		List<Company> cmpsList = companyService.getAllCompanies();

		assertEquals(list, cmpsList);
		assertThat(cmpsList.size()).isGreaterThan(0);
		assertTrue(cmpsList.size() >0);
		assertNotNull(cmpsList);
	}
	
	
	@Test
	public void testGetOneCompanyById() {
		
		Company company = Company.builder()
		.cmpName("TATA")
		.cmpProf(56000.00)
		.build();
		
		when(companyRepository.findById(company.getCmpId())).thenReturn(Optional.of(company));
		Company cmpObj = companyService.getOneCompany(company.getCmpId());
		assertEquals(cmpObj.getCmpId(), company.getCmpId());
	}
	
	@Test
	public void testDeleteCompanyById() {
		
		Company company = Company.builder()
		.cmpName("TATA")
		.cmpProf(56000.00)
		.build();
		
		//companyService.deleteCompanyById(company.getCmpId());
		when(companyRepository.findById(company.getCmpId())).thenReturn(Optional.of(company));
		verify(companyRepository,times(1)).deleteById(company.getCmpId());
	}
	
	@Test
	public void testUpdateCompany() {
		
		Company company = Company.builder()
		.cmpName("TATA")
		.cmpProf(56000.00)
		.build();
		
		companyService.updateCompanyById(company);
		when(companyRepository.save(company)).thenReturn(company);
		verify(companyRepository,times(1)).save(company);
		
	}
}










