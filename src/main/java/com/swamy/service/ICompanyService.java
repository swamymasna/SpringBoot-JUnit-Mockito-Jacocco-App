package com.swamy.service;

import java.util.List;

import com.swamy.model.Company;

public interface ICompanyService {

	public Integer saveCompany(Company company);
	public List<Company> getAllCompanies();
	public Company getOneCompany(Integer id);
	public void deleteCompanyById(Integer id);
	public void updateCompanyById(Company company);
}
