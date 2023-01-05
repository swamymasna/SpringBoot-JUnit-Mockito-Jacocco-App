package com.swamy.test.rest.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.swamy.model.Company;
import com.swamy.service.ICompanyService;

@WebMvcTest
public class CompanyRestControllerTest {

	@MockBean
	private ICompanyService companyService;

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testSaveCompany01() throws Exception {

		Company company = Company.builder()
				.cmpId(1)
				.cmpName("SUZUKI")
				.cmpProf(56000.00)
				.build();

		ObjectMapper mapper = new ObjectMapper();
		String jsonCmp = mapper.writeValueAsString(company);

		when(companyService.saveCompany(company)).thenReturn(company.getCmpId());
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/company")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonCmp);
		ResultActions perform = mockMvc.perform(requestBuilder);
		MvcResult mvcResult = perform.andReturn();
		MockHttpServletResponse response = mvcResult.getResponse();
		int status = response.getStatus();
		assertEquals(201, status);
		
	}
	
	@Test
	public void testSaveCompany02() throws Exception {

		Company company = Company.builder()
				.cmpId(1)
				.cmpName("SUZUKI")
				.cmpProf(56000.00)
				.build();

		ObjectMapper mapper = new ObjectMapper();
		String jsonCmp = mapper.writeValueAsString(company);

		when(companyService.saveCompany(company)).thenReturn(null);
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/company")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonCmp);
		ResultActions perform = mockMvc.perform(requestBuilder);
		MvcResult mvcResult = perform.andReturn();
		MockHttpServletResponse response = mvcResult.getResponse();
		int status = response.getStatus();
		assertEquals(400, status);
		
	}
	
	@Test
	public void testGetAllCompanies01() throws Exception {
		
		Company company = Company.builder()
				.cmpId(1)
				.cmpName("SUZUKI")
				.cmpProf(56000.00)
				.build();
		
		List<Company>list = new ArrayList<>();
		list.add(company);
		
		when(companyService.getAllCompanies()).thenReturn(list);
		
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/company");
		ResultActions perform = mockMvc.perform(requestBuilder);
		MvcResult mvcResult = perform.andReturn();
		MockHttpServletResponse response = mvcResult.getResponse();
		int status = response.getStatus();
		assertEquals(200, status);
	}
	
	@Test
	public void testGetAllCompanies02() throws Exception {
		
		Company company = Company.builder()
				.cmpId(1)
				.cmpName("SUZUKI")
				.cmpProf(56000.00)
				.build();
		
		List<Company>list = new ArrayList<>();
		list.add(company);
		
		when(companyService.getAllCompanies()).thenReturn(null);
		
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/company");
		ResultActions perform = mockMvc.perform(requestBuilder);
		MvcResult mvcResult = perform.andReturn();
		MockHttpServletResponse response = mvcResult.getResponse();
		int status = response.getStatus();
		assertEquals(400, status);
	}
	
	@Test
	public void testGetOneCompany01() throws Exception {
		
		Company company = Company.builder()
				.cmpId(1)
				.cmpName("SUZUKI")
				.cmpProf(56000.00)
				.build();
		
		when(companyService.getOneCompany(company.getCmpId())).thenReturn(company);
		
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/company/1");
		ResultActions perform = mockMvc.perform(requestBuilder);
		MvcResult mvcResult = perform.andReturn();
		MockHttpServletResponse response = mvcResult.getResponse();
		int status = response.getStatus();
		assertEquals(200, status);
	}
	
	@Test
	public void testGetOneCompany02() throws Exception {
		
		Company company = Company.builder()
				.cmpId(1)
				.cmpName("SUZUKI")
				.cmpProf(56000.00)
				.build();
		
		when(companyService.getOneCompany(company.getCmpId())).thenReturn(null);
		
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/company/1");
		ResultActions perform = mockMvc.perform(requestBuilder);
		MvcResult mvcResult = perform.andReturn();
		MockHttpServletResponse response = mvcResult.getResponse();
		int status = response.getStatus();
		assertEquals(400, status);
	}
	
	@Test
	public void testDeleteCompanyById01() throws Exception {
		
		Company company = Company.builder()
				.cmpId(1)
				.cmpName("SUZUKI")
				.cmpProf(56000.00)
				.build();
		
		ObjectMapper mapper = new ObjectMapper();
		String cmpJson = mapper.writeValueAsString(company);
		
		when(companyService.getOneCompany(company.getCmpId())).thenReturn(company);
		companyService.deleteCompanyById(company.getCmpId());
		
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/company/1")
		.contentType(MediaType.APPLICATION_JSON)
		.content(cmpJson);
		
		ResultActions perform = mockMvc.perform(requestBuilder);
		MvcResult mvcResult = perform.andReturn();
		MockHttpServletResponse response = mvcResult.getResponse();
		int status = response.getStatus();
		assertEquals(200, status);
	}
	
	@Test
	public void testDeleteCompanyById02() throws Exception {
		
		Company company = Company.builder()
				.cmpId(1)
				.cmpName("SUZUKI")
				.cmpProf(56000.00)
				.build();
		
		ObjectMapper mapper = new ObjectMapper();
		String cmpJson = mapper.writeValueAsString(company);
		
		when(companyService.getOneCompany(company.getCmpId())).thenReturn(null);
		companyService.deleteCompanyById(company.getCmpId());
		
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/company/1")
		.contentType(MediaType.APPLICATION_JSON)
		.content(cmpJson);
		
		ResultActions perform = mockMvc.perform(requestBuilder);
		MvcResult mvcResult = perform.andReturn();
		MockHttpServletResponse response = mvcResult.getResponse();
		int status = response.getStatus();
		assertEquals(400, status);
	}
	
	@Test
	public void testUpdateCompany01() throws Exception {
		
		Company company = Company.builder()
				.cmpId(1)
				.cmpName("SUZUKI")
				.cmpProf(56000.00)
				.build();
		
		ObjectMapper mapper = new ObjectMapper();
		String cmpJson = mapper.writeValueAsString(company);
		
		when(companyService.getOneCompany(company.getCmpId())).thenReturn(company);
		companyService.updateCompanyById(company);
		
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/company")
		.contentType(MediaType.APPLICATION_JSON)
		.content(cmpJson);
		
		ResultActions perform = mockMvc.perform(requestBuilder);
		MvcResult mvcResult = perform.andReturn();
		MockHttpServletResponse response = mvcResult.getResponse();
		int status = response.getStatus();
		assertEquals(200, status);
	}
	
	@Test
	public void testUpdateCompany02() throws Exception {
		
		Company company = Company.builder()
				.cmpId(1)
				.cmpName("SUZUKI")
				.cmpProf(56000.00)
				.build();
		
		ObjectMapper mapper = new ObjectMapper();
		String cmpJson = mapper.writeValueAsString(company);
		
		when(companyService.getOneCompany(company.getCmpId())).thenReturn(null);
		companyService.updateCompanyById(company);
		
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/company")
		.contentType(MediaType.APPLICATION_JSON)
		.content(cmpJson);
		
		ResultActions perform = mockMvc.perform(requestBuilder);
		MvcResult mvcResult = perform.andReturn();
		MockHttpServletResponse response = mvcResult.getResponse();
		int status = response.getStatus();
		assertEquals(400, status);
	}
}









