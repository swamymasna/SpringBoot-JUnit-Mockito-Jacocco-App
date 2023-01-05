package com.swamy.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swamy.model.Company;
import com.swamy.service.ICompanyService;

@RestController
@RequestMapping("/company")
public class CompanyRestController {

	@Autowired
	private ICompanyService companyService;

	@PostMapping
	public ResponseEntity<String>saveCompany(@RequestBody Company company){
		ResponseEntity<String>resp = null;
		String message = null;
		Integer id = companyService.saveCompany(company);
		if(id!=null) {
			message = "Company Saved With Id: "+id;
			resp = new ResponseEntity<>(message,HttpStatus.CREATED);
		}
		else {
			message = "Company Not Saved";
			resp = new ResponseEntity<>(message,HttpStatus.BAD_REQUEST);
		}
		return resp;
	}

	@GetMapping
	public ResponseEntity<?>getAllCompanies(){
		ResponseEntity<?>resp = null;
		String message = null;
		List<Company> list = companyService.getAllCompanies();
		if(list!=null) {
			resp = new ResponseEntity<>(list,HttpStatus.OK);
		}
		else {
			message = "Company Not Found";
			resp = new ResponseEntity<>(message,HttpStatus.BAD_REQUEST);
		}
		return resp;
	}

	@GetMapping("/{id}")
	public ResponseEntity<?>getOneCompanyById(@PathVariable Integer id){
		ResponseEntity<?>resp = null;
		String message = null;
		Company company = companyService.getOneCompany(id);
		if(company!=null) {
			resp = new ResponseEntity<>(company,HttpStatus.OK);
		}
		else {
			message = "Company Not Found";
			resp = new ResponseEntity<>(message,HttpStatus.BAD_REQUEST);
		}
		return resp;
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String>deleteCompanyById(@PathVariable Integer id){
		ResponseEntity<String>resp = null;
		String message = null;
		Company company = companyService.getOneCompany(id);
		if(company!=null) {
			companyService.deleteCompanyById(id);
			message = "Company '"+id+"' Deleted";
			resp = new ResponseEntity<>(message,HttpStatus.OK);
		}
		else {
			message = "Company Not Found To Delete";
			resp = new ResponseEntity<>(message,HttpStatus.BAD_REQUEST);
		}
		return resp;
	}

	@PutMapping
	public ResponseEntity<String>updateCompany(@RequestBody Company company){
		ResponseEntity<String>resp = null;
		String message = null;
		Company cmpObj = companyService.getOneCompany(company.getCmpId());
		if(cmpObj!=null) {
			companyService.updateCompanyById(company);
			message = "Company '"+company.getCmpId()+"' Updated";
			resp = new ResponseEntity<>(message,HttpStatus.OK);
		}
		else {
			message = "Company Not Found To Update";
			resp = new ResponseEntity<>(message,HttpStatus.BAD_REQUEST);
		}
		return resp;
	}
}

